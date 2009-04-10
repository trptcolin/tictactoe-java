package trptcolin.main;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 8, 2009
 * Time: 11:10:30 AM
 */
public abstract class Board
{
    protected char[] squares;

    public char winner;
    public boolean won;

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
//                              
//    public abstract int numberOfSquares();
//    public abstract char charAt(int position);
//    public abstract void populate(char mark, int position) throws Exception;

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
        
//        if(isWon(position))
//        {
//            won = true;
//            winner = mark;
//        }
//        else
//        {
//            won = false;
//            winner = 0;
//        }
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

    private boolean isWon(int position)
    {
        char c;
        
        for(int[] winSet : winSets)
        {
            if(position == winSet[0] || position == winSet[1] || position == winSet[2])
            {
                c = charAt(winSet[0]);
                if(c != 0 && c == charAt(winSet[1]) && c == charAt(winSet[2]))
                {
                    winner = c;
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isWon()
    {
        char c;
        
        for (int[] winSet : winSets)
        {
            c = charAt(winSet[0]);
            if(c != 0 && c == charAt(winSet[1]) && c == charAt(winSet[2]))
            {
                winner = c;
                return true;
            }
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
        List<Integer> openSpaces = new ArrayList<Integer>();
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
        return isFull() && !isWon();
    }
}
