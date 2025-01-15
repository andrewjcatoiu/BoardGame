public class Player {
    
    private String name;
    private String color;
    private int settlements;
    private int cities;
    private int roads;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.settlements = 5;
        this.cities = 4;
        this.roads = 15;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public int getSettlements() {
        return this.settlements;
    }

    public int getCities() {
        return this.cities;
    }

    public int getRoads() {
        return this.roads;
    }

    @Override
    public String toString() {
        return this.name + " is playing as " + this.color;
    }
}
