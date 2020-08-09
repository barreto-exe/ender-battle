package game.inventario;

import com.badlogic.gdx.utils.Array;
import game.inventario.food.Food;
import game.tools.Constant;
import game.tools.Constant.Farming;

/**
 *
 * @author Karen
 */
public class Inventory
{

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    //Sección de Armamento
    private Array<BattleObject> espadas;
    private Array<BattleObject> hachas;
    private Array<BattleObject> picos;
    private Array<BattleObject> palas;

    //Sección de Protección
    private Array<BattleObject> botas;
    private Array<BattleObject> pechos;
    private Array<BattleObject> pantalones;
    private Array<BattleObject> cascos;

    //Sección de Alimentos
    private Food[] food;

    //Esmeraldas del jugador
    private int esmeraldas;
    //</editor-fold>

    /**
     * Es el inventario del jugador.
     */
    public Inventory()
    {
        espadas = new Array<>();
        hachas = new Array<>();
        picos = new Array<>();
        palas = new Array<>();
        botas = new Array<>();
        pechos = new Array<>();
        pantalones = new Array<>();
        cascos = new Array<>();

        food = new Food[9];

        //Curan 1/4 de corazón
        food[2] = new Food(Farming.APPLE, 2.5f);
        food[3] = new Food(Farming.PEAR, 2.5f);
        food[0] = new Food(Farming.CARROT, 2.5f);

        //Curan 1/2 corazón
        food[5] = new Food(Farming.BERRY, 5);
        food[1] = new Food(Farming.POTATO, 5);
        food[4] = new Food(Farming.WATERMELON, 5);

        //Curan 1 corazón
        food[8] = new Food(Farming.RABBIT, 10);
        food[6] = new Food(Farming.CHICKEN, 10);
        food[7] = new Food(Farming.BEEF, 10);
    }

    public Food[] getFood()
    {
        return food;
    }

    public void addEsmeraldas(int esmeraldas)
    {
        this.esmeraldas += esmeraldas;
    }
    
    public int getEsmeraldas()
    {
        return esmeraldas;
    }

    public void setEsmeraldas(int esmeraldas)
    {
        this.esmeraldas = esmeraldas;
    }
    
    public static int getIndex(Farming type)
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

    private Array<BattleObject> getArray(Constant.BattleObject object)
    {
        switch (object)
        {
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
        if (findBattleObject(object.getObject(), object.getMaterial().getMaterial()) != null)
        {
            return false;
        }
        
        Array<BattleObject> array = getArray(object.getObject());

        if (array == null)
        {
            return false;
        }

        array.add(object);
        return true;
    }

    public boolean removeBattleObject(BattleObject object)
    {
        BattleObject item = findBattleObject(object.getObject(), object.getMaterial().getMaterial());
        
        if (item == null)
        {
            return false;
        }

        Array<BattleObject> array = getArray(object.getObject());

        if (array == null)
        {
            return false;
        }
            
        array.removeValue(item, true);
        return true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add y Remove Food">
    public boolean addFood(Farming type)
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

    public boolean removeFood(Farming type)
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
    
    public BattleObject findBestBattleObject(Constant.BattleObject object)
    {
        Array<BattleObject> array = getArray(object);
        
        if (array.isEmpty())
        {
            return null;
        }
        
        BattleObject mayor = array.first();
        
        for (BattleObject o : array)
        {
            if (mayor.getFactorObject() > o.getFactorObject())
            {
                mayor = o;
            }
        }
        
        return mayor;
    }

    public BattleObject findBattleObject(Constant.BattleObject object, Constant.Material material)
    {
        Array<BattleObject> array = getArray(object);
        
        if (array.isEmpty())
        {
            return null;
        }
        for (BattleObject o : array)
        {
            if (o.getMaterial().getMaterial().equals(material))
            {
                return o;
            }
        }
        return null;
    }
}
