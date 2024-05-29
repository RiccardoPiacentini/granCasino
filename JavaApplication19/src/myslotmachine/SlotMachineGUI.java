package myslotmachine;

import casinò.MainMenu;
import java.awt.Color;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SlotMachineGUI extends javax.swing.JFrame {
    int credito;
    String nome;
    String password;
    // Slot machine instance
    SlotMachine slotMachine;
    // Chip flags for different bet amounts
    private boolean flag_chip_5 = false;
    private boolean flag_chip_10 = false;
    private boolean flag_chip_25 = false;
    private boolean flag_chip_50 = false;
    // Lever and animation flags
    private boolean flag_lever = true;
    private boolean flag_lever_animation = false;
    private boolean flag_spin_animation = false;
    private int jackpot;
    
    // Array of file paths for slot icons
    private static final String[] slotIcons = {"/Foto/cherries.png", "/Foto/seven_1.png", "/Foto/diamond.png", "/Foto/lemon.png", "/Foto/apple.png", "/Foto/watermelon.png"};
    
    /**
     * Creates new form SlotMachineGUI
     */
    public SlotMachineGUI() throws IOException {
        this.credito = caricacredito();
        this.slotMachine = new SlotMachine(5, credito, 10000);
        initComponents();
        EffectWin.setVisible(false);
        updateDisplay();
        play("music.wav", true); // Play background music
    }
    public int caricacredito() throws IOException{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("accountTemp.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SlotMachineGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String riga=br.readLine();
        String[] dividi=riga.split(",");
        int credito=Integer.parseInt(dividi[2]);
        nome=dividi[0];
        password=dividi[1];
        return credito;
    }
    // Rimuovi la parola chiave static dal metodo play
    public void play(String path, boolean loop){
        try {
            // Get the URL of the audio file
            URL url = getClass().getResource("/Musica/" + path);
            if (url != null) {
                // Open an input stream for the audio file
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
                // Get a Clip object for playing the audio
                Clip clip = AudioSystem.getClip();
                // Open the audio input stream
                clip.open(audioInput);
                // Start playing the audio
                clip.start();
                // If looping is enabled, loop the audio continuously
                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                if (loop && path.equals("music.wav")) {
                    setBackgroundMusicClip(clip); // Imposta il clip del suono in sottofondo
                }
            } else {
                // Print a message if the audio file does not exist
                System.out.println("audio file does not exist"+path);
            }
        } catch (Exception e) {
            // Print an error message if there is an issue with audio playback
            System.out.println("Audio playback error");
        }
    }

private Clip backgroundMusicClip;

public void setBackgroundMusicClip(Clip clip) {
    this.backgroundMusicClip = clip;
}

// Resto del codice rimane invariato...


    public void pauseBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
    }

    public void resumeBackgroundMusic() {
        if (backgroundMusicClip != null && !backgroundMusicClip.isRunning()) {
            backgroundMusicClip.start();
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // This method resets the chip icons and flags to their default state
    public void resetChip(){
        // Reset chip icons
        chip_5.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_5.png")));
        chip_10.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_10.png")));
        chip_25.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_25.png")));
        chip_50.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_50.png")));
        // Reset flags
        flag_chip_5 = false;
        flag_chip_10 = false;
        flag_chip_25 = false;
        flag_chip_50 = false;
    }

    // This method updates the display with the current jackpot, balance, and bet values
    private void updateDisplay() {
        jackpote_value.setText(slotMachine.getJackpot()+ "€");
        balance_value.setText(slotMachine.getBalance()+ "€");
        bet_value.setText(slotMachine.getBet() + "€");
    }

    // This method sets the bet amount and updates the bet display
    private void setBetAmount(int bet){
        slotMachine.setBet(bet);
        bet_value.setText(bet + "€");
    }

    // This method checks if there is enough balance for the current bet
    // If not, it triggers an error animation and plays an error sound
    private boolean checkError(){
        if(slotMachine.getBalance() < slotMachine.getBet()){
            balance_value.setForeground(Color.red);
            errorAnimation();
            play("errore.wav", false);
            return true;
        } else {
            Color yellow = new Color(255, 255, 102);
            balance_value.setForeground(yellow);
            return false;
        }
    }

    // This method animates the lever moving down
    public void leverDownAnimation() {
        if (flag_lever_animation) {
            return; // Avoid starting another animation if one is already in progress
        }

        flag_lever_animation = true;
        flag_lever = false;

        play("lever.wav", false);

        // Lever animation
        spin();
        slotMachine.extract();

        Timer timer = new Timer(120, new ActionListener() {
            private int frame = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                lever.setIcon(new ImageIcon(getClass().getResource("/Foto/leva_" + frame + ".png")));
                frame++;

                if (frame > 5) {
                    ((Timer) e.getSource()).stop(); // Stop the timer at the end of the animation
                    flag_lever_animation = false;
                }
            }
        });

        timer.start();
    }

    // This method animates the lever moving up
    public void leverUpAnimation() {
        if (flag_lever_animation) {
            return; // Avoid starting another animation if one is already in progress
        }

        flag_lever_animation = true;
        flag_lever = true;

        play("lever.wav", false);

        Timer timer = new Timer(120, new ActionListener() {
            private int frame = 5;

            @Override
            public void actionPerformed(ActionEvent e) {
                lever.setIcon(new ImageIcon(getClass().getResource("/Foto/leva_" + frame + ".png")));
                frame--;

                if (frame < 0) {
                    ((Timer) e.getSource()).stop(); // Stop the timer at the end of the animation
                    flag_lever_animation = false;
                }
            }
        });

        timer.start();
    }

    // This method animates a win by playing a win sound and changing the slot image
    private void winAnimation(){
        play("win.wav", false);
        play("jackpot_1.wav",false);
        //EffectWin.setVisible(true);
        Timer timer = new Timer(120, new ActionListener() {
            private int frame = 2;
            private int times = 2;

            @Override
            public void actionPerformed(ActionEvent e) {
                slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot" + frame + ".png")));
                frame--;

                if (frame < 2) {
                    if(times < 0){
                        slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot0.png"))); // Reset image
                        ((Timer) e.getSource()).stop(); // Stop the timer at the end of the animation
                    }
                    else{
                        frame = 3;
                        times--;
                    }
                }
            }
        });

        timer.start();
    }

    // This method animates a double seven win by playing a special sound and changing the slot image
    private void doubleSevenAnimation(){
        play("winTwoSeven.wav", false);

        Timer timer = new Timer(140, new ActionListener() {
            private int frame = 3;
            private int times = 1;

            @Override
            public void actionPerformed(ActionEvent e) {
                slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot" + frame + ".png")));
                frame--;

                if (frame < 2) {
                    if(times < 0){
                        slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot0.png"))); // Reset image
                        ((Timer) e.getSource()).stop(); // Stop the timer at the end of the animation
                    }
                    else{
                        frame = 3;
                        times--;
                    }
                }
            }
        });

        timer.start();
    }

    // This method animates a jackpot win by playing a special sound and changing the slot image
    private void jackpotAnimation(){
    pauseBackgroundMusic(); // Metti in pausa la musica di sottofondo
        play("spiderman.wav", false);
        EffectWin.setVisible(true);

        Timer timer = new Timer(120, new ActionListener() {
            private int frame = 3;
            private int times = 3;

            @Override
            public void actionPerformed(ActionEvent e) {
                slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot" + frame + ".png")));
                frame--;

                if (frame < 0) {
                    if (times < 0) {
                        slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot0.png"))); // Resetta l'immagine
                        ((Timer) e.getSource()).stop(); // Ferma il timer alla fine dell'animazione

                        // Inizia un nuovo Timer per gestire il ritardo di 8 secondi
                        new Timer(8000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                EffectWin.setVisible(false);
                                resumeBackgroundMusic(); // Riprendi la musica di sottofondo dopo l'animazione del jackpot
                                ((Timer) e.getSource()).stop(); // Ferma il timer di ritardo
                            }
                        }).start();
                    } else {
                        frame = 3;
                        times--;
                    }
                }
            }
        });

    timer.start();
}


    // This method animates an error condition by playing an error sound and changing the slot image
    private void errorAnimation(){
        play("error.wav", false);

        Timer timer = new Timer(90, new ActionListener() {
            private int frame = 4;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(frame % 2 != 0) {
                    slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot1.png")));
                } else {
                    slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot0.png")));
                }
                frame--;

                if (frame < 0) {
                    slot.setIcon(new ImageIcon(getClass().getResource("/Foto/slot0.png"))); // Reset image
                        ((Timer) e.getSource()).stop(); // Stop the timer at the end of the animation
                }
            }
        });

        timer.start();
    }

    // This method initiates the spinning animation of the slot machine
    public void spin(){
        if (flag_spin_animation) {
            return; // Avoid starting another animation if one is already in progress
        }

        play("spin.wav", false);

        Timer timer = new Timer(120, new ActionListener() {
            Random rand = new Random();
            int j = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                j++;

                if (j < 5) {
                    slot0.setIcon(new ImageIcon(getClass().getResource(slotIcons[rand.nextInt(5) + 1])));
                    slot1.setIcon(new ImageIcon(getClass().getResource(slotIcons[rand.nextInt(5) + 1])));
                    slot2.setIcon(new ImageIcon(getClass().getResource(slotIcons[rand.nextInt(5) + 1])));
                }
                if (j >= 5 && j < 15) {
                    slot0.setIcon(new ImageIcon(getClass().getResource(slotIcons[slotMachine.getNumber(0)])));
                    slot1.setIcon(new ImageIcon(getClass().getResource(slotIcons[rand.nextInt(5) + 1])));
                    slot2.setIcon(new ImageIcon(getClass().getResource(slotIcons[rand.nextInt(5) + 1])));
                }
                if (j >= 15 && j < 25) {
                    slot1.setIcon(new ImageIcon(getClass().getResource(slotIcons[slotMachine.getNumber(1)])));
                    slot2.setIcon(new ImageIcon(getClass().getResource(slotIcons[rand.nextInt(5) + 1])));
                }
                if (j >= 25) {
                    slot2.setIcon(new ImageIcon(getClass().getResource(slotIcons[slotMachine.getNumber(2)])));

                    if (slotMachine.wonJackpot()) {
                        jackpotAnimation(); // Jackpot win animation
                    } else if (slotMachine.wonTwoSevens()) {
                        //jackpotAnimation();
                        doubleSevenAnimation(); // Double seven win animation
                    } else if (slotMachine.wonCombination()){
                        //jackpotAnimation();
                        winAnimation(); // Normal win animation
                    }

                    ((Timer) e.getSource()).stop(); // Stop the timer at the end of the animation
                    flag_spin_animation = false;
                    updateDisplay();
                    leverUpAnimation();
                }
            }
        });
        timer.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pannel = new javax.swing.JLayeredPane();
        EffectWin = new javax.swing.JLabel();
        bet_value = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        balance_immagine = new javax.swing.JLabel();
        jackpote_value = new javax.swing.JLabel();
        balance_value = new javax.swing.JLabel();
        chip_5 = new javax.swing.JButton();
        chip_10 = new javax.swing.JButton();
        chip_25 = new javax.swing.JButton();
        chip_50 = new javax.swing.JButton();
        slot2 = new javax.swing.JLabel();
        slot0 = new javax.swing.JLabel();
        slot1 = new javax.swing.JLabel();
        lever = new javax.swing.JButton();
        bet_asset = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        frame = new javax.swing.JLabel();
        slot = new javax.swing.JLabel();
        wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pannel.setMaximumSize(new java.awt.Dimension(1280, 720));
        pannel.setName(""); // NOI18N
        pannel.setPreferredSize(new java.awt.Dimension(1280, 720));

        EffectWin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/uomoragno.gif"))); // NOI18N
        pannel.add(EffectWin);
        EffectWin.setBounds(130, 10, 920, 640);

        bet_value.setFont(new java.awt.Font("Consolas", 1, 30)); // NOI18N
        bet_value.setForeground(new java.awt.Color(255, 255, 102));
        bet_value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bet_value.setText("0€");
        bet_value.setToolTipText("");
        bet_value.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bet_value.setName(""); // NOI18N
        pannel.add(bet_value);
        bet_value.setBounds(1140, 340, 70, 160);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/Logo_883.svg.png"))); // NOI18N
        pannel.add(logo);
        logo.setBounds(590, -30, 260, 170);

        balance_immagine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balance_immagine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/saldo.png"))); // NOI18N
        pannel.add(balance_immagine);
        balance_immagine.setBounds(100, 40, 110, 120);

        jackpote_value.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jackpote_value.setForeground(new java.awt.Color(204, 0, 0));
        jackpote_value.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jackpote_value.setText("0€");
        jackpote_value.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jackpote_value.setName(""); // NOI18N
        pannel.add(jackpote_value);
        jackpote_value.setBounds(430, 110, 520, 60);

        balance_value.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        balance_value.setForeground(new java.awt.Color(255, 255, 102));
        balance_value.setText("0€");
        balance_value.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        balance_value.setName(""); // NOI18N
        pannel.add(balance_value);
        balance_value.setBounds(200, 80, 240, 70);

        chip_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/chip_5.png"))); // NOI18N
        chip_5.setToolTipText("");
        chip_5.setBorder(null);
        chip_5.setBorderPainted(false);
        chip_5.setContentAreaFilled(false);
        chip_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chip_5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chip_5MouseExited(evt);
            }
        });
        chip_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chip_5ActionPerformed(evt);
            }
        });
        pannel.add(chip_5);
        chip_5.setBounds(90, 150, 130, 120);

        chip_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/chip_10.png"))); // NOI18N
        chip_10.setToolTipText("");
        chip_10.setBorder(null);
        chip_10.setBorderPainted(false);
        chip_10.setContentAreaFilled(false);
        chip_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chip_10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chip_10MouseExited(evt);
            }
        });
        chip_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chip_10ActionPerformed(evt);
            }
        });
        pannel.add(chip_10);
        chip_10.setBounds(90, 280, 130, 120);

        chip_25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/chip_25.png"))); // NOI18N
        chip_25.setToolTipText("");
        chip_25.setBorder(null);
        chip_25.setBorderPainted(false);
        chip_25.setContentAreaFilled(false);
        chip_25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chip_25MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chip_25MouseExited(evt);
            }
        });
        chip_25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chip_25ActionPerformed(evt);
            }
        });
        pannel.add(chip_25);
        chip_25.setBounds(90, 410, 130, 120);

        chip_50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/chip_50.png"))); // NOI18N
        chip_50.setToolTipText("");
        chip_50.setBorder(null);
        chip_50.setBorderPainted(false);
        chip_50.setContentAreaFilled(false);
        chip_50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chip_50MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chip_50MouseExited(evt);
            }
        });
        chip_50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chip_50ActionPerformed(evt);
            }
        });
        pannel.add(chip_50);
        chip_50.setBounds(90, 540, 130, 120);

        slot2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/seven_1.png"))); // NOI18N
        slot2.setPreferredSize(new java.awt.Dimension(40, 40));
        pannel.add(slot2);
        slot2.setBounds(780, 300, 145, 290);

        slot0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/seven_1.png"))); // NOI18N
        slot0.setPreferredSize(new java.awt.Dimension(40, 40));
        pannel.add(slot0);
        slot0.setBounds(360, 300, 145, 290);

        slot1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/seven_1.png"))); // NOI18N
        slot1.setPreferredSize(new java.awt.Dimension(40, 40));
        pannel.add(slot1);
        slot1.setBounds(570, 300, 145, 290);

        lever.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/leva_0.png"))); // NOI18N
        lever.setToolTipText("");
        lever.setBorder(null);
        lever.setBorderPainted(false);
        lever.setContentAreaFilled(false);
        lever.setName(""); // NOI18N
        lever.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                leverMouseExited(evt);
            }
        });
        lever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leverActionPerformed(evt);
            }
        });
        pannel.add(lever);
        lever.setBounds(1000, 70, 160, 590);

        bet_asset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bet_asset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/puntata_label.png"))); // NOI18N
        pannel.add(bet_asset);
        bet_asset.setBounds(1110, 160, 130, 500);

        Back.setBackground(new java.awt.Color(255, 255, 0));
        Back.setForeground(new java.awt.Color(255, 255, 0));
        Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/back.png"))); // NOI18N
        Back.setToolTipText("");
        Back.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 6, true));
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        pannel.add(Back);
        Back.setBounds(1180, 20, 80, 70);

        frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/frame.png"))); // NOI18N
        pannel.add(frame);
        frame.setBounds(0, 0, 1280, 720);

        slot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/slot0.png"))); // NOI18N
        pannel.add(slot);
        slot.setBounds(40, 20, 1280, 720);

        wallpaper.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/sfondo.jpg"))); // NOI18N
        pannel.add(wallpaper);
        wallpaper.setBounds(0, 0, 1280, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chip_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chip_5ActionPerformed
        play("coins.wav", false); //sound of changing bet
        resetChip(); //reset all chip buttons
        chip_5.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_5_A.png"))); //saturation -45
        flag_chip_5 = true; //set the flag
        setBetAmount(5); //set the bet amount
    }//GEN-LAST:event_chip_5ActionPerformed

    private void chip_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chip_10ActionPerformed
        play("coins.wav", false); //sound of changing bet
        resetChip();  //reset all chip buttons
        chip_10.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_10_A.png"))); //saturation -45
        flag_chip_10 = true; //set the flag
        setBetAmount(10); //set the bet amount
    }//GEN-LAST:event_chip_10ActionPerformed

    private void chip_25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chip_25ActionPerformed
        play("coins.wav", false); //sound of changing bet
        resetChip(); //reset all chip buttons
        chip_25.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_25_A.png"))); //saturation -45
        flag_chip_25 = true; //set the flag
        setBetAmount(25); //set the bet amount
    }//GEN-LAST:event_chip_25ActionPerformed

    private void chip_50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chip_50ActionPerformed
        play("coins.wav", false); //sound of changing bet
        resetChip(); //reset all chip buttons
        chip_50.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_50_A.png"))); //saturation -45
        flag_chip_50 = true; //set the flag
        setBetAmount(50); //set the bet amount
    }//GEN-LAST:event_chip_50ActionPerformed

    private void leverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leverActionPerformed
        if(!flag_lever || checkError()){
        } else {
            leverDownAnimation(); 
        }
    }//GEN-LAST:event_leverActionPerformed

    private void chip_5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_5MouseEntered
        if(!flag_chip_5) chip_5.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_5_B.png"))); //luminosità +45
    }//GEN-LAST:event_chip_5MouseEntered

    private void chip_5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_5MouseExited
        if(!flag_chip_5) chip_5.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_5.png"))); //reset dell'immagine
    }//GEN-LAST:event_chip_5MouseExited

    private void chip_10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_10MouseEntered
        if(!flag_chip_10) chip_10.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_10_B.png"))); //luminosità +45
    }//GEN-LAST:event_chip_10MouseEntered

    private void chip_10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_10MouseExited
        if(!flag_chip_10) chip_10.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_10.png"))); //reset dell'immagine
    }//GEN-LAST:event_chip_10MouseExited

    private void chip_25MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_25MouseEntered
        if(!flag_chip_25) chip_25.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_25_B.png"))); //luminosità +45
    }//GEN-LAST:event_chip_25MouseEntered

    private void chip_25MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_25MouseExited
        if(!flag_chip_25) chip_25.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_25.png"))); //reset dell'immagine
    }//GEN-LAST:event_chip_25MouseExited

    private void chip_50MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_50MouseEntered
        if(!flag_chip_50) chip_50.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_50_A.png"))); //luminosità +45
    }//GEN-LAST:event_chip_50MouseEntered

    private void chip_50MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chip_50MouseExited
        if(!flag_chip_50) chip_50.setIcon(new ImageIcon(getClass().getResource("/Foto/chip_50.png"))); //reset dell'immagine
    }//GEN-LAST:event_chip_50MouseExited

    private void leverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leverMouseEntered

    }//GEN-LAST:event_leverMouseEntered

    private void leverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leverMouseExited
        
    }//GEN-LAST:event_leverMouseExited

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        pauseBackgroundMusic();
        String linea=nome+","+password+","+slotMachine.getBalance();
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("accountTemp.txt"));
            bw.write(linea);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(SlotMachineGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        MainMenu menu=new MainMenu();
        menu.setVisible(true);
    }//GEN-LAST:event_BackActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SlotMachineGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SlotMachineGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(SlotMachineGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton Back;
    javax.swing.JLabel EffectWin;
    javax.swing.JLabel balance_immagine;
    javax.swing.JLabel balance_value;
    javax.swing.JLabel bet_asset;
    javax.swing.JLabel bet_value;
    javax.swing.JButton chip_10;
    javax.swing.JButton chip_25;
    javax.swing.JButton chip_5;
    javax.swing.JButton chip_50;
    javax.swing.JLabel frame;
    javax.swing.JLabel jackpote_value;
    javax.swing.JButton lever;
    javax.swing.JLabel logo;
    javax.swing.JLayeredPane pannel;
    javax.swing.JLabel slot;
    javax.swing.JLabel slot0;
    javax.swing.JLabel slot1;
    javax.swing.JLabel slot2;
    javax.swing.JLabel wallpaper;
    // End of variables declaration//GEN-END:variables
}
