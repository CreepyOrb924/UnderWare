package io.github.underware.util.chat;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatUtilTest {

    @Test
    void addCountToMatch() {
        String myString = "This is {} my {} test {} string";

        int matches = StringUtils.countMatches(myString, "{}");
        for (int i = 0; i < matches; i++) {
            myString = myString.replaceFirst("(\\{})", "{" + i + "}");
        }

        assertEquals("This is {0} my {1} test {2} string", myString);
    }

    @Test
    void addObjectsToMatch() {
        String myString = "This is {} my {} test {} string";

        int matches = StringUtils.countMatches(myString, "{}");
        for (int i = 0; i < matches; i++) {
            myString = myString.replaceFirst("(\\{})", "{" + i + "}");
        }

        Object[] objects = {"foo", 2, "bar"};
        myString = new MessageFormat(myString).format(objects);

        assertEquals("This is foo my 2 test bar string", myString);
    }

}