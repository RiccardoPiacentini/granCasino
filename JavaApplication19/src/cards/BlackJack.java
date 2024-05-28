package cards;

import casinò.MainMenu;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class BlackJack {
    private class Card {
        String value;
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return value + "-" + type;
        }

        public int getValue() {
            if ("AJQK".contains(value)) {
                if (value.equals("A")) {
                    return 11;
                }
                return 10;
            }
            return Integer.parseInt(value);
        }

        public boolean isAce() {
            return value.equals("A");
        }

        public String getImagePath() {
            return toString() + ".png";
        }
    }

    ArrayList<Card> deck;
    Random random = new Random();

    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerAceCount;

    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;

    int boardWidth = 600;
    int boardHeight = boardWidth;

    int cardWidth = 110;
    int cardHeight = 154;

    JFrame frame = new JFrame("BlackJack");
    JPanel gamePanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                Image hiddenCardImg = new ImageIcon(getClass().getResource("BACK.png")).getImage();
                int visibleDealerSum = 0;
                if (!stayButton.isEnabled()) {
                    hiddenCardImg = new ImageIcon(getClass().getResource(hiddenCard.getImagePath())).getImage();
                    visibleDealerSum = dealerSum;
                } else {
                    visibleDealerSum = dealerHand.get(0).getValue();
                }
                g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

                for (int i = 0; i < dealerHand.size(); i++) {
                    Card card = dealerHand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
                    if (stayButton.isEnabled()) {
                        visibleDealerSum += card.getValue();
                    }
                }

                for (int i = 0; i < playerHand.size(); i++) {
                    Card card = playerHand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
                }

                if (!stayButton.isEnabled()) {
                    dealerSum = reduceDealerAce();
                    playerSum = reducePlayerAce();
                    String message = "";
                    if (playerSum > 21) {
                        message = "Hai perso!";
                        credit -= 5;
                    } else if (dealerSum > 21) {
                        message = "Hai vinto!";
                        credit += 10;
                    } else if (playerSum == dealerSum) {
                        message = "Pareggio!";
                    } else if (playerSum > dealerSum) {
                        message = "Hai vinto!";
                        credit += 10;
                    } else {
                        message = "Hai perso!";
                        credit -= 5;
                    }

                    g.setFont(new Font("Arial", Font.PLAIN, 30));
                    g.setColor(Color.white);
                    g.drawString(message, 220, 250);

                    buttonPanel.removeAll();
                    buttonPanel.add(replayButton);
                    buttonPanel.add(mainMenuButton);
                    buttonPanel.revalidate();
                    buttonPanel.repaint();
                }

                // Draw the credit on the screen
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.setColor(Color.white);
                g.drawString("Credito: " + credit, 20, 295);
                g.drawString("Puntata: 5€", 475, 295);

                // Draw the sums above the cards
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.setColor(Color.yellow);
                if(stayButton.isEnabled()){
                    visibleDealerSum/=2;
                }
                g.drawString("Banco: " + visibleDealerSum, 20, 190);
                g.drawString("Giocatore: " + playerSum, 20, 315);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Carta");
    JButton stayButton = new JButton("Stai");
    JButton replayButton = new JButton("Rigioca");
    JButton mainMenuButton = new JButton("Main Menu");

    private int credit;

    public BlackJack() {
        readCreditFromFile(); // Read credit at the beginning

        startGame();

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Card card = deck.remove(deck.size() - 1);
                playerSum += card.getValue();
                playerAceCount += card.isAce() ? 1 : 0;
                playerHand.add(card);
                if (reducePlayerAce() > 21) {
                    hitButton.setEnabled(false);
                }
                gamePanel.repaint();
            }
        });

        stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (dealerSum < 17) {
                    Card card = deck.remove(deck.size() - 1);
                    dealerSum += card.getValue();
                    dealerAceCount += card.isAce() ? 1 : 0;
                    dealerHand.add(card);
                }
                gamePanel.repaint();
            }
        });

        replayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
                hitButton.setEnabled(true);
                stayButton.setEnabled(true);
                buttonPanel.removeAll();
                buttonPanel.add(hitButton);
                buttonPanel.add(stayButton);
                buttonPanel.revalidate();
                buttonPanel.repaint();
                gamePanel.repaint();
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveCreditToFile(); // Save credit before returning to main menu
                frame.dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new MainMenu().setVisible(true);
                    }
                });
            }
        });

        gamePanel.repaint();
    }

    public void startGame() {
        buildDeck();
        shuffleDeck();

        dealerHand = new ArrayList<Card>();
        dealerSum = 0;
        dealerAceCount = 0;

        hiddenCard = deck.remove(deck.size() - 1);
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        Card card = deck.remove(deck.size() - 1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);

        playerHand = new ArrayList<Card>();
        playerSum = 0;
        playerAceCount = 0;

        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }
    }

    public void buildDeck() {
        deck = new ArrayList<Card>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (int i = 0; i < types.length; i++) {
            for (int j = 0; j < values.length; j++) {
                Card card = new Card(values[j], types[i]);
                deck.add(card);
            }
        }
    }

    public void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
    }

    public int reducePlayerAce() {
        while (playerSum > 21 && playerAceCount > 0) {
            playerSum -= 10;
            playerAceCount -= 1;
        }
        return playerSum;
    }

    public int reduceDealerAce() {
        while (dealerSum > 21 && dealerAceCount > 0) {
            dealerSum -= 10;
            dealerAceCount -= 1;
        }
        return dealerSum;
    }

    public void readCreditFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("accountTemp.txt"))) {
            String line = br.readLine();  // Read the first line from the file
            if (line != null) {
                String[] parts = line.split(",");  // Split the line by comma
                if (parts.length >= 3) {
                    credit = Integer.parseInt(parts[2].trim());  // Get the third element and convert to int
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCreditToFile() {
        try {
            File file = new File("accountTemp.txt");
            if (!file.exists()) {
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;

            if ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    parts[2] = Integer.toString(credit); // Update the credit value
                    line = String.join(",", parts);
                }
            }
            content.append(line);
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(content.toString());
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BlackJack();
            }
        });
    }
}
