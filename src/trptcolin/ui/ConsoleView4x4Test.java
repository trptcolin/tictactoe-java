package trptcolin.ui;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.boards.Board4x4;
import trptcolin.baseGame.Board;
import trptcolin.baseGame.PlayerFactory;
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

    @Before
    public void setup()
    {
        ControllerImpl controller = new ControllerImpl(new Board4x4());
        Board board = controller.board;
        PlayerFactory playerFactory = new PlayerFactoryImpl();
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
