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

public abstract class BattleObject {
    
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private String description;
    protected float factorObject;
    private boolean isPorted;
    protected Material material;
    private int use;
    //</editor-fold>

    public BattleObject(String description, String material){
        if (setObject(description, material))
        {
            this.description = description;
            this.material = new Material(material);
        }
        description = "";
        this.material = new Material("");
        isPorted = false;
        setFactorObject();
        
    }

    public String getDescription() {
        return description;
    }

    public Material getMaterial() {
        return material;
    }
    
    protected abstract boolean setBattleObject(String object);
    
    private boolean isMaterial(String name)
    {
        switch (name){
            case ("madera"):
                return true;
            case ("hierro"):
                return true;
            case ("oro"):
                return true;
            case("diamante"):
                return true;
            default:
                return false;
        }
    }

    private boolean setObject(String description, String material)
    {
        if (setBattleObject(description) && isMaterial(material)){
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public void setIsPorted(boolean isPorted)
    {
        this.isPorted = isPorted;
    }
    

    protected abstract void setFactorObject();

    @Override
    public String toString() {
        return "Pieza: " + description + 
             "\nMaterial: " + material.getMaterial();
    }
    
    
}
