package joueurs;

import echecs.FinaleEchecs;

class Humain extends Joueur {

	/**
	 * Initialise un nouveau joueur humain
	 * @param couleur La couleur (BLANC/NOIR) de l'humain
	 */
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
