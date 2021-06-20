package my.capitaly;

import my.capitaly.dice.RolledDice;
import my.capitaly.field.Field;
import my.capitaly.field.LuckyField;
import my.capitaly.field.PropertyField;
import my.capitaly.field.ServiceField;
import my.capitaly.player.NotEnoughMoneyException;
import my.capitaly.player.Player;
import my.capitaly.player.strategy.StrategyType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayGround {

    private final List<Player> players = new ArrayList<>();
    private final List<Field> fields = new ArrayList<>();
    private final List<Player> losers = new ArrayList<>();

    /**
     * Gives back the second loser from the losers list.
     *
     * @param path File passed to read
     * @throws Exception If strategies types are read and a different type is given -> throws Exception
     */
    public void play(Path path) {
        System.out.println(path.getFileName());
        try {
            List<String> lines = init(path);
            playTheGame(lines);

            if (losers.size() > 1) {
                System.out.println("Second loser: " + losers.get(1));
            } else if (losers.size() == 1 && players.size() == 1) {
                System.out.println(losers.get(0));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Goes through the players' strategies. If the strategy is between Greedy, Careful, or Tactical -> Calls the
     * strategy type from Enum StrategyType
     *
     * @param type player's strategy type
     * @return Enum constants
     * @throws Exception If strategies types are read and a different type is given -> throws Exception
     */
    private StrategyType getStrategyType(String type) throws Exception {
        switch (type) {
            case "greedy":
                return StrategyType.GREEDY;
            case "careful":
                return StrategyType.CAREFUL;
            case "tactical":
                return StrategyType.TACTICAL;
            default:
                throw new Exception("Not supported type: " + type);
        }
    }

    /**
     * Reads the dice numbers then checks if player loses
     *
     * @param lines All the lines of the file
     * @throws Exception If strategies types are read and a different type is given -> throws Exception
     */

    private void playTheGame(List<String> lines) throws Exception {
        int diceIndex = fields.size() + players.size() + 3;

        if (diceIndex + 1 > lines.size() || (fields.size() < 1) || (players.size() < 1)) {
            System.out.println("No moves defined");
            return;
        }

        for (int index = diceIndex + 1; index < lines.size(); index++) {
            String[] values = lines.get(index).split(" ");
            RolledDice rolledDice = new RolledDice(
                    Integer.parseInt(values[2]),
                    Integer.parseInt(values[3])
            );
            // If player is still playing (in players list) and loses, player
            // is added to the losers list and removed from players list
            findPlayer(values[0], getStrategyType(values[1])).ifPresent(player -> {
                try {
                    playOneTurnWithPlayer(player, rolledDice);
                } catch (NotEnoughMoneyException ex) {
                    losers.add(player);
                    players.remove(player);
                }
            });

            if (players.size() <= 1) {
                break;
            }
        }
    }

    /**
     * Gets the number passed as numbers of fields and pass the players in
     * the players list and the fields in fields list
     *
     * @param path Path of file read from
     * @return lines read
     * @throws Exception If strategies types are read and a different type is given -> throws Exception
     */
    private List<String> init(Path path) throws Exception {
        List<String> lines = Files.readAllLines(path);
        if (lines.size() != 0) {
            int numberOfFields = Integer.parseInt(lines.get(0));
            fillFieldsList(lines, numberOfFields);
            fillPlayersList(lines, numberOfFields);
        } else {
            throw new FileEmptyException();
        }
        return lines;
    }

    /**
     * Makes sure that player playing is the same player in the index of the players list.
     * Not to deal with NullPointerException then return player can be/can't be found in list
     *
     * @param playerName   Player's turn in action
     * @param strategyType Player's strategy type
     * @return player list can be empty or not
     */
    private Optional<Player> findPlayer(String playerName, StrategyType strategyType) {
        for (Player player : players) {
            if (player.getName().equals(playerName) && (player.getStrategyType() == strategyType)) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    /**
     * Reads in the player's name and strategy line by line depending on the number of players given.
     *
     * @param lines          Number of players given
     * @param numberOfFields Passed to inform the program to read after those numbers of lines.
     * @throws Exception If strategies types are read and a different type is given -> throws Exception
     */
    private void fillPlayersList(List<String> lines, int numberOfFields) throws Exception {
        if (fields.size() == 0) throw new Exception("No fields found");
        int numberOfPlayersIndex = Integer.parseInt(lines.get(numberOfFields + 1));
        for (int index = numberOfFields + 2;
             index <= numberOfFields + numberOfPlayersIndex + 1;
             index++) {
            String[] player = lines.get(index).split(" ");
            switch (player[1]) {
                case "greedy":
                    players.add(new Player(player[0], StrategyType.GREEDY));
                    break;
                case "careful":
                    players.add(new Player(player[0], StrategyType.CAREFUL));
                    break;
                case "tactical":
                    players.add(new Player(player[0], StrategyType.TACTICAL));
                    break;
            }
        }
    }

    /**
     * Read lines of fields according to a given number and defines them as property, service, or lucky fields
     *
     * @param lines          Lines of fields read
     * @param numberOfFields Number passed to give number of fields
     */

    private void fillFieldsList(List<String> lines, int numberOfFields) throws Exception {
        for (int index = 1; index < numberOfFields; index++) {
            String[] fieldTypes = lines.get(index).split(" ");
            switch (fieldTypes[0]) {
                case "property":
                    fields.add(new PropertyField());
                    break;
                case "service":
                    fields.add(new ServiceField(Integer.parseInt(fieldTypes[1])));
                    break;
                case "lucky":
                    fields.add(new LuckyField(Integer.parseInt(fieldTypes[1])));
                    break;
                default:
                    throw new Exception("No field defined for this board ");
            }
        }

    }


    /**
     * Keep track of the player's position after rolling the dice and performs action according to which field the
     * player stepped on
     *
     * @param player     Player thrown the dice
     * @param rolledDice number of both dice thrown
     * @throws NotEnoughMoneyException if player doesn't have enough money -> loses
     */
    private void playOneTurnWithPlayer(Player player, RolledDice rolledDice) throws NotEnoughMoneyException {
        int position = player.increasePosition(rolledDice.getValue() - 1, fields.size());
        Field field = fields.get(position);
        field.playerStepped(player);
        //System.out.println(player);
    }
}
