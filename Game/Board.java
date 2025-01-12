
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Board {

    private final Deck deck;
    private final Tiles tiles;
    private final Numbers numbers;
    
    public Board() {
        this.deck = new Deck();
        this.tiles = new Tiles();
        this.numbers = new Numbers();
    }

    public void initBoard() {
        this.deck.initDecks();
        System.out.println(this.deck);

        this.tiles.initTiles();
        System.out.println(this.tiles);

        this.numbers.initNumbers();
        System.out.println(this.numbers);

        this.deck.shuffle();
        System.out.println(this.deck);

        this.tiles.shuffle();
        System.out.println(this.tiles);

        this.numbers.shuffle();
        System.out.println(this.numbers);
    }

    public void display() {
        JFrame frame = new JFrame("Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new HexBoardPanel(tiles.getTiles(), numbers.getNumbers()));
        frame.setVisible(true);

        // ArrayList<Integer> numbers = this.numbers.getNumbers();
        // int i = 0;

        // for (Tile tile : this.tiles.getTiles()) {
        //     String material = tile.getMaterial();
        //     Integer n = null;
        //     if (!material.equals("Desert")) {
        //         n = numbers.get(i);
        //     }
        //     JButton button = new JButton(tile.getMaterial() + " " + (n != null ? n : ""));
            
        //     frame.add(button);
        //     i++;
        // }
    }

    private class HexBoardPanel extends JPanel {
        private final ArrayList<Tile> tiles;
        private final ArrayList<Integer> numbers;
    
        public HexBoardPanel(ArrayList<Tile> tiles, ArrayList<Integer> numbers) {
            this.tiles = tiles;
            this.numbers = numbers;
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            int hexSize = 60; // Side length of each hexagon
            int xOffset = 100; // X offset for the board
            int yOffset = 100; // Y offset for the board
            double hexHeight = Math.sqrt(3) * hexSize; // Height of a hexagon
            double hexWidth = 2 * hexSize; // Width of a hexagon
            double verticalSpacing = hexHeight * 0.75; // Staggered vertical spacing
            double horizontalSpacing = hexSize * 1.5; // Horizontal spacing

            int[][] hexPositions = {
                {0, 0}, {1, 0}, {2, 0},
                {-1, 1}, {0, 1}, {1, 1}, {2, 1}, {3, 1},
                {-2, 2}, {-1, 2}, {0, 2}, {1, 2}, {2, 2}, {3, 2}, {4, 2},
                {-1, 3}, {0, 3}, {1, 3}, {2, 3}, {3, 3},
                {0, 4}, {1, 4}, {2, 4}
            };

            int tileIndex = 0;
            for (int[] position : hexPositions) {
                if (tileIndex >= tiles.size()) break;

                // Calculate hexagon coordinates
                int x = xOffset + (int) (position[0] * horizontalSpacing);
                int y = yOffset + (int) (position[1] * verticalSpacing);
                drawHexagon(g2d, x, y, hexSize, tiles.get(tileIndex), numbers, tileIndex);
                tileIndex++;
            }
        }

    
        private void drawHexagon(Graphics2D g2d, int x, int y, int size, Tile tile, ArrayList<Integer> numbers, int tileIndex) {
            Polygon hex = new Polygon();
            for (int i = 0; i < 6; i++) {
                int angle = 60 * i;
                int xOffset = (int) (x + size * Math.cos(Math.toRadians(angle)));
                int yOffset = (int) (y + size * Math.sin(Math.toRadians(angle)));
                hex.addPoint(xOffset, yOffset);
            }
    
            // Set color based on material
            Color color = getMaterialColor(tile.getMaterial());
            g2d.setColor(color);
            g2d.fillPolygon(hex);
    
            // Draw the hexagon border
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(hex);
    
            // Draw the number in a circle (if applicable)
            Integer num = tileIndex < numbers.size() ? numbers.get(tileIndex) : null;
            if (num != null) {
                g2d.setColor(Color.WHITE);
                int circleRadius = 20;
                g2d.fillOval(x - circleRadius / 2, y - circleRadius / 2, circleRadius, circleRadius);
    
                g2d.setColor(Color.BLACK);
                g2d.drawOval(x - circleRadius / 2, y - circleRadius / 2, circleRadius, circleRadius);
    
                String numText = String.valueOf(num);
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x - fm.stringWidth(numText) / 2;
                int textY = y + fm.getAscent() / 2;
                g2d.drawString(numText, textX, textY);
            }
    
            // Draw the material name
            g2d.setColor(Color.BLACK);
            String material = tile.getMaterial();
            FontMetrics fm = g2d.getFontMetrics();
            int textX = x - fm.stringWidth(material) / 2;
            int textY = y + size / 2;
            g2d.drawString(material, textX, textY);
        }
    
        private Color getMaterialColor(String material) {
            switch (material) {
                case "Brick":
                    return new Color(178, 34, 34); // Red
                case "Wood":
                    return new Color(34, 139, 34); // Green
                case "Sheep":
                    return new Color(144, 238, 144); // Light Green
                case "Wheat":
                    return new Color(240, 230, 140); // Yellow
                case "Ore":
                    return new Color(169, 169, 169); // Gray
                case "Desert":
                    return new Color(238, 221, 130); // Sand
                default:
                    return Color.LIGHT_GRAY; // Default color
            }
        }
    }
    
}
