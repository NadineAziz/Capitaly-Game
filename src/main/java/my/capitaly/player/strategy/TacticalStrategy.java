package my.capitaly.player.strategy;

import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;
import my.capitaly.player.actions.PlayerActions;
import my.capitaly.player.actions.PlayerActionsImpl;

public class TacticalStrategy implements Strategy {

    private final PlayerActions playerActions;
    private int chance = 0;

    public TacticalStrategy(Player player) {
        playerActions = new PlayerActionsImpl(player);
    }

    /**
     * Tactical player builds a house or buys a property every second chance
     *
     * @param propertyField The property field the player is on
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playStrategy(PropertyField propertyField) throws NotEnoughMoneyException {
        if (chance % 2 != 0) {
            if (playerActions.isOwned(propertyField)) {
                playerActions.buildHouse(propertyField);
            } else {
                playerActions.buyProperty(propertyField);
            }
        }
        chance++;
    }

    @Override
    public PlayerActions getPlayerAction() {
        return playerActions;
    }
}
