import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 3:39:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GameController
{
    protected Board board;

    public GameController(Board board)
    {
        this.board = board;
    }

    protected char charAt(int position)
    {
        char mark = board.charAt(position);
        if(mark == 0)
            mark = ' ';
        return mark;
    }

    protected abstract String boardToString();

    public abstract void updateDisplay();
    public abstract void printInitialBoard();
    public abstract void printFinalBoard();

    public abstract int requestUserMove(char mark);
    public abstract int requestGameType();
//    protected abstract int requestUserInput();

    public abstract boolean shouldPlayAgain();

    public abstract void setGUI(GUI gui);
}
