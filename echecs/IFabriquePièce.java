package echecs;

import joueurs.Joueur;

public interface IFabriquePièce {
    IPièce getPièce(String nom, IJoueur joueur, int x, int y);
}
