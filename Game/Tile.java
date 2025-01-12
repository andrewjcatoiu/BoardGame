public class Tile {

    private final String material;
    private int number;
    private int x;
    private int y;

    public Tile(String m, int n, int x, int y) {
        material = m;
        number = n;
        this.x = x;
        this.y = y;
    }

    public Tile(String m) {
        material = m;
        number = 0;
    }

    public String getMaterial() {
        return material;
    }

    public int getNumber() {
        return number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
