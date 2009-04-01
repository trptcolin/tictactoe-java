import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 30, 2009
 * Time: 1:54:00 PM
 */
public class ConsoleView implements View
{
    private ConcreteController controller;

    public ConsoleView(Controller controller)
    {
        this.controller = (ConcreteController)controller;
    }

    public void clear()
    {
        
    }

    public void redraw()
    {
        System.out.println(boardToString());
        if(controller.board.gameOver())
        {
            System.out.println("Press any key to play again.");
        }
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

        getUserInput();
    }

    public void getUserMove(char mark)
    {
        System.out.print("Player " + mark + ", make your move (1-9): ");

        getUserInput();
    }

    public void getUserInput(final boolean playAgain)
    {
        Thread thread = new Thread() {
            public void run()
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String moveInput = "";
                int move = -1;
                while(move == -1)
                {
                    if(playAgain)
                    {
                        try
                        {
                            if(br.readLine() != null)
                            {
                                move = 0;
                                controller.waitingForInput = false;
                                controller.playAgain = true;
                                return;
                            }
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try
                        {
                            moveInput = br.readLine();
                            move = Integer.parseInt(moveInput);
                            if(!controller.board.isOccupied(move - 1))
                            {
                                controller.waitingForInput = false;
                                controller.gameType = move;
                                controller.lastMove = move - 1;
                            }
                            else
                            {
                                System.out.print("Invalid input! Try again: ");
                                move = -1;
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.print("Invalid input! Try again: ");
                        }
                    }
                }
            }
        };

        thread.start();
    }
    public void getUserInput()
    {
        getUserInput(false);
    }

    public void addFinalMessage()
    {
        String endMessage = "";
        if(controller.board.isWon())
            endMessage += "Player " + controller.board.getWinner() + " was the winner!";
        else
            endMessage +="It was a tie!";

        endMessage += "\nFinal board position below\n===\n";

        System.out.println(endMessage);
        getUserInput(true);
    }

    public void stopListening()
    {
    }
}
