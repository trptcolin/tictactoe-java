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
        Controller gameController = new Controller(board);
        gameController.setGUI(new ConsoleView(gameController));
        
        Player player1, player2;

        int gameType;
        Game game;

        boolean playAgain = true;

        while(playAgain)
        {
            gameType = gameController.requestGameType();
            player1 = PlayerFactory.createPlayer(board, 'X', gameController, gameType);
            player2 = PlayerFactory.createPlayer(board, 'O', gameController, gameType);

            game = new Game(board, gameController, player1, player2);
            
            playAgain = game.play();
        }
    }
}
