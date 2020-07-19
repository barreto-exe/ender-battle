/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.comunicacion;

import java.io.Serializable;

/**
 * Es una relación entre un String y un Objeto para comunicar al servidor y al
 * cliente.
 *
 * @author luisb
 */
public class PaqueteOperacion<E> implements Serializable
{

    /**
     * Indica el tipo de operacion contra el servidor.
     */
    public enum Operacion implements Serializable
    {
        REGISTRAR,
        INICIAR_SESION,
        CREAR_PARTIDA,
        
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
    }

    private Operacion tipo;
    private E informacion;

    public PaqueteOperacion(Operacion tipo, E informacion)
    {
        this.tipo = tipo;
        this.informacion = informacion;
    }

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

}
