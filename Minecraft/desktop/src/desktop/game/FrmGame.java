package desktop.game;

import basedatos.DBUsuario;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import comunicacion.MetodosSocket;
import comunicacion.MetodosSocket.UsesSocket;
import comunicacion.PaqueteOperacion;
import comunicacion.PaqueteOperacion.Operacion;
import comunicacion.PaqueteResultado;
import game.MainGame;
import game.tools.Constant;
import java.awt.Canvas;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author luisb
 */
public final class FrmGame extends JFrame implements UsesSocket
{
    private DBUsuario usuario;
    private final Canvas contenedorJuego;
    private final MainGame game;
    
    /**
     * Ventana que contiene al juego.
     * @param usuario contiene a los datos del usuario que está jugando
     */
    public FrmGame(DBUsuario usuario)
    {
        initComponents();
        this.usuario = usuario;

        final Container container = getContentPane();
        container.setLayout(new BorderLayout());

        //<editor-fold defaultstate="collapsed" desc="Lwjgl Config">
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = Constant.FRAME_HEIGHT;
        config.width = Constant.FRAME_WIDTH;
        //</editor-fold>

        game = new MainGame(usuario);
        game.setVentanaOrigen(this);

        //<editor-fold defaultstate="collapsed" desc="Añadir juego al jFrame">
        LwjglAWTCanvas canvas = new LwjglAWTCanvas(game, config);
        contenedorJuego = canvas.getCanvas();
        container.add(contenedorJuego, BorderLayout.CENTER);
        pack();
        //</editor-fold>
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        setPreferredSize(new java.awt.Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT));
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Sale de la partida al cerrar el juego
    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        if(usuario !=null) 
        {
            MetodosSocket.enviarPaquete
            (
                new PaqueteOperacion(Operacion.SALIR_PARTIDA, usuario),
                this
            );
            
            //Enviar abandonar partida aquí
            game.getProgreso().setEnPartida(false);
        }
    }//GEN-LAST:event_formWindowClosing

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public DBUsuario getUsuario()
    {
        return usuario;
    }
    public void setUsuario(DBUsuario usuario)
    {
        this.usuario = usuario;
    }
    //</editor-fold>
    
    /**
     * Hace visible la ventana de juego.
     */
    public void mostrar()
    {
        setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        this.requestFocus();
        contenedorJuego.requestFocus();
    }
    
    @Override
    public void recibirRespuestaServer(final Socket socket, final MetodosSocket.UsesSocket ventanaOrigen)
    {
        final JFrame formulario = this;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    //Leer respuesta del servidor
                    ObjectInputStream paqueteRecibido = new ObjectInputStream(socket.getInputStream());
                    PaqueteResultado resultado = (PaqueteResultado) paqueteRecibido.readObject();

                    //<editor-fold defaultstate="collapsed" desc="Resultados de salir de partida">
                    if (resultado.getResultado() == PaqueteOperacion.ResultadoOperacion.SALIR_PARTIDA_EXITOSO)
                    {
                        JOptionPane.showMessageDialog(null, "Has salido de la partida.");
                    }
                    //</editor-fold>

                    socket.close();
                } catch (IOException | ClassNotFoundException ex)
                {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(formulario, ex.getMessage());
                }
            }
        }
        ).start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
