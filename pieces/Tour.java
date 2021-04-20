package pieces;

import echecs.Joueur;

public class Tour extends Pièce {
    private static final String SYMBOLE = "T";

    public Tour(Joueur joueur, int x, int y) {
        super(joueur, x, y);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }
}
