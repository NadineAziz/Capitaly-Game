package my.capitaly.player.actions;

import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;

public interface PlayerActions {
    /**Takes a property field and build a house according to the player's owned property and total money
     * @param field property type field
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    void buildHouse(PropertyField field) throws NotEnoughMoneyException;

    /**
     * takes a property field and buys a property according to the player's conditions
     * @param field property type field
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    void buyProperty(PropertyField field) throws NotEnoughMoneyException;

    Player getPlayer();

    void removeOwner();

    boolean isOwned(PropertyField field);
}
