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
 *
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;



public class CodeMaker {
    private ArrayList<CodeValue> userPeg;
    private ArrayList<CodeValue> answerKey;
    private ArrayList<PegState> result;

    public CodeMaker(ArrayList<CodeValue> userCode, ArrayList<CodeValue> answerKey) {
        this.userPeg = userCode;
        this.answerKey = answerKey;
        this.result = new ArrayList<>(GameConfig.CODE_LENGTH);
    }

    public ArrayList<PegState> getResult() {
        ArrayList<CodeValue> tempUser = new ArrayList<>();
        ArrayList<CodeValue> tempAnswer = new ArrayList<>();
        for (int i = 0; i < userPeg.size(); i++) {
            if (userPeg.get(i).equals(answerKey.get(i))) {
                result.add(PegState.RED);
            } else {
                tempUser.add(userPeg.get(i));
                tempAnswer.add(answerKey.get(i));
            }
        }
        ArrayList<CodeValue> unmatchedUser = new ArrayList<>(tempUser);
        ArrayList<CodeValue> unmatchedAnswer = new ArrayList<>(tempAnswer);

        for (CodeValue user : unmatchedUser) {
            if (unmatchedAnswer.contains(user)) {
                result.add(PegState.WHITE);
                unmatchedAnswer.remove(user); // Remove only the first occurrence
            }
        }
        while (result.size() < userPeg.size()) {
            result.add(PegState.EMPTY);
        }
        return result;

    }
}
