package io.github.underware.module.setting.impl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EnumSettingTest {

    enum MyEnum {
        FOO,
        BAR,
        SPAM,
        EGGS
    }

    private final EnumSetting myEnumSetting = new EnumSetting("foo", "foo", MyEnum.FOO);

    @Test
    void getNextEnumValue() {
        Enum<?> anEnum = myEnumSetting.getNextEnumValue();

        assertEquals(MyEnum.BAR, anEnum);
    }

    @Test
    void setValueEnum() {
        myEnumSetting.setValue(MyEnum.EGGS);

        assertEquals(MyEnum.EGGS, myEnumSetting.getValue());
    }

    @Test
    void setValueString() {
        // Given
        Enum<?>[] enumConstants = myEnumSetting.getValue().getDeclaringClass().getEnumConstants();

        // When
        myEnumSetting.setValue(Arrays.stream(enumConstants)
                .filter(anEnum -> anEnum.name().equalsIgnoreCase("bar"))
                .findAny()
                .orElse(enumConstants[0]));

        // Then
        assertEquals(MyEnum.BAR, myEnumSetting.getValue());
    }

    @Test
    void testSetValueInvalidString() {
        // Given
        Enum<?>[] enumConstants = myEnumSetting.getValue().getDeclaringClass().getEnumConstants();

        // When
        myEnumSetting.setValue(Arrays.stream(enumConstants)
                .filter(anEnum -> anEnum.name().equalsIgnoreCase("ham"))
                .findAny()
                .orElse(enumConstants[0]));

        // Then
        assertEquals(MyEnum.FOO, myEnumSetting.getValue());
    }

}