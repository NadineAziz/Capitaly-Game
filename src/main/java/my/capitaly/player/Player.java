package my.capitaly.player;

import my.capitaly.field.PropertyField;
import my.capitaly.player.actions.PlayerActions;
import my.capitaly.player.strategy.Strategy;
import my.capitaly.player.strategy.StrategyFactory;
import my.capitaly.player.strategy.StrategyType;

public class Player implements Strategy {

    private final String name;
    private final Strategy strategy;
    private final StrategyType strategyType;
    private int money = 10000;
    private int currentPosition = 0;
    private int round = 0;

    public Player(String name, StrategyType playerStrategy) throws Exception {
        this.name = name;
        this.strategyType = playerStrategy;
        this.strategy = StrategyFactory.createStrategy(this, playerStrategy);
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Checks if player has the money then subtracts value from player's total amount.
     *
     * @param price Value of property or service.
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    public void subtractMoney(int price) throws NotEnoughMoneyException {
        if (getMoney() > price) {
            setMoney(getMoney() - price);
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    public void addMoney(int money) {
        setMoney(getMoney() + money);
    }

    public String getName() {
        return name;
    }

    /**
     * If player loses then all his properties should be freed.
     *
     * @param propertyField property field of the owner.
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playStrategy(PropertyField propertyField) throws NotEnoughMoneyException {
        try {
            strategy.playStrategy(propertyField);
        } catch (NotEnoughMoneyException ex) {
            strategy.getPlayerAction().removeOwner();
            throw ex;
        }
    }

    /**
     * Gets the player action according to the field the player steps on depending on the strategy.
     *
     * @return playerAction
     */
    @Override
    public PlayerActions getPlayerAction() {
        return strategy.getPlayerAction();
    }

    /**
     * Keeps track of the round and the last position the player was on. Then increases it and saves the new position
     *
     * @param inc            total number of steps the player moved.
     * @param numberOfFields to make sure the player is not out of the board.
     * @return currentPosition : new saved position
     */
    public int increasePosition(int inc, int numberOfFields) {
        int newPosition = (currentPosition + inc) % numberOfFields;
        if (newPosition < currentPosition) {
            round++;
        }
        currentPosition = newPosition;
        return currentPosition;
    }

    public int getRound() {
        return round;
    }

    @Override
    public String toString() {
        return getName();
    }
}
