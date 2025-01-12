public class Player {
    
    public String name;
    public String color;
    public int settlements;
    public int cities;
    public int roads;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.settlements = 5;
        this.cities = 4;
        this.roads = 15;
    }

    @Override
    public String toString() {
        return this.name + " is playing as " + this.color;
    }
}
