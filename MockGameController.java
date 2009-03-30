/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 11:24:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockGameController extends GameController
{
    public boolean requestUserMoveCalled = false;
    public int userPositionGiven = -1;

    public boolean boardPrinted = false;

    public MockGameController(Board board)
    {
        super(board);
    }

    public int requestUserMove(char mark)
    {
        requestUserMoveCalled = true;
        userPositionGiven = 0;
        return userPositionGiven;
    }

    public void updateDisplay()
    {
        boardPrinted = true;
    }
      public void printInitialBoard()
    {

    }

    public void printFinalBoard()
    {

    }

    public String boardToString()
    {
        return "";
    }

    public int requestGameType()
    {
        return -1;
    }
    
    protected int requestUserInput()
    {
        return -1;
    }

    public boolean shouldPlayAgain()
    {
        return false;
    }

    public void setGUI(GUI gui)
    {
    }
}
