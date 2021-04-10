package pieces;

import echecs.Joueur;

public class Roi extends Piece {
    private static final String SYMBOLE = "R";

    public Roi(Joueur joueur) {
        super(joueur);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }

}
