/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

/**
 *
 * @author Karen
 */
public class Protection extends BattleObject{
    private float protection;

    public Protection(String description, String material) {
        super(description, material);
    }

    @Override
    protected void setFactorObject() {
        factorObject = protection * material.getFactor();
    }

    @Override
    protected boolean setBattleObject(String object) {
        switch (object){
            case ("botas"):
                protection = 5f;
                return true;
            case ("pecho"):
                protection = 4f;
                return true;
            case ("pantalon"):
                protection = 3f;
                return true;
            case ("casco"):
                protection = 2f;
                return true;
            default:
                protection = 0;
                return false;
        }
    }
    
    

    
}
