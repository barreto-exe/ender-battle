package comunicacion;

import java.io.Serializable;
import comunicacion.PaqueteOperacion.ResultadoOperacion;

/**
 *
 * @author luisb
 * @param <E>
 */
public class PaqueteResultado <E> implements Serializable
{
    private ResultadoOperacion resultado;
    private E informacion;

    /**
     * Paquete que envía el servidor como resultado de una operación. Dentro
     * contiene información que está en contexto con la respuesta.
     * @param resultado tipo de resultado.
     */
    public PaqueteResultado(ResultadoOperacion resultado)
    {
        this.resultado = resultado;
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
