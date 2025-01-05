
import java.util.ArrayList;
import java.util.Collections;



public final class Deck {
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
                Card card = new Card(m);
                deck.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Card card : this.deck) {
            sb.append(card.getMaterial());
            sb.append("\t");
        }
        sb.append("]");

        return sb.toString();
    }
}
