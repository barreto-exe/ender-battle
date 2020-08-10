package servidor;

import basedatos.DBPartida;
import basedatos.DBUsuario;
import comunicacion.PaqueteOperacion;
import comunicacion.PaqueteOperacion.Operacion;
import static comunicacion.PaqueteOperacion.Operacion.*;
import comunicacion.PaqueteOperacion.ResultadoOperacion;
import comunicacion.PaqueteResultado;
import comunicacion.ProgresoJugador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author luisb
 */
public class HiloPartidas implements Runnable
{
    private FrmPrincipal ventanaServer;

    public HiloPartidas(FrmPrincipal ventanaServer)
    {
        this.ventanaServer = ventanaServer;
    }
    
    
    @Override
    public void run()
    {
        System.out.println("Corriendo hilo server partidas");
        Socket socket = null;

        try
        {
            ServerSocket servidor = new ServerSocket(27014);
            boolean responderResultado;
            
            while (true)
            {
                ventanaServer.appendVentanaPartidas("Esperando socket \n");
                socket = servidor.accept();
                responderResultado = true;
                
                String ip = socket.getInetAddress().toString().replaceAll("/", "");
                
                ObjectInputStream input  = new ObjectInputStream(socket.getInputStream());
                PaqueteOperacion paquete = (PaqueteOperacion) input.readObject();
                Operacion operacion = paquete.getTipo();
                
                ventanaServer.appendVentanaPartidas("Operacion: " + operacion.toString() + "|"+ ip +"\n\n");
                
                PaqueteResultado resultado = new PaqueteResultado(ResultadoOperacion.ERROR);
                
                if(operacion == REPORTE_PROGRESO)
                {
                    final ProgresoJugador progresoEntrada = (ProgresoJugador) paquete.getInformacion();
                    
                    //Registrar el progreso reportado por el jugador
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            DBPartida.registarProgreso(progresoEntrada);
                        }
                    }).start();
                    
                    //Si salió de partida entonces no hacer más nada 
                    if(!progresoEntrada.isEnPartida())
                    {
                        responderResultado = false;
                    }
                    else
                    {
                        //Obtener progreso de los usuarios de la partida
                        ArrayList<ProgresoJugador> progresosSalida = DBPartida.progresoJugadores(progresoEntrada.getIdPartida());

                        resultado.setResultado(ResultadoOperacion.RESPUESTA_REPORTE);
                        resultado.setInformacion(progresosSalida);
                    }
                }
                
                //**************************************************************
                //Enviar respuesta al cliente
                if(responderResultado)
                {
                    try
                    {
                        ObjectOutputStream paqueteEnvio = new ObjectOutputStream(socket.getOutputStream());
                        paqueteEnvio.writeObject(resultado);
                    }
                    catch(IOException ex)
                    {
                        ventanaServer.appendVentanaPartidas(ex.getMessage() + "\n" + ex.getStackTrace().toString() + "\n");
                    }
                }
                socket.close();
            }

        } 
        catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            ventanaServer.appendVentanaPartidas(ex.getMessage() + "\n" + ex.getStackTrace().toString() + "\n");
        }
    }
    
    private void escribirDatosAsincrono(ProgresoJugador progreso)
    {
        
    }
}
