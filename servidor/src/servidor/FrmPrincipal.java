package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import basedatos.*;
import basedatos.DBPartida.*;
import static basedatos.DBPartida.obtenerEstadoPartida;
import comunicacion.*;
import comunicacion.PaqueteOperacion.*;
import static comunicacion.PaqueteOperacion.Operacion.*;
import static comunicacion.PaqueteOperacion.ResultadoOperacion.*;
import java.util.HashMap;

/**
 *
 * @author luisb
 */
public class FrmPrincipal extends javax.swing.JFrame implements Runnable
{
    public FrmPrincipal()
    {
        super("Servidor");
        initComponents();

        new Thread(this).start();
        new Thread(new HiloPartidas(this)).start();
    }

    
    @Override
    public void run()
    {
        System.out.println("Corriendo hilo server");
        Socket socket = null;

        try
        {
            ServerSocket servidor = new ServerSocket(27015);
            boolean responderResultado;
            
            while (true)
            {
                txtPrincipal.append("Esperando socket \n");
                socket = servidor.accept();
                responderResultado = true;
                
                String ip = socket.getInetAddress().toString().replaceAll("/", "");
                
                ObjectInputStream input  = new ObjectInputStream(socket.getInputStream());
                PaqueteOperacion paquete = (PaqueteOperacion) input.readObject();
                Operacion operacion = paquete.getTipo();
                
                txtPrincipal.append("Operacion: " + operacion.toString() + "|"+ ip +"\n\n");
                
                PaqueteResultado resultado = new PaqueteResultado(ResultadoOperacion.ERROR);
                
                //<editor-fold defaultstate="collapsed" desc="Registro/Inicio de sesión">
                if (operacion == INICIAR_SESION || operacion == REGISTRAR)
                {
                    DBUsuario usuarioObj = (DBUsuario) paquete.getInformacion();

                    String add = 
                            "Correo:"   + usuarioObj.getCorreo()  + "\n" +
                            "Usuario: " + usuarioObj.getUsuario() + "\n" +
                            "Pass: "    + usuarioObj.getPass()    + "\n" +
                            ip + "\n";

                    if (operacion == REGISTRAR)
                    {
                        txtRegistro.append("\n Registrar usuario: \n" + add);

                        //Verificar existencia de correo
                        if (DBUsuario.idCorreo(usuarioObj.getCorreo()) != -1)
                        {
                            txtRegistro.append("Correo no disponible. \n");
                            resultado.setResultado(CORREO_NO_DISPONIBLE);
                        }
                        //Verificar existencia de usuario
                        else if (DBUsuario.idUsuario(usuarioObj.getUsuario()) != -1)
                        {
                            txtRegistro.append("Usuario no disponible. \n");
                            resultado.setResultado(USUARIO_NO_DISPONIBLE);

                        }
                        //Si el usuario y el correo están disponibles
                        else
                        {
                            if (DBUsuario.registrarUsuario(usuarioObj))
                            {
                                txtRegistro.append("Usuario registrado exitosamente. \n");
                                resultado.setResultado(USUARIO_REGISTRADO);
                            }
                            else
                            {
                                txtRegistro.append("Hubo un error al registrar el usuario. \n");
                                resultado.setResultado(ResultadoOperacion.ERROR);
                            }
                        }
                    }

                    if (operacion == INICIAR_SESION)
                    {
                        txtInicioSesion.append("\n Iniciar sesión usuario: \n" + add);
                        int idUsuario = DBUsuario.consultarUsuario(usuarioObj);
                        
                        //Verificar datos de usuario
                        if (idUsuario == -1)
                        {
                            txtInicioSesion.append("Credencial de usuario inválida. \n");
                            resultado.setResultado(CREDENCIAL_INVALIDA);
                        }
                        else
                        {
                            txtInicioSesion.append("Inicio de sesión válido. \n");
                            resultado.setResultado(SESION_VALIDA);
                            resultado.setInformacion(idUsuario);
                        }
                    }
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Crear Partida">
                if (operacion == CREAR_PARTIDA)
                {
                    DBPartida partida = (DBPartida) paquete.getInformacion();
                    
                    String add = 
                        "Nombre Partida: " + partida.getNombre()            + "\n" +
                        "Descripcion:"     + partida.getDescripcion()       + "\n" +
                        "Cantidad Jug.:"   + partida.getLimiteJugadores() + "\n" +
                        ip + "\n";
                    
                    txtPartidas.append(add + "Crear partida \n\n");
                    
                    if(DBPartida.crearPartida(partida))
                    {
                        resultado.setResultado(ResultadoOperacion.PARTIDA_CREADA);
                        resultado.setInformacion(DBPartida.idUltimaPartida());
                    }
                    else
                    {
                        resultado.setResultado(ResultadoOperacion.PARTIDA_YA_EXISTE);
                    }
                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Unirse a partida">
                if(operacion == UNIRSE_PARTIDA)
                {
                    DBUsuario usuario = (DBUsuario) paquete.getInformacion();
                    
                    String add = 
                            "Nombre: "     + usuario.getUsuario()  + "\n" +
                            "Personaje: "  + usuario.getPersonajeSeleccionado() + "\n" +
                            "Partida: "    + usuario.getPartida() + "\n";
                    
                    txtPartidas.append(add + "Unirse a partida \n\n");
                    
                    if(DBPartida.agregarJugador(usuario, ip))
                    {
                        resultado.setResultado(ResultadoOperacion.UNIDO_EXITOSAMENTE);
                        resultado.setInformacion(DBPartida.usuariosPartida(usuario.getPartida()));
                    }
                    else
                    {
                        resultado.setResultado(ResultadoOperacion.PARTIDA_LLENA);
                    }
                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Salir de Partida">
                if(operacion == SALIR_PARTIDA)
                {
                    DBUsuario usuario = (DBUsuario) paquete.getInformacion();

                    String add =
                            "Nombre: "     + usuario.getUsuario()  + "\n" +
                            "Personaje: "  + usuario.getPersonajeSeleccionadoString() + "\n" +
                            "Partida: "    + usuario.getPartida() + "\n";

                    txtPartidas.append(add + "Salir de partida \n\n");

                    resultado.setResultado(SALIR_PARTIDA_EXITOSO);
                    DBPartida.sacarJugador(usuario);
                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Actualizar usuarios y estado de partida">
                //Si se pide enviar la lista de jugadores
                if(operacion == PEDIR_ESTADO_PARTIDA)
                {
                    int partida = ((DBPartida) paquete.getInformacion()).getId();
                    EstadoPartida estado = obtenerEstadoPartida(partida);
                    
                    switch (estado)
                    {
                        case LOBBY:
                        {
                            resultado.setResultado(USUARIOS_PARTIDA);
                            resultado.setInformacion(DBPartida.usuariosPartida(partida));
                            break;
                        }
                        case JUGANDO:
                        {
                            resultado.setResultado(PARTIDA_INICIADA);
                            break;
                        }
                        case TERMINADA:
                        {
                            resultado.setResultado(PARTIDA_TERMINADA);
                            break;
                        }
                    }
                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Actualizar partidas activas">
                if(operacion == PEDIR_PARTIDAS_ACTIVAS)
                {
                    resultado.setResultado(PARTIDAS_ACTIVAS);
                    resultado.setInformacion(DBPartida.partidasActivas());
                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Comenzar la partida">
                if(operacion == COMENZAR_PARTIDA)
                {
                    int partida = ((DBPartida) paquete.getInformacion()).getId();

                    DBPartida.comenzarPartida(partida);

                    responderResultado = false;
                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Pedir Estadísticas del Usuario">
                if(operacion == ESTADISTICAS)
                {
                    String usuario = (String) paquete.getInformacion();

                    HashMap<String,String> estadisticas = DBUsuario.estadisticasUsuario(usuario);
                    resultado.setInformacion(estadisticas);

                    if(estadisticas != null)
                    {
                        resultado.setResultado(RESPUESTA_ESTADISTICAS);
                    }
                    else
                    {
                        resultado.setResultado(NO_HAY_ESTADISTICAS);
                    }
                }
                //</editor-fold>
                
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
                        System.out.println(ex.getMessage());
                        txtPrincipal.append(ex.getMessage() + "\n" + ex.getStackTrace().toString() + "\n");
                    }
                }
                socket.close();
            }

        } 
        catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            txtPrincipal.append(ex.getMessage() + "\n" + ex.getStackTrace().toString() + "\n");
        }
    }
    
    public void appendVentanaPartidas(String texto)
    {
        this.txtPartidas.append(texto);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel = new javax.swing.JPanel();
        jtpPrincipal = new javax.swing.JTabbedPane();
        jpPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPrincipal = new javax.swing.JTextArea();
        jpRegistro = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRegistro = new javax.swing.JTextArea();
        jpInicioSesion = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtInicioSesion = new javax.swing.JTextArea();
        jpPartidas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPartidas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        txtPrincipal.setColumns(20);
        txtPrincipal.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtPrincipal.setRows(5);
        jScrollPane1.setViewportView(txtPrincipal);

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpPrincipal.addTab("Principal", jpPrincipal);

        jScrollPane2.setAutoscrolls(true);

        txtRegistro.setColumns(20);
        txtRegistro.setRows(5);
        jScrollPane2.setViewportView(txtRegistro);

        javax.swing.GroupLayout jpRegistroLayout = new javax.swing.GroupLayout(jpRegistro);
        jpRegistro.setLayout(jpRegistroLayout);
        jpRegistroLayout.setHorizontalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpRegistroLayout.setVerticalGroup(
            jpRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpPrincipal.addTab("Registro de Usuarios", jpRegistro);

        jScrollPane3.setAutoscrolls(true);

        txtInicioSesion.setColumns(20);
        txtInicioSesion.setRows(5);
        jScrollPane3.setViewportView(txtInicioSesion);

        javax.swing.GroupLayout jpInicioSesionLayout = new javax.swing.GroupLayout(jpInicioSesion);
        jpInicioSesion.setLayout(jpInicioSesionLayout);
        jpInicioSesionLayout.setHorizontalGroup(
            jpInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpInicioSesionLayout.setVerticalGroup(
            jpInicioSesionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInicioSesionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpPrincipal.addTab("Inicio de Sesión", jpInicioSesion);

        jScrollPane4.setAutoscrolls(true);

        txtPartidas.setColumns(20);
        txtPartidas.setRows(5);
        jScrollPane4.setViewportView(txtPartidas);

        javax.swing.GroupLayout jpPartidasLayout = new javax.swing.GroupLayout(jpPartidas);
        jpPartidas.setLayout(jpPartidasLayout);
        jpPartidasLayout.setHorizontalGroup(
            jpPartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPartidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpPartidasLayout.setVerticalGroup(
            jpPartidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPartidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpPrincipal.addTab("Partidas", jpPartidas);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpPrincipal)
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpPrincipal)
                .addContainerGap())
        );

        getContentPane().add(jPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        System.exit(1);
    }//GEN-LAST:event_formWindowClosing
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpInicioSesion;
    private javax.swing.JPanel jpPartidas;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpRegistro;
    private javax.swing.JTabbedPane jtpPrincipal;
    private javax.swing.JTextArea txtInicioSesion;
    private javax.swing.JTextArea txtPartidas;
    private javax.swing.JTextArea txtPrincipal;
    private javax.swing.JTextArea txtRegistro;
    // End of variables declaration//GEN-END:variables
}
