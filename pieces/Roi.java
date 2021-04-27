package pieces;

import echecs.IJoueur;

public class Roi extends Pièce {
    private static final String SYMBOLE = "R";

    public Roi(IJoueur joueur, int y, int x) {
        super(joueur, y, x);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }

    @Override
    public boolean craintMat() {
        return true;
    }
}
