package casinò;

import java.io.*;
import java.util.Scanner;

public class Account extends javax.swing.JFrame {
    public String user="ciao";
    public Account() {
        initComponents();
        impostaAccount();
    }

    public void impostaAccount() {
    File file = new File("accountTemp.txt");
    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String username = parts[0];
                String credito = parts[2];
                jLabel1.setText(username);
                jLabel2.setText("Credito: "+credito+"€"); // Modifica qui per visualizzare il credito correttamente
            } else {
                System.out.println("Formato riga non valido: " + line);
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("File non trovato: " + e.getMessage());
    }
    return;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Deposita = new javax.swing.JButton();
        Preleva = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        L_user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 720));
        jPanel1.setLayout(null);

        Deposita.setText("Deposita");
        jPanel1.add(Deposita);
        Deposita.setBounds(300, 280, 90, 30);

        Preleva.setText("Preleva");
        jPanel1.add(Preleva);
        Preleva.setBounds(60, 280, 90, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Cancella Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(50, 430, 152, 42);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(50, 170, 80, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel2);
        jLabel2.setBounds(220, 170, 100, 20);

        L_user.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        L_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Group 65.png"))); // NOI18N
        jPanel1.add(L_user);
        L_user.setBounds(0, 0, 500, 640);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String usernameDaCancellare = "username_da_cancellare";
        String nomeFile = "percorso_del_tuo_file.txt";
        String nomeFileTemporaneo = "temp.txt";

        try {
            File fileOriginale = new File(nomeFile);
            File fileTemporaneo = new File(nomeFileTemporaneo);

            Scanner scanner = new Scanner(fileOriginale);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemporaneo));

            while (scanner.hasNextLine()) {
                String riga = scanner.nextLine();
                if (!riga.startsWith(usernameDaCancellare)) {
                    writer.write(riga);
                    writer.newLine();
                }
            }

            scanner.close();
            writer.close();

            // Sovrascrivi il file originale con il file temporaneo
            fileOriginale.delete();
            fileTemporaneo.renameTo(fileOriginale);

            System.out.println("Riga con username cancellata con successo!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
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
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Account.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Account().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Deposita;
    private javax.swing.JLabel L_user;
    private javax.swing.JButton Preleva;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
