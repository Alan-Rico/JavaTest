package com.alan.javatest.util;

import org.junit.Test;

import static com.alan.javatest.util.PasswordUtil.SecurityLevel.*;
import static org.junit.Assert.*;

public class PasswordUtilTest {

    @Test
    public void waek_when_has_less_that_8_letters(){
        assertEquals(WEAK, PasswordUtil.assessPasword("123!av"));
    }

    @Test
    public void waek_when_has_only_letters(){
        assertEquals(WEAK, PasswordUtil.assessPasword("abhstwe"));
    }

    @Test
    public void medium_when_has_letters_and_numbers(){
        assertEquals(MEDIUM, PasswordUtil.assessPasword("abcd1234"));
    }

    @Test
    public void strong_when_has_letters_numbers_and_symbols(){
        assertEquals(STRONG, PasswordUtil.assessPasword("abcd1234!"));
    }

}