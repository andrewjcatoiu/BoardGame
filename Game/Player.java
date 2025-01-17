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

    public void updateActiveCoords(int tileIndex, int x, int y, int isSettlement, int isCity) {
        ArrayList<int[]> tileCoords = this.activeCoords.get(tileIndex);
        if (tileCoords == null) {
            tileCoords = new ArrayList<>();
        }
        
        int[] building = new int[] {x, y, isSettlement, isCity};
        tileCoords.add(building);
        this.activeCoords.put(tileIndex, tileCoords);
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

        Map<Integer, ArrayList<int[]>> coords = Board.getCoordinates();
        int tileIndex = 0;
        for (var entry : coords.entrySet()) {
            ArrayList<int[]> pairs = entry.getValue();
            for (int[] pair : pairs) {
                if (pair[0] == x && pair[1] == y) {
                    updateActiveCoords(tileIndex, x, y, 1, 0);
                }
            }

            tileIndex++;
        }
    }

    public void buildCity(int x, int y) {
        updateHand("Wheat", -2);
        updateHand("Ore", -3);

        Map<Integer, ArrayList<int[]>> coords = Board.getCoordinates();
        int tileIndex = 0;
        for (var entry : coords.entrySet()) {
            ArrayList<int[]> pairs = entry.getValue();
            for (int[] pair : pairs) {
                if (pair[0] == x && pair[1] == y) {
                    updateActiveCoords(tileIndex, x, y, 0, 1);
                }
            }

            tileIndex++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.name + " is playing as " + this.color + ".\n";
        sb.append(str);
        sb.append("Current hand: [\n");
        for (var entry : hand.entrySet()) {
            String material = entry.getKey();
            int count = entry.getValue();
            sb.append("\t").append(material).append(": ").append(count).append("\n");
        }

        sb.append("]\n\n");
        sb.append("Active coordinates: [");
        for (var entry : activeCoords.entrySet()) {
            ArrayList<Tile> tiles = Tiles.getTiles();
            Tile tile = tiles.get(entry.getKey());
            sb.append(tile.getMaterial()).append(" ").append(tile.getNumber()).append(" [\n\t\t");

            ArrayList<int[]> pairs = entry.getValue();
            for (int[] pair : pairs) {
                sb.append("(").append(pair[0]).append(", ").append(pair[1]).append(") ");

                if (pair[2] == 1) {
                    sb.append("Settlement");
                } else if (pair[3] == 1) {
                    sb.append("City");
                }
                
                sb.append("\n\t\t");
            }
        }

        sb.append("\n]\n\n");
        return sb.toString();
    }
}
