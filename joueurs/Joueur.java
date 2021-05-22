package joueurs;

import echecs.IJoueur;

public abstract class Joueur implements IJoueur {
	private final CouleurJoueur couleur;
	
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
