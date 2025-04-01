/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/31/2025
 * Time: 9:13 PM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: RandomSolver
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Random;

public class RandomSolver {
    private final ArrayList<PegState> expectedResult;
    private int turnCount;
    private final ArrayList<CodeValue> answerKey;

    public RandomSolver(ArrayList<CodeValue> answerKey) {
        this.expectedResult = new ArrayList<>();
        assignExpectedResult();
        this.answerKey = new ArrayList<>();
        this.turnCount = 0;
    }

    public int solve() {
        ArrayList<PegState> result = new ArrayList<>();
        while (!result.equals(expectedResult)) {
            String guessStr = "";
            Random random = new Random();
            for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
                guessStr = guessStr + (random.nextInt(6) + 1);
            }
            ArrayList<CodeValue> guess = CodeValue.parseString(guessStr);
            CodeMaker codeMaker = new CodeMaker(guess, answerKey);
            result = codeMaker.getResult();
            turnCount++;
        }
        return turnCount;
    }
    private void assignExpectedResult() {
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            expectedResult.add(PegState.RED);
        }
    }
}
