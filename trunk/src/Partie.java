
/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 24/05/2009
 */
public class Partie {

    protected Joueur j1,j2;

    //////////////////////////////////////////////// CONSTRUCTEURS //////////////////////////////////////////////

    /**
     * Constructeur de Partie.
     */
    public Partie() {

        this.j1 = new JoueurHumain();
        this.j2 = new JoueurIA();
    }

    ///////////////////////////////////////////////// ACCESSEURS ///////////////////////////////////////////////////

    /**
     * Accesseur de l'attribut "j1" de la classe Partie.
     * @return Renvoie un Joueur.
     */
    public Joueur getJ1(){

    	return this.j1;
    }

    /**
    * Accesseur de l'attribut "j2" de la classe Partie.
     * @return Renvoie un Joueur.
     */
    public Joueur getJ2(){

    	return this.j2;
    }

    ///////////////////////////////////////////////// MUTATEURS ////////////////////////////////////////////////////

    /**
     * Mutateur de l'attribut "j1" de la classe Partie.
     * @param j Joueur que l'on veut copier dans j1.
     */
    public void setJ1(Joueur j){
    	
    	this.j1 = j;
    }
    
    /**
     * Mutateur de l'attribut "j2" de la classe Partie.
     * @param j Joueur que l'on veut copier dans j2.
     */
    public void setJ2(Joueur j){
    	
    	this.j2 = j;
    }

    ///////////////////////////////////////////////////////////// AUTRES METHODES /////////////////////////////////////////////////

    /**
     * Procédure de vie de la partie.
     */
    public void run() {

        System.out.println("Commencement de la partie ...");
        boolean partieTerminee = false;

        // Création des environnements : objets utilisés pour représenter l'environnement des joueurs
        Environnement envJH = new Environnement();
        Environnement envJH_v = new Environnement();
        Environnement envJIA = new Environnement();
        Environnement envJIA_v = new Environnement();
        int[] coup;

        while (!partieTerminee) {

            // effacer les environnements et les remplir avec les données exactes des joueurs
            this.getJ1().marquerEnvironnementExact(envJH.efface());
            this.getJ2().marquerEnvironnementExact(envJIA.efface());

            // Brouiller l'environnement réel de JH et créer l'environnement virtuel que verra JIA
            int i;
            double sante_radar = 0;
            double sante_brouilleur = 0;

            for(i = 0 ; i < this.getJ1().getBateauTireur().getTailleBateau() ; i++) {
                if(this.getJ1().getBateauTireur().getIemeTouche(i)) {
                    sante_radar += 1/this.getJ1().getBateauTireur().getTailleBateau();
                }
            }

            if(this.getJ2().getBateauBrouilleur().getIemeTouche(this.getJ2().getBateauBrouilleur().getTailleBateau() - 1)) {
                sante_brouilleur += 1;
            }

            envJH_v = envJH.brouille((1 - (sante_radar * (2/3))) * sante_brouilleur);

            // Brouiller l'environnement réel de JIA et créer l'environnement virtuel que verra JH
            i = 0;
            sante_radar = 0;
            sante_brouilleur = 0;

            for(i = 0 ; i < this.getJ2().getBateauTireur().getTailleBateau() ; i++) {
                if(this.getJ2().getBateauTireur().getIemeTouche(i)) {
                    sante_radar += 1/this.getJ2().getBateauTireur().getTailleBateau();
                }
            }

            if(this.getJ1().getBateauBrouilleur().getIemeTouche(this.getJ1().getBateauBrouilleur().getTailleBateau() - 1)) {
                sante_brouilleur += 1;
            }

            envJIA_v = envJIA.brouille((1 - (sante_radar * (2/3))) * sante_brouilleur);

            // Afficher les environnements
            System.out.println("Env. Joueur humain :");
            System.out.print(envJH);
            System.out.println("Env. Joueur IA :");
            System.out.print(envJIA_v);

            // Actions de j1
            i = 0;
            
            while(i < this.getJ1().getLstBateau().size()){
            
            	String listeAction;
            	Bateau bat = this.getJ1().getLstBateau().get(i);

                System.out.println("Vous controlez actuellement votre " + bat.toString() + ".");
            	listeAction = this.getJ1().getAction(bat);
            	this.getJ1().appliquerAction(listeAction, bat, this.getJ2());
            	i++;
            }

            //Test de victoire
            if (this.getJ2().aPerdu()) {
                System.out.println("Le " + this.getJ1() + " a gagné la partie !!");
                partieTerminee = true;
            }

            if (!partieTerminee) {

//                this.getJ1().marquerEnvironnementExact(envJH.efface());
//                this.getJ2().marquerEnvironnementExact(envJIA.efface());
//
//
//                System.out.println("Env. Joueur humain:");
//                System.out.print(envJH);
//                System.out.println("Env. Joueur IA:");
//                System.out.print(envJIA);


                // Actions de j2
                coup = this.getJ2().getCoup();
                this.getJ1().appliquerCoup(coup);

                if (this.getJ1().aPerdu()) {
                    System.out.println("Le " + this.getJ2() + " a gagné la partie !!");
                    partieTerminee = true;
                }
            }
        }

        if (this.getJ1().aPerdu()) {
            System.out.println("Le " + this.getJ2() + " a gagné la partie !!");

        } else {
            System.out.println("Le " + this.getJ1() + " a gagné la partie !!");
        }
    }

    /**
     * Fonction principale du programme.
     * @param args Arguments du programme.
     */
    public static void main(String[] args) {
        // création d'une partie
        Partie p = new Partie();
        // execution de la partie
        p.run();

    }
}
