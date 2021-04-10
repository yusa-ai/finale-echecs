package appli;

import echecs.FinaleEchecs;
import pieces.Roi;

public class Main {
    public static void main(String[] args) {
        FinaleEchecs fe = new FinaleEchecs();
        fe.damier[6][2] = new Roi();
        System.out.println(fe);
    }
}
