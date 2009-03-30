import junit.framework.Assert;
import org.junit.Before;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 28, 2009
 * Time: 10:40:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class GUITest extends Assert
{
    private GUIController guiController;
    private SwingGUI swingGui;

    @Before
    public void setup()
    {
        guiController = new GUIController(new Board());
        swingGui = new SwingGUI(guiController);
    }
    
}
