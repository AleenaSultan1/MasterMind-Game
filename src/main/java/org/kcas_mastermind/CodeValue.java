package org.kcas_mastermind;

import java.util.ArrayList;

public enum CodeValue {
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6')
    ;
    private final char value;

    CodeValue(char value) {
        this.value = value;
    }
    public char getValue() {
        return value;
    }

    // Convert an integer to a MastermindDigit
    public static CodeValue fromInt(char num) {
        for (CodeValue digit : values()) {
            if (digit.value == num) {
                return digit;
            }
        }
        throw new IllegalArgumentException("Invalid number: " + num);
    }

    public static ArrayList<CodeValue> parseString(String number) {
        ArrayList<CodeValue> list = new ArrayList<CodeValue>();
        for (int i = 0; i < number.length(); i++) {
            list.add(fromInt(number.charAt(i)));
        }
        return list;
    }
}
