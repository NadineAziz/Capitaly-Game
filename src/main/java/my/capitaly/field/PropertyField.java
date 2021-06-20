package my.capitaly.field;


import my.capitaly.field.action.step.PropertyStepAction;
import my.capitaly.field.action.step.StepAction;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public class PropertyField implements Field {

    private final StepAction propertyFieldStepAction;
    private Player owner;
    private boolean houseBuilt = false;

    public PropertyField() {
        propertyFieldStepAction = new PropertyStepAction(this);
    }

    public boolean isOwner(Player player) {
        return owner == player;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public boolean isHouseBuilt() {
        return houseBuilt;
    }

    public void setHouseBuilt(boolean houseBuilt) {
        this.houseBuilt = houseBuilt;
    }

    /**
     * Calls doStep method from StepAction class to perform property step action.
     * Calls player strategy to buy/noy buy property according to player's strategy.
     *
     * @param player player turn
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void playerStepped(Player player) throws NotEnoughMoneyException {
        propertyFieldStepAction.doStep(player);
        player.playStrategy(this);
    }
}
