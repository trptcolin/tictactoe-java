package trptcolin.players;

import trptcolin.baseGame.Board;
import trptcolin.baseGame.Player;

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
    public static Hashtable<String, Integer> boardScores = new Hashtable<String, Integer>();

    public static int searchDepth = 8;

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

    private int bestMove(int depth) throws Exception
    {
        List<Integer> possibleMoves = possibleMoves(board);

        int bestScoreSoFar = -infinity;
        int currentScore;
        int bestMoveSoFar = -1;

        Stack<Integer> moveStack = new Stack<Integer>();
        
        for(int possibleMove: possibleMoves)
        {
            makeScratchMove(moveStack, possibleMove, ComputerPlayer.this);
            currentScore = minimax(moveStack, depth, this);            
            undoScratchMove(moveStack, possibleMove);

            if(currentScore > bestScoreSoFar)
            {
                bestScoreSoFar = currentScore;
                bestMoveSoFar = possibleMove;
            }
        }

        return bestMoveSoFar;
    }
    
    private void makeScratchMove(Stack<Integer> moveStack, int possibleMove, Player otherPlayer)
            throws Exception
    {
        moveStack.push(possibleMove);
        board.populate(otherPlayer.mark, possibleMove);
    }

    private void undoScratchMove(Stack<Integer> moveStack, int possibleMove)
            throws Exception
    {
        board.clear(possibleMove);
        moveStack.pop();
    }

    private int minimax(Stack<Integer> moveStack, int depth, Player player) throws Exception
    {
        if(board.gameOver || depth == 0)
        {
            return finalScore(player);
        }
        else
        {
            int bestScore = -infinity - 1;
            bestScore = calculateBestScore(moveStack, depth, player, bestScore);
            
            if(bestScore > 0)
                // a win should take the fewest possible moves
                return bestScore - depth;
            else
                // a loss or tie should take the most possible moves (more chances for opponent mistake)
                return bestScore + depth;
        }
    }
    
    private int finalScore(Player player)
    {
        if(board.gameOver && (player.mark == board.winner))
            return infinity;
        else if(board.gameOver && board.isTie())
            return 0;
        else if(player.getOtherPlayer().mark == board.winner)
            return -infinity;
        else
        {
            return -infinity;
        }
    }

    private int calculateBestScore(Stack<Integer> moveStack, int depth, Player player, int bestScore)
            throws Exception
    {
        Player otherPlayer = player.getOtherPlayer();

        int otherPlayerScore;
        int maxOtherPlayerScore = -infinity - 1;

        List<Integer> possibleOtherPlayerMoves = otherPlayer.possibleMoves(board);

        for(int possibleMove : possibleOtherPlayerMoves)
        {
            otherPlayerScore = getValueToOtherPlayer(moveStack, player, possibleMove, depth);
            maxOtherPlayerScore = Math.max(otherPlayerScore, maxOtherPlayerScore);
            bestScore = -maxOtherPlayerScore;
        }
        
        return bestScore;
    }

    public int getValueToOtherPlayer(Stack<Integer> moveStack, Player player, int possibleMove, int depth)
            throws Exception
    {
        int otherPlayerScore;
        Player otherPlayer = player.getOtherPlayer();

        makeScratchMove(moveStack, possibleMove, otherPlayer);

        String boardString = board.toString();

        if(boardScores.containsKey(boardString))
            otherPlayerScore = getCachedScore(player, boardString);
        else
        {
            otherPlayerScore = minimax(moveStack, depth - 1, otherPlayer);
            writeScoreToCache(player, boardString, otherPlayerScore);
        }

        undoScratchMove(moveStack, possibleMove);
        
        return otherPlayerScore;
    }

    private void writeScoreToCache(Player player, String boardString, int otherPlayerScore)
    {
        if(player.mark == 'X')
            boardScores.put(boardString, -otherPlayerScore);
        else
            boardScores.put(boardString, otherPlayerScore);
    }

    private int getCachedScore(Player player, String boardString)
    {
        int otherPlayerScore = boardScores.get(boardString);
        
        if(player.mark == 'X')
            otherPlayerScore *= -1;
        
        return otherPlayerScore;
    }

}
