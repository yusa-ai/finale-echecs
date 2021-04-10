package echecs;

public class Joueur {

    private final CouleurJoueur couleur;

    public Joueur(CouleurJoueur couleur) {
        this.couleur = couleur;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }
}
