package kdl.minecraft.desktop;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import kdl.minecraft.desktop.menu.FrmPrincipal;
import kdl.minecraft.recursos.Reproductor;
import kdl.minecraft.recursos.Sonido;


public class DesktopLauncher
{
    /**
     * Identificador del hilo donde se reproduce la música.
     */
    private static Thread hiloMusica;
    /**
     * Identificador del objeto reproductor de la música.
     */
    private static Reproductor reproductorTemaPrincipal;

    public static void main(String[] arg)
    {
        try
        {
            //Adaptar interfaz de usuario al sistema operativo.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex)
        {
            //Informar error 
            System.out.println("No se puede establecer el aspecto" + ex);
        }
        
        //LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //new LwjglApplication(new Juego(), config);
        
        FrmPrincipal frm = new FrmPrincipal();
        frm.setVisible(true);
        new Thread(frm).start();
        
        comenzarMusica();
    }

    /**
     * Reproduce el tema principal del menú.
     */
    public static void comenzarMusica()
    {
        reproductorTemaPrincipal = new Reproductor(Sonido.TEMA_PRINCIPAL, true);
        hiloMusica = new Thread(reproductorTemaPrincipal);
        hiloMusica.start();
    }

    /**
     * Detiene la reproducción del tema principal del menú.
     */
    public static void detenerMusica()
    {
        hiloMusica.interrupt();
        reproductorTemaPrincipal.detenerReproduccion();
    }

}
