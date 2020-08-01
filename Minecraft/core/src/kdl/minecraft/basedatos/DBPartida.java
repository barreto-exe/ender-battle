/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.basedatos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *Es una partida que un jugador puede hostear o a la que se puede unir.
 * 
 * @author luisb
 */
public class DBPartida implements Serializable
{
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
    
    
    public static boolean agregarJugador(DBUsuario usuario, String ip) 
    {
        String query = 
                "SELECT "
                + "(SELECT limiteJugadores FROM m_partidas WHERE id = ?) - "
                + "(SELECT COUNT(*) FROM m_partidas_jugadores WHERE id_partida = ?) "
                + "AS EspaciosDisponibles";
        DBOperacion operacion = new DBOperacion(query);
        operacion.pasarParametro(usuario.getPartida());
        operacion.pasarParametro(usuario.getPartida());
        
        DBMatriz resultado    = operacion.consultar();

        resultado.leer();

        try
        {
            //Si hay espacios disponibles en la partida, se registra al jugador
            if((int)resultado.getValor("EspaciosDisponibles") > 0)
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
}
