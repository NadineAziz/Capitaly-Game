package my.capitaly.dice;

public class RolledDice {
    private int rightValue;
    private int leftValue;

    public RolledDice(int rightValue, int leftValue) {
        this.rightValue = rightValue;
        this.leftValue = leftValue;
    }

    public int getValue() {
        return rightValue + leftValue;
    }
}
