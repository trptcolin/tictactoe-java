package trptcolin.ui;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.baseGame.Board;
import trptcolin.baseGame.MockBoard;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 28, 2009
 * Time: 10:42:42 PM
 */
public class ControllerImplTest extends Assert
{
    private Board board;
    private ControllerImpl controller;
    private MockView mockView;

    @Before
    public void setup()
    {
        board = new MockBoard();
        controller = new ControllerImpl(board);
        mockView = new MockView();

        controller.view = mockView;
    }

    @Test
    public void shouldSetGUI() throws Exception
    {
        MockView newMockView = new MockView();
        controller.view = newMockView;
        assertEquals(newMockView, controller.view);

        controller.view = mockView;
        assertEquals(mockView, controller.view);
    }

    @Test
    public void shouldRedrawGUIOnUpdateDisplay() throws Exception
    {
        controller.updateDisplay();
        assertEquals(true, mockView.redrawCalled);
    }

    @Test
    public void shouldPrintInitialBoard() throws Exception
    {
        controller.printInitialBoard();

        assertEquals(true, mockView.clearCalled);
        assertEquals(true, mockView.buildBoardCalled);
        // uses updateDisplay(), so same test
        assertEquals(true, mockView.redrawCalled);
    }

    @Test
    public void shouldPrintFinalBoard() throws Exception
    {
        controller.printFinalBoard();

        assertEquals(true, mockView.stopListeningCalled);
        assertEquals(true, mockView.addFinalMessageCalled);
        // uses updateDisplay(), so same test
        assertEquals(true, mockView.redrawCalled);
    }

    @Test
    public void shouldRequestUserMove() throws Exception
    {
        final int[] squareChosen = new int[]{-1};

        Thread thread = new Thread() {
            public void run()
            {
                squareChosen[0] = controller.requestUserMove('X');
            }
        };
        thread.start();

        Thread.sleep(101);
        controller.squareChosen(0);

        thread.join();

        assertEquals(0, squareChosen[0]);
    }

    @Test
    public void shouldSetLastMoveOnSquareChosen() throws Exception
    {
        controller.waitingForInput = true;
        controller.squareChosen(0);
        assertEquals(false, controller.waitingForInput);
        assertEquals(0, controller.lastMove);
    }

    @Test
    public void shouldPlayAgain() throws Exception
    {
        final boolean[] playAgain = new boolean[]{false};


        Thread thread = new Thread() {
            public void run()
            {
                playAgain[0] = controller.shouldPlayAgain();
            }
        };

        thread.start();

        Thread.sleep(101);
        controller.playAgain(true);

        thread.join();

        assertEquals(true, controller.playAgain);
    }

    @Test
    public void shouldChooseGameType() throws Exception
    {
        final int[] gameType = new int[]{-1};

        Thread thread = new Thread() {
            public void run()
            {
                gameType[0] = controller.requestGameType();
            }
        };

        thread.start();

        Thread.sleep(101);
        controller.gameTypeChosen(1);
        thread.join();

        assertEquals(1, controller.gameType);
        assertEquals(false, board.gameOver());
    }
}
