package trptcolin.players;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.main.Board;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 12:31:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest extends Assert
{
    private Board board = new Board();
    private Player player;

    @Before
    public void setup()
    {
        player = new MockPlayer(board, 'X');
    }

    @Test
    public void shouldHaveMarkX() throws Exception
    {
        assertEquals('X', player.mark);
    }

    @Test
    public void shouldHaveBoard() throws Exception
    {
        assertEquals(board, player.board);
    }

    @Test
    public void shouldGetOtherPlayerMark() throws Exception
    {
        assertEquals('O', Player.otherPlayerMark('X'));
    }

    @Test
    public void shouldHaveOnePossibleResultingBoard() throws Exception
    {
        for(int i = 0; i < 8; i++)
            player.makeMove();
        assertEquals(1, player.possibleResultingBoards(board).size());
    }

    @Test
    public void shouldHaveTwoPossibleResultingBoards() throws Exception
    {
        for(int i = 0; i < 7; i++)
            player.makeMove();

        Player otherPlayer = new MockPlayer(board, 'O');

        assertEquals(2, player.possibleResultingBoards(board).size());
    }

    @Test
    public void shouldKnowAboutOtherPlayer() throws Exception
    {
        Player player2 = new MockPlayer(board, 'O');
        player.setOtherPlayer(player2);

        assertEquals(player2, player.getOtherPlayer());
        assertEquals(player, player2.getOtherPlayer());
        assertEquals(player2, player2.getOtherPlayer().getOtherPlayer());
        assertEquals(player, player.getOtherPlayer().getOtherPlayer());
    }
}
