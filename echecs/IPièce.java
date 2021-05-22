package echecs;

public interface IPièce {
    IJoueur getJoueur();
    int getX();
    int getY();
    /**
     * Détermine si la pièce craint l'échec (Roi)
     * @return vrai/faux
     */
    boolean craintEchec();
    String toString();

    /**
     * Détermine si les règles de la pièce lui permettent de se rendre
     * à une coordonnée
     * @param yDest La position y de destination
     * @param xDest La position x de destination
     * @return vrai/faux
     */
    boolean peutAllerEn(int yDest, int xDest);
    
    /**
     * Détermine si aucune pièce ne se trouve sur le chemin de la pièce
     * pour se rendre à une coordonnée
     * @param yDest La position y d'arrivée
     * @param xDest La position x d'arrivée
     * @param fe La finale d'échecs
     * @return vrai/faux
     */
    boolean trajectoireLibre(int yDest, int xDest, FinaleEchecs fe);

    /**
     * Déplace la pièce à une coordonnée
     * @param yDest La position y d'arrivée
     * @param xDest La position x d'arrivée
     */
    void déplacer(int yDest, int xDest);
}
