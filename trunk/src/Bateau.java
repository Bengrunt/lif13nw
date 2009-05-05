
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Frédéric Armetta <farmetta@bat710.univ-lyon1.fr>
 */
public abstract class Bateau {
    
    protected int xCaseArriere, yCaseArriere; // position de l'arrière du bateau
    protected int tailleBateau; // taille du bateau
    protected boolean[] touche; // état de la coque (touche[0] correspond à la case arrière)
    int deltaX, deltaY; // orientation du bateau, par rapport à sa case arrière
    
    
    
    /*
     * Constructeur de Bateau : créé un bateau placé aléatoirement dans l'environnement
     * */
    
    public Bateau(int _tailleBateau, ArrayList<Bateau> lstBateau) {
        tailleBateau = _tailleBateau;
        touche = new boolean[tailleBateau];
        for (int i = 0; i < tailleBateau ; i ++) {
            touche[i] = false;
        }
        
        initPosition(lstBateau);
    }
    
    private void initPosition(ArrayList<Bateau> lstBateau) {
        
        /*
         * Choix aléatoire de la position de la case arrière et de la direction, puis calcul de validité de la position obtenue (à compléter ..)
         * 
         * */
        do {
            xCaseArriere = (int) (Math.random() * Environnement.MAX_X);
            yCaseArriere = (int) (Math.random() * Environnement.MAX_Y);

            deltaX = 0; deltaY = 0;

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
                    deltaY = tailleBateau;
                    deltaX = 0;
                    break;

                case 4:
                    deltaY = -tailleBateau;
                    deltaX = 0;
                    break;
            }

        } while (!coordonneesPossibles(xCaseArriere + deltaX, yCaseArriere + deltaY)); // TODO ajouter test de chevauchement, en utilisant lstBateau
        
    }
    
    /*
     * 
     * test : x et y sont bien dans l'environnement ?
    */
    private boolean coordonneesPossibles(int x, int y) {
        return ((x >= 0) && (x < Environnement.MAX_X) && (y >= 0) && (y < Environnement.MAX_Y));
        
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
                
                env.set(xCaseArriere  + i * Integer.signum(deltaX), yCaseArriere , 'X');
            
            }
            
            
        }
        
    }

   private int[] calculeCoordCaseBat( int numCase ) {
       int[] coords = new int[2];
       coords[0] = this.xCaseArriere + (numCase - 1) * this.deltaX;
       coords[1] = this.yCaseArriere + (numCase - 1) * this.deltaY;

       return coords;
   }

    
   public boolean estTouche( int[] coup ) {
       boolean res = false;
       int[] coords = new int[2];
       int i = 1;
       while( i <= this.tailleBateau ) {
           coords = this.calculeCoordCaseBat(i);
           if( ( coords[0] == coup[0] ) && ( coords[1] == coup[1] ) ) {
               res = true;
               break;
           }
           i++;
       }

       return res;
   }
    
    public boolean estCoule() {
        int i = 1;
        int casesTouchees = 0;
        while( i <= this.tailleBateau ) {
            if( this.touche[i] = true ) {
                casesTouchees++;
            }
            i++;
        }

        if( casesTouchees == this.tailleBateau ) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
}
