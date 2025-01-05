import java.util.ArrayList;
import java.util.Collections;

public class Numbers {
    private final ArrayList<Integer> numbers;

    public Numbers() {
        numbers = new ArrayList<>();
    }

    public void initNumbers() {
        int[] rolls = {2, 3, 4, 5, 6, 8, 9, 10, 11, 12};
        for (int r : rolls) {
            if (r != 2 && r != 12) {
                numbers.add(r);
                numbers.add(r);
            } else {
                numbers.add(r);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(numbers);
    }

    @Override
    public String toString() {
        return "Numbers: " + numbers.toString() + "\n";
    }
}
