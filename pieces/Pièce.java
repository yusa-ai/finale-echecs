package pieces;

import echecs.CouleurJoueur;
import echecs.IPièce;
import echecs.Joueur;

public abstract class Pièce implements IPièce {

    private final Joueur joueur;
    private int x;
    private int y;

    public Pièce(Joueur joueur, int x, int y) {
        this.joueur = joueur;
        this.x = x;
        this.y = y;
    }

    public abstract String getSymbole();

    @Override
    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        if (getJoueur().getCouleur() == CouleurJoueur.BLANC)
            return getSymbole().toUpperCase();
        return getSymbole().toLowerCase();
    }
}
