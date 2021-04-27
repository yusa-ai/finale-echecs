package appli;

import echecs.FinaleEchecs;
import joueurs.FabriqueJoueur;
import pieces.FabriquePièce;

public class Main {
    public static void main(String[] args) {
        FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();

        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur);
        System.out.println(fe);
    }
}
