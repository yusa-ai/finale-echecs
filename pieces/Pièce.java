package pieces;

import echecs.IJoueur;
import joueurs.CouleurJoueur;
import echecs.IPièce;

public abstract class Pièce implements IPièce {

    private final IJoueur joueur;
    private int x;
    private int y;

    public Pièce(IJoueur joueur, int y, int x) {
        this.joueur = joueur;
        this.x = x;
        this.y = y;
    }

    public abstract String getSymbole();

    @Override
    public IJoueur getJoueur() {
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
    public boolean craintMat() {
        return false;
    }

    @Override
    public String toString() {
        if (getJoueur().getCouleur() == CouleurJoueur.BLANC)
            return getSymbole().toUpperCase();
        return getSymbole().toLowerCase();
    }
}
