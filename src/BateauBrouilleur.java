
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 24/05/2009
 */
public class BateauBrouilleur extends Bateau {

    //////////////////////////////////////////// ATTRIBUTS /////////////////////////////////////////

    public static final int MAX_DEP = 2; // nombre d'actions de déplacements par tour.
    public static final int MAX_TIR = 0; // nombre d'actions de tir par tour.

    //////////////////////////////////////// CONSTRUCTEURS /////////////////////////////////////////

    /**
     * Constructeur de BateauBrouilleur.
     * @param taille La taille du bateau brouilleur.
     * @param lstBateau La liste de bateaux dans laquelle on crée le bateau brouilleur.
     */
    public BateauBrouilleur(int taille, ArrayList<Bateau> lstBateau) {

        super(taille, lstBateau);
    }

    /**
     * Fonction qui donne la classe du bateau.
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        return "Bateau Brouilleur";
    }
}
