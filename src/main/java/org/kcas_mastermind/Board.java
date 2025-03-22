/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/20/2025
 * Time: 11:30 AM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: Board
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private static ArrayList<CodeValue> userPeg;
    private static ArrayList<PegState> scoringPeg;

    private Board(ArrayList<CodeValue> userPeg, ArrayList<PegState> scoringPeg) {
        this.userPeg = userPeg;
        this.scoringPeg = scoringPeg;
    }

    public static Board updateBoard(ArrayList<CodeValue> userPeg, ArrayList<PegState> scoringPeg) {
        if (userPeg.size() != GameConfig.CODE_LENGTH | scoringPeg.size() != GameConfig.CODE_LENGTH) {
            System.out.println("Error: invalid board length");
            return null;
        }
        return new Board(userPeg, scoringPeg);
    }

    public static Board makeNewBoard() {
        userPeg = new ArrayList<CodeValue>();
        scoringPeg = new ArrayList<PegState>();
        System.out.println("Clearing/Making new board");
        return new Board(userPeg, scoringPeg);
    }

    public ArrayList<PegState> formatScoringPeg() {
        Collections.sort(scoringPeg);
        return scoringPeg;
    }

    @Override
    public String toString() {
        String scoringOutput = "";
        for (PegState peg : scoringPeg) {
            scoringOutput += peg.toString();
        }

        String userOutput = "";
        for (CodeValue codeValue : userPeg) {
            userOutput += codeValue.toString();
        }
        return userOutput + " --> " + scoringOutput;
    }
}
