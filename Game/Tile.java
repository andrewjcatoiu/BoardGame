/**
 * Class responsible for creating a tile.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Tile {

    /**
     * The material associated with the tile.
     */
    private final String material;

    /**
     * The roll number associated with the tile.
     */
    private int number;

    /**
     * The x-coordinate of the center of the tile.
     */
    private int x;

    /**
     * The y-coordinate of the center of the tile.
     */
    private int y;

    /**
     * Default constructor.
     * 
     * @param m the material
     * @param n the roll number
     * @param x the x-coordinate of the center
     * @param y the y-coordinate of the center
     */
    public Tile(String m, int n, int x, int y) {
        material = m;
        number = n;
        this.x = x;
        this.y = y;
    }

    /**
     * Desert tile constructor.
     * 
     * @param m the matieral associated with desert
     */
    public Tile(String m) {
        material = m;
        number = 0;
    }

    /**
     * Getter for material of the tile.
     * 
     * @return returns a string
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Getter for the roll number of the tile.
     * 
     * @return returns an integer
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter for the x-coordinate of the tile's center.
     * 
     * @return returns an integer
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y-coordinate of the tile's center.
     * 
     * @return returns an integer
     */
    public int getY() {
        return y;
    }
}