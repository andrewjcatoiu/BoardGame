import java.util.ArrayList;
import java.util.Map;

/**
 * Class responsible for trading and dealing cards to players.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Bank {

    /**
     * Amount of wood in the bank.
     */
    public static int wood;

    /**
     * Amount of brick in the bank.
     */
    public static int brick;

    /**
     * Amount of sheep in the bank.
     */
    public static int sheep;

    /**
     * Amount of wheat in the bank.
     */
    public static int wheat;

    /**
     * Amount of ore in the bank.
     */
    public static int ore;

    /**
     * Amount of development cards available.
     */
    public static int devs;

    /**
     * The list of players in the game.
     */
    private final ArrayList<Player> players;

    /**
     * Default constructor.
     * 
     * @param players the list of players
     */
    public Bank(ArrayList<Player> players) {
        Bank.wood = 19;
        Bank.brick = 19;
        Bank.sheep = 19;
        Bank.wheat = 19;
        Bank.ore = 19;
        Bank.devs = 25;
        this.players = players;
    }

    /**
     * Deals cards to players who control tiles associated with the number that has been rolled.
     * 
     * @param roll the roll number
     */
    public void deal(int roll) {
        for (Player player : players) {
            Map<Integer, ArrayList<int[]>> activeCoords = player.getActiveCoords();
            for (var entry : activeCoords.entrySet()) {
                int tileIndex = entry.getKey();
                ArrayList<Tile> tiles = Tiles.getTiles();
                if (tiles.get(tileIndex).getNumber() == roll) {
                    ArrayList<int[]> coords = entry.getValue();
                    String material = tiles.get(tileIndex).getMaterial();
                    for (int[] pair : coords) {
                        int inc = 0;
                        if (pair[2] == 0 && pair[3] == 1) {
                            inc = 2;
                        } else if (pair[2] == 1 && pair[3] == 0) {
                            inc = 1;
                        }
    
                        player.updateHand(material, inc);
                    }
                }
            }
        }
    }

    public static boolean checkResource(String resource) {
        Map<String, Integer> resources = Map.of(
            "Wood", wood,
            "Brick", brick,
            "Sheep", sheep,
            "Wheat", wheat,
            "Ore", ore
        );

        return resources.getOrDefault(resource, 0) > 0;
    }
}