
public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();

        Board board = new Board();
        Player player1 = new Player("Andrew", "Red");
        Player player2 = new Player("Eddie", "White");
        Player player3 = new Player("Zo", "Blue");
        Player player4 = new Player("Bob", "Orange");
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
        System.out.println(player4);

        
        board.initBoard();

        dice.roll();
        dice.roll();
        dice.roll();

        // board.display();
    }
}