
package client;

import lectureEcritureFichier.LectureFichierTexte;
import modele.*;

import java.io.File;
import java.util.*;

public class clientMatthieu {
    public static void main(String[] args){
        File unFichierScenario = new File("scenarios" + File.separator + "scenario_10.txt");
        File unAutreFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_10modif_POUR_TEST.txt");
        Scenario scenar =  LectureFichierTexte.lecture(unFichierScenario);

        Scenario scenarAutre =  LectureFichierTexte.lecture(unAutreFichierScenario);
        // ChoixUtilisateur choixUser = new ChoixUtilisateur(0,0,2, -1, -1, -1, -1, -1, -1, -1, -1, 1, 2000);
        // ChoixUtilisateur choixUser = new ChoixUtilisateur(true, 1);
        // ChoixUtilisateur choixUser = new ChoixUtilisateur(2,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 5);

        /*
        ChoixUtilisateur choixUser = new ChoixUtilisateur(2,0,2, -1, -1, -1, -1, -1, -1, -1, -1, 1, 10);
        ArrayList<Itineraire> test = Algorithmes.rechercheItineraire(scenar, choixUser);
        System.out.println(test);
         */
        ChoixUtilisateur choixUser;
        choixUser = new ChoixUtilisateur(1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 10);
        //ArrayList<Itineraire> test = Algorithmes.trouveItineraireGloutonPire(scenar, choixUser, 350);
        //System.out.println(test);


        ArrayList<Itineraire> test = Algorithmes.rechercheItineraire(scenar, choixUser);
        System.out.println(test);


        /*
        choixUser = new ChoixUtilisateur(true, 2);

        test = Algorithmes.rechercheItineraire(scenar, choixUser);
        System.out.println(test);

         */

        /*
        choixUser = new ChoixUtilisateur(true, 2);

        ArrayList<Itineraire> test = Algorithmes.rechercheItineraire(scenar, choixUser);
        System.out.println(test);

        choixUser = new ChoixUtilisateur(-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 1);
        test = Algorithmes.rechercheItineraire(scenar, choixUser);
        System.out.println(test);

         */

        /*

        ChoixUtilisateur choixUser = new ChoixUtilisateur(0,0,2, -1, -1, -1, -1, -1, -1, -1, -1, 2, 20);
        ArrayList<Itineraire> test = Algorithmes.rechercheItineraire(scenar, choixUser);
        test.sort(Comparator.reverseOrder());
        System.out.println(test);

        /*
        AvancerJoueur nagui = new AvancerJoueur();
        nagui.ajouteQuete(scenar.get(3), 18);
        nagui.ajouteQuete(scenar.get(5), scenar.get(5).distanceDeplacement(scenar.get(3)));
        nagui.ajouteQuete(scenar.get(6), scenar.get(6).distanceDeplacement(scenar.get(5)));
        nagui.ajouteQuete(scenar.get(8), scenar.get(8).distanceDeplacement(scenar.get(6)));
        nagui.ajouteQuete(scenar.get(7), scenar.get(7).distanceDeplacement(scenar.get(8)));
        nagui.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(scenar.get(7)));
        nagui.ajouteQuete(scenar.get(9), scenar.get(9).distanceDeplacement(scenar.get(0)));


        System.out.println(nagui);
        nagui.chercheEtAjouteQueteEntre(scenar, choixUser, 6);
        nagui.chercheEtAjouteQueteEntre(scenar, choixUser, 7);
        nagui.chercheEtAjouteQueteEntre(scenar, choixUser, 8);
        System.out.println(nagui);

         */




        /*

        // ==================================================================== LES NIVEAUX
        ChoixUtilisateur choixUser;


        // Niveau 1 ------------------------------

        // Solution efficace qui soit "gloutonne"
        choixUser = new ChoixUtilisateur(true, 1);

        // Solution exhaustive utilisant aussi la tactique gloutonne
        choixUser = new ChoixUtilisateur(true, 2);




        // Niveau 2 ------------------------------

        // Solution efficace optimale en termes de durée
        choixUser = new ChoixUtilisateur(1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1);

        // Solution efficace optimale en termes de nombre de quêtes et non de durée
        choixUser = new ChoixUtilisateur(-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1);

        // Solution efficace optimale en termes de déplacement total
        choixUser = new ChoixUtilisateur(-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 1);

        // Solution exhaustive optimale en termes de durée
        choixUser = new ChoixUtilisateur(1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 1);

        // Solution  exhaustive optimale en termes de nombre de quêtes
        choixUser = new ChoixUtilisateur(-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 1);
        // Solution exhaustive optimale en termes de déplacement total
        choixUser = new ChoixUtilisateur(-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 1);

        // Étendre les cas précédents traités en proposant les N meilleures solutions :
        //      -> Modifier le dernier argument (parNbrSolutionsMax).

        // Etendre les cas précédents en proposant les pires solution plutôt que les meilleures :
        //      -> Sur les critères objectif, remplacé 1 par 2.


        ArrayList<Itineraire> test = Algorithmes.rechercheItineraire(scenar, choixUser);
        System.out.println(test);

*/



    }
}

