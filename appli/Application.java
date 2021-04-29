package appli;

import echecs.FinaleEchecs;
import joueurs.FabriqueJoueur;
import pieces.FabriquePièce;

import java.util.*;

public class Application {
    public static void main(String[] args) {
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
        if (mode == 1 || mode == 2) {
            System.out.println("Dans les modes 1 et 2, vous pouvez abandonner ou faire une proposition de nulle");
            System.out.println("au début de votre tour en tapant \"abandonner\" ou \"nulle\" respectivement.");
        }

        while (true) {
            System.out.println("_____________________________________");
            System.out.println(fe);
            System.out.println("Le joueur " + fe.getCourant().getCouleur() + " a le trait");

            if (finPartie(fe)) break;

            boolean roiEnEchec = fe.roiEnEchec();
            if (roiEnEchec)
                System.out.println("Attention, votre roi est en échec !");
            System.out.println("_____________________________________");

            if (fe.getCourant().estHumain()) {
                String saisie;
                saisie = sc.nextLine();

                if (abandon(fe, sc, saisie)) break;
                if (saisie.equalsIgnoreCase("ABANDONNER") || saisie.equalsIgnoreCase("NULLE"))
                    saisie = sc.nextLine(); // demander un coup

                jouerHumain(fe, sc, saisie);
            }
            else {
                fe.jouerIA();
                System.out.println(fe.getCoupIA());
            }

            fe.prochainTour();
        }
        sc.close();
    }

    private static boolean abandon(FinaleEchecs fe, Scanner sc, String saisie) {
        if (saisie.equalsIgnoreCase("ABANDONNER")) {
            System.out.println("Le joueur " + fe.getCourant().getCouleur() + " abandonne la partie !");
            return true;
        }
        if (saisie.equalsIgnoreCase("NULLE")) {
            System.out.println("Le joueur " + fe.getCourant().getCouleur() + " propose un match nul.");

            if (fe.contreIA()) {
                System.out.println("L'IA accepte !!! ^-^ Match nul.");
                return true;
            }

            System.out.println("Acceptez-vous ? (O/N)");
            String réponse = "";
            while (!réponse.equalsIgnoreCase("O") && !réponse.equalsIgnoreCase("N")) {
                System.out.print("Réponse : ");
                while (!sc.hasNextLine()) {
                    System.out.print("Réponse : ");
                    sc.nextLine();
                }
                réponse = sc.nextLine();
            }
            if (réponse.equalsIgnoreCase("O")) {
                System.out.println("Le joueur " + fe.getPrécédent().getCouleur() + " accepte le match nul !");
                return true;
            }
            else {
                System.out.println("La partie continue.");
                return false;
            }
        }
        return false;
    }

    private static void jouerHumain(FinaleEchecs fe, Scanner sc, String saisie) {
        int ySrc = 0, xSrc = 0, yDest = 0, xDest = 0;
        do {
            if (FinaleEchecs.formatValide(saisie)) {
                String s = saisie.toLowerCase();
                ySrc = FinaleEchecs.toY(s.charAt(0)); // on traduit la lettre par sa coordonnée y
                xSrc = FinaleEchecs.toX(s.charAt(1));
                yDest = FinaleEchecs.toY(s.charAt(2));
                xDest = FinaleEchecs.toX(s.charAt(3));

                if (!fe.coupLégal(ySrc, xSrc, yDest, xDest)) {
                    System.out.println("Le coup est illégal !");
                    saisie = sc.nextLine();
                }

                // Si le coup est légal et que le roi est en échec
                else if (fe.roiEnEchec()) {
                    if (fe.coupDébloqueRoi(ySrc, xSrc, yDest, xDest))
                        break;
                    else {
                        System.out.println("Vous devez absolument débloquer votre roi !");
                        saisie = sc.nextLine();
                    }
                }
                else break;
            }
            else {
                System.out.println("Le format de la saisie est incorrect !");
                saisie = sc.nextLine();
            }
        }
        while (true);

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
