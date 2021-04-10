package echecs;

import pieces.*;

public class FinaleEchecs {
    private static final int LONGUEUR = 8;
    private final IPiece[][] damier = new Piece[LONGUEUR][LONGUEUR];

    private final Joueur blanc = new Joueur(CouleurJoueur.BLANC);
    private final Joueur noir = new Joueur(CouleurJoueur.NOIR);

    private Joueur courant = blanc;

    public FinaleEchecs() {
        // changer l'initialisation des pièces ?
        damier[7][4] = new Roi(noir);
        damier[6][1] = new Tour(blanc);
        damier[5][4] = new Roi(blanc);
    }

    @Override
    public String toString() {
        String lettres = "    a   b   c   d   e   f   g   h    ";
        String separateur = "   --- --- --- --- --- --- --- ---   ";
        String newLine = System.lineSeparator();

        StringBuilder sb = new StringBuilder();
        sb.append(lettres).append(newLine).append(separateur).append(newLine);

        for (int i = LONGUEUR - 1; i >= 0; --i) {
            sb.append(i + 1);
            for (int j = 0; j < LONGUEUR; ++j) {
                sb.append(" | ");
                if (damier[i][j] == null)
                    sb.append(" ");
                else
                    sb.append(damier[i][j]);
            }
            sb.append(" | ").append(i + 1).append(newLine);
            sb.append(separateur).append(newLine);
        }
        sb.append(lettres);
        return sb.toString();
    }
}
