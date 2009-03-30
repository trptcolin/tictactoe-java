import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 30, 2009
 * Time: 1:12:34 PM
 */
public interface GUI
{
    public void clear();
    public void redraw();

    public void buildBoard();
    public void buildGameTypeChoices();
    public void addFinalMessage();

    public void stopListening();
}
