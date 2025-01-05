import java.util.ArrayList;

public class Tiles {
    private final ArrayList<Tile> tiles;

    public Tiles() {
        tiles = new ArrayList<>();
    }

    public void initTiles() {
        tiles.add(new Tile("hello", 2, 3, 4));
    }
}
