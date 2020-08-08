/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.actors.collectibles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    //Atributos de información del arma
    protected Constant.BattleObject object;
    protected Constant.Material material;
    //</editor-fold>

    public BattleObjectCollectible(Constant.BattleObject object, Constant.Material material, World world, Vector2 posicion)
    {
        super(world, posicion);
        this.object = object;
        this.material = material;
        
        setFrame(object);
        setColor(material);
    }

    public Constant.BattleObject getObject() {
        return object;
    }
    
    
    private void setFrame(Constant.BattleObject object)
    {
        switch (object)
        {
            case SWORD:
                break;
            case AX:
                break;
            case PICK:
                break;
            case SHOVEL:
                break;
            case BOOTS:
                break;
            case HELMET:
                break;
            case SHIRTFRONT:
                break;
            case LEGGING:
                break;
        }
    }
    
    private void setColor(Constant.Material material)
    {
        switch (material)
        {
            case WOOD:
                setColor(Color.TAN);
                break;
            case IRON:
                setColor(Color.TAN);
                break;
            case GOLD:
                setColor(Color.TAN);
                break;
            case DIAMOND:
                setColor(Color.TAN);
                break;
        }
    }
}
