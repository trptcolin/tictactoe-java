package trptcolin.baseGame;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 8, 2009
 * Time: 11:10:30 AM
 */
public abstract class Board
{
    protected char[] squares;

    public char winner = 0;
    public boolean gameOver = false;

    public static int[][] winSets;

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


    public boolean isOccupied(int position)
    {
        return charAt(position) != 0;
    }
    
    public int numberOfSquares()
    {
        return squares.length;
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
        
        if(isWon(position))
        {
            gameOver = true;
            winner = mark;
        }
        else
        {
            gameOver = isFull();
            winner = 0;
        }
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

    public boolean isWon(int position)
    {        
        for(int[] winSet : winSets)
        {
            if(position == winSet[0] || position == winSet[1] || position == winSet[2])
            {
                if (winsWithSet(winSet)) return true;
            }
        }
        return false;
    }

    public boolean isWon()
    {
        for(int[] winSet : winSets)
        {
            if (winsWithSet(winSet)) return true;
        }
        return false;
    }

    private boolean winsWithSet(int[] winSet)
    {
        char c;
        c = charAt(winSet[0]);
        if(c != 0 && c == charAt(winSet[1]) && c == charAt(winSet[2]))
        {
            winner = c;
            return true;
        }
        return false;
    }

    public boolean sameSquares(Board otherBoard)
    {
        for(int i = 0; i < numberOfSquares(); i++)
            if(otherBoard.charAt(i) != this.squares[i])
                return false;

        return true;
    }

    public abstract Board copy() throws Exception;

    public List<Integer> openSpaces()
    {
        List<Integer> openSpaces = new LinkedList<Integer>();
        for(int i = 0; i < numberOfSquares(); i++)
        {
            if(charAt(i) == 0)
            {
                openSpaces.add(i);
            }
        }

        return openSpaces;
    }

    public void clear() throws Exception
    {
        for(int i = 0; i < numberOfSquares(); i++)
            squares[i] = '\0';
    }

    public void clear(int position) throws Exception
    {
        squares[position] = '\0';
    }


    public boolean empty()
    {
        for(int i = 0; i < numberOfSquares(); i++)
        {
            if(isOccupied(i))
                return false;
        }
        return true;
    }

    public boolean isTie()
    {
        return gameOver && winner == 0;
    }

    public int hashCode()
    {
        String squareString = new String(squares);
        return squareString.hashCode();
    }

    public boolean equals(Board otherBoard)
    {
        int biggestBoardSize = Math.max(squares.length, otherBoard.squares.length);
        for(int i = 0; i < biggestBoardSize; i++)
        {
            if(squares[i] != otherBoard.squares[i])
                return false;
        }
        return true;
    }

    public void populateWithoutChecks(char mark, int position) throws Exception
    {
        if(isOccupied(position))
        {
            throw new Exception();
        }
        squares[position] = mark;
    }

    public String toString()
    {

        StringBuffer buffer = new StringBuffer();
        for (char square : squares)
        {
            if(square != 0)
                buffer.append(square);
            else
                buffer.append(" ");
        }
        return buffer.toString();
    }
}
