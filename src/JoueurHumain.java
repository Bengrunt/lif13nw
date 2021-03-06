
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 31/05/2009
 * @see http://code.google.com/p/lif13nw/
 */
public class JoueurHumain extends Joueur {

    /**
     * Saisie d'un coup au clavier pour le joueur humain.
     * @return Renvoie un tableau d'entiers contenant les coordonnées du coup.
     */
    @Override
    public int[] getCoup() {

        int[] retour = new int[2];
        String ligne = "";
        boolean saisieOK = false;

        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

        while (!saisieOK) {

            saisieOK = true;
            System.out.println("Saisissez les coordonnées du tir (deux entiers séparés d'un espace pris dans l'intervalle [1, " + Environnement.MAX_X + "], [1, " + Environnement.MAX_Y + "]):");

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
    public String getDeplacement(Bateau bat) {

        String dep = new String();
        boolean saisieOK = false;

        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

        while (!saisieOK) {

            saisieOK = true;
            System.out.println("Saisissez le déplacement pour ce bateau. (\"a\": avancer, \"r\": reculer, \"g\": piv vers la gauche, \"d\": piv vers la droite):");

            try {

                dep = entree.readLine();
            } catch (IOException ex) {

                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }



            if (dep.length() != 1) { // Un seul caractère

                saisieOK = false;
                System.out.println("Mauvaise saisie, recommencez:");
                continue;
            }

            if (!(dep.equalsIgnoreCase("a") || dep.equalsIgnoreCase("r") || dep.equalsIgnoreCase("g") || dep.equalsIgnoreCase("d"))) { // test validité : { A ; a ; R ; r ; D ; d ; G ; g }

                saisieOK = false;
                System.out.println("Mauvaise saisie, recommencez:");
                continue;
            }
            
            int taille = bat.getTailleBateau();
            int dx = bat.getDeltaX();
            int dy = bat.getDeltaY();
            int xca = bat.getXCaseArriere();
            int yca = bat.getYCaseArriere();
            ArrayList<Bateau> lst = this.getLstBateau();

            if(bat.getClass() == BateauBrouilleur.class) { //C'est un bateau brouilleur

                //Création d'un bateau virtuel pour calculer la possibilité du déplacement
                BateauBrouilleur batSimu = new BateauBrouilleur(taille, lst);

                batSimu.setTailleBateau(taille);
                batSimu.setXCaseArriere(xca);
                batSimu.setYCaseArriere(yca);
                batSimu.setDeltaX(dx);
                batSimu.setDeltaY(dy);

                if (dep.equalsIgnoreCase("a")) {

                    batSimu.avance();
                } else if(dep.equalsIgnoreCase("r")) {

                    batSimu.recule();
                } else if(dep.equalsIgnoreCase("g")) {

                    batSimu.pivote45direct();
                } else {

                    batSimu.pivote45indirect();
                }


                int [] caseArr = { batSimu.getXCaseArriere() , batSimu.getYCaseArriere() };

                saisieOK = !(batSimu.testChevauchement(caseArr, batSimu.getDeltaX(), batSimu.getDeltaY(), lst));

                if(!saisieOK) {
                    System.out.println("Déplacement impossible, veuillez en choisir un autre:");
                    continue;
                }
            }
            else { //C'est un bateau radar-tireur

                //Création d'un bateau virtuel pour calculer la possibilité du déplacement
                BateauRadarTireur batSimu = new BateauRadarTireur(taille, lst);

                batSimu.setTailleBateau(taille);
                batSimu.setXCaseArriere(xca);
                batSimu.setYCaseArriere(yca);
                batSimu.setDeltaX(dx);
                batSimu.setDeltaY(dy);

                if (dep.equalsIgnoreCase("a")) {

                    batSimu.avance();
                } else if (dep.equalsIgnoreCase("r")) {

                    batSimu.recule();
                } else if (dep.equalsIgnoreCase("g")) {

                    batSimu.pivote45direct();
                } else {

                    batSimu.pivote45indirect();
                }

                int [] caseArr = { batSimu.getXCaseArriere() , batSimu.getYCaseArriere() };

                saisieOK = !(batSimu.testChevauchement(caseArr, batSimu.getDeltaX(), batSimu.getDeltaY(), lst));

                if(!saisieOK) {
                    System.out.println("Déplacement impossible, veuillez en choisir un autre:");
                    continue;
                }
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
    		} else if (bat.getClass() == BateauRadarTireur.class){
    		
    			MaxTir = BateauRadarTireur.MAX_TIR;
    			MaxDep = BateauRadarTireur.MAX_DEP;
    		}

    		while(!fin){
    			
    			tirRest = MaxTir - cptTir;
    			depRest = MaxDep - cptDep;
    			saisieOK = false;
    			
    			while (!saisieOK) {
    				
    				saisieOK = true;
    				System.out.println("Quelle action voulez-vous effectuer avec ce bateau ? (\"t\": tir, \"d\": déplacement, \"s\": supprimer l'action précédament saisie, \"f\": fin de la saisie des actions pour ce bateau):");
            
    				try {

    					act = entree.readLine();
    				} catch (IOException ex) {

    					Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
    				}

    				if (act.length() != 1) { // Un seul caractère

    					saisieOK = false;
    				} else if (!(act.equalsIgnoreCase("t") || act.equalsIgnoreCase("d") || act.equalsIgnoreCase("s") || act.equalsIgnoreCase("f"))){

    					saisieOK = false;
    				}
    			}

    			if(act.equalsIgnoreCase("t")) {
    				
    				if(tirRest > 0){
    					
    					System.out.println("Ce bateau peut encore tirer " + tirRest + " fois.");
    					int [] coup = this.getCoup();
    					listeAction += "t" + " " + coup[0] + " " + coup[1] + " ";
    					nbCharAct[actionNb] = 5;
    					actionNb++;
        				cptTir++;
    				}
    				else{
                        
                        String str_neg = "";

    					if (MaxTir > 0) {
                            str_neg = "plus";
                        } else {
                            str_neg = "pas";
                        }
    					
                        System.out.println("Vous ne pouvez " + str_neg + " tirer avec ce " + bat.toString() + ".");
    				}
    			}
    			else if (act.equalsIgnoreCase("d")){
    		
    				if(depRest > 0){
    					
    					System.out.println("Ce bateau peut encore effectuer " + depRest + " déplacements.");
    					String dep = this.getDeplacement(bat);
    					listeAction = "d " + dep + " ";
    					nbCharAct[actionNb] = 4;
    					actionNb++;
    					cptDep++;
    				}
    				else{

                        String str_neg = "";

    					if (MaxDep > 0) {
                            str_neg = "plus";
                        } else {
                            str_neg = "pas";
                        }

    					System.out.println("Vous ne pouvez " + str_neg + " déplacer ce " + bat.toString() + ".");
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
    					
    					System.out.println("Il n'y a pas d'action à annuler pour ce bateau.");
    				}
    			}

    			if(tirRest == 0 && depRest == 0){
    				
    				fin = true;
    			}
    		}

    		System.out.println("Fin des Actions pour ce bateau.");
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
