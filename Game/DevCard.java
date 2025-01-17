/**
 * Class responsible for created a development card.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class DevCard {

    /**
     * The type of development card.
     */
    public String dev;

    /**
     * Default constructor.
     * 
     * @param dev the type of card (e.g., Knight)
     */
    public DevCard(String dev) {
        this.dev = dev;
    }

    /**
     * Getter for the type of card.
     * 
     * @return returns a string
     */
    public String getDev() {
        return this.dev;
    }
}