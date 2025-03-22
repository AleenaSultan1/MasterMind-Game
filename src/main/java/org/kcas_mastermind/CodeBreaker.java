/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:03 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: GameManager
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;
import java.util.ArrayList;
import java.util.Scanner;

public class CodeBreaker {
    private String userCode;
    private CodeMaker codeMaker;

    public CodeBreaker() {
        this.userCode = "";
        this.codeMaker = null;
    }

    public ArrayList<CodeValue> getUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        System.out.print("Hello! Welcome to Mastermind!"
                + " Please enter a four digit user code with digits between 1 to 6\n");

        while (!isValid) {
            System.out.print("Enter user code: ");
            userCode = scanner.nextLine();
            if (!isValidInput(userCode)) {
                System.out.println(" Invalid input!");
                System.out.println("Please enter exactly 4 digits between 1 and 6.");
            } else {
                isValid = true;
            }
        }

        return CodeValue.parseString(userCode);
    }

    /**
     * Validates the user input.
     * @param input The user's input string.
     * @return True if valid, otherwise false.
     */
    private boolean isValidInput(String input) {
        // Check length
        if (input.length() != 4) {
            return false;
        }

        // Check if all characters are digits between 1 and 6
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            //Looked this up to see how to test each individual character to
            //Make sure it was between '1' and '6'
            //https://www.geeksforgeeks.org/character-isdigit-method-in-java-with-examples/
            if (!Character.isDigit(ch) || ch < '1' || ch > '6') {
                return false;
            }
        }

        return true;
    }

    public boolean checkUserInput() {
        boolean isDone;
        do {
            getUserInput();
            isDone = true;
        } while (!isDone);

        return isDone;
    }

    public static void main(String[] args) {
        CodeBreaker codeBreaker = new CodeBreaker();
        codeBreaker.checkUserInput();
    }
}


