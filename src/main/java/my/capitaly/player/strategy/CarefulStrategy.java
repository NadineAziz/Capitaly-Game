package my.capitaly.player.strategy;

import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;
import my.capitaly.player.actions.PlayerActions;
import my.capitaly.player.actions.PlayerActionsImpl;

public class CarefulStrategy implements Strategy {

    private final PlayerActions playerActions;
    private int amountOfMoneyForThisRound;
    private int currentRound = 0;

    public CarefulStrategy(Player player) {
        amountOfMoneyForThisRound = player.getMoney() / 2;
        playerActions = new PlayerActionsImpl(player);
    }

    private boolean hasEnoughMoney() {
        return playerActions.getPlayer().getMoney() > amountOfMoneyForThisRound;
    }

    /**
     * Careful player builds a house or buys a property in a round up to half oh his money
     *
     * @param propertyField The property field the player is on
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playStrategy(PropertyField propertyField) throws NotEnoughMoneyException {
        int round = playerActions.getPlayer().getRound();
        if (round > currentRound) {
            currentRound = round;
            amountOfMoneyForThisRound = playerActions.getPlayer().getMoney() / 2;
        }

        if ((playerActions.getPlayer().getMoney() > amountOfMoneyForThisRound)) {
            if (playerActions.isOwned(propertyField) && hasEnoughMoney()) {
                playerActions.buildHouse(propertyField);
            } else if (hasEnoughMoney()) {
                playerActions.buyProperty(propertyField);
            }
        }
    }

    @Override
    public PlayerActions getPlayerAction() {
        return playerActions;
    }
}
