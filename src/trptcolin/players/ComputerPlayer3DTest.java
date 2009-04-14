package trptcolin.players;

import junit.framework.Assert;
import org.junit.Before;
import trptcolin.boards.BoardIn3D;
import trptcolin.main.Board;
import trptcolin.main.Player;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 14, 2009
 * Time: 1:46:16 PM
 */
public class ComputerPlayer3DTest extends Assert
{
    Board board;
    char mark;
    ComputerPlayer computerPlayer;
    Player otherPlayer;

    @Before
    public void setup()
    {
       board = new BoardIn3D();
       mark = 'X';
       computerPlayer = new ComputerPlayer(board, mark);
       computerPlayer.setSearchDepth(2);
       otherPlayer = new ComputerPlayer(board, 'O');

       computerPlayer.setOtherPlayer(otherPlayer);
    }
}
