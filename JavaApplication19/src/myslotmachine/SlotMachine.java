package myslotmachine;
import java.util.Random;

/**
 * @author joel
 */


import java.util.Random;

public class SlotMachine {
    private int[] extraction; // array to hold the extracted numbers
    private int jackpot; // current jackpot amount
    private int balance; // player's balance
    private int bet; // current bet amount

    // Constructor for SlotMachine class
    public SlotMachine(int bet, int balance, int jackpot) {
        extraction = new int[3]; // initialize the extraction array with 3 slots
        this.jackpot = jackpot;
        this.balance = balance;
        this.bet = bet;
    }

    // Method to simulate the extraction of numbers
    public void extract() {
        Random rand = new Random();

        // Generate random numbers for each slot in the extraction array
        for (int i = 0; i < 3; i++) {
            extraction[i] = rand.nextInt(5); // numbers range from 0 to 4
        }

        // Debugging purposes
        // extraction[0] = 1;
        // extraction[1] = 1;
        // extraction[2] = 1;

        // Increment the jackpot only if no other combination has been won
        if (!wonCombination() && !wonTwoSevens() && !wonJackpot()) {
            balance -= bet; // decrease player's balance by the bet amount
            jackpot += bet; // increase jackpot by the bet amount
        }
    }

    // Method to check if a combination has been won
    public boolean wonCombination() {
        boolean flag = false;

        // Check if all three numbers in the extraction array are the same
        if (extraction[0] == extraction[1] && extraction[0] == extraction[2] && !wonJackpot()) {
            flag = true;

            // Double the bet amount and add it to the balance if it's less than the jackpot
            if (bet * 2 > jackpot) {
                balance += jackpot;
                jackpot = 0;
            } else {
                balance += bet * 2;
                jackpot -= 2 * bet;
            }
        }

        return flag;
    }

    // Method to check if the jackpot has been won
    public boolean wonJackpot() {
        boolean flag = false;

        // Check if all three numbers in the extraction array are 1 (representing jackpot)
        if (extraction[0] == 1 && extraction[1] == 1 && extraction[2] == 1) {
            flag = true;

            balance += jackpot; // add the jackpot amount to the balance
            jackpot = 0; // reset the jackpot amount to 0
        }

        return flag;
    }

    // Method to check if a combination of two sevens has been won
    public boolean wonTwoSevens() {
        boolean flag = false;

        // Check if two out of three numbers in the extraction array are 1 (representing seven)
        if ((extraction[0] == 1 && extraction[1] == 1 && extraction[2] != 1) ||
            (extraction[0] == 1 && extraction[2] == 1 && extraction[1] != 1) ||
            (extraction[1] == 1 && extraction[2] == 1 && extraction[0] != 1)) {
            flag = true;

            // Triple the bet amount and add it to the balance if it's less than the jackpot
            if (bet * 3 > jackpot) {
                balance += jackpot;
                jackpot = 0;
            } else {
                balance += bet * 3;
                jackpot -= 3 * bet;
            }
        }

        return flag;
    }
    
    // Getter method to retrieve a number from the extraction array at a specific index
    public int getNumber(int index){
        return extraction[index];
    }

    // Getter methods for retrieving jackpot, balance, and bet amounts
    public int getJackpot() {
        return jackpot;
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    // Setter method to set the bet amount
    public void setBet(int bet) {
        this.bet = bet;
    }
}
