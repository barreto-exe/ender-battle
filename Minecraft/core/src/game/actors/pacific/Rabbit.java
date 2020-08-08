/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.pacific;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import game.screens.GameScreen;
import game.tools.Constant;
import game.tools.Sonido;

/**
 *
 * @author Karen
 */
public class Rabbit extends PacificMob
{
    public Rabbit(GameScreen screen, float x, float y) {
        super(screen, (TextureRegion) screen.getAtlas().findRegion("conejo"), 5, x, y, 149 / 4, 32, Sonido.RABBIT);
        textureMeat = screen.getAtlas().findRegion("conejo_carne");
        
        speed = 1.2f;
        type = Constant.Farming.RABBIT;
    }

    //<editor-fold defaultstate="collapsed" desc="Definición de Vertices del Top">
    @Override
    protected Vector2[] getVerticesTop()
    {
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-16, 18).scl(1 / Constant.PPM);
        vertice[1] = new Vector2(16, 18).scl(1 / Constant.PPM);
        vertice[2] = new Vector2(-16, 3).scl(1 / Constant.PPM);
        vertice[3] = new Vector2(16, 3).scl(1 / Constant.PPM);
        return vertice;
    }
    //</editor-fold>  
}
