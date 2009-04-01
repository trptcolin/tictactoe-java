import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 26, 2009
 * Time: 1:22:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerFactoryTest extends Assert
{
    private Board board;
    private Controller controller;

    @Before
    public void setup()
    {
        board = new Board();
        controller = new MockController(board);
    }

    @Test
    public void shouldCreatePlayersForComputerVComputer() throws Exception
    {
        Player playerX = PlayerFactory.createPlayer(board, 'X', controller, 1);
        Player playerO = PlayerFactory.createPlayer(board, 'O', controller, 1);

        assertTrue(playerX instanceof ComputerPlayer);
        assertTrue(playerO instanceof ComputerPlayer);
    }

    @Test
    public void shouldCreatePlayersForComputerVHuman() throws Exception
    {
        Player playerX = PlayerFactory.createPlayer(board, 'X', controller, 2);
        Player playerO = PlayerFactory.createPlayer(board, 'O', controller, 2);

        assertTrue(playerX instanceof ComputerPlayer);
        assertTrue(playerO instanceof HumanPlayer);
    }

    @Test
    public void shouldCreatePlayersForHumanVComputer() throws Exception
    {
        Player playerX = PlayerFactory.createPlayer(board, 'X', controller, 3);
        Player playerO = PlayerFactory.createPlayer(board, 'O', controller, 3);

        assertTrue(playerX instanceof HumanPlayer);
        assertTrue(playerO instanceof ComputerPlayer);
    }

    @Test
    public void shouldCreatePlayersForHumanVHuman() throws Exception
    {
        Player playerX = PlayerFactory.createPlayer(board, 'X', controller, 4);
        Player playerO = PlayerFactory.createPlayer(board, 'O', controller, 4);

        assertTrue(playerX instanceof HumanPlayer);
        assertTrue(playerO instanceof HumanPlayer);
    }
}
