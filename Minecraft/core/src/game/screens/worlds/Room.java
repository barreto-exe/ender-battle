/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens.worlds;

import game.tools.Constant;
import game.tools.Constant.Bosses;
import game.tools.Constant.MapType;

/**
 *
 * @author Karen
 */
public class Room {
    private Constant.Bosses boss;
    private String map;
    private int mapNum;
    private final Constant.MapType type;

    public Room(int map, MapType type) {
        this.type = type;  
        this.mapNum = map;
        
        switch (map)
        {
            case 1:
                boss = Bosses.CREEPER;
                break;
            case 2:
                boss = Bosses.ZOMBIE;
                break;
            case 3:
                boss = Bosses.ENDERMAN;
                break;
            case 4:
                boss = Bosses.PIGMAN;
                        break;
            case 5:
                boss = Bosses.SPIDER;
                        break;
            case 6:
                boss = Bosses.SKELETON;
                break;
        } 
        
        switch (type)
        {
            case FIGHT:
                this.map = "sala_0" + map + ".tmx";
                break;
                
            case BIOME:
                this.map = "bioma_0" + map + ".tmx";
                boss = null;
                break;
             
            case END:
                this.map = "sala_03.tmx";
                boss = Constant.Bosses.ENDERDRAGON;
                break;
        }
    }

    public MapType getType()
    {
        return type;
    }

    public int getMapNum()
    {
        return mapNum;
    }
    

    public Bosses getBoss() {
        return boss;
    }

    public String getMap() {
        return map;
    }

    @Override
    public String toString()
    {
        return "Room{" + "boss=" + boss + ", map=" + map + ", mapNum=" + mapNum + ", type=" + type + '}';
    }
}
