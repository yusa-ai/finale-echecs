package appli;

import echecs.FinaleEchecs;
import joueurs.FabriqueJoueur;
import pieces.FabriquePièce;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();
        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur);

        Scanner sc = new Scanner(System.in);
        String saisie;
        boolean formatValide;
        int ySrc = 0, xSrc = 0, yDest = 0, xDest = 0;

        while (true) {
            System.out.println(fe);
            System.out.println(System.lineSeparator());
            System.out.println("Le joueur " + fe.getCourant().getCouleur() + " a le trait");

            if (finPartie(fe)) break;

            boolean roiEnEchec = fe.roiEnEchec();
            if (roiEnEchec)
                System.out.println("Attention, votre roi est en échec !");

            do {
                saisie = sc.nextLine();
                formatValide = FinaleEchecs.formatValide(saisie);
                if (formatValide) {
                    String s = saisie.toLowerCase();
                    ySrc = FinaleEchecs.toY(s.charAt(0)); // on traduit la lettre par sa coordonnée y
                    xSrc = FinaleEchecs.toX(s.charAt(1));
                    yDest = FinaleEchecs.toY(s.charAt(2));
                    xDest = FinaleEchecs.toX(s.charAt(3));

                    if (!fe.coupLégal(ySrc, xSrc, yDest, xDest)) {
                        System.out.println("Le coup est illégal !");
                        formatValide = false;
                    }

                    // Si le coup est légal et que le roi est en échec
                    else if (roiEnEchec) {
                        if (fe.coupDébloqueRoi(ySrc, xSrc, yDest, xDest))
                            roiEnEchec = false;
                        else
                            System.out.println("Vous devez absolument débloquer votre roi !");
                    }
                }
                else
                    System.out.println("Le format de la saisie est incorrect !");
            }
            while (!formatValide || roiEnEchec);

            fe.jouer(ySrc, xSrc, yDest, xDest);
            fe.prochainTour();
        }
    }

    private static boolean finPartie(FinaleEchecs fe) {
        if (fe.pat()) {
            System.out.println("");
            System.out.println("");
            return true;
        }

        if (fe.mat()) {
            System.out.println("");
            System.out.println("");
            return true;
        }

        return false;
    }
}
