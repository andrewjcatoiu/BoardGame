import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Board board = new Board();
        ArrayList<Player> order = dice.buildPlayerOrder();

        Bank bank = new Bank(board, order);

        board.initBoard();
        board.display();
        
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

