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
public class GUIController extends GameController
{
    protected GUI gui;
    public int lastMove = -1;
    protected int gameType = -1;
    private char activeMark = 0;
    public boolean playAgain = false;
    public boolean waitingForInput = false;  
    
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
        gui.clear();
        gui.buildGameTypeChoices();
        requestUserInput();
        return gameType;
    }
    
    protected int requestUserInput()
    {
        waitingForInput = true;

        boolean gameOver = board.gameOver();

        while(waitingForInput && !gameOver)
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
        waitingForInput = true;
        while(waitingForInput)
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

    public void squareChosen(int square)
    {
        waitingForInput = false;
        lastMove = square;
    }


    public void playAgain(boolean b)
    {
        waitingForInput = false;
        playAgain = b;
    }

    public void gameTypeChosen(int gameType)
    {
        waitingForInput = false;
        this.gameType = gameType;
    }

//    public void actionPerformed(ActionEvent e)
//    {
//        JButton button = (JButton) e.getSource();
//        gui.setWaitingForInput(false);
//
//        String name = button.getName();
//        if(name == "playAgain")
//        {
//            playAgain = true;
//            gui.clear();
//            try
//            {
//                board.clear();
//            }
//            catch(Exception ex)
//            {
//            }
//        }
//        else
//        {
//            gameType = Integer.parseInt(button.getName()) + 1;
//        }
//    }

}

