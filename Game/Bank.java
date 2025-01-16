import java.util.ArrayList;
import java.util.Map;

public class Bank {

    public int wood;
    public int brick;
    public int sheep;
    public int wheat;
    public int ore;
    public int devs;
    private Tiles tiles;
    private final Board board;
    private final ArrayList<Player> players;
    // public Map<Integer, ArrayList<int[]>> coords;

    public Bank(Board board, ArrayList<Player> players) {
        this.wood = 19;
        this.brick = 19;
        this.sheep = 19;
        this.wheat = 19;
        this.ore = 19;
        this.devs = 25;
        this.board = board;
        this.players = players;
    }

    public void deal(int roll) {
        // ArrayList<Tile> tilesList = tiles.getTiles();

        // int tileIndex = 0;
        // for (Tile tile : tilesList) {
        //     if (tile.getNumber() == roll) {
        //         ArrayList<int[]> coords = board.getCoordinates(tileIndex);
        //         Map<Integer, ArrayList<int[]>> activeCoords = new HashMap<>();
        //         for (int[] pair : coords) {
        //             // if house flag exists, add to activeCoords

        //         }
        //         // obtain coordinate pairs of tile
        //         // check for house flag on each coordinate pair
        //     }

        //     tileIndex++;
        // }

        // ArrayList<Tile> tilesList = tiles.getTiles();
        // int tileIndex = 0;
        // for (Tile tile : tilesList) {
        //     if (tile.getNumber() == roll) {

        //     }
        // }

        for (Player player : players) {
            Map<Integer, ArrayList<int[]>> activeCoords = player.getActiveCoords();
            for (var entry : activeCoords.entrySet()) {
                int tileIndex = entry.getKey();
                ArrayList<int[]> coords = entry.getValue();
                String material = tiles.getTile(tileIndex).getMaterial();
                
                for (int[] pair : coords) {
                    // if pair has settlement flag, add 1
                    // if pair has city flag, add 2
                }
            }

        }

    }

    // roll a number X
    // obtain coords of tiles with matching number X
    // check each coord for house and settlement flags
}
