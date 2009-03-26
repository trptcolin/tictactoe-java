/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 4:27:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class HumanPlayer extends Player
{
    GameDisplay gameDisplay;

    public HumanPlayer(Board board, char mark, GameDisplay gameDisplay)
    {
        super(board, mark);
        this.gameDisplay = gameDisplay;
    }

    public void makeMove() throws Exception
    {
        int position = gameDisplay.requestUserMove(mark);
        board.populate(mark, position);
    }
}
