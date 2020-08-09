/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ui;

import game.actors.Player;

/**
 *
 * @author luisb
 */
public final class FrmTienda extends javax.swing.JFrame
{
    Player player;
    
    /**
     *  Ventana que ofrece la vista de la tienda al jugador.
     * 
     * @param player jugador que verá la tienda.
     */
    public FrmTienda(Player player)
    {
        super("Tienda");
        initComponents();
        setResizable(false);
        this.player = player;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        imgCasco = new javax.swing.JLabel();
        imgPechera = new javax.swing.JLabel();
        imgPantalones = new javax.swing.JLabel();
        imgBotas = new javax.swing.JLabel();
        imgEspada = new javax.swing.JLabel();
        imgHacha = new javax.swing.JLabel();
        imgPico = new javax.swing.JLabel();
        imPala = new javax.swing.JLabel();
        jsMateriales = new javax.swing.JSlider();
        lblMadera = new javax.swing.JLabel();
        lblOro = new javax.swing.JLabel();
        lblHierro = new javax.swing.JLabel();
        lblPrecioBotas = new javax.swing.JLabel();
        lblPrecioPantalones = new javax.swing.JLabel();
        lblPrecioPechera = new javax.swing.JLabel();
        lblPrecioCasco = new javax.swing.JLabel();
        lblPrecioHacha = new javax.swing.JLabel();
        lblPrecioPico = new javax.swing.JLabel();
        lblPrecioPala = new javax.swing.JLabel();
        lblPrecioEspada = new javax.swing.JLabel();
        imgManzana = new javax.swing.JLabel();
        imgZanahoria = new javax.swing.JLabel();
        imgCarne = new javax.swing.JLabel();
        lblPrecioManzana = new javax.swing.JLabel();
        lblPrecioZanahoria = new javax.swing.JLabel();
        lblPrecioCarne = new javax.swing.JLabel();

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

        imgCasco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco.png"))); // NOI18N
        imgCasco.setToolTipText("Click para añadir a la compra");

        imgPechera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pechera.png"))); // NOI18N
        imgPechera.setToolTipText("Click para añadir a la compra");

        imgPantalones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pantalones.png"))); // NOI18N
        imgPantalones.setToolTipText("Click para añadir a la compra");

        imgBotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/botas.png"))); // NOI18N
        imgBotas.setToolTipText("Click para añadir a la compra");

        imgEspada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/espada.png"))); // NOI18N
        imgEspada.setToolTipText("Click para añadir a la compra");

        imgHacha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/hacha.png"))); // NOI18N
        imgHacha.setToolTipText("Click para añadir a la compra");

        imgPico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pico.png"))); // NOI18N
        imgPico.setToolTipText("Click para añadir a la compra");

        imPala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/shovel.png"))); // NOI18N
        imPala.setToolTipText("Click para añadir a la compra");

        jsMateriales.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jsMateriales.setMajorTickSpacing(1);
        jsMateriales.setMaximum(3);
        jsMateriales.setMinimum(1);
        jsMateriales.setMinorTickSpacing(1);
        jsMateriales.setOrientation(javax.swing.JSlider.VERTICAL);
        jsMateriales.setPaintTicks(true);
        jsMateriales.setSnapToTicks(true);
        jsMateriales.setValue(1);
        jsMateriales.setFocusable(false);

        lblMadera.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblMadera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMadera.setText("Madera");

        lblOro.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblOro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOro.setText("Oro");

        lblHierro.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblHierro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHierro.setText("Hierro");

        lblPrecioBotas.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioBotas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioBotas.setText("6");

        lblPrecioPantalones.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPantalones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPantalones.setText("8");

        lblPrecioPechera.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPechera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPechera.setText("15");

        lblPrecioCasco.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioCasco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioCasco.setText("10");

        lblPrecioHacha.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioHacha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioHacha.setText("10");

        lblPrecioPico.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPico.setText("8");

        lblPrecioPala.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPala.setText("6");

        lblPrecioEspada.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioEspada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioEspada.setText("15");

        imgManzana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/manzana.png"))); // NOI18N
        imgManzana.setToolTipText("Click para añadir a la compra");

        imgZanahoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/zanahoria.png"))); // NOI18N
        imgZanahoria.setToolTipText("Click para añadir a la compra");

        imgCarne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/ganado_carne.png"))); // NOI18N
        imgCarne.setToolTipText("Click para añadir a la compra");

        lblPrecioManzana.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioManzana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioManzana.setText("5");

        lblPrecioZanahoria.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioZanahoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioZanahoria.setText("7");

        lblPrecioCarne.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioCarne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioCarne.setText("10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsMateriales, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPrecioManzana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(imgManzana))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imgZanahoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrecioZanahoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imgCarne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrecioCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblOro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMadera, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHierro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(lblPrecioBotas, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imgBotas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(imgPantalones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPrecioPantalones, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(imgCasco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPrecioCasco, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(imgPechera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPrecioPechera, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(imPala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblPrecioPala, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrecioPico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(imgPico, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblPrecioHacha, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imgHacha))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imgEspada, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblPrecioEspada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imgPantalones)
                                    .addComponent(imgBotas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPrecioBotas)
                                    .addComponent(lblPrecioPantalones))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imgCasco)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecioCasco))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imgPechera)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecioPechera))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imPala)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecioPala))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imgPico)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecioPico)))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imgEspada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecioEspada))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(imgHacha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblPrecioHacha))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblHierro)
                                .addGap(76, 76, 76)
                                .addComponent(lblOro)
                                .addGap(77, 77, 77)
                                .addComponent(lblMadera))
                            .addComponent(jsMateriales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imgManzana, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgZanahoria, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgCarne, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecioManzana)
                    .addComponent(lblPrecioZanahoria)
                    .addComponent(lblPrecioCarne))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
        if(evt.getKeyChar() == 'T' || evt.getKeyChar() == 't')
        {
            this.setVisible(false);
        }
    }//GEN-LAST:event_formKeyPressed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowLostFocus
    {//GEN-HEADEREND:event_formWindowLostFocus
        this.setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imPala;
    private javax.swing.JLabel imgBotas;
    private javax.swing.JLabel imgCarne;
    private javax.swing.JLabel imgCasco;
    private javax.swing.JLabel imgEspada;
    private javax.swing.JLabel imgHacha;
    private javax.swing.JLabel imgManzana;
    private javax.swing.JLabel imgPantalones;
    private javax.swing.JLabel imgPechera;
    private javax.swing.JLabel imgPico;
    private javax.swing.JLabel imgZanahoria;
    private javax.swing.JSlider jsMateriales;
    private javax.swing.JLabel lblHierro;
    private javax.swing.JLabel lblMadera;
    private javax.swing.JLabel lblOro;
    private javax.swing.JLabel lblPrecioBotas;
    private javax.swing.JLabel lblPrecioCarne;
    private javax.swing.JLabel lblPrecioCasco;
    private javax.swing.JLabel lblPrecioEspada;
    private javax.swing.JLabel lblPrecioHacha;
    private javax.swing.JLabel lblPrecioManzana;
    private javax.swing.JLabel lblPrecioPala;
    private javax.swing.JLabel lblPrecioPantalones;
    private javax.swing.JLabel lblPrecioPechera;
    private javax.swing.JLabel lblPrecioPico;
    private javax.swing.JLabel lblPrecioZanahoria;
    // End of variables declaration//GEN-END:variables
}
