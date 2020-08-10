package game.ui;

import com.badlogic.gdx.audio.Sound;
import game.actors.Player;
import game.inventario.Arm;
import game.inventario.BattleObject;
import game.inventario.Protection;
import game.tools.Constant;
import static game.tools.Sonido.soundManager;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

/**
 *
 * @author luisb
 */
public final class FrmTienda extends javax.swing.JFrame
{
    Player player;
    private HashMap<String, JLabel>  vistasPrecios;
    private HashMap<String, Integer> listaPreciosBase;
    
    
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
        
        vistasPrecios = new HashMap<>();
        //<editor-fold defaultstate="collapsed" desc="JLabels">
        vistasPrecios.put("manzana", lblPrecioManzana);
        vistasPrecios.put("zanahoria", lblPrecioZanahoria);
        vistasPrecios.put("carne", lblPrecioCarne);
        vistasPrecios.put("casco", lblPrecioCasco);
        vistasPrecios.put("pechera", lblPrecioPechera);
        vistasPrecios.put("pantalones", lblPrecioPantalones);
        vistasPrecios.put("botas", lblPrecioBotas);
        vistasPrecios.put("espada", lblPrecioEspada);
        vistasPrecios.put("hacha", lblPrecioHacha);
        vistasPrecios.put("pico", lblPrecioPico);
        vistasPrecios.put("pala", lblPrecioPala);
        //</editor-fold>

        listaPreciosBase = new HashMap<>();
        //<editor-fold defaultstate="collapsed" desc="Precios">
        listaPreciosBase.put("manzana", 5);
        listaPreciosBase.put("zanahoria", 7);
        listaPreciosBase.put("carne", 10);
        listaPreciosBase.put("casco", 10);
        listaPreciosBase.put("pechera", 15);
        listaPreciosBase.put("pantalones", 8);
        listaPreciosBase.put("botas", 6);
        listaPreciosBase.put("espada", 15);
        listaPreciosBase.put("hacha", 10);
        listaPreciosBase.put("pico", 8);
        listaPreciosBase.put("pala", 6);
        //</editor-fold>
        
        actualizarVista();
    }
    
    private void actualizarVista()
    {
        JLabel labelPrecio, imgObjeto;
        int precio, materialSeleccionado = this.jsMateriales.getValue();
    
        //A cada objeto de la tienda
        for(String objeto : listaPreciosBase.keySet())
        {
            labelPrecio = vistasPrecios.get(objeto);                
            imgObjeto = (JLabel) labelPrecio.getLabelFor();
    
            
            precio = listaPreciosBase.get(objeto);
                

            //Multiplicar precio si no es comida
            if(!(objeto.equals("manzana") || objeto.equals("zanahoria") || objeto.equals("carne")))
            {
                precio*=materialSeleccionado;
                
                imgObjeto.setIcon
                (
                    new javax.swing.ImageIcon
                    (
                        getClass().getResource("/sprites/objetos/"+objeto+materialSeleccionado+".png")
                    )
                );
            }
            //Actualizar precio de acuerdo al material seleccionado
            labelPrecio.setText("" + precio);
            
            //Habilitar imagen de objeto si tiene suficientes esmeraldas para comprar
            //imgObjeto.setEnabled(player.getInventory().getEsmeraldas() >= precio);
        }
    }
    
    public void mostrar()
    {
        setVisible(true);
        requestFocus();
        actualizarVista();
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
        imgPala = new javax.swing.JLabel();
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

        setType(java.awt.Window.Type.UTILITY);
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

        imgCasco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgCasco.setToolTipText("casco");
        imgCasco.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgCasco.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgPechera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgPechera.setToolTipText("pechera");
        imgPechera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPechera.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgPantalones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgPantalones.setToolTipText("pantalones");
        imgPantalones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPantalones.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgBotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgBotas.setToolTipText("botas");
        imgBotas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgBotas.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgEspada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgEspada.setToolTipText("espada");
        imgEspada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgEspada.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgHacha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgHacha.setToolTipText("hacha");
        imgHacha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgHacha.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgPico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgPico.setToolTipText("pico");
        imgPico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPico.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgPala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N
        imgPala.setToolTipText("pala");
        imgPala.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPala.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

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
        jsMateriales.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                jsMaterialesStateChanged(evt);
            }
        });

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
        lblPrecioBotas.setLabelFor(imgBotas);
        lblPrecioBotas.setText("6");

        lblPrecioPantalones.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPantalones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPantalones.setLabelFor(imgPantalones);
        lblPrecioPantalones.setText("8");

        lblPrecioPechera.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPechera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPechera.setLabelFor(imgPechera);
        lblPrecioPechera.setText("15");

        lblPrecioCasco.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioCasco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioCasco.setLabelFor(imgCasco);
        lblPrecioCasco.setText("10");

        lblPrecioHacha.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioHacha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioHacha.setLabelFor(imgHacha);
        lblPrecioHacha.setText("10");

        lblPrecioPico.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPico.setLabelFor(imgPico);
        lblPrecioPico.setText("8");

        lblPrecioPala.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioPala.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioPala.setLabelFor(imgPala);
        lblPrecioPala.setText("6");

        lblPrecioEspada.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioEspada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioEspada.setLabelFor(imgEspada);
        lblPrecioEspada.setText("15");

        imgManzana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/manzana.png"))); // NOI18N
        imgManzana.setToolTipText("manzana");
        imgManzana.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgManzana.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgZanahoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/zanahoria.png"))); // NOI18N
        imgZanahoria.setToolTipText("zanahoria");
        imgZanahoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgZanahoria.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        imgCarne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/ganado_carne.png"))); // NOI18N
        imgCarne.setToolTipText("carne");
        imgCarne.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgCarne.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                imgObjetoClicked(evt);
            }
        });

        lblPrecioManzana.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioManzana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioManzana.setLabelFor(imgManzana);
        lblPrecioManzana.setText("5");

        lblPrecioZanahoria.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioZanahoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioZanahoria.setLabelFor(imgZanahoria);
        lblPrecioZanahoria.setText("7");

        lblPrecioCarne.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPrecioCarne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioCarne.setLabelFor(imgCarne);
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
                        .addGap(65, 65, 65))
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
                                    .addComponent(imgPala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(imgPala)
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

    private void jsMaterialesStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jsMaterialesStateChanged
    {//GEN-HEADEREND:event_jsMaterialesStateChanged
        actualizarVista();
    }//GEN-LAST:event_jsMaterialesStateChanged

    private void imgObjetoClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_imgObjetoClicked
    {//GEN-HEADEREND:event_imgObjetoClicked
        //Si está deshabilitado no hago nada.
        if(!((JLabel)evt.getSource()).isEnabled())
            return;
        
        
        soundManager.get("sonidos/pick.ogg", Sound.class).play();

        //Nombre del objeto
        String objeto = ((JLabel)evt.getSource()).getToolTipText();

        //Obtener precio del objeto
        int precio = listaPreciosBase.get(objeto);

        //Si no es comida
        if(!(objeto.equals("manzana") || objeto.equals("zanahoria") || objeto.equals("carne")))
        {
            precio*=this.jsMateriales.getValue();
            
            
            Constant.Material material = null;
            //<editor-fold defaultstate="collapsed" desc="Material">
            switch(this.jsMateriales.getValue())
            {
                case 1:
                    material = Constant.Material.WOOD;
                    break;
                case 2:
                    material = Constant.Material.GOLD;
                    break;
                case 3:
                    material = Constant.Material.IRON;
                    break;
            }
            //</editor-fold>
            
            Constant.BattleObjectEnum type = Constant.BattleObjectEnum.SWORD;
            BattleObject object = null;
            switch(objeto)
            {
                //<editor-fold defaultstate="collapsed" desc="Protection Object">
                case "botas":
                    type = Constant.BattleObjectEnum.BOOTS;
                    object = new Protection(type,material);
                    break;
                case "pantalones":
                    type = Constant.BattleObjectEnum.LEGGING;
                    object = new Protection(type,material);
                    break;
                case "pechera":
                    type = Constant.BattleObjectEnum.SHIRTFRONT;
                    object = new Protection(type,material);
                    break;
                case "casco":
                    type = Constant.BattleObjectEnum.HELMET;
                    object = new Protection(type,material);
                    break;
                //</editor-fold>
                    
                //<editor-fold defaultstate="collapsed" desc="Arm Object">
                case "pico":
                    type = Constant.BattleObjectEnum.PICK;
                    object = new Arm(type,material);
                    break;
                case "pala":
                    type = Constant.BattleObjectEnum.SHOVEL;
                    object = new Arm(type,material);
                    break;
                case "hacha":
                    type = Constant.BattleObjectEnum.AX;
                    object = new Arm(type,material);
                    break;
                case "espada":
                    type = Constant.BattleObjectEnum.SWORD;
                    object = new Arm(type,material);
                    break;
                //</editor-fold>
            }
            
            if(player.getInventory().findBattleObject(type, material) != null)
            {
                JOptionPane.showMessageDialog(null, "Ya tienes esto objeto");
                return;
            }
            
            player.getInventory().addBattleObject(object);
        }
        //Si es comida
        else
        {
            switch(objeto)
            {
                case "manzana":
                    player.getInventory().addFood(Constant.Farming.APPLE);
                    break;
                case "zanahoria":
                    player.getInventory().addFood(Constant.Farming.CARROT);
                    break;
                case "carne":
                    player.getInventory().addFood(Constant.Farming.BEEF);
                    break;
            }
        }
        
        //Cobrar
        player.getInventory().addEsmeraldas(-precio);
        
        actualizarVista();
    }//GEN-LAST:event_imgObjetoClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgBotas;
    private javax.swing.JLabel imgCarne;
    private javax.swing.JLabel imgCasco;
    private javax.swing.JLabel imgEspada;
    private javax.swing.JLabel imgHacha;
    private javax.swing.JLabel imgManzana;
    private javax.swing.JLabel imgPala;
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
