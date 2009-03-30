/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 29, 2009
 * Time: 12:01:56 PM
 */
public class MockGUI implements GUI
{
    public boolean redrawCalled = false;
    public boolean clearCalled = false;
    public boolean buildBoardCalled = false;
    public boolean stopListeningCalled = false;
    public boolean addFinalMessageCalled = false;
    public boolean buildGameTypeChoicesCalled = false;
    public boolean setWaitingForInputCalled = false;
    public boolean setWaitingForInputCalledWith = false;

    private GUIController guiController;

    public MockGUI(final GUIController guiController)
    {
        this.guiController = guiController;
    }

    public void redraw()
    {
        redrawCalled = true;
    }

    public void clear()
    {
        clearCalled = true;
    }

    public void buildBoard()
    {
        buildBoardCalled = true;
    }

    public void buildGameTypeChoices()
    {
        buildGameTypeChoicesCalled = true;
    }

    public void stopListening()
    {
        stopListeningCalled = true;
    }

    public void addFinalMessage()
    {
        addFinalMessageCalled = true;
    }

}
