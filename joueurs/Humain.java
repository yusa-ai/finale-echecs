package joueurs;

import echecs.FinaleEchecs;

public class Humain extends Joueur {

	/**
	 * Initialise un nouveau joueur humain
	 * @param couleur La couleur (BLANC/NOIR) de l'humain
	 */
    public Humain(CouleurJoueur couleur) {
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
