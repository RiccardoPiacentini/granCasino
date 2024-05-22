package casinò;
import java.util.Random;

class Horse implements Runnable {
    private String name;
    private int distance;
    private static final int MAX_DISTANCE = 1000;
    private static final int MIN_DISTANCE = 100;
    private static final int STEP_RANGE = 50;
    private static final Random RANDOM = new Random();

    public Horse(String name) {
        this.name = name;
        this.distance = 0;
    }

    public void run() {
        System.out.println(name + " è partito!");
        while (distance < MAX_DISTANCE) {
            int step = RANDOM.nextInt(STEP_RANGE) + MIN_DISTANCE;
            distance += step;
            System.out.println(name + " ha percorso " + step + " metri. Distanza totale: " + distance + " metri.");
            try {
                Thread.sleep(100); // Simula il tempo di attesa tra un passo e l'altro
            } catch (InterruptedException e) {
                System.out.println(name + " è stato interrotto.");
                return;
            }
        }
        System.out.println(name + " ha terminato la corsa!");
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }
}

public class Cavalli {
    public static String runRaceAndGetWinner() {
        Horse[] horses = new Horse[5]; // Creiamo 5 cavalli
        Thread[] threads = new Thread[5]; // Un thread per ogni cavallo

        // Inizializziamo i cavalli e i loro threads
        for (int i = 0; i < 5; i++) {
            horses[i] = new Horse("Cavallo " + (i + 1));
            threads[i] = new Thread(horses[i]);
        }

        // Avviamo la corsa
        System.out.println("La corsa inizia!");
        for (int i = 0; i < 5; i++) {
            threads[i].start(); // Avviamo i threads dei cavalli
        }

        // Attendi che tutti i cavalli terminino la corsa
        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join(); // Attendi che il thread del cavallo termini
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // La corsa è finita, determiniamo il vincitore
        int maxDistance = 0;
        String winner = "";
        for (int i = 0; i < 5; i++) {
            if (horses[i].getDistance() > maxDistance) {
                maxDistance = horses[i].getDistance();
                winner = horses[i].getName();
            }
        }
        return winner;
    }

    public static void main(String[] args) {
        String winner = runRaceAndGetWinner();
        System.out.println("Il vincitore è " + winner + "!");
    }
}
