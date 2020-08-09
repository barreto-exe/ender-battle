package game.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author luisb
 */
public class Sonido 
{
    public final static AssetManager soundManager;
    
    static
    {
        soundManager = new AssetManager();
        soundManager.load("sonidos/temaPrincipal.ogg", Sound.class);
        soundManager.load("sonidos/click.ogg", Sound.class);
        soundManager.load("sonidos/arbolhit.ogg", Sound.class);
        soundManager.load("sonidos/eat1.ogg", Sound.class);
        soundManager.load("sonidos/eat2.ogg", Sound.class);
        soundManager.load("sonidos/eat3.ogg", Sound.class);
        soundManager.load("sonidos/esmeralda.ogg", Sound.class);
        soundManager.load("sonidos/explosion.ogg", Sound.class);
        soundManager.load("sonidos/hurt1.ogg", Sound.class);
        soundManager.load("sonidos/hurt2.ogg", Sound.class);
        soundManager.load("sonidos/pick.ogg", Sound.class);
        soundManager.load("sonidos/psss.ogg", Sound.class);
        soundManager.load("sonidos/mobs/chicken.ogg", Sound.class);
        soundManager.load("sonidos/mobs/cow.ogg", Sound.class);
        soundManager.load("sonidos/mobs/creeper.ogg", Sound.class);
        soundManager.load("sonidos/mobs/enderman.ogg", Sound.class);
        soundManager.load("sonidos/mobs/pig.ogg", Sound.class);
        soundManager.load("sonidos/mobs/pigman.ogg", Sound.class);
        soundManager.load("sonidos/mobs/rabbit.ogg", Sound.class);
        soundManager.load("sonidos/mobs/sheep.ogg", Sound.class);
        soundManager.load("sonidos/mobs/skeleton.ogg", Sound.class);
        soundManager.load("sonidos/mobs/spider.ogg", Sound.class);
        soundManager.load("sonidos/mobs/zombie.ogg", Sound.class);
        soundManager.finishLoading();
    }
    
    public static void click()
    {
        soundManager.get("sonidos/click.ogg", Sound.class).play();
    }
}
