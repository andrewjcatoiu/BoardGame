
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
    // private final Numbers numbers;
    
    public Board() {
        this.deck = new Deck();
        this.tiles = new Tiles();
        // this.numbers = new Numbers();
    }

    public void initBoard() {
        this.deck.initDecks();
        // System.out.println(this.deck);

        this.tiles.initTiles();
        // System.out.println(this.tiles);
        
        this.tiles.shuffle();
        // System.out.println(this.tiles);
        
        this.deck.shuffle();
        // System.out.println(this.deck);
    }

    public void display() {
        JFrame frame = new JFrame("Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new HexBoardPanel(tiles.getTiles()));
        frame.setVisible(true);        
    }

    private class HexBoardPanel extends JPanel {
        private final ArrayList<Tile> tiles;
    
        public HexBoardPanel(ArrayList<Tile> tiles) {
            this.tiles = tiles;
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
                        drawHexagon(g2d, rowX + col * xOffset, rowY, tileSize, material, color, number);

                        tileIndex++;
                    }
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
            System.out.println(material + " " + number);
            ArrayList<int[]> arr = new ArrayList<>();
            for (int i = 0; i < xPoints.length; i++) {
                int[] coords = new int[2];
                coords[0] = xPoints[i];
                coords[1] = yPoints[i];
                arr.add(coords);
                System.out.println("Coord " + i + ": (" + coords[0] + ", " + coords[1] + ")");
                String s = "(" + coords[0] + ", " + coords[1] + ")";
                g2d.drawString(s, coords[0], coords[1]);
            }

            // for (int a : xPoints) {
            //     System.out.println(a);
            // }

            // for (int b : yPoints) {
            //     System.out.println(b);
            // }
            System.out.println();
            
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
                    return new Color(242, 179, 86); // Sand
                default:
                    return Color.LIGHT_GRAY; // Default color
            }
        }
    }   
}
