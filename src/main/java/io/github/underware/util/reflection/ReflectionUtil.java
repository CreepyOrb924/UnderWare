package io.github.underware.util.reflection;

import io.github.underware.UnderWare;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Madmegsox1
 * @since 14/06/2021
 */
public class ReflectionUtil {

    /**
     * Private helper method
     *
     * @param directory   The directory to start with
     * @param packageName The package name to search for. Will be needed for getting the
     *                    Class object.
     * @param classes     if a file isn't loaded but still is in the directory
     */
    private static void checkDirectory(File directory, String packageName,
                                       ArrayList<Class<?>> classes) throws ClassNotFoundException {
        File tmpDirectory;

        if (directory.exists() && directory.isDirectory()) {
            final String[] files = directory.list();
            assert files != null;
            for (final String file : files) {
                if (file.endsWith(".class")) {
                    try {
                        classes.add(Class.forName(packageName + '.'
                                + file.substring(0, file.length() - 6)));
                    } catch (final NoClassDefFoundError | ClassNotFoundException ignored) {
                        // do nothing. this class hasn't been found by the
                        // loader, and we don't care.
                    }
                } else if ((tmpDirectory = new File(directory, file))
                        .isDirectory()) {
                    checkDirectory(tmpDirectory, packageName + "." + file, classes);
                }
            }
        }
    }

    /**
     * Private helper method.
     *
     * @param connection  the connection to the jar
     * @param packageName the package name to search for
     * @param classes     the current ArrayList of all classes. This method will simply
     *                    add new classes.
     * @throws ClassNotFoundException if a file isn't loaded but still is in the jar file
     * @throws IOException            if it can't correctly read from the jar file.
     */
    private static void checkJarFile(JarURLConnection connection,
                                     String packageName, ArrayList<Class<?>> classes)
            throws ClassNotFoundException, IOException {
        final JarFile jarFile = connection.getJarFile();
        final Enumeration<JarEntry> entries = jarFile.entries();
        String name;

        for (JarEntry jarEntry; entries.hasMoreElements()
                && ((jarEntry = entries.nextElement()) != null); ) {
            name = jarEntry.getName();

            if (name.contains(".class")) {
                name = name.substring(0, name.length() - 6).replace('/', '.');

                if (name.contains(packageName)) {
                    classes.add(Class.forName(name));
                }
            }
        }
    }

    /**
     * Attempts to list all the classes in the specified package as determined
     * by the context class loader
     *
     * @param packageName the package name to search
     * @return a list of classes that exist within that package
     * @throws ClassNotFoundException if something went wrong
     */
    public static ArrayList<Class<?>> getClassesForPackage(String packageName)
            throws ClassNotFoundException {
        final ArrayList<Class<?>> classes = new ArrayList<>();

        try {
            final ClassLoader cld = Thread.currentThread()
                    .getContextClassLoader();

            if (cld == null)
                throw new ClassNotFoundException("Can't get class loader.");

            final Enumeration<URL> resources = cld.getResources(packageName
                    .replace('.', '/'));
            URLConnection connection;

            for (URL url; resources.hasMoreElements()
                    && ((url = resources.nextElement()) != null); ) {
                try {
                    connection = url.openConnection();

                    if (connection instanceof JarURLConnection) {
                        checkJarFile((JarURLConnection) connection, packageName,
                                classes);
                    } else {
                        try {
                            checkDirectory(
                                    new File(URLDecoder.decode(url.getPath(),
                                            "UTF-8")), packageName, classes);
                        } catch (final UnsupportedEncodingException ex) {
                            throw new ClassNotFoundException(
                                    packageName
                                            + " does not appear to be a valid package (Unsupported encoding)",
                                    ex);
                        }
                    }
                } catch (final IOException ioex) {
                    throw new ClassNotFoundException(
                            "IOException was thrown when trying to get all resources for "
                                    + packageName, ioex);
                }
            }
        } catch (final NullPointerException ex) {
            throw new ClassNotFoundException(
                    packageName
                            + " does not appear to be a valid package (Null pointer exception)",
                    ex);
        } catch (final IOException ioex) {
            throw new ClassNotFoundException(
                    "IOException was thrown when trying to get all resources for "
                            + packageName, ioex);
        }

        return classes;
    }

    /**
     * Attempts to invoke and add all the classes in the
     * specified package as determined by the context
     * class loader to a specified list
     *
     * @param packageName the package name to search
     * @param list        the list to add the classes to
     * @param tClass      the type of class we will be invoking
     * @param <T>         the type of object we will be invoking
     * @throws ClassNotFoundException if something went wrong
     */
    @SuppressWarnings("unchecked")
    public static <T> void getAddClassesFromPackageToList(String packageName, List<T> list, Class<T> tClass) throws ClassNotFoundException {
        final List<Class<?>> classes = getClassesForPackage(packageName);

        classes.stream()
                .filter(aClass -> tClass.isAssignableFrom(aClass) && !(aClass == tClass) && !aClass.isInterface())
                .map(aClass -> {
                    try {
                        return aClass.getConstructor().newInstance();
                    } catch (final InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        UnderWare.LOGGER.warn("Unable to instantiate class {}.", aClass);
                        e.printStackTrace();
                        return null;
                    }
                }).forEach(object -> list.add((T) object));
    }

}
