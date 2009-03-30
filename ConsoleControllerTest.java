import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 3:40:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsoleControllerTest extends Assert
{
    private ConsoleController gameDisplay;
    private Board board;

    @Before
    public void setup()
    {
        board = new Board();
        gameDisplay = new ConsoleController(board);
    }

    @Test
    public void shouldRequestUserMove()
    {
        InputStream realStdin = System.in;
        PrintStream realStdout = System.out;

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        
        int move = gameDisplay.requestUserMove('X');

        System.setIn(realStdin);
        System.setOut(realStdout);
        
        assertEquals(0, move);
    }

    @Test
    public void shouldDetermineGameType() throws Exception
    {
        InputStream realStdin = System.in;
        PrintStream realStdout = System.out;

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        int gameType = gameDisplay.requestGameType();

        System.setIn(realStdin);
        System.setOut(realStdout);

        assertEquals(1, gameType);
    }
}
