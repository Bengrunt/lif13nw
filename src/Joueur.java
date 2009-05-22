
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 22/05/2009
 */
public abstract class Joueur {

    //////////////////////////////////////// ATTRIBUTS /////////////////////////////////////////////////////

    protected ArrayList<Bateau> lstBateau; // Liste des bateaux du joueur.
    protected BateauRadarTireur bT; // Bateau radar-tireur
    protected BateauBrouilleur bB; // Bateau brouilleur

    //////////////////////////////////////// CONSTRUCTEURS ///////////////////////////////////////////////////

    /**
     * Constructeur de Joueur.
     */
    public Joueur() {

        lstBateau = new ArrayList(); //Liste des bateaux du joueur.
        // création des deux bateaux du joueur
        // paramètres : taille : founit la taille des bateaux
        //              lstBateau : liste des bateaux présents, afin de tester le chevauchement lors du placement initial    
        bT = new BateauRadarTireur(3, lstBateau);
        lstBateau.add(bT);
        bB = new BateauBrouilleur(2, lstBateau);
        lstBateau.add(bB);
    }


    /////////////////////////////////////////// ACCESSEURS //////////////////////////////////////////////////////

    /**
     * Accesseur de l'attribut bT de la classe "joueur".
     * @return Renvoie un Bateau.
     */
    public Bateau getBateauTireur() {
        return this.bT;
    }

    /**
     * Accesseur de l'attribut bB de la classe "joueur".
     * @return Renvoie le bateau brouilleur du joueur.
     */
    public Bateau getBateauBrouilleur() {
        return this.bB;
    }

    /**
     * Accesseur de l'attribut lstBateau de la classe "joueur".
     * @return Renvoie un ArrayList<Bateau>.
     */
    public ArrayList<Bateau> getLstBateau() {
        return this.lstBateau;
    }

    ////////////////////////////////////////// AUTRES METHODES //////////////////////////////////////////////////
    /*
     * Fonction qui détermine si un joueur a perdu.
     * @return Renvoie true si le joueur a perdu.
     */
    public boolean aPerdu() {

        return bT.estCoule();
    }

    /*
     * Retourne l'environnement exact du joueur 
     * 
     */
    public void marquerEnvironnementExact(Environnement env) {

        bT.marquerEnvironnementExact(env);
        bB.marquerEnvironnementExact(env);
    }

    /*
     * Fonction abstraite, à définir pour JoueurHumain (dans ce cas, saisie clavier du coup) et JoueurIA (dans ce cas, stratégie IA sur la base des informations perçues par le joeueur IA)
     * */
    public int[] getCoup() { // retourne les coordonnées x et y du coup
        return null;
    }

    /**
     * Fonction abstraite, pour récupérer le déplacement.
     * @return Renvoie une chaine de caractère représentant le déplacement.
     */
    public String getDeplacement() {

        String dep = new String();

        return dep;
    }

    /**
     * Procédure qui regarde si le joueur est concerné par le coup et applique les dégats occasionnés si c'est le cas.
     * @param coup Tableau de deux entiers qui décrivent les coordonnées du coup.
     */
    public void appliquerCoup(int[] coup) {

        int i;
        int tailleBb = this.bB.getTailleBateau();
        int tailleBt = this.bT.getTailleBateau();
        
        //On vérifie si le bateau tireur est touché.
        if(this.bT.estTouche(coup)) {

            for(i = 0 ; i < tailleBt ; i++) {

                if(Arrays.equals(this.bT.calculeCoordCaseBat(i), coup)) {

                    this.bT.setIemeTouche(i, true);
                    break;
                }
            }

            System.out.println("Le bateau tireur du " + this.toString() + " a été touché sur sa " + i + "ième case");
        }

        //On vérifie si le bateau brouilleur est touché.
        if(this.bB.estTouche(coup)) {

            for(i = 0 ; i < tailleBb ; i++) {

                if(Arrays.equals(this.bB.calculeCoordCaseBat(i), coup)) {

                    this.bB.setIemeTouche(i, true);
                    break;
                }
            }

            System.out.println("Le bateau brouilleur du " + this.toString() + " a été touché sur sa " + i + "ième case");
        }
    }

    /**
     * Procédure qui teste si le déplacement est faisable et l'applique le cas échéant.
     * @param dep Chaine de caractère représentant le déplacement à effectuer.
     * @param bat Le Bateau qui se déplace.
     * @return Renvoie true si le déplacement a été effectué, false sinon.
     */
    public boolean appliquerDeplacement(String dep, Bateau bat) {

        boolean res = false;
        int taille = bat.getTailleBateau();
        int dx = bat.getDeltaX();
        int dy = bat.getDeltaY();
        ArrayList<Bateau> lst = this.getLstBateau();

        if(bat.getClass() == BateauBrouilleur.class) { //C'est un bateau brouilleur

            BateauBrouilleur batSimu = new BateauBrouilleur(taille, lst);


        }
        else { //C'est un bateau radar-tireur



        }




        return res;
    }
}



