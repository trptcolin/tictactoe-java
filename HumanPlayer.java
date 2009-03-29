/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 4:27:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class HumanPlayer extends Player
{
    GameController gameController;

    public HumanPlayer(Board board, char mark, GameController gameController)
    {
        super(board, mark);
        this.gameController = gameController;
    }

    public void makeMove() throws Exception
    {
        int position = gameController.requestUserMove(mark);
        board.populate(mark, position);
    }
}
