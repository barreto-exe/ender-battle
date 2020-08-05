/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tools;

import com.badlogic.gdx.Gdx;
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
            case(Input.Keys.ESCAPE):
            {
                GameScreen.getGame().getVentanaOrigen().cerrarPartida();
                Gdx.app.exit();
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
            default:
            {
                return false;
            }
        }
    }

}
