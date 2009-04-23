package trptcolin.persistence;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: Apr 22, 2009
 * Time: 1:33:22 PM
 */
public class MockBoardScoreStorage extends BoardScoreStorage
{
    private Hashtable<String, Integer> boardScores;

    public MockBoardScoreStorage()
    {
        boardScores = new Hashtable<String, Integer>();
    }

    public boolean containsKey(String key)
    {
        return boardScores.containsKey(key);
    }
    public void put(String key, int value)
    {
        boardScores.put(key, value);
    }

    public int get(String key)
    {
        return boardScores.get(key);
    }

    public void clear()
    {
        boardScores.clear();
    }

    public int size()
    {
        return boardScores.size();
    }
}
