
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
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
    }
}
