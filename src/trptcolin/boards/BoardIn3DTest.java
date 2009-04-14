package trptcolin.boards;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.main.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 9, 2009
 * Time: 11:33:12 AM
 */
public class BoardIn3DTest extends Assert
{
    private BoardIn3D board;

    @Before
    public void setup()
    {
        board = new BoardIn3D();
    }

    @Test
    public void shouldBeEmpty() throws Exception
    {
        for(int position = 0; position < 27; position++)
            assertEquals(false, board.isOccupied(position));

        assertEquals(true, board.empty());
    }

    @Test
    public void shouldNotBeOver() throws Exception
    {
        assertEquals(false, board.gameOver());
    }

    @Test
    public void shouldBeFull() throws Exception
    {
        for(int position = 0; position < 27; position++)
            board.populate('X', position);

        assertEquals(true, board.gameOver());
        assertEquals(true, board.isFull());
    }

    @Test
    public void shouldClearBoard() throws Exception
    {
        board.populate('X', 0);
        assertEquals(false, board.empty());

        board.clear();
        assertEquals(true, board.empty());
    }

    @Test
    public void shouldWinWidthwiseOnAnyDepthwiseSlice() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 1);
        board.populate('X', 2);
        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('O', 9);
        board.populate('O', 10);
        board.populate('O', 11);
        assertEquals('O', board.getWinner());

        board.clear();

        board.populate('X', 18);
        board.populate('X', 19);
        board.populate('X', 20);
        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldWinHeightwiseOnAnyDepthwiseSlice() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 3);
        board.populate('X', 6);
        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('O', 9);
        board.populate('O', 12);
        board.populate('O', 15);
        assertEquals('O', board.getWinner());

        board.clear();

        board.populate('X', 18);
        board.populate('X', 21);
        board.populate('X', 24);
        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldWinDiagonallyOnAnyDepthwiseSlice() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 4);
        board.populate('X', 8);
        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('O', 9);
        board.populate('O', 13);
        board.populate('O', 17);
        assertEquals('O', board.getWinner());

        board.clear();

        board.populate('X', 18);
        board.populate('X', 22);
        board.populate('X', 26);
        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldWinDepthwiseOnAnyWidthwiseSlice() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 9);
        board.populate('X', 18);
        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('O', 1);
        board.populate('O', 10);
        board.populate('O', 19);
        assertEquals('O', board.getWinner());

        board.clear();

        board.populate('X', 2);
        board.populate('X', 11);
        board.populate('X', 20);
        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldWinDiagonallyOnAnyWidthwiseSlice() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 12);
        board.populate('X', 24);

        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('X', 1);
        board.populate('X', 13);
        board.populate('X', 25);

        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('X', 2);
        board.populate('X', 14);
        board.populate('X', 26);

        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldWinDiagonallyOnAnyHeightwiseSlice() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 10);
        board.populate('X', 20);

        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('X', 3);
        board.populate('X', 13);
        board.populate('X', 23);

        assertEquals('X', board.getWinner());

        board.clear();

        board.populate('X', 6);
        board.populate('X', 16);
        board.populate('X', 26);

        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldWinDiagonallyIn3D() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 13);
        board.populate('X', 26);
        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldHave49PossibleWinSets() throws Exception
    {
        assertEquals(49, board.winSets.length);
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

    @Test
    public void shouldRecordWinnerAfterPopulating() throws Exception
    {
        board.populate('X', 0);
        board.populate('X', 1);

        assertEquals(false, board.gameOver);
        assertEquals(0, board.winner);

        board.populate('X', 2);

        assertEquals(true, board.gameOver);
        assertEquals('X', board.winner);
    }

    @Test
    public void shouldHashBySquares() throws Exception
    {
        board.populate('X', 0);

        Board newBoard = new BoardIn3D();
        newBoard.populate('X', 0);

        assertEquals(board.hashCode(), newBoard.hashCode());
    }
}
