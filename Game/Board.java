
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Board {

    private final Deck deck;
    private final Tiles tiles;
    private final Map<Integer, ArrayList<int[]>> coords;
    
    public Board() {
        this.deck = new Deck();
        this.tiles = new Tiles();
        this.coords = new HashMap<>();
    }

    public void initBoard() {
        this.deck.initDecks();

        this.tiles.initTiles();
        
        this.tiles.shuffle();
        
        this.deck.shuffle();
    }

    public void display() {
        JFrame frame = new JFrame("Board Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        HexBoardPanel panel = new HexBoardPanel(tiles.getTiles(), this.coords);
        frame.add(panel);
        frame.setVisible(true);
        panel.paintImmediately(0, 0, frame.getWidth(), frame.getHeight());
    }

    private class HexBoardPanel extends JPanel {
        private final ArrayList<Tile> tiles;
        private final Map<Integer, ArrayList<int[]>> coords;
    
        public HexBoardPanel(ArrayList<Tile> tiles, Map<Integer, ArrayList<int[]>> coords) {
            this.tiles = tiles;
            this.coords = coords;
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
                        drawHexagon(g2d, rowX + col * xOffset, rowY, tileSize, material, color, tileIndex, number);

                        tileIndex++;
                    }
                }
            }
        }

        private void drawHexagon(Graphics2D g2d, int x, int y, int size, String material, Color color, int tileIndex, Integer number) {
            int[] xPoints = new int[6];
            int[] yPoints = new int[6];
            for (int i = 0; i < 6; i++) {
                xPoints[i] = x + (int) (size * Math.cos(Math.toRadians(60 * i + 30)));
                yPoints[i] = y + (int) (size * Math.sin(Math.toRadians(60 * i + 30)));
            }

            Polygon hexagon = new Polygon(xPoints, yPoints, 6);
            
            ArrayList<int[]> pairs = new ArrayList<>();
            for (int i = 0; i < xPoints.length; i++) {
                pairs.add(new int[]{xPoints[i], yPoints[i]});
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

    @Override
    public String toString() {
        if (coords.isEmpty()) {
            return "Coordinates map is empty. Ensure the board has been displayed.";
        }

        StringBuilder sb = new StringBuilder();
        for (var entry : this.coords.entrySet()) {
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
