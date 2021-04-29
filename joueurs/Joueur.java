package joueurs;

import echecs.IJoueur;

public class Joueur implements IJoueur {

    private final CouleurJoueur couleur;
    private final TypeJoueur type;

    public Joueur(CouleurJoueur couleur, TypeJoueur type) {
        this.couleur = couleur;
        this.type = type;
    }

    @Override
    public boolean estHumain() {
        return type == TypeJoueur.HUMAIN;
    }

    @Override
    public CouleurJoueur getCouleur() {
        return couleur;
    }

}
