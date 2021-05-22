package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import echecs.FinaleEchecs;
import joueurs.FabriqueJoueur;
import pieces.FabriquePièce;


class FinaleEchecsTest {
	
	@Test
	/**
	 * Test d'un pat
	 */
	void patTest() {
		FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();
        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur, 1);
        
        // Série de coups pour pat
        coupsPat(fe);
        assertTrue(fe.pat());
        assertFalse(fe.mat());
	}

	@Test
	/**
	 * Test d'un échec et mat
	 */
	void matTest() {
		FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();
        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur, 1);
        
        // Série de coups pour mat
        coupsMat(fe);
        assertTrue(fe.mat());
	}

	@Test
	/**
	 * Test d'un match nul suite à une insuffisance de matériel (il ne reste que 2 roi sur le terrain)
	 */
	void nulleTest() {
		FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();
        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur, 1);
        
        // Série de coups pour matériel insuffisant
        coupsNulle(fe);
        assertTrue(fe.nulle());
	}

	@Test
	/**
	 * Test de différent 
	 */
	void formatValideTest() {
		assertFalse(FinaleEchecs.formatValide("bonjour je dois faire quoi"));
		assertFalse(FinaleEchecs.formatValide("e6i6"));
		assertFalse(FinaleEchecs.formatValide("d5d9"));
		assertFalse(FinaleEchecs.formatValide("1a1g"));
		assertTrue(FinaleEchecs.formatValide("a1g1"));
	}
	
	@Test
	/**
	 * 
	 */
	void coupLégalTest() {
		FabriquePièce fPièce = new FabriquePièce();
        FabriqueJoueur fJoueur = new FabriqueJoueur();
        FinaleEchecs fe = new FinaleEchecs(fPièce, fJoueur, 1);
		
		assertFalse(fe.coupLégal(-1, 99, 2, 200));
		assertFalse(fe.coupLégal(0, 0, 0, 0));
		// Aucune pièce sur position de départ
		assertFalse(fe.coupLégal(toY('d'), toX('6'), toY('d'), toX('5')));
		// Pièce n'appartient pas au joueur courant (BLANC)
		assertFalse(fe.coupLégal(toY('e'), toX('8'), toY('d'), toX('8')));
		// Pièce mange allié
		assertFalse(fe.coupLégal(toY('f'), toX('6'), toY('e'), toX('6')));
		// Coup qui met le roi en danger
		assertFalse(fe.coupLégal(toY('f'), toY('6'), toY('f'), toX('8')));
		// Le roi se met en danger
		assertFalse(fe.coupLégal(toY('e'), toY('6'), toY('e'), toX('7')));
	}

	private int toY(char lettre) {
		return FinaleEchecs.toY(lettre);
	}
	
	private int toX(char chiffre) {
		return FinaleEchecs.toX(chiffre);
	}
	
	private void coupsPat(FinaleEchecs fe) {
		fe.jouer(toY('e'), toX('6'), toY('d'), toX('6'));
        fe.jouer(toY('e'), toX('8'), toY('d'), toX('8'));
        fe.jouer(toY('d'), toX('6'), toY('c'), toX('6'));
        fe.jouer(toY('h'), toX('6'), toY('h'), toX('7'));
        fe.jouer(toY('c'), toX('6'), toY('b'), toX('6'));
        fe.jouer(toY('d'), toX('8'), toY('c'), toX('8'));
        fe.jouer(toY('b'), toX('6'), toY('a'), toX('6'));
        fe.jouer(toY('c'), toX('8'), toY('b'), toX('8'));
        fe.jouer(toY('f'), toX('6'), toY('g'), toX('6'));
        fe.jouer(toY('h'), toX('7'), toY('h'), toX('6'));
        fe.jouer(toY('g'), toX('6'), toY('h'), toX('6'));
        fe.jouer(toY('b'), toX('8'), toY('a'), toX('8'));
        fe.jouer(toY('h'), toX('6'), toY('b'), toX('6'));
	}
	
	private void coupsMat(FinaleEchecs fe) {
		fe.jouer(toY('e'), toX('6'), toY('d'), toX('6'));
        fe.jouer(toY('e'), toX('8'), toY('d'), toX('8'));
        fe.jouer(toY('d'), toX('6'), toY('c'), toX('6'));
        fe.jouer(toY('h'), toX('6'), toY('h'), toX('7'));
        fe.jouer(toY('c'), toX('6'), toY('b'), toX('6'));
        fe.jouer(toY('d'), toX('8'), toY('c'), toX('8'));
        fe.jouer(toY('b'), toX('6'), toY('a'), toX('6'));
        fe.jouer(toY('c'), toX('8'), toY('b'), toX('8'));
        fe.jouer(toY('f'), toX('6'), toY('f'), toX('7'));
        fe.jouer(toY('b'), toX('8'), toY('a'), toX('8'));
        fe.jouer(toY('f'), toX('7'), toY('f'), toX('8'));
	}
	
	private void coupsNulle(FinaleEchecs fe) {
		fe.jouer(toY('f'), toX('6'), toY('h'), toX('6'));
        fe.jouer(toY('e'), toX('8'), toY('f'), toX('8'));
        fe.jouer(toY('h'), toX('6'), toY('h'), toX('7'));
        fe.jouer(toY('f'), toX('8'), toY('g'), toX('8'));
        fe.jouer(toY('e'), toX('6'), toY('d'), toX('6'));
        fe.jouer(toY('g'), toX('8'), toY('h'), toX('7'));
	}
}
