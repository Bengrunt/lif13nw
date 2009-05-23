

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 23/05/2009
 */
public abstract class Joueur {

    //////////////////////////////////////// ATTRIBUTS /////////////////////////////////////////////////////

    protected ArrayList<Bateau> lstBateau; // Liste des bateaux du joueur.
    protected BateauRadarTireur bT; // Bateau radar-tireur
    protected BateauBrouilleur bB; // Bateau brouilleur

    //////////////////////////////////////// CONSTRUCTEURS ///////////////////////////////////////////////////

    /**
     * Constructeur de Joueur.
     */
    public Joueur() {

        lstBateau = new ArrayList(); //Liste des bateaux du joueur.
        // création des deux bateaux du joueur
        // paramètres : taille : founit la taille des bateaux
        //              lstBateau : liste des bateaux présents, afin de tester le chevauchement lors du placement initial    
        bT = new BateauRadarTireur(3, lstBateau);
        lstBateau.add(bT);
        bB = new BateauBrouilleur(2, lstBateau);
        lstBateau.add(bB);
    }


    /////////////////////////////////////////// ACCESSEURS //////////////////////////////////////////////////////

    /**
     * Accesseur de l'attribut bT de la classe "joueur".
     * @return Renvoie un Bateau.
     */
    public Bateau getBateauTireur() {
        return this.bT;
    }

    /**
     * Accesseur de l'attribut bB de la classe "joueur".
     * @return Renvoie le bateau brouilleur du joueur.
     */
    public Bateau getBateauBrouilleur() {
        return this.bB;
    }

    /**
     * Accesseur de l'attribut lstBateau de la classe "joueur".
     * @return Renvoie un ArrayList<Bateau>.
     */
    public ArrayList<Bateau> getLstBateau() {
        return this.lstBateau;
    }

    ////////////////////////////////////////// AUTRES METHODES //////////////////////////////////////////////////
    /*
     * Fonction qui détermine si un joueur a perdu.
     * @return Renvoie true si le joueur a perdu.
     */
    public boolean aPerdu() {

        return bT.estCoule();
    }

    /*
     * Retourne l'environnement exact du joueur 
     * 
     */
    public void marquerEnvironnementExact(Environnement env) {

        bT.marquerEnvironnementExact(env);
        bB.marquerEnvironnementExact(env);
    }

    /*
     * Fonction abstraite, à définir pour JoueurHumain (dans ce cas, saisie clavier du coup) et JoueurIA (dans ce cas, stratégie IA sur la base des informations perçues par le joeueur IA)
     * */
    public int[] getCoup() { // retourne les coordonnées x et y du coup
        return null;
    }

    /**
     * Fonction abstraite, pour récupérer le déplacement.
     * @return Renvoie une chaine de caractère représentant le déplacement.
     */
    public String getDeplacement() {

        String dep = new String();

        return dep;
    }

    /**
     * Procédure qui regarde si le joueur est concerné par le coup et applique les dégats occasionnés si c'est le cas.
     * @param coup Tableau de deux entiers qui décrivent les coordonnées du coup.
     */
    
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
    			
    			while (!saisieOK) {
    				

    				saisieOK = true;
    				System.out.println("Quelle action voulez-vous effectuer sur ce bateau ? (t : tir ou d : deplacement ou s : supprimer l'action précédente ou f : finir les action sur ce bateau) :");
            
    				try {

    					act = entree.readLine();
    				} catch (IOException ex) {

    					Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
    				}



    				if (act.length() == 1) { // Un seul caractère

    					saisieOK = false;
    				}

    				if (!(act.equalsIgnoreCase("t") || act.equalsIgnoreCase("d") || act.equalsIgnoreCase("s") || act.equalsIgnoreCase("f"))){

    					saisieOK = false;
    				}
    			}
    	
    			if(act.equalsIgnoreCase("t")) {
    				
    				if(tirRest > 0){
    					
    					int [] coup = this.getCoup();
    					listeAction += "t" + ";" + coup[0] + ";" + coup[1] + ";";
    					nbCharAct[actionNb] = 4;
    					actionNb++;
        				cptTir++;
    				}
    				else{
    					
    					System.out.println("Vous ne pouvez pas/plus tirer avec ce bateau");
    				}
    			}
    			else if (act.equalsIgnoreCase("d")){
    		
    				if(depRest > 0){
    					
    					String dep = this.getDeplacement();
    					listeAction += "d" + ";" + dep + ";";
    					nbCharAct[actionNb] = 3;
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
    
 public void appliquerCoup(int[] coup) {

        int i;
        int tailleBb = this.bB.getTailleBateau();
        int tailleBt = this.bT.getTailleBateau();
        
        //On vérifie si le bateau tireur est touché.
        if(this.bT.estTouche(coup)) {

            for(i = 0 ; i < tailleBt ; i++) {

                if(Arrays.equals(this.bT.calculeCoordCaseBat(i), coup)) {

                    this.bT.setIemeTouche(i, true);
                    break;
                }
            }

            System.out.println("Le bateau tireur du " + this.toString() + " a été touché sur sa " + i + "ième case");
        }

        //On vérifie si le bateau brouilleur est touché.
        if(this.bB.estTouche(coup)) {

            for(i = 0 ; i < tailleBb ; i++) {

                if(Arrays.equals(this.bB.calculeCoordCaseBat(i), coup)) {

                    this.bB.setIemeTouche(i, true);
                    break;
                }
            }

            System.out.println("Le bateau brouilleur du " + this.toString() + " a été touché sur sa " + i + "ième case");
        }
    }

    /**
     * Procédure qui teste si le déplacement est faisable et l'applique le cas échéant.
     * @param dep Chaine de caractère représentant le déplacement à effectuer.
     * @param bat Le Bateau qui se déplace.
     * @return Renvoie true si le déplacement a été effectué, false sinon.
     */
    public boolean appliquerDeplacement(String dep, Bateau bat) {

        boolean res = false;
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

            if(dep.equalsIgnoreCase("a")) {

                batSimu.avance();
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
            } else if(dep.equalsIgnoreCase("g")) {

                batSimu.pivote45direct();
            } else {

                batSimu.pivote45indirect();
            }


            int [] caseArr = { batSimu.getXCaseArriere() , batSimu.getYCaseArriere() };

            res = batSimu.testChevauchement(caseArr, batSimu.getDeltaX(), batSimu.getDeltaY(), lst);
        }

        if(res) { //Le déplacement est faisable on l'applique réellement.

            if(dep.equalsIgnoreCase("a")) {

                bat.avance();
            } else if(dep.equalsIgnoreCase("g")) {

                bat.pivote45direct();
            } else {

                bat.pivote45indirect();
            }
        }

        return res;
    }
    
    public void appliquerAction(String str, Bateau bat){
    	
    	StringTokenizer stk = new StringTokenizer(str, ";");
    	
    	while(stk.nextToken() != ""){
    		
    		if(stk.nextToken() == "t"){
        		
        		int [] coup = new int[2];
        		coup[0] = Integer.parseInt(stk.nextToken());
        		coup[1] = Integer.parseInt(stk.nextToken());
        		this.appliquerCoup(coup);
        	}
        	else if(stk.nextToken() == "d"){
        		
        		String instruction = stk.nextToken();
        		this.appliquerDeplacement(instruction, bat);
        	}
    	}
    }
}



