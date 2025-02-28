import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for player moves.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Player {
    
    /**
     * The name of the player.
     */
    private String name;

    /**
     * The color structures the player will build.
     */
    private String color;

    /**
     * The number of settlements the player can build.
     */
    private int settlements;

    /**
     * The number of cities the player can build.
     */
    private int cities;

    /**
     * The number of roads the player can build.
     */
    private int roads;

    /**
     * The player's current hand of cards.
     */
    private final Map<String, Integer> hand;

    /**
     * A map of tile vertex coordinates controlled by the player.
     */
    private final Map<Integer, ArrayList<int[]>> activeCoords;

    /**
     * Default constructor.
     * 
     * @param name the player's name
     * @param color the player's building color
     */
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

    /**
     * Getter for player's name.
     * 
     * @return returns a string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for player's building color.
     * 
     * @return returns a string
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Getter for the number of settlements the player can build.
     * 
     * @return returns an integer
     */
    public int getSettlements() {
        return this.settlements;
    }

    /**
     * Getter for the number of cities the player can build.
     * 
     * @return returns an integer
     */
    public int getCities() {
        return this.cities;
    }

    /**
     * Getter for the number of roads the player can build.
     * 
     * @return returns an integer
     */
    public int getRoads() {
        return this.roads;
    }

    /**
     * Getter for the player's controlled coordinates.
     * 
     * @return returns a map of tile entries with the tile index as key and list of coordinate pairs as value
     */
    public Map<Integer, ArrayList<int[]>> getActiveCoords() {
        return this.activeCoords;
    }

    /**
     * Updates the player's controlled coordinates map after building.
     * 
     * @param tileIndex used to store the coordinate pair
     * @param x the x-coordinate of the newly built structure
     * @param y the y-coordinate of the newly built structure
     * @param isSettlement flag to indicate if the structure built is a settlement
     * @param isCity flag to indicate if the structure built is a city
     */
    public void updateActiveCoords(int tileIndex, int x, int y, int isSettlement, int isCity) {
        ArrayList<int[]> tileCoords = this.activeCoords.get(tileIndex);
        if (tileCoords == null) {
            tileCoords = new ArrayList<>();
        }
        
        int[] building = new int[] {x, y, isSettlement, isCity};
        tileCoords.add(building);
        this.activeCoords.put(tileIndex, tileCoords);
    }

    /**
     * Updates the current hand of the player.
     * 
     * @param material the material to update
     * @param inc the amount to increment onto the material count
     */
    public void updateHand(String material, int inc) {
        int currAmount = this.hand.get(material);
        currAmount += inc;
        this.hand.put(material, currAmount);
    }

    /**
     * Builds a settlement. Updates player's hand. Updates active coordinates map, with new tiles and coordinates to be 
     * controlled. Sets settlement flag to true.
     * 
     * @param x the x-coordinate of the tile vertex
     * @param y the y-coordinate of the tile vertex
     */
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

    /**
     * Builds a city. Updates player's hand. Updates active coordinates map by setting the settlement
     * flag to false and setting the city flag to true.
     * 
     * @param x the x-coordinate of the tile vertex
     * @param y the y-coordinate of the tile vertex
     */
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

    public void trade(String tradeMat, String reveivedMat) {
        if (this.hand.get(tradeMat) != null) {
            // define flags (has 3 for 1, 2 for 1 will be implemented later)
            // check for flags, default 4 for 1 with other flags set to false
            // depending on flag, check for sufficient amount in player hand (check for at least 3 of the same resource in hand if 3 for 1 flag set to true, etc.)
            // check if bank has the resource the player is looking for
            // update player hand + bank
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
