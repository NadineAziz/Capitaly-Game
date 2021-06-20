package my.capitaly.field.action.step;

import my.capitaly.field.LuckyField;
import my.capitaly.player.Player;

public class LuckyStepAction implements StepAction {

    private final LuckyField luckyField;

    public LuckyStepAction(LuckyField luckyField) {
        this.luckyField = luckyField;
    }

    /**
     * Gets lucky field value and adds it to the player's money
     *
     * @param player Player passed
     */
    @Override
    public void doStep(Player player) {
        player.addMoney(luckyField.getLuckyValue());
    }
}
