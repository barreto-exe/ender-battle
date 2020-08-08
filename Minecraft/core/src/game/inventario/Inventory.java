package game.inventario;

import com.badlogic.gdx.utils.Array;
import game.inventario.food.Food;
import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Inventory {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private Array <BattleObject> espadas;
    private Array <BattleObject> hachas;
    private Array <BattleObject> picos;
    private Array <BattleObject> palas;
    private Array <BattleObject> botas;
    private Array <BattleObject> pechos;
    private Array <BattleObject> pantalones;
    private Array <BattleObject> cascos;
    private Food[] food; 
    //</editor-fold>
    
    public Inventory() {
        espadas = new Array<>();
        hachas = new Array<>();
        picos = new Array<>();
        palas = new Array<>();
        botas = new Array<>();
        pechos = new Array<>();
        pantalones = new Array<>();
        cascos = new Array<>();
        food = new Food[9];
        
        food[0] = new Food(Constant.Farming.CARROT, 2);
        food[1] = new Food(Constant.Farming.POTATO, 2);
        food[2] = new Food(Constant.Farming.APPLE, 2);
        food[3] = new Food(Constant.Farming.PEAR, 2);
        food[4] = new Food(Constant.Farming.WATERMELON, 3);
        food[5] = new Food(Constant.Farming.BERRY, 1);
        food[6] = new Food(Constant.Farming.CHICKEN, 4);
        food[7] = new Food(Constant.Farming.BEEF, 5);
        food[8] = new Food(Constant.Farming.RABBIT, 3);
    }
    
    private int getIndex(Constant.Farming type)
    {
        switch (type)
        {
            case CARROT:
                return 0;
            case POTATO:
                return 1;
            case APPLE:
                return 2;
            case PEAR:
                return 3;
            case WATERMELON:
                return 4;
            case BERRY:
                return 5;
            case CHICKEN:
                return 6;
            case BEEF:
                return 7;
            case RABBIT:
                return 8;
            default:
                return -1;
        }
    }
    
    private Array <BattleObject> getArray (Constant.BattleObject object)
    {
        switch (object){
            case SWORD:
                return espadas;
            case AX:
                return hachas;
            case PICK:
                return picos;
            case SHOVEL:
                return palas;
            case BOOTS:
                return botas;
            case SHIRTFRONT:
                return pechos;
            case HELMET:
                return cascos;
            case LEGGING:
                return pantalones;
            default:
                return null;
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Add y Remove BattleObject">
    public boolean addBattleObject(BattleObject object)
    {
        Array <BattleObject> array = getArray(object.getObject());
        
        if (array == null)
        {
            return false;
        }
        
        if (findBattleObject(array, object.getMaterial().getMaterial()) != null)
        {
            return false;
        }
        
        array.add(object);
        return true;
    }
    
    public boolean removeBattleObject(BattleObject object)
    {
        Array <BattleObject> array = getArray(object.getObject());
        
        if (array == null)
        {
            return false;
        }
        
        BattleObject item = findBattleObject(array, object.getMaterial().getMaterial());
        
        if (item != null)
        {
            array.removeValue(item, true);
            return true;
        }
        System.out.println("item no encontrado");
        return false;
    }
    //</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="Add y Remove Food">
    
    public boolean addFood(Constant.Farming type)
    {
        int index = getIndex(type);
        
        if (index >= 0)
        {
            food[index].setCant(food[index].getCant() + 1);
            System.out.println("añadido al inventario objeto indice: " + index);
            return true;
        }
                
        return false;
    }
    
    public boolean removeFood(Constant.Farming type)
    {
        int index = getIndex(type);        
        
        if (index >= 0)
        {
            if (food[index].getCant() > 0)
            {
                food[index].setCant(food[index].getCant() - 1);
                return true;
            }
        }
        
        return false;
    }
    //</editor-fold>
    
    private BattleObject findBattleObject(Array <BattleObject> array, Constant.Material material)
    {
        for (BattleObject object : array) {
            if (object.getMaterial().getMaterial().equals(material))
            {
                return object;
            }
        }
        return null;
    }
}
