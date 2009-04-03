import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 8:32:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComputerPlayer extends Player
{
    private int infinity = 1000000;

    public ComputerPlayer(Board board, char mark)
    {
        super(board, mark);
    }

    public void makeMove() throws Exception
    {
        board.populate(mark, bestMove(8));
    }


    private int getMove(Board initialBoard, Board resultBoard)
    {
        int bestMove = -1;
        for(int position : initialBoard.openSpaces())
        {
            if(resultBoard.isOccupied(position))
            {
                bestMove = position;
            }
        }
        return bestMove;
    }
    
    private int bestMove(int depth) throws Exception
    {
        //TODO MDM - Why not moves?
        List<Board> possibleResultingBoards = possibleResultingBoards(board);

        int bestScoreSoFar = -infinity;
        int currentScore;
        int bestMoveSoFar = -1;
        int move;

        for(Board resultBoard : possibleResultingBoards)
        {
            currentScore = minimax(resultBoard, depth, this);
            
            move = getMove(board, resultBoard);

            if(currentScore > bestScoreSoFar)
            {
                bestScoreSoFar = currentScore;
                bestMoveSoFar = move;
            }
        }

        return bestMoveSoFar;
    }


    private int minimax(Board board, int depth, Player player) throws Exception
    {
        if(board.gameOver() || depth == 0)
        {
            if(board.isWinner(player))
                return infinity;
            else if(board.isTie())
                return 0;
            else
                return -infinity;
        }
        else
        {
            int bestScore = -infinity - 1;
            Player otherPlayer = player.getOtherPlayer();

            int otherPlayerScore;
            int maxOtherPlayerScore = -infinity - 1;
            
            for(Board child : otherPlayer.possibleResultingBoards(board))
            {
                otherPlayerScore = minimax(child, depth - 1, otherPlayer);
                maxOtherPlayerScore = Math.max(otherPlayerScore, maxOtherPlayerScore);
                bestScore = -maxOtherPlayerScore;
            }
            
            if(bestScore > 0)
                // a win should take the fewest possible moves
                return bestScore - depth;
            else
                // a loss or tie should take the most possible moves (more chances for opponent mistake)
                return bestScore + depth;
        }
    }

}
