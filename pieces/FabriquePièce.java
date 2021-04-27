package pieces;

import echecs.IFabriquePièce;
import echecs.IJoueur;
import echecs.IPièce;

public class FabriquePièce implements IFabriquePièce {
    public IPièce getPièce(String nom, IJoueur joueur, int y, int x) {
        if (nom == null)
            return null;
        if (nom.equalsIgnoreCase("ROI"))
            return new Roi(joueur, y, x);
        if (nom.equalsIgnoreCase("TOUR"))
            return new Tour(joueur, y, x);
        return null;
    }
}
