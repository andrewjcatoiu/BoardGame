import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class responsible for dice rolling and game randomness.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Dice {

    /**
     * A shared {@code Random} instance used for generating random values.
     */
    private static final Random random = new Random();
    
    /**
     * Default constructor.
     */
    public Dice() {}

    /**
     * Rolls the dice.
     * 
     * @return returns an integer sum of the two die
     */
    public int roll() {
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        return die1 + die2;
    }

    /**
     * Determines the turn rotation of the players. The first person in the list will go first.
     * 
     * @return returns a shuffled list of the players
     */
    public ArrayList<Player> buildPlayerOrder() {
        ArrayList<Player> playerOrder = new ArrayList<>();
        playerOrder.add(new Player("Andrew", "Red"));
        playerOrder.add(new Player("Eddie", "White"));
        playerOrder.add(new Player("Zo", "Blue"));
        playerOrder.add(new Player("Bob", "Orange"));

        Collections.shuffle(playerOrder);
        return playerOrder;
    }
}