package trptcolin.players;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.boards.BoardIn2D;
import trptcolin.main.Board;
import trptcolin.main.Controller;
import trptcolin.main.Player;
import trptcolin.main.PlayerFactory;
import trptcolin.ui.MockController;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 26, 2009
 * Time: 1:22:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerFactoryImplTest extends Assert
{
    private Board board;
    private Controller controller;
    private PlayerFactory playerFactory;

    @Before
    public void setup()
    {
        board = new BoardIn2D();
        controller = new MockController(board);
        playerFactory = new PlayerFactoryImpl();
    }

    @Test
    public void shouldCreatePlayersForComputerVComputer() throws Exception
    {
        Player playerX = playerFactory.createPlayer(board, 'X', controller, 1);
        Player playerO = playerFactory.createPlayer(board, 'O', controller, 1);

        assertTrue(playerX instanceof ComputerPlayer);
        assertTrue(playerO instanceof ComputerPlayer);
    }

    @Test
    public void shouldCreatePlayersForComputerVHuman() throws Exception
    {
        Player playerX = playerFactory.createPlayer(board, 'X', controller, 2);
        Player playerO = playerFactory.createPlayer(board, 'O', controller, 2);

        assertTrue(playerX instanceof ComputerPlayer);
        assertTrue(playerO instanceof HumanPlayer);
    }

    @Test
    public void shouldCreatePlayersForHumanVComputer() throws Exception
    {
        Player playerX = playerFactory.createPlayer(board, 'X', controller, 3);
        Player playerO = playerFactory.createPlayer(board, 'O', controller, 3);

        assertTrue(playerX instanceof HumanPlayer);
        assertTrue(playerO instanceof ComputerPlayer);
    }

    @Test
    public void shouldCreatePlayersForHumanVHuman() throws Exception
    {
        Player playerX = playerFactory.createPlayer(board, 'X', controller, 4);
        Player playerO = playerFactory.createPlayer(board, 'O', controller, 4);

        assertTrue(playerX instanceof HumanPlayer);
        assertTrue(playerO instanceof HumanPlayer);
    }
}
