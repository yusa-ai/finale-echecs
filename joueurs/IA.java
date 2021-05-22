package joueurs;

import echecs.FinaleEchecs;
import echecs.IPièce;
import utils.Paire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IA extends Joueur {

	/**
	 * Initialise une nouvelle IA
	 * @param couleur La couleur (BLANC/NOIR) de l'IA
	 */
    public IA(CouleurJoueur couleur) {
        super(couleur);
    }

    /**
     * Fait jouer un coup à l'IA
     * Cette IA joue un coup valide tiré entièrement au hasard
     */
    public void jouer(FinaleEchecs fe) {
        Random r = new Random();
        List<IPièce> piècesIA = fe.piècesJoueur();

        int position;
        IPièce choix;

        List<Paire<Integer>> coords;

        int yDest, xDest;
        IPièce pièceSurDest;

        while (true) {
            // On prend une pièce au hasard, qu'on va essayer de jouer
            position = r.nextInt(piècesIA.size());
            choix = piècesIA.get(position);
            piècesIA.remove(position);

            // On s'apprête à tester chacune des coords de l'échiquier
            // sur laquelle la pièce pourrait se rendre dans un ordre aléatoire
            coords = new ArrayList<>();
            for (int i = 0; i < FinaleEchecs.LONGUEUR; ++i)
                for (int j = 0; j < FinaleEchecs.LONGUEUR; ++j) {
                    if (choix.getY() == i && choix.getX() == j) continue; // position actuelle
                    if (!choix.peutAllerEn(i, j)) continue;
                    coords.add(new Paire<>(i, j));
                }
            Collections.shuffle(coords);

            while (!coords.isEmpty()) {
                yDest = coords.get(0).getX();
                xDest = coords.get(0).getY();
                coords.remove(0);

                // Pièce alliée sur la destination
                pièceSurDest = fe.occupante(yDest, xDest);
                if (pièceSurDest != null && pièceSurDest.getJoueur() == fe.getCourant()) continue;

                if (choix.trajectoireLibre(yDest, xDest, fe)) {

                    // Le roi ne peut pas se mettre en échec
                    if (choix.craintEchec() && fe.échecSurPosition(yDest, xDest)) continue;

                    // Une autre pièce ne peut pas mettre en échec le roi
                    if (!choix.craintEchec() && fe.coupExposeRoi(choix.getY(), choix.getX(), yDest, xDest)) continue;

                    if (!fe.roiEnEchec() // si le roi n'est pas en échec
                            // ou si le roi est en échec mais que le coup le débloque
                            || fe.roiEnEchec() && fe.coupDébloqueRoi(choix.getY(), choix.getX(), yDest, xDest)) {

                        fe.jouer(choix.getY(), choix.getX(), yDest, xDest);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public boolean estHumain() {
        return false;
    }

}
