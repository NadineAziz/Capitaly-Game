package my.capitaly.player.strategy;

import my.capitaly.player.Player;

    public class StrategyFactory {

    public static Strategy createStrategy(Player player, StrategyType strategyType) throws Exception {
        switch (strategyType) {
            case GREEDY:
                return createGreedy(player);
            case CAREFUL:
                return createCareful(player);
            case TACTICAL:
                return createTactical(player);
            default:
                throw new Exception("Unknown Player Type");
        }
    }

    public static Strategy createGreedy(Player player) {
        return new GreedyStrategy(player);
    }

    public static Strategy createCareful(Player player) {
        return new CarefulStrategy(player);
    }

    public static Strategy createTactical(Player player) {
        return new TacticalStrategy(player);
    }
}
