package my.capitaly.player.actions;

import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerActionsImpl implements PlayerActions {

    private final Player player;
    private final List<PropertyField> ownedFields = new ArrayList<>();

    public PlayerActionsImpl(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Checks is player is the owner and allows him/her to buy a house by subtracting house price from his money
     * and setting the field to have a house.
     *
     * @param field The field the player stepped on.
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void buildHouse(PropertyField field) throws NotEnoughMoneyException {
        if (field.getOwner() == player) {
            player.subtractMoney(4000);
            field.setHouseBuilt(true);
        }
    }

    /**
     * If property has no owner and player steps in field then the field
     * is added to player's ownership list and subtract 1000 from his total money
     *
     * @param field field type
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    @Override
    public void buyProperty(PropertyField field) throws NotEnoughMoneyException {
        if (field.getOwner() == null) {
            ownedFields.add(field);
            player.subtractMoney(1000);
            field.setOwner(player);
        }
    }

    public void removeOwner() {
        for (PropertyField field : ownedFields) {
            field.setOwner(null);
        }
    }

    public boolean isOwned(PropertyField field) {
        return ownedFields.contains(field);
    }

}
