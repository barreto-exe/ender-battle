package game.inventario;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Protection extends BattleObject{
    private float protection;

    public Protection(Constant.BattleObject object, Constant.Material material) {
        super(object, material);
    }

    @Override
    protected void setFactorObject() {
        factorObject = protection * material.getFactor();
    }

    @Override
    protected boolean setBattleObject(Constant.BattleObject object) {
        switch (object){
            case BOOTS:
                protection = 5f;
                return true;
            case  SHIRTFRONT:
                protection = 4f;
                return true;
            case LEGGING:
                protection = 3f;
                return true;
            case HELMET:
                protection = 2f;
                return true;
            default:
                protection = 0;
                return false;
        }
    }
    
}
