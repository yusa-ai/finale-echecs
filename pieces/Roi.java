package pieces;

import echecs.FinaleEchecs;
import echecs.IJoueur;

public class Roi extends Pièce {
    private static final String SYMBOLE = "R";

    /**
     * Initialise un nouveau Roi
     * @param joueur Le joueur auquel appartient le roi
     * @param y La position y du roi
     * @param x La position x du roi
     */
    public Roi(IJoueur joueur, int y, int x) {
        super(joueur, y, x);
    }

    @Override
    public boolean peutAllerEn(int yDest, int xDest) {
        // Déplacement 1 case autour du Roi dans n'importe quelle direction
        return Math.abs(yDest - getY()) <= 1 && Math.abs(xDest - getX()) <= 1;
    }

    @Override
    public boolean trajectoireLibre(int yDest, int xDest, FinaleEchecs fe) {
        return true;
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }

    @Override
    public boolean craintEchec() {
        return true;
    }
}
