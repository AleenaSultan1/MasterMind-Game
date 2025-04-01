/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 3/21/2025
 * Time: 11:52 AM
 *
 * Project: csci205_hw
 * Package: org.kcas_mastermind
 * Class: GameConfig
 *
 * Description:
 * The `GameConfig` class stores configuration constants for the Mastermind game,
 * such as the maximum number of attempts allowed and the length of the secret code.
 * ****************************************
 */

package org.kcas_mastermind;

/**
 * author: Khanh Cao
 * The `GameConfig` class provides configuration constants for the Mastermind game.
 * These constants define the rules and constraints of the game, such as the
 * maximum number of attempts and the length of the secret code.
 */
public class GameConfig {
    public static final int MAX_ATTEMPTS = 12;  // Maximum number of guesses
    public static final int CODE_LENGTH = 4;  // Number of slots in the guess
    public static final int COLOR_NUM = 6;  // Number of possible color to guess
}