package basedatos;

import comunicacion.ProgresoJugador;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *Es una partida que un jugador puede hostear o a la que se puede unir.
 * 
 * @author luisb
 */
public class DBPartida implements Serializable
{
    private String nombre, descripcion;
    private EstadoPartida estado;
    private int limiteJugadores, cantidadJugadores;
    private int id;

    /**
     * Crea un nuevo objeto de partida.
     * @param nombre el nombre con el que se identificará la partida.
     * @param descripcion algún mensaje que el jugador desee que se vea en el lobby.
     * @param limiteJugadores es la cantidad de jugadores para la partida.
     */
    public DBPartida(String nombre, String descripcion, int limiteJugadores)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.limiteJugadores = limiteJugadores;
        this.estado = EstadoPartida.LOBBY;
    }

    public DBPartida(String nombre, String descripcion, EstadoPartida estado, int limiteJugadores, int cantidadJugadores, int id)
    {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.limiteJugadores = limiteJugadores;
        this.cantidadJugadores = cantidadJugadores;
        this.id = id;
    }

    public DBPartida()
    {
        this.nombre = "";
        this.id = -1;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public String getDescripcion()
    {
        return descripcion;
    }
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    
    public int getLimiteJugadores()
    {
        return limiteJugadores;
    }
    public void setLimiteJugadores(int limiteJugadores)
    {
        this.limiteJugadores = limiteJugadores;
    }
    
    public int getCantidadJugadores()
    {
        return cantidadJugadores;
    }
    
    public void setCantidadJugadores(int cantidadJugadores)
    {
        this.cantidadJugadores = cantidadJugadores;
    }
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    
    public EstadoPartida getEstado()
    {
        return estado;
    }
    public void setEstado(EstadoPartida estado)
    {
        this.estado = estado;
    }
    //</editor-fold>
    
    
    /**
     * Registra una partida en la base de datos.
     * 
     * @param partida es la partida a crear
     * @return <code>True</code> si la partida fue creada, o <code>False</code>
     * si hubo algún error.
     */
    public static boolean crearPartida(DBPartida partida) 
    {
        //Verifica que no haya partida con el mismo nombre abierta
        if(idPartida(partida) != -1)
        {
            //Existe, así que no se puede crear la partida
            return false;
        }
        
        String query
            = "INSERT INTO `m_partidas` (nombre,descripcion,estado,limiteJugadores)"
            + "VALUES (?,?,?,?)";
        
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(partida.getNombre());
        operacion.pasarParametro(partida.getDescripcion());
        operacion.pasarParametro(partida.getEstado().getValue());
        operacion.pasarParametro(partida.getLimiteJugadores());

        //Retornar la validación del registro afectado.
        return operacion.ejecutar() == 1;
    }
    
    /**
     * Consulta el id de una partida.
     * 
     * @param partida es la partida a consultar.
     * @return el id de la partida si existe una con el mismo nombre 
     * y está en estado <code>LOBBY</code> o <code>JUGANDO</code>. Si no, retorna -1.
     */
    public static int idPartida(DBPartida partida) 
    {        
        String query = 
                "SELECT id FROM m_partidas WHERE "
                + "nombre = ? AND "
                + "(estado = 1 OR estado = 2)";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(partida.getNombre());
        
        DBMatriz resultado = operacion.consultar();
        
        if(resultado.leer())
        {
            return (int)resultado.getValor("id");
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Devuelve el id de la útlima partida creada activa.
     * 
     * @return El id de la última partida creada activa. Retorna -1 si no hay ninguna.
     */
    public static int idUltimaPartida()
    {
        String query = 
                "SELECT id FROM m_partidas "
                + "ORDER BY id DESC LIMIT 1";
        DBOperacion op = new DBOperacion(query);
        
        DBMatriz resultado = op.consultar();

        //Si existe una partida reciente y abierta
        if(resultado.leer())
        {
            return (int)resultado.getValor("id");
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Agrega un jugador a la partida.
     * @param usuario contine datos del usuario y el id de la partida a la que
     * se quiere unir.
     * @param ip del usuario.
     * @return true si se pudo unir a la partida.
     */
    public static boolean agregarJugador(DBUsuario usuario, String ip) 
    {
        String query = 
                "SELECT "
                + "(SELECT limiteJugadores FROM m_partidas WHERE id = ?) - "
                + "(SELECT COUNT(*) FROM m_partidas_jugadores WHERE id_partida = ?) "
                + "AS EspaciosDisponibles,"
                + "(SELECT estado FROM m_partidas WHERE id = ?) as Estado";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(usuario.getPartida());
        operacion.pasarParametro(usuario.getPartida());
        operacion.pasarParametro(usuario.getPartida());
        
        DBMatriz resultado    = operacion.consultar();

        resultado.leer();

        try
        {
            //Si hay espacios disponibles en la partida, se registra al jugador
            if((int)resultado.getValor("EspaciosDisponibles") > 0 && (int)resultado.getValor("Estado") == 1)
            {
                query = 
                        "INSERT INTO m_partidas_jugadores (id_partida,personajeSeleccionado,id_jugador,ip) "
                        + "VALUES (?,?,?,?)";
                operacion = new DBOperacion(query);
                operacion.pasarParametro(usuario.getPartida());
                operacion.pasarParametro(usuario.getPersonajeSeleccionado());
                operacion.pasarParametro(DBUsuario.idUsuario(usuario.getUsuario()));
                operacion.pasarParametro(ip);

                return operacion.ejecutar() == 1;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }

    }
    
    /**
     * Eliminar a un jugador de su partida. En caso de que la partida quede vacía, la
     * elimina.
     * @param usuario es el usuario que dejará de pertenecer a su partida.
     */
    public static void sacarJugador(DBUsuario usuario)
    {
        //Eliminar al jugador
        String query =
                "DELETE FROM m_partidas_jugadores WHERE "
                + "id_jugador = ? AND "
                + "id_partida = ?; ";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(usuario.getId());
        operacion.pasarParametro(usuario.getPartida());
        operacion.ejecutar();
        
        //Eliminar partidas vacías
        query =
                "DELETE FROM m_partidas WHERE "
                + "(SELECT COUNT(*) FROM m_partidas_jugadores j WHERE j.id_partida = m_partidas.id) = 0";
        operacion = new DBOperacion(query);
        operacion.ejecutar();
    }
    
    /**
     * Setear a la partida como en juego.
     * @param idPartida es el id de la partida que comenzará.
     * @return true si se colocó como comenzada.
     */
    public static boolean comenzarPartida(int idPartida)
    {
        String query =
                "UPDATE m_partidas SET estado = 2  WHERE id = ?";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(idPartida);
        
        return operacion.ejecutar() > 0;
    }
    
    /**
     * Consulta el estado de la partida.
     * @param idPartida id de la partida a consultar.
     * @return el estado de la partida.
     */
    public static EstadoPartida obtenerEstadoPartida(int idPartida)
    {
        String query = 
                "SELECT estado FROM m_partidas WHERE id = ?";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(idPartida);
        
        DBMatriz resultado = operacion.consultar();
        
        if(resultado.leer())
        {
            int estado = (int)resultado.getValor("estado");
            switch(estado)
            {
                case 1:
                    return EstadoPartida.LOBBY;
                case 2:
                    return EstadoPartida.JUGANDO;
                case 3:
                    return EstadoPartida.TERMINADA;
            }
        }
        return EstadoPartida.TERMINADA;
    }
    
    /**
     * Consultar los usuarios que pertenecen a una determinada partida.
     * @param idPartida es el id de la partida que se quiere consultar.
     * @return arraylist con las instancias de los usuarios que pertenecen a la partida.
     */
    public static ArrayList<DBUsuario> usuariosPartida(int idPartida)
    {
        ArrayList<DBUsuario> usuarios = new ArrayList<>();
        String query = 
                "SELECT id_jugador as id, ( SELECT u.usuario FROM m_usuarios u WHERE u.id = id_jugador ) AS usuario, personajeSeleccionado, ip \n" +
                "FROM m_partidas_jugadores WHERE id_partida = ?";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(idPartida);
        
        DBMatriz resultado = operacion.consultar();
        
        while(resultado.leer())
        {
            usuarios.add(new DBUsuario(
                    (String) resultado.getValor("usuario"), 
                    (String) resultado.getValor("ip"),
                    (int) resultado.getValor("id"),
                    (int) resultado.getValor("personajeSeleccionado")
            ));
        }
        
        return usuarios;
    }
    
    /**
     * Consultar el progresos de los jugadores de una partida indicada.
     * @param idPartida es el id de la partida que se desea consultar.
     * @return arraylist con el progreso de cada jugador perteneciente a la partida.
     */
    public static ArrayList<ProgresoJugador> progresoJugadores(int idPartida)
    {
        ArrayList<ProgresoJugador> progresos = new ArrayList<>();
        
        for(DBUsuario usuario : usuariosPartida(idPartida))
        {
            usuario.setPartida(idPartida);
            progresos.add(obtenerProgreso(usuario));
        }
        
        return progresos;
    }
    
    /**
     * Consulta el progreso de un usuario en la partida a la que pertenece.
     * @param usuario es la instancia del usuario por cuyo progreso que se va a consultar.
     * @return el progreso del jugador en la partida a la que pertenece.
     */
    public static ProgresoJugador obtenerProgreso(DBUsuario usuario)
    {
        String query =
                "SELECT *, (SELECT usuario FROM m_usuarios WHERE id = ?) as nombreJugador "
                + "FROM m_partidas_progreso WHERE "
                + "idpartida = ? AND idjugador = ?";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(usuario.getId());
        operacion.pasarParametro(usuario.getPartida());
        operacion.pasarParametro(usuario.getId());
        
        DBMatriz resultado = operacion.consultar();
        
        if(resultado.leer())
        {
            int partidaGanada  = (int)resultado.getValor("partidaGanada");
            int partidaEnCurso = (int)resultado.getValor("partidaEnCurso");
            
            return new ProgresoJugador
                (
                    (int)resultado.getValor("idpartida"),
                    (int)resultado.getValor("idjugador"),
                    (int)resultado.getValor("personajeSeleccionado"),
                    (int)resultado.getValor("animalesMatados"),
                    (int)resultado.getValor("monstruosMatados"),
                    (int)resultado.getValor("jefesMatados"),
                    (int)resultado.getValor("esmeraldasRecogidas"),
                    (int)resultado.getValor("objetosRecogidos"),
                    partidaGanada == 1,
                    partidaEnCurso == 1,
                    (String)resultado.getValor("nombreJugador")
                );
        }
        
        return null;
    }
    
    /**
     * Registra una instancia del progreso de un jugador en la base de datos.
     * @param progreso es el progreso a registrar.
     */
    public static void registarProgreso(ProgresoJugador progreso)
    {
        String query = 
                "DELETE FROM m_partidas_progreso WHERE "
                + "idpartida = ? AND idjugador = ?";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(progreso.getIdPartida());
        operacion.pasarParametro(progreso.getIdJugador());
        
        operacion.ejecutar();
        
        query = 
                "INSERT INTO m_partidas_progreso VALUES "
                + "(?,?,?,?,?,?,?,?,?,?)";
        operacion = new DBOperacion(query);
        operacion.pasarParametro(progreso.getIdPartida());
        operacion.pasarParametro(progreso.getIdJugador());
        operacion.pasarParametro(progreso.getPersonajeSeleccionado());
        operacion.pasarParametro(progreso.getAnimalesMatados());
        operacion.pasarParametro(progreso.getMonstruosMatados());
        operacion.pasarParametro(progreso.getJefesMatados());
        operacion.pasarParametro(progreso.getEsmeraldasRecogidas());
        operacion.pasarParametro(progreso.getObjetosRecogidos());
        operacion.pasarParametro(progreso.isGanoPartida());
        operacion.pasarParametro(progreso.isEnPartida());
        operacion.ejecutar();
    }
    
    /**
     * Consulta la cantidad de usuarios en la partida.
     * @param idPartida es el id de la partida a consultar.
     * @return el número de jugadores en la partida. -1 si no existe la partida.
     */
    public static int cantidadUsuariosPartida(int idPartida)
    {
        String query = 
                "SELECT COUNT(*) as cantidadJugadores \n" +
                "FROM m_partidas_jugadores WHERE id_partida = ?";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(idPartida);
        
        DBMatriz resultado = operacion.consultar();
        
        if(resultado.leer())
        {
            return (int)resultado.getValor("cantidadJugadores");
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Consulta las partidas activas a las que un jugador se puede unir.
     * @return un arraylist con los datos de las partiads disponibles.
     */
    public static ArrayList<DBPartida> partidasActivas()
    {
        ArrayList<DBPartida> partidas = new ArrayList<>();
        
        String query = "SELECT * FROM m_partidas WHERE estado < 3";
        DBOperacion operacion = new DBOperacion(query);

        DBMatriz resultado = operacion.consultar();

        String nombre, descripcion;
        EstadoPartida estado = EstadoPartida.LOBBY;
        int id, limiteJugadores, cantidadJugadores, estadoInt;
        
        while(resultado.leer())
        {
            nombre = (String) resultado.getValor("nombre");
            descripcion = (String) resultado.getValor("descripcion");
            estadoInt = (int) resultado.getValor("estado");
            switch(estadoInt)
            {
                case 1:
                {
                    estado = EstadoPartida.LOBBY;
                    break;
                }
                case 2:
                {
                    estado = EstadoPartida.JUGANDO;
                    break;
                }
            }
            
            id = (int) resultado.getValor("id");
            limiteJugadores = (int) resultado.getValor("limiteJugadores");
            cantidadJugadores = DBPartida.cantidadUsuariosPartida(id);
            
            partidas.add(new DBPartida(nombre,descripcion,estado,limiteJugadores,cantidadJugadores,id));
        }
         
        return partidas;
    }
    
    public enum EstadoPartida implements Serializable
    {
        LOBBY(1),
        JUGANDO(2),
        TERMINADA(3);
        
        private final int value;
        private EstadoPartida(int value)
        {
            this.value = value;
        }
        public int getValue()
        {
            return value;
        }
    }
}
