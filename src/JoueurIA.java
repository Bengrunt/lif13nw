
/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
 */
public class JoueurIA extends Joueur {

    /**
     * Fonction qui récupère les coordonnées du coup d'un joueur IA.
     * @return Renvoie un tableau de deux entiers représentant les coordonnées x et y du coup.
     */
    @Override
    public int[] getCoup() {
        
        int[] retour = new int[2];

        retour[0] = (int) ( Math.random() * (double)Environnement.MAX_X );
        retour[1] = (int) ( Math.random() * (double)Environnement.MAX_Y );

        System.out.println("Votre adversaire tire en [" + retour[0] + "," + retour[1] + "]." );
        
        return retour;
    }

    @Override
    public String toString() {
        return "Joueur IA";

    }
}
