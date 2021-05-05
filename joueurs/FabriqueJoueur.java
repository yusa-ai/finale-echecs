package joueurs;

import echecs.IFabriqueJoueur;
import echecs.IJoueur;

public class FabriqueJoueur implements IFabriqueJoueur {
    public IJoueur getJoueur(String couleur, String type) {
        if (couleur == null || type == null)
            return null;

        CouleurJoueur c = null;
        if (couleur.equalsIgnoreCase("BLANC"))
            c = CouleurJoueur.BLANC;
        else if (couleur.equalsIgnoreCase("NOIR"))
            c = CouleurJoueur.NOIR;

        if (type.equalsIgnoreCase("HUMAIN"))
            return new Joueur(c);
        else if (type.equalsIgnoreCase("IA"))
            return new IA(c);
        return null;
    }
}
