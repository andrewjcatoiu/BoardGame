import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for trading and dealing cards to players.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Bank {

    /**
     * Amount of development cards available.
     */
    public static int devs;

    public static Map<String, Integer> tradingBlock;

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
        Bank.devs = 25;
        Bank.tradingBlock = new HashMap<>();
        Bank.tradingBlock.put("Wood", 19);
        Bank.tradingBlock.put("Brick", 19);
        Bank.tradingBlock.put("Sheep", 19);
        Bank.tradingBlock.put("Wheat", 19);
        Bank.tradingBlock.put("Ore", 19);

        this.players = players;
    }

    public static void updateTradingBlock(String material, int inc) {
        int currAmount = tradingBlock.get(material);
        currAmount += inc;
        tradingBlock.put(material, currAmount);
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
        return tradingBlock.getOrDefault(resource, 0) > 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "Cards Available: [\n";
        sb.append(str);
        for (var entry : tradingBlock.entrySet()) {
            String resource = entry.getKey();
            int count = entry.getValue();
            sb.append("\t").append(resource).append(": ").append(count).append("\n");
        }

        sb.append("]\n\n");
        sb.append("Remaining Development Cards: ").append(Bank.devs).append("\n\n");
        return sb.toString();
    }
}