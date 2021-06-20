package my.capitaly.field.action.step;

import my.capitaly.field.ServiceField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public class ServiceStepAction implements StepAction {

    private final ServiceField serviceField;

    public ServiceStepAction(ServiceField serviceField) {
        this.serviceField = serviceField;
    }

    /**
     * Gets the service price and subtracts it from player's money
     *
     * @param player Player's turn in action
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void doStep(Player player) throws NotEnoughMoneyException {
        player.subtractMoney(serviceField.getServicePrice());
    }
}
