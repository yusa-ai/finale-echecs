package echecs;

public interface IPièce {
    IJoueur getJoueur();
    int getX();
    int getY();
    boolean craintEchec();
    String toString();

    boolean peutAllerEn(int yDest, int xDest, FinaleEchecs fe);
    boolean trajectoireLibre(int yDest, int xDest, FinaleEchecs fe);

    void déplacer(int yDest, int xDest);
}
