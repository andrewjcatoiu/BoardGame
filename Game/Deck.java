
import java.util.ArrayList;
import java.util.Collections;

public final class Deck {
    public static final int MATERIAL_CAPACITY = 19;
    public ArrayList<Card> mats;
    public ArrayList<DevCard> devs;

    public Deck() {
        this.mats = new ArrayList<>();
        this.devs = new ArrayList<>();
    };

    public void initDecks() {
        String[] materialOptions = {"Wood", "Brick", "Sheep", "Wheat", "Ore"};
        for (String m : materialOptions) {
            for (int i = 0; i < MATERIAL_CAPACITY; i++) {
                Card card = new Card(m);
                mats.add(card);
            }
        }

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

    public void shuffle() {
        Collections.shuffle(this.mats);
        Collections.shuffle(this.devs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Resources: [");
        for (Card card : this.mats) {
            sb.append(card.getMaterial());
            sb.append("\t");
        }

        sb.append("]\nDevelopment Cards: [");
        for (DevCard devCard : this.devs) {
            sb.append(devCard.getDev());
            sb.append("\t");
        }
        sb.append("]\n");

        return sb.toString();
    }
}