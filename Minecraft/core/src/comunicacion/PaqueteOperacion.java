package comunicacion;

import java.io.Serializable;

/**
 * Es una relación entre un String y un Objeto para comunicar al servidor y al
 * cliente.
 *
 * @author luisb
 */
public class PaqueteOperacion<E> implements Serializable
{
    private Operacion tipo;
    private E informacion;

    /**
     * Paquete de para realizar peticiones al servidor.
     * @param tipo de la operación o petición.
     * @param informacion relacionada a la operación.
     */
    public PaqueteOperacion(Operacion tipo, E informacion)
    {
        this.tipo = tipo;
        this.informacion = informacion;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Operacion getTipo()
    {
        return tipo;
    }
    
    public void setTipo(Operacion tipo)
    {
        this.tipo = tipo;
    }
    
    public E getInformacion()
    {
        return informacion;
    }
    
    public void setInformacion(E informacion)
    {
        this.informacion = informacion;
    }
    //</editor-fold>

    /**
     * Indica el tipo de operacion contra el servidor.
     */
    public enum Operacion implements Serializable
    {
        REGISTRAR,
        INICIAR_SESION,
        CREAR_PARTIDA,
        UNIRSE_PARTIDA,
        PEDIR_ESTADO_PARTIDA,
        PEDIR_PARTIDAS_ACTIVAS,
        SALIR_PARTIDA,
        COMENZAR_PARTIDA,
        REPORTE_PROGRESO
    }

    /**
     * Indica el resultado arrojado por el servidor luego de la operación.
     */
    public enum ResultadoOperacion implements Serializable
    {
        ERROR,
        
        //Respuestas para REGISTRAR
        CORREO_NO_DISPONIBLE,
        USUARIO_NO_DISPONIBLE,
        USUARIO_REGISTRADO,
        
        //Respuestas para INICIAR_SESION
        CREDENCIAL_INVALIDA,
        SESION_VALIDA,
        
        //Respuestas para CREAR_PARTIDA
        PARTIDA_CREADA,
        PARTIDA_YA_EXISTE,
        
        //Respuestas para UNIRSE_PARTIDA
        UNIDO_EXITOSAMENTE,
        PARTIDA_LLENA,
        
        //Respuesta para PEDIR_PARTIDAS_ACTIVAS
        PARTIDAS_ACTIVAS,
        
        //Respuesta para USUARIOS_ESTADO_PARTIDA
        USUARIOS_PARTIDA,
        PARTIDA_INICIADA,
        PARTIDA_TERMINADA,
        
        //Respuesta para SALIR_PARTIDA
        SALIR_PARTIDA_EXITOSO,
        
        //Respuesta para REPORTE_PROGRESO
        RESPUESTA_REPORTE,
    }

}
