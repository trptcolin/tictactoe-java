package trptcolin.main;

import trptcolin.baseGame.*;
import trptcolin.boards.Board3x3;
import trptcolin.players.PlayerFactoryImpl;
import trptcolin.ui.ConsoleView;
import trptcolin.ui.ControllerImpl;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 1:18:35 PM
 */
public class TicTacToe
{
    public static void main(String[] args)
    {
        Game game;

        PlayerFactory playerFactory = new PlayerFactoryImpl();
        Player player1, player2;

        Board board = new Board3x3();
        Controller gameController = new ControllerImpl(board);
        gameController.setUI(new ConsoleView(gameController, playerFactory, board));

        boolean playAgain = true;
        int gameType;
                   
        while(playAgain)
        {
            gameType = gameController.requestGameType();
            player1 = playerFactory.createPlayer(board, 'X', gameController, gameType);
            player2 = playerFactory.createPlayer(board, 'O', gameController, gameType);

            game = new Game(board, gameController, player1, player2);
            
            playAgain = game.play();
        }
    }
}
