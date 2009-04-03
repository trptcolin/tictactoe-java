package trptcolin.main;

import trptcolin.players.Player;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 9:47:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class Board
{
    private char[] squares = new char[9];
    protected char winner = 0;
    private final int[][] winSets = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6} };


    public Board()
    {
    }

    public char getWinner()
    {
        if(isWon())
            return winner;
        else
            return 0;
    }

    public boolean isWinner(Player player)
    {
        return player.mark == getWinner();
    }

    private int flattenRowCol(int row, int col)
    {
        return (3 * row) + col;
    }

    public boolean isOccupied(int position)
    {
        return squares[position] != 0;
    }

    public boolean isOccupied(int row, int col)
    {
        return isOccupied(flattenRowCol(row, col));
    }

    public char charAt(int position)
    {
        return squares[position];
    }

    public void populate(char mark, int position) throws Exception
    {
        if(isOccupied(position))
        {
            throw new Exception();
        }
        squares[position] = mark;
    }

    public void populate(char mark, int row, int col) throws Exception
    {
        populate(mark, flattenRowCol(row, col));
    }

    public boolean gameOver()
    {
        return isFull() || isWon();
    }

    public boolean isFull()
    {
        for (char mark : squares)
            if (mark == 0)
                return false;
        return true;
    }

    public boolean isWon()
    {
        int consecutiveMarks = 0;
        char currentMark = 0;
        char previousMark = 0;

        for (int[] winSet : winSets)
        {
            for (int position : winSet)
            {
                previousMark = currentMark;
                currentMark = squares[position];
                if (currentMark == 0 || (consecutiveMarks > 0 && currentMark != previousMark))
                {
                    consecutiveMarks = 0;
                    break;
                }
                else
                {
                    consecutiveMarks++;
                    if (consecutiveMarks > 2)
                    {
                        winner = currentMark;
                        return true;
                    }
                    continue;
                }
            }
        }
        return false;
    }

    public boolean sameSquares(Board otherBoard)
    {
        for(int i=0; i<9; i++)
            if(otherBoard.squares[i] != this.squares[i])
                return false;


        return true;
    }

    public Board copy() throws Exception
    {
        Board newBoard = new Board();
        for(int position = 0; position < 9; position++)
            newBoard.populate(squares[position], position);
        
        return newBoard;
    }

    public List<Integer> openSpaces()
    {
        List<Integer> openSpaces = new ArrayList<Integer>();
        for(int i = 0; i < 9; i++)
        {
            if(squares[i] == 0)
            {
                openSpaces.add(i);
            }
        }

        return openSpaces;
    }

    public void clear() throws Exception
    {
        for(int i = 0; i < 9; i++)
            squares[i] = '\0';
    }

    public boolean empty()
    {
        for(int i = 0; i < 9; i++)
        {
            if(isOccupied(i))
                return false;
        }
        return true;
    }

    public boolean isTie()
    {
        return isFull() && !isWon();
    }
}
