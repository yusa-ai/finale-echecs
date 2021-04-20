package echecs;

import pieces.*;

import java.util.ArrayList;
import java.util.List;

public class FinaleEchecs {
    private static final int LONGUEUR = 8;

    private final List<IPièce> pièces = new ArrayList<>();

    //Todo : faire une fabrique
    private final Joueur blanc = new Joueur(CouleurJoueur.BLANC);
    private final Joueur noir = new Joueur(CouleurJoueur.NOIR);

    private Joueur courant = blanc;

    public FinaleEchecs() {
        // changer l'initialisation des pièces ?
        pièces.add(new Roi(noir, 7, 4));
        pièces.add(new Roi(blanc, 6, 1));
        pièces.add(new Roi(blanc, 5, 4));
    }

    private IPièce chercherPièce(int x, int y)  {
        for (IPièce pièce : pièces)
            if (pièce.getX() == x && pièce.getY() == y)
                return pièce;
        return null;
    }

    @Override
    public String toString() {
        String lettres = "    a   b   c   d   e   f   g   h    ";
        String separateur = "   --- --- --- --- --- --- --- ---   ";
        String newLine = System.lineSeparator();

        StringBuilder sb = new StringBuilder();
        sb.append(lettres).append(newLine).append(separateur).append(newLine);

        // lignes de 8 à 1
        for (int i = LONGUEUR - 1; i >= 0; --i) {
            sb.append(i + 1);
            for (int j = 0; j < LONGUEUR; ++j) {
                sb.append(" | ");
                // TODO trouver mieux ?
                IPièce pièce = chercherPièce(i, j);
                if (pièce == null)
                    sb.append(" ");
                else
                    sb.append(pièce);
            }
            sb.append(" | ").append(i + 1).append(newLine);
            sb.append(separateur).append(newLine);
        }
        sb.append(lettres);
        return sb.toString();
    }
}
