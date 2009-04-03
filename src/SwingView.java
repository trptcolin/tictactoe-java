import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 26, 2009
 * Time: 3:50:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwingView implements View
{
    protected Controller controller;
    protected PlayerFactory playerFactory;
    protected JFrame jframe = new JFrame();

    public SwingView()
    {
    }
    
    public SwingView(final Controller controller, PlayerFactory playerFactory)
    {
        this.controller = controller;
        this.playerFactory = playerFactory;
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

    public void buildBoard()
    {
        jframe.getContentPane().removeAll();
        jframe.setLayout(new GridLayout(3, 3));
                
        for(int i = 0; i < 9; i++)
        {
            JLabel square = createLabel(i);
            jframe.add(square);
        }
        setVisible(true);
    }

    private JLabel createLabel(int i)
    {
        JLabel square = new JLabel(" ");
        square.setHorizontalAlignment(SwingConstants.CENTER);
        square.setFont(new Font("Georgia", Font.PLAIN, 100));
        square.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        square.setCursor(new Cursor(Cursor.HAND_CURSOR));
        square.setName("" + i);
        square.addMouseListener(new SquareLabel(i));
        return square;
    }

    public void buildGameTypeChoices()
    {
        JButton[] buttons = new JButton[4];


        jframe.getContentPane().setLayout(new GridLayout(2, 2));

        for(int i = 0; i < playerFactory.numberOfGameTypes; i++)
        {
            String gameTypeString = playerFactory.gameTypeToString(i);
            buttons[i] = new JButton(gameTypeString);

            buttons[i].setName("" + (i - 1));
            buttons[i].addActionListener(new GameTypeButton(i));
            jframe.add(buttons[i]);
        }
        setVisible(true);
    }

    public void addFinalMessage()
    {
        Container content = jframe.getContentPane();
        content.setLayout(new GridLayout(4, 3));

        JButton playAgain = new JButton("Play Again");
        playAgain.addActionListener(new PlayAgainButton());
        playAgain.setName("playAgain");
        jframe.add(playAgain);

        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        jframe.add(quit);

        jframe.setSize(500, 600);
    }

    private class PlayAgainButton extends JButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            controller.playAgain(true);
        }
    }

    private class GameTypeButton extends JButton implements ActionListener
    {
        private int index;
        public GameTypeButton(int index)
        {
            super(" ");
            this.index = index;
        }

        public void actionPerformed(ActionEvent e)
        {
            controller.gameTypeChosen(index + 1);
        }
    }

    private class SquareLabel extends JLabel implements MouseListener
    {
        private int index;

        public SquareLabel(int index)
        {
            super(" ");
            this.index = index;
        }


        public void mouseClicked(MouseEvent e)
        {
        }

        public void mousePressed(MouseEvent e)
        {
            controller.squareChosen(index);
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
    }

    public void clear()
    {
        jframe.getContentPane().removeAll();
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
                mark = controller.charAt(squareNumber);
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

    public void getUserMove(char mark)
    {
    }
}
