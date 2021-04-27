package pieces;

import echecs.IFabriquePièce;
import echecs.IJoueur;
import echecs.IPièce;

public class FabriquePièce implements IFabriquePièce {
    public IPièce getPièce(String nom, IJoueur joueur, int x, int y) {
        if (nom == null)
            return null;
        if (nom.equalsIgnoreCase("ROI"))
            return new Roi(joueur, x, y);
        if (nom.equalsIgnoreCase("TOUR"))
            return new Tour(joueur, x, y);
        return null;
    }
}
