package echecs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinaleEchecs {
    private static final int LONGUEUR = 8;

    private final List<IPièce> pièces = new ArrayList<>();

    private final IJoueur blanc;
    private final IJoueur noir;

    private IJoueur courant;

    public FinaleEchecs(IFabriquePièce fPièce, IFabriqueJoueur fJoueur) {
        blanc = fJoueur.getJoueur("BLANC");
        noir = fJoueur.getJoueur("NOIR");
        courant = blanc;

        pièces.add(fPièce.getPièce("ROI", noir, toY('e'), toX('8')));
        pièces.add(fPièce.getPièce("TOUR", blanc, toY('b'), toX('7')));
        pièces.add(fPièce.getPièce("ROI", blanc, toY('e'), toX('6')));
        pièces.add(fPièce.getPièce("TOUR", noir, toY('h'), toX('6')));

        /*pièces.add(fPièce.getPièce("roi", noir, toY('a'), toX('8')));
        pièces.add(fPièce.getPièce("roi", blanc, toY('a'), toX('6')));
        pièces.add(fPièce.getPièce("tour", blanc, toY('b'), toX('6')));*/ // b6 pour pat, c8 pour mat
    }

    public static boolean formatValide(String saisie) {
        final int TAILLE = 4;
        // REGEX: Sans tenir compte de la casse, une lettre de A à H puis un chiffre de 1 à 8, deux fois
        final String FORMAT = "(?i)([a-h][1-8]){2}";

        return (saisie.length() == TAILLE && saisie.matches(FORMAT));
    }

    public boolean coupLégal(int ySrc, int xSrc, int yDest, int xDest) {
        // La destination se trouve dans l'échiquier
        if (yDest < 0 || xDest < 0 || yDest > LONGUEUR - 1 || xDest > LONGUEUR - 1)
            return false;

        // La source et la destination sont deux positions distinctes
        if (ySrc == yDest && xSrc == xDest)
            return false;

        // Il y a une pièce sur la case de départ
        IPièce pièce = occupante(ySrc, xSrc);
        if (pièce == null)
            return false;

        // La pièce appartient au joueur
        if (pièce.getJoueur() != getCourant())
            return false;

        // La destination n'est pas occupée par une pièce alliée
        if (alliéSurCase(yDest, xDest))
            return false;

        // La pièce peut se déplacer à la destination
        if (!pièce.peutAllerEn(yDest, xDest, this))
            return false;
        if (!pièce.trajectoireLibre(yDest, xDest, this))
            return false;

        // Si la pièce est un Roi
        if (pièce.craintEchec())
            // le roi ne doit pas être en échec à la position d'arrivée
            return !échecSurPosition(yDest, xDest);

        return true;
    }

    private boolean alliéSurCase(int yDest, int xDest) {
        IPièce pièce = occupante(yDest, xDest);
        return (pièce != null && pièce.getJoueur() == getCourant());
    }

    public boolean roiEnEchec() {
        IPièce roi = roiCourant();
        return échecSurPosition(roi.getY(), roi.getX());
    }

    private IPièce roiCourant() {
        for (IPièce pièce : pièces)
            if (pièce.getJoueur() == courant && pièce.craintEchec())
                return pièce;
        return null;
    }

    private boolean échecSurPosition(int y, int x) {
        for (IPièce p : pièces)
            if (p.getJoueur() != getCourant()) {
                if (p.peutAllerEn(y, x, this) &&
                        p.trajectoireLibre(y, x, this) &&
                        !(p.getY() == y && p.getX() == x))
                    return true;
            }
        return false;
    }

    public boolean coupDébloqueRoi(int ySrc, int xSrc, int yDest, int xDest) {
        IPièce pièce = occupante(ySrc, xSrc);

        pièce.déplacer(yDest, xDest); // On simule le déplacement de la pièce pour savoir
        // si celle-ci débloque le roi
        boolean coupDébloqueRoi = !roiEnEchec();
        pièce.déplacer(ySrc, xSrc);

        return coupDébloqueRoi;
    }

    public void jouer(int ySrc, int xSrc, int yDest, int xDest) {
        IPièce pièce;
        for (int i = 0; i < pièces.size(); ++i) {
            pièce = pièces.get(i);

            // S'il y a une pièce (adverse) à la destination, la retirer du plateau (elle est mangée)
            if (pièce.getY() == yDest && pièce.getX() == xDest)
                pièces.remove(i);

            // Déplacer la pièce à la case de départ sur la case d'arrivée
            if (pièce.getY() == ySrc && pièce.getX() == xSrc)
                pièce.déplacer(yDest, xDest);
        }

    }

    public IPièce occupante(int y, int x)  {
        for (IPièce pièce : pièces)
            if (pièce.getY() == y && pièce.getX() == x)
                return pièce;
        return null;
    }

    public void prochainTour() {
        if (courant == blanc)
            courant = noir;
        else
            courant = blanc;
    }

    public IJoueur getCourant() {
        return courant;
    }

    public IJoueur getPrécédent() {
        if (courant == blanc)
            return noir;
        return blanc;
    }

    public static int toY(char lettre) {
        final List<Character> LETTRES =
                new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));

        return LETTRES.indexOf(lettre);
    }

    public static int toX(char chiffre) {
        return Character.getNumericValue(chiffre) - 1;
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
                IPièce pièce = occupante(j, i);
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

    public boolean pat() {
        IPièce roi = roiCourant();
        int rx = roi.getX();
        int ry = roi.getY();

        // On vérifie si le roi est en échec sur les 8 cases autour de lui
        for (int x = rx + 1; x >= rx - 1; --x) {
            for (int y = ry - 1; y <= ry + 1; ++y) {
                if (y < 0 || x < 0 || y > LONGUEUR - 1 || x > LONGUEUR - 1)
                    continue;

                if (y == ry && x == rx)
                    continue;

                if (!échecSurPosition(y, x))
                    return false;
            }
        }

        // Le roi est bloqué : on regarde si les autres pièces peuvent le protéger
        for (IPièce pièce : pièces)
            if (pièce.getJoueur() == courant && !pièce.craintEchec()) {
                // On essaie de déplacer chaque autre pièce du joueur sur toutes les cases de l'échiquier
                for (int y = 0; y < LONGUEUR; ++y)
                    for (int x = 0; x < LONGUEUR; ++x) {
                        if (pièce.peutAllerEn(y, x, this) && pièce.trajectoireLibre(y, x, this))
                            if (coupDébloqueRoi(pièce.getY(), pièce.getX(), y, x))
                                return false;
                    }
            }

        return true;
    }

    public boolean mat() {
        return pat() && roiEnEchec();
    }
}
