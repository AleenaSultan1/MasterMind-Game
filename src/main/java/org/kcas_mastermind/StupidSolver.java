/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/28/2025
 * Time: 3:16 PM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: StupidSolver
 *
 * Description:
 * A naive brute-force solver for the Mastermind game. It first identifies the
 * CodeValue colors present in the code using uniform guesses, then determines
 * their correct positions by substitution.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Author: Khanh Cao
 * The StupidSolver class implements a simple algorithm for solving a Mastermind code.
 * It detects present CodeValues by brute force, and checks all positions by trial.
 */
public class StupidSolver {

    private final ArrayList<CodeValue> answerKey;
    private CodeValue absentPeg;
    private final ArrayList<CodeValue> presentPegs;
    public ArrayList<CodeValue> finalAnswer;
    private int turnCounter;

    /**
     * Author: Khanh Cao
     * Constructs a StupidSolver with the specified answer key.
     *
     * @param answerKey the secret code to solve
     */
    public StupidSolver(ArrayList<CodeValue> answerKey) {
        this.absentPeg = CodeValue.SIX;
        this.answerKey = answerKey;
        this.turnCounter = 0;
        this.presentPegs = new ArrayList<>();
        this.finalAnswer = new ArrayList<>(Collections.nCopies(GameConfig.CODE_LENGTH, null));
    }

    /**
     * Author: Khanh Cao
     * Solves the Mastermind code by determining which pegs are present,
     * and then checking their positions one by one.
     *
     * @return the number of turns used to solve the code
     * @throws Exception if the final answer does not match the answer key
     */
    public Integer solve() throws Exception {
        getPresentPegs();
        // Checking each of final guess's peg's position
        checkPosition();
        boolean result = finalAnswer.equals(answerKey);
        if (result) {
            return turnCounter;
        } else {
            throw new Exception("Error");
        }
    }

    /**
     * Author: Khanh Cao
     * Identifies which CodeValues are present in the answerKey by submitting
     * uniform guesses and collecting those that yield RED pegs in the result.
     */
    private void getPresentPegs() {
        ArrayList<PegState> emptyPegs =
                new ArrayList<>(Collections.nCopies(GameConfig.CODE_LENGTH, PegState.EMPTY));
        outerLoop:
        for (int i = 1; i < GameConfig.COLOR_NUM + 1; i++) {
            turnCounter++;
            ArrayList<CodeValue> guessPeg = getUniformGuess(i);

            // Pass to codeMaker to check guess
            CodeMaker codeMaker = new CodeMaker(guessPeg, answerKey);
            ArrayList<PegState> guessResult = codeMaker.getResult();

            if (guessResult.equals(emptyPegs)) {
                absentPeg = CodeValue.fromInt((char) (i + '0'));
                continue;
            }
            // Add however many colors that returns red to final guess
            for (PegState pegState : guessResult) {
                if (pegState == PegState.RED) {
                    presentPegs.add(CodeValue.fromInt((char) (i + '0')));
                    if (presentPegs.size() == GameConfig.CODE_LENGTH) {
                        break outerLoop;
                    }
                }
            }
        }
    }

    /**
     * Author: Khanh Cao
     * Creates a guess where all values are the same CodeValue.
     *
     * @param i the integer value of the CodeValue to fill
     * @return a list of CodeValues forming the uniform guess
     */
    private static ArrayList<CodeValue> getUniformGuess(int i) {
        return CodeValue.parseString(Integer.toString(i).repeat(GameConfig.CODE_LENGTH));
    }

    /**
     * Author: Khanh Cao
     * Constructs a guess with a specific peg placed at a given index, and
     * all other unknowns filled with the absentPeg.
     *
     * @param presentPeg the CodeValue to test
     * @param index      the index at which to place the peg
     * @return a guess with the peg inserted
     */
    private ArrayList<CodeValue> createTestGuess(CodeValue presentPeg, int index) {
        ArrayList<CodeValue> guess = new ArrayList<>();
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            if (finalAnswer.get(i) != null) {
                guess.add(finalAnswer.get(i));
            } else {
                guess.add(absentPeg);
            }
        }
        guess.set(index, presentPeg);
        return guess;
    }

    /**
     * Author: Khanh Cao
     * Attempts to place each present peg into the correct position
     * by checking the number of RED pegs returned with each test guess.
     */
    private void checkPosition() {
        int redCount = 0;
        for (CodeValue peg : presentPegs) {
            for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
                if (finalAnswer.get(i) != null) {
                    continue;
                }
                ArrayList<CodeValue> guess = createTestGuess(peg, i);
                CodeMaker codeMaker = new CodeMaker(guess, answerKey);
                ArrayList<PegState> guessResult = codeMaker.getResult();
                turnCounter++;
                int newRedCount = Collections.frequency(guessResult, PegState.RED);
                if (newRedCount > redCount) {
                    redCount = newRedCount;
                    finalAnswer.set(i, peg);
                }
            }
        }
    }
}
