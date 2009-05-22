
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 22/05/2009
 */
public class JoueurHumain extends Joueur {

    //////////////////////////////////////////////////// AUTRES METHODES /////////////////////////////////////////////////////////

    /*
     * Saisie d'un coup au clavier pour le joueur humain.
     * @return Renvoie un tableau d'entiers.
     */
    @Override
    public int[] getCoup() {

        int[] retour = new int[2];
        String ligne = "";
        boolean saisieOK = false;

        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

        while (!saisieOK) {

            saisieOK = true;
            System.out.println("Veuillez entrer les coordonnées du coup (deux entiers séparés d'un espace [1, " + Environnement.MAX_X + "], [1, " + Environnement.MAX_Y + "]):");

            try {

                ligne = entree.readLine();
            } catch (IOException ex) {

                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }

            StringTokenizer st = new StringTokenizer(ligne);

            if (st.countTokens() == 2) { // deux nombres séparés par un espace

                try {

                    retour[0] = Integer.parseInt(st.nextToken()) - 1; // coordonnées manipulées à partir de 0
                    retour[1] = Integer.parseInt(st.nextToken()) - 1;
                } catch (NumberFormatException e) {

                    saisieOK = false; // la valeur saisie n'est probablement pas un nombre
                }
            } else {

                saisieOK = false;
            }

            if (!(retour[0] >= 0 && retour[0] < Environnement.MAX_X && retour[1] >= 0 && retour[1] < Environnement.MAX_Y)) { // test validité

                saisieOK = false;
            }
        }

        return retour;
    }

    /**
     * Fonction de saisie de déplacement clavier pour le joueur humain.
     * @return Renvoie un StringTokenizer.
     */
    @Override
    public StringTokenizer getDeplacement() {

        String str = new String();
        StringTokenizer dep = new StringTokenizer(str);

        return dep;
    }

    /**
     * Fonction qui donne le type de joueur.
     * @return Renvoie une chaine de caractères.
     */
    @Override
    public String toString() {

        return "Joueur Humain";
    }
}
