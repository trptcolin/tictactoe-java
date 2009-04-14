package trptcolin.players;

import trptcolin.main.Board;
import trptcolin.main.Player;

import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 8:32:51 PM
 */
public class ComputerPlayer extends Player
{
    public static Hashtable boardScores = new Hashtable();

    public static int searchDepth = 5;

    private int infinity = 1000000;

    public ComputerPlayer(Board board, char mark)
    {
        super(board, mark);
    }

    public void setSearchDepth(int newDepth){
        searchDepth = newDepth;
    }

    public void makeMove() throws Exception
    {
        board.populate(mark, bestMove(searchDepth));
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
        List<Integer> possibleMoves = possibleMoves(board);

        int bestScoreSoFar = -infinity;
        int currentScore;
        int bestMoveSoFar = -1;
        int move;

        Stack<Integer> moveStack = new Stack<Integer>();
        
        for(int possibleMove: possibleMoves)
        {
            moveStack.push(possibleMove);
            board.populate(mark, possibleMove);

            currentScore = minimax(moveStack, depth, this);

            board.clear(possibleMove);
            moveStack.pop();

            move = possibleMove;

            if(currentScore > bestScoreSoFar)
            {
                bestScoreSoFar = currentScore;
                bestMoveSoFar = move;
            }
        }     

        return bestMoveSoFar;
    }

    private int minimax(Stack<Integer> moveStack, int depth, Player player) throws Exception
    {
        boolean gameOver = board.gameOver;

        if(gameOver || depth == 0)
        {
            if(gameOver && (player.mark == board.winner))
                return infinity;
            else if(gameOver && board.isTie())
                return 0;
            else if(player.getOtherPlayer().mark == board.winner)
                return -infinity;
            else
            {
                return -infinity;
            }
        }
        else
        {
            int bestScore = -infinity - 1;
            Player otherPlayer = player.getOtherPlayer();

            int otherPlayerScore;
            int maxOtherPlayerScore = -infinity - 1;

            List<Integer> possibleOtherPlayerMoves = otherPlayer.possibleMoves(board);

            for(int possibleMove : possibleOtherPlayerMoves)
            {
                moveStack.push(possibleMove);
                board.populate(otherPlayer.mark, possibleMove);

//                if(boardScores.containsKey(board))
//                {
//                    otherPlayerScore = (Integer) boardScores.get(board);
//                    otherPlayerScore = minimax(child, depth - 1, otherPlayer);
//                }
//                else
//                {
    //                    otherPlayerScore = minimax(child, depth - 1, otherPlayer);
                    otherPlayerScore = minimax(moveStack, depth - 1, otherPlayer);

//                    if(player.mark == mark)
//                        boardScores.put(board, -otherPlayerScore);
//                    else
//                        boardScores.put(board, otherPlayerScore);
//
//                }

                board.clear(possibleMove);
                moveStack.pop();

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
