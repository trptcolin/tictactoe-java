package trptcolin.ui;

import trptcolin.main.Controller;
import trptcolin.main.PlayerFactory;
import trptcolin.main.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 14, 2009
 * Time: 2:54:22 PM
 */
public class ConsoleView4x4 extends ConsoleView
{
    public ConsoleView4x4(Controller controller, PlayerFactory playerFactory, Board board)
    {
        super(controller, playerFactory, board);
    }

    protected String boardToString()
    {
        return  " " + charAt(0) + " | " + charAt(1) + " | " + charAt(2) + " | " + charAt(3) + " \n" +
                "---------------\n" +
                " " + charAt(4) + " | " + charAt(5) + " | " + charAt(6) + " | " + charAt(7) + " \n" +
                "---------------\n" +
                " " + charAt(8) + " | " + charAt(9) + " | " + charAt(10) + " | " + charAt(11) + " \n" +
                "---------------\n" +
                " " + charAt(12) + " | " + charAt(13) + " | " + charAt(14) + " | " + charAt(15) + " \n";
    }

    public void getUserMove(char mark)
    {
        System.out.print("Player " + mark + ", make your move (1-16): ");

        getUserInput();
    }
}
