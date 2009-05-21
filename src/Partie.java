
/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 21/05/2009
 */
public class Partie {

    protected Joueur j1,  j2;

    public void setJ1(Joueur J1){
    	
    	this.j1 = J1;
    }
    
    public void setJ2(Joueur J2){
    	
    	this.j2 = J2;
    }
    
    public Joueur getJ1(){
    	
    	return this.j1;
    }
    
    public Joueur getJ2(){
    	
    	return this.j2;
    }
    
    public Partie() {
    	
        Joueur J1 = new JoueurHumain();
        Joueur J2 = new JoueurIA();
        
        this.setJ1(J1);
        this.setJ2(J2);
    }

    // procédure de vie de la partie
    public void run() {
        System.out.println("Commencement pde la partie ...");
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
            coup = this.getJ1().getCoup();
            System.out.println("Application du coup " + (coup[0] + 1) + "," + (coup[1] + 1));
            this.getJ2().appliquerCoup(coup);

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

    // point d'entrée du programme
    public static void main(String[] args) {
        // création d'une partie
        Partie p = new Partie();
        // execution de la partie
        p.run();

    }
}
