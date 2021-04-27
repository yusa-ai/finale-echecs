package joueurs;

import echecs.IFabriqueJoueur;
import echecs.IJoueur;

public class FabriqueJoueur implements IFabriqueJoueur {
    public IJoueur getJoueur(String couleur) {
        if (couleur == null)
            return null;
        if (couleur.equalsIgnoreCase("BLANC"))
            return new Joueur(CouleurJoueur.BLANC);
        if (couleur.equalsIgnoreCase("NOIR"))
            return new Joueur(CouleurJoueur.NOIR);
        return null;
    }
}
