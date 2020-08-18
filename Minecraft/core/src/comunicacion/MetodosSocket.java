package comunicacion;

import basedatos.DBOperacion;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author luisb
 */
public class MetodosSocket
{
    /**
     * Envía información al servidor.
     * @param paquete es objeto que encapsula la información enviada.
     * @param ventanaOrigen es la ventana desde donde fue enviado el paquete
     * y que a su vez recibirá el paquete. Si es null, entonces no se recibirá una
     * respuesta del paquete.
     */
    public static void enviarPaquete(PaqueteOperacion paquete, UsesSocket ventanaOrigen)
    {
        try
        {
            int puerto;
            if(paquete.getTipo().equals(PaqueteOperacion.Operacion.REPORTE_PROGRESO))
            {
                puerto = 27014;
            }
            else
            {
                puerto = 27015;
            }
            
            //Enviar solicitud al server
            Socket socket = new Socket(DBOperacion.SERVIDOR, puerto);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(paquete);
            
            if(ventanaOrigen != null)
                ventanaOrigen.recibirRespuestaServer(socket, ventanaOrigen);
        } 
        catch (UnknownHostException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Host desconocido.", "Error", JOptionPane.ERROR_MESSAGE, null);
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "No hubo conexión con el servidor.", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }
    
    public interface UsesSocket
    {
        /**
         * Método encargado de recibir respuestas de los paquetes enviados a servidor.
         * @param socket el que se utilizó para enviar el paquete.
         * @param ventanaDelegada ventana encargada de recibir la respuesta.
         */
        public void recibirRespuestaServer(final Socket socket, final UsesSocket ventanaDelegada);
    }
    
    private MetodosSocket(){}
}
