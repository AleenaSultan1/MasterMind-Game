package org.kcas_mastermind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CodeMaker class.
 * Author: Aleena Sultan for entire file.
 */
class CodeMakerTest {
    private ArrayList<CodeValue> answerKey;

    @BeforeEach
    void setUp() {
        // Initialize the answer key with a predefined sequence
        answerKey = new ArrayList<>();
        answerKey.add(CodeValue.ONE);
        answerKey.add(CodeValue.TWO);
        answerKey.add(CodeValue.THREE);
        answerKey.add(CodeValue.FOUR);
    }

    @Test
    void testAllCorrectPositions() {
        ArrayList<CodeValue> userGuess = new ArrayList<>(answerKey);
        CodeMaker codeMaker = new CodeMaker(userGuess, answerKey);
        ArrayList<PegState> result = codeMaker.getResult();

        assertEquals(4, result.size());
        assertTrue(result.stream().allMatch(peg -> peg == PegState.RED), "All pegs should be RED");
    }

    @Test
    void testAllIncorrect() {
        ArrayList<CodeValue> userGuess = new ArrayList<>();
        userGuess.add(CodeValue.FIVE);
        userGuess.add(CodeValue.FIVE);
        userGuess.add(CodeValue.FIVE);
        userGuess.add(CodeValue.FIVE);
        CodeMaker codeMaker = new CodeMaker(userGuess, answerKey);
        ArrayList<PegState> result = codeMaker.getResult();

        assertEquals(4, result.size());
        assertTrue(result.stream().allMatch(peg -> peg == PegState.EMPTY), "All pegs should be EMPTY");
    }

    @Test
    void testCorrectValuesWrongPositions() {
        ArrayList<CodeValue> userGuess = new ArrayList<>();
        userGuess.add(CodeValue.FOUR);
        userGuess.add(CodeValue.THREE);
        userGuess.add(CodeValue.TWO);
        userGuess.add(CodeValue.ONE);
        CodeMaker codeMaker = new CodeMaker(userGuess, answerKey);
        ArrayList<PegState> result = codeMaker.getResult();

        assertEquals(4, result.size());
        assertTrue(result.stream().allMatch(peg -> peg == PegState.WHITE), "All pegs should be WHITE");
    }

    @Test
    void testMixedResults() {
        ArrayList<CodeValue> userGuess = new ArrayList<>();
        userGuess.add(CodeValue.ONE);
        userGuess.add(CodeValue.THREE);
        userGuess.add(CodeValue.TWO);
        userGuess.add(CodeValue.FIVE);
        CodeMaker codeMaker = new CodeMaker(userGuess, answerKey);
        ArrayList<PegState> result = codeMaker.getResult();

        assertEquals(4, result.size());
        assertEquals(PegState.RED, result.get(0), "First peg should be RED");
        assertEquals(PegState.WHITE, result.get(1), "Second peg should be WHITE");
        assertEquals(PegState.WHITE, result.get(2), "Third peg should be WHITE");
        assertEquals(PegState.EMPTY, result.get(3), "Fourth peg should be EMPTY");
    }
}
