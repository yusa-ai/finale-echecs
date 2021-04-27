package pieces;

import echecs.IJoueur;

public class Tour extends Pièce {
    private static final String SYMBOLE = "T";

    public Tour(IJoueur joueur, int y, int x) {
        super(joueur, y, x);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }
}
