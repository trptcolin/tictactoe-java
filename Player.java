import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 12:31:07 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Player
{
    protected Board board;
    protected char mark;
    protected Player otherPlayer;

    public Player(Board board, char mark)
    {
        this.board = board;
        this.mark = mark;
    }

    abstract public void makeMove() throws Exception;

    public void setOtherPlayer(Player other)
    {
        this.otherPlayer = other;
        other.otherPlayer = this; 
    }

    public Player getOtherPlayer()
    {
        return otherPlayer;
    }

    public static char otherPlayerMark(char mark)
    {
       return mark == 'X' ? 'O' : 'X';
    }

//    public int[] possibleMoves()
//    {
//        int[] possibleMoves = new int[8];
//        return possibleMoves;
//    }
//

    public List<Board> possibleResultingBoards(Board board) throws Exception
    {
        List<Board> resultingBoards = new ArrayList<Board>();

        for(int possibleMove : board.openSpaces())
        {
            Board newBoard = board.copy();
            newBoard.populate(mark, possibleMove);
            resultingBoards.add(newBoard);
        }
        
        return resultingBoards;
    }
}
