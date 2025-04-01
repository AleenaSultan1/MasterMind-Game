package org.kcas_mastermind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the GameManger class.
 * Author: Aleena Sultan for entire file.
 */

class GameManagerTest {
    private GameManager gameManager;

    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
    }

    @Test
    void testGenerateSecretCode() {
        ArrayList<CodeValue> secretCode = gameManager.generateSecretCode();
        assertNotNull(secretCode, "Generated secret code should not be null.");
        assertEquals(GameConfig.CODE_LENGTH, secretCode.size(),
                "Generated code should have correct length.");
    }

    @Test
    void testAssignExpectedResult() {
        ArrayList<PegState> expectedResult = new ArrayList<>();
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            expectedResult.add(PegState.RED);
        }
        assertEquals(expectedResult, gameManager.getExpectedResult(),
                "Expected result should match the predefined value.");
    }

    @Test
    void testStartRandomGame() {
        assertDoesNotThrow(() -> gameManager.startRandomGame(5),
                "Random game simulation should not throw an exception.");
    }

    @Test
    void testStartStupidGame() {
        assertDoesNotThrow(() -> gameManager.startStupidGame(5),
                "Stupid game simulation should not throw an exception.");
    }

    @Test
    void testStartMinMaxGame() {
        assertDoesNotThrow(() -> gameManager.startMinMaxGame(5),
                "MinMax game simulation should not throw an exception.");
    }

}
