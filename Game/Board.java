
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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        for (Tile tile : this.tiles.getTiles()) {
            // fill up a coordinate plane
        }

        for (int number : this.numbers.getNumbers()) {
            // fill up same coordinate plane
        }
    }
}
