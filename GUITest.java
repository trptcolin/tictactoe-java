import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.*;

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
    private GUI gui;

    @Before
    public void setup()
    {
        guiController = new GUIController(new Board());
        gui = new GUI(guiController);
    }

    @Test
    public void shouldSetWaitingForInput() throws Exception
    {
        assertEquals(false, gui.isWaitingForInput());
        gui.setWaitingForInput(true);
        assertEquals(true, gui.isWaitingForInput());
        gui.setWaitingForInput(false);
        assertEquals(false, gui.isWaitingForInput());
    }


    
}
