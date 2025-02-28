import java.util.ArrayList;

/**
 * Class responsible for running the simulation of this board game.
 * 
 * @author Andrew Catoiu
 * @version January 2025
 */
public class Game {
    /**
     * Initializes all classes necessary to play the game.
     * 
     * @param args command line arguments
     */
    public static void main(String args[]) {
        Dice dice = new Dice();
        Board board = new Board();
        ArrayList<Player> order = dice.buildPlayerOrder();

        Bank bank = new Bank(order);

        board.initBoard();
        board.display();
        // board.chooseStructurePlacement(order);
        
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

        System.out.println(bank);
        for (Player player : order) {
            if (player.getName().equals("Andrew")) {
                player.buildSettlement(171, 50);
                System.out.println(player);
                for (int i = 0; i < 10; i++) {
                    int roll = dice.roll();
                    bank.deal(roll);
                    System.out.println(player);
                }
            }
        }

        // board.chooseStructurePlacement(order);


        System.out.println(board);
    }

    public void chooseStructurePlacement(ArrayList<Player> order) {
        
    }
}

