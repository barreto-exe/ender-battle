package game.tools;

/**
 * Clase contenedora de constantes del programa.
 * 
 * @author Karen
 */
public class Constant
{
    
    public static final float PPM = 50;
    public static final int MAX_MAP = 6400;
    public static final int PLAYER_WIDTH = 512;  
    public static final int PLAYER_HEIGHT = 128;  
    public static final int IMPULSE_JUMP = 15;  
    public static final float SPEED_PLAYER = 3.8f;  
    public static final int FRAME_WIDTH = 855;  
    public static final int FRAME_HEIGHT = 500;  
    public static final int IMPULSE_ATTACK = 8;   

    public enum State
    {
        JUMPING, HITTING, WALKING_RIGHT, WALKING_LEFT, FALLING, STANDING, PICKING_UP
    }

    public enum Farming
    {
        APPLE, PEAR, BERRY, WATERMELON, CARROT, POTATO, CHICKEN, RABBIT, BEEF;
        
        public static Farming getEnumByDesc(String desc)
        {
            switch(desc)
            {
                case "manzana":
                    return APPLE;
                case "pera":
                    return PEAR;
                case "baya":
                    return BERRY;
                case "sandia":
                    return WATERMELON;
                case "zanahoria":
                    return CARROT;
                case "papa":
                    return POTATO;
                case "pollo":
                    return CHICKEN;
                case "conejo":
                    return RABBIT;
                case "carne":
                    return BEEF;
            }
            return null;
        }
        
        public static String getDescByEnum(Farming type)
        {
            switch(type)
            {
                case APPLE:
                    return "manzana";
                case PEAR:
                    return "pera";
                case BERRY:
                    return "baya";
                case WATERMELON:
                    return "sandia";
                case CARROT:
                    return "zanahoria";
                case POTATO:
                    return "papa";
                case CHICKEN:
                    return "pollo";
                case RABBIT:
                    return "conejo";
                case BEEF:
                    return "carne";
            }
            return null;
        }
    };

    public enum PlayerCondition
    {
        NORMAL, BURNED, ENTANGLED, POISONED
    }
    
    public enum BattleObjectEnum
    {
        HELMET,SHIRTFRONT,LEGGING,BOOTS, AX, SWORD, SHOVEL, PICK; 
        
        public static BattleObjectEnum getEnumByDesc(String desc)
        {
            switch(desc)
            {
                case "casco":
                    return HELMET;
                case "pechera":
                    return SHIRTFRONT;
                case "pantalones":
                    return LEGGING;
                case "botas":
                    return BOOTS;
                case "hacha":
                    return AX;
                case "espada":
                    return SWORD;
                case "pala":
                    return SHOVEL;
                case "pico":
                    return PICK;
            }
            return null;
        }
        
        public static int getIntByEnum(BattleObjectEnum battleObject)
        {
            switch (battleObject)
            {
                case SWORD:
                case AX:
                case PICK:
                case SHOVEL:
                    return 0;
                case HELMET:
                    return 1;
                case SHIRTFRONT:
                    return 2;
                case LEGGING:
                    return 3;
                case BOOTS:
                    return 4;
            }
            
            return -1;
        }
    }
    
    public enum Material
    {
        WOOD, GOLD, IRON, DIAMOND;
        
        public static int getIntByEnum(Material material)
        {
            switch(material)
            {
                case WOOD:
                    return 1;
                case GOLD:
                    return 2;
                case IRON:
                    return 3;
                case DIAMOND:
                    return 4;
            }
            return 0;
        }
    }
    
    
    public static final short GROUND_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short VILLAGER_BIT = 4;
    public static final short OBJECT_BIT = 8;
    public static final short TREE_BIT = 16;
    public static final short MOB_BIT = 32;
    public static final short PLAYER_FEET_BIT = 64;
    public static final short MOB_SENSOR_BIT = 128;
    public static final short MOB_TOP_BIT = 256;
    public static final short ARROW_SENSOR_BIT = 512;
}
