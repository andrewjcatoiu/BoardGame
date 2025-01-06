
public class Game {
    public static void main(String[] args) {
        Dice dice = new Dice();

        Board board = new Board();
        board.initBoard();

        dice.roll();
        dice.roll();
        dice.roll();

        board.display();
    }
}