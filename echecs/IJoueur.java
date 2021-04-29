package echecs;

import joueurs.CouleurJoueur;
import joueurs.TypeJoueur;

public interface IJoueur {
    public CouleurJoueur getCouleur();
    boolean estHumain();
}
