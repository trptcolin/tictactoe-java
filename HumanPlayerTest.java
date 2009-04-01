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
    MockController controller;
    HumanPlayer humanPlayer;

    @Before
    public void setup()
    {
        board = new MockBoard();
        controller = new MockController(board);
        mark = 'X';
        humanPlayer = new HumanPlayer(board, mark, controller);
    }

    @Test
    public void shouldHaveGameDisplay() throws Exception
    {
        assertEquals(controller, humanPlayer.controller);
    }

    @Test
    public void shouldGetMoveFromGameDisplayAndPopulateBoard() throws Exception
    {
        humanPlayer.makeMove();
        assertEquals(true, controller.requestUserMoveCalled);

        assertEquals(true, board.populateCalled);
        assertEquals(mark, board.populatedMark);
        assertEquals(controller.userPositionGiven, board.populatedPosition);
    }
}
