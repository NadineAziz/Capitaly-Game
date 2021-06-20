package my.capitaly.field;

import my.capitaly.field.action.step.ServiceStepAction;
import my.capitaly.field.action.step.StepAction;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public class ServiceField implements Field {

    private int servicePrice;
    private StepAction serviceFieldStepAction;

    public ServiceField(int servicePrice) {
        this.servicePrice = servicePrice;
        serviceFieldStepAction = new ServiceStepAction(this);
    }

    public int getServicePrice() {
        return servicePrice;
    }

    /**
     * Calls doStep to perform service step action from StepAction class
     *
     * @param player Player turn
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playerStepped(Player player) throws NotEnoughMoneyException {
        serviceFieldStepAction.doStep(player);
    }
}
