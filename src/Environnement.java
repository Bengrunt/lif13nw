
/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 31/05/2009
 * @see http://code.google.com/p/lif13nw/
 * Les objets environnement sont utilisés pour les représentations graphiques des environnements.
 */

public class Environnement {

    public static final int MAX_X = 10; // Dimension de l'axe des abscisse de l'environnement: x in [1, MAX_X].
    public static final int MAX_Y = 5; // Dimension de l'axe des ordonnées de l'environnement: y in [1, MAX_Y].
    private char[][] env; // Tableau de caractères représentant l'environnement.

    /**
     * Constructeur d'Environnement.
     */
    public Environnement() {

        env = new char[MAX_X][MAX_Y];
    }

    /**
     * Constructeur d'Environnement par copie d'un autre environnement existant.
     * @param env L'environnement que l'on recopie.
     */
    public Environnement(char[][] env) {

        this.env = env;
    }

    /**
     * Accesseur de l'attribut "env" de la classe Environnement.
     * @return Renvoie un tableau de caractères représentant l'environnement.
     */
    public char[][] getEnv() {

        return this.env;
    }

    /**
     * Accesseur de la case de coordonnées [x,y] de l'attribut "env" de la classe Environnement.
     * @param x Coordonnées en x.
     * @param y Coordonnées en y.
     * @return Renvoie un caractère représentant l'occupation de la case [x,y] de l'environnement.
     */
    public char getEnv(int x, int y) {

        return this.env[x][y];
    }

    /**
     * Mutateur de l'attribut env. Modifie une seule case "[x][y]" et lui donne la valeur "val".
     * @param x Coordonnées en x.
     * @param y Coordonnées en y.
     * @param val Nouvelle valeur de la case [x][y].
     */
    public void set(int x, int y, char val) {

        env[x][y] = val;
    }

    /*
     * Fonction de remise a zéro de l'environnement.
     * @return Renvoie un Environnement sans bateaux.
     */
    public Environnement efface() {

        int i;
        int j;

        for (i = 0 ; i < MAX_X ; i++) {

            for (j = 0 ; j < MAX_Y ; j++) {

                this.set(i, j, 'O');
            }
        }

        return this;
    }

    /**
     * Fonction qui donne une version brouillée de l'environnement en changeant plus ou moins aléatoirement, selon la valeur de coeff, les valeurs de chacune des cases de l'environnement.
     * @param coeff Coefficient de brouillage pouvant varier de 0.0 (aucun brouillage) à 1.0 (brouillage de chaque case).
     * @return Renvoie un version brouillée de l'environnement. Ne modifie pas l'environnement original.
     */
    public Environnement brouille(double coeff) {

        Environnement env_virtuel = new Environnement();
        int i;
        int j;

        for (i = 0 ; i < MAX_X ; i++) {

            for (j = 0 ; j < MAX_Y ; j++) {

                if(coeff > Math.random()) // Test qui détermine si on brouille la case [i][j].
                {
                   double rnd_symbole=Math.random(); // Détermine par quel symbole on remplace la valeur originale.

                   if (rnd_symbole >= 0.98) {
                       env_virtuel.set(i, j, 'R'); // 2% de cases 'Radar'
                   } else if (rnd_symbole >= 0.96) {
                       env_virtuel.set(i, j, 'B'); // 2% de cases 'Brouilleur'
                   } else if (rnd_symbole >= 0.92) {
                       env_virtuel.set(i, j, 'X'); // 4% de cases 'Coque'
                   } else if (rnd_symbole >= 0.88) {
                       env_virtuel.set(i, j, 'M'); // 4% de cases 'Moteur'
                   } else {
                       env_virtuel.set(i, j, 'O'); // 88% de cases 'Eau'
                   }
                } else {
                    env_virtuel.set(i, j, this.getEnv(i, j)); // Si on ne brouille pas la case on recopie simplement la valeur originale.
                }
            }
        }

        return env_virtuel;
    }

    /*
     * Fonction de transformation de l'environnement en chaîne de caractères afin de pouvoir l'afficher dans la console.
     * @return Renvoie une chaine de caractères représentant l'environnement (avec graduation).
     */
    @Override
    public String toString() {

        String retour = new String();
        int i,j;
        int lg_env_X = this.getEnv().length;
        int lg_env_Y = (this.getEnv())[0].length;

        for (i = lg_env_Y ; i > 0 ; i--) {

            retour += i + "|";

            for (j = 0 ; j < lg_env_X ; j++) {

                retour += this.getEnv(j, i - 1) + " ";
            }

            retour += "\n";
        }

        retour += "  ";

        for (i = 0 ; i < lg_env_X ; i++) {
            retour += "¯ ";
        }

        retour += "\n ";

        for (i = 1 ; i <= lg_env_X ; i++) {
            retour += " " + i;
        }

        retour += " \n";
        
        return retour;
    }
}
