import java.util.ArrayList;
import java.util.Collections;

/**
 * Class responsible for building the deck of development cards.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public final class Deck {
    /**
     * The initial list of development cards.
     */
    public ArrayList<DevCard> devs;

    /**
     * Default constructor.
     */
    public Deck() {
        this.devs = new ArrayList<>();
    };

    /**
     * Populates the list of development cards. 
     */
    public void initDecks() {
        String[][] devOptions = {{"Knight", "14"}, {"Victory Point", "5"}, {"Year of Plenty", "2"}, {"Monopoly", "2"}, {"Road Building", "2"}};
        for (String[] d : devOptions) {
            String dev = d[0];
            int capacity = Integer.parseInt(d[1]);
            for (int i = 0; i < capacity; i++) {
                DevCard devCard = new DevCard(dev);
                devs.add(devCard);
            }
        }
    }

    /**
     * Shuffles the list of development cards.
     */
    public void shuffle() {
        Collections.shuffle(this.devs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Development Cards: [");
        for (DevCard devCard : this.devs) {
            sb.append(devCard.getDev());
            sb.append("\t");
        }

        sb.append("]\n");
        return sb.toString();
    }
}