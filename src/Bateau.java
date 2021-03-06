
import java.util.ArrayList;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 31/05/2009
 * @see http://code.google.com/p/lif13nw/
 */
public abstract class Bateau {

    protected int xCaseArriere,  yCaseArriere; // position de l'arrière du bateau
    protected int tailleBateau; // taille du bateau (en nombre de cases)
    protected boolean[] touche; // état de la coque (touche[0] correspond à la case arrière)
    protected int deltaX,  deltaY; // orientation du bateau, par rapport à sa case arrière

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

        initPosition(lstBateau); //Initialise la position du bateau.
    }

    /**
     * Accesseur de l'attribut "xCaseArriere" de la classe Bateau.
     * @return Renvoie un entier représentant la valeur en abscisse de la case arrière du bateau.
     */
    public int getXCaseArriere() {

        return this.xCaseArriere;
    }

    /**
     * Accesseur de l'attribut "yCaseArriere" de la classe Bateau.
     * @return Renvoie un entier représentant la valeur en ordonnée de la case arrière du bateau.
     */
    public int getYCaseArriere() {

        return this.yCaseArriere;
    }

    /**
     * Accesseur de l'attribut "tailleBateau" de la classe Bateau.
     * @return Renvoie un entier représentant le nombre de cases occupées par le bateau.
     */
    public int getTailleBateau() {

        return this.tailleBateau;
    }

    /**
     * Accesseur de la ième case de l'attribut "touche" de la classe Bateau.
     * @param i Indice de la case de l'attribut "touche" à laquelle on veut accéder.
     * @return Renvoie true si la ième case du bateau a été touchée.
     */
    public boolean getIemeTouche(int i) {

        return this.touche[i];
    }

    /**
     * Accesseur de l'attribut "deltaX" de la classe Bateau.
     * @return Renvoie un entier représentant l'orientation selon l'axe X du bateau.
     */
    public int getDeltaX() {

        return this.deltaX;
    }

    /**
     * Accesseur de l'attribut "deltaY" de la classe Bateau.
     * @return Renvoie un entier représentant l'orientation selon l'axe Y du bateau.
     */
    public int getDeltaY() {

        return this.deltaY;
    }

    /**
     * Mutateur de l'attribut "xCaseArriere" de la classe Bateau.
     * @param x Nouvelle valeur en abscisse de la case arrière du bateau.
     */
    public void setXCaseArriere(int x) {

        this.xCaseArriere = x;
    }

    /**
     * Mutateur de l'attribut "yCaseArriere" de la classe Bateau.
     * @param y Nouvelle valeur en ordonnée de la case arrière du bateau.
     */
    public void setYCaseArriere(int y) {

        this.yCaseArriere = y;
    }

    /**
     * Mutateur de l'attribut "tailleBateau" de la classe Bateau.
     * @param t Nouvelle taille du bateau.
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
     * @param dx Nouvelle orientation selon l'axe X du bateau.
     */
    public void setDeltaX(int dx) {

        this.deltaX = dx;
    }

    /**
     * Mutateur de l'attribut "deltaY" de la classe Bateau.
     * @param dy Nouvelle orientation selon l'axe Y du bateau.
     */
    public void setDeltaY(int dy) {

        this.deltaY = dy;
    }

    /**
     * Procédure qui initialise aléatoirement la position de la case arrière et de la direction, puis calcule la validité de la position obtenue (avec notamment un test de chevauchement).
     * @param lstBateau Liste de bateaux du joueur.
     */
    private void initPosition(ArrayList<Bateau> lstBateau) {

    	int[] coords = new int [2];
    	
        do {

            this.setXCaseArriere((int) (Math.random() * Environnement.MAX_X));
            this.setYCaseArriere((int) (Math.random() * Environnement.MAX_Y));
            
            coords[0] = this.getXCaseArriere();
            coords[1] = this.getYCaseArriere();
            
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

        } while (this.testChevauchement(coords, this.getDeltaX(), this.getDeltaY(), lstBateau));
    }

    /*
     * Fonction qui teste si des coordonnées sont bien dans le domaine de l'environnement de jeu.
     * @param coords Coordonnées (x,y) à tester.
     * @return Renvoie true si les coordonnées sont bien dans l'environnement.
     */
    private boolean coordonneesPossibles(int[] coords) {
        
        return ((coords[0] >= 0) && (coords[0] < Environnement.MAX_X) && (coords[1] >= 0) && (coords[1] < Environnement.MAX_Y));
    }

    /**
     * Fonction qui teste si des coordonnées sont disponibles (i.e. teste si il n y a pas chevauchement avec un bateau existant).
     * @param caseArr Coordonnées de la case arriere du bateau à tester.
     * @param deltaX Orientation selon l'axe X du bateau à tester.
     * @param deltaY Orientation selon l'axe Y du bateau à tester.
     * @param lstBateau Liste des bateaux avec lesquels les coordonnées sont comparées.
     * @return Renvoie true si les coordonnées sont disponibles.
     */
    
    protected boolean testChevauchement(int[] caseArr, int deltaX, int deltaY, ArrayList<Bateau> lstBateau) {
    	
    	boolean chevauche = false;
    	int i = 0, j = 0, k = 0;
    	int tailleBat = this.getTailleBateau();
    	int[] nouvCoords = new int [2];
    	int[] listCoords = new int [2];
    	int tailleList = lstBateau.size();
    	
    	while((i < tailleBat ) && !chevauche) {

            j = 0;
    		nouvCoords[0] = caseArr[0] + i * deltaX;
            nouvCoords[1] = caseArr[1] + i * deltaY;
            
    		if(!this.coordonneesPossibles(nouvCoords)) {
    			
    			chevauche = true;
    			continue;
    		}
    		j = 0;
    		
    		while((j < tailleList) && !chevauche && tailleList > 0) {

    			//if(!(this.equals(lstBateau.get(j)))){ // ne fonctionne PAS, hashcode() different...
    			if(this.getClass() != lstBateau.get(j).getClass()){ //Solution Temporaire ; teste juste les classes. Trouver une fonction de test global. Idée : multiplier les tests.
    				k = 0;
    				
    				while((k < lstBateau.get(j).getTailleBateau()) && !chevauche) {
    				
                        listCoords[0] = (lstBateau.get(j).calculeCoordCaseBat(k))[0];
                        listCoords[1] = (lstBateau.get(j).calculeCoordCaseBat(k))[1];

    				
                        if((nouvCoords[0] == listCoords[0]) && (nouvCoords[1] == listCoords[1])) {


                            chevauche = true;
                            break;
    					}
    					k++;
    				}
    			}
    			j++;
    		}
    		i++;
    	}

        return chevauche;
    }

    /**
     * Marque l'environnement env pour chaque case associée au bateau: un 'X' pour une case normal, un 'M' pour le moteur, un 'B' pour le brouilleur, un 'R' pour le radar et d'un 'I' si la case est touchée.
     * @param env L'environnement où se trouve le bateau à afficher.
     */
    public void marquerEnvironnementExact(Environnement env) {
        
        int tailleBat = this.getTailleBateau();
        int[] coords = new int[2];
        
        for (int i = 0 ; i < tailleBat; i++) {

            coords = this.calculeCoordCaseBat(i);
            
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
     * Fonction qui calcule les coordonnées d'une case du bateau.
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
     * Fonction qui détermine si le bateau est touché par un tir.
     * @param coup Un tableau de deux entiers représentant les coordonnées de l'endroit où à été tiré le coup.
     * @return Renvoie true si le bateau est touché par le coup.
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
     * Fonction qui détermine si un bateau est coulé après de multiples coups au but.
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
		this.setYCaseArriere(this.getYCaseArriere() + this.getDeltaY());
	}
	
	/**
     * Procédure qui fait reculer le bateau d'une case selon son axe directeur.
     */
	public void recule() {
		
		this.setXCaseArriere(this.getXCaseArriere() - this.getDeltaX());
		this.setYCaseArriere(this.getYCaseArriere() - this.getDeltaY());
	}
    
    /**
     * Procédure qui fait pivoter le bateau sur lui-même de 45° dans le sens direct (vers babord).
     */
    public void pivote45direct() {
        
        if (this.getDeltaX() == 1 && this.getDeltaY() == 0) {
            this.setDeltaX(0);
            this.setDeltaY(1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == 1) {
            this.setDeltaX(-1);
            this.setDeltaY(0);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 0) {
            this.setDeltaX(0);
            this.setDeltaY(-1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == -1) {
            this.setDeltaX(1);
            this.setDeltaY(0);
        }
    }
    
    /**
     * Procédure qui fait pivoter le bateau sur lui-même de 45° dans le sens indirect (vers tribord).
     */
    public void pivote45indirect() {
        
        if (this.getDeltaX() == 1 && this.getDeltaY() == 0) {
            this.setDeltaX(0);
            this.setDeltaY(-1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == -1) {
            this.setDeltaX(-1);
            this.setDeltaY(0);
        }
        else if(this.getDeltaX() == -1 && this.getDeltaY() == 0) {
            this.setDeltaX(0);
            this.setDeltaY(1);
        }
        else if(this.getDeltaX() == 0 && this.getDeltaY() == 1) {
            this.setDeltaX(1);
            this.setDeltaY(0);
        }
    }
}

