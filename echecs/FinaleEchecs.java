package echecs;

import javafx.util.Pair;
import java.util.*;

public class FinaleEchecs {
    private static final int LONGUEUR = 8;

    private final List<IPièce> pièces = new ArrayList<>();

    private final IJoueur blanc;
    private final IJoueur noir;

    private IJoueur courant;

    private String coupIA; // pour affichage

    public FinaleEchecs(IFabriquePièce fPièce, IFabriqueJoueur fJoueur, int mode) {
        switch (mode) {
            case 2:
                blanc = fJoueur.getJoueur("BLANC", "HUMAIN");
                noir = fJoueur.getJoueur("NOIR", "IA");
                break;
            case 3:
                blanc = fJoueur.getJoueur("BLANC", "IA");
                noir = fJoueur.getJoueur("NOIR", "IA");
                break;
            default:
                blanc = fJoueur.getJoueur("BLANC", "HUMAIN");
                noir = fJoueur.getJoueur("NOIR", "HUMAIN");
        }
        courant = blanc;

        pièces.add(fPièce.getPièce("ROI", noir, toY('e'), toX('8')));
        pièces.add(fPièce.getPièce("TOUR", blanc, toY('f'), toX('6')));
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

        // Le déplacement de la pièce ne peut pas mettre le roi en échec
        return !coupExposeRoi(ySrc, xSrc, yDest, xDest);
    }

    private boolean alliéSurCase(int yDest, int xDest) {
        IPièce pièce = occupante(yDest, xDest);
        return (pièce != null && pièce.getJoueur() == getCourant());
    }

    public boolean roiEnEchec() {
        IPièce roi = roiCourant();
        assert roi != null;
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

            //ne pièce peut en manger une autre pour libérer le roi
        IPièce pièceSurDest = occupante(yDest, xDest);
        if (pièceSurDest != null)
            pièceSurDest.déplacer(-1, -1); // hors de l'échiquier

        pièce.déplacer(yDest, xDest); // On simule le déplacement de la pièce pour savoir
        // si celle-ci débloque le roi
        boolean coupDébloqueRoi = !roiEnEchec();

        // Restauration de l'état du jeu
        pièce.déplacer(ySrc, xSrc);
        if (pièceSurDest != null)
            pièceSurDest.déplacer(yDest, xDest);
        return coupDébloqueRoi;
    }

    public boolean coupExposeRoi(int ySrc, int xSrc, int yDest, int xDest) {
        return !coupDébloqueRoi(ySrc, xSrc, yDest, xDest);
    }

    public void jouer(int ySrc, int xSrc, int yDest, int xDest) {
        // S'il y a une pièce (adverse) à la destination, la retirer du plateau : elle est mangée
        pièces.removeIf(pièce -> pièce.getY() == yDest && pièce.getX() == xDest);

        IPièce pièceJouée = occupante(ySrc, xSrc);
        pièceJouée.déplacer(yDest, xDest);
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

    public boolean nulle() {
        final int NB_ROIS = 2;
        if (pièces.size() == NB_ROIS) {
            for (IPièce pièce : pièces)
                if (!pièce.craintEchec())
                    return false;
        }
        else {
            return false;
        }

        return true;
    }

    public boolean pat() {
        IPièce roi = roiCourant();
        assert roi != null;
        int rx = roi.getX();
        int ry = roi.getY();

        // On vérifie si le roi est en échec sur les 8 cases autour de lui
        for (int x = rx + 1; x >= rx - 1; --x) {
            for (int y = ry - 1; y <= ry + 1; ++y) {
                if (y < 0 || x < 0 || y > LONGUEUR - 1 || x > LONGUEUR - 1)
                    continue;

                if (y == ry && x == rx)
                    continue;

                roi.déplacer(y, x); // simulation
                if (!échecSurPosition(y, x)) {
                    roi.déplacer(ry, rx); // on replace le roi à sa position initiale
                    return false;
                }
            }
        }
        roi.déplacer(ry, rx);

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

    public void jouerIA() {
        assert !courant.estHumain() : "Le joueur courant est humain.";

        Random r = new Random();
        List<IPièce> piècesIA = new ArrayList<>();
        for (IPièce pièce : pièces) // on récupère les pièces de l'IA
            if (pièce.getJoueur() == courant)
                piècesIA.add(pièce);

        int position;
        IPièce choix;

        List<Pair<Integer, Integer>> coords;

        int yDest, xDest;
        IPièce pièceSurDest;

        while (true) {
            // On prend une pièce au hasard, qu'on va essayer de jouer
            position = r.nextInt(piècesIA.size());
            choix = piècesIA.get(position);
            piècesIA.remove(position);

            // On va tester chacune des coords de l'échiquier dans un ordre aléatoire
            coords = new ArrayList<>();
            for (int i = 0; i < LONGUEUR; ++i)
                for (int j = 0; j < LONGUEUR; ++j) {
                    if (choix.getY() == i && choix.getX() == j) continue; // position actuelle
                    coords.add(new Pair<>(i, j));
                }
            Collections.shuffle(coords);

            while (!coords.isEmpty()) {
                yDest = coords.get(0).getKey();
                xDest = coords.get(0).getValue();
                coords.remove(0);

                // Pièce alliée sur la destination
                pièceSurDest = occupante(yDest, xDest);
                if (pièceSurDest != null && pièceSurDest.getJoueur() == courant) continue;

                if (choix.peutAllerEn(yDest, xDest, this) && choix.trajectoireLibre(yDest, xDest, this)) {

                    // Le roi ne peut pas se mettre en échec
                    if (choix.craintEchec() && échecSurPosition(yDest, xDest)) continue;

                    // Une autre pièce ne peut pas mettre en échec le roi
                    if (!choix.craintEchec() && coupExposeRoi(choix.getY(), choix.getX(), yDest, xDest)) continue;

                    if (!roiEnEchec() // si le roi n'est pas en échec
                            // ou si le roi est en échec mais que le coup le débloque
                            || roiEnEchec() && coupDébloqueRoi(choix.getY(), choix.getX(), yDest, xDest)) {

                        // pour affichage dans le main
                        coupIA = toLettre(choix.getY()) + Integer.toString(choix.getX() + 1)
                                + toLettre(yDest) + (xDest + 1);

                        jouer(choix.getY(), choix.getX(), yDest, xDest);
                        return;
                    }
                }
            }
        }
    }

    public boolean contreIA() {
        return !noir.estHumain();
    }

    public static int toY(char lettre) {
        final List<Character> LETTRES =
                new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));

        return LETTRES.indexOf(lettre);
    }

    public static int toX(char chiffre) {
        return Character.getNumericValue(chiffre) - 1;
    }

    public static char toLettre(int y) {
        final List<Character> LETTRES =
                new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
        return LETTRES.get(y);
    }

    public IJoueur getCourant() {
        return courant;
    }

    public IJoueur getPrécédent() {
        if (courant == blanc)
            return noir;
        return blanc;
    }

    public String getCoupIA() {
        return coupIA;
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
}
