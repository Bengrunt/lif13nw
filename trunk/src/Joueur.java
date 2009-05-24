
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 24/05/2009
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
    public String getDeplacement(Bateau bat) {

        String dep = new String();

        return dep;
    }

    /**
     * Fonction abstraite qui récupere les actions du joueur pour un bateau donné.
     * @param bat Le bateau utilisé.
     * @return Renvoie une chaine de caractères codant les différentes actions entreprises par le joueur.
     */
    public String getAction(Bateau bat){

    	return null;
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

            //Modification de str_ieme purement cosmétique...
            String str_ieme = "";
            String str_spec = "";

            if (i == 0) {
                str_ieme = "ère";
                str_spec = " Son Moteur est détruit!";
            } else if (i == tailleBt - 1) {
               str_ieme = "ième";
               str_spec = " Son Radar est détruit!";
            } else {
                str_ieme = "ième";
            }

            System.out.println("Le bateau tireur du " + this.toString() + " a été touché sur sa " + (i + 1) + str_ieme + " case." + str_spec);
        }

        //On vérifie si le bateau brouilleur est touché.
        if(this.bB.estTouche(coup)) {

            for(i = 0 ; i < tailleBb ; i++) {

                if(Arrays.equals(this.bB.calculeCoordCaseBat(i), coup)) {

                    this.bB.setIemeTouche(i, true);
                    break;
                }
            }

            //Modification de str_ieme purement cosmétique...
            String str_ieme = "";
            String str_spec = "";

            if (i == 0) {
                str_ieme = "ère";
                str_spec = " Son Moteur est détruit!";
            } else if (i == tailleBt - 1) {
               str_ieme = "ième";
               str_spec = " Son Brouilleur est détruit!";
            } else {
                str_ieme = "ième";
            }

            System.out.println("Le bateau brouilleur du " + this.toString() + " a été touché sur sa " + (i + 1) + str_ieme + " case." + str_spec + ".");
        }
    }

    /**
     * Procédure qui teste si le déplacement est faisable et l'applique le cas échéant.
     * @param dep Chaine de caractère représentant le déplacement à effectuer.
     * @param bat Le Bateau qui se déplace.
     * @return Renvoie true si le déplacement a été effectué, false sinon.
     */
    public void appliquerDeplacement(String dep, Bateau bat) {


    	if(dep.equalsIgnoreCase("a")) {

            bat.avance();

    	} else if(dep.equalsIgnoreCase("g")) {

            bat.pivote45direct();
        } else {

               bat.pivote45direct();
           }
    }
    
    public void appliquerAction(String str, Bateau bat, Joueur cible){
    	
    	StringTokenizer stk = new StringTokenizer(str);
    	
    	String temp = "";
    	
    	try{
    	
    		temp = stk.nextToken();
    	}catch (Exception e){
    		
    		System.out.println("La liste d'action du " + bat.getClass().toString() + " est vide.");
    	}
        
    	while(!(temp.equalsIgnoreCase(""))){

    		
    		if(temp.equalsIgnoreCase("t")){
        		
        		int [] coup = new int[2];
        		coup[0] = Integer.parseInt(stk.nextToken()); //VALEURS OK
        		coup[1] = Integer.parseInt(stk.nextToken()); // AVEC LE -1 QUI VA BIEN
        		//System.out.println("Vous tirez en [" + (coup[0] + 1) + "," + (coup[1] + 1) + "]." );
        		cible.appliquerCoup(coup); /// <========= IL FAUT APPLIQUER LE COUP SUR L'AUTRE JOUEUR ! :)
        	}
        	else if(temp.equalsIgnoreCase("d")){
        		
        		String instruction = stk.nextToken();
        		System.out.println("Instruction de déplacement : " + instruction);
        		this.appliquerDeplacement(instruction, bat);


        	}

            try{
    		
            	temp = stk.nextToken();
            } catch (Exception e){
            	
            	System.out.println("Fin de la liste d'action du " + bat.getClass().toString() + ".");
            	break;
            }
    	}
    }
}



