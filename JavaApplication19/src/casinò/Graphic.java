/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package casinò;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ricpi
 */
public class Graphic extends javax.swing.JFrame {
    String temp="accountTemp.txt";
    
    double credito_file;
    /**
     * Creates new form Graphic
     */
    public Graphic() {
        initComponents();
        ImageIcon icon = new ImageIcon("Resources/Logo_883.svg.png");
        setIconImage(icon.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        TF_username = new javax.swing.JTextField();
        PF_password = new javax.swing.JPasswordField();
        B_registrati = new javax.swing.JButton();
        B_login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));
        setMaximumSize(new java.awt.Dimension(1820, 980));
        setPreferredSize(new java.awt.Dimension(1250, 720));
        setSize(new java.awt.Dimension(1250, 720));

        jPanel2.setLayout(null);
        jPanel2.add(TF_username);
        TF_username.setBounds(560, 400, 120, 50);
        jPanel2.add(PF_password);
        PF_password.setBounds(560, 450, 120, 50);

        B_registrati.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        B_registrati.setText("Registrati");
        B_registrati.setPreferredSize(new java.awt.Dimension(8, 23));
        B_registrati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_registratiActionPerformed(evt);
            }
        });
        jPanel2.add(B_registrati);
        B_registrati.setBounds(560, 560, 120, 40);

        B_login.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        B_login.setText("Login");
        B_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_loginActionPerformed(evt);
            }
        });
        jPanel2.add(B_login);
        B_login.setBounds(560, 510, 120, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Group 40.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, 0, 1250, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1251, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_loginActionPerformed
        String username = TF_username.getText();
        char[] Password_char = PF_password.getPassword();
        String Password = new String(Password_char);
        File inputFile = new File("input.txt");

        try (Scanner reader = new Scanner(inputFile);
             FileWriter fileWriter = new FileWriter("accountTemp.txt");
             BufferedWriter bw = new BufferedWriter(fileWriter)) {

            boolean loginSuccessful = false;

            while (reader.hasNextLine()) {
                String riga = reader.nextLine();
                System.out.println("Riga letta: " + riga); // Debug

                if (riga != null && !riga.trim().isEmpty()) {
                    String[] dividi = riga.split(",");
                    if (dividi.length == 3) {
                        String username_file = dividi[0];
                        String password_file = dividi[1];
                        double credito_file = Double.parseDouble(dividi[2]);

                        if (username_file.equals(username) && password_file.equals(Password)) {
                            bw.write(riga);
                            bw.close();
                            System.out.println("Login riuscito: " + riga); // Debug
                            this.dispose();
                            MainMenu men = new MainMenu();
                            men.setVisible(true);
                            loginSuccessful = true;
                            break;
                        }
                    } else {
                        System.out.println("Formato della riga non valido: " + riga); // Debug
                    }
                }
            }

            if (!loginSuccessful) {
                JOptionPane.showMessageDialog(null, "Password o Username errato.", "Errore", JOptionPane.ERROR_MESSAGE);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Graphic.class.getName()).log(Level.SEVERE, "File non trovato", ex);
        } catch (IOException ex) {
            Logger.getLogger(Graphic.class.getName()).log(Level.SEVERE, "Errore di input/output", ex);
        }
    }//GEN-LAST:event_B_loginActionPerformed

    private void B_registratiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_registratiActionPerformed
        Signin frame = new Signin();
        frame.setVisible(true);
    }//GEN-LAST:event_B_registratiActionPerformed
    public double getCredito(){
        return credito_file;
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
            java.util.logging.Logger.getLogger(Graphic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Graphic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Graphic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Graphic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Graphic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_login;
    private javax.swing.JButton B_registrati;
    private javax.swing.JPasswordField PF_password;
    private javax.swing.JTextField TF_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

}
