
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 31/05/2009
 * @see http://code.google.com/p/lif13nw/
 */
public class BateauBrouilleur extends Bateau {

    public static final int MAX_DEP = 1; // nombre d'actions de déplacements par tour.
    public static final int MAX_TIR = 0; // nombre d'actions de tir par tour.

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
     * @return Renvoie une chaine de caractères donnant le type du bateau.
     */
    @Override
    public String toString() {

        return "Bateau Brouilleur";
    }
}
