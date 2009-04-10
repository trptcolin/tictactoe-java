package trptcolin.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 12:31:07 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Player
{
    public Board board;
    public char mark;
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

    public List<Board> possibleResultingBoards(Board board) throws Exception
    {
        List<Board> resultingBoards = new ArrayList<Board>();
//
//        if(board.gameOver())
//        {
//            return resultingBoards;
//        }
        
        for(int possibleMove : board.openSpaces())
        {
            Board newBoard = board.copy();
            newBoard.populate(mark, possibleMove);
            resultingBoards.add(newBoard);
        }
        
        return resultingBoards;
    }
}
