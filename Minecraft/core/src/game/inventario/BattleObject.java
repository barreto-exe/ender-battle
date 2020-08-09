package game.inventario;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public abstract class BattleObject
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private Constant.BattleObject object;
    protected int factorObject;
    private boolean isPorted;
    protected Material material;
    private int use;
    //</editor-fold>

    /**
     * Representa un objeto de batalla, como armas y piezas de armadura.
     * @param object es el tipo de objeto.
     * @param material de qué está hecho.
     */
    public BattleObject(Constant.BattleObject object, Constant.Material material)
    {
        if (setObject(object))
        {
            this.object = object;
            this.material = new Material(material);
        }

        isPorted = false;
        setFactorObject();
    }

    public Constant.BattleObject getObject()
    {
        return object;
    }

    public Material getMaterial()
    {
        return material;
    }

    public int getFactorObject()
    {
        return factorObject;
    }

    protected abstract boolean setBattleObject(Constant.BattleObject object);

    private boolean setObject(Constant.BattleObject object)
    {
        if (setBattleObject(object))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setIsPorted(boolean isPorted)
    {
        this.isPorted = isPorted;
    }

    protected abstract void setFactorObject();

}
