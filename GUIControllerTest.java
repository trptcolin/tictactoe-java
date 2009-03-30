import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 28, 2009
 * Time: 10:42:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class GUIControllerTest extends Assert
{
    private Board board;
    private GUIController guiController;
    private GUI gui;
    private MockGUI mockGui;

    @Before
    public void setup()
    {
        board = new MockBoard();
        guiController = new GUIController(board);
        gui = new GUI(guiController);
        mockGui = new MockGUI(guiController);
        
        guiController.gui = gui;
    }

    @Test
    public void shouldSetGUI() throws Exception
    {
        guiController.gui = gui;
        assertEquals(gui, guiController.gui);

        guiController.gui = mockGui;
        assertEquals(mockGui, guiController.gui);
    }

    @Test
    public void shouldRedrawGUIOnUpdateDisplay() throws Exception
    {
        guiController.gui = mockGui;
        guiController.updateDisplay();
        assertEquals(true, mockGui.redrawCalled);
    }

    @Test
    public void shouldPrintInitialBoard() throws Exception
    {
        guiController.gui = mockGui;

        guiController.printInitialBoard();
        
        assertEquals(true, mockGui.redrawCalled);
    }

    @Test
    public void shouldRequestUserMove() throws Exception
    {
        guiController.gui = mockGui;
        int squareChosen = -1;

        squareChosen = guiController.requestUserMove('X');

        // TODO: how can I simulate actionPerformed() simultaneously? the execution here is consecutive

        for(Component square : gui.jframe.getContentPane().getComponents())
        {
            if(square instanceof JLabel)
            {
                clickOn(square);
                squareChosen = Integer.parseInt(square.getName());
                System.out.println("squareChosen = " + squareChosen);
                break;
            }
        }
        
        assertEquals(0, squareChosen);

    }

    private void clickOn(Component component) throws AWTException
    {
        Robot robot = new Robot();
        robot.setAutoWaitForIdle(true);
        robot.mouseMove(component.getX() + 10, component.getY() + 10);
        robot.mousePress(MouseEvent.BUTTON1_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
    }
}
