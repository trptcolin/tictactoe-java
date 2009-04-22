package trptcolin.players;

import trptcolin.baseGame.Board;
import trptcolin.baseGame.Controller;
import trptcolin.baseGame.Player;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 4:27:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class HumanPlayer extends Player
{
    Controller controller;

    public HumanPlayer(Board board, char mark, Controller controller)
    {
        super(board, mark);
        this.controller = controller;
    }

    public void makeMove()
    {
        int position;
        boolean legalMove = false;
        while(!legalMove)
        {
            position = controller.requestUserMove(mark);
            
            if(board.isOccupied(position))
            {
                controller.updateDisplay();
            }
            else
            {
                try
                {
                    board.populate(mark, position);
                    legalMove = true;
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
