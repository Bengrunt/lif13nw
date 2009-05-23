
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 23/05/2009
 */
public class BateauRadarTireur extends Bateau {

    /////////////////////////////////////////// ATTRIBUTS //////////////////////////////////////////

    public static final int MAX_DEP = 1; // nombre d'actions de déplacements par tour.
    public static final int MAX_TIR = 2; // nombre d'actions de tir par tour.

    ///////////////////////////////////////// CONSTRUCTEURS ////////////////////////////////////////

    /**
     * Constructeur de BateauRadarTireur.
     * @param taille La taille du bateau radar-tireur.
     * @param lstBateau La liste de bateaux dans laquelle on crée le bateau radar-tireur.
     */
    public BateauRadarTireur(int taille, ArrayList<Bateau> lstBateau) {

        super(taille, lstBateau);
    }

    /**
     * Fonction qui donne la classe du bateau.
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        return "Bateau Radar-Tireur";
    }
}
