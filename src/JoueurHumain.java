
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 23/05/2009
 */
public class JoueurHumain extends Joueur {

    //////////////////////////////////////////////////// AUTRES METHODES /////////////////////////////////////////////////////////

    /*
     * Saisie d'un coup au clavier pour le joueur humain.
     * @return Renvoie un tableau d'entiers.
     */
    @Override
    public int[] getCoup() {

        int[] retour = new int[2];
        String ligne = "";
        boolean saisieOK = false;

        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

        while (!saisieOK) {

            saisieOK = true;
            System.out.println("Veuillez entrer les coordonnées du coup (deux entiers séparés d'un espace [1, " + Environnement.MAX_X + "], [1, " + Environnement.MAX_Y + "]):");

            try {

                ligne = entree.readLine();
            } catch (IOException ex) {

                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }

            StringTokenizer st = new StringTokenizer(ligne);

            if (st.countTokens() == 2) { // deux nombres séparés par un espace

                try {

                    retour[0] = Integer.parseInt(st.nextToken()) - 1; // coordonnées manipulées à partir de 0
                    retour[1] = Integer.parseInt(st.nextToken()) - 1;
                } catch (NumberFormatException e) {

                    saisieOK = false; // la valeur saisie n'est probablement pas un nombre
                }
            } else {

                saisieOK = false;
            }

            if (!(retour[0] >= 0 && retour[0] < Environnement.MAX_X && retour[1] >= 0 && retour[1] < Environnement.MAX_Y)) { // test validité

                saisieOK = false;
            }
        }

        return retour;
    }

    /**
     * Fonction de saisie de déplacement clavier pour le joueur humain.
     * @return Renvoie un StringTokenizer.
     */
    @Override
    public String getDeplacement() {

        String dep = new String();
        boolean saisieOK = false;

        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

        while (!saisieOK) {

            saisieOK = true;
            System.out.println("Veuillez entrer votre série de déplacements séparés par des espaces.(a : avancer ; g : piv à gauche ; d : piv à droite) ");

            try {

                dep = entree.readLine();
            } catch (IOException ex) {

                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }



            if (dep.length() != 1) { // Un seul caractère

                saisieOK = false;
            }

            if (!(dep.equalsIgnoreCase("a") || dep.equalsIgnoreCase("g") || dep.equalsIgnoreCase("d"))) { // test validité : { A ; a ; D ; d ; G ; g }

                saisieOK = false;
            }
        }

        return dep;
    }

    /**
     * Fonction qui récupere les actions du joueur pour un bateau donné.
     * @param bat Le bateau utilisé.
     * @return Renvoie une chaine de caractères codant les différentes actions entreprises par le joueur.
     */
    @Override
    public String getAction(Bateau bat){
    	
    	int cptTir = 0;
    	int cptDep = 0;
    	int tirRest;
    	int depRest;
    	int MaxTir = 0;
    	int MaxDep = 0;
    	int actionNb = 0;
    	int [] nbCharAct = new int[100];
    	
    	String act = "";
    	String listeAction = "";

    	boolean saisieOK = false;
    	boolean fin = false;
    	BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));
    	
    		if (bat.getClass() == BateauBrouilleur.class){
    		
    			MaxTir = BateauBrouilleur.MAX_TIR;
    			MaxDep = BateauBrouilleur.MAX_DEP;
    		}
    	
    		if (bat.getClass() == BateauRadarTireur.class){
    		
    			MaxTir = BateauRadarTireur.MAX_TIR;
    			MaxDep = BateauRadarTireur.MAX_DEP;
    		}
    	
    		while(!fin){
    			
    			tirRest = MaxTir - cptTir;
    			depRest = MaxDep - cptDep;
    			saisieOK = false;
    			
    			while (!saisieOK) {
    				
    				saisieOK = true;
    				System.out.println("Quelle action voulez-vous effectuer sur ce bateau ? (t : tir ou d : deplacement ou s : supprimer l'action précédente ou f : finir les action sur ce bateau) :");
            
    				try {

    					act = entree.readLine();
    				} catch (IOException ex) {

    					Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
    				}



    				if (act.length() != 1) { // Un seul caractère

    					saisieOK = false;
    				}
    				
    				if (!(act.equalsIgnoreCase("t") || act.equalsIgnoreCase("d") || act.equalsIgnoreCase("s") || act.equalsIgnoreCase("f"))){

    					saisieOK = false;
    				}
    			}
    	
    			if(act.equalsIgnoreCase("t")) {
    				
    				if(tirRest > 0){
    					
    					System.out.println("Il vous reste " + tirRest + " tirs");
    					int [] coup = this.getCoup();
    					listeAction += "t" + " " + coup[0] + " " + coup[1] + " ";
    					nbCharAct[actionNb] = 5;
    					actionNb++;
        				cptTir++;
    				}
    				else{
    					
    					System.out.println("Vous ne pouvez pas/plus tirer avec ce bateau");
    				}
    			}
    			else if (act.equalsIgnoreCase("d")){
    		
    				if(depRest > 0){
    					
    					System.out.println("Il vous reste " + depRest + " déplacements");
    					String dep = this.getDeplacement();
    					listeAction += "d" + " " + dep + " ";
    					nbCharAct[actionNb] = 4;
    					actionNb++;
    					cptDep++;
    				}
    				else{
    					
    					System.out.println("Vous ne pouvez pas/plus déplacer ce bateau");
    				}
    			}
    			else if (act.equalsIgnoreCase("f")){
    	    		
    				fin = true;
    			}
    			else if (act.equalsIgnoreCase("s")){
    	    		
    				if(actionNb > 0){
    					
    					StringBuffer stb = new StringBuffer(listeAction);
        				stb.trimToSize();
        				int start = stb.length() - nbCharAct[actionNb];
        				int end = stb.length();
        				stb.delete(start, end);
        				listeAction = stb.toString();
        				actionNb++;
    				}
    				else{
    					
    					System.out.println("Il n'y a pas d'action à annuler");
    				}
    				
    				
    			}
    			if((tirRest <= 0) && depRest <= 0){
    				
    				fin = true;
    			}
    		}

    		
    		System.out.println("Fin des Actions pour ce bateau");
    		return listeAction;

    }

    /**
     * Fonction qui donne le type de joueur.
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        return "Joueur Humain";
    }
}
