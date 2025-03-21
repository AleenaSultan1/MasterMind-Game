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
    private GameState currentGameState;
    private CodeBreaker codeBreaker;
    private final ArrayList<String> expectedResult;

    public GameManager() {
        this.secretCode = "";
        this.currentGameState = GameState.IN_PROGRESS;
        this.codeBreaker = new CodeBreaker();
        this.expectedResult = new ArrayList<>();
    }

    private void assignExpectedResult() {
        for (int i = 0; i < 4; i++) {
            expectedResult.add("*");
        }
    }

    public CodeValue generateSecretCode() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            secretCode = secretCode + random.nextInt(7);
        }
        return CodeValue.parseString(secretCode);
    }

    public void startGame(GameState newState) {
        // Check if the transition is valid
        int count = 0;
        while (currentGameState == GameState.IN_PROGRESS && count < GameConfig.MAX_ATTEMPTS) {
            count +=1 ;
            String userInput = codeBreaker.getUserInput();
            codeBreaker.sendUserCodeToCodeMaker();
            ArrayList<PegState> result = CodeMaker.getResult();
            if (result == expectedResult){
                currentGameState = GameState.END;
                System.out.println("Congratulations! You won!");
                break;
            }

        }


    }

    //public ArrayList<>()
}



