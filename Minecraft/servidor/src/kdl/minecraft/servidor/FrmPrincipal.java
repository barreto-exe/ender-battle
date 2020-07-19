/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kdl.minecraft.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import kdl.minecraft.basedatos.DBUsuario;
import kdl.minecraft.basedatos.DBUsuario.Operacion;
import kdl.minecraft.basedatos.DBUsuario.ResultadoOperacion;
import kdl.minecraft.comunicacion.Partida;

/**
 *
 * @author luisb
 */
public class FrmPrincipal extends javax.swing.JFrame implements Runnable
{

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal()
    {
        super("Servidor");
        initComponents();

        new Thread(this).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

    @Override
    public void run()
    {
        System.out.println("Corriendo hilo server");

        try
        {
            ServerSocket servidor = new ServerSocket(27015);

            while (true)
            {
                txtPrincipal.append("Esperando socket \n");
                Socket socket = servidor.accept();

                ObjectInputStream paquete = new ObjectInputStream(socket.getInputStream());
                Object objeto = paquete.readObject();

                if (objeto.getClass() == DBUsuario.class)
                {
                    DBUsuario usuarioObj = (DBUsuario) objeto;

                    String ip = socket.getInetAddress().toString().replaceAll("/", "");
                    ResultadoOperacion resultado = ResultadoOperacion.ERROR;

                    String add
                            = usuarioObj.getCorreo() + "\n"
                            + usuarioObj.getUsuario() + "\n"
                            + usuarioObj.getPass() + "\n"
                            + ip + "\n";

                    if (Operacion.REGISTRAR == usuarioObj.getOperacion())
                    {

                        txtRegistro.append("\n Registrar usuario: \n" + add);

                        //Verificar existencia de correo
                        if (DBUsuario.idCorreo(usuarioObj.getCorreo()) != -1)
                        {
                            txtRegistro.append("Correo no disponible. \n");
                            resultado = ResultadoOperacion.CORREO_NO_DISPONIBLE;
                        } //Verificar existencia de usuario
                        else if (DBUsuario.idUsuario(usuarioObj.getUsuario()) != -1)
                        {
                            txtRegistro.append("Usuario no disponible. \n");
                            resultado = ResultadoOperacion.USUARIO_NO_DISPONIBLE;

                        } //Si el usuario y el correo están disponibles
                        else
                        {
                            if (DBUsuario.registrarUsuario(usuarioObj))
                            {
                                txtRegistro.append("Usuario registrado exitosamente. \n");
                                resultado = ResultadoOperacion.USUARIO_REGISTRADO;
                            } else
                            {
                                txtRegistro.append("Hubo un error al registrar el usuario. \n");
                                resultado = ResultadoOperacion.ERROR;
                            }
                        }
                    }

                    if (Operacion.INICIAR_SESION == usuarioObj.getOperacion())
                    {
                        txtInicioSesion.append("\n Iniciar sesión usuario: \n" + add);

                        //Verificar datos de usuario
                        if (DBUsuario.consultarUsuario(usuarioObj) == -1)
                        {
                            txtInicioSesion.append("Credencial de usuario inválida. \n");
                            resultado = ResultadoOperacion.CREDENCIAL_INVALIDA;
                        } else
                        {
                            //Iniciar juego aquí
                            resultado = ResultadoOperacion.INICIAR_JUEGO;
                        }
                    }

                    //**************************************************************
                    //Enviar respuesta al cliente
                    socket = new Socket(ip, 27016);
                    ObjectOutputStream paqueteEnvio = new ObjectOutputStream(socket.getOutputStream());
                    paqueteEnvio.writeObject(resultado);
                    socket.close();
                }
                if (objeto.getClass() == Partida.class)
                {
                    Partida partida = (Partida) objeto;
                    txtPartidas.append(partida.getNombre()+ "\n\n");
                }
            }

        } catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.getMessage());
            txtPrincipal.append(ex.getStackTrace().toString() + "\n");
            new Thread(this).start();
        }

    }
}
