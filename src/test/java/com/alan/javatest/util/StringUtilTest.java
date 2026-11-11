package com.alan.javatest.util;


public class StringUtilTest {
    public static void main(String[] args) {
        assertEquals(StringUtil.repeat("Hola", 3), "HolaHolaHola");

        assertEquals(StringUtil.repeat("Hola", 1), "Hola");
    }

    private static void assertEquals(String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new RuntimeException(actual + " is not equal to expected " + expected);
        }
    }
}