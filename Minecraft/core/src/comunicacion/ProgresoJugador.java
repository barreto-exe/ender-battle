package comunicacion;

import basedatos.DBUsuario;
import java.io.Serializable;

/**
 * Agrupa la información del progreso del jugador en un objeto
 * para poder enviarlo o recibirlo del servidor.
 * @author luisb
 */
public class ProgresoJugador implements Serializable
{
    private int idPartida;
    private int idJugador;
    private int personajeSeleccionado;
    private int animalesMatados;
    private int monstruosMatados;
    private int jefesMatados;
    private int esmeraldasRecogidas;
    private int objetosRecogidos;
    private boolean ganoPartida;
    private boolean enPartida;
    private String nombreJugador;

    public ProgresoJugador(DBUsuario usuario)
    {
        idPartida = usuario.getPartida();
        idJugador = usuario.getId();
        personajeSeleccionado = usuario.getPersonajeSeleccionado();
        animalesMatados = 0;
        monstruosMatados = 0;
        jefesMatados = 0;
        esmeraldasRecogidas = 0;
        objetosRecogidos = 0;
        ganoPartida = false;
        enPartida = true;
    }

    public ProgresoJugador()
    {
        idPartida = idJugador = personajeSeleccionado = 0;
        animalesMatados = 0;
        monstruosMatados = 0;
        jefesMatados = 0;
        esmeraldasRecogidas = 0;
        objetosRecogidos = 0;
        ganoPartida = false;
        enPartida = true;
    }
    
    public ProgresoJugador(int idPartida, int idJugador, int personajeSeleccionado, int animalesMatados, int monstruosMatados, int jefesMatados, int esmeraldasRecogidas, int objetosRecogidos, boolean ganoPartida, boolean enPartida, String nombreJugador)
    {
        this.idPartida = idPartida;
        this.idJugador = idJugador;
        this.personajeSeleccionado = personajeSeleccionado;
        this.animalesMatados = animalesMatados;
        this.monstruosMatados = monstruosMatados;
        this.jefesMatados = jefesMatados;
        this.esmeraldasRecogidas = esmeraldasRecogidas;
        this.objetosRecogidos = objetosRecogidos;
        this.ganoPartida = ganoPartida;
        this.enPartida = enPartida;
        this.nombreJugador = nombreJugador;
    }

    public String getNombreJugador()
    {
        return nombreJugador;
    }

    public int getIdJugador()
    {
        return idJugador;
    }

    public int getIdPartida()
    {
        return idPartida;
    }

    public void setIdPartida(int idPartida)
    {
        this.idPartida = idPartida;
    }

    public int getPersonajeSeleccionado()
    {
        return personajeSeleccionado;
    }

    public void setPersonajeSeleccionado(int personajeSeleccionado)
    {
        this.personajeSeleccionado = personajeSeleccionado;
    }
    
    public String getPersonajeSeleccionadoString()
    {
        switch (personajeSeleccionado)
        {
            case 0:
            {
                return "normal";
            }
            case 1:
            {
                return "rojo";
            }
            case 2:
            {
                return "verde";
            }
            case 3:
            {
                return "amarillo";
            }
            case 4:
            {
                return "morado";
            }
            case 5:
            {
                return "gris";
            }
            default:
            {
                return "";
            }
        }
    }

    public int getAnimalesMatados()
    {
        return animalesMatados;
    }

    public void setAnimalesMatados(int animalesMatados)
    {
        this.animalesMatados = animalesMatados;
    }

    public int getMonstruosMatados()
    {
        return monstruosMatados;
    }

    public void setMonstruosMatados(int monstruosMatados)
    {
        this.monstruosMatados = monstruosMatados;
    }

    public int getJefesMatados()
    {
        return jefesMatados;
    }

    public void setJefesMatados(int jefesMatados)
    {
        this.jefesMatados = jefesMatados;
    }

    public int getEsmeraldasRecogidas()
    {
        return esmeraldasRecogidas;
    }

    public void setEsmeraldasRecogidas(int esmeraldasRecogidas)
    {
        this.esmeraldasRecogidas = esmeraldasRecogidas;
    }

    public int getObjetosRecogidos()
    {
        return objetosRecogidos;
    }

    public void setObjetosRecogidos(int objetosRecogidos)
    {
        this.objetosRecogidos = objetosRecogidos;
    }

    public boolean isGanoPartida()
    {
        return ganoPartida;
    }

    public void setGanoPartida(boolean ganoPartida)
    {
        this.ganoPartida = ganoPartida;
    }

    public boolean isEnPartida()
    {
        return enPartida;
    }

    public void setEnPartida(boolean enPartida)
    {
        this.enPartida = enPartida;
    }
 
    
}
