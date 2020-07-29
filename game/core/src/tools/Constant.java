/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Karen
 */
public class Constant {
    public static final float PPM = 50;
    public static final int MAX_MAP = 4480;
    public static final int PLAYER_WIDTH = 512;  //width de la region de "Walking"
    public static final int PLAYER_HEIGHT = 128;  //height de la region de "Walking"
    public static final int IMPULSE_JUMP = 14;  //height de la region de "Walking"
    public static final float SPEED_PLAYER = 3.8f;  //height de la region de "Walking"
    public static final int FRAME_WIDTH = 855;  //width del frame 
    public static final int FRAME_HEIGHT = 500;  //height del frame  
    
    public enum State{JUMPING, HITTING, WALKING_RIGHT, WALKING_LEFT, FALLING, STANDING} ;
    
    public static final short GROUND_BIT = 1;
    public static final short PLAYER_BIT = 2;
    public static final short ESMERALD_BIT = 4;
    public static final short FRUIT_BIT = 8;
    public static final short MOB_FEET_BIT = 16;
    public static final short MOB_BIT = 32;
    public static final short PLAYER_FEET_BIT = 64;
    public static final short MOB_SENSOR_BIT = 128;
}
