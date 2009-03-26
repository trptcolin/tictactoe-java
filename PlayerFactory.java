/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 26, 2009
 * Time: 1:09:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerFactory
{
    // Naming convention _V_ determines choices
    public enum GameType
    {
        Computer_V_Computer,
        Computer_V_Human,
        Human_V_Computer,
        Human_V_Human
    }

    public static Player createPlayer(Board board, char mark, GameDisplay gameDisplay, int gameTypeCode)
    {
        Player player;
        GameType gameType = GameType.Computer_V_Computer;

        gameTypeCode -= 1;

        for(GameType gt : GameType.values())
            if(gt.ordinal() == gameTypeCode)
                gameType = gt;

        switch(gameType)
        {
            case Computer_V_Computer:
                player = new ComputerPlayer(board, mark);
                break;
            case Computer_V_Human:
                if(mark == 'X')
                    player = new ComputerPlayer(board, mark);
                else
                    player = new HumanPlayer(board, mark, gameDisplay);
                break;
            case Human_V_Computer:
                if(mark == 'X')
                    player = new HumanPlayer(board, mark, gameDisplay);
                else
                    player = new ComputerPlayer(board, mark);
                break;
            case Human_V_Human:
                player = new HumanPlayer(board, mark, gameDisplay);
                break;
            default:
                player = new ComputerPlayer(board, mark);
        }

        return player;
    }
}
