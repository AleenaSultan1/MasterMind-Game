/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/20/2025
 * Time: 11:30 AM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: Board
 *
 * Description:
 * The `Board` class represents the game board for the Mastermind game.
 * It stores the user's guessed code and the corresponding scoring pegs,
 * which indicate how close the guess is to the secret code.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;

/**
 * The `Board` class represents the game board for the Mastermind game.
 * It maintains the user's guessed code and the corresponding scoring pegs,
 * which provide feedback on the accuracy of the guess.
 */
public class Board {
    // Stores the user's guessed code as a list of `CodeValue` objects
    private static ArrayList<CodeValue> userPeg;

    // Stores the scoring pegs as a list of `PegState` objects
    private static ArrayList<PegState> scoringPeg;

    /**
     * Private constructor to initialize the `Board` with the user's guessed code
     * and the corresponding scoring pegs.
     *
     * @param userPeg    The user's guessed code as a list of `CodeValue` objects.
     * @param scoringPeg The scoring pegs as a list of `PegState` objects.
     */
    private Board(ArrayList<CodeValue> userPeg, ArrayList<PegState> scoringPeg) {
        Board.userPeg = userPeg;
        Board.scoringPeg = scoringPeg;
    }

    public static Board makeNewBoard() {
        return new Board(null, null);
    }
    /**
     * Updates the game board with the latest user guess and scoring pegs.
     *
     * @param userPeg    The user's latest guessed code as a list of `CodeValue` objects.
     * @param scoringPeg The latest scoring pegs as a list of `PegState` objects.
     */
    public void updateBoard(ArrayList<CodeValue> userPeg, ArrayList<PegState> scoringPeg) {
        if (userPeg != null) {
            Board.userPeg = userPeg;
        }
        if (scoringPeg != null) {
            Board.scoringPeg = scoringPeg;
        }
    }

    /**
     * Generates a string representation of the game board.
     * The output includes the user's guessed code and the corresponding scoring pegs.
     *
     * @return A string representation of the board in the format:
     *         "UserGuess --> ScoringPegs".
     */
    @Override
    public String toString() {
        // Build the scoring pegs output
        String scoringOutput = "";
        for (PegState peg : scoringPeg) {
            scoringOutput += peg.toString();
        }

        // Build the user's guessed code output
        String userOutput = "";
        for (CodeValue codeValue : userPeg) {
            userOutput += codeValue.toString();
        }

        // Combine the user's guess and scoring pegs into a single string
        return userOutput + " --> " + scoringOutput;
    }
}