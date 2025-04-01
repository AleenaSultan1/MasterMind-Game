package org.kcas_mastermind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Board class.
 * Author: Aleena Sultan for entire file.
 */

class BoardTest {

    private Board board;
    private ArrayList<CodeValue> testUserPeg;
    private ArrayList<PegState> testScoringPeg;

    @BeforeEach
    void setUp() {
        testUserPeg = new ArrayList<>(Arrays.asList(CodeValue.ONE,
                CodeValue.TWO, CodeValue.THREE, CodeValue.FOUR));
        testScoringPeg = new ArrayList<>(Arrays.asList(PegState.RED,
                PegState.WHITE, PegState.EMPTY, PegState.EMPTY));
        board = Board.makeNewBoard();
    }

    @Test
    void testMakeNewBoard() {
        assertNotNull(board, "Board instance should not be null.");
    }

    @Test
    void testUpdateBoard() {
        board.updateBoard(testUserPeg, testScoringPeg);
        assertEquals(testUserPeg, Board.getUserPeg(),
                "User pegs should be updated correctly.");
        assertEquals(testScoringPeg, Board.getScoringPeg(),
                "Scoring pegs should be updated correctly.");
    }

    @Test
    void testUpdateBoardWithNullValues() {
        board.updateBoard(null, testScoringPeg);
        assertEquals(testScoringPeg, Board.getScoringPeg(),
                "Scoring pegs should be updated correctly even if user pegs are null.");
    }

    @Test
    void testToString() {
        board.updateBoard(testUserPeg, testScoringPeg);
        String expectedString = "1234 --> *+--";
        assertEquals(expectedString, board.toString(),
                "String representation should be formatted correctly.");
    }
}
