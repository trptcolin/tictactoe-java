package trptcolin.boards;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.baseGame.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 14, 2009
 * Time: 2:25:41 PM
 */
public class Board4By4Test extends Assert
{
    private Board board;

    @Before
    public void setup()
    {
        board = new Board4By4();
    }

    @Test
    public void shouldBeEmpty() throws Exception
    {
        assertEquals(true, board.empty());
    }

    @Test
    public void shouldBeFull() throws Exception
    {
        for(int i = 0; i < 16; i++)
            board.populate('X', i);

        assertEquals(true, board.isFull());
    }


    @Test
    public void shouldBeVerticalWin() throws Exception
    {
        board.populate('X', 3);
        board.populate('X', 7);
        board.populate('X', 11);
        board.populate('X', 15);

        assertEquals(true, board.isWon());
    }

    @Test
    public void shouldBeHorizontalWin() throws Exception
    {
        board.populate('X', 12);
        board.populate('X', 13);
        board.populate('X', 14);
        board.populate('X', 15);

        assertEquals(true, board.isWon());
    }

    @Test
    public void shouldBeDiagonalWin() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 5);
        board.populate('X', 10);
        board.populate('X', 15);

        assertEquals(true, board.isWon());
    }

    @Test
    public void shouldCopyItself() throws Exception
    {
        board.populate('X', 10);

        Board newBoard = board.copy();
        assertEquals('X', newBoard.charAt(10));

        board.populate('O', 11);
        assertEquals(0, newBoard.charAt(11));
    }

    @Test
    public void shouldHashBySquares() throws Exception
    {
        board.populate('X', 13);
        Board newBoard = board.copy();

        assertEquals(board.hashCode(), newBoard.hashCode());
        assertTrue(board.equals(newBoard));
    }

    @Test
    public void shouldNotWinWithOnly3InARow() throws Exception
    {
        board.populate('X', 12);
        board.populate('X', 13);
        board.populate('X', 14);

        assertEquals(false, board.isWon());
    }

    @Test
    public void shouldNotWinAtPositionWithOnly3InARow() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 1);
        board.populate('X', 2);

        assertEquals(false, board.isWon(0));
    }

    @Test
    public void shouldConvertToString() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 1);
        board.populate('X', 2);
        board.populate('X', 15);

        assertEquals("XXX            X", board.toString());
    }

}
