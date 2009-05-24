
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 23/05/2009
 */
public abstract class Bateau {

    ///////////////////////////////// ATTRIBUTS ////////////////////////////////////////////////

    protected int xCaseArriere,  yCaseArriere; // position de l'arrière du bateau
    protected int tailleBateau; // taille du bateau
    protected boolean[] touche; // état de la coque (touche[0] correspond à la case arrière)
    protected int deltaX,  deltaY; // orientation du bateau, par rapport à sa case arrière

    //////////////////////////////// CONSTRUCTEURS: ////////////////////////////////////////////

    /*
     * Constructeur de Bateau : crée un bateau placé aléatoirement dans l'environnement.
     * @param _tailleBateau Taille du bateau.
     * @param lstBateau Liste de bateaux du joueur.
     */
    public Bateau(int _tailleBateau, ArrayList<Bateau> lstBateau) {

        this.setTailleBateau(_tailleBateau);
        int tailleBat = this.getTailleBateau();
        touche = new boolean[tailleBat];

        for (int i = 0; i < tailleBat; i++) {

            touche[i] = false;
        }

        initPosition(lstBateau);
    }

    /////////////////////////////// ACCESSEURS: ////////////////////////////////////////////////

    /**
     * Accesseur de l'attribut "xCaseArriere" de la classe Bateau.
     * @return Renvoie un entier.
     */
    public int getXCaseArriere() {

        return this.xCaseArriere;
    }

    /**
     * Accesseur de l'attribut "yCaseArriere" de la classe Bateau.
     * @return Renvoie un entier.
     */
    public int getYCaseArriere() {

        return this.yCaseArriere;
    }

    /**
     * Accesseur de l'attribut "tailleBateau" de la classe Bateau.
     * @return Renvoie un entier.
     */
    public int getTailleBateau() {

        return this.tailleBateau;
    }

    /**
     * Accesseur de la ième case de l'attribut "touche" de la classe Bateau.
     * @param i Indice de la case de l'attribut "touche" à laquelle on veut accéder.
     * @return Renvoie un booléen.
     */
    public boolean getIemeTouche(int i) {

        return this.touche[i];
    }

    /**
     * Accesseur de l'attribut "deltaX" de la classe Bateau.
     * @return Renvoie un entier.
     */
    public int getDeltaX() {

        return this.deltaX;
    }

    /**
     * Accesseur de l'attribut "deltaY" de la classe Bateau.
     * @return Renvoie un entier.
     */
    public int getDeltaY() {

        return this.deltaY;
    }

    ///////////////////////// MUTATEURS: /////////////////////////////////

    /**
     * Mutateur de l'attribut "xCaseArriere" de la classe Bateau.
     * @param x Nouvelle valeur de l'attribut.
     */
    public void setXCaseArriere(int x) {

        this.xCaseArriere = x;
    }

    /**
     * Mutateur de l'attribut "yCaseArriere" de la classe Bateau.
     * @param y Nouvelle valeur de l'attribut.
     */
    public void setYCaseArriere(int y) {

        this.yCaseArriere = y;
    }

    /**
     * Mutateur de l'attribut "tailleBateau" de la classe Bateau.
     * @param t Nouvelle valeur de l'attribut.
     */
    public void setTailleBateau(int t) {

        this.tailleBateau = t;
    }

    /**
     * Mutateur de la ième case de l'attribut "touche" de la classe Bateau.
     * @param i Numéro de la case de l'arribut "touche" que l'on veut modifier.
     * @param mod Nouvelle valeur de la ième case de l'attribut "touche".
     */
    public void setIemeTouche(int i, boolean mod) {

        this.touche[i] = mod;
    }

    /**
     * Mutateur de l'attribut "deltaX" de la classe Bateau.
     * @param dx Nouvelle valeur de l'attribut.
     */
    public void setDeltaX(int dx) {

        this.deltaX = dx;
    }

    /**
     * Mutateur de l'attribut "deltaY" de la classe Bateau.
     * @param dy Nouvelle valeur de l'attribut.
     */
    public void setDeltaY(int dy) {

        this.deltaY = dy;
    }

    //////////////////////////////////////////////////////// AUTRES METHODES: ///////////////////////////////////////////////////////////////////

    /**
     * Procédure qui initialise aléatoirement la position de la case arrière et de la direction, puis calcul de validité de la position obtenue.
     * @param lstBateau Liste de bateaux.
     */
    private void initPosition(ArrayList<Bateau> lstBateau) {

    	int[] michel = new int [2];
    	
        do {

            this.setXCaseArriere((int) (Math.random() * Environnement.MAX_X));
            this.setYCaseArriere((int) (Math.random() * Environnement.MAX_Y));
            
            michel[0] = this.getXCaseArriere();
            michel[1] = this.getYCaseArriere();
            
            switch ((int) (Math.random() * 4) + 1) {
                case 1:
                    this.setDeltaX(1); 
                    this.setDeltaY(0); 
                    break;
                case 2:
                    this.setDeltaX(-1);
                    this.setDeltaY(0); 
                    break;

                case 3:
                	this.setDeltaX(0);
                	this.setDeltaY(1);
                    break;

                case 4:
                	this.setDeltaX(0);
                    this.setDeltaY(-1);
                    break;
            }

        } while (this.testChevauchement(michel, this.getDeltaX(), this.getDeltaY(), lstBateau));

    }

    /*
     * Fonction qui teste si des coordonnées sont bien dans l'environnement.
     * @param coords Coordonnées (x,y) à tester.
     * @return Renvoie true si les coordonnées sont bien dans l'environnement.
     */

    private boolean coordonneesPossibles(int[] coords) {
        
        return ((coords[0] >= 0) && (coords[0] < Environnement.MAX_X) && (coords[1] >= 0) && (coords[1] < Environnement.MAX_Y));
    }

    /**
     * Fonction qui teste si des coordonnées sont disponibles (i.e. teste si il n y a pas chevauchement avec un bateau existant).
     * @param caseArr Coordonnées de la case arriere du bateau à tester.
     * @param deltaX Orientation du bateau à tester.
     * @param deltaY Orientation du bateau à tester.
     * @param lstBateau Liste des bateaux avec lesquels les coordonnées sont comparées.
     * @return Renvoie true si les coordonnées sont disponibles.
     */
    
    protected boolean testChevauchement(int[] caseArr, int deltaX, int deltaY, ArrayList<Bateau> lstBateau) {
    	
    	boolean chevauche = false;
    	int i = 0;
    	int j = 0;
    	int k = 0;
    	int tailleBat = this.getTailleBateau();
    	int[] test = new int [2];
    	int[] test2 = new int [2];
    	int tailleList = lstBateau.size();
    	
    	while((i < tailleBat ) && !chevauche) {
    		
    		test[0] = caseArr[0] + i * deltaX;
            test[1] = caseArr[1] + i * deltaY;
            
    		if(!this.coordonneesPossibles(test)) {
    			
    			chevauche = true;
    			break;
    		}
    		
    		while((j < tailleList) && !chevauche) {
    			
    			while((k < lstBateau.get(j).getTailleBateau())) {
    				
    				test2[0] = (lstBateau.get(j).calculeCoordCaseBat(k))[0];
    				test2[1] = (lstBateau.get(j).calculeCoordCaseBat(k))[1];
    				
    				if((test[0] == test2[0]) && (test[1] == test2[1])) {

                        //Ignore le chevauchement pour sa propre case arriere (nécéssaire pour pivotement).
                        if(k == 0 && this == lstBateau.get(j) && test[0] == this.getXCaseArriere() && test[1] == this.getYCaseArriere()) {
                            chevauche = false;
                            break;
                        }
    					
    					chevauche = true;
    					break;
    				}
    				k++;
    			}
    			j++;
    		}
    		i++;	
    	}

        return chevauche;
    }

    /**
     * Marque l'environnement env d'un 'X' pour chaque case associée au bateau, et d'un 'I' si la case est touchée.
     * @param env L'environnement où se trouve le bateau à afficher.
     */
    public void marquerEnvironnementExact(Environnement env) {
        
        int tailleBat = this.getTailleBateau();
        int[] coords = new int[2];
        
        for (int i = 0 ; i < tailleBat; i++) {

            coords = this.calculeCoordCaseBat(i);
            /*System.out.println("coord 0 : " + coords[0]);
            System.out.println("coord 1 : " + coords[1]);*/

            if (this.getIemeTouche(i)) {

                env.set(coords[0], coords[1], 'I');
            }

            else {

                if(i == 0) { // C'est le moteur

                    env.set(coords[0], coords[1], 'M');
                } else if(i == tailleBat - 1) { // C'est l'équipement spécial.

                    if(this.getClass() == BateauRadarTireur.class) { // Si c'est un bateau radar-tireur

                        env.set(coords[0], coords[1], 'R');
                    } else { // Si c'est un brouilleur

                        env.set(coords[0], coords[1], 'B');
                    }
                } else {

                    env.set(coords[0], coords[1], 'X');
                }
            }
        }
    }

    /**
     * Fonction qui calcule les coordonnées sur la grille d'une case du bateau.
     * @param index Indice de la case dont on veut calculer les coordonnées (l'indiciation commence à 0).
     * @return Renvoie un tableau de deux entiers, respectivements x et y les coordonnées de la case du bateau.
     */
    public int[] calculeCoordCaseBat(int index) {

        int[] coords = new int[2];
        coords[0] = this.getXCaseArriere() + index * this.getDeltaX();
        coords[1] = this.getYCaseArriere() + index * this.getDeltaY();

        return coords;
    }

    /**
     * Fonction qui détermine si le bateau est touché.
     * @param coup Un tableau de deux entiers représentant les coordonnées de l'endroit où à été tiré le coup.
     * @return Renvoie un booléen qui vaut true si le bateau est touché par le coup.
     */
    public boolean estTouche(int[] coup) {

        boolean res = false;
        int[] coords = new int[2];
        int i = 0;
        int tailleBat = this.getTailleBateau();

        while (i < tailleBat) {
            coords = this.calculeCoordCaseBat(i);
            if ((coords[0] == coup[0]) && (coords[1] == coup[1])) {
                res = true;
                break;
            }
            i++;
        }

        return res;
    }

    /**
     * Fonction qui détermine si un bateau est coulé.
     * @return Renvoie un booléen qui vaut true si le bateau est coulé.
     */
    public boolean estCoule() {

        int i = 0;
        int casesTouchees = 0;
        int tailleBat = this.getTailleBateau();

        while (i < tailleBat) {
            if (this.getIemeTouche(i) == true) {
                casesTouchees++;
            }
            i++;
        }

        if (casesTouchees == tailleBat) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Procédure qui fait avancer le bateau d'une case selon son axe directeur.
     */
	public void avance() {
		
		this.setXCaseArriere(this.getXCaseArriere() + this.getDeltaX());
		this.setXCaseArriere(this.getYCaseArriere() + this.getDeltaY());
	}
    
    /**
     * Procédure qui fait pivoter le bateau sur lui-même de 45° dans le sens direct.
     */
    public void pivote45direct() {
        
        if (this.getDeltaX() == 1 && this.getDeltaY() == 0) {
            this.setDeltaY(1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == 1) {
            this.setDeltaX(-1);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 0) {
            this.setDeltaY(-1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == -1) {
            this.setDeltaX(1);
        }
    }
    
    /**
     * Procédure qui fait pivoter le bateau sur lui-même de 45° dans le sens indirect.
     */
    public void pivote45indirect() {
        
        if (this.getDeltaX() == 1 && this.getDeltaY() == 0) {
            this.setDeltaY(-1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == -1) {
            this.setDeltaX(-1);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 0) {
            this.setDeltaY(1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == 1) {
            this.setDeltaX(1);
        }
    }
}

