public class Board {

    private final Deck deck;
    private final Tiles tiles;
    
    public Board() {
        this.deck = new Deck();
        this.tiles = new Tiles();
    }

    public void initBoard() {
        this.deck.initDecks();
        System.out.println(this.deck);

        this.tiles.initTiles();
        System.out.println(this.tiles);

        this.deck.shuffle();
        System.out.println(this.deck);

        this.tiles.shuffle();
        System.out.println(this.tiles);
    }
}
