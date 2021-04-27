package echecs;

public interface IPièce {
    IJoueur getJoueur();
    int getX();
    int getY();
    boolean craintMat();
    String toString();
}
