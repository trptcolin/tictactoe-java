/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 3:14:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game
{
    private Board board;
    private Player player1;
    private Player player2;
    private GameDisplay gameDisplay;

    public Game(Board board, GameDisplay gameDisplay, Player player1, Player player2)
    {
        this.board = board;

        this.player1 = player1;
        this.player2 = player2;
        player1.setOtherPlayer(player2);

        this.gameDisplay = gameDisplay;
    }

    public void play()
    {
        Player[] players = { player1, player2 };
        int turn = 0;

        while(!board.gameOver())
        {
            if(board.empty())
                gameDisplay.printInitialBoard();
            else
                gameDisplay.print();

            try
            {
                players[turn % 2].makeMove();
                board.gameOver();
            }
            catch(Exception e)
            {
                System.out.println("There was a problem!");
                e.printStackTrace();
            }
            turn++;
        }

        gameDisplay.printFinalBoard();
    }
}
