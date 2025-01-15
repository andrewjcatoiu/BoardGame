import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dice {

    private static final Random random = new Random();
    
    public Dice() {}

    public int roll() {
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        return die1 + die2;
    }

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