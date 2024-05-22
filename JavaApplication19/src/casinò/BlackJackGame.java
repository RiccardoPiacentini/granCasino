/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package casinò;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author ricpi
 */
public class BlackJackGame extends javax.swing.JFrame {
    private final List<JLabel> playerCardLabels;
    private final List<JLabel> dealerCardLabels;

    private final MazzoCarte mazzo;
    private final Giocatore giocatore;
    private final Giocatore dealer;
    private final Scanner scanner;
    /**
     * Creates new form BlackJackGame
     */
    public BlackJackGame() {
        initComponents();
        mazzo = new MazzoCarte();
        giocatore = new Giocatore();
        dealer = new Giocatore();
        scanner = new Scanner(System.in);

        // Inizializza le liste delle etichette delle carte
        playerCardLabels = new ArrayList<>();
        dealerCardLabels = new ArrayList<>();

        // Aggiungi etichette alla lista (assicurati di avere le etichette nel GUI Builder)
        playerCardLabels.add(PlayerCard);
        playerCardLabels.add(PlayerCard1);
        // Aggiungi altre etichette per le carte del giocatore, se necessario

        dealerCardLabels.add(DealerCard);
        // Aggiungi altre etichette per le carte del dealer, se necessario

        iniziaPartita();
    }

     private void iniziaPartita() {
        // Il dealer pesca due carte
        dealer.prendiCarta(mazzo.pescaCarta());
        dealer.prendiCarta(mazzo.pescaCarta());

        // Il giocatore pesca due carte
        giocatore.prendiCarta(mazzo.pescaCarta());
        giocatore.prendiCarta(mazzo.pescaCarta());

        mostraManoGiocatore();
        mostraManoDealerParziale();
    }
    private void mostraManoGiocatore() {
        System.out.println("Mano del giocatore:");

        // Assicurati che ci siano abbastanza etichette per mostrare tutte le carte
        while (playerCardLabels.size() < giocatore.getMano().size()) {
            JLabel newLabel = new JLabel();
            playerCardLabels.add(newLabel);
            jPanel1.add(newLabel);
            // Aggiorna layout del jPanel1 se necessario
        }

        for (int i = 0; i < giocatore.getMano().size(); i++) {
            Carta carta = giocatore.getMano().get(i);
            System.out.println(carta);
            String cartaFisica = carta.toShortString();
            System.out.println(cartaFisica);
            String cartaFisicaPath = "./cards/" + cartaFisica + ".png";
            ImageIcon icona = new ImageIcon(cartaFisicaPath);
            playerCardLabels.get(i).setIcon(icona);
        }
        System.out.println("Punteggio del giocatore: " + giocatore.getPunteggio());
    }


    private void mostraManoDealerParziale() {
        System.out.println("Mano parziale del dealer:");
        Carta primaCarta = dealer.getMano().get(0);
        System.out.println(primaCarta);
        String cartaFisica = primaCarta.toShortString();
        String cartaFisicaPath = "./cards/" + cartaFisica + ".png";
        ImageIcon icona = new ImageIcon(cartaFisicaPath);
        dealerCardLabels.get(0).setIcon(icona);
    }


    private void mostraManoDealer() {
        System.out.println("Mano del dealer:");

        // Assicurati che ci siano abbastanza etichette per mostrare tutte le carte
        while (dealerCardLabels.size() < dealer.getMano().size()) {
            JLabel newLabel = new JLabel();
            dealerCardLabels.add(newLabel);
            jPanel1.add(newLabel);
            // Aggiorna layout del jPanel1 se necessario
        }

        for (int i = 0; i < dealer.getMano().size(); i++) {
            Carta carta = dealer.getMano().get(i);
            System.out.println(carta);
            String cartaFisica = carta.toShortString();
            String cartaFisicaPath = "./cards/" + cartaFisica + ".png";
            ImageIcon icona = new ImageIcon(cartaFisicaPath);
            dealerCardLabels.get(i).setIcon(icona);
        }
        System.out.println("Punteggio del dealer: " + dealer.getPunteggio());
    }


    private void mostraRisultato() {
        int punteggioGiocatore = giocatore.getPunteggio();
        int punteggioDealer = dealer.getPunteggio();

        String risultato;
        if (punteggioGiocatore > 21) {
            risultato = "Hai sballato! Il dealer vince.";
        } else if (punteggioDealer > 21 || punteggioGiocatore > punteggioDealer) {
            risultato = "Hai vinto!";
        } else if (punteggioGiocatore < punteggioDealer) {
            risultato = "Il dealer vince.";
        } else {
            risultato = "Pareggio.";
        }

        JOptionPane.showMessageDialog(this, risultato);
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
        CartaJB = new javax.swing.JButton();
        StaiJB = new javax.swing.JButton();
        DealerCard = new javax.swing.JLabel();
        HandlerCoveredCard = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PlayerCard = new javax.swing.JLabel();
        PlayerCard1 = new javax.swing.JLabel();
        Sfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        CartaJB.setBorderPainted(false);
        CartaJB.setContentAreaFilled(false);
        CartaJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CartaJBActionPerformed(evt);
            }
        });
        jPanel1.add(CartaJB);
        CartaJB.setBounds(850, 430, 120, 60);

        StaiJB.setBorderPainted(false);
        StaiJB.setContentAreaFilled(false);
        StaiJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaiJBActionPerformed(evt);
            }
        });
        jPanel1.add(StaiJB);
        StaiJB.setBounds(850, 370, 120, 50);
        jPanel1.add(DealerCard);
        DealerCard.setBounds(560, 210, 60, 86);
        jPanel1.add(HandlerCoveredCard);
        HandlerCoveredCard.setBounds(660, 210, 60, 87);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cards/BACK_1.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(780, 200, 87, 75);
        jPanel1.add(PlayerCard);
        PlayerCard.setBounds(560, 420, 60, 87);
        jPanel1.add(PlayerCard1);
        PlayerCard1.setBounds(696, 420, 60, 87);

        Sfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Group 44.png"))); // NOI18N
        jPanel1.add(Sfondo);
        Sfondo.setBounds(0, 0, 1280, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StaiJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaiJBActionPerformed
         // Turno del dealer
        while (dealer.getPunteggio() < 17) {
            dealer.prendiCarta(mazzo.pescaCarta());
        }
        mostraManoDealer();
        mostraRisultato();
        resetGame();
    }//GEN-LAST:event_StaiJBActionPerformed
    private void resetGame() {
        giocatore.svuotaMano();
        dealer.svuotaMano();
        mazzo.mescolaMazzo();
        iniziaPartita();
    }
    private void CartaJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CartaJBActionPerformed
        giocatore.prendiCarta(mazzo.pescaCarta());
        mostraManoGiocatore();

        if (giocatore.getPunteggio() > 21) {
            mostraRisultato();
            resetGame();
        }
    }//GEN-LAST:event_CartaJBActionPerformed

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
            java.util.logging.Logger.getLogger(BlackJackGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlackJackGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlackJackGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlackJackGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlackJackGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CartaJB;
    private javax.swing.JLabel DealerCard;
    private javax.swing.JLabel HandlerCoveredCard;
    private javax.swing.JLabel PlayerCard;
    private javax.swing.JLabel PlayerCard1;
    private javax.swing.JLabel Sfondo;
    private javax.swing.JButton StaiJB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
