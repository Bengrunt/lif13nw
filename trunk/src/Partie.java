
/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 23/05/2009
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
        Environnement envJIA = new Environnement();
        int[] coup;

        while (!partieTerminee) {

            // effacer les environnements et les remplir avec les données exactes des joueurs
            this.getJ1().marquerEnvironnementExact(envJH.efface());
            this.getJ2().marquerEnvironnementExact(envJIA.efface());

            // Afficher les environnements
            System.out.println("Env. Joueur humain :");
            System.out.print(envJH);
            System.out.println("Env. Joueur IA :");
            System.out.print(envJIA);

            // Action de j1
            /*coup = this.getJ1().getCoup();
            System.out.println("Application du coup " + (coup[0] + 1) + "," + (coup[1] + 1));
            this.getJ2().appliquerCoup(coup);*/
            int i = 0;
            
            while(i <= this.getJ1().getLstBateau().size()){
            
            	String listeAction;
            	listeAction = this.getJ1().getAction(this.getJ1().getLstBateau().get(i));
            	this.getJ1().appliquerAction(listeAction, this.getJ1().getLstBateau().get(i));
            	i++;
            }
            

            if (this.getJ2().aPerdu()) {
                System.out.println(this.getJ1() + " a gagné la partie !!");
                partieTerminee = true;
            }

            if (!partieTerminee) {

                this.getJ1().marquerEnvironnementExact(envJH.efface());
                this.getJ2().marquerEnvironnementExact(envJIA.efface());


                System.out.println("Env. Joueur humain :");
                System.out.print(envJH);
                System.out.println("Env. Joueur IA :");
                System.out.print(envJIA);


                // Acion de j2
                coup = this.getJ2().getCoup();
                System.out.println("Application du coup " + (coup[0] + 1) + "," + (coup[1] + 1));
                this.getJ1().appliquerCoup(coup);

                if (this.getJ1().aPerdu()) {
                    System.out.println(this.getJ2() + " a gagné la partie !!");
                    partieTerminee = true;
                }

            }

        }

        if (this.getJ1().aPerdu()) {
            System.out.println(this.getJ2() + " a gagné la partie !!");

        } else {
            System.out.println(this.getJ1() + " a gagné la partie !!");
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
