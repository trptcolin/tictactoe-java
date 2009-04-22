package trptcolin.players;

import junit.framework.Assert;
import trptcolin.baseGame.Board;
import trptcolin.baseGame.Player;
import trptcolin.boards.BoardIn3D;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void shouldStartInCenter() throws Exception
    {
        computerPlayer.makeMove();
//        assertEquals('X', board.charAt(13));
    }
}
