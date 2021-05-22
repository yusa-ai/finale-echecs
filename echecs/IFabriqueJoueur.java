package echecs;

public interface IFabriqueJoueur {
	/**
	 * Revoie le joueur créé si son type et sa couleur existe
	 * @param couleur La couleur du joueur (Blanc/Noir)
	 * @param type Le type du joueur (Humain/IA)
	 * @return le joueur / nulle
	 */
    IJoueur getJoueur(String couleur, String type);
}
