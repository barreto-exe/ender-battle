/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.collectibles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class BattleObjectCollectible extends ObjectCollectible
{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private TextureAtlas atlas;
    
    //Atributos de información del arma
    private Constant.BattleObject object;
    private Constant.Material material;
    //</editor-fold>

    public BattleObjectCollectible(Constant.BattleObject object, Constant.Material material, TextureAtlas atlas, World world, Vector2 posicion)
    {
        super(world, posicion);
        this.object = object;
        this.material = material;
        this.atlas = atlas;
        
        setFrame(object);
        setColor(material);
    }

    public Constant.BattleObject getObject()
    {
        return object;
    }
    
    
    private void setFrame(Constant.BattleObject object)
    {
        switch (object)
        {
            case SWORD:
                setRegion(atlas.findRegion("espada"));
                break;
            case AX:
                setRegion(atlas.findRegion("hacha"));
                break;
            case PICK:                                   //FALTA SPRITE DE PICO Y PALA !!!!!!!!!!!!!!!
                setRegion(atlas.findRegion(""));
                break;
            case SHOVEL:
                setRegion(atlas.findRegion(""));
                break;
            case BOOTS:
                setRegion(atlas.findRegion("botas"));
                break;
            case HELMET:
                setRegion(atlas.findRegion("casco"));
                break;
            case SHIRTFRONT:
                setRegion(atlas.findRegion("pechera"));
                break;
            case LEGGING:
                setRegion(atlas.findRegion("pantalones"));
                break;
        }
    }
    
    private void setColor(Constant.Material material)
    {
        switch (material)
        {
            case WOOD:
                setColor(Color.BROWN);
                break;
            case IRON:
                setColor(Color.GRAY);
                break;
            case GOLD:
                setColor(Color.GOLD);
                break;
            case DIAMOND:
                setColor(Color.TAN);
                break;
        }
    }
}
