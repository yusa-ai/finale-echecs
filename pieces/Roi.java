package pieces;

import echecs.Joueur;

public class Roi extends Pièce {
    private static final String SYMBOLE = "R";

    public Roi(Joueur joueur, int x, int y) {
        super(joueur, x, y);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }

}
