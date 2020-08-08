package game.inventario.food;

import game.tools.Constant.Farming;

/**
 *
 * @author Karen
 */
public class Food {
    private Farming type;
    private float food;
    private int cant;

    public Food(Farming type, float food) {
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
