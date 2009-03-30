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
        
        guiController.gui = mockGui;
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
        guiController.updateDisplay();
        assertEquals(true, mockGui.redrawCalled);
    }

    @Test
    public void shouldPrintInitialBoard() throws Exception
    {
        guiController.printInitialBoard();

        assertEquals(true, mockGui.clearCalled);
        assertEquals(true, mockGui.buildBoardCalled);
        // uses updateDisplay(), so same test        
        assertEquals(true, mockGui.redrawCalled);
    }

    @Test
    public void shouldPrintFinalBoard() throws Exception
    {
        guiController.printFinalBoard();

        assertEquals(true, mockGui.stopListeningCalled);
        assertEquals(true, mockGui.addFinalMessageCalled);
        // uses updateDisplay(), so same test
        assertEquals(true, mockGui.redrawCalled);
    }

    @Test
    public void shouldRequestUserMove() throws Exception
    {
        final int[] squareChosen = new int[]{-1};

        Thread thread = new Thread() {
            public void run()
            {
                squareChosen[0] = guiController.requestUserMove('X');
            }
        };
        thread.start();

        Thread.sleep(101);
        guiController.squareChosen(0);
        
        thread.join();

        assertEquals(0, squareChosen[0]);
    }

    @Test
    public void shouldSetLastMoveOnSquareChosen() throws Exception
    {
        guiController.waitingForInput = true;
        guiController.squareChosen(0);
        assertEquals(false, guiController.waitingForInput);
        assertEquals(0, guiController.lastMove);
    }

    @Test
    public void shouldPlayAgain() throws Exception
    {
        final boolean[] playAgain = new boolean[]{false};


        Thread thread = new Thread() {
            public void run()
            {
                playAgain[0] = guiController.shouldPlayAgain();
            }
        };

        thread.start();

        Thread.sleep(101);
        guiController.playAgain(true);

        thread.join();

        assertEquals(true, guiController.playAgain);
    }

    @Test
    public void shouldChooseGameType() throws Exception
    {
        final int[] gameType = new int[]{-1};
        
        Thread thread = new Thread() {
            public void run()
            {
                gameType[0] = guiController.requestGameType();
            }
        };

        thread.start();

        Thread.sleep(101);
        guiController.gameTypeChosen(1);

        thread.join();

        assertEquals(1, guiController.gameType);
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
