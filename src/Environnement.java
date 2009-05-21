
/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 21/05/2009
 * Les objets environnement sont utilisés pour les représentations graphiques des environnements.
 */

public class Environnement {

    public static final int MAX_X = 15; // x in [1, 15]
    public static final int MAX_Y = 5; // y in [1, 5]
    private char[][] env;

    ////////////////////////////////////////// CONSTRUCTEURS ///////////////////////////////////////////

    /**
     * Constructeur d'Environnement.
     */
    public Environnement() {

        env = new char[MAX_X][MAX_Y];
    }

    /**
     * Constructeur d'Environnement par copie d'un environnement.
     * @param env L'environnement que l'on recopie.
     */
    public Environnement(char[][] env) {

        this.env = env;
    }

    ///////////////////////////////////////// ACCESSEURS ///////////////////////////////////////////////

    /**
     * Accesseur de l'attribut "env" de la classe Environnement.
     * @return Renvoie un tableau de caractères.
     */
    public char[][] getEnv() {

        return this.env;
    }

    /**
     * Accesseur de la case de coordonnées [x][y] de l'attribut "env" de la classe Environnement.
     * @param x Coordonnées en x.
     * @param y Coordonnées en y.
     * @return Renvoie un caractère.
     */
    public char getEnv(int x, int y) {

        return this.env[x][y];
    }

    ///////////////////////////////////////// MUTATEURS ////////////////////////////////////////////////

    /**
     * Mutateur de l'attribut env. Modifie une seule case "[x][y]" et lui donne la valeur "val".
     * @param x Coordonnées en x.
     * @param y Coordonnées en y.
     * @param val Nouvelle valeur de la case [x][y].
     */
    public void set(int x, int y, char val) {

        env[x][y] = val;
    }

    //////////////////////////////////////// AUTRES METHODES ///////////////////////////////////////////

    /*
     * Fonction de remise a zéro de l'environnement.
     * @return Renvoie un Environnement.
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

    /*
     * Fonction de transformation de l'environnement en chaîne, afin de pouvoir l'afficher en mose console
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        String retour = new String();
        int i,j;
        int lg_env_X = this.getEnv().length;
        int lg_env_Y = (this.getEnv())[0].length;

        for (i = 0 ; i < lg_env_Y ; i++) {

            for (j = 0 ; j < lg_env_X ; j++) {

                retour += this.getEnv(j, i);
            }

            retour += "\n";
        }

        return retour;
    }
}
