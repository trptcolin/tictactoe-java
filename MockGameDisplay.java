/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 11:24:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockGameDisplay extends GameDisplay
{
    public boolean requestUserMoveCalled = false;
    public int userPositionGiven = -1;

    public boolean boardPrinted = false;

    public MockGameDisplay(Board board)
    {
        super(board);
    }

    public int requestUserMove(char mark)
    {
        requestUserMoveCalled = true;
        userPositionGiven = 0;
        return userPositionGiven;
    }

    public void print()
    {
        boardPrinted = true;
    }
}
