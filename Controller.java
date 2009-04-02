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
    String boardToString();

    void updateDisplay();
    void printInitialBoard();
    void printFinalBoard();

    int requestUserMove(char mark);
    int requestGameType();

    boolean shouldPlayAgain();

    void setUI(View view);
    void gameTypeChosen(int gameType);
    void squareChosen(int square);
    void playAgain(boolean b);

    void setWaitingForInput(boolean b);
    void setPlayAgain(boolean b);
    void setGameType(int i);
    void setLastMove(int move);
}
