
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
 */
public class BateauRadarTireur extends Bateau {

    /**
     * Constructeur de BateauRadarTireur.
     * @param taille La taille du bateau radar-tireur.
     * @param lstBateau La liste de bateaux dans laquelle on crée le bateau radar-tireur.
     */
    public BateauRadarTireur(int taille, ArrayList<Bateau> lstBateau) {

        super(taille, lstBateau);
    }
}
