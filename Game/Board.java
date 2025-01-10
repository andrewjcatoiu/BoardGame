
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;


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
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(4, 5));

        ArrayList<Integer> numbers = this.numbers.getNumbers();
        int i = 0;

        for (Tile tile : this.tiles.getTiles()) {
            String material = tile.getMaterial();
            Integer n = null;
            if (!material.equals("Desert")) {
                n = numbers.get(i);
            }
            JButton button = new JButton(tile.getMaterial() + " " + (n != null ? n : ""));
            
            frame.add(button);
            i++;
        }
    }
}
