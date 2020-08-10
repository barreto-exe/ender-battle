package game.ui;

import com.badlogic.gdx.Input;
import com.sun.glass.events.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisb
 */
public class FrmJugadores extends javax.swing.JFrame
{

    /**
     * Creates new form FrmJugadores
     */
    public FrmJugadores()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtJugadores = new javax.swing.JTable();

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
                {"Barreto", "Azul", "2", "40"}
            },
            new String []
            {
                "Jugador", "Color", "Jefes Ganados", "Esmeraldas"
            }
        ));
        jtJugadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtJugadores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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
        setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtJugadores;
    // End of variables declaration//GEN-END:variables
}
