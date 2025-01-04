
import java.util.ArrayList;



public class Deck {
    // 95 cards
    // 19 each material

    public static final int MATERIAL_CAPACITY = 19;
    // public static final int DECK_SIZE = 95;

    public ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    };


    public void initDeck() {
        String[] materialOptions = {"Wood", "Brick", "Sheep", "Wheat", "Ore"};
        for (String m : materialOptions) {
            for (int i = 0; i < MATERIAL_CAPACITY; i++) {
                deck.add(new Card(m));
            }
        }
    }

    public void shuffle() {
        
    }



    // ArrayList of cards with type 'Card'
}
