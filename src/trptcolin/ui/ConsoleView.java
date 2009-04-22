package trptcolin.ui;

import trptcolin.baseGame.PlayerFactory;
import trptcolin.baseGame.View;
import trptcolin.baseGame.Board;
import trptcolin.baseGame.Controller;

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
    private Controller controller;
    private Board board;
    private PlayerFactory playerFactory;

    public ConsoleView(Controller controller, PlayerFactory playerFactory, Board board)
    {
        this.controller = controller;
        this.board = board;
        this.playerFactory = playerFactory;
    }

    public void clear()
    {
        
    }

    public void redraw()
    {
        System.out.println(boardToString());
        if(board.gameOver())
        {
            System.out.println("Press any key to play again (Ctrl-C to quit)");
        }
    }

    protected char charAt(int position)
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

        for(int i = 0; i < PlayerFactory.numberOfGameTypes; i++)
        {
            String gameTypeString = playerFactory.gameTypeToString(i);
            choices += "\t" + (i + 1) + " - " + gameTypeString + "\n";
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

    private void getUserPlayAgain()
    {
        Thread thread = new Thread() {
            public void run()
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int move = -1;
                while(move == -1)
                {
                    try
                    {
                        if(br.readLine() != null)
                        {
                            move = 0;
                            controller.setWaitingForInput(false);
                            controller.setPlayAgain(true);
                            return;
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        };

        thread.start();
    }
    protected void getUserInput()
    {
        Thread thread = new Thread() {
            public void run()
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String moveInput;
                int move = -1;
                while(move == -1)
                {
                    try
                    {
                        moveInput = br.readLine();
                        move = Integer.parseInt(moveInput);
                        if(!board.isOccupied(move - 1))
                        {
                            controller.setWaitingForInput(false);
                            controller.setGameType(move);
                            controller.setLastMove(move - 1);
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
        };
        thread.start();
    }

    public void addFinalMessage()
    {
        String endMessage = "";
        if(board.isWon())
            endMessage += "Player " + board.getWinner() + " was the winner!";
        else
            endMessage +="It was a tie!";

        endMessage += "\nFinal board position below\n===\n";

        System.out.println(endMessage);
        getUserPlayAgain();
    }

    public void stopListening()
    {
    }
}
