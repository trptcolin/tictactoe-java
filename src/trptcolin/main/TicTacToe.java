package trptcolin.main;

import trptcolin.players.PlayerFactoryImpl;
import trptcolin.players.Player;
import trptcolin.players.PlayerFactory;
import trptcolin.ui.Controller;
import trptcolin.ui.ControllerImpl;
import trptcolin.ui.SwingView;

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
        Game game;

        PlayerFactory playerFactory = new PlayerFactoryImpl();
        Player player1, player2;

        Board board = new Board();
        Controller gameController = new ControllerImpl(board);
        gameController.setUI(new SwingView(gameController, playerFactory));

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