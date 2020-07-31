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
public class Arm extends BattleObject{
    private float attack;

    public Arm(String description, String material) {
        super(description, material);
    }

    @Override
    protected void setFactorObject() {
        factorObject = attack * material.getFactor();
    }
        
    @Override
    protected boolean setBattleObject(String object) {
        switch (object){
            case ("espada"):
                attack = 5f;
                return true;
            case ("hacha"):
                attack = 4f;
                return true;
            case ("pico"):
                attack = 3f;
                return true;
            case ("pala"):
                attack = 2f;
                return true;
            default:
                return false;
        }
    }
}
