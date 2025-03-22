/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/21/2025
 * Time: 1:27 PM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: CodeMaker
 *
 * Description:
 * The `CodeMaker` class is responsible for evaluating the user's guessed code
 * against the secret answer key in the Mastermind game. It generates feedback
 * in the form of `PegState` values (RED, WHITE, or EMPTY) to indicate how close
 * the guess is to the answer key.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;

/**
 * The `CodeMaker` class evaluates the user's guessed code against the secret
 * answer key and generates feedback in the form of `PegState` values.
 */
public class CodeMaker {
    // The user's guessed code as a list of `CodeValue` objects
    private final ArrayList<CodeValue> userPeg;

    // The secret answer key as a list of `CodeValue` objects
    private final ArrayList<CodeValue> answerKey;

    // The result of the evaluation as a list of `PegState` objects
    private final ArrayList<PegState> result;

    /**
     * Constructs a `CodeMaker` instance with the user's guessed code and the secret answer key.
     *
     * @param userPeg   The user's guessed code as a list of `CodeValue` objects.
     * @param answerKey The secret answer key as a list of `CodeValue` objects.
     */
    public CodeMaker(ArrayList<CodeValue> userPeg, ArrayList<CodeValue> answerKey) {
        this.userPeg = userPeg;
        this.answerKey = answerKey;
        this.result = new ArrayList<>(GameConfig.CODE_LENGTH); // Initialize the result list
    }

    /**
     * Evaluates the user's guessed code against the secret answer key and generates feedback.
     * The feedback is represented as a list of `PegState` values:
     * - RED: Correct value in the correct position.
     * - WHITE: Correct value but in the wrong position.
     * - EMPTY: Incorrect value.
     *
     * @return A list of `PegState` values representing the feedback for the user's guess.
     */
    public ArrayList<PegState> getResult() {
        // Temporary lists to store unmatched values
        ArrayList<CodeValue> tempUser = new ArrayList<>();
        ArrayList<CodeValue> tempAnswer = new ArrayList<>();

        // First pass: Check for exact matches (RED pegs)
        for (int i = 0; i < userPeg.size(); i++) {
            if (userPeg.get(i).equals(answerKey.get(i))) {
                result.add(PegState.RED); // Correct value in the correct position
            } else {
                tempUser.add(userPeg.get(i)); // Add to temporary lists for further processing
                tempAnswer.add(answerKey.get(i));
            }
        }

        // Second pass: Check for correct values in the wrong position (WHITE pegs)
        ArrayList<CodeValue> unmatchedUser = new ArrayList<>(tempUser);
        ArrayList<CodeValue> unmatchedAnswer = new ArrayList<>(tempAnswer);

        for (CodeValue user : unmatchedUser) {
            if (unmatchedAnswer.contains(user)) {
                result.add(PegState.WHITE); // Correct value but in the wrong position
                unmatchedAnswer.remove(user); // Remove only the first occurrence to avoid duplicates
            }
        }

        // Fill the remaining slots with EMPTY pegs
        while (result.size() < userPeg.size()) {
            result.add(PegState.EMPTY); // Incorrect value
        }

        return result;
    }
}