/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Aleena Sultan
 * Date: 20/03/2025
 * Time: 11:03 am
 *
 * Project: csci205_hw
 * Package: PACKAGE_NAME
 * Class: GameManager
 *
 * Description:
 * The GameManager class is responsible for managing the flow of the Mastermind game.
 * It handles the generation of the secret code, tracking game state, and managing
 * interactions between the CodeBreaker and CodeMaker.
 * ****************************************
 */

package org.kcas_mastermind;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * author: Aleena Sultan
 * The GameManager class manages the core logic of the Mastermind game.
 * It generates the secret code, tracks the game state, and handles interactions
 * between the CodeBreaker and CodeMaker.
 */

public class GameManager {
    private String secretCode;
    private final ArrayList<PegState> expectedResult;
    private final Board board;
    private final CodeBreaker codeBreaker;


    /**
     * author: Aleena Sultan
     * Constructs a new GameManager instance.
     * Initializes the secret code, CodeBreaker, expected result, and CodeMaker.
     */
    public GameManager() {
        this.secretCode = "";
        this.expectedResult = new ArrayList<>();
        assignExpectedResult();
        this.board = Board.makeNewBoard();
        this.codeBreaker = new CodeBreaker();

    }

    /**
     * author: Aleena Sultan
     * Assigns the expected result for a correct guess.
     * The expected result is a list of four PegState.RED values,
     * indicating all pegs are correct in both value and position.
     */
    private void assignExpectedResult() {
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            expectedResult.add(PegState.RED);
        }
    }

    /**
     * author: Aleena Sultan
     * Generates a random secret code for the game.
     * The code consists of four digits, each ranging from 0 to 6.
     *
     * @return A list of CodeValue objects representing the secret code.
     */
    public ArrayList<CodeValue> generateSecretCode() {
        Random random = new Random();
        secretCode = "";
        for (int i = 0; i < GameConfig.CODE_LENGTH; i++) {
            secretCode = secretCode + (random.nextInt(6) + 1);
        }
        return CodeValue.parseString(secretCode);
    }

    /**
     * author: Khanh Cao
     * Starts the Mastermind game.
     * Manages the game loop, including user input, code evaluation, and board updates.
     * The game continues until the player guesses the correct code or exhausts all attempts.
     */
    public void startGame() {
        Scanner scnr = new Scanner(System.in);
        boolean gameWon = false;
        boolean isDone;

        ArrayList<CodeValue> answerKey = generateSecretCode();

        // Loop until the user is done
        System.out.println("Guess my " + GameConfig.CODE_LENGTH
                + " digit code, using numbers between 1 and 6. You have "
                + GameConfig.MAX_ATTEMPTS + " guesses.");
        do {
            int count = 0;
            while (count < GameConfig.MAX_ATTEMPTS) {
                count++;
                System.out.print("Guess " + count + ": ");
                ArrayList<CodeValue> userInput = codeBreaker.getUserInput();
                ArrayList<PegState> result = codeBreaker.startRound(userInput, answerKey);
                if (result.equals(expectedResult)) {
                    gameWon = true;
                    break;
                }
                System.out.println(board);
            }
            if (gameWon) {
                System.out.println("Game won!");
            } else {
                System.out.println("Game lost!");
                System.out.println("The answer was " + answerKey);
            }

            // Ask if user wants to continue
            System.out.print("\nTry again? [y | n] ");
            isDone = scnr.nextLine().strip().equalsIgnoreCase("n");
        } while (!isDone);
        scnr.close();
    }

    /**
     * author: Khanh Cao
     * Starts a series of games using the RandomSolver algorithm.
     *
     * @param gameNumber The number of games to simulate.
     */
    public void startRandomGame(int gameNumber) {
        long startTime = System.nanoTime();
        ArrayList<Integer> turnResults = new ArrayList<>();

        for (int i = 0; i < gameNumber; i++) {
            ArrayList<CodeValue> answerKey = generateSecretCode();
            RandomSolver randomSolver = new RandomSolver(answerKey);
            int turnCount = randomSolver.solve();
            turnResults.add(turnCount);
        }
        long endTime = System.nanoTime();
        double durationInSec = (endTime - startTime) / 1e9;

        printSolverStats("RandomSolver", turnResults, durationInSec);
    }

    /**
     * author: Khanh Cao
     * Starts a series of games using the StupidSolver algorithm.
     *
     * @param gameNumber The number of games to simulate.
     */
    public void startStupidGame(int gameNumber) {
        ArrayList<Integer> turnResults = new ArrayList<>();
        long startTime = System.nanoTime();

        for (int i = 0; i < gameNumber; i++) {
            ArrayList<CodeValue> answerKey = generateSecretCode();
            StupidSolver stupidSimulation = new StupidSolver(answerKey);
            try {
                int turnCount = stupidSimulation.solve();
                turnResults.add(turnCount);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        long endTime = System.nanoTime();
        double durationInSec = (endTime - startTime) / 1e9;

        printSolverStats("StupidSolver", turnResults, durationInSec);
    }

    /**
     * author: Khanh Cao
     * Starts a series of games using the MinMaxSolver algorithm.
     *
     * @param gameNumber The number of games to simulate.
     */
    public void startMinMaxGame(int gameNumber) {
        ArrayList<Integer> turnResults = new ArrayList<>();
        long startTime = System.nanoTime();

        for (int i = 0; i < gameNumber; i++) {
            ArrayList<CodeValue> answerKey = generateSecretCode();
            MinMaxSolver minMaxSolver = new MinMaxSolver(answerKey);
            int turnCount = minMaxSolver.solveGame();
            turnResults.add(turnCount);
        }
        long endTime = System.nanoTime();
        double durationInSec = (endTime - startTime) / 1e9;

        printSolverStats("MinMaxSolver", turnResults, durationInSec);
    }

    /**
     * author: Khanh Cao
     * Ref: Generated by DeepSeek
     * Prints the statistics of a solver's performance.
     *
     * @param solverName    The name of the solver algorithm.
     * @param turnResults   A list containing the number of turns taken in each game.
     * @param durationInSec The total duration of the games in seconds.
     */
    private void printSolverStats(String solverName, ArrayList<Integer> turnResults, double durationInSec) {
        if (turnResults.isEmpty()) {
            System.out.println(solverName + " failed to complete any games.");
            return;
        }

        int min = turnResults.stream().mapToInt(Integer::intValue).min().orElse(-1);
        int max = turnResults.stream().mapToInt(Integer::intValue).max().orElse(-1);
        double avg = turnResults.stream().mapToInt(Integer::intValue).average().orElse(0.0);

        System.out.println("\n=== Stats for " + solverName + " ===");
        System.out.println("Number of games played: " + turnResults.size());
        System.out.println("Mean number of turns: " + String.format("%.2f", avg));
        System.out.println("Least number of turns: " + min);
        System.out.println("Greatest number of turns: " + max);
        System.out.printf("Total time taken: %.3f seconds%n", durationInSec);
    }

    /**
     * author: Khanh Cao
     * Ref: Framework generated by DeepSeek
     * Runs an interactive mode where the user can either play the game or run a solver simulation.
     */
    public void runInteractiveMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Mastermind!");
        System.out.print("Choose mode: [1] Game Mode or [2] Solver Mode: ");

        String choice = scanner.nextLine().strip();

        if (choice.equals("1")) {
            // Game mode: user plays
            startGame();
        } else if (choice.equals("2")) {
            // Solver mode: simulation
            System.out.println("Select a solver:");
            System.out.println("[1] RandomSolver");
            System.out.println("[2] StupidSolver");
            System.out.println("[3] MinMaxSolver");
            System.out.print("Your choice: ");
            String solverChoice = scanner.nextLine().strip();

            System.out.print("How many games to simulate? ");
            int numGames;
            try {
                numGames = Integer.parseInt(scanner.nextLine().strip());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Exiting.");
                return;
            }

            switch (solverChoice) {
                case "1":
                    startRandomGame(numGames);
                    break;
                case "2":
                    startStupidGame(numGames);
                    break;
                case "3":
                    startMinMaxGame(numGames);
                    break;
                default:
                    System.out.println("Invalid solver selection.");
                    break;
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    /**
     * author: Aleena Sultan
     * Gets the expected result of the game.
     *
     * @return An ArrayList of PegState representing the expected result.
     */
    public ArrayList<PegState> getExpectedResult() {
        return expectedResult;
    }

}