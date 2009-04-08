package trptcolin.players;

import trptcolin.main.Board;
import trptcolin.main.Controller;
import trptcolin.main.Player;
import trptcolin.main.PlayerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 26, 2009
 * Time: 1:09:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerFactoryImpl implements PlayerFactory
{   
    public final static int numberOfGameTypes = 4;


    public PlayerFactoryImpl()
    {
    }

    public Player createPlayer(Board board, char mark, Controller controller, int gameTypeCode)
    {
        Player player;

        gameTypeCode -= 1;

        switch(gameTypeCode)
        {
            case 0:
                player = new ComputerPlayer(board, mark);
                break;
            case 1:
                if(mark == 'X')
                    player = new ComputerPlayer(board, mark);
                else
                    player = new HumanPlayer(board, mark, controller);
                break;
            case 2:
                if(mark == 'X')
                    player = new HumanPlayer(board, mark, controller);
                else
                    player = new ComputerPlayer(board, mark);
                break;
            case 3:
                player = new HumanPlayer(board, mark, controller);
                break;
            default:
                player = new ComputerPlayer(board, mark);
        }

        return player;
    }

    public String gameTypeToString(int gameTypeCode)
    {
        String gameTypeString;
        switch(gameTypeCode)
        {
            case 0:
                gameTypeString = "Computer (X) vs. Computer (O)";
                break;
            case 1:
                gameTypeString = "Computer (X) vs. Human (O)";
                break;
            case 2:
                gameTypeString = "Human (X) vs. Computer (O)";
                break;
            case 3:
                gameTypeString = "Human (X) vs. Human (O)";
                break;
            default:
                gameTypeString = "Unknown main.Game Type";
        }
        return gameTypeString;
    }
}
