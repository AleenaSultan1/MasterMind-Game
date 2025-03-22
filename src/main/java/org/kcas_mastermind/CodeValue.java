package org.kcas_mastermind;

import java.util.ArrayList;

/**
 * The `CodeValue` enum represents the possible values for a code in the Mastermind game.
 * Each value corresponds to a digit between 1 and 6, which can be used in the user's guess
 * or the secret code.
 */
public enum CodeValue {
    ONE('1'),   // Represents the digit '1'
    TWO('2'),   // Represents the digit '2'
    THREE('3'), // Represents the digit '3'
    FOUR('4'),  // Represents the digit '4'
    FIVE('5'),  // Represents the digit '5'
    SIX('6');   // Represents the digit '6'

    // The character value associated with the enum constant
    private final char value;

    /**
     * Constructor for the `CodeValue` enum.
     *
     * @param value The character value associated with the enum constant.
     */
    CodeValue(char value) {
        this.value = value;
    }


    /**
     * Converts a character to the corresponding `CodeValue` enum constant.
     *
     * @param num The character to convert (must be between '1' and '6').
     * @return The corresponding `CodeValue` enum constant.
     * @throws IllegalArgumentException If the input character is invalid.
     */
    public static CodeValue fromInt(char num) {
        // Iterate through all enum constants to find a match
        for (CodeValue digit : values()) {
            if (digit.value == num) {
                return digit;
            }
        }
        // Throw an exception if no match is found
        throw new IllegalArgumentException("Invalid number: " + num);
    }

    /**
     * Parses a string of digits into a list of `CodeValue` enum constants.
     *
     * @param number The string to parse (must consist of digits between '1' and '6').
     * @return An `ArrayList` of `CodeValue` enum constants.
     */
    public static ArrayList<CodeValue> parseString(String number) {
        ArrayList<CodeValue> list = new ArrayList<>();
        // Iterate through each character in the string
        for (int i = 0; i < number.length(); i++) {
            // Convert the character to a `CodeValue` and add it to the list
            list.add(fromInt(number.charAt(i)));
        }
        return list;
    }

    /**
     * Returns the string representation of the `CodeValue` enum constant.
     *
     * @return The character value as a string.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}