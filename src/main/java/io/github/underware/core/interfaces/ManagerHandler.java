package io.github.underware.core.interfaces;

import java.util.List;

/**
 * A way to reduce code duplication for managers that are used to access objects.
 *
 * @param <T> The type of object that will be accessed.
 */
public interface ManagerHandler<T> {

    void init();

    void loadObjects();

    List<T> getObjects();

    List<T> getObjectsAlphabetically();

    default T get(Class<? extends T> aClass) {
        return getObjects().stream()
                .filter(t -> t.getClass() == aClass)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Unable to find " + aClass + "."));
    }

    T get(String name);

}
