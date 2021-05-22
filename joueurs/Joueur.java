package joueurs;

import echecs.IJoueur;

public abstract class Joueur implements IJoueur {
	private final CouleurJoueur couleur;
	
	/**
	 * Initialise un nouveau joueur
	 * @param couleur La couleur (BLANC/NOIR) du joueur
	 */
	public Joueur(CouleurJoueur couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public abstract boolean estHumain();
	
	@Override
	public CouleurJoueur getCouleur() {
		return couleur;
	}
	
	
}
