package game.tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.screens.GameScreen;

/**
 *
 * @author Karen
 */
public class HandleInput extends InputAdapter
{
    private VirtualController controller;
    private GameScreen screen;

    public HandleInput(VirtualController controller, GameScreen screen)
    {
        this.controller = controller;
        this.screen = screen;
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
            case (Input.Keys.K):
            case (Input.Keys.ENTER):
            { 
                controller.setPickingUp(true); 
                return true; 
            } 
            case (Input.Keys.E):
            {
                controller.setRight(false);
                controller.setLeft(false);
                
                //Posición relativa a la ventana de Juego
                GameScreen.getVentanaInventario().setLocationRelativeTo(screen.getGame().getVentanaOrigen());
                //Mostrar inventario
                GameScreen.getVentanaInventario().setVisible(true);
                return true;
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
            case (Input.Keys.K):
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
