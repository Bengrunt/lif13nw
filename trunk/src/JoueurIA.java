
import java.util.StringTokenizer;


/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 22/05/2009
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

    /**
     * Fonction de calcul de déplacement pour le joueur IA.
     * @return Renvoie un StringTokenizer contenant les actions que fait le joueur IA.
     */
    @Override
    public StringTokenizer getDeplacement() {

        String str = new String();
        StringTokenizer dep = new StringTokenizer(str);

        return dep;
    }

    /**
     * Fonction qui donne le type de joueur.
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        return "Joueur IA";
    }
    
    public int[] getCoupSuivant(){
    	
    	
    	int[] retour1 = new int[2];
    	int[] retour2 = new int[2];
    	
    	retour2.clone()[0] = this.getCoup()[0];
    	retour2.clone()[1] = this.getCoup()[1];
    	
    	this.getCoup();
    	
    	return retour1;
    }
}
