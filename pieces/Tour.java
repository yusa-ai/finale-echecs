package pieces;

import echecs.IJoueur;

public class Tour extends Pièce {
    private static final String SYMBOLE = "T";

    public Tour(IJoueur joueur, int x, int y) {
        super(joueur, x, y);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }
}
