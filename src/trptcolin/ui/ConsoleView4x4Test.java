package trptcolin.ui;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.boards.Board4By4;
import trptcolin.main.Board;
import trptcolin.main.PlayerFactory;
import trptcolin.players.PlayerFactoryImpl;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 14, 2009
 * Time: 2:56:47 PM
 */
public class ConsoleView4x4Test extends Assert
{
    private ConsoleView4x4 consoleView;
    private ControllerImpl controller;
    private Board board;
    private PlayerFactory playerFactory;

    @Before
    public void setup()
    {
        controller = new ControllerImpl(new Board4By4());
        board = (Board4By4)controller.board;
        playerFactory = new PlayerFactoryImpl();
        consoleView = new ConsoleView4x4(controller, playerFactory, board);
    }

    @Test
    public void shouldBeEmpty() throws Exception
    {
        assertEquals(   "   |   |   |   \n" +
                        "---------------\n" +
                        "   |   |   |   \n" +
                        "---------------\n" +
                        "   |   |   |   \n" +
                        "---------------\n" +
                        "   |   |   |   \n",
                        consoleView.boardToString());
    }
}
