/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package casinò;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import myslotmachine.SlotMachineGUI;

/**
 *
 * @author nicol
 */
public class Ippica extends javax.swing.JFrame {
    String nome;
    int credito;
    String password;
    /**
     * Creates new form Ippica
     */
    public Ippica() throws FileNotFoundException, IOException {
        initComponents();
        this.credito=caricacredito();
        impostaquote();
        impostaBottoni();
        impostaNomi();
        startTimer(); 
        impostaOff();
    }
    
    public void startThreadsAndAdvanceProgressBars(JProgressBar[] progressBars) {
    // Verifica che il numero di progress bar sia esattamente 5
    if (progressBars.length != 5) {
        throw new IllegalArgumentException("Devono esserci esattamente 5 progress bar.");
    }

    for (int i = 0; i < 5; i++) {
        final int index = i; // Variabile finale per l'uso nel thread

        Thread thread = new Thread(() -> {
            while (progressBars[index].getValue() < 100) {
                try {
                    // Simula un lavoro con una pausa
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // Avanza la progress bar
                SwingUtilities.invokeLater(() -> {
                    int currentValue = progressBars[index].getValue();
                    if (currentValue < 100) {
                        progressBars[index].setValue(currentValue + 1);
                    }
                });
            }
        });

        // Avvia il thread
        thread.start();
    }
}

    
    public void impostaOff(){
        jProgressBar1.setVisible(false);
        jProgressBar2.setVisible(false);
        jProgressBar3.setVisible(false);
        jProgressBar4.setVisible(false);
        jProgressBar5.setVisible(false);
        return;
    }
    
    public void impostaOn(){
        jProgressBar1.setVisible(true);
        jProgressBar2.setVisible(true);
        jProgressBar3.setVisible(true);
        jProgressBar4.setVisible(true);
        jProgressBar5.setVisible(true);
        return;
    }
    
    public void impostaNomi() throws FileNotFoundException{
        String riga = " ";
        int c = 0;
        ArrayList<String> names = new ArrayList<>();
        File inputFile = new File("input2.txt");
        Scanner reader = new Scanner(inputFile);
        while(reader.hasNextLine()){
            riga = reader.nextLine();
            names.add(riga);
        }
        Collections.shuffle(names);
        jLabel12.setText(names.get(0));
        jLabel13.setText(names.get(1));
        jLabel14.setText(names.get(2));
        jLabel15.setText(names.get(3));
        jLabel16.setText(names.get(4));
        return;
    }
    
    private void startTimer() {
        // Crea un ActionListener per il timer
        ActionListener actionListener = new ActionListener() {
            private int remainingSeconds = 2 * 60; // 5 minuti in secondi

            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;

                int minutes = remainingSeconds / 60;
                int seconds = remainingSeconds % 60;

                // Aggiorna jLabel3 usando SwingUtilities.invokeLater
                SwingUtilities.invokeLater(() -> {
                    jLabel3.setText(String.format("%d:%02d", minutes, seconds));
                });

                if (remainingSeconds <= 0) {
                    ((Timer) e.getSource()).stop();
                }
            }
        };

        // Crea e avvia il timer
        Timer timer = new Timer(1000, actionListener);
        timer.start();
    }
    
    public void impostaBottoni(){
        P1Cav1.setBackground(Color.WHITE);
        P1Cav2.setBackground(Color.WHITE);
        P1Cav3.setBackground(Color.WHITE);
        P1Cav4.setBackground(Color.WHITE);
        P1Cav5.setBackground(Color.WHITE);
        
        P12Cav1.setBackground(Color.WHITE);
        P12Cav2.setBackground(Color.WHITE);
        P12Cav3.setBackground(Color.WHITE);
        P12Cav4.setBackground(Color.WHITE);
        P12Cav5.setBackground(Color.WHITE);
        
        P123Cav1.setBackground(Color.WHITE);
        P123Cav2.setBackground(Color.WHITE);
        P123Cav3.setBackground(Color.WHITE);
        P123Cav4.setBackground(Color.WHITE);
        P123Cav5.setBackground(Color.WHITE);
    }
    
    public void impostaquote(){
        Random random = new Random();
        //primi posti
        P1Cav1.setText(String.format("%.2f", 3.1 + random.nextDouble() * 7.9));
        P1Cav2.setText(String.format("%.2f", 4.1 + random.nextDouble() * 9.9));
        P1Cav3.setText(String.format("%.2f", 6.1 + random.nextDouble() * 13.9));
        P1Cav4.setText(String.format("%.2f", 7.1 + random.nextDouble() * 18.9));
        P1Cav5.setText(String.format("%.2f", 8.1 + random.nextDouble() * 24.9));

        //secondi posti
        P12Cav1.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.5));
        P12Cav2.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.6));
        P12Cav3.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.8));
        P12Cav4.setText(String.format("%.2f", 1.5 + random.nextDouble() * 1.0));
        P12Cav5.setText(String.format("%.2f", 1.5 + random.nextDouble() * 2.0));

        //terzi posti
        P123Cav1.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.2));
        P123Cav2.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.3));
        P123Cav3.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.5));
        P123Cav4.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.7));
        P123Cav5.setText(String.format("%.2f", 1.0 + random.nextDouble() * 0.9));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        P1Cav4 = new javax.swing.JButton();
        P12Cav1 = new javax.swing.JButton();
        P1Cav5 = new javax.swing.JButton();
        P12Cav2 = new javax.swing.JButton();
        P123Cav1 = new javax.swing.JButton();
        P1Cav2 = new javax.swing.JButton();
        P123Cav5 = new javax.swing.JButton();
        P123Cav2 = new javax.swing.JButton();
        P1Cav3 = new javax.swing.JButton();
        P12Cav3 = new javax.swing.JButton();
        P123Cav3 = new javax.swing.JButton();
        P12Cav5 = new javax.swing.JButton();
        P123Cav4 = new javax.swing.JButton();
        P12Cav4 = new javax.swing.JButton();
        P1Cav1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(null);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Foto/back.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(0, 510, 60, 80);

        P1Cav4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P1Cav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1Cav4ActionPerformed(evt);
            }
        });
        jPanel1.add(P1Cav4);
        P1Cav4.setBounds(930, 320, 80, 80);

        P12Cav1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P12Cav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P12Cav1ActionPerformed(evt);
            }
        });
        jPanel1.add(P12Cav1);
        P12Cav1.setBounds(1010, 80, 80, 80);

        P1Cav5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P1Cav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1Cav5ActionPerformed(evt);
            }
        });
        jPanel1.add(P1Cav5);
        P1Cav5.setBounds(930, 400, 80, 80);

        P12Cav2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P12Cav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P12Cav2ActionPerformed(evt);
            }
        });
        jPanel1.add(P12Cav2);
        P12Cav2.setBounds(1010, 160, 80, 80);

        P123Cav1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P123Cav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P123Cav1ActionPerformed(evt);
            }
        });
        jPanel1.add(P123Cav1);
        P123Cav1.setBounds(1090, 80, 80, 80);

        P1Cav2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P1Cav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1Cav2ActionPerformed(evt);
            }
        });
        jPanel1.add(P1Cav2);
        P1Cav2.setBounds(930, 160, 80, 80);

        P123Cav5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P123Cav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P123Cav5ActionPerformed(evt);
            }
        });
        jPanel1.add(P123Cav5);
        P123Cav5.setBounds(1090, 400, 80, 80);

        P123Cav2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P123Cav2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P123Cav2ActionPerformed(evt);
            }
        });
        jPanel1.add(P123Cav2);
        P123Cav2.setBounds(1090, 160, 80, 80);

        P1Cav3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P1Cav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1Cav3ActionPerformed(evt);
            }
        });
        jPanel1.add(P1Cav3);
        P1Cav3.setBounds(930, 240, 80, 80);

        P12Cav3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P12Cav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P12Cav3ActionPerformed(evt);
            }
        });
        jPanel1.add(P12Cav3);
        P12Cav3.setBounds(1010, 240, 80, 80);

        P123Cav3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P123Cav3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P123Cav3ActionPerformed(evt);
            }
        });
        jPanel1.add(P123Cav3);
        P123Cav3.setBounds(1090, 240, 80, 80);

        P12Cav5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P12Cav5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P12Cav5ActionPerformed(evt);
            }
        });
        jPanel1.add(P12Cav5);
        P12Cav5.setBounds(1010, 400, 80, 80);

        P123Cav4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P123Cav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P123Cav4ActionPerformed(evt);
            }
        });
        jPanel1.add(P123Cav4);
        P123Cav4.setBounds(1090, 320, 80, 80);

        P12Cav4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P12Cav4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P12Cav4ActionPerformed(evt);
            }
        });
        jPanel1.add(P12Cav4);
        P12Cav4.setBounds(1010, 320, 80, 80);

        P1Cav1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        P1Cav1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1Cav1ActionPerformed(evt);
            }
        });
        jPanel1.add(P1Cav1);
        P1Cav1.setBounds(930, 80, 80, 80);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(810, 526, 90, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(1050, 506, 100, 40);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(670, 530, 64, 22);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("1-2 Posto");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(1010, 60, 80, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.add(jLabel20);
        jLabel20.setBounds(250, 526, 160, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("1-2-3 posto");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel4);
        jLabel4.setBounds(1090, 60, 80, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("1° Posto");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel6);
        jLabel6.setBounds(930, 60, 80, 20);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("1");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(820, 180, 90, 40);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Rectangle 46(2).png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(800, 160, 130, 90);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("1");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(820, 250, 90, 40);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Rectangle 46(2).png"))); // NOI18N
        jPanel1.add(jLabel9);
        jLabel9.setBounds(800, 230, 130, 90);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("1");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(820, 330, 90, 40);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Rectangle 46(2).png"))); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(800, 310, 130, 90);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("1");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(820, 410, 90, 40);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Rectangle 46(2).png"))); // NOI18N
        jPanel1.add(jLabel10);
        jLabel10.setBounds(800, 390, 130, 90);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("1");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(820, 100, 90, 40);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Rectangle 46(2).png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(800, 80, 130, 90);

        jProgressBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.add(jProgressBar1);
        jProgressBar1.setBounds(50, 130, 700, 90);

        jProgressBar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.add(jProgressBar2);
        jProgressBar2.setBounds(50, 220, 700, 90);

        jProgressBar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.add(jProgressBar3);
        jProgressBar3.setBounds(50, 310, 700, 90);

        jProgressBar4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.add(jProgressBar4);
        jProgressBar4.setBounds(50, 400, 700, 90);

        jProgressBar5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.add(jProgressBar5);
        jProgressBar5.setBounds(50, 40, 700, 90);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("In euro: ");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(593, 520, 80, 40);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Rectangle 46.png"))); // NOI18N
        jPanel1.add(jLabel18);
        jLabel18.setBounds(1030, 500, 140, 60);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Vincita:  ");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(753, 520, 60, 40);

        jButton1.setText("Conferma");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(505, 530, 90, 23);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("IL tuo credito: ");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(120, 530, 120, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Group 47.png"))); // NOI18N
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jLabel1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        jLabel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -190, 1920, 1080);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void P1Cav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1Cav4ActionPerformed
        String valore = jTextField1.getText();
        String quota = P1Cav4.getText().replace(",", "");
        if (P1Cav4.getBackground() == Color.WHITE) {
            impostaBottoni();
            P1Cav4.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P1Cav4.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P1Cav4ActionPerformed

    private void P12Cav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P12Cav1ActionPerformed
        String valore = jTextField1.getText();
        String quota = P12Cav1.getText().replace(",", "");
        if (P12Cav1.getBackground() == Color.WHITE) {
            impostaBottoni();
            P12Cav1.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P12Cav1.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P12Cav1ActionPerformed

    private void P1Cav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1Cav5ActionPerformed
        String valore = jTextField1.getText();
        String quota = P1Cav5.getText().replace(",", "");
        if (P1Cav5.getBackground() == Color.WHITE) {
            impostaBottoni();
            P1Cav5.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P1Cav5.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P1Cav5ActionPerformed

    private void P12Cav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P12Cav2ActionPerformed
        String valore = jTextField1.getText();
        String quota = P12Cav2.getText().replace(",", "");
        if (P12Cav2.getBackground() == Color.WHITE) {
            impostaBottoni();
            P12Cav2.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P12Cav2.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P12Cav2ActionPerformed

    private void P123Cav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P123Cav1ActionPerformed
        String valore = jTextField1.getText();
        String quota = P123Cav1.getText().replace(",", "");
        if (P123Cav1.getBackground() == Color.WHITE) {
            impostaBottoni();
            P123Cav1.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P123Cav1.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P123Cav1ActionPerformed

    private void P1Cav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1Cav2ActionPerformed
        String valore = jTextField1.getText();
        String quota = P1Cav2.getText().replace(",", "");
        if (P1Cav2.getBackground() == Color.WHITE) {
            impostaBottoni();
            P1Cav2.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P1Cav2.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P1Cav2ActionPerformed

    private void P123Cav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P123Cav5ActionPerformed
        String valore = jTextField1.getText();
        String quota = P123Cav5.getText().replace(",", "");
        if (P123Cav5.getBackground() == Color.WHITE) {
            impostaBottoni();
            P123Cav5.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P123Cav5.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P123Cav5ActionPerformed

    private void P123Cav2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P123Cav2ActionPerformed
        String valore = jTextField1.getText();
        String quota = P123Cav2.getText().replace(",", "");
        if (P123Cav2.getBackground() == Color.WHITE) {
            impostaBottoni();
            P123Cav2.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P123Cav2.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P123Cav2ActionPerformed

    private void P1Cav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1Cav3ActionPerformed
        String valore = jTextField1.getText();
        String quota = P1Cav3.getText().replace(",", "");
        if (P1Cav3.getBackground() == Color.WHITE) {
            impostaBottoni();
            P1Cav3.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P1Cav3.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P1Cav3ActionPerformed

    private void P12Cav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P12Cav3ActionPerformed
        String valore = jTextField1.getText();
        String quota = P12Cav3.getText().replace(",", "");
        if (P12Cav3.getBackground() == Color.WHITE) {
            impostaBottoni();
            P12Cav3.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P12Cav3.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P12Cav3ActionPerformed

    private void P123Cav3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P123Cav3ActionPerformed
        String valore = jTextField1.getText();
        String quota = P123Cav3.getText().replace(",", "");
        if (P123Cav3.getBackground() == Color.WHITE) {
            impostaBottoni();
            P123Cav3.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P123Cav3.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P123Cav3ActionPerformed

    private void P12Cav5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P12Cav5ActionPerformed
        String valore = jTextField1.getText();
        String quota = P12Cav5.getText().replace(",", "");
        if (P12Cav5.getBackground() == Color.WHITE) {
            impostaBottoni();
            P12Cav5.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P12Cav5.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P12Cav5ActionPerformed

    private void P123Cav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P123Cav4ActionPerformed
        String valore = jTextField1.getText();
        String quota = P123Cav4.getText().replace(",", "");
        if (P123Cav4.getBackground() == Color.WHITE) {
            impostaBottoni();
            P123Cav4.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P123Cav4.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P123Cav4ActionPerformed

    private void P12Cav4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P12Cav4ActionPerformed
        String valore = jTextField1.getText();
        String quota = P12Cav4.getText().replace(",", "");
        if (P12Cav4.getBackground() == Color.WHITE) {
            impostaBottoni();
            P12Cav4.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P12Cav4.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P12Cav4ActionPerformed

    private void P1Cav1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1Cav1ActionPerformed
        String valore = jTextField1.getText();
        String quota = P1Cav1.getText().replace(",", "");
        if (P1Cav1.getBackground() == Color.WHITE) {
            impostaBottoni();
            P1Cav1.setBackground(Color.GREEN);
            Double val = Double.parseDouble(valore);
            Double quo = Double.parseDouble(quota);
            Double ris = (val * quo)/100;
            String risultato = Double.toString(ris);
            jLabel2.setText(risultato);
        } else {
            P1Cav1.setBackground(Color.WHITE);
            jLabel2.setText(" ");
        }
    }//GEN-LAST:event_P1Cav1ActionPerformed

    @SuppressWarnings("empty-statement")
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
    // Recupera i valori di puntata e credito e rimuovi eventuali spazi
    String puntataStr = jTextField1.getText().trim();
    String creditoStr = jLabel20.getText().trim();

    // Debug: Stampa i valori per assicurarsi che siano corretti
    System.out.println("Puntata inserita: " + puntataStr);
    System.out.println("Credito disponibile: " + creditoStr);

    // Converti le stringhe in numeri interi
    int puntata = Integer.parseInt(puntataStr);
    int credito = Integer.parseInt(creditoStr);

    // Controlla se la puntata è maggiore del credito
    if (puntata > credito) {
        JOptionPane.showMessageDialog(null, "Credito insufficiente.", "Errore", JOptionPane.ERROR_MESSAGE);
    } else {
        // Procedi con l'azione se la puntata è valida
        impostaOn();
        JProgressBar[] progressBars = {jProgressBar1, jProgressBar2, jProgressBar3, jProgressBar4, jProgressBar5};
        startThreadsAndAdvanceProgressBars(progressBars);
    }
    // Resetta il campo di input
    jTextField1.setText("");
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Inserisci un valore numerico valido.", "Errore", JOptionPane.ERROR_MESSAGE);
    jTextField1.setText(""); // Resetta il campo di input solo in caso di errore di formattazione
    e.printStackTrace(); // Stampa l'eccezione per il debug
}

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String linea=nome+","+password+","+credito;
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("accountTemp.txt"));
            bw.write(linea);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Ippica.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        MainMenu menu=new MainMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    public int caricacredito() throws IOException{
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("accountTemp.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SlotMachineGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String riga=br.readLine();
        String[] dividi=riga.split(",");
        String credito=dividi[2];
        nome=dividi[0];
        password=dividi[1];
        jLabel20.setText(credito);
        int credito2 = Integer.parseInt(credito);
        return credito2;
    }
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Ippica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ippica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ippica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ippica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Ippica().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Ippica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton P123Cav1;
    private javax.swing.JButton P123Cav2;
    private javax.swing.JButton P123Cav3;
    private javax.swing.JButton P123Cav4;
    private javax.swing.JButton P123Cav5;
    private javax.swing.JButton P12Cav1;
    private javax.swing.JButton P12Cav2;
    private javax.swing.JButton P12Cav3;
    private javax.swing.JButton P12Cav4;
    private javax.swing.JButton P12Cav5;
    private javax.swing.JButton P1Cav1;
    private javax.swing.JButton P1Cav2;
    private javax.swing.JButton P1Cav3;
    private javax.swing.JButton P1Cav4;
    private javax.swing.JButton P1Cav5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
