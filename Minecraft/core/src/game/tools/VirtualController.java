package game.tools;

/**
 * @author Karen
 */
public class VirtualController
{
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean hitting;
    private boolean pickingUp;
    private boolean viewingStore;

    public boolean isLeft()
    {
        return left;
    }

    public void setLeft(boolean left)
    {
        this.left = left;
    }

    public boolean isRight()
    {
        return right;
    }

    public void setRight(boolean right)
    {
        this.right = right;
    }

    public boolean isUp()
    {
        return up;
    }

    public void setUp(boolean up)
    {
        this.up = up;
    }

    public boolean isHitting()
    {
        return hitting;
    }

    public void setHitting(boolean hitting)
    {
        this.hitting = hitting;
    }

    public boolean isPickingUp()
    {
        return pickingUp;
    }

    public void setPickingUp(boolean pickingUp)
    {
        this.pickingUp = pickingUp;
    }

    public boolean isViewingStore() {
        return viewingStore;
    }

    public void setViewingStore(boolean viewingStore) {
        this.viewingStore = viewingStore;
    }
}
