/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.screens.worlds;

import game.tools.Constant;

/**
 *
 * @author Karen
 */
public class Room {
    private Constant.Bosses boss;
    private String map;
    private final Constant.MapType type;

    public Room(int map, Constant.MapType type) {
        this.type = type;        
        
        switch (type)
        {
            case FIGHT:
                this.map = "sala_0" + map + ".tmx";

                switch (map)
                {
                    case 1:
                        boss = Constant.Bosses.CREEPER;
                        break;
                    case 2:
                        boss = Constant.Bosses.PIGMAN;
                        break;
                    case 3:
                        boss = Constant.Bosses.ENDERMAN;
                        break;
                    case 4:
                        boss = Constant.Bosses.SPIDER;
                        break;
                    case 5:
                        boss = Constant.Bosses.ZOMBIE;
                        break;
                    case 6:
                        boss = Constant.Bosses.SKELETON;
                        break;
                } 
                
                break;
                
            case BIOME:
                this.map = "bioma_0" + map + ".tmx";
                boss = null;
                break;
             
            case END:
                this.map = "sala_01.tmx";
                boss = Constant.Bosses.ENDERDRAGON;
                break;
        }
    }

    public Constant.Bosses getBoss() {
        return boss;
    }

    public String getMap() {
        return map;
    }
    
}
