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

public class GameManager {
    private String secretCode;
    private CodeBreaker codeBreaker;
    private CodeMaker codeMaker;
    private final ArrayList<PegState> expectedResult;
    private Board board;

    public GameManager() {
        this.secretCode = "";
        this.codeBreaker = new CodeBreaker();
        this.expectedResult = new ArrayList<>();
        assignExpectedResult();
        this.codeMaker = new CodeMaker(null, null);
        this.board = Board.makeNewBoard();
    }

    private void assignExpectedResult() {
        for (int i = 0; i < 4; i++) {
            expectedResult.add(PegState.RED);
        }
    }

    public ArrayList<CodeValue> generateSecretCode() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            secretCode = secretCode + (random.nextInt(7) + 1);
        }
        return CodeValue.parseString(secretCode);
    }

    public void startGame() {
        // Check if the transition is valid
        int count = 0;
        while (count < GameConfig.MAX_ATTEMPTS) {
            count ++ ;
            System.out.println("Guess my code, using numbers between 1 and 6. You have " + GameConfig.MAX_ATTEMPTS + " guesses.");
            System.out.print("Guess " + count + ": ");
            ArrayList<CodeValue> userInput = codeBreaker.getUserInput();
            ArrayList <CodeValue> answerKey = generateSecretCode();
            codeMaker = new CodeMaker(userInput, answerKey );
            ArrayList<PegState> result = codeMaker.getResult();
            board = Board.updateBoard(userInput, result);
            if (result.equals(expectedResult)){
                break;
            }
            System.out.println(board.toString());
        }

    }


}



