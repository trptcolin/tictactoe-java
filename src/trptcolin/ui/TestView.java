package trptcolin.ui;

import trptcolin.baseGame.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 1, 2009
 * Time: 11:22:24 PM
 */
public class TestView extends SwingView
{
    public TestView(final Controller controller)
    {
        this.controller = controller;
        setupWindow();
    }
    
    protected void setupWindow()
    {
        int windowWidth = 500;
        int windowHeight = 500;

        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setSize(windowWidth, windowHeight);
        jframe.setBackground(Color.white);

        setVisible(true);
    }

    private void setVisible(boolean visible)
    {
        jframe.setVisible(visible);
    }
}
