package my.capitaly.field;

import my.capitaly.field.action.step.LuckyStepAction;
import my.capitaly.field.action.step.StepAction;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public class LuckyField implements Field {

    private final int luckyValue;
    private final StepAction stepAction;

    public LuckyField(int luckyValue) {
        this.luckyValue = luckyValue;
        stepAction = new LuckyStepAction(this);
    }

    public int getLuckyValue() {
        return luckyValue;
    }

    /**
     * Calls doStep to perform lucky step action from StepAction class
     *
     * @param player Player turn
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playerStepped(Player player) throws NotEnoughMoneyException {
        stepAction.doStep(player);
    }
}
