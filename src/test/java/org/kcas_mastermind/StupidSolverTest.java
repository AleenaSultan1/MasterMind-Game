package org.kcas_mastermind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the StupidSolver class.
 * Author: Aleena Sultan for entire file.
 */
class StupidSolverTest {

    private StupidSolver solver;
    private ArrayList<CodeValue> answerKey;

    @BeforeEach
    void setUp() {
        answerKey = new ArrayList<>(Arrays.asList(CodeValue.ONE,
                CodeValue.TWO, CodeValue.THREE, CodeValue.FOUR));
        solver = new StupidSolver(answerKey);
    }

    @Test
    void testSolveCorrectSolution() {
        try {
            int turns = solver.solve();
            assertTrue(turns > 0,
                    "Solver should find the answer in a positive number of turns.");
        } catch (Exception e) {
            fail("Solver threw an exception unexpectedly.");
        }
    }

    @Test
    void testFinalAnswerCorrectness() {
        try {
            solver.solve();
            assertEquals(answerKey, solver.finalAnswer,
                    "Final answer should match the answer key.");
        } catch (Exception e) {
            fail("The solver did not guess the correct answer key.");
        }
    }

    @Test
    void testTurnCounterIncrements() {
        try {
            int turns = solver.solve();
            assertTrue(turns > 0, "Turn counter should be greater than zero.");
        } catch (Exception e) {
            fail("Counter is not incremented.");
        }
    }

}