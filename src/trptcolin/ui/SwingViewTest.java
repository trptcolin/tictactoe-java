package trptcolin.ui;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.boards.BoardIn2D;
import trptcolin.players.PlayerFactoryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 31, 2009
 * Time: 7:23:10 PM
 */
public class SwingViewTest extends Assert
{
    private MockController controller;
    private SwingView swingView;
    @Before
    public void setup()
    {
        controller = new MockController(new BoardIn2D());
        swingView = new SwingView(controller, new PlayerFactoryImpl());
    }

    @Test
    public void shouldSetupView() throws Exception
    {
        assertEquals(WindowConstants.EXIT_ON_CLOSE, swingView.jframe.getDefaultCloseOperation());
        assertEquals(true, swingView.jframe.isVisible());
    }

    @Test
    public void shouldBuildBoard() throws Exception
    {
        swingView.buildBoard();
        assertTrue(swingView.jframe.getContentPane().getLayout() instanceof GridLayout);
        assertEquals(9, swingView.jframe.getContentPane().getComponentCount());
        for(int i = 0; i < 9; i++)
            assertEquals(1, swingView.jframe.getContentPane().getComponent(i).getMouseListeners().length);
    }

    @Test
    public void shouldClearContent() throws Exception
    {
        swingView.clear();
        assertEquals(0, swingView.jframe.getContentPane().getComponentCount());
    }

    @Test
    public void shouldSetupListenersForSquares() throws Exception
    {
        swingView.buildBoard();

        int squareIndex = 8;

        MouseListener[] mouseListeners = swingView.jframe.getContentPane().getComponent(squareIndex).getMouseListeners();
        mouseListeners[0].mousePressed(new MouseEvent(new JLabel(" "), 1, 1, 0, 0, 0, 1, false, 0));

        assertEquals(true, controller.squareChosenCalled);
        assertEquals(squareIndex, controller.squareChosenCalledWith);
    }

    @Test
    public void shouldSetupListenersForGameTypeButtons() throws Exception
    {
        swingView.buildGameTypeChoices();

        int buttonIndex = 3;
        
        ActionListener[] actionListeners = swingView.jframe.getContentPane().getComponent(buttonIndex).getListeners(ActionListener.class);
        actionListeners[0].actionPerformed(new ActionEvent(new JLabel(" "), 1, " "));

        assertEquals(true, controller.gameTypeChosenCalled);
        assertEquals(buttonIndex + 1, controller.gameTypeChosenCalledWith);
    }

    @Test
    public void shouldSetupListenerForPlayAgainButton() throws Exception
    {
        swingView.addFinalMessage();

        int buttonIndex = 0;

        ActionListener[] actionListeners = swingView.jframe.getContentPane().getComponent(buttonIndex).getListeners(ActionListener.class);
        actionListeners[0].actionPerformed(new ActionEvent(new JLabel(" "), 1, " "));

        assertEquals(true, controller.playAgainCalled);
        assertEquals(true, controller.playAgainCalledWith);
    }
}
