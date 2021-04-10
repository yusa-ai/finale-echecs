package pieces;

import echecs.CouleurJoueur;
import echecs.IPiece;
import echecs.Joueur;

public class Piece implements IPiece {
    private static final String SYMBOLE = " ";

    private final Joueur joueur;

    public Piece(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }

    @Override
    public String toString() {
        if (getJoueur().getCouleur() == CouleurJoueur.BLANC)
            return getSymbole();
        return getSymbole().toLowerCase();
    }
}
