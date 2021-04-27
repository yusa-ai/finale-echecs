package echecs;

public interface IPièce {
    IJoueur getJoueur();
    int getX();
    int getY();
    String toString();
}
