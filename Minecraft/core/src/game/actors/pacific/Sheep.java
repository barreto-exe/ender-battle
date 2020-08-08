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
public class Sheep extends PacificMob
{
    public Sheep(GameScreen screen, float x, float y) {
        super(screen, (TextureRegion) screen.getAtlas().findRegion("oveja"), 7, x, y, 267 / 3, 64, Sonido.SHEEP);
        textureMeat = screen.getAtlas().findRegion("ganado_carne");
        
        speed = 1.2f;
        type = Constant.Farming.BEEF;
    }

    //<editor-fold defaultstate="collapsed" desc="Definición de Vertices del Top">
    @Override
    protected Vector2[] getVerticesTop()
    {
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-42, 34).scl(1 / Constant.PPM);
        vertice[1] = new Vector2(42, 34).scl(1 / Constant.PPM);
        vertice[2] = new Vector2(-42, 10).scl(1 / Constant.PPM);
        vertice[3] = new Vector2(42, 10).scl(1 / Constant.PPM);
        return vertice;
    }
    //</editor-fold>  
}
