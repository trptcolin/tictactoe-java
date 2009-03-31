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

    public void makeMove()
    {
        int position;
        boolean legalMove = false;
        while(!legalMove)
        {
            position = gameController.requestUserMove(mark);
            
            if(board.isOccupied(position))
            {
                gameController.updateDisplay();
            }
            else
            {
                try
                {
                    board.populate(mark, position);
                    legalMove = true;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
