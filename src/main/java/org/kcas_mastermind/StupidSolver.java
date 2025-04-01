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
 *
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class StupidSolver extends Exception {

    private CodeValue absentPeg;
    private ArrayList<CodeValue> presentPegs;
    public ArrayList<CodeValue> finalAnswer;
    private int turnCounter;

    public StupidSolver() {
        this.absentPeg = CodeValue.SIX;
        this.turnCounter = 0;
        this.presentPegs = new ArrayList<>();
        this.finalAnswer = new ArrayList<>(Collections.nCopies(GameConfig.CODE_LENGTH, null));

    }

    public Integer solve(ArrayList<CodeValue> answerKey) throws Exception {
        getPresentPegs(answerKey);
        // Checking each of final guess's peg's position
        checkPosition(answerKey);
        boolean result = finalAnswer.equals(answerKey);
        if (result) {
            return turnCounter;
        } else {
            throw new Exception("Error");
        }
    }

    private void getPresentPegs(ArrayList<CodeValue> answerKey) {
        ArrayList<PegState> emptyPegs = new ArrayList<PegState>(Collections.nCopies(GameConfig.CODE_LENGTH, PegState.EMPTY));
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

    private static ArrayList<CodeValue> getUniformGuess(int i) {
        String guessStr = "";
        for (int j = 0; j < GameConfig.CODE_LENGTH; j++) {
            guessStr += Integer.toString(i);
        }
        return CodeValue.parseString(guessStr);
    }

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

    private void checkPosition(ArrayList<CodeValue> answerKey) {
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
//                System.out.println("New count " + newRedCount);
//                System.out.println("Old count " + redCount);
//                System.out.println("Final so far" + finalAnswer.get(i));
//                System.out.println("Key" + answerKey);
//                System.out.println("Current guess" + guess);

                if (newRedCount > redCount) {
                    redCount = newRedCount;
                    finalAnswer.set(i, peg);
                }
            }
        }


    }

}
