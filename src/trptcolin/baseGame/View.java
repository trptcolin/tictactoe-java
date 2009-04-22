package trptcolin.baseGame;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 30, 2009
 * Time: 1:12:34 PM
 */
public interface View
{
    public void clear();
    public void redraw();

    public void buildBoard();
    public void buildGameTypeChoices();
    public void addFinalMessage();

    public void stopListening();

    public void getUserMove(char mark);
}
