import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 4:27:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class HumanPlayerTest extends Assert
{
    MockBoard board;
    char mark;
    MockGameDisplay gameDisplay;
    HumanPlayer humanPlayer;

    @Before
    public void setup()
    {
        board = new MockBoard();
        gameDisplay = new MockGameDisplay(board);
        mark = 'X';
        humanPlayer = new HumanPlayer(board, mark, gameDisplay);
    }

    @Test
    public void shouldHaveGameDisplay() throws Exception
    {
        assertEquals(gameDisplay, humanPlayer.gameDisplay);
    }

    @Test
    public void shouldGetMoveFromGameDisplayAndPopulateBoard() throws Exception
    {
        humanPlayer.makeMove();
        assertEquals(true, gameDisplay.requestUserMoveCalled);

        assertEquals(true, board.populateCalled);
        assertEquals(mark, board.populatedMark);
        assertEquals(gameDisplay.userPositionGiven, board.populatedPosition);
    }
}
