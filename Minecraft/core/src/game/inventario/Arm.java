package game.inventario;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Arm extends BattleObject
{
    private float attack;

    /**
     * Representa un arma del juego.
     * @param object el tipo de objeto.
     * @param material de qué está hecho.
     */
    public Arm(Constant.BattleObject object, Constant.Material material)
    {
        super(object, material);
    }

    @Override
    protected void setFactorObject()
    {
        factorObject = attack * material.getFactor();
    }

    @Override
    protected boolean setBattleObject(Constant.BattleObject object)
    {
        switch (object)
        {
            case SWORD:
                attack = 5f;
                return true;
            case AX:
                attack = 4f;
                return true;
            case PICK:
                attack = 3f;
                return true;
            case SHOVEL:
                attack = 2f;
                return true;
            default:
                attack = 0;
                return false;
        }
    }
}
