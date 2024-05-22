package casinò;

public enum SemeCarta {
    PICCHE, CUORI, FIORI, QUADRI;
    public char getEnglishInitial() {
        switch (this) {
            case PICCHE:
                return 'S'; // Spades
            case CUORI:
                return 'H'; // Hearts
            case FIORI:
                return 'C'; // Clubs
            case QUADRI:
                return 'D'; // Diamonds
            default:
                throw new IllegalArgumentException("Seme sconosciuto: " + this);
        }
    }
}
