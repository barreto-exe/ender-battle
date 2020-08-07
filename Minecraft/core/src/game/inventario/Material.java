package game.inventario;

/**
 *
 * @author Karen
 */
public class Material {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private String name;
    private int factor;
    //</editor-fold>

    public Material(String name) {
        setFactor(name);
    }

    public String getMaterial() {
        return name;
    }

    public int getFactor() {
        return factor;
    }

    private boolean setFactor(String name) {
        switch (name){
            case ("madera"):
                this.name = name;
                factor = 2;
                return true;
            case ("hierro"):
                this.name = name;
                factor = 3;
                return true;
            case ("oro"):
                this.name = name;
                factor = 4;
                return true;
            case("diamante"):
                this.name = name;
                factor = 5;
                return true;
            default:
                this.name = "";
                factor = 0;
                return false;
        }
    }
    
    
}
