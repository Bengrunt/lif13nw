
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
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

        tailleBateau = _tailleBateau;
        touche = new boolean[tailleBateau];

        for (int i = 0; i < tailleBateau; i++) {

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
     * @param i Numéro de la case de l'attribut "touche" à laquelle on veut accéder.
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

        do {

            this.setXCaseArriere((int) (Math.random() * Environnement.MAX_X));
            this.setYCaseArriere((int) (Math.random() * Environnement.MAX_Y));


            switch ((int) (Math.random() * 4) + 1) {
                case 1:
                    deltaX = tailleBateau;
                    deltaY = 0;
                    break;
                case 2:
                    deltaX = -tailleBateau;
                    deltaY = 0;
                    break;

                case 3:
                    deltaX = 0;
                    deltaY = tailleBateau;
                    break;

                case 4:
                    deltaY = -tailleBateau;
                    deltaX = 0;
                    break;
            }

        } while (!coordonneesPossibles(xCaseArriere + deltaX, yCaseArriere + deltaY));
        	
        	
        	; // TODO ajouter test de chevauchement, en utilisant lstBatea

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
     * @param coords Coordonnées (x,y) à tester.
     * @param lstBateau Liste des bateaux avec lesquels les coordonnées sont comparées.
     * @return Renvoie true si les coordonnées sont disponibles.
     */
    public boolean testChevauchement(int[] coords, ArrayList<Bateau> lstBateau) {
        boolean res = false;

        for(int i = 0 ; i < lstBateau.size() ; i++) {

            for(int j = 0 ; j < lstBateau.get(i).getTailleBateau() ; j++) {

                res = !coordonneesPossibles(lstBateau.get(i).calculeCoordCaseBat(j));
            }
        }

        return res;
    }
    
    private boolean testChevauchement(int[] coords, ArrayList<Bateau> lstBateau){
    	
    	int i = 0;
    	int j = 0;
    	boolean b = true;
    	while((i < lstBateau.size()) && b){
    		
    		while((j <= lstBateau.get(j).tailleBateau) && b){
    			
    			int[] test = new int[2];
    			test = this.calculeCoordCaseBat(j);
    			
    			if(!this.coordonneesPossibles(test)){
    				
    				b = false;
    			}
    			
    		}
    		j++;
    	}
    	i++;
    	return b;
    }

    /*
     * Marque l'environnement env d'un 'X' pour chaque case associée au bateau
     * 
     */
    public void marquerEnvironnementExact(Environnement env) {

        if (deltaY != 0) {




            for (int i = 0; i < tailleBateau; i++) {

                char info = 'X';
                if (touche[i]) {

                    info = 'I';
                }


                env.set(xCaseArriere, yCaseArriere + i * Integer.signum(deltaY), 'X');

            }

        } else {



            for (int i = 0; i < tailleBateau; i++) {

                char info = 'X';
                if (touche[i]) {

                    info = 'I';
                }

                env.set(xCaseArriere + i * Integer.signum(deltaX), yCaseArriere, 'X');

            }


        }

    }

    /**
     * Fonction qui calcule les coordonnées sur la grille d'une case du bateau.
     * @param numCase Numéro de la case dont on veut calculer les coordonnées.
     * @return Renvoie un tableau de deux entiers, respectivements x et y les coordonnées de la case du bateau.
     */
    public int[] calculeCoordCaseBat(int numCase) {

        int[] coords = new int[2];
        coords[0] = this.getXCaseArriere() + (numCase - 1) * this.getDeltaX();
        coords[1] = this.getYCaseArriere() + (numCase - 1) * this.getDeltaY();

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
        int i = 1;

        while (i <= this.getTailleBateau()) {
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

        int i = 1;
        int casesTouchees = 0;

        while (i <= this.getTailleBateau()) {
            if (this.getIemeTouche(i) == true) {
                casesTouchees++;
            }
            i++;
        }

        if (casesTouchees == this.getTailleBateau()) {
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
        else if(this.getDeltaX() == 1 && this.getDeltaY() == 1) {
            this.setDeltaX(0);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == 1) {
            this.setDeltaX(-1);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 1) {
            this.setDeltaY(0);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 0) {
            this.setDeltaY(-1);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == -1) {
            this.setDeltaX(0);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == -1) {
            this.setDeltaX(1);
        }
        else {
            this.setDeltaY(0);
        }
    }
    
    /**
     * Procédure qui fait pivoter le bateau sur lui-même de 45° dans le sens indirect.
     */
    public void pivote45indirect() {
        
        if (this.getDeltaX() == 1 && this.getDeltaY() == 0) {
            this.setDeltaY(-1);
        }
        else if(this.getDeltaX() == 1 && this.getDeltaY() == -1) {
            this.setDeltaX(0);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == -1) {
            this.setDeltaX(-1);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == -1) {
            this.setDeltaY(0);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 0) {
            this.setDeltaY(1);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 1) {
            this.setDeltaX(0);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == 1) {
            this.setDeltaX(1);
        }
        else {
            this.setDeltaY(0);
        }
    }

}

