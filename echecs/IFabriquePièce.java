package echecs;

public interface IFabriquePièce {
    IPièce getPièce(String nom, IJoueur joueur, int y, int x);
}
