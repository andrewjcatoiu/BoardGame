import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for creating and shuffling a list of tiles.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Tiles {
    
    /**
     * The initial list of tiles.
     */
    private static final ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * The initial list of roll numbers.
     */
    private final ArrayList<Integer> numbers;

    /**
     * Default constructor.
     */
    public Tiles() {
        numbers = new ArrayList<>();
    }

    /**
     * Populates the list of roll numbers.
     */
    public void initNumbers() {
        int[] rolls = {2, 3, 4, 5, 6, 8, 9, 10, 11, 12};
        for (int r : rolls) {
            if (r != 2 && r != 12) {
                numbers.add(r);
                numbers.add(r);
            } else {
                numbers.add(r);
            }
        }
    }

    /**
     * Initializes and shuffles list of roll numbers. Associates each number to a tile from the list of shuffled
     * tiles. Populates tiles list.
     */
    public void initTiles() {
        initNumbers();
        Collections.shuffle(numbers);
        String[][] tileCounts = {{"Wood", "4"}, {"Brick", "3"}, {"Sheep", "4"}, {"Wheat", "4"}, {"Ore", "3"}, {"Desert", "1"}};
        int numberIndex = 0;

        for (String[] type : tileCounts) {
            String material = type[0];
            int capacity = Integer.parseInt(type[1]);
            for (int i = 0; i < capacity; i++) {
                if (!material.equals("Desert")) {
                    int number = numbers.get(numberIndex);
                    tiles.add(new Tile(material, number, 0, 0));
                } else {
                    tiles.add(new Tile(material));
                }

                numberIndex++;
            }
        }
    }

    /**
     * Shuffles list of tiles.
     */
    public void shuffle() {
        Collections.shuffle(tiles);
    }

    /**
     * Getter for the entire list of tiles.
     * 
     * @return returns a list of tile instances
     */
    public static ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     * Getter for a specific tile.
     * 
     * @param index the tile index
     * @return returns a tile instance
     */
    public Tile getTile(int index) {
        return tiles.get(index);
    }

    /**
     * Getter for the entire list of roll numbers.
     * 
     * @return returns a list of roll numbers
     */
    public ArrayList<Integer> getNumbers() {
        return this.numbers;
    }

    @Override
    public String toString() {
        List<String> tilesAsString = new ArrayList<>();
        for (Tile tile : tiles) {
            tilesAsString.add(tile.getMaterial() + "(" + tile.getNumber() + ")");
        }

        String str = "Tiles: " + tilesAsString.toString() + "\n";
        return str;
    }
}