public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Bank bank = new Bank();
        Board board = new Board();

        board.initBoard();
        board.display();
        
        Player player1 = new Player("Andrew", "Red");
        Player player2 = new Player("Eddie", "White");
        Player player3 = new Player("Zo", "Blue");
        Player player4 = new Player("Bob", "Orange");

        System.out.println();
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
        System.out.println(player4);
        
        
        dice.roll();
        dice.roll();
        dice.roll();
    }
}

