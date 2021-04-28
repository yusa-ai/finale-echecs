package pieces;

import echecs.FinaleEchecs;
import echecs.IJoueur;
import echecs.IPièce;

public class Roi extends Pièce {
    private static final String SYMBOLE = "R";

    public Roi(IJoueur joueur, int y, int x) {
        super(joueur, y, x);
    }

    @Override
    public boolean peutAllerEn(int yDest, int xDest, FinaleEchecs fe) {
        // Déplacement 1 case autour du Roi dans n'importe quelle direction
        if (Math.abs(yDest - getY()) > 1 || Math.abs(xDest - getX()) > 1)
            return false;
        return true;
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
