package echecs;

public interface IFabriquePièce {
	/**
	 * Renvoie la pièce créée si son nom de pièce existe
	 * @param nom le nom de pièce
	 * @param joueur le joueur qui possède la pièce
	 * @param y la position y de départ
	 * @param x la position x de départ
	 * @return la pièce / nulle
	 */
    IPièce getPièce(String nom, IJoueur joueur, int y, int x);
}
