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
 * Description: A Mastermind solver that uses random guessing to find the solution code.
 * This implementation generates random codes until it matches the answer key.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Random;

/**
 * Author: Khanh Cao (whole file)
 * A solver that attempts to guess the Mastermind code through random generation.
 * Continues generating random codes until the correct solution is found.
 */
public class RandomSolver {
    private final ArrayList<PegState> expectedResult;
    private int turnCount;
    private final ArrayList<CodeValue> answerKey;

    /**
     * Constructs a RandomSolver with the specified answer key.
     * Initializes the expected result to be all RED pegs.
     *
     * @param answerKey the secret code to be solved
     */
    public RandomSolver(ArrayList<CodeValue> answerKey) {
        this.expectedResult = new ArrayList<>();
        assignExpectedResult();
        this.answerKey = answerKey;
        this.turnCount = 0;
    }

    /**
     * Attempts to solve the puzzle by generating random guesses.
     * Continues until a guess matches the answer key exactly.
     *
     * @return the number of guesses taken to find the solution
     */
    public int solve() {
        ArrayList<PegState> result = new ArrayList<>();
        while (!result.equals(expectedResult)) {
            StringBuilder guessStr = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
                guessStr.append(random.nextInt(6) + 1);
            }
            ArrayList<CodeValue> guess = CodeValue.parseString(guessStr.toString());
            CodeMaker codeMaker = new CodeMaker(guess, answerKey);
            result = codeMaker.getResult();
            turnCount++;
        }
        return turnCount;
    }

    /**
     * Initializes the expected result list with all RED PegStates.
     * This represents a completely correct guess where all pegs
     * are the right color in the right position.
     */
    private void assignExpectedResult() {
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            expectedResult.add(PegState.RED);
        }
    }
}