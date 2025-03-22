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
import java.util.Random;
import java.util.Scanner;


public class GameManager {
    private String secretCode;
    private final CodeBreaker codeBreaker;
    private CodeMaker codeMaker;
    private final ArrayList<PegState> expectedResult;
    private Board board;
    private boolean gameWon;

    public GameManager() {
        this.secretCode = "";
        this.codeBreaker = new CodeBreaker();
        this.expectedResult = new ArrayList<>();
        assignExpectedResult();
        this.codeMaker = new CodeMaker(null, null);
        this.board = Board.updateBoard(null, null);
        this.gameWon = false;
    }

    private void assignExpectedResult() {
        for (int i = 0; i < 4; i++) {
            expectedResult.add(PegState.RED);
        }
    }

    public ArrayList<CodeValue> generateSecretCode() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            secretCode = secretCode + (random.nextInt(6) + 1);
        }
        return CodeValue.parseString(secretCode);
    }

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



