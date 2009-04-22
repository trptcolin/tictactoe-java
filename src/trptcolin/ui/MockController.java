package trptcolin.ui;

import trptcolin.main.Board;
import trptcolin.main.Controller;
import trptcolin.main.View;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 11:24:32 AM
 */
public class MockController implements Controller
{
    protected Board board;
    protected View view;

    public boolean requestUserMoveCalled = false;
    public int userPositionGiven = -1;

    public boolean boardPrinted = false;
    public boolean gameTypeChosenCalled;
    public int gameTypeChosenCalledWith;
    public boolean squareChosenCalled;
    public int squareChosenCalledWith;
    public boolean playAgainCalled;
    public boolean playAgainCalledWith;

    public MockController(Board board)
    {
        this.board = board;
    }

    public int requestUserMove(char mark)
    {
        requestUserMoveCalled = true;
        userPositionGiven = 0;
        return userPositionGiven;
    }

    public char charAt(int position)
    {
        return 0;
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

    public void setUI(View view)
    {
    }

    public void gameTypeChosen(int gameType)
    {
        gameTypeChosenCalled = true;
        gameTypeChosenCalledWith = gameType;
    }

    public void squareChosen(int square)
    {
        squareChosenCalled = true;
        squareChosenCalledWith = square;
    }

    public void playAgain(boolean b)
    {
        playAgainCalled = true;
        playAgainCalledWith = b;
    }

    public void setWaitingForInput(boolean b)
    {
    }

    public void setPlayAgain(boolean b)
    {
    }

    public void setGameType(int i)
    {
    }

    public void setLastMove(int move)
    {
    }
}
