package trptcolin.players;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.main.MockBoard;
import trptcolin.ui.MockController;

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
        Assert.assertEquals(true, controller.requestUserMoveCalled);

        Assert.assertEquals(true, board.populateCalled);
        Assert.assertEquals(mark, board.populatedMark);
        Assert.assertEquals(controller.userPositionGiven, board.populatedPosition);
    }
}
