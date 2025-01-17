import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tiles {
    private static final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Integer> numbers;

    public Tiles() {
        // tiles = new ArrayList<>();
        numbers = new ArrayList<>();
    }

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

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public static ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int index) {
        return tiles.get(index);
    }

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