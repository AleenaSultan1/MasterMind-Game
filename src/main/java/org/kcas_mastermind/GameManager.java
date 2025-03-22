/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:03 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
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
import java.util.Scanner;

/**
 * The GameManager class manages the core logic of the Mastermind game.
 * It generates the secret code, tracks the game state, and handles interactions
 * between the CodeBreaker and CodeMaker.
 */

public class GameManager {
    private String secretCode;
    private final CodeBreaker codeBreaker;
    private CodeMaker codeMaker;
    private final ArrayList<PegState> expectedResult;
    private Board board;
    private boolean gameWon;

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
        this.board = Board.updateBoard(null, null);
        this.gameWon = false;
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
        for (int i = 0; i < 4; i++) {
            secretCode = secretCode + (random.nextInt(6) + 1);
        }
        return CodeValue.parseString(secretCode);
    }

    /**
     * Starts the Mastermind game.
     * Manages the game loop, including user input, code evaluation, and board updates.
     * The game continues until the player guesses the correct code or exhausts all attempts.
     */
    public void startGame() {
        Scanner scnr = new Scanner(System.in);

        boolean isDone;
        // Loop until the user is done
        System.out.println("Guess my 4 digit code, using numbers between 1 and 6. You have " + GameConfig.MAX_ATTEMPTS + " guesses.");
        do {
            int count = 0;
            while (count < GameConfig.MAX_ATTEMPTS) {
                count++;
                System.out.print("Guess " + count + ": ");
                ArrayList<CodeValue> userInput = codeBreaker.getUserInput();
                ArrayList<CodeValue> answerKey = generateSecretCode();
                codeMaker = new CodeMaker(userInput, answerKey);
                ArrayList<PegState> result = codeMaker.getResult();
                board = Board.updateBoard(userInput, result);
                if (result.equals(expectedResult)) {
                    gameWon = true;
                    break;
                }
                System.out.println(board);
            }
            if (gameWon) {
                System.out.println("Game won!");
            } else {
                System.out.println("Game lost!");
            }

            // Ask if user wants to continue
            System.out.print("\nTry again? [y | n] ");
            isDone = scnr.nextLine().strip().equalsIgnoreCase("n");
        } while (!isDone);
    }


}



