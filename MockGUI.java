/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 29, 2009
 * Time: 12:01:56 PM
 */
public class MockGUI extends GUI
{
    public boolean redrawCalled = false;

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
}
