/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:00 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: GameManager
 *
 * Description:
 *
 * ****************************************
 */

package org.kcas_mastermind;

/**
 * The main class to start the Mastermind game.
 * This class initializes the game and starts it by calling the `GameManager`.
 */

public class Main {

    /**
     * The entry point of the application.
     * Creates an instance of `GameManager` and starts the game.
     *
     * @param args Command-line arguments (not used in this application).
     */

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startStupidGame(100);
    }

}