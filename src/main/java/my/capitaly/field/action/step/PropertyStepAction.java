package my.capitaly.field.action.step;


import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public class PropertyStepAction implements StepAction {

    private final PropertyField propertyField;

    public PropertyStepAction(PropertyField propertyField) {
        this.propertyField = propertyField;
    }

    /**
     * Checks if the property has an owner and that it's not the player passed.
     * Checks if there is a house built to charge the player to pay it's owner. Otherwise player buys property.
     *
     * @param player Player's turn in action
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void doStep(Player player) throws NotEnoughMoneyException {
        if (propertyField.getOwner() != null && !propertyField.isOwner(player)) {
            if (propertyField.isHouseBuilt()) {
                player.subtractMoney(2000);
                propertyField.getOwner().addMoney(2000);
            } else {
                player.subtractMoney(500);
                propertyField.getOwner().addMoney(500);
            }
        }
    }
}
