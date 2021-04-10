package pieces;

import echecs.IPiece;

public class Piece implements IPiece {
    private static final String SYMBOLE = " ";

    @Override
    public String toString() {
        return SYMBOLE;
    }
}
