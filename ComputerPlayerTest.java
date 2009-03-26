import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 8:33:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComputerPlayerTest extends Assert
{
    Board board;
    char mark;
    ComputerPlayer computerPlayer;
    Player otherPlayer;

    @Before
    public void setup()
    {
        board = new Board();
        mark = 'X';
        computerPlayer = new ComputerPlayer(board, mark);
        otherPlayer = new ComputerPlayer(board, 'O');

        computerPlayer.setOtherPlayer(otherPlayer);
    }

    @Test
    public void shouldWinHorizontally() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 3);
        board.populate('X', 1);
        board.populate('O', 4);

        computerPlayer.makeMove();
        
        assertEquals('X', board.charAt(2));
    }

    @Test
    public void shouldWinVertically() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 3);
        board.populate('O', 4);

        computerPlayer.makeMove();

        assertEquals('X', board.charAt(6));
    }

    @Test
    public void shouldWinDiagonally() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 4);
        board.populate('O', 2);
        
        computerPlayer.makeMove();

        assertEquals('X', board.charAt(8));
    }

    @Test
    public void shouldTakeFork() throws Exception
    {
        board.populate('X', 4);
        board.populate('O', 1);
        board.populate('X', 3);
        board.populate('O', 5);

        computerPlayer.makeMove();
        
        assertTrue(board.charAt(0) == 'X' || board.charAt(6) == 'X');
    }

    @Test
    public void shouldBlockVerticalWin() throws Exception
    {
        board.populate('X', 1);
        board.populate('O', 3);
        board.populate('X', 4);
                                      
        otherPlayer.makeMove();

        assertEquals('O', board.charAt(7));
    }

    @Test
    public void shouldBlockDiagonalWin() throws Exception
    {
        board.populate('X', 2);
        board.populate('O', 0);
        board.populate('X', 4);

        otherPlayer.makeMove();

        assertEquals('O', board.charAt(6));
    }

    @Test
    public void shouldStartInMiddleOrCorner() throws Exception
    {
        computerPlayer.makeMove();

        assertTrue('X' == board.charAt(4) || 'X' == board.charAt(0));
    }

}