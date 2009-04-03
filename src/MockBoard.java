/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 11:22:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockBoard extends Board
{
    public boolean populateCalled = false;
    public char populatedMark = 0;
    public int populatedPosition = -1;

    public boolean gameOverCalled = false;

    public void populate(char mark, int position) throws Exception
    {
        populateCalled = true;
        populatedMark = mark;
        populatedPosition = position;

        super.populate(mark, position);
    }

    public boolean gameOver()
    {
        gameOverCalled = true;
        return super.gameOver();
    }
}
