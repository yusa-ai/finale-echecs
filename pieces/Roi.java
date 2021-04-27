package pieces;

import echecs.IJoueur;

public class Roi extends Pièce {
    private static final String SYMBOLE = "R";

    public Roi(IJoueur joueur, int x, int y) {
        super(joueur, x, y);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }

}
