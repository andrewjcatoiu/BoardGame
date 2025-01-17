import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();
        Board board = new Board();
        ArrayList<Player> order = dice.buildPlayerOrder();

        Bank bank = new Bank(order);

        board.initBoard();
        board.display();
        
        // boolean flag = true;
        // while (flag) {
        //     for (Player player : order) {
        //         System.out.println(player.getName() + " rolled " + dice.roll());
        //         System.out.println(player);
        //         if (player.getName().equals("Andrew")) {
        //             player.buildSettlement(214, 124);
        //         }
        //     }
            
        //     System.out.println();
        //     flag = false;
        // }

        for (Player player : order) {
            if (player.getName().equals("Andrew")) {
                player.buildSettlement(171, 199);
                System.out.println(player);
                for (int i = 0; i < 10; i++) {
                    int roll = dice.roll();
                    bank.deal(roll);
                    System.out.println(player);
                }
            }
        }



        System.out.println(board);
    }
}

