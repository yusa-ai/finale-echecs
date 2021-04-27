package appli;

import echecs.FinaleEchecs;
import echecs.IPièce;
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
        while (true) {
            System.out.println(fe);
            System.out.println(System.lineSeparator());
            System.out.println("Tour du joueur " + fe.getCourant().getCouleur());

            saisie = sc.nextLine();
            boolean coupValide = vérifier(fe, saisie);
            while (!coupValide) {
                System.out.println("Saisie incorrecte !");
                saisie = sc.nextLine();
                coupValide = vérifier(fe, saisie);
            }
            jouer(fe, saisie);
            // détecter fin de partie?
            // changer le joueur courant

            break; // TODO remove
        }
    }

    private static boolean vérifier(FinaleEchecs fe, String saisie) {
        return (formatValide(saisie) && coupLégal(fe, saisie));
    }

    private static boolean formatValide(String saisie) {
        final int TAILLE = 4;
        // REGEX: Sans tenir compte de la casse, une lettre de A à H puis un chiffre de 1 à 8, deux fois
        final String FORMAT = "(?i)([a-h][1-8]){2}";

        return (saisie.length() == TAILLE && saisie.matches(FORMAT));
    }

    private static boolean coupLégal(FinaleEchecs fe, String saisie) {
        final List<Character> LETTRES =
                new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));

        String s = saisie.toLowerCase();
        int ySrc = LETTRES.indexOf(s.charAt(0)); // on traduit la lettre par sa coordonnée y
        int xSrc = Character.getNumericValue(s.charAt(1)) - 1;
        int yDest = LETTRES.indexOf(s.charAt(2));
        int xDest = Character.getNumericValue(s.charAt(3)) - 1;

        // La source et la destination sont deux positions distinctes
        if (ySrc == yDest && xSrc == xDest)
            return false;

        // Il y a une pièce sur la case de départ
        IPièce pièce = fe.occupante(ySrc, xSrc);
        if (pièce == null)
            return false;

        // La pièce appartient au joueur
        if (pièce.getJoueur() != fe.getCourant())
            return false;

        // La pièce peut se déplacer à la destination


        // Si la pièce est un Roi... (fonction à part peut-être ?)
            // Si le Roi est mis en échec en se déplaçant à la dest, return false
            // (pour ça, itérer à travers toutes les pièces adverses et regarder si l'une d'elles peut attaquer la position d'arrivée)

            // Si


        return true;
    }

    private static void jouer(FinaleEchecs fe, String saisie) {

    }
}
