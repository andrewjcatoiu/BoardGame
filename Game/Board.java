
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class responsible for drawing the board and storing playable coordinates.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Board {

    /**
     * Initial deck of shuffled development cards.
     */
    private final Deck deck;

    /**
     * Initial deck of shuffled hex tiles.
     */
    private final Tiles tiles;

    /**
     * Map of playable coordinates. Each entry key is the index of a tile after shuffling, and its
     * value is a list of coordinate pairs representing each of the six vertices for that tile.
     */
    private static final Map<Integer, ArrayList<int[]>> coords = new HashMap<>();

    /**
     * Default constructor.
     */
    public Board() {
        this.deck = new Deck();
        this.tiles = new Tiles();
    }

    /**
     * Initializes development cards and tiles. Cards and tiles are shuffled after.
     */
    public void initBoard() {
        this.deck.initDecks();
        this.tiles.initTiles();
        this.tiles.shuffle();    
        this.deck.shuffle();
    }

    /**
     * Opens frame and initializes inner class for drawing.
     */
    public void display() {
        JFrame frame = new JFrame("Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        HexBoardPanel panel = new HexBoardPanel(Tiles.getTiles(), coords);
        frame.add(panel);
        frame.setVisible(true);
        panel.paintImmediately(0, 0, frame.getWidth(), frame.getHeight());
    }

    /**
     * Draws hexagon-shaped board.
     */
    private class HexBoardPanel extends JPanel {

        /**
         * The list of tiles after shuffle.
         */
        private final ArrayList<Tile> tiles;

        /**
         * Initial map of playable coordinates.
         */
        private final Map<Integer, ArrayList<int[]>> coords;
    
        /**
         * Default constructor.
         * 
         * @param tiles the shuffled tiles
         * @param coords the map to store coordinate pairs
         */
        public HexBoardPanel(ArrayList<Tile> tiles, Map<Integer, ArrayList<int[]>> coords) {
            this.tiles = tiles;
            this.coords = coords;
        }
    
        /**
         * Automatically called during paint(). Draws a hexagon shaped component with 19 hexagon shaped sub-components.
         * 
         * @param g the {@code Graphics} context in shich to paint
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int tileSize = 50;
            int startX = 300;
            int startY = 100;

            int xOffset = (int) (tileSize * Math.sqrt(3));
            int yOffset = tileSize * 3 / 2;

            int[] rows = {3, 4, 5, 4, 3};
            int tileIndex = 0;

            for (int row = 0; row < rows.length; row++) {
                int tilesInRow = rows[row];
                int rowX = startX - (tilesInRow * xOffset) / 2;
                int rowY = startY + row * yOffset;

                for (int col = 0; col < tilesInRow; col++) {
                    if (tileIndex < tiles.size()) {                        
                        Tile tile = tiles.get(tileIndex);
                        String material = tile.getMaterial();
                        Color color = getMaterialColor(material);
                        Integer number = tile.getNumber() != 0 ? tile.getNumber() : null;

                        int centerX = rowX + col * xOffset;
                        int centerY = rowY;

                        int[] xPoints = new int[6];
                        int[] yPoints = new int[6];
                        for (int i = 0; i < 6; i++) {
                            xPoints[i] = (int) Math.round(centerX + tileSize * Math.cos(Math.toRadians(60 * i + 30)));
                            yPoints[i] = (int) Math.round(centerY + tileSize * Math.sin(Math.toRadians(60 * i + 30)));
                        }

                        drawHexagon(g2d, centerX, centerY, material, color, tileIndex, number, xPoints, yPoints);
                        tileIndex++;
                    }
                }
            }

            int buttonRadius = 10;
            for (var entry : coords.entrySet()) {
                for (int[] vertex : entry.getValue()) {
                    JButton button = new JButton();
                    button.setBounds(vertex[0] - buttonRadius / 2, vertex[1] - buttonRadius / 2, buttonRadius, buttonRadius);
                    button.addActionListener(e -> System.out.println("Button clicked at: " + vertex[0] + ", " + vertex[1]));
                    this.add(button);
                }
            }
        }

        /**
         * Draws a singular hex tile. Components of tile include edges, fill color, material, roll number, and a circle to
         * surround the roll number. Fill color is completely dependent on the material. Coordinate pairs are stored as an
         * integer array of length 4, with the first two items being the x/y-coordinates, the third being a flag to indicate
         * if a settlement has been built, and the fourth being a flag to indicate if a city has been built.
         * 
         * @param g2d the {@code Graphics 2D} context used for rendering the hexagon
         * @param x the x-coordinate of the hexagon's center
         * @param y the y-coordinate of the hexagon's center
         * @param material the resource type associated with the tile 
         * @param color the color to fill the hexagon
         * @param tileIndex the index of the tile from the ArrayList, used to store its vertex coordinates
         * @param number the roll number of the tile (can be {@code null} if the tile is a desert)
         * @param xPoints an array of x-coordinates for the vertices of the hexagon
         * @param yPoints an array of y-coordinates for the vertices of the hexagon
         */
        private void drawHexagon(Graphics2D g2d, int x, int y, String material, Color color, int tileIndex, Integer number, int[] xPoints, int[] yPoints) {
            Polygon hexagon = new Polygon(xPoints, yPoints, 6);
            
            ArrayList<int[]> pairs = new ArrayList<>();
            for (int i = 0; i < xPoints.length; i++) {
                pairs.add(new int[]{xPoints[i], yPoints[i], 0, 0});
            }

            coords.put(tileIndex, pairs);
            
            g2d.setColor(color);
            g2d.fillPolygon(hexagon);

            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(hexagon);

            g2d.setColor(Color.BLACK);
            String text = material;
            int textX = x - g2d.getFontMetrics().stringWidth(text) / 2;
            int textY = y + g2d.getFontMetrics().getAscent() - 15;

            g2d.drawString(text, textX, textY);

            if (number != null) {
                int circleRadius = 20;
                int circleX = x - circleRadius / 2;
                int circleY = y + 13 - circleRadius / 2;

                g2d.setColor(Color.WHITE);
                g2d.fillOval(circleX, circleY, circleRadius, circleRadius);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(circleX, circleY, circleRadius, circleRadius);

                String numText = String.valueOf(number);
                int numberX = x - g2d.getFontMetrics().stringWidth(numText) / 2;
                int numberY = y + g2d.getFontMetrics().getAscent() + 5;
                
                g2d.drawString(numText, numberX, numberY);
            }
        }
    
        /**
         * Uses the associated material to determine the fill color of the tile.
         * 
         * @param material the resource type
         * @return a fill color
         */
        private Color getMaterialColor(String material) {
            return switch (material) {
                case "Brick" -> new Color(255, 160, 0);
                case "Wood" -> new Color(34, 139, 34);
                case "Sheep" -> new Color(144, 238, 144);
                case "Wheat" -> new Color(240, 230, 140);
                case "Ore" -> new Color(163, 137, 138);
                case "Desert" -> new Color(202, 167, 100);
                default -> Color.LIGHT_GRAY;
            };
        }
    }

    /**
     * Getter for a tile's vertices.
     * 
     * @param tileIndex the index used to locate the tile in the coordinates map
     * @return returns a list of coordinate pairs
     */
    public ArrayList<int[]> getTileVertices(int tileIndex) {
        return coords.get(tileIndex);
    }

    /**
     * Getter for all playable coordinates.
     * 
     * @return returns a map of tiles along with their associated list of vertex coordinates
     */
    public static Map<Integer, ArrayList<int[]>> getCoordinates() {
        return coords;
    }

    @Override
    public String toString() {
        if (coords.isEmpty()) {
            return "Coordinates map is empty. Ensure the board has been displayed.";
        }

        StringBuilder sb = new StringBuilder();
        for (var entry : coords.entrySet()) {
            Tile tile = tiles.getTile(entry.getKey());
            sb.append(tile.getMaterial()).append(" ").append(tile.getNumber()).append(" [\n\t\t");

            ArrayList<int[]> pairs = entry.getValue();
            for (int[] pair : pairs) {
                sb.append("(").append(pair[0]).append(", ").append(pair[1]).append(")\n\t\t");
            }

            sb.append("\n]\n\n");
        }

        return sb.toString();
    }
}