/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:00 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: GameManager
 *
 * Description:
 * The `CodeBreaker` class is responsible for handling user input in the Mastermind game.
 * It prompts the user to enter a 4-digit code, validates the input to ensure it consists
 * of digits between 1 and 6, and converts the input into a list of `CodeValue` objects
 * for further processing by the game logic.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `CodeBreaker` class is responsible for handling user input for the Mastermind game.
 * It validates the input and converts it into a list of `CodeValue` objects for further processing.
 */
public class CodeBreaker {
    private String userCode;
    private Board board;


    /**
     * Default constructor for the `CodeBreaker` class.
     * Initializes the `userCode` to an empty string.
     */
    public CodeBreaker() {
        this.userCode = "";
        this.board = Board.makeNewBoard();
    }

    /**
     * Prompts the user to input a 4-digit code and validates the input.
     * The input must consist of digits between 1 and 6 (inclusive).
     *
     * @return An `ArrayList` of `CodeValue` objects representing the validated user input.
     */
    public ArrayList<CodeValue> getUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false; // Flag to track if the input is valid

        // Keep asking for input until a valid code is provided
        while (!isValid) {
            userCode = scanner.nextLine(); // Read user input

            // Validate the input
            if (!isValidInput(userCode)) {
                System.out.println("Invalid input!");
                System.out.print("Please enter exactly 4 digits between 1 and 6: ");
            } else {
                isValid = true; // Exit the loop if input is valid
            }
        }

        // Parse the valid input into a list of `CodeValue` objects
        return CodeValue.parseString(userCode);
    }

    /**
     * Validates the user's input to ensure it meets the requirements.
     * The input must be 4 characters long, and each character must be a digit between 1 and 6.
     *
     * @param input The user's input string to validate.
     * @return `true` if the input is valid, `false` otherwise.
     */
    private boolean isValidInput(String input) {
        // Check if the input length is exactly 4 characters
        if (input.length() != 4) {
            return false;
        }

        // Check each character in the input
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Ensure the character is a digit between 1 and 6
            if (!Character.isDigit(ch) || ch < '1' || ch > '6') {
                return false;
            }
        }

        // If all checks pass, the input is valid
        return true;
    }

    /**
     * Starts the Mastermind game.
     * Manages the game loop, including user input, code evaluation, and board updates.
     * The game continues until the player guesses the correct code or exhausts all attempts.
     */
    public ArrayList<PegState> startRound(ArrayList<CodeValue> answerKey) {
        ArrayList<CodeValue> userInput = getUserInput();
        CodeMaker codeMaker = new CodeMaker(userInput, answerKey);
        ArrayList<PegState> result = codeMaker.getResult();
        board.updateBoard(userInput, result);
        return result;
    }
}