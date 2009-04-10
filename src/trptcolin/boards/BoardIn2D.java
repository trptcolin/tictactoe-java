package trptcolin.boards;

import trptcolin.main.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 9:47:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class BoardIn2D extends Board
{
    private static int[][] winSetsIn2D = new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6} };

    public BoardIn2D()
    {
        squares = new char[9];
        winner = 0;
        winSets = winSetsIn2D;
    }

    private int flattenRowCol(int row, int col)
    {
        return (3 * row) + col;
    }


    public boolean isOccupied(int row, int col)
    {
        return isOccupied(flattenRowCol(row, col));
    }


    public void populate(char mark, int row, int col) throws Exception
    {
        populate(mark, flattenRowCol(row, col));
    }

    public Board copy() throws Exception
    {
        Board newBoard = new BoardIn2D();
        for(int position = 0; position < squares.length; position++)
            newBoard.populate(squares[position], position);
        
        return newBoard;
    }

   
}
