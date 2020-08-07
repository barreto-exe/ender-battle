package game.tools;

/**
 *
 * @author Karen
 */
public class Constant
{

    public static final float PPM = 50;
    public static final int MAX_MAP = 6400;
    public static final int PLAYER_WIDTH = 512;  //width de la region de "Walking"
    public static final int PLAYER_HEIGHT = 128;  //height de la region de "Walking"
    public static final int IMPULSE_JUMP = 15;  
    public static final float SPEED_PLAYER = 3.8f;  
    public static final int FRAME_WIDTH = 855;  //width del frame 
    public static final int FRAME_HEIGHT = 500;  //height del frame  
    public static final int IMPULSE_ATTACK = 8;  //height del frame  

    public enum State
    {
        JUMPING, HITTING, WALKING_RIGHT, WALKING_LEFT, FALLING, STANDING, PICKING_UP
    };

    public enum Farming
    {
        APPLE, PEAR, BERRY, WATERMELON, CARROT, POTATO, CHICKEN, RABBIT, BEEF
    };

    public enum PlayerCondition
    {
        NORMAL,
        BURNED,
        ENTANGLED,
        POISONED,
    }
    
    public enum Protection{
        HELMET,CHESTPLATE,LEGGING,BOOTS 
    }
    
    public static final short GROUND_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short ESMERALD_BIT = 4;
    public static final short FOOD_BIT = 8;
    public static final short TREE_BIT = 16;
    public static final short MOB_BIT = 32;
    public static final short PLAYER_FEET_BIT = 64;
    public static final short MOB_SENSOR_BIT = 128;
    public static final short MOB_TOP_BIT = 256;
    public static final short ARROW_SENSOR_BIT = 512;
}
