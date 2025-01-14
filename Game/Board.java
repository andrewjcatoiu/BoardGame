
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
        
        this.numbers.initNumbers();
        System.out.println(this.numbers);
        
        // this.numbers.shuffle();
        // System.out.println(this.numbers);

        this.tiles.initTiles(this.numbers);
        System.out.println(this.tiles);
        
        this.tiles.shuffle();
        System.out.println(this.tiles);
        
        // this.deck.shuffle();
        // System.out.println(this.deck);
    }

    public void display() {
        JFrame frame = new JFrame("Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new HexBoardPanel(tiles.getTiles(), numbers.getNumbers()));
        frame.setVisible(true);        
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
            int startX = 300;
            int startY = 100;

            int xOffset = (int) (tileSize * Math.sqrt(3));
            int yOffset = tileSize * 3 / 2;
            int numberIndex = 0;

            int[] rowCounts = {3, 4, 5, 4, 3};

            for (int row = 0; row < rowCounts.length; row++) {
                int tilesInRow = rowCounts[row];
                int rowX = startX - (tilesInRow * xOffset) / 2;
                int rowY = startY + row * yOffset;

                for (int col = 0; col < tilesInRow; col++) {
                    if (numberIndex >= numbers.size()) {
                        break;
                    }

                    Tile tile = tiles.get(row * 5 + col);
                    String material = tile.getMaterial();
                    Color color = getMaterialColor(material);

                    Integer number = !"Desert".equalsIgnoreCase(material) ? numbers.get(numberIndex++) : null;

                    drawHexagon(g2d, rowX, rowY, tileSize, material, color, number);
                }
            }
        }

        private void drawHexagon(Graphics2D g2d, int x, int y, int size, String material, Color color, Integer number) {
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
            String text = material;
            int textX = x - g2d.getFontMetrics().stringWidth(text) / 2;
            int textY = y + g2d.getFontMetrics().getAscent() - 15;

            g2d.drawString(text, textX, textY);

            if (!"Desert".equalsIgnoreCase(material) && number != null) {
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
                
                g2d.setColor(Color.BLACK);
                g2d.drawString(numText, numberX, numberY);
            }
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
