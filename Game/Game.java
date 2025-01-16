import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Board board = new Board();
        Bank bank = new Bank(board);

        board.initBoard();
        board.display();
        
        ArrayList<Player> order = dice.buildPlayerOrder();
        boolean flag = true;
        while (flag) {
            for (Player player : order) {
                System.out.println(player.getName() + " rolled " + dice.roll());
            }
            
            System.out.println();
            flag = false;
        }

        System.out.println(board);
    }
}

