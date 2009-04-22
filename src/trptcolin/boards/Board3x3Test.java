package trptcolin.boards;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.baseGame.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 9:47:23 AM
 */

public class Board3x3Test extends Assert
{

    private Board3x3 board;

    @Before
    public void setup()
    {
        board = new Board3x3();
    }

    @Test
    public void shouldCopyItself() throws Exception
    {
        board.populate('X', 0);
        Board otherBoard;
        otherBoard = board.copy();

        assertEquals(true, board.sameSquares(otherBoard));
        otherBoard.populate('X', 8);
        assertEquals(false, board.sameSquares(otherBoard));
        board.populate('X', 8);
        assertEquals(true, board.sameSquares(otherBoard));
    }

}
