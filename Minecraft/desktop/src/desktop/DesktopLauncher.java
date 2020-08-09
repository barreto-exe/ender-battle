package desktop;

import com.badlogic.gdx.audio.Sound;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import desktop.menu.FrmPrincipal;
import game.tools.Sonido;

public class DesktopLauncher
{
    public static Sound music;
    
    public static void main(String[] arg)
    {
        colocarInterfaz();

        FrmPrincipal frm = new FrmPrincipal();
        frm.setVisible(true);
        
        
        music = Sonido.soundManager.get("sonidos/temaPrincipal.ogg", Sound.class);
        music.setLooping(music.play(), true);
    }

    public static void colocarInterfaz()
    {
        try
        {
            //Adaptar interfaz de usuario al sistema operativo.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            //Informar error 
            System.out.println("No se puede establecer el aspecto" + ex);
        }

    }
}
