/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/20/2025
 * Time: 11:25 AM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: PegState
 *
 * Description:
 *
 * ****************************************
 */


package org.kcas_mastermind;

/**
 * author: Aleena Sultan
 * Represents the possible states of a peg in the game.
 * - RED: Indicates a correct value in the correct position.
 * - WHITE: Indicates a correct value but in the wrong position.
 * - EMPTY: Indicates an incorrect value.
 */

public enum PegState {
    RED("*"),
    WHITE("+"),
    EMPTY("-")
    ;
    private final String peg;

    PegState(String peg) {
        this.peg = peg;
    }

    @Override
    public String toString() {
        return peg;
    }
}
