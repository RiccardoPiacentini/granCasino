package casinò;

public class Carta {
    private final ValoreCarta valore;
    private final SemeCarta seme;

    public Carta(ValoreCarta valore, SemeCarta seme) {
        this.valore = valore;
        this.seme = seme;
    }
    public String toShortString() {
        return valore.getValore() + "-" + seme.getEnglishInitial();
    }
    public int getValore() {
        return valore.getValore();
    }

    public String toString() {
        return valore + " di " + seme;
    }
    
}
