package trptcolin.main;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.players.MockPlayer;
import trptcolin.ui.MockController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 3:16:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameTest extends Assert
{
    private MockBoard board;
    private MockController gameDisplay;
    private MockPlayer player1;
    private MockPlayer player2;
    private Game game;

    @Before
    public void setup()
    {
        board = new MockBoard();
        gameDisplay = new MockController(board);
        player1 = new MockPlayer(board, 'X');
        player2 = new MockPlayer(board, 'O');
        game = new Game(board, gameDisplay, player1, player2);
    }

    @Test
    public void shouldPlayGame() throws Exception
    {
        InputStream realStdin = System.in;
        PrintStream realStdout = System.out;

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        game.play();

        System.setIn(realStdin);
        System.setOut(realStdout);

        
        assertEquals(true, player1.makeMoveCalled);
        assertEquals(true, player2.makeMoveCalled);
        assertEquals(true, board.gameOverCalled);

        assertEquals(true, gameDisplay.boardPrinted);
        assertEquals(true, board.gameOver());
    }
}
