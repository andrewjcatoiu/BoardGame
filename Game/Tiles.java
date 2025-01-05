import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tiles {
    private final ArrayList<Tile> tiles;

    public Tiles() {
        tiles = new ArrayList<>();
    }

    public void initTiles() {
        String[][] tileCounts = {{"Wood", "4"}, {"Brick", "3"}, {"Sheep", "4"}, {"Wheat", "4"}, {"Ore", "3"}};
        for (String[] type : tileCounts) {
            String material = type[0];
            int capacity = Integer.parseInt(type[1]);
            for (int i = 0; i < capacity; i++) {
                tiles.add(new Tile(material));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(tiles);
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
