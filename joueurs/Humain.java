package joueurs;

import echecs.FinaleEchecs;

class Humain extends Joueur {

    Humain(CouleurJoueur couleur) {
        super(couleur);
    }

    @Override
    public void jouer(FinaleEchecs finaleEchecs) {
    }

    @Override
    public boolean estHumain() {
        return true;
    }

}
