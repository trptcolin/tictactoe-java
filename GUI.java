import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
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
    private GUIController guiController;
    private JLabel[] squares = new JLabel[9];
    private int lastSquareClicked = -1;
    private boolean waitingForInput = false;
    private JFrame jframe = new JFrame();

//    public static void main(String[] args)
    public GUI(final GUIController guiController)
    {
        this.guiController = guiController;
        
        int windowWidth = 500;
        int windowHeight = 500;

        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container content = jframe.getContentPane();

        content.setLayout(new GridLayout(3, 3));

        jframe.setSize(windowWidth, windowHeight);

        jframe.setMaximizedBounds(new Rectangle());
        jframe.setBackground(Color.white);

        buildBoard();

        jframe.setVisible(true);
    }

    private void clear()
    {
        
    }
    
    private void buildBoard()
    {
        int x;
        for(int i = 0; i < 9; i++)
        {
            squares[i] = new JLabel(" ");
            jframe.add(squares[i]);
            squares[i].setBackground(new Color(255 - 5*i, 5*i, 5*i));
            squares[i].setHorizontalAlignment(SwingConstants.CENTER);

            squares[i].setFont(new Font("Georgia", Font.PLAIN, 100));
            squares[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));

            final int squareIndex = i;
            
            squares[i].addMouseListener(new MouseInputAdapter(){
                public void mousePressed(MouseEvent e)
                {
                    moveMade(squares[squareIndex], squareIndex, this);
                }

                public void mouseEntered(MouseEvent e)
                {
                    squares[squareIndex].setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            });
        }
    }

    public void moveMade(JLabel square, int squareIndex, MouseInputAdapter inputListener)
    {
        if(guiController.board.isOccupied(squareIndex))
        {
            
        }
        else
        {
            guiController.setLastMove(squareIndex);
            lastSquareClicked = squareIndex;
            waitingForInput = false;
            square.setText("" + guiController.getActiveMark());
        }

        square.removeMouseListener(inputListener);
        square.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public int getLastSquareClicked()
    {
        return lastSquareClicked;
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
        
        for(int i = 0; i < 9; i++)
        {
            mark = guiController.board.charAt(i);
            if(mark != 0)
            {
                squares[i].setText("" + mark);
            }
        }
    }

    public void stopListening()
    {
        for(JLabel square : squares)
        {
            for(MouseListener listener : square.getMouseListeners())
            {
                square.removeMouseListener(listener);
                square.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    public int requestGameType()
    {
        Container content = jframe.getContentPane();
        content.removeAll();
        
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        
        JButton computerVComputerButton = new JButton("Computer (X) vs. Computer (O)");
        computerVComputerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
            }
        });
        jframe.add(computerVComputerButton);

        JButton humanVComputerButton = new JButton("Human (X) vs. Computer (O)");
        jframe.add(humanVComputerButton);

        JButton computerVHumanButton = new JButton("Computer (X) vs. Human (O)");
        jframe.add(computerVHumanButton);

        JButton humanVHumanButton = new JButton("Human (X) vs. Human (O)");
        jframe.add(humanVHumanButton);

        jframe.setVisible(true);
        
        try
        {
            Thread.sleep(40000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return -1;
    }
}
