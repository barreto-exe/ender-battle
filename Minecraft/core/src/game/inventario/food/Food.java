/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.inventario.food;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Food {
    private Constant.Farming type;
    private float food;
    private int cant;

    public Food(Constant.Farming type, float food) {
        this.type = type;
        this.food = food;
        cant = 0;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getCant() {
        return cant;
    }
}
