
public enum CodeValue {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6")
    ;
    private final String value;

    CodeValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    // Convert an integer to a MastermindDigit
    public static CodeValue fromInt(String num) {
        for (CodeValue digit : values()) {
            if (digit.value == num) {
                return digit;
            }
        }
        throw new IllegalArgumentException("Invalid number: " + num);
    }
}