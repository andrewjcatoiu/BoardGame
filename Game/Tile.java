public class Tile {

    private final String material;
    private int number;
    private int x;
    private int y;

    public Tile(String m, int n, int x, int y) {
        material = m;
        number = n;
        x = x;
        y = y;
    }

    public Tile(String m) {
        material = m;
    }
}
