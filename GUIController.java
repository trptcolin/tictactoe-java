/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 27, 2009
 * Time: 9:12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class GUIController extends GameController
{
    protected GUI gui;
    
    public int lastMove = -1;
    protected int gameType = -1;
    public boolean playAgain = false;
    public boolean waitingForInput = false;

    public GUIController(Board board)
    {
        super(board);
    }

    protected char charAt(int spot)
    {
        return board.charAt(spot);
    }

    public void setGUI(GUI gui)
    {
        this.gui = gui;
    }
    public void updateDisplay()
    {
        gui.redraw();
    }

    public void printInitialBoard()
    {
        gui.clear();
        gui.buildBoard();
        updateDisplay();
    }

    public void printFinalBoard()
    {
        gui.stopListening();
        gui.addFinalMessage();
        updateDisplay();
    }

    public String boardToString()
    {
        return "";
    }

    public int requestUserMove(char mark)
    {
        return requestUserInput();
    }

    public int requestGameType()
    {
        gui.clear();
        gui.buildGameTypeChoices();

        waitingForInput = true;
        while(waitingForInput)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("gameType = " + gameType);
        return gameType;
    }
    
    protected int requestUserInput()
    {
        waitingForInput = true;

        boolean gameOver = board.gameOver();

        while(waitingForInput && !gameOver)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return lastMove;
    }

    public boolean shouldPlayAgain()
    {
        waitingForInput = true;
        while(waitingForInput)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        if(playAgain)
        {
            try
            {
                board.clear();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return playAgain;
    }

    public void squareChosen(int square)
    {
        waitingForInput = false;
        lastMove = square;
    }


    public void playAgain(boolean b)
    {
        waitingForInput = false;
        playAgain = b;
    }

    public void gameTypeChosen(int gameType)
    {
        waitingForInput = false;
        this.gameType = gameType;
    }

}

