package pieces;

import echecs.CouleurJoueur;
import echecs.IPiece;
import echecs.Joueur;

public abstract class Piece implements IPiece {

    private final Joueur joueur;

    public Piece(Joueur joueur) {
        this.joueur = joueur;
    }

    public abstract String getSymbole();

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public String toString() {
        if (getJoueur().getCouleur() == CouleurJoueur.BLANC)
            return getSymbole();
        return getSymbole().toLowerCase();
    }
}
