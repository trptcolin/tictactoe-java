package trptcolin.baseGame;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 11:20:03 AM
 */
public class MockPlayer extends Player
{
    public boolean makeMoveCalled = false;

    public MockPlayer(Board board, char mark)
    {
        super(board, mark);
    }


    public void makeMove() throws Exception
    {
        makeMoveCalled = true;
        
        for(int position = 0; position < 9; position++)
        {
            if(!board.isOccupied(position))
            {
                board.populate(mark, position);
                return;
            }
        }
    }
}
