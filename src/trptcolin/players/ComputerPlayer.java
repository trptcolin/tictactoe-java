package trptcolin.players;

import trptcolin.main.Board;
import trptcolin.main.Player;

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

    public static long bestMoveTime = 0;

    private int infinity = 1000000;
private long gameOverCallLength = 0;
private long winnerCallLength = 0;
private long tieCallLength = 0;
private long buildingPossibleBoardsCallLength = 0;

    public ComputerPlayer(Board board, char mark)
    {
        super(board, mark);
    }

    public void makeMove() throws Exception
    {
        board.populate(mark, bestMove(4));
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
long now = System.currentTimeMillis();

        //TODO MDM - Why not moves?
        List<Board> possibleResultingBoards = possibleResultingBoards(board);

System.out.println("possibleResultingBoardsTime = " + (System.currentTimeMillis() - now));

        int bestScoreSoFar = -infinity;
        int currentScore;
        int bestMoveSoFar = -1;
        int move;

        for(Board resultBoard : possibleResultingBoards)
        {

long minimaxStart = System.currentTimeMillis();
gameOverCallLength = 0;
winnerCallLength = 0;            
buildingPossibleBoardsCallLength = 0;
            
            currentScore = minimax(resultBoard, depth, this);

long minimaxDuration = System.currentTimeMillis() - minimaxStart;
System.out.println("minimaxDuration = " + minimaxDuration);
System.out.println("gameOverCallLength = " + gameOverCallLength);
System.out.println("tieCallLength = " + gameOverCallLength);

System.out.println("winnerCallLength = " + winnerCallLength);
System.out.println("buildingPossibleBoardsCallLength = " + buildingPossibleBoardsCallLength);
            move = getMove(board, resultBoard);

            if(currentScore > bestScoreSoFar)
            {
                bestScoreSoFar = currentScore;
                bestMoveSoFar = move;
            }
        }

bestMoveTime = System.currentTimeMillis() - now;        

        return bestMoveSoFar;
    }


    private int minimax(Board board, int depth, Player player) throws Exception
    {

long beforeGameOver = System.currentTimeMillis();

        boolean gameOver = board.gameOver();

gameOverCallLength += (System.currentTimeMillis() - beforeGameOver);

long beforeBoardIsWinner = System.currentTimeMillis();
        boolean isWinner = gameOver && (player.mark == board.winner);
winnerCallLength += (System.currentTimeMillis() - beforeBoardIsWinner);

long beforeTie = System.currentTimeMillis();
        boolean isTie = gameOver && board.isTie();
tieCallLength += (System.currentTimeMillis() - beforeTie);

        if(gameOver || depth == 0)
        {
            if(isWinner)
                return infinity;
            else if(isTie)
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

long beforeBuildingPossibleBoards = System.currentTimeMillis();
            List<Board> possibleBoards = otherPlayer.possibleResultingBoards(board);
buildingPossibleBoardsCallLength += (System.currentTimeMillis() - beforeBuildingPossibleBoards);


            for(Board child : possibleBoards)
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
