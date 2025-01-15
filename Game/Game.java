import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Bank bank = new Bank();
        Board board = new Board();

        board.initBoard();
        // board.display();
        
        ArrayList<Player> order = dice.buildPlayerOrder();
        boolean flag = true;
        while (flag) {
            for (Player player : order) {
                System.out.println(player.getName() + " rolled " + dice.roll());
            }
            
            System.out.println();
            flag = false;
        }
    }
}

