package my.capitaly.field;

import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public interface Field {
    /**
     * Gets field the player stepped on and calls doStep method in StepAction to perform an action
     * depending on the field
     *
     * @param player Player turn in action
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    void playerStepped(Player player) throws NotEnoughMoneyException;

}
