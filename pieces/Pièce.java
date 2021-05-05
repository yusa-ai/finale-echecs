package pieces;

import echecs.FinaleEchecs;
import echecs.IJoueur;
import joueurs.CouleurJoueur;
import echecs.IPièce;

abstract class Pièce implements IPièce {

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
    public void déplacer(int yDest, int xDest) {
        y = yDest;
        x = xDest;
    }

    @Override
    public boolean craintEchec() {
        return false;
    }

    @Override
    public abstract boolean peutAllerEn(int yDest, int xDest, FinaleEchecs fe);

    @Override
    public abstract boolean trajectoireLibre(int yDest, int xDest, FinaleEchecs fe);

    @Override
    public String toString() {
        if (getJoueur().getCouleur() == CouleurJoueur.BLANC)
            return getSymbole().toUpperCase();
        return getSymbole().toLowerCase();
    }
}
