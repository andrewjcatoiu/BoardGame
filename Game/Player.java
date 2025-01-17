import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    
    private String name;
    private String color;
    private int settlements;
    private int cities;
    private int roads;
    private final Map<String, Integer> hand;
    private final Map<Integer, ArrayList<int[]>> activeCoords;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.settlements = 5;
        this.cities = 4;
        this.roads = 15;
        
        this.hand = new HashMap<>();
        this.hand.put("Wood", 0);
        this.hand.put("Brick", 0);
        this.hand.put("Sheep", 0);
        this.hand.put("Wheat", 0);
        this.hand.put("Ore", 0);

        this.activeCoords = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getSettlements() {
        return this.settlements;
    }

    public int getCities() {
        return this.cities;
    }

    public int getRoads() {
        return this.roads;
    }

    public Map<Integer, ArrayList<int[]>> getActiveCoords() {
        return this.activeCoords;
    }

    public void updateHand(String material, int num) {
        int currAmount = this.hand.get(material);
        currAmount += num;
        this.hand.put(material, currAmount);
    }

    public void buildSettlement(int x, int y) {
        updateHand("Wood", -1);
        updateHand("Brick", -1);
        updateHand("Sheep", -1);
        updateHand("Wheat", -1);

        ArrayList<Tile> tiles = Tiles.getTiles();
        // obtain all tiles with the coordinate pair (including tileIndex)
        // change settlement flag to 1 in the int[] at int[2]
        // add coordinate pair to active coords with map.put(tileIndex, ArrayList<int[]> coordinate pair )
        
    }

    public void buildCity(int x, int y) {
        updateHand("Wheat", -2);
        updateHand("Ore", -3);
    }

    @Override
    public String toString() {
        return this.name + " is playing as " + this.color;
    }
}
