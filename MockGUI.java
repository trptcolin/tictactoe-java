/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 29, 2009
 * Time: 12:01:56 PM
 */
public class MockGUI extends GUI
{
    public boolean redrawCalled = false;
    public boolean clearCalled = false;
    public boolean buildBoardCalled = false;
    public boolean stopListeningCalled = false;
    public boolean addFinalMessageCalled = false;
    public boolean buildGameTypeChoicesCalled = false;
    public boolean setWaitingForInputCalled = false;
    public boolean setWaitingForInputCalledWith = false;

    public MockGUI(final GUIController guiController)
    {
        super(guiController);
        this.guiController = guiController;
    }

    public void redraw()
    {
        redrawCalled = true;
        super.redraw();
    }

    public void clear()
    {
        clearCalled = true;
        super.redraw();
    }

    public void buildBoard()
    {
        buildBoardCalled = true;
        super.buildBoard();
    }

    public void buildGameTypeChoices()
    {
        buildGameTypeChoicesCalled = true;
    }

    public void stopListening()
    {
        stopListeningCalled = true;
        super.stopListening();
    }

    public void addFinalMessage()
    {
        addFinalMessageCalled = true;
        super.addFinalMessage();
    }

    public void setWaitingForInput(boolean waiting)
    {
        super.setWaitingForInput(waiting);
        setWaitingForInputCalled = true;
        setWaitingForInputCalledWith = waiting;
    }
}
