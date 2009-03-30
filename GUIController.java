import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 27, 2009
 * Time: 9:12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class GUIController extends GameController implements MouseListener, ActionListener
{
    protected GUI gui;
    private int lastMove = -1;
    private int gameType = -1;
    private char activeMark = 0;
    private boolean playAgain = false;
    
    public GUIController(Board board)
    {
        super(board);
        gui = new GUI(this);
    }

    public void updateDisplay()
    {
        gui.redraw();
    }

    public void printInitialBoard()
    {
        gui.clear();
        gui.buildBoard();
        updateDisplay();
    }

    public void printFinalBoard()
    {
        gui.stopListening();
        gui.addFinalMessage();
        updateDisplay();
    }

    public String boardToString()
    {
        return "";
    }

    public int requestUserMove(char mark)
    {
        return requestUserInput();
    }

    public int requestGameType()
    {
        gui.buildGameTypeChoices();
        requestUserInput();
        return gameType;
    }
    
    protected int requestUserInput()
    {
        gui.setWaitingForInput(true);

        boolean gameOver = board.gameOver();

        while(gui.isWaitingForInput() && !gameOver)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return lastMove;
    }

    public boolean shouldPlayAgain()
    {
        gui.setWaitingForInput(true);
        while(gui.isWaitingForInput())
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return playAgain;
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        gui.setWaitingForInput(false);
        lastMove = Integer.parseInt(e.getComponent().getName());

    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton) e.getSource();
        gui.setWaitingForInput(false);

        String name = button.getName();
        if(name == "playAgain")
        {
            playAgain = true;
            try
            {
                board.clear();
            }
            catch(Exception ex)
            {
            }
            gui.clear();
                        
        }
        else
        {
            gameType = Integer.parseInt(button.getName()) + 1;
        }
    }
}

