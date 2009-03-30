import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 30, 2009
 * Time: 1:54:00 PM
 */
public class ConsoleView implements GUI
{
    private int[] squares = new int[9];
    private GameController controller;

    public ConsoleView(GameController controller)
    {
        this.controller = controller;
    }

    public void clear()
    {
        
    }

    public void redraw()
    {
        System.out.println(boardToString());
    }

    private char charAt(int position)
    {
        char mark = controller.charAt(position);
        if(mark == 0)
            mark = ' ';
        return mark; 
    }

    protected String boardToString()
    {
        return  " " + charAt(0) + " | " + charAt(1) + " | " + charAt(2) + " \n" +
                "-----------\n" +
                " " + charAt(3) + " | " + charAt(4) + " | " + charAt(5) + " \n" +
                "-----------\n" +
                " " + charAt(6) + " | " + charAt(7) + " | " + charAt(8) + " \n";
    }

    public void buildBoard()
    {
        try
        {
            controller.board.clear();
            for(int i = 0; i < 9; i++)
            {
                controller.board.populate(Integer.toString(i + 1).charAt(0), i);
            }
            controller.updateDisplay();
            controller.board.clear();
        }
        catch(Exception e)
        {
        }
    }

    public void buildGameTypeChoices()
    {
        String choices = "";

        for(PlayerFactory.GameType gameType : PlayerFactory.GameType.values())
        {
            // relies on naming convention _V_ between player types
            String[] playerNames = gameType.toString().split("_V_");
            choices += "\t" + (gameType.ordinal() + 1) +" - " + playerNames[0] + " (X) vs. " +
                    playerNames[1] + " (O)\n";
        }

        System.out.println( "What kind of game would you like to play?\n" +
                choices );

        requestUserInput();
    }

    private void requestUserInput()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String moveInput = "";
        int move = -1;

        try
        {
            moveInput = br.readLine();
        }
        catch(Exception e)
        {
            System.out.print("Invalid input! Try again: ");
            requestUserInput();
        }

    }

    public void addFinalMessage()
    {
    }

    public void stopListening()
    {
    }
}
