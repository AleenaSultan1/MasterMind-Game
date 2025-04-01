/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 31/03/2025
 * Time: 7:51 pm
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: MinMaxSolver
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Aleena Sultan
 * The MinMaxSolver class implements Donald Knuth's algorithm for solving
 * Mastermind in 5 guesses or fewer. It uses a minimax approach to determine
 * the optimal next guess based on the remaining possible solutions.
 *
 */
public class MinMaxSolver {
    private final List<ArrayList<CodeValue>> allPossibleCodes;
    private List<ArrayList<CodeValue>> remainingGuesses;
    private ArrayList<CodeValue> bestGuess;
    private final ArrayList<CodeValue> secretCode;
    private int attempts;

    /**
     * author: Aleena Sultan
     * Constructs a new MinMax solver instance.
     * Initializes all possible codes, sets the optimal first guess,
     * and generates a random secret code.
     */
    public MinMaxSolver(ArrayList<CodeValue> secretCode) {
        this.allPossibleCodes = generateAllPossibleCode();
        this.remainingGuesses = new ArrayList<>(allPossibleCodes);
        this.bestGuess = CodeValue.parseString("1122"); // Optimal first guess
        this.secretCode = secretCode;
        this.attempts = 0;
    }

    /**
     * author: Aleena Sultan
     * Generates all possible 4-digit codes using numbers 1-6.
     * @return List of all possible code combinations
     */
    public List<ArrayList<CodeValue>> generateAllPossibleCode() {
        List<ArrayList<CodeValue>> codes = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    for (int l = 1; l <= 6; l++) {
                        codes.add(CodeValue.parseString("" + i + j + k + l));
                    }
                }
            }
        }
        return codes;
    }

    /**
     * author: Aleena Sultan
     * Solves the Mastermind game using Knuth's algorithm.
     * Guarantees solution in 5 guesses or fewer.
     */
    public int solveGame() {
        final int MAX_ATTEMPTS = 5;
        while (attempts < MAX_ATTEMPTS) {
            attempts++;
            ArrayList<PegState> feedback = checkInitialGuess();

            if (isSolved(feedback)) {
                return attempts;
            }

            removeImpossibleCode(feedback);
            bestGuess = getNextBestGuess();
        }

        // Final attempt (5th guess)
        if (remainingGuesses.size() == 1) {
            return MAX_ATTEMPTS;
        } else {
            System.out.println("Algorithm error: Exceeded 5-guess limit");
            return -1;
        }
    }

    /**
     * author: Aleena Sultan
     * Checks the current guess against the secret code.
     * @return The feedback (peg states) for the current guess
     */
    public ArrayList<PegState> checkInitialGuess() {
        return new CodeMaker(bestGuess, secretCode).getResult();
    }

    /**
     * author: Aleena Sultan
     * Removes impossible codes based on feedback from last guess.
     * @param result The feedback (peg states) from last guess
     */
    public List<ArrayList<CodeValue>> removeImpossibleCode(ArrayList<PegState> result) {
        List<ArrayList<CodeValue>> newRemaining = new ArrayList<>();
        for (ArrayList<CodeValue> code : remainingGuesses) {
            if (new CodeMaker(bestGuess, code).getResult().equals(result)) {
                newRemaining.add(code);
            }
        }
        remainingGuesses = newRemaining;
        return remainingGuesses;
    }

    /**
     * author: Aleena Sultan
     * Determines the next best guess using MinMax algorithm.
     * This code was based on the information at GitHub
     * NathanDuran/Mastermind-Five-Guess-Algorithm
     * GitHub
     * @see
     * <a href="https://github.com/NathanDuran/Mastermind-Five-Guess-Algorithm/blob/master/Five-Guess-Algorithm.cpp">...</a>
     *
     * @return The optimal next guess
     */
    public ArrayList<CodeValue> getNextBestGuess() {
        if (remainingGuesses.size() == 1) {
            return remainingGuesses.getFirst();
        }

        int minMaxScore = Integer.MAX_VALUE;
        ArrayList<CodeValue> bestNextGuess = null;

        // First try remaining possible solutions
        for (ArrayList<CodeValue> guess : remainingGuesses) {
            int maxGroupSize = calculateMaxGroupSize(guess);
            if (maxGroupSize < minMaxScore) {
                minMaxScore = maxGroupSize;
                bestNextGuess = guess;
            }
        }

        // If no good candidate, try all possibilities
        if (bestNextGuess == null) {
            for (ArrayList<CodeValue> guess : allPossibleCodes) {
                int maxGroupSize = calculateMaxGroupSize(guess);
                if (maxGroupSize < minMaxScore) {
                    minMaxScore = maxGroupSize;
                    bestNextGuess = guess;
                }
            }
        }

        return bestNextGuess;
    }

    /**
     * author: Aleena Sultan
     * Calculates the maximum group size for a potential guess.
     * This code was based on the information from DeepSeek
     * I asked it to give me a way to organise the remaining possibilities
     *
     * @param guess The guess to evaluate
     * @return Size of the largest group of remaining possibilities
     */
    private int calculateMaxGroupSize(ArrayList<CodeValue> guess) {
        Map<String, Integer> feedbackGroups = new HashMap<>();
        for (ArrayList<CodeValue> code : remainingGuesses) {
            String feedbackKey = new CodeMaker(guess, code).getResult().toString();
            Integer count = feedbackGroups.get(feedbackKey);
            feedbackGroups.put(feedbackKey, (count == null) ? 1 : count + 1);
        }

        int max = 0;
        for (Integer count : feedbackGroups.values()) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    /**
     * author: Aleena Sultan
     * Checks if feedback indicates a correct guess.
     * @param feedback The feedback to check
     * @return true if all pegs are correct (RED), false otherwise
     */
    public boolean isSolved(ArrayList<PegState> feedback) {
        for (PegState peg : feedback) {
            if (peg != PegState.RED) {
                return false;
            }
        }
        return true;
    }

}