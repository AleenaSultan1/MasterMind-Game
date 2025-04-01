package org.kcas_mastermind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RandomSolver class.
 * Author: Aleena Sultan for entire file.
 */
class RandomSolverTest {

    private RandomSolver randomSolver;

    @BeforeEach
    void setUp() {
        ArrayList<CodeValue> testSecretCode = new ArrayList<>(Arrays.asList(CodeValue.ONE,
                CodeValue.TWO, CodeValue.THREE, CodeValue.FOUR));
        randomSolver = new RandomSolver(testSecretCode);
    }

    @Test
    void testSolveReturnsPositiveTurnCount() {
        int turnCount = randomSolver.solve();
        assertTrue(turnCount > 0, "Solver should return a positive number of turns.");
    }

}
