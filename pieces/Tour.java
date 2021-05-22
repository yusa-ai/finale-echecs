package pieces;

import echecs.FinaleEchecs;
import echecs.IJoueur;
import echecs.IPièce;

class Tour extends Pièce {
    private static final String SYMBOLE = "T";

    /**
     * Initialise une nouvelle Tour
     * @param joueur Le joueur auquel appartient la tour
     * @param y La position y de la tour
     * @param x La position x de la tour
     */
    public Tour(IJoueur joueur, int y, int x) {
        super(joueur, y, x);
    }

    @Override
    public boolean peutAllerEn(int yDest, int xDest) {
        // Déplacement en ligne OU en colonne
        return !(yDest != getY() && xDest != getX());
    }

    @Override
    public boolean trajectoireLibre(int yDest, int xDest, FinaleEchecs fe) {
        int yOffset = getY() - yDest;
        int xOffset = getX() - xDest;

        int offset = Math.abs(yOffset - xOffset);

        int dy = getY() - yDest > 0 ? -1 : 1;
        int dx = getX() - xDest > 0 ? -1 : 1;

        IPièce pièce;
        for (int i = 1; i < offset; ++i) {
            if (yOffset == 0) // décalage en x
                pièce = fe.occupante(getY(), getX() + i * dx);
            else // décalage en y
                pièce = fe.occupante(getY() + i * dy, getX());

            if (pièce != null)
                return false;
        }
        return true;
    }

    @Override
    public String getSymbole() {
        return SYMBOLE;
    }
}
