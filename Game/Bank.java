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
    public int wood;

    /**
     * Amount of brick in the bank.
     */
    public int brick;

    /**
     * Amount of sheep in the bank.
     */
    public int sheep;

    /**
     * Amount of wheat in the bank.
     */
    public int wheat;

    /**
     * Amount of ore in the bank.
     */
    public int ore;

    /**
     * Amount of development cards available.
     */
    public int devs;

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
        this.wood = 19;
        this.brick = 19;
        this.sheep = 19;
        this.wheat = 19;
        this.ore = 19;
        this.devs = 25;
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
}