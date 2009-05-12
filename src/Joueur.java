import java.util.ArrayList;

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
     

	
    
    /*
     * Interoger les bateaux du joueur (bT.testCoup(coup), etc.) afin que ceux-ci examine le coup et s'ils sont concernés enregistrent les dégas occasionnés
     * 
     * */
    public void appliquerCoup(int[] coup) { // appliquer le coup aux cooronnées x (coup[0]), y(coup[1]) de coup
        
        
    }
    
    
    
    
}
