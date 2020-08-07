package game.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 *
 * @author Karen
 */
public class HandleInput extends InputAdapter
{
    private VirtualController controller;

    public HandleInput(VirtualController controller)
    {
        this.controller = controller;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case (Input.Keys.RIGHT):
            case (Input.Keys.D):
            {
                controller.setRight(true);
                return true;
            }
            case (Input.Keys.LEFT):
            case (Input.Keys.A):
            {
                controller.setLeft(true);
                return true;
            }
            case (Input.Keys.W):
            case (Input.Keys.UP):
            {
                controller.setUp(true);
                return true;
            }
            case (Input.Keys.J): 
            case (Input.Keys.SPACE):
            { 
                controller.setHitting(true); 
                return true; 
            } 
            case (Input.Keys.Q):
            case (Input.Keys.ENTER):
            { 
                controller.setPickingUp(true); 
                return true; 
            } 
            case (Input.Keys.E):
            {
                //Abrir inventario
            }
            default:
            {
                return false;
            }
        }
    }

    @Override
    public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
            case (Input.Keys.RIGHT):
            case (Input.Keys.D):
            {
                controller.setRight(false);
                return true;
            }
            case (Input.Keys.LEFT):
            case (Input.Keys.A):
            {
                controller.setLeft(false);
                return true;
            }
            case (Input.Keys.W):
            case (Input.Keys.UP):
            {
                controller.setUp(false);
                return true;
            }
            case (Input.Keys.J): 
            case (Input.Keys.SPACE):
            { 
                controller.setHitting(false); 
                return true; 
            } 
            case (Input.Keys.E):
            case (Input.Keys.ENTER):
            { 
                controller.setPickingUp(false); 
                return true; 
            } 
            default:
            {
                return false;
            }
        }
    }

}
