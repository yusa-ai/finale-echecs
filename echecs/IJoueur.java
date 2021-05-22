package echecs;

import joueurs.CouleurJoueur;

public interface IJoueur {
    CouleurJoueur getCouleur();
    /**
     * Détermine si le joueur est humain ou non
     * @return vrai/faux
     */
    boolean estHumain();

    /**
     * Fait jouer un coup à l'IA
     * Cette méthode est inutile pour un Humain
     * @param finaleEchecs La partie de finale d'échecs
     */
    void jouer(FinaleEchecs finaleEchecs);
}
