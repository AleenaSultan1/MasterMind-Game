package org.kcas_mastermind;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the CodeBreaker class.
 * Author: Aleena Sultan for entire file.
 */
class CodeBreakerTest {
    private CodeBreaker codeBreaker;

    @BeforeEach
    void setUp() {
        codeBreaker = new CodeBreaker();
    }

    @Test
    void testIsValidCode() {
        assertTrue(codeBreaker.isValidInput("1234"), "Valid input should return true");
    }

    @Test
    void testIsValidLength() {
        assertFalse(codeBreaker.isValidInput("123"),
                "Input with less than 4 digits should return false");
        assertFalse(codeBreaker.isValidInput("12345"),
                "Input with more than 4 digits should return false");
    }

    @Test
    void testIsValidCharacters() {
        assertFalse(codeBreaker.isValidInput("12a4"),
                "Input containing non-numeric characters should return false");
        assertFalse(codeBreaker.isValidInput("0789"),
                "Input containing numbers outside the valid range should return false");
    }



}