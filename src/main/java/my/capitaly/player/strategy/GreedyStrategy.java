package my.capitaly.player.strategy;

import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;
import my.capitaly.player.actions.PlayerActions;
import my.capitaly.player.actions.PlayerActionsImpl;

public class GreedyStrategy implements Strategy {

    private final PlayerActions playerActions;

    public GreedyStrategy(Player player) {
        playerActions = new PlayerActionsImpl(player);
    }

    /**
     * Greedy player builds a house or buys a property
     *
     * @param propertyField The property field the player is on
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playStrategy(PropertyField propertyField) throws NotEnoughMoneyException {
        if (playerActions.isOwned(propertyField) && propertyField.getOwner() == null) {
            playerActions.buildHouse(propertyField);
        } else {
            playerActions.buyProperty(propertyField);
        }
    }

    @Override
    public PlayerActions getPlayerAction() {
        return playerActions;
    }
}
