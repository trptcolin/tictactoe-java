package trptcolin.boards;

import trptcolin.baseGame.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 14, 2009
 * Time: 2:26:30 PM
 */
public class Board4By4 extends Board
{
    private static int[][] winSetsIn4x4 = new int[][]{
            {0,1,2,3}, {4,5,6,7}, {8,9,10,11}, {12,13,14,15},
            {0,4,8,12}, {1,5,9,13}, {2,6,10,14}, {3,7,11,15},
            {0,5,10,15}, {3,6,9,12}
    };

    public Board4By4()
    {
        squares = new char[16];
        winSets = winSetsIn4x4;
    }

    public Board copy() throws Exception
    {
        Board newBoard = new Board4By4();
        for(int position = 0; position < squares.length; position ++)
        {
            newBoard.populateWithoutChecks(squares[position], position);
        }

        return newBoard;
    }

    public boolean isWon()
    {
        char c;

        for(int[] winSet : winSets)
        {
            c = charAt(winSet[0]);
            if(c != 0 && c == charAt(winSet[1]) && c == charAt(winSet[2]) && c == charAt(winSet[3]))
            {
                winner = c;
                return true;
            }
            
        }
        return false;
    }

    public boolean isWon(int position)
    {
        char c;

        for(int[] winSet : winSets)
        {
            if(position == winSet[0] || position == winSet[1] || position == winSet[2] || position == winSet[3])
            {
                c = charAt(winSet[0]);
                if(c != 0 && c == charAt(winSet[1]) && c == charAt(winSet[2]) && c == charAt(winSet[3]))
                {
                    winner = c;
                    return true;
                }
            }
        }
        return false;
    }
}
