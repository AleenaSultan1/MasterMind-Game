package org.kcas_mastermind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MinMaxSolverTest {
    private MinMaxSolver solver;

    @BeforeEach
    void setUp() {
        solver = new MinMaxSolver();
    }

    @Test
    void testGenerateAllPossibleCode() {
        List<ArrayList<CodeValue>> allCodes = solver.generateAllPossibleCode();
        assertEquals(1296, allCodes.size(), "Total possible codes should be 1296");
    }

    @Test
    void testCheckInitialGuess() {
        ArrayList<PegState> feedback = solver.checkInitialGuess();
        assertNotNull(feedback, "Feedback should not be null");
    }

    @Test
    void testRemoveImpossibleCode() {
        ArrayList<PegState> feedback = solver.checkInitialGuess();
        List<ArrayList<CodeValue>> remainingGuesses = solver.removeImpossibleCode(feedback);
        assertTrue(remainingGuesses.size() < 1296, "Remaining guesses should be reduced");
    }

    @Test
    void testIsSolved() {
        ArrayList<PegState> correctFeedback = new ArrayList<>();
        correctFeedback.add(PegState.RED);
        correctFeedback.add(PegState.RED);
        correctFeedback.add(PegState.RED);
        correctFeedback.add(PegState.RED);
        assertTrue(solver.isSolved(correctFeedback),
                "isSolved should return true for all RED pegs");
    }

    @Test
    void testSolveGame() {
        int attempts = solver.solveGame();
        assertTrue(attempts > 0 && attempts <= 5,
                "Game should be solved in 5 or fewer attempts");
    }
}