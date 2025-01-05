import java.util.Random;

public class Dice {

    private static final Random random = new Random();
    
    public Dice() {}

    public int roll() {
        int die1 = random.nextInt(6) + 1;
        System.out.println("Rolled: " + die1);
        int die2 = random.nextInt(6) + 1;
        System.out.println("Rolled: " + die2);

        int sum = die1 + die2;
        System.out.println("Total: " + sum + "\n");
        return sum;
    }
}