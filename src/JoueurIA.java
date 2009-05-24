
import java.util.ArrayList;


/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 24/05/2009
 */
public class JoueurIA extends Joueur {

    /**
     * Fonction qui récupère les coordonnées du coup d'un joueur IA.
     * @return Renvoie un tableau de deux entiers représentant les coordonnées x et y du coup.
     */
    @Override
    public int[] getCoup() {
        
        int[] retour = new int[2];

        retour[0] = (int) ( Math.random() * Environnement.MAX_X );
        retour[1] = (int) ( Math.random() * Environnement.MAX_Y );

        System.out.println("Votre adversaire tire en [" + (retour[0] + 1) + "," + (retour[1] +1) + "]." );
        
        return retour;
    }

    /**
     * Fonction de calcul de déplacement pour le joueur IA.
     * @return Renvoie un StringTokenizer contenant les actions que fait le joueur IA.
     */
    @Override
    public String getDeplacement(Bateau bat) {

        Boolean res = false;
        String dep = new String();

        int taille = bat.getTailleBateau();
        int dx = bat.getDeltaX();
        int dy = bat.getDeltaY();
        int xca = bat.getXCaseArriere();
        int yca = bat.getYCaseArriere();
        ArrayList<Bateau> lst = this.getLstBateau();

        while(! res) {

            int rnd = (int) Math.random() * 4;

            switch(rnd) {

                case 0 : dep = "a";
                break;

                case 1 : dep = "r";
                break;

                case 2 : dep = "g";
                break;

                case 3 : dep = "d";
                break;
            }


            if(bat.getClass() == BateauBrouilleur.class) { //C'est un bateau brouilleur

                //Création d'un bateau virtuel pour calculer la possibilité du déplacement
                BateauBrouilleur batSimu = new BateauBrouilleur(taille, lst);

                batSimu.setTailleBateau(taille);
                batSimu.setXCaseArriere(xca);
                batSimu.setYCaseArriere(yca);
                batSimu.setDeltaX(dx);
                batSimu.setDeltaY(dy);

                if(dep.equalsIgnoreCase("a")) {

                    batSimu.avance();
                } else if(dep.equalsIgnoreCase("r")) {

                    batSimu.recule();
                } else if(dep.equalsIgnoreCase("g")) {

                    batSimu.pivote45direct();
                } else {

                    batSimu.pivote45indirect();
                }


                int [] caseArr = { batSimu.getXCaseArriere() , batSimu.getYCaseArriere() };

                res = batSimu.testChevauchement(caseArr, batSimu.getDeltaX(), batSimu.getDeltaY(), lst);


            }
            else { //C'est un bateau radar-tireur

                //Création d'un bateau virtuel pour calculer la possibilité du déplacement
                BateauRadarTireur batSimu = new BateauRadarTireur(taille, lst);

                batSimu.setTailleBateau(taille);
                batSimu.setXCaseArriere(xca);
                batSimu.setYCaseArriere(yca);
                batSimu.setDeltaX(dx);
                batSimu.setDeltaY(dy);

                if(dep.equalsIgnoreCase("a")) {

                    batSimu.avance();
                } else if(dep.equalsIgnoreCase("r")) {

                    batSimu.recule();
                } else if(dep.equalsIgnoreCase("g")) {

                    batSimu.pivote45direct();
                } else {

                    batSimu.pivote45indirect();
                }


                int [] caseArr = { batSimu.getXCaseArriere() , batSimu.getYCaseArriere() };

                res = batSimu.testChevauchement(caseArr, batSimu.getDeltaX(), batSimu.getDeltaY(), lst);
            }
        }

        return dep;
    }
    
    @Override
    public String getAction(Bateau bat){
    	
    	int random = 0;
    	String listeAction = "";
    	
    	
    	random = (int) ( Math.random()) * 2;
    	
    	switch(random) {
    		
    	case 0 :
    		int [] coup = this.getCoup();
    		listeAction += "t " + coup[0] + " " + coup[1] + " ";
    		break;
    		
    	case 1 :
    		listeAction += "d " + this.getDeplacement(bat) + " " ; 
    		break;
    	}
    	
    	return listeAction;
    }

    /**
     * Fonction qui donne le type de joueur.
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        return "Joueur IA";
    }
}
