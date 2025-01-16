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
        for (Player player : players) {
            Map<Integer, ArrayList<int[]>> activeCoords = player.getActiveCoords();
            for (var entry : activeCoords.entrySet()) {
                int tileIndex = entry.getKey();
                if (tiles.getTile(tileIndex).getNumber() == roll) {
                    ArrayList<int[]> coords = entry.getValue();
                    String material = tiles.getTile(tileIndex).getMaterial();
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
