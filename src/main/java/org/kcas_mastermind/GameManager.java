/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:03 am
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: GameManager
 *
 * Description:
 * The GameManager class is responsible for managing the flow of the Mastermind game.
 * It handles the generation of the secret code, tracking game state, and managing
 * interactions between the CodeBreaker and CodeMaker.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Random;

/**
 * The GameManager class manages the core logic of the Mastermind game.
 * It generates the secret code, tracks the game state, and handles interactions
 * between the CodeBreaker and CodeMaker.
 */
public class GameManager {
    /** The secret code to be guessed by the player. */
    private String secretCode;

    /** The CodeBreaker instance responsible for handling user input. */
    private final CodeBreaker codeBreaker;

    /** The CodeMaker instance responsible for evaluating user guesses. */
    private CodeMaker codeMaker;

    /** The expected result of a correct guess (all pegs in the RED state). */
    private final ArrayList<PegState> expectedResult;

    /** The game board used to display the current state of the game. */
    private Board board;

    /**
     * Constructs a new GameManager instance.
     * Initializes the secret code, CodeBreaker, expected result, and CodeMaker.
     */
    public GameManager() {
        this.secretCode = "";
        this.codeBreaker = new CodeBreaker();
        this.expectedResult = new ArrayList<>();
        assignExpectedResult();
        this.codeMaker = new CodeMaker(null, null);
        this.board = null;
    }

    /**
     * Assigns the expected result for a correct guess.
     * The expected result is a list of four PegState.RED values,
     * indicating all pegs are correct in both value and position.
     */
    private void assignExpectedResult() {
        for (int i = 0; i < 4; i++) {
            expectedResult.add(PegState.RED);
        }
    }

    /**
     * Generates a random secret code for the game.
     * The code consists of four digits, each ranging from 0 to 6.
     *
     * @return A list of CodeValue objects representing the secret code.
     */
    public ArrayList<CodeValue> generateSecretCode() {
        Random random = new Random();
        //Used ChatGPT
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            codeBuilder.append(random.nextInt(7));  // Generate numbers between 0 and 6
        }
        secretCode = codeBuilder.toString();
        return CodeValue.parseString(secretCode);
    }

    /**
     * Starts the Mastermind game.
     * Manages the game loop, including user input, code evaluation, and board updates.
     * The game continues until the player guesses the correct code or exhausts all attempts.
     */
    public void startGame() {
        int count = 0;
        //board = board.makeNewBoard();
        ArrayList<CodeValue> answerKey = generateSecretCode();  // Generate once
        board = Board.makeNewBoard();
        while (count < GameConfig.MAX_ATTEMPTS) {
            count++;
            ArrayList<CodeValue> userInput = codeBreaker.getUserInput();
            codeMaker = new CodeMaker(userInput, answerKey);
            ArrayList<PegState> result = codeMaker.getResult();
            Board.updateBoard(userInput, result);
            System.out.println(board); // Use toString for display
            if (result.equals(expectedResult)) {
                System.out.println("Congratulations! You've guessed the correct code.");
                break;
            }
        }

        if (count == GameConfig.MAX_ATTEMPTS) {
            System.out.println("Game over! You've used all attempts.");
        }
    }
}