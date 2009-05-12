
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
 */
public abstract class Joueur {

    // Bateaux du joueur
    protected BateauRadarTireur bT;
    protected BateauBrouilleur bB;

    public Joueur() {
        ArrayList<Bateau> lstBateau = new ArrayList();
        // création des deux bateaux du joueur
        // paramètres : taille : founit la taille des bateaux
        //              lstBateau : liste des bateaux présents, afin de tester le chevauchement lors du placement initial    
        bT = new BateauRadarTireur(3, lstBateau);
        lstBateau.add(bT);
        bB = new BateauBrouilleur(3, lstBateau);
    }

    /*
     * Lorsque le bateauTireur est coulé, la partie est perdue pour le joueur
     * */
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
    public abstract int[] getCoup();  // retourne les coordonnées x et y du coup

    /**
     * Procédure qui applique éxamine un coup et regarde si le joueur est concerné par le coup et applique les dégats occasionnés si c'est le cas.
     * @param coup Tableau de deux entiers qui décrivent les coordonnées du coup.
     */
    public void appliquerCoup(int[] coup) {
        int i;
        
        //On vérifie si le bateau tireur est touché.
        if( this.bT.estTouche(coup) ) {
            for( i = 1 ; i <= this.bT.getTailleBateau() ; i++ )  {
                if( Arrays.equals(this.bT.calculeCoordCaseBat(i), coup) ) {
                    this.bT.setIemeTouche(i, true);
                    break;
                }
            }

            System.out.println("Le bateau tireur du " + this.toString() + " a été touché sur sa " + i + "ième case");
        }

        //On vérifie si le bateau brouilleur est touché.
        if( this.bB.estTouche(coup) ) {
            for( i = 1 ; i <= this.bB.getTailleBateau() ; i++ ) {
                if(Arrays.equals(this.bB.calculeCoordCaseBat(i), coup) ) {
                    this.bB.setIemeTouche(i, true);
                    break;
                }
            }

            System.out.println("Le bateau brouilleur du " + this.toString() + " a été touché sur sa " + i + "ième case");
        }
    }
}



