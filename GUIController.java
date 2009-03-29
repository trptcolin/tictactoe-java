/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 27, 2009
 * Time: 9:12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class GUIController extends GameController
{
    private GUI gui;
    private boolean waitingForInput;
    private int lastMove = -1;
    private char activeMark = 0;

    public GUIController(Board board)
    {
        super(board);
        gui = new GUI(this);
    }

    public void updateDisplay()
    {
        gui.redraw();
    }

    public void printInitialBoard()
    {
        updateDisplay();
    }

    public void printFinalBoard()
    {
        gui.stopListening();
//        gui.addFinalMessage();
        updateDisplay();
    }

    public String boardToString()
    {
        return "";
    }

    public int requestUserMove(char mark)
    {
        gui.setWaitingForInput(true);
        activeMark = mark;

        boolean gameOver = board.gameOver();

        while(gui.isWaitingForInput() && !gameOver)
        {
            try
            {
//                Thread.sleep(100);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return lastMove;
    }
    public int requestGameType()
    {
        return 2;
    }
    protected int requestUserInput()
    {
        return -1;
    }

    public boolean isWaitingForInput()
    {
        return waitingForInput;
    }

    public int lastMove()
    {
        return lastMove;
    }

    public void setLastMove(int lastSquare)
    {
        this.lastMove = lastSquare;
    }

    public char getActiveMark()
    {
        return activeMark;
    }

    public boolean shouldPlayAgain()
    {
        return false;
    }
}

