/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minecraft.comunicacion;

import java.io.Serializable;
import minecraft.comunicacion.PaqueteOperacion.ResultadoOperacion;

/**
 *
 * @author luisb
 */
public class PaqueteResultado <E> implements Serializable
{
    private ResultadoOperacion resultado;
    private E informacion;

    public PaqueteResultado(ResultadoOperacion resultado)
    {
        this.resultado = resultado;
    }

    public PaqueteResultado(int ERROR)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultadoOperacion getResultado()
    {
        return resultado;
    }

    public void setResultado(ResultadoOperacion resultado)
    {
        this.resultado = resultado;
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
