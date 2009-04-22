package trptcolin.baseGame;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: Apr 22, 2009
 * Time: 9:23:56 AM
 */
public class BoardTest extends Assert
{
    private MockBoard board;

    @Before
    public void setup()
    {
        board = new MockBoard();
    }

    @Test
    public void shouldBeEmpty() throws Exception
    {
        assertEquals(false, board.isOccupied(0));
        assertEquals(false, board.isOccupied(1));
        assertEquals(false, board.isOccupied(2));
        assertEquals(false, board.isOccupied(3));
        assertEquals(false, board.isOccupied(4));
        assertEquals(false, board.isOccupied(5));
        assertEquals(false, board.isOccupied(6));
        assertEquals(false, board.isOccupied(7));
        assertEquals(false, board.isOccupied(8));

        assertEquals(true, board.empty());
    }

    @Test
    public void shouldMakeMove() throws Exception
    {
        board.populate('X', 0);
        assertEquals(true, board.isOccupied(0));
        assertEquals(false, board.isOccupied(1));
        assertEquals(false, board.isOccupied(2));
        assertEquals(false, board.isOccupied(3));
        assertEquals(false, board.isOccupied(4));
        assertEquals(false, board.isOccupied(5));
        assertEquals(false, board.isOccupied(6));
        assertEquals(false, board.isOccupied(7));
        assertEquals(false, board.isOccupied(8));
    }

    @Test
    public void shouldNotBeOver() throws Exception
    {
        assertEquals(false, board.gameOver());
    }

    @Test
    public void shouldBeFull() throws Exception
    {
        board.populate('X', 0);
        board.populate('0', 1);
        board.populate('X', 2);
        board.populate('O', 3);
        board.populate('X', 4);
        board.populate('O', 5);
        board.populate('X', 6);
        board.populate('O', 7);
        board.populate('X', 8);

        assertEquals(true, board.gameOver());
        assertEquals(true, board.isFull());
    }

    @Test
    public void shouldBeTie() throws Exception
    {
        board.populate('X', 0);
        board.populate('0', 1);
        board.populate('X', 2);
        board.populate('O', 3);
        board.populate('X', 4);
        board.populate('X', 5);
        board.populate('0', 6);
        board.populate('X', 7);
        board.populate('O', 8);

        assertEquals(true, board.gameOver());
        assertEquals(true, board.isTie());
    }

    @Test
    public void shouldBeHorizontalWin() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 6);
        board.populate('X', 1);
        board.populate('O', 7);
        board.populate('X', 2);
        assertEquals(true, board.gameOver());
        assertEquals(true, board.isWon());
        assertEquals('X', board.getWinner());
    }

    @Test
    public void shouldBeVerticalWin() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 3);
        board.populate('O', 4);
        board.populate('X', 6);
        assertEquals(true, board.gameOver());
        assertEquals(true, board.isWon());
    }

    @Test
    public void shouldBeDiagonalWin() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 4);
        board.populate('O', 5);
        board.populate('X', 8);
        assertEquals(true, board.gameOver());
        assertEquals(true, board.isWon());
    }

    @Test
    public void shouldNotAllowDuplication() throws Exception
    {
        board.populate('X', 0);
        try
        {
            board.populate('O', 0);
            fail("Exception expected.");
        }
        catch (Exception e)
        {

        }
    }

    @Test
    public void shouldHaveOneOpenSpace() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 2);
        board.populate('O', 4);
        board.populate('X', 3);
        board.populate('O', 5);
        board.populate('X', 7);
        board.populate('O', 6);

        assertEquals(1, board.openSpaces().size());
        assert(board.openSpaces().contains(8));
    }

    @Test
    public void shouldHaveTwoOpenSpaces() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 2);
        board.populate('O', 4);
        board.populate('X', 3);
        board.populate('O', 5);
        board.populate('X', 7);

        assertEquals(2, board.openSpaces().size());
        assertTrue(board.openSpaces().contains(8));
        assertTrue(board.openSpaces().contains(6));
    }

    @Test
    public void shouldClearBoard() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 2);
        board.populate('O', 4);
        board.populate('X', 3);
        board.populate('O', 5);
        board.populate('X', 7);

        board.clear();

        assertEquals(0, board.charAt(0));
        assertEquals(0, board.charAt(1));
        assertEquals(0, board.charAt(2));
        assertEquals(0, board.charAt(3));
        assertEquals(0, board.charAt(4));
        assertEquals(0, board.charAt(5));
        assertEquals(0, board.charAt(6));
        assertEquals(0, board.charAt(7));
        assertEquals(0, board.charAt(8));
    }

    @Test
    public void shouldIdentifyWinner() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 3);
        board.populate('O', 2);
        board.populate('X', 6);

        Player player = new MockPlayer(board, 'X');
        assertEquals(true, board.isWinner(player));
    }   
}
