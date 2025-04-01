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

public class StupidSolver {

    private CodeValue absentPeg;
    private ArrayList<CodeValue> presentPegs;
    private ArrayList<CodeValue> finalAnswer;
    private int turnCounter;

    public StupidSolver() {
        this.turnCounter = 0;
        this.presentPegs = new ArrayList<>();
        this.finalAnswer = new ArrayList<>(Collections.nCopies(GameConfig.CODE_LENGTH, null));

    }

    public Integer solve(ArrayList<CodeValue> answerKey) {
        getPresentPegs(answerKey);
        // Checking each of final guess's peg's position
        boolean result = determineFinalPosition(answerKey);
        if (result) {
            return turnCounter;
        } else {
            return 0;
        }
    }

    private void getPresentPegs(ArrayList<CodeValue> answerKey) {
        ArrayList<PegState> emptyPegs = new ArrayList<PegState>(Collections.nCopies(GameConfig.CODE_LENGTH, PegState.EMPTY));
        outerLoop:
        for (int i = 1; i < GameConfig.COLOR_NUM; i++) {
            turnCounter += 1;
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


    private

    private boolean determineFinalPosition(ArrayList<CodeValue> answerKey) {
        for (CodeValue peg : new HashSet<>(presentPegs)) {
            int count = Collections.frequency(presentPegs, peg);
            checkPosition(answerKey, peg, count);
        }

        return finalAnswer.equals(answerKey);
    }


    private void checkPosition(ArrayList<CodeValue> answerKey, CodeValue currentPeg, int count) {
        List<Integer> possiblePositions = new ArrayList<>();
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            if (finalAnswer.get(i) == null) {
                possiblePositions.add(i);
            }
        }

        // Implement binary search
        while (count > 0 && !possiblePositions.isEmpty()) {
            // Binary search-like approach - test half the positions at once
            int split = (possiblePositions.size() + 1) / 2;
            List<Integer> testPositions = possiblePositions.subList(0, split);

            // Create test guess
            ArrayList<CodeValue> testGuess = createTestGuess(currentPeg, testPositions);

            // Get response
            turnCounter += 1;
            CodeMaker codeMaker = new CodeMaker(testGuess, answerKey);
            List<PegState> response = codeMaker.getResult();

            int correctPositions = Collections.frequency(response, PegState.RED);

            if (correctPositions > 0) {
                // Some of these positions are correct
                for (int i = 0; i < correctPositions; i++) {
                    finalAnswer.set(testPositions.get(i), currentPeg);
                    count--;
                }
                possiblePositions = testPositions.subList(correctPositions, testPositions.size());
            } else {
                // None of these positions are correct
                possiblePositions = possiblePositions.subList(split, possiblePositions.size());
            }
        }
    }

    private ArrayList<CodeValue> createTestGuess(CodeValue peg, List<Integer> positions) {
        ArrayList<CodeValue> guess = new ArrayList<>();
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            if (positions.contains(i)) {
                guess.add(peg);
            } else if (finalAnswer.get(i) != null) {
                guess.add(finalAnswer.get(i));
            } else {
                guess.add(absentPeg);
            }
        }
        return guess;
    }
}
