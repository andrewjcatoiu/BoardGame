import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tiles {
    private final ArrayList<Tile> tiles;

    public Tiles() {
        tiles = new ArrayList<>();
    }

    public void initTiles(Numbers numbers) {
        ArrayList<Integer> numberList = numbers.getNumbers();
        String[][] tileCounts = {{"Wood", "4"}, {"Brick", "3"}, {"Sheep", "4"}, {"Wheat", "4"}, {"Ore", "3"}, {"Desert", "1"}};
        int numberIndex = 0;

        for (String[] type : tileCounts) {
            String material = type[0];
            int capacity = Integer.parseInt(type[1]);
            for (int i = 0; i < capacity; i++) {
                if (numberIndex < numberList.size()) {
                    int number = numberList.get(numberIndex++);
                    tiles.add(new Tile(material, number, 0, 0));
                } else {
                    tiles.add(new Tile(material, 0, 0, 0));
                }
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    public ArrayList<Tile> getTiles() {
        return this.tiles;
    }

    @Override
    public String toString() {
        List<String> tilesAsString = new ArrayList<>();
        for (Tile tile : tiles) {
            tilesAsString.add(tile.getMaterial());
        }

        String str = "Tiles: " + tilesAsString.toString() + "\n";
        return str;
    }
}


