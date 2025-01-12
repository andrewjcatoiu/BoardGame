
import java.awt.Color;
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

            int tileSize = 50;
            int xOffset = (int) (tileSize * Math.sqrt(3));
            int yOffset = tileSize * 3 / 2;

            int tileIndex = 0;
            int rows = 5;
            int columns = 5;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    if (tileIndex >= tiles.size()) {
                        return;
                    }

                    int x = 100 + col * xOffset + (row % 2 == 1 ? xOffset / 2 : 0);
                    int y = 100 + row * yOffset;

                    Tile tile = tiles.get(tileIndex);
                    String material = tile.getMaterial();
                    Color color = getMaterialColor(material);

                    drawHexagon(g2d, x, y, tileSize, material, color);

                    tileIndex++;
                }
            }
        }


    
        // private void drawHexagon(Graphics2D g2d, int x, int y, int size, Tile tile, ArrayList<Integer> numbers, int tileIndex) {
        //     Polygon hex = new Polygon();
        //     for (int i = 0; i < 6; i++) {
        //         int angle = 60 * i;
        //         int xOffset = (int) (x + size * Math.cos(Math.toRadians(angle)));
        //         int yOffset = (int) (y + size * Math.sin(Math.toRadians(angle)));
        //         hex.addPoint(xOffset, yOffset);
        //     }
    
        //     // Set color based on material
        //     Color color = getMaterialColor(tile.getMaterial());
        //     g2d.setColor(color);
        //     g2d.fillPolygon(hex);
    
        //     // Draw the hexagon border
        //     g2d.setColor(Color.BLACK);
        //     g2d.drawPolygon(hex);
    
        //     // Draw the number in a circle (if applicable)
        //     Integer num = tileIndex < numbers.size() ? numbers.get(tileIndex) : null;
        //     if (num != null) {
        //         g2d.setColor(Color.WHITE);
        //         int circleRadius = 20;
        //         g2d.fillOval(x - circleRadius / 2, y - circleRadius / 2, circleRadius, circleRadius);
    
        //         g2d.setColor(Color.BLACK);
        //         g2d.drawOval(x - circleRadius / 2, y - circleRadius / 2, circleRadius, circleRadius);
    
        //         String numText = String.valueOf(num);
        //         FontMetrics fm = g2d.getFontMetrics();
        //         int textX = x - fm.stringWidth(numText) / 2;
        //         int textY = y + fm.getAscent() / 2;
        //         g2d.drawString(numText, textX, textY);
        //     }
    
        //     // Draw the material name
        //     g2d.setColor(Color.BLACK);
        //     String material = tile.getMaterial();
        //     FontMetrics fm = g2d.getFontMetrics();
        //     int textX = x - fm.stringWidth(material) / 2;
        //     int textY = y + size / 2;
        //     g2d.drawString(material, textX, textY);
        // }

        private void drawHexagon(Graphics2D g2d, int x, int y, int size, String material, Color color) {
            int[] xPoints = new int[6];
            int[] yPoints = new int[6];
            for (int i = 0; i < 6; i++) {
                xPoints[i] = x + (int) (size * Math.cos(Math.toRadians(60 * i + 30)));
                yPoints[i] = y + (int) (size * Math.sin(Math.toRadians(60 * i + 30)));

            }

            Polygon hexagon = new Polygon(xPoints, yPoints, 6);
            g2d.setColor(color);
            g2d.fillPolygon(hexagon);

            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(hexagon);

            g2d.setColor(Color.BLACK);
            g2d.drawString(material, x - size / 2, y);
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
