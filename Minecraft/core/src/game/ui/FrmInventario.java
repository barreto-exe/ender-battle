package game.ui;

import com.badlogic.gdx.audio.Sound;
import game.actors.Player;
import game.inventario.BattleObject;
import game.inventario.Inventory;
import game.inventario.food.Food;
import game.tools.Constant;
import game.tools.Constant.BattleObjectEnum;
import game.tools.Constant.Farming;
import game.tools.Sonido;
import static game.tools.Sonido.soundManager;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author luisb
 */
public final class FrmInventario extends javax.swing.JFrame
{
    private Player player;
    private HashMap<String, JLabel> vistasAlimentos;
    private HashMap<String, Component[]> vistasObjetosBatalla;
    
    /**
     * Ventana que ofrece la vista del inventario del jugador.
     * @param player jugador que posee el inventario.
     */
    public FrmInventario(Player player)
    {
        super("Inventario");
        initComponents();
        setResizable(false);
        this.player = player;
        
        vistasAlimentos = new HashMap<>();
        vistasAlimentos.put("manzana", lblManzana);
        vistasAlimentos.put("pera", lblPera);
        vistasAlimentos.put("zanahoria", lblZanahoria);
        vistasAlimentos.put("baya", lblBaya);
        vistasAlimentos.put("papa", lblPapa);
        vistasAlimentos.put("sandia", lblSandia);
        vistasAlimentos.put("conejo", lblConejo);
        vistasAlimentos.put("pollo", lblPollo);
        vistasAlimentos.put("carne", lblCarne);
        
        vistasObjetosBatalla = new HashMap<>();
        vistasObjetosBatalla.put("casco", new Component[]{imgCasco,btnUsarCasco});
        vistasObjetosBatalla.put("pechera", new Component[]{imgPechera,btnUsarPechera});
        vistasObjetosBatalla.put("pantalones", new Component[]{imgPantalones,btnUsarPantalones});
        vistasObjetosBatalla.put("botas", new Component[]{imgBotas,btnUsarBotas});
        vistasObjetosBatalla.put("espada", new Component[]{imgEspada,rbEspada});
        vistasObjetosBatalla.put("hacha", new Component[]{imgHacha,rbHacha});
        vistasObjetosBatalla.put("pico", new Component[]{imgPico,rbPico});
        vistasObjetosBatalla.put("pala", new Component[]{imgPala,rbPala});
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        btngArmas = new javax.swing.ButtonGroup();
        imgManzana = new javax.swing.JLabel();
        imgPera = new javax.swing.JLabel();
        imgZanahoria = new javax.swing.JLabel();
        imgBaya = new javax.swing.JLabel();
        imgPapa = new javax.swing.JLabel();
        imgSandia = new javax.swing.JLabel();
        imgConejo = new javax.swing.JLabel();
        imgPollo = new javax.swing.JLabel();
        imgCarne = new javax.swing.JLabel();
        lblComida = new javax.swing.JLabel();
        lblManzana = new javax.swing.JLabel();
        lblPera = new javax.swing.JLabel();
        lblZanahoria = new javax.swing.JLabel();
        lblBaya = new javax.swing.JLabel();
        lblPapa = new javax.swing.JLabel();
        lblSandia = new javax.swing.JLabel();
        lblConejo = new javax.swing.JLabel();
        lblPollo = new javax.swing.JLabel();
        lblCarne = new javax.swing.JLabel();
        lblBatalla = new javax.swing.JLabel();
        imgCasco = new javax.swing.JLabel();
        imgPechera = new javax.swing.JLabel();
        imgPantalones = new javax.swing.JLabel();
        imgBotas = new javax.swing.JLabel();
        btnUsarCasco = new javax.swing.JToggleButton();
        btnUsarPechera = new javax.swing.JToggleButton();
        btnUsarPantalones = new javax.swing.JToggleButton();
        btnUsarBotas = new javax.swing.JToggleButton();
        imgEspada = new javax.swing.JLabel();
        imgHacha = new javax.swing.JLabel();
        imgPico = new javax.swing.JLabel();
        imgPala = new javax.swing.JLabel();
        rbEspada = new javax.swing.JRadioButton();
        rbHacha = new javax.swing.JRadioButton();
        rbPico = new javax.swing.JRadioButton();
        rbPala = new javax.swing.JRadioButton();

        setPreferredSize(new java.awt.Dimension(685, 430));
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

        imgManzana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/manzana.png"))); // NOI18N
        imgManzana.setToolTipText("manzana");
        imgManzana.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgManzana.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgPera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/pera.png"))); // NOI18N
        imgPera.setToolTipText("pera");
        imgPera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPera.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgZanahoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/zanahoria.png"))); // NOI18N
        imgZanahoria.setToolTipText("zanahoria");
        imgZanahoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgZanahoria.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgBaya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/bayas.png"))); // NOI18N
        imgBaya.setToolTipText("baya");
        imgBaya.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgBaya.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgPapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/papa.png"))); // NOI18N
        imgPapa.setToolTipText("papa");
        imgPapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPapa.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgSandia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/sandia.png"))); // NOI18N
        imgSandia.setToolTipText("sandia");
        imgSandia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgSandia.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgConejo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/conejo_carne.png"))); // NOI18N
        imgConejo.setToolTipText("conejo");
        imgConejo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgConejo.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgPollo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/pollo_carne.png"))); // NOI18N
        imgPollo.setToolTipText("pollo");
        imgPollo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgPollo.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        imgCarne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/comida/ganado_carne.png"))); // NOI18N
        imgCarne.setToolTipText("carne");
        imgCarne.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imgCarne.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                alimentoClicked(evt);
            }
        });

        lblComida.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblComida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblComida.setText("Comida");

        lblManzana.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblManzana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManzana.setLabelFor(imgManzana);
        lblManzana.setText("1");

        lblPera.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblPera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPera.setLabelFor(imgPera);
        lblPera.setText("1");

        lblZanahoria.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblZanahoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZanahoria.setLabelFor(imgZanahoria);
        lblZanahoria.setText("1");

        lblBaya.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblBaya.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBaya.setLabelFor(imgBaya);
        lblBaya.setText("1");

        lblPapa.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblPapa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPapa.setLabelFor(imgPapa);
        lblPapa.setText("1");

        lblSandia.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblSandia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSandia.setLabelFor(imgSandia);
        lblSandia.setText("1");

        lblConejo.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblConejo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConejo.setLabelFor(imgConejo);
        lblConejo.setText("1");

        lblPollo.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblPollo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPollo.setLabelFor(imgPollo);
        lblPollo.setText("1");

        lblCarne.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblCarne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCarne.setLabelFor(imgCarne);
        lblCarne.setText("1");

        lblBatalla.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblBatalla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBatalla.setText("Batalla");

        imgCasco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/casco1.png"))); // NOI18N

        imgPechera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pechera1.png"))); // NOI18N

        imgPantalones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pantalones1.png"))); // NOI18N

        imgBotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/botas1.png"))); // NOI18N

        btnUsarCasco.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnUsarCasco.setText("Usar");
        btnUsarCasco.setToolTipText("casco");
        btnUsarCasco.setFocusable(false);
        btnUsarCasco.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        btnUsarPechera.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnUsarPechera.setText("Usar");
        btnUsarPechera.setToolTipText("pechera");
        btnUsarPechera.setFocusable(false);
        btnUsarPechera.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        btnUsarPantalones.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnUsarPantalones.setText("Usar");
        btnUsarPantalones.setToolTipText("pantalones");
        btnUsarPantalones.setFocusable(false);
        btnUsarPantalones.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        btnUsarBotas.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnUsarBotas.setText("Usar");
        btnUsarBotas.setToolTipText("botas");
        btnUsarBotas.setFocusable(false);
        btnUsarBotas.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        imgEspada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/espada1.png"))); // NOI18N

        imgHacha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/hacha1.png"))); // NOI18N

        imgPico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pico1.png"))); // NOI18N

        imgPala.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sprites/objetos/pala1.png"))); // NOI18N

        btngArmas.add(rbEspada);
        rbEspada.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        rbEspada.setText("Espada");
        rbEspada.setToolTipText("espada");
        rbEspada.setFocusable(false);
        rbEspada.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        btngArmas.add(rbHacha);
        rbHacha.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        rbHacha.setText("Hacha");
        rbHacha.setToolTipText("hacha");
        rbHacha.setFocusable(false);
        rbHacha.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        btngArmas.add(rbPico);
        rbPico.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        rbPico.setText("Pico");
        rbPico.setToolTipText("pico");
        rbPico.setFocusable(false);
        rbPico.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        btngArmas.add(rbPala);
        rbPala.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        rbPala.setText("Pala");
        rbPala.setToolTipText("pala");
        rbPala.setFocusable(false);
        rbPala.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                portarArmaClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgBotas)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsarBotas))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgPantalones)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsarPantalones))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgPechera)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsarPechera))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgCasco)
                                .addGap(18, 18, 18)
                                .addComponent(btnUsarCasco)))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgPala)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPala))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgPico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbPico))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgHacha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbHacha))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgEspada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbEspada))))
                    .addComponent(lblBatalla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblComida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblManzana, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imgManzana))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(imgPera)
                                        .addGap(6, 6, 6)
                                        .addComponent(imgZanahoria))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblPera, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblZanahoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBaya, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPapa, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSandia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgBaya)
                                .addGap(6, 6, 6)
                                .addComponent(imgPapa)
                                .addGap(6, 6, 6)
                                .addComponent(imgSandia))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblConejo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgConejo))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgPollo)
                            .addComponent(lblPollo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgCarne)
                            .addComponent(lblCarne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblComida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgManzana)
                            .addComponent(imgPera)
                            .addComponent(imgZanahoria))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblManzana)
                            .addComponent(lblPera)
                            .addComponent(lblZanahoria))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSandia)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblBaya)
                                        .addComponent(lblPapa))))
                            .addComponent(imgBaya)
                            .addComponent(imgPapa)
                            .addComponent(imgSandia))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(imgConejo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblConejo)
                                    .addComponent(lblPollo)
                                    .addComponent(lblCarne)))
                            .addComponent(imgPollo)
                            .addComponent(imgCarne)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnUsarCasco)
                            .addComponent(imgCasco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnUsarPechera)
                            .addComponent(imgPechera))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnUsarPantalones)
                            .addComponent(imgPantalones))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(imgBotas)
                            .addComponent(btnUsarBotas)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblBatalla)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(rbEspada)
                            .addComponent(imgEspada))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(rbHacha)
                            .addComponent(imgHacha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(imgPico)
                            .addComponent(rbPico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(imgPala)
                            .addComponent(rbPala))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
        if(evt.getKeyChar() == 'E' || evt.getKeyChar() == 'e')
        {
            this.setVisible(false);
        }
    }//GEN-LAST:event_formKeyPressed

    private void alimentoClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_alimentoClicked
    {//GEN-HEADEREND:event_alimentoClicked
        //Obtener label clickeado
        JLabel origen = ((JLabel)evt.getSource());
        
        //Alimento que se decidió comer
        Farming alimento = Farming.getEnumByDesc(origen.getToolTipText());
        
        //Obtener la cantidad de vida a curar
        int index = Inventory.getIndex(alimento);
        Food food = player.getInventory().getFood()[index];
        
        //Si no tiene en el inventario
        if(food.getCant() <= 0)
        {
            //No hacer nada
            return;
        }
        
        //<editor-fold defaultstate="collapsed" desc="Sonido comer">
        int eatToSound = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
        Sonido.soundManager.get("sonidos/eat"+eatToSound+".ogg", Sound.class).play();
        //</editor-fold>    
        
        //Añadir vida al jugador
        player.addLife(food.getFood());
        
        //Sacar el alimento
        player.getInventory().removeFood(alimento);
        
        actualizarVista();
    }//GEN-LAST:event_alimentoClicked

    private void formWindowLostFocus(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowLostFocus
    {//GEN-HEADEREND:event_formWindowLostFocus
        this.setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus

    private void portarArmaClicked(java.awt.event.ActionEvent evt)//GEN-FIRST:event_portarArmaClicked
    {//GEN-HEADEREND:event_portarArmaClicked
        String objeto  = ((JToggleButton)evt.getSource()).getToolTipText();
        boolean portar = ((JToggleButton)evt.getSource()).isSelected();
        
        Constant.BattleObjectEnum typeObject = Constant.BattleObjectEnum.getEnumByDesc(objeto);
        
        //Sonido
        int toSound = (int) ((Math.random() * ((3 - 1) + 1)) + 1);
        Sonido.soundManager.get("sonidos/objetobatalla"+toSound+".ogg", Sound.class).play(Sonido.volumen);
        
        //Obtener el mejor objeto de ese tipo en el inventario
        BattleObject bestBattleObject = player.getInventory().findBestBattleObject(typeObject);
        
        if(portar)
        {
            player.portarObjeto(bestBattleObject);
        }
        else
        {
            player.desportarObjeto(typeObject);
        }
        
    }//GEN-LAST:event_portarArmaClicked

    /**
     * Muestra la ventana del inventario.
     */
    public void mostrar()
    {
        actualizarVista();
        setVisible(true);
        requestFocus();
    }
    
    /**
     * Actualiza los componentes de acuerdo al inventario del jugador.
     */
    private void actualizarVista()
    {
        JLabel label;
        
        //Por cada comida del inventario
        for(Food food : player.getInventory().getFood())
        {
            //Tomar el label correspondiente
            label = vistasAlimentos.get(Farming.getDescByEnum(food.getType()));

            //Actualizar cantidad de comida
            label.setText(food.getCant() + "");
            
            //Si no tiene esa comida, deshabilitar botón
            label.getLabelFor().setEnabled(food.getCant() != 0);
        }
        
        Constant.BattleObjectEnum typeObject;
        BattleObject bestBattleObject;
        int material;
        JLabel image;
        
        //Por cada tipo de objeto
        for(String objeto : vistasObjetosBatalla.keySet())
        {
            typeObject = Constant.BattleObjectEnum.getEnumByDesc(objeto);
            
            //Obtener el mejor objeto de ese tipo en el inventario
            bestBattleObject = player.getInventory().findBestBattleObject(typeObject);
            if(bestBattleObject != null)
            {
                //Obtener número que representa al material
                material = Constant.Material.getIntByEnum(bestBattleObject.getMaterial().getMaterial());
                
                //Colocar imagen al jlabel del objeto (con color que corresponde)
                image = (JLabel)vistasObjetosBatalla.get(objeto)[0];
                image.setIcon
                (
                    new javax.swing.ImageIcon
                    (
                        getClass().getResource("/sprites/objetos/"+objeto+material+".png")
                    )
                );
                
                //Habilitar componentes para ese objeto
                for(Component component : vistasObjetosBatalla.get(objeto))
                {
                    component.setEnabled(true);
                }
                
                //Colocar el boton como seleccionado si el jugador 
                //está portando el objeto
                ((JToggleButton)vistasObjetosBatalla.get(objeto)[1])
                .setSelected(bestBattleObject.isPorted());
            }
            else
            {
                //Deshabilitar componentes para ese objeto
                for(Component component : vistasObjetosBatalla.get(objeto))
                {
                    component.setEnabled(false);
                }
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnUsarBotas;
    private javax.swing.JToggleButton btnUsarCasco;
    private javax.swing.JToggleButton btnUsarPantalones;
    private javax.swing.JToggleButton btnUsarPechera;
    private javax.swing.ButtonGroup btngArmas;
    private javax.swing.JLabel imgBaya;
    private javax.swing.JLabel imgBotas;
    private javax.swing.JLabel imgCarne;
    private javax.swing.JLabel imgCasco;
    private javax.swing.JLabel imgConejo;
    private javax.swing.JLabel imgEspada;
    private javax.swing.JLabel imgHacha;
    private javax.swing.JLabel imgManzana;
    private javax.swing.JLabel imgPala;
    private javax.swing.JLabel imgPantalones;
    private javax.swing.JLabel imgPapa;
    private javax.swing.JLabel imgPechera;
    private javax.swing.JLabel imgPera;
    private javax.swing.JLabel imgPico;
    private javax.swing.JLabel imgPollo;
    private javax.swing.JLabel imgSandia;
    private javax.swing.JLabel imgZanahoria;
    private javax.swing.JLabel lblBatalla;
    private javax.swing.JLabel lblBaya;
    private javax.swing.JLabel lblCarne;
    private javax.swing.JLabel lblComida;
    private javax.swing.JLabel lblConejo;
    private javax.swing.JLabel lblManzana;
    private javax.swing.JLabel lblPapa;
    private javax.swing.JLabel lblPera;
    private javax.swing.JLabel lblPollo;
    private javax.swing.JLabel lblSandia;
    private javax.swing.JLabel lblZanahoria;
    private javax.swing.JRadioButton rbEspada;
    private javax.swing.JRadioButton rbHacha;
    private javax.swing.JRadioButton rbPala;
    private javax.swing.JRadioButton rbPico;
    // End of variables declaration//GEN-END:variables
}
