/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 3:39:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Controller
{
    char charAt(int position);

    void updateDisplay();
    void printInitialBoard();
    void printFinalBoard();

    int requestUserMove(char mark);
    int requestGameType();

    boolean shouldPlayAgain();

    void setGUI(View view);
    void gameTypeChosen(int gameType);
    void squareChosen(int square);
    void playAgain(boolean b);
}
