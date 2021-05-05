package echecs;

import joueurs.CouleurJoueur;

public interface IJoueur {
    CouleurJoueur getCouleur();
    boolean estHumain();

    void jouer(FinaleEchecs finaleEchecs);
}
