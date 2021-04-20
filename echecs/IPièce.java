package echecs;

public interface IPièce {
    Joueur getJoueur();
    int getX();
    int getY();
    String toString();
}
