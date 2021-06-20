package my.capitaly.field.action.step;


import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public interface StepAction {

    /**
     * Decides what should the player pay/earn/buy according to the field the player stepped on.
     *
     * @param player Player turn in action
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    void doStep(Player player) throws NotEnoughMoneyException;

}
