package casinò;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private final List<Carta> mano;

    public Giocatore() {
        mano = new ArrayList<>();
    }

    public void prendiCarta(Carta carta) {
        mano.add(carta);
    }

    public List<Carta> getMano() {
        return mano;
    }
    public void svuotaMano() {
        mano.clear();
    }
    public int getPunteggio() {
        int punteggio = 0;
        int numeroAssi = 0;

        for (Carta carta : mano) {
            punteggio += carta.getValore();
            if (carta.getValore() == 11) {
                numeroAssi++;
            }
        }

        while (punteggio > 21 && numeroAssi > 0) {
            punteggio -= 10;
            numeroAssi--;
        }
            
        return punteggio;
    }
}
