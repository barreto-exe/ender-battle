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

    /**
     * Gestor de entrada de teclado y mouse.
     * @param controller controlador virtual.
     * @param screen pantalla actual del juego.
     */
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
                GameScreen.getVentanaInventario().mostrar();
                return true;
            }
            case (Input.Keys.T):
            {
                if(!screen.getGame().getPlayer().hasVillager())
                    return true;
                
                controller.setRight(false);
                controller.setLeft(false);
                
                GameScreen.getVentanaTienda().setLocationRelativeTo(screen.getGame().getVentanaOrigen());
                GameScreen.getVentanaTienda().mostrar();
                return true;
            }
            case (Input.Keys.P):
            {
                screen.switchPaused();
                return true;
            }
            case(Input.Keys.ESCAPE):
            {
                GameScreen.getVentanaJugadores().setLocationRelativeTo(screen.getGame().getVentanaOrigen());
                GameScreen.getVentanaJugadores().setVisible(true);
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
