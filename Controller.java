/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 27, 2009
 * Time: 9:12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class Controller extends GameController
{
    protected View view;
    
    public int lastMove = -1;
    protected int gameType = -1;
    public boolean playAgain = false;
    public boolean waitingForInput = false;

    public Controller(Board board)
    {
        super(board);
    }

    protected char charAt(int spot)
    {
        return board.charAt(spot);
    }

    public void setGUI(View view)
    {
        this.view = view;
    }
    public void updateDisplay()
    {
        view.redraw();
    }

    public void printInitialBoard()
    {
        view.clear();
        view.buildBoard();
        updateDisplay();
    }

    public void printFinalBoard()
    {
        view.stopListening();
        view.addFinalMessage();
        updateDisplay();
    }

    public String boardToString()
    {
        return "";
    }

    public int requestUserMove(char mark)
    {
        return requestUserInput(mark);
    }

    public int requestGameType()
    {
        view.clear();
        view.buildGameTypeChoices();

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

        return gameType;
    }
    
    protected int requestUserInput(char mark)
    {
        waitingForInput = true;

        boolean gameOver = board.gameOver();

        view.getUserMove(mark);
        
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

