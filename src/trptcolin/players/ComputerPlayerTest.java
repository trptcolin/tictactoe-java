package trptcolin.players;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.boards.Board3x3;
import trptcolin.baseGame.Board;
import trptcolin.baseGame.Player;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 8:33:05 PM
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
        board = new Board3x3();
        mark = 'X';
        computerPlayer = new ComputerPlayer(board, mark);
        computerPlayer.setSearchDepth(4);
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
        
        Assert.assertEquals('X', board.charAt(2));
    }

    @Test
    public void shouldWinVertically() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 3);
        board.populate('O', 4);

        computerPlayer.makeMove();

        Assert.assertEquals('X', board.charAt(6));
    }

    @Test
    public void shouldWinDiagonally() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 4);
        board.populate('O', 2);
        
        computerPlayer.makeMove();

        Assert.assertEquals('X', board.charAt(8));
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

        Assert.assertEquals('O', board.charAt(7));
    }

    @Test
    public void shouldBlockDiagonalWin() throws Exception
    {
        board.populate('X', 2);
        board.populate('O', 0);
        board.populate('X', 4);

        otherPlayer.makeMove();

        Assert.assertEquals('O', board.charAt(6));
    }

    @Test
    public void shouldStartInMiddleOrCorner() throws Exception
    {
        computerPlayer.makeMove();

        assertTrue('X' == board.charAt(4) || 'X' == board.charAt(0));
    }

    @Test
    public void shouldSetSearchDepth() throws Exception
    {
        int depth = ComputerPlayer.searchDepth;
        computerPlayer.setSearchDepth(depth + 1);
        assertEquals(depth + 1, ComputerPlayer.searchDepth);
    }

    @Test
    public void shouldClearCache() throws Exception
    {
        ComputerPlayer.boardScores.clear();
        assertEquals(0, ComputerPlayer.boardScores.size());
    }

    @Test
    public void shouldCacheValueWhenNoKeyExists() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 2);
        board.populate('O', 3);
        board.populate('X', 4);
        board.populate('O', 5);

        board.populate('X', 7);
        board.populate('O', 6);

        Stack<Integer> moves = new Stack<Integer>();

        ComputerPlayer.boardScores.clear();
        
        // looking only one move deep
        computerPlayer.getValueToOtherPlayer(moves, computerPlayer, 8, 1);

        assertEquals(1, ComputerPlayer.boardScores.size());
    }

    @Test
    public void shouldGetCachedValueWhenKeyExists() throws Exception
    {
        board.populate('X', 0);
        board.populate('O', 1);
        board.populate('X', 2);
        board.populate('O', 3);
        board.populate('X', 4);
        board.populate('O', 5);

        board.populate('X', 7);


        int moveToCheck = 6;
        // since we're checking for the other player, it gets
        board.populate('O', moveToCheck);


        String boardString = board.toString();
        ComputerPlayer.boardScores.put(boardString, 12345);
        
        board.clear(moveToCheck);
        Stack<Integer> moves = new Stack<Integer>();

        assertEquals(true, ComputerPlayer.boardScores.containsKey(boardString));
        assertEquals(12345, (int)ComputerPlayer.boardScores.get(boardString));

        int value = computerPlayer.getValueToOtherPlayer(moves, computerPlayer, moveToCheck, 1);

        // since boardScores contained the score for player X, and value holds the score for player O
        assertEquals(-12345, value);
    }
}