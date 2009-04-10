package trptcolin.ui;

import trptcolin.main.Controller;
import trptcolin.main.PlayerFactory;
import trptcolin.main.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 9, 2009
 * Time: 2:30:14 PM
 */
public class ConsoleViewIn3D extends ConsoleView
{
    public ConsoleViewIn3D(Controller controller, PlayerFactory playerFactory, Board board)
    {
        super(controller, playerFactory, board);
    }

    protected String boardToString()
    {
        return  " " + charAt(0) + " | " + charAt(1) + " | " + charAt(2) + " \t" +
                " " + charAt(9) + " | " + charAt(10) + " | " + charAt(11) + " \t" +
                " " + charAt(18) + " | " + charAt(19) + " | " + charAt(20) + " \n" +
                "-----------\t-----------\t-----------\n" +

                " " + charAt(3) + " | " + charAt(4) + " | " + charAt(5) + " \t" +
                " " + charAt(12) + " | " + charAt(13) + " | " + charAt(14) + " \t" +
                " " + charAt(21) + " | " + charAt(22) + " | " + charAt(23) + " \n" +

                "-----------\t-----------\t-----------\n" +
                " " + charAt(6) + " | " + charAt(7) + " | " + charAt(8) + " \t" +
                " " + charAt(15) + " | " + charAt(16) + " | " + charAt(17) + " \t" +
                " " + charAt(24) + " | " + charAt(25) + " | " + charAt(26) + " \n";
    }

    public void getUserMove(char mark)
    {
        System.out.print("Player " + mark + ", make your move (1-9, 10-18, 19-27): ");

        getUserInput();
    }
}
