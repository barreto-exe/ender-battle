package game.ui;

import com.sun.glass.events.KeyEvent;
import game.tools.Sonido;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisb
 */
public class FrmJugadores extends javax.swing.JFrame
{
    private JFrame ventanaJuego;
    
    /**
     * Creates new form FrmJugadores
     */
    public FrmJugadores(JFrame ventanaJuego)
    {
        initComponents();
        this.ventanaJuego = ventanaJuego;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtJugadores = new javax.swing.JTable();
        jsVolumen = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        btnAbandonar = new javax.swing.JButton();

        addWindowFocusListener(new java.awt.event.WindowFocusListener()
        {
            public void windowGainedFocus(java.awt.event.WindowEvent evt)
            {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt)
            {
                formWindowLostFocus(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                formKeyPressed(evt);
            }
        });

        jtJugadores.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jtJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Jugador", "Color", "Jefes Ganados", "Esmeraldas"
            }
        ));
        jtJugadores.setFocusable(false);
        jtJugadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtJugadores);

        jsVolumen.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jsVolumen.setPaintLabels(true);
        jsVolumen.setPaintTicks(true);
        jsVolumen.setValue(100);
        jsVolumen.setFocusable(false);
        jsVolumen.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                jsVolumenStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Volumen");

        btnAbandonar.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        btnAbandonar.setText("Abandonar Partida");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAbandonarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAbandonar, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .addComponent(jsVolumen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jsVolumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAbandonar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_TAB)
        {
            setVisible(false);
        }
    }//GEN-LAST:event_formKeyPressed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowLostFocus
    {//GEN-HEADEREND:event_formWindowLostFocus
        //setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus

    private void jsVolumenStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jsVolumenStateChanged
    {//GEN-HEADEREND:event_jsVolumenStateChanged
        Sonido.volumen = ((float)jsVolumen.getValue() / 100);
        Sonido.music.setVolume(Sonido.musicId, Sonido.volumen);
    }//GEN-LAST:event_jsVolumenStateChanged

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAbandonarActionPerformed
    {//GEN-HEADEREND:event_btnAbandonarActionPerformed
        int r = JOptionPane.showConfirmDialog(this, "¿Seguro deseas abandonar?", "Abandonar Partida", JOptionPane.OK_CANCEL_OPTION);
        if(r == JOptionPane.YES_OPTION)
        {
            this.setVisible(false);
            ventanaJuego.setVisible(false);
        }
    }//GEN-LAST:event_btnAbandonarActionPerformed

    public void actualizarTabla(Object[][] rows)
    {
        ((DefaultTableModel) jtJugadores.getModel()).setRowCount(0);
        DefaultTableModel tabla = (DefaultTableModel) jtJugadores.getModel();
        
        for(Object[] row : rows)
        {
            tabla.addRow(row);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jsVolumen;
    private javax.swing.JTable jtJugadores;
    // End of variables declaration//GEN-END:variables
}
