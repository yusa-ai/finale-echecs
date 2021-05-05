package joueurs;

import echecs.FinaleEchecs;
import echecs.IJoueur;

class Joueur implements IJoueur {

    private final CouleurJoueur couleur;

    Joueur(CouleurJoueur couleur) {
        this.couleur = couleur;
    }

    @Override
    public void jouer(FinaleEchecs finaleEchecs) {
    }

    @Override
    public boolean estHumain() {
        return true;
    }

    @Override
    public CouleurJoueur getCouleur() {
        return couleur;
    }

}
