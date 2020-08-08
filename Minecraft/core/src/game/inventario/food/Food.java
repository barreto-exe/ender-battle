package game.inventario.food;

import game.tools.Constant.Farming;

/**
 *
 * @author Karen
 */
public class Food
{
    private Farming type;
    private float food;
    private int cant;

    /**
     * Representa un alimento.
     * @param type tipo de alimento.
     * @param food vida que aporta al comerlo.
     */
    public Food(Farming type, float food)
    {
        this.type = type;
        this.food = food;
        cant = 0;
    }

    public Farming getType()
    {
        return type;
    }

    public void setCant(int cant)
    {
        this.cant = cant;
    }

    public int getCant()
    {
        return cant;
    }

    public float getFood()
    {
        return food;
    }

}
