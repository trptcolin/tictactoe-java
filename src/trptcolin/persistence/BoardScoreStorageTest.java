package trptcolin.persistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: colin
 * Date: Apr 22, 2009
 * Time: 1:29:18 PM
 */
public class BoardScoreStorageTest extends Assert
{
    private BoardScoreStorage bss = new BoardScoreStorage();

    @Test
    public void shouldNotContainKey() throws Exception
    {
        assertEquals(false, bss.containsKey("testing"));
    }

    @Test
    public void shouldPutAndContain() throws Exception
    {
//        bss.put("XXXOOO   ", 400);
        assertEquals(true, bss.containsKey("XXXOOO   "));
        bss.clear();
    }

    @Test
    public void shouldGet() throws Exception
    {
//        bss.put("XXXOOO   ", 400);
//        assertEquals(400, bss.get("XXXOOO   "));
//        bss.clear();
    }
}
