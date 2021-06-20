package my.capitaly.player.strategy;

import my.capitaly.field.PropertyField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.actions.PlayerActions;

public interface Strategy {

    void playStrategy(PropertyField propertyField) throws NotEnoughMoneyException;

    PlayerActions getPlayerAction();

}
