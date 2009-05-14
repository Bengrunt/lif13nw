
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
 */
public class BateauBrouilleur extends Bateau {

    /**
     * Constructeur de BateauBrouilleur.
     * @param taille La taille du bateau brouilleur.
     * @param lstBateau La liste de bateaux dans laquelle on crée le bateau brouilleur.
     */
    public BateauBrouilleur(int taille, ArrayList<Bateau> lstBateau) {

        super(taille, lstBateau);
    }
}
