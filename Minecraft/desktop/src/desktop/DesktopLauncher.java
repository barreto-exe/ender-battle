package desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import desktop.menu.FrmPrincipal;
import game.MainGame;
import game.tools.Reproductor;
import game.tools.Sonido;
import tools.Constant;
import basedatos.DBUsuario;
import java.io.File;
import javax.swing.JOptionPane;

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

    private static boolean juegoIniciado;

    public static void main(String[] arg)
    {
        colocarInterfaz();
        comenzarMusica();
        juegoIniciado = false;

        FrmPrincipal frm = new FrmPrincipal();
        frm.setVisible(true);
    }

    public static void comenzarJuego(DBUsuario usuario)
    {
        if (juegoIniciado)
        {
            return;
        }

        GameLauncher.setUsuario(usuario);
        juegoIniciado = true;

        //<editor-fold defaultstate="collapsed" desc="Lanzar GameLauncher">
        String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = (GameLauncher.class).getCanonicalName();
        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);
        try
        {
            Process process = builder.start();
            process.waitFor();
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //</editor-fold>
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
