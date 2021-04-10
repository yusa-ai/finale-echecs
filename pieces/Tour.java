package pieces;

import echecs.Joueur;

public class Tour extends Piece {
    private static final String SYMBOLE = "T";

    public Tour(Joueur joueur) {
        super(joueur);
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }
}
