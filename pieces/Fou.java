package pieces;

import echecs.Joueur;

public class Fou extends Piece {
    public Fou(Joueur joueur) {
        super(joueur);
    }

    @Override
    public String getSymbole() {
        return "F";
    }
}
