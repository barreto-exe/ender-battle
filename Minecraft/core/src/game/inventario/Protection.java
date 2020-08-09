package game.inventario;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Protection extends BattleObject
{

    private int protection;

    /**
     * Representa una pieza de armadura.
     * @param object el tipo de la pieza.
     * @param material dice de qué está hecha.
     */
    public Protection(Constant.BattleObject object, Constant.Material material)
    {
        super(object, material);
    }

    @Override
    protected void setFactorObject()
    {
        factorObject = protection * material.getFactor();
    }

    @Override
    protected boolean setBattleObject(Constant.BattleObject object)
    {
        switch (object)
        {
            case SHIRTFRONT:
                protection = 3;
                return true;
            case HELMET:
                protection = 2;
                return true;
            case LEGGING:
                protection = 2;
                return true;
            case BOOTS:
                protection = 1;
                return true;
            default:
                protection = 0;
                return false;
        }
    }

}
