package game.actors.collectibles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import game.inventario.Arm;
import game.inventario.BattleObject;
import game.inventario.Protection;
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
    private BattleObject object;
    //</editor-fold>

    /**
     * Representa un objeto de batalla en el juego.
     * @param object indica el tipo de objeto.
     * @param material del que está hecho el objeto.
     * @param atlas que contiene la textura del objeto.
     * @param world donde se ubicará el objeto.
     * @param posicion donde se ubicará el objeto.
     */
    public BattleObjectCollectible(Constant.BattleObjectEnum object, Constant.Material material, TextureAtlas atlas, World world, Vector2 posicion)
    {
        super(world, posicion);
        this.atlas = atlas;
        
        switch (object)
        {
            case SWORD:
            case AX:
            case PICK:
            case SHOVEL:
                this.object = new Arm(object, material);
                break;
            case BOOTS:
            case LEGGING:
            case HELMET:
            case SHIRTFRONT:
                this.object = new Protection(object, material);
                break;
        }
        
        setFrame(object);
        setColor(material);
    }

    public BattleObject getObject()
    {
        return object;
    }
    
    
    private void setFrame(Constant.BattleObjectEnum object)
    {
        switch (object)
        {
            case SWORD:
                setRegion(atlas.findRegion("espada"));
                break;
            case AX:
                setRegion(atlas.findRegion("hacha"));
                break;
            case PICK:                                 
                setRegion(atlas.findRegion("pico"));
                break;
            case SHOVEL:
                setRegion(atlas.findRegion("pala"));
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
                setColor(Color.CYAN);
                break;
        }
    }
}
