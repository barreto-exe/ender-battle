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
    
    public static final Sonido CLICK = new Sonido("click.mp3", 2);
    public static final Sonido TEMA_PRINCIPAL = new Sonido("temaPrincipal.mp3", 254);

    public static final Sonido ARBOLHIT  = new Sonido("arbolhit.mp3", 2);
    public static final Sonido EAT1      = new Sonido("eat1.mp3", 2);
    public static final Sonido EAT2      = new Sonido("eat2.mp3", 2);
    public static final Sonido EAT3      = new Sonido("eat3.mp3", 2);
    public static final Sonido PSSS      = new Sonido("psss.mp3", 2);
    public static final Sonido EXPLOSION = new Sonido("explosion.mp3", 2);
    public static final Sonido PICK      = new Sonido("pick.mp3", 2);
    public static final Sonido ESMERALDA = new Sonido("esmeralda.mp3", 2);
    public static final Sonido HURT1     = new Sonido("hurt1.mp3", 2);
    public static final Sonido HURT2     = new Sonido("hurt2.mp3", 2);
    
    public static final Sonido CHICKEN   = new Sonido("mobs/chicken.mp3", 2);
    public static final Sonido COW       = new Sonido("mobs/cow.mp3", 2);
    public static final Sonido CREEPER   = new Sonido("mobs/creeper.mp3", 2);
    public static final Sonido ENDERMAN  = new Sonido("mobs/enderman.mp3", 2);
    public static final Sonido PIG       = new Sonido("mobs/pig.mp3", 2);
    public static final Sonido PIGMAN    = new Sonido("mobs/pigman.mp3", 2);
    public static final Sonido RABBIT    = new Sonido("mobs/rabbit.mp3", 2);
    public static final Sonido SHEEP     = new Sonido("mobs/sheep.mp3", 2);
    public static final Sonido SKELETON  = new Sonido("mobs/skeleton.mp3", 2);
    public static final Sonido SPIDER    = new Sonido("mobs/spider.mp3", 2);
    public static final Sonido ZOMBIE    = new Sonido("mobs/zombie.mp3", 2);
    
    private String nombre;
    private int duracion;
    private boolean reproduciendo;
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
    
    public void reproducir()
    {
        new Thread(new Reproductor(this,false)).start();
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

    public boolean isReproduciendo()
    {
        return reproduciendo;
    }

    public void setReproduciendo(boolean reproduciendo)
    {
        this.reproduciendo = reproduciendo;
    }
    
}
