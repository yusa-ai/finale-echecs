package echecs;

import pieces.Piece;

import java.awt.*;

public class FinaleEchecs {
    private static final int LONGUEUR = 8;
    public final IPiece[][] damier = new Piece[LONGUEUR][LONGUEUR];

    public FinaleEchecs() {
        for (int i = 0; i < LONGUEUR; ++i)
            for (int j = 0; j < LONGUEUR; ++j)
                damier[i][j] = new Piece();
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
            for (int j = 0; j < LONGUEUR; ++j)
                sb.append(" | ").append(damier[i][j]);
            sb.append(" | ").append(i + 1).append(newLine);
            sb.append(separateur).append(newLine);
        }
        sb.append(lettres);
        return sb.toString();
    }
}
