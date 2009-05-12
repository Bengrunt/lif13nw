/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
 */
/*
 * 
 * Les objets environnement sont utilisés pour les représentations graphique des environnements 
*/
public class Environnement {

    public static final int MAX_X = 15; // x in [1, 15]
    public static final int MAX_Y = 5; // y in [1, 5]
    char[][] env;
    
    public Environnement() {
        
        env = new char[MAX_Y][MAX_X];
        
        
        
        
    }
    
    
    /*
     * Remise a zéro de l'environnement, pour pouvoir par la suite imprimer les positions mises à jour
     * */
    public Environnement efface() {
        for (int i = 0; i < MAX_Y; i++) {
            for (int j = 0; j < MAX_X; j++) {
                env[i][j] = 'O';
                
            }
        }
        return this;
    }
    
    /*
     * Définition d'une case de l'environnement
     * */
    public void set(int x, int y, char val) {
        env[y][x] = val;
    }
 
    /*
     * Transformation de l'environnement en chaîne, afin de pouvoir l'afficher en mose console
     * */
    @Override public String toString() {
        String retour = new String();
        
        for (int i = 0; i < env.length; i++) {
            for (int j = 0; j < env[0].length; j++) {
               retour += env[i][j];
                
            }
            retour +="\n";
        }
        
        return retour;
    }
    
}
