/**
 * @author Benjamin Guillon, Mamy Raminosoa
 * @since 5/05/2009
 * @version 12/05/2009
 */
public class Partie {

    protected Joueur j1,  j2;

    public Partie() {
        j1 = new JoueurHumain();
        j2 = new JoueurIA();


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
            j1.marquerEnvironnementExact(envJH.efface());
            j2.marquerEnvironnementExact(envJIA.efface());

            // Afficher les environnements
            System.out.println("Env. Joueur humain :");
            System.out.print(envJH);
            System.out.println("Env. Joueur IA :");
            System.out.print(envJIA);

            // Action de j1
            coup = j1.getCoup();
            System.out.println("Application du coup " + (coup[0] + 1) + "," + (coup[1] + 1));
            j2.appliquerCoup(coup);

            if (j2.aPerdu()) {
                System.out.println(j1 + " a gagné la partie !!");
                partieTerminee = true;
            }

            if (!partieTerminee) {

                j1.marquerEnvironnementExact(envJH.efface());
                j2.marquerEnvironnementExact(envJIA.efface());
                

                System.out.println("Env. Joueur humain :");
                System.out.print(envJH);
                System.out.println("Env. Joueur IA :");
                System.out.print(envJIA);

                
                // Acion de j2
                coup = j2.getCoup();
                System.out.println("Application du coup " + (coup[0] + 1) + "," + (coup[1] + 1));
                j1.appliquerCoup(coup);

                if (j1.aPerdu()) {
                    System.out.println(j2 + " a gagné la partie !!");
                    partieTerminee = true;
                }

            }

        }

        if (j1.aPerdu()) {
            System.out.println(j2 + " a gagné la partie !!");

        } else {
            System.out.println(j1 + " a gagné la partie !!");
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
