package appli;

import echecs.FinaleEchecs;
import echecs.IPièce;
import joueurs.FabriqueJoueur;
import pieces.FabriquePièce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        // Sans tenir compte de la casse, une lettre de A à H puis un chiffre de 1 à 8, deux fois
        final String FORMAT = "(?i)([a-h][1-8]){2}";

        return (saisie.length() == TAILLE && saisie.matches(FORMAT));
    }

    private static boolean coupLégal(FinaleEchecs fe, String saisie) {
        final List<Character> LETTRES = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));

        char[] coup = saisie.toCharArray();
        int ySrc = LETTRES.indexOf(coup[0]);
        int xSrc = Character.getNumericValue(coup[1]) - 1;
        int yDest = LETTRES.indexOf(coup[2]); // TODO trouver mieux?
        int xDest = Character.getNumericValue(coup[3]) - 1;

        // Regarder si la source et la destination sont les mêmes positions
        if (ySrc == yDest && xSrc == xDest)
            return false;

        // Regarder s'il y a une pièce à la case de départ
        IPièce pièce = fe.occupante(ySrc, xSrc);
        if (pièce == null)
            return false;

        // Regarder si la pièce appartient au joueur
        if (pièce.getJoueur() != fe.getCourant())
            return false;

        // Regarder si la pièce peut se déplacer à la destination

        // Si la pièce est un Roi... (fonction à part peut-être ?)
            // Si le Roi est mis en échec en se déplaçant à la dest, return false
            // Si



        return false;
    }

    private static void jouer(FinaleEchecs fe, String saisie) {

    }
}
