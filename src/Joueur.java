
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 31/05/2009
 * @see http://code.google.com/p/lif13nw/
 */
public abstract class Joueur {

    protected ArrayList<Bateau> lstBateau; // Liste des bateaux du joueur.

    /**
     * Constructeur de Joueur.
     */
    public Joueur() {

        lstBateau = new ArrayList(); //Liste des bateaux du joueur.

        // création des deux bateaux du joueur
        BateauRadarTireur bT = new BateauRadarTireur(3, lstBateau);
        lstBateau.add(bT);
        BateauBrouilleur bB = new BateauBrouilleur(3, lstBateau);
        lstBateau.add(bB);
    }

    /**
     * Accesseur de l'attribut bT de la classe "joueur".
     * @return Renvoie le bateau radar-tireur du joueur.
     */
    public Bateau getBateauTireur() {
        return this.lstBateau.get(0);
    }

    /**
     * Accesseur de l'attribut bB de la classe "joueur".
     * @return Renvoie le bateau brouilleur du joueur.
     */
    public Bateau getBateauBrouilleur() {
        return this.lstBateau.get(1);
    }

    /**
     * Accesseur de l'attribut lstBateau de la classe "joueur".
     * @return Renvoie un ArrayList<Bateau>.
     */
    public ArrayList<Bateau> getLstBateau() {
        return this.lstBateau;
    }

    /**
     * Fonction qui détermine si un joueur a perdu.
     * @return Renvoie true si le joueur a perdu.
     */
    public boolean aPerdu() {

        return this.getBateauTireur().estCoule();
    }

    /**
     * Procédure qui marque l'environnement du joueur de la position et de l'état de ses bateaux.
     */
    public void marquerEnvironnementExact(Environnement env) {

        this.getBateauTireur().marquerEnvironnementExact(env);
        this.getBateauBrouilleur().marquerEnvironnementExact(env);
    }

    /**
     * Fonction abstraite, pour récuperer l'action de tir d'un joueur.
     * @return Renvoie les coordonnées de l'endroit ou le joueur tire.
     */
    public int[] getCoup() {

        return null;
    }

    /**
     * Fonction abstraite, pour récupérer l'action de déplacement d'un joueur pour un bateau donné.
     * @param bat Le bateau à déplacer.
     * @return Renvoie une chaine de caractère représentant le déplacement.
     */
    public String getDeplacement(Bateau bat) {

        return null;
    }

    /**
     * Fonction abstraite qui récupere les actions du joueur pour un bateau donné.
     * @param bat Le bateau utilisé.
     * @return Renvoie une chaine de caractères codant les différentes actions entreprises par le joueur.
     */
    public String getAction(Bateau bat){

    	return null;
    }

    /**
     * Procédure qui calcule et génère les effets d'un tir sur les bateaux du joueur.
     * @param coup Les coordonnées de l'endroit où l'adversaire a tiré.
     */
    public void appliquerCoup(int[] coup) {

        int i;
        int tailleBt = this.getBateauTireur().getTailleBateau();
        int tailleBb = this.getBateauBrouilleur().getTailleBateau();
        boolean batTouche = false;

        //On vérifie si le bateau tireur est touché.
        if(this.getBateauTireur().estTouche(coup)) {

            batTouche = true;

            for(i = 0 ; i < tailleBt ; i++) {

                if(Arrays.equals(this.getBateauTireur().calculeCoordCaseBat(i), coup)) {

                    this.getBateauTireur().setIemeTouche(i, true);
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
        if(this.getBateauBrouilleur().estTouche(coup)) {

            batTouche = true;

            for(i = 0 ; i < tailleBb ; i++) {

                if(Arrays.equals(this.getBateauBrouilleur().calculeCoordCaseBat(i), coup)) {

                    this.getBateauBrouilleur().setIemeTouche(i, true);
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

        if(!batTouche) {
            System.out.println("Aucun bateau n'a été touché par le tir !");
        }
    }

    /**
     * Procédure qui teste si le déplacement est faisable et l'applique le cas échéant.
     * @param dep Chaine de caractère représentant le déplacement à éffectuer.
     * @param bat Le Bateau qui se déplace.
     * @return Renvoie true si le déplacement a été effectué.
     */
    public void appliquerDeplacement(String dep, Bateau bat) {


    	if(dep.equalsIgnoreCase("a")) {

            bat.avance();

        } else if (dep.equalsIgnoreCase("r")) {

            bat.recule();
    	} else if (dep.equalsIgnoreCase("g")) {

            bat.pivote45direct();
        } else {

           bat.pivote45indirect();
        }
    }

    /**
     * Procédure qui calcule et génère les effets des actions du joueur.
     * @param str Chaine de caractères réprésentant la liste d'actions du joueur.
     * @param bat Bateau sur lequel le joueur agit.
     * @param cible Joueur adverse sur lequel le joueur tire.
     */
    public void appliquerAction(String str, Bateau bat, Joueur cible){
    	
    	StringTokenizer stk = new StringTokenizer(str);
    	
    	String temp = "";
    	
    	try{
    	
    		temp = stk.nextToken();
    	}catch (Exception e){
    		
    		System.out.println("La liste d'action du " + bat.toString() + " est vide.");
    	}
        
    	while(!(temp.equalsIgnoreCase(""))){

    		if(temp.equalsIgnoreCase("t")){
        		
        		int [] coup = new int[2];
        		coup[0] = Integer.parseInt(stk.nextToken());
        		coup[1] = Integer.parseInt(stk.nextToken());
        		cible.appliquerCoup(coup); // On applique le coup donné sur les navires de l'adversaire.
        	}
        	else if(temp.equalsIgnoreCase("d")){
        		
        		String instruction = stk.nextToken();
        		System.out.println("Instruction de déplacement : " + instruction);
                this.appliquerDeplacement(instruction, bat);
        	}

            try{
    		
            	temp = stk.nextToken();
            } catch (Exception e){
            	
            	System.out.println("Fin de la liste d'action du " + bat.toString() + ".");
            	break;
            }
    	}
    }
}
