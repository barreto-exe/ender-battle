package game.tools;

/**
 *
 * @author luisb
 */
public class Sonido
{
    /**
     * Carpeta en donde se encuentran los sonidos del proyecto.
     * Está relativa a la ubicación de assets.
     */
    private static final String CARPETA_SONIDOS = "/sonidos/";
    
    public  static final Sonido CLICK = new Sonido("click.mp3", 2);
    public  static final Sonido TEMA_PRINCIPAL = new Sonido("temaPrincipal.mp3", 254);
    
    private String nombre;
    private int duracion;
    /**
     * Objeto que relaciona un archivo de sonido con su duración.
     * @param nombre nombre del archivo y su extensión.
     * @param duracion es la duración de la reproducción del sonido en segudos. Si duración es 0,
     * el objeto <code>Reproductor</code> lo asumirá como loop.
     */
    private Sonido(String nombre, int duracion)
    {
        this.nombre    = nombre;
        this.duracion  = duracion;
    }

    /**
     * Reproduce un sonido a partir de la constante <code>CLICK</code>.
     * Es sabido que el sonido de click no se necesita controlar en una variable, 
     * así que esta función facilita el llamado del sonido.
     */
    public static void Click()
    {
        new Thread(new Reproductor(Sonido.CLICK, false)).start();
    }
    
    public String getUbicacionSonido()
    {
        return CARPETA_SONIDOS + nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getDuracion()
    {
        return duracion;
    }
}
