/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.pacific;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.MassData;
import game.screens.GameScreen;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Cow extends PacificMob
{
    public Cow(GameScreen screen, float x, float y) {
        super(screen, (TextureRegion) screen.getAtlas().findRegion("vaca"), 7, x, y, 528 / 4, 80);
        textureMeat = screen.getAtlas().findRegion("ganado_carne");
        
        speed = 1.2f;
        type = Constant.Farming.BEEF;
    }

    //<editor-fold defaultstate="collapsed" desc="Definición de Vertices del Top">
    @Override
    protected Vector2[] getVerticesTop() 
    {
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-64, 42).scl(1 / Constant.PPM);
        vertice[1] = new Vector2(64, 42).scl(1 / Constant.PPM);
        vertice[2] = new Vector2(-64, 10).scl(1 / Constant.PPM);
        vertice[3] = new Vector2(64, 10).scl(1 / Constant.PPM);
        return vertice;
    }
    //</editor-fold>  
}
