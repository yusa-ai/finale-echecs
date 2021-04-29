package echecs;

public interface IFabriqueJoueur {
    IJoueur getJoueur(String couleur, String type);
}
