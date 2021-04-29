package appli;

import echecs.FinaleEchecs;
import joueurs.FabriqueJoueur;
import pieces.FabriquePièce;

import java.util.*;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();

        Scanner sc = new Scanner(System.in);

        // Sélection du mode de jeu
        System.out.println("Veuillez saisir le n° du mode de jeu :");
        System.out.println("1 : Humain vs. Humain");
        System.out.println("2 : Humain vs. IA");
        System.out.println("3 : IA vs. IA");
        int mode = 0;
        while (mode < 1 || mode > 3) {
            System.out.print("Sélection : ");
            while (!sc.hasNextInt()) {
                System.out.print("Sélection : ");
                sc.nextLine();
            }
            mode = sc.nextInt();
        }
        sc.nextLine(); // flush

        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur, mode);

        while (true) {
            System.out.println("#####################################");
            System.out.println(fe);
            System.out.println("Le joueur " + fe.getCourant().getCouleur() + " a le trait");

            if (finPartie(fe)) break;

            boolean roiEnEchec = fe.roiEnEchec();
            if (roiEnEchec)
                System.out.println("Attention, votre roi est en échec !");
            System.out.println("#####################################");

            // Si le joueur courant est HUMAIN
            if (fe.getCourant().estHumain())
                jouerHumain(fe, roiEnEchec, sc);
            else
                fe.jouerIA();

            fe.prochainTour();
            Thread.sleep(250);
        }
        sc.close();
    }

    private static void jouerHumain(FinaleEchecs fe, boolean roiEnEchec, Scanner sc) {
        String saisie;
        boolean formatValide;
        int ySrc = 0, xSrc = 0, yDest = 0, xDest = 0;
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
    }

    private static boolean finPartie(FinaleEchecs fe) {
        if (fe.nulle()) {
            System.out.println("Matériel insuffisant ! Match nul.");
            return true;
        }

        if (fe.mat()) {
            System.out.println("Echec et mat !");
            System.out.println("Le joueur " + fe.getPrécédent().getCouleur() + " remporte la partie !");
            return true;
        }

        if (fe.pat()) {
            System.out.println("Pat détecté ! Match nul.");
            return true;
        }

        return false;
    }
}
