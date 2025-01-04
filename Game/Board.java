import java.util.ArrayList;

public class Board {

    private final ArrayList<String> players;
    private final ArrayList<String> tiles;
    private final ArrayList<String> deck;

    public Board(ArrayList<String> players, ArrayList<String> tiles, ArrayList<String> deck) {
        this.players = new ArrayList<>();
        this.tiles = new ArrayList<>();
        this.deck = new ArrayList<>();
    }
}
