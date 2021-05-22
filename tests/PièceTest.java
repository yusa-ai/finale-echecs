package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pieces.*;
import joueurs.Humain;
import joueurs.CouleurJoueur;

class PièceTest {

	@Test
	/**
	 * Test des déplacements théoriquement possibles du roi
	 * (les 8 cases autour de lui)
	 */
	void roiPeutAllerEnTest() {
		Humain h = new Humain(CouleurJoueur.BLANC);
		Pièce roi = new Roi(h, 0, 0);
		
		int rx = roi.getX();
        int ry = roi.getY();

        for (int x = rx + 1; x >= rx - 1; --x) {
        	for (int y = ry - 1; y <= ry + 1; ++y) {
        		if (x == rx && y == ry) continue;
        	
        		assertTrue(roi.peutAllerEn(y, x));
        	}
        }
        
        assertFalse(roi.peutAllerEn(0, 2));
	}

	@Test
	/**
	 * Test des déplacements théoriquement possibles de la tour
	 * (horizontaux OU verticaux)
	 */
	void tourPeutAllerEnTest() {
		Humain h = new Humain(CouleurJoueur.BLANC);
		Pièce tour = new Tour(h, 0, 0);
		
		assertTrue(tour.peutAllerEn(0, 6));
		assertTrue(tour.peutAllerEn(6, 0));
		assertFalse(tour.peutAllerEn(1, 1));
	}
	
}
