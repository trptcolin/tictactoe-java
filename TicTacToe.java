/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 1:18:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicTacToe
{
    public static void main(String[] args)
    {
        Board board = new Board();
        GameDisplay gameDisplay = new GameDisplay(board);

        Player player1, player2;

        int gameType = gameDisplay.requestGameType();
        player1 = PlayerFactory.createPlayer(board, 'X', gameDisplay, gameType);
        player2 = PlayerFactory.createPlayer(board, 'O', gameDisplay, gameType);

        Game game = new Game(board, gameDisplay, player1, player2);

        game.play();
    }
}
