
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 23/05/2009
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

                    retour[0] = convAlpha((((st.nextToken()).trim()).toCharArray())[0]); // coordonnées manipulées à partir de 0
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
    public String getDeplacement() {

        String dep = new String();
        boolean saisieOK = false;

        BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

        while (!saisieOK) {

            saisieOK = true;
            System.out.println("Veuillez entrer votre série de déplacements séparés par des espaces.(a : avancer ; g : piv à gauche ; d : piv à droite \n");

            try {

                dep = entree.readLine();
            } catch (IOException ex) {

                Logger.getLogger(Joueur.class.getName()).log(Level.SEVERE, null, ex);
            }



            if (dep.length() == 1) { // Un seul caractère

                saisieOK = false;
            }

            if (!(dep.equalsIgnoreCase("a") || dep.equalsIgnoreCase("g") || dep.equalsIgnoreCase("d"))) { // test validité : { A ; a ; D ; d ; G ; g }

                saisieOK = false;
            }
        }

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

    private int convAlpha(char ch) {
        int res = 0;

        switch(ch) {

            case 'a' : res = 1;
            break;

            case 'b' : res = 2;
            break;

            case 'c' : res = 3;
            break;

            case 'd' : res = 4;
            break;

            case 'e' : res = 5;
            break;

            case 'f' : res = 6;
            break;

            case 'g' : res = 7;
            break;

            case 'h' : res = 8;
            break;

            case 'i' : res = 9;
            break;

            case 'j' : res = 10;
            break;

            case 'k' : res = 11;
            break;

            case 'l' : res = 12;
            break;

            case 'm' : res = 13;
            break;

            case 'n' : res = 14;
            break;

            case 'o' : res = 15;
            break;
        }

        return res;
    }
}
