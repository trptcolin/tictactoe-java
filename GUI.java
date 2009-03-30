import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 26, 2009
 * Time: 3:50:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class GUI
{

    protected GUIController guiController;
    protected boolean waitingForInput = false;
    protected JFrame jframe = new JFrame();
    
    public GUI(final GUIController guiController)
    {
        this.guiController = guiController;

        int windowWidth = 500;
        int windowHeight = 500;

        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setSize(windowWidth, windowHeight);
        jframe.setMaximizedBounds(new Rectangle());
        jframe.setBackground(Color.white);

        jframe.setVisible(true);
    }
    
    protected void buildBoard()
    {
        jframe.getContentPane().removeAll();
        jframe.setLayout(new GridLayout(3, 3));
                
        for(int i = 0; i < 9; i++)
        {
            JLabel square = new JLabel(" ");
            square.setHorizontalAlignment(SwingConstants.CENTER);
            square.setFont(new Font("Georgia", Font.PLAIN, 100));
            square.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            square.setCursor(new Cursor(Cursor.HAND_CURSOR));
            square.setName("" + i);
            square.addMouseListener(guiController);
            jframe.add(square);
        }
        jframe.setVisible(true);
    }

    public void clear()
    {
        jframe.getContentPane().removeAll();
    }

    public void setWaitingForInput(boolean waiting)
    {
        this.waitingForInput = waiting;
    }

    public boolean isWaitingForInput()
    {
        return waitingForInput;
    }

    public void redraw()
    {
        char mark;
        JLabel square;

        for(Component component : jframe.getContentPane().getComponents())
        {
            if(component instanceof JLabel)
            {
                square = (JLabel)component;
                int squareNumber = Integer.parseInt(square.getName());
                mark = guiController.board.charAt(squareNumber);
                if(mark != 0)
                    square.setText("" + mark);
            }
        }
    }

    public void stopListening()
    {
        JLabel square;
        for(Component component : jframe.getContentPane().getComponents())
        {
            if(component instanceof JLabel)
            {
                square = (JLabel) component;

                for(MouseListener listener : square.getMouseListeners())
                {
                    square.removeMouseListener(listener);
                    square.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }
    }

    public void buildGameTypeChoices()
    {
        JButton[] buttons = new JButton[4];

        int i = 0;

        jframe.getContentPane().setLayout(new GridLayout(2, 2));
        for(PlayerFactory.GameType gameType : PlayerFactory.GameType.values())
        {
            // relies on naming convention _V_ between player types
            String[] playerNames = gameType.toString().split("_V_");
            buttons[i] = new JButton("" + playerNames[0] + " (X) vs. " + playerNames[1] + " (O)");

            buttons[i].setName("" + i);
            buttons[i].addActionListener(guiController);
            jframe.add(buttons[i]);
            i++;
        }
        jframe.setVisible(true);
    }

    public void addFinalMessage()
    {
        Container content = jframe.getContentPane();
        content.setLayout(new GridLayout(4, 3));

        JButton playAgain = new JButton("Play Again");
        playAgain.addActionListener(guiController);
        playAgain.setName("playAgain");
        jframe.add(playAgain);

        jframe.setSize(500, 600);
    }

}
