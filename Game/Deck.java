
import java.util.ArrayList;



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
                System.out.println(card.getMaterial());
            }
        }

        System.out.println(this.deck);
    }

    public void shuffle() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Card card : this.deck) {
            sb.append(card.getMaterial());
            sb.append(", ");
        }
        sb.append("]");

        return sb.toString();
    }


    // ArrayList of cards with type 'Card'
}
