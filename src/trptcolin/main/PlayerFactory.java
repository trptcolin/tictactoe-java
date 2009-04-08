package trptcolin.main;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Apr 2, 2009
 * Time: 9:29:15 AM
 */
public interface PlayerFactory
{
    static int numberOfGameTypes = 4;   
    Player createPlayer(Board board, char mark, Controller controller, int gameTypeCode);

    String gameTypeToString(int i);
}
