package joueurs;

import echecs.IJoueur;

public class Joueur implements IJoueur {

    private final CouleurJoueur couleur;

    public Joueur(CouleurJoueur couleur) {
        this.couleur = couleur;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }
}
