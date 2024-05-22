package casinò;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazzoCarte {
    private final List<Carta> mazzo;

    public MazzoCarte() {
        mazzo = new ArrayList<>();
        for (SemeCarta seme : SemeCarta.values()) {
            for (ValoreCarta valore : ValoreCarta.values()) {
                mazzo.add(new Carta(valore, seme));
            }
        }
        mescolaMazzo();
    }

    public void mescolaMazzo() {
        Collections.shuffle(mazzo);
    }

    public Carta pescaCarta() {
        return mazzo.remove(0);
    }
}
