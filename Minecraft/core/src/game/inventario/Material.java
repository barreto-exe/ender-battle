package game.inventario;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Material
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private Constant.Material material;
    private int factor;
    //</editor-fold>

    /**
     * Es un material para aplicar a un objeto de batalla. Ayuda a determinar
     * la cantidad de protección o ataque que aporta el objeto de batalla
     * al que se le asigne.
     * @param material es el tipo del material.
     */
    public Material(Constant.Material material)
    {
        this.material = material;
        setFactor(material);
    }

    public Constant.Material getMaterial()
    {
        return material;
    }

    public int getFactor()
    {
        return factor;
    }

    private boolean setFactor(Constant.Material material)
    {
        switch (material)
        {
            case WOOD:
                factor = 2;
                return true;
            case GOLD:
                factor = 2;
                return true;
            case IRON:
                factor = 3;
                return true;
            case DIAMOND:
                factor = 4;
                return true;
            default:
                factor = 0;
                return false;
        }
    }

}
