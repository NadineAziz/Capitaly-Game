package my.capitaly.player;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException() {
        super("Don't have enough of money!");
    }
}
