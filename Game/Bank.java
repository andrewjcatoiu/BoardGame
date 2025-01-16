import java.util.ArrayList;
import java.util.HashMap;
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
    // public Map<Integer, ArrayList<int[]>> coords;

    public Bank(Board board) {
        this.wood = 19;
        this.brick = 19;
        this.sheep = 19;
        this.wheat = 19;
        this.ore = 19;
        this.devs = 25;
        this.board = board;
    }

    public void deal(int roll) {
        ArrayList<Tile> tilesList = tiles.getTiles();

        int tileIndex = 0;
        for (Tile tile : tilesList) {
            if (tile.getNumber() == roll) {
                ArrayList<int[]> coords = board.getCoordinates(tileIndex);
                Map<Integer, ArrayList<int[]>> map = new HashMap<>();
                // obtain coordinate pairs of tile
                // check for house flag on each coordinate pair
            }

            tileIndex++;
        }
    }

    // roll a number X
    // obtain coords of tiles with matching number X
    // check each coord for house and settlement flags
}
