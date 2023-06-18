
import lectureEcritureFichier.LectureFichierTexte;
import modele.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Algorithmes {
        private Scenario creerScenarioTest10(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_10_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private Scenario creerScenarioTest6(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_6_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private Scenario creerScenarioTest3(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_3_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }


    @Test @Order(3)
    void trouveItineraireGlouton() {
        System.out.println("[TEST] --- trouveItineraireGlouton");
        Scenario scenar = creerScenarioTest10();
        Quete departJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        ChoixUtilisateur choixUser = new ChoixUtilisateur(true, 1);
        ArrayList<Itineraire> setRes = Algorithmes.rechercheItineraire(scenar, choixUser, true);

        Itineraire res = null;
        for (Itineraire iti : setRes){
            res = iti;
        }

        AvancerJoueur pourTestAJ = new AvancerJoueur();
        pourTestAJ.ajouteQuete(scenar.get(1), scenar.get(1).distanceDeplacement(departJoueur));
        pourTestAJ.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(scenar.get(1)));
        pourTestAJ.ajouteQuete(scenar.get(14), scenar.get(14).distanceDeplacement(scenar.get(0)));
        pourTestAJ.ajouteQuete(scenar.get(16), scenar.get(16).distanceDeplacement(scenar.get(14)));
        pourTestAJ.ajouteQuete(scenar.get(15), scenar.get(15).distanceDeplacement(scenar.get(16)));
        pourTestAJ.ajouteQuete(scenar.get(18), scenar.get(18).distanceDeplacement(scenar.get(15)));

        Itineraire pourTest = new Itineraire(pourTestAJ, 0);
        assertEquals(res, pourTest);

        // ==============
        choixUser = new ChoixUtilisateur(true, 2);
        setRes = Algorithmes.rechercheItineraire(scenar, choixUser, true);

        res = null;
        for (Itineraire iti : setRes){
            res = iti;
        }

        pourTestAJ = new AvancerJoueur();
        pourTestAJ.ajouteQuete(scenar.get(1), scenar.get(1).distanceDeplacement(departJoueur));
        pourTestAJ.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(scenar.get(1)));
        pourTestAJ.ajouteQuete(scenar.get(14), scenar.get(14).distanceDeplacement(scenar.get(0)));
        pourTestAJ.ajouteQuete(scenar.get(16), scenar.get(16).distanceDeplacement(scenar.get(14)));
        pourTestAJ.ajouteQuete(scenar.get(15), scenar.get(15).distanceDeplacement(scenar.get(16)));
        pourTestAJ.ajouteQuete(scenar.get(12), scenar.get(12).distanceDeplacement(scenar.get(15)));
        pourTestAJ.ajouteQuete(scenar.get(2), scenar.get(2).distanceDeplacement(scenar.get(12)));
        pourTestAJ.ajouteQuete(scenar.get(13), scenar.get(13).distanceDeplacement(scenar.get(2)));
        pourTestAJ.ajouteQuete(scenar.get(9), scenar.get(9).distanceDeplacement(scenar.get(13)));
        pourTestAJ.ajouteQuete(scenar.get(17), scenar.get(17).distanceDeplacement(scenar.get(9)));
        pourTestAJ.ajouteQuete(scenar.get(3), scenar.get(3).distanceDeplacement(scenar.get(17)));
        pourTestAJ.ajouteQuete(scenar.get(7), scenar.get(7).distanceDeplacement(scenar.get(3)));
        pourTestAJ.ajouteQuete(scenar.get(8), scenar.get(8).distanceDeplacement(scenar.get(7)));
        pourTestAJ.ajouteQuete(scenar.get(4), scenar.get(4).distanceDeplacement(scenar.get(8)));
        pourTestAJ.ajouteQuete(scenar.get(5), scenar.get(5).distanceDeplacement(scenar.get(4)));
        pourTestAJ.ajouteQuete(scenar.get(11), scenar.get(11).distanceDeplacement(scenar.get(5)));
        pourTestAJ.ajouteQuete(scenar.get(6), scenar.get(6).distanceDeplacement(scenar.get(11)));
        pourTestAJ.ajouteQuete(scenar.get(10), scenar.get(10).distanceDeplacement(scenar.get(6)));
        pourTestAJ.ajouteQuete(scenar.get(18), scenar.get(18).distanceDeplacement(scenar.get(10)));

        pourTest = new Itineraire(pourTestAJ, 0);
        assertEquals(res, pourTest);


        // ==============
        scenar = creerScenarioTest6();
        choixUser = new ChoixUtilisateur(true, 1);
        setRes = Algorithmes.rechercheItineraire(scenar, choixUser, true);

        res = null;
        for (Itineraire iti : setRes){
            res = iti;
        }

                pourTestAJ = new AvancerJoueur();
        pourTestAJ.ajouteQuete(scenar.get(6), scenar.get(6).distanceDeplacement(departJoueur));
        pourTestAJ.ajouteQuete(scenar.get(3), scenar.get(3).distanceDeplacement(scenar.get(6)));
        pourTestAJ.ajouteQuete(scenar.get(9), scenar.get(9).distanceDeplacement(scenar.get(3)));
        pourTestAJ.ajouteQuete(scenar.get(4), scenar.get(4).distanceDeplacement(scenar.get(9)));
        pourTestAJ.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(scenar.get(4)));
        pourTestAJ.ajouteQuete(scenar.get(1), scenar.get(1).distanceDeplacement(scenar.get(0)));
        pourTestAJ.ajouteQuete(scenar.get(5), scenar.get(5).distanceDeplacement(scenar.get(1)));
        pourTestAJ.ajouteQuete(scenar.get(2), scenar.get(2).distanceDeplacement(scenar.get(5)));
        pourTestAJ.ajouteQuete(scenar.get(7), scenar.get(7).distanceDeplacement(scenar.get(2)));
        pourTestAJ.ajouteQuete(scenar.get(10), scenar.get(10).distanceDeplacement(scenar.get(7)));
        pourTestAJ.ajouteQuete(scenar.get(8), scenar.get(8).distanceDeplacement(scenar.get(10)));
        pourTestAJ.ajouteQuete(scenar.get(11), scenar.get(11).distanceDeplacement(scenar.get(8)));

        pourTest = new Itineraire(pourTestAJ, 0);
        assertEquals(res, pourTest);


        // ==============
        choixUser = new ChoixUtilisateur(true, 2);
        setRes = Algorithmes.rechercheItineraire(scenar, choixUser, true);

        res = null;
        for (Itineraire iti : setRes){
            res = iti;
        }

        pourTest = new Itineraire(pourTestAJ, 0);
        assertEquals(res, pourTest);

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test @Order(10)
    void trouveItineraireTemps() {
        System.out.println("[TEST] --- trouveItineraireTemps");
        Scenario scenar = creerScenarioTest3();

        ChoixUtilisateur choixUser = new ChoixUtilisateur(2,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 5);
        ArrayList<Itineraire> res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        ArrayList<String> test = new ArrayList<>();

        test.add("[ITINERAIRE - 74]: les quêtes : [1, 6, 3, 4, 7, 5, 2, 0] /// total : 8 /// Temps : 74 /// Déplacements : 38 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 74]: les quêtes : [1, 6, 3, 4, 2, 5, 7, 0] /// total : 8 /// Temps : 74 /// Déplacements : 38 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 74]: les quêtes : [1, 6, 3, 7, 5, 4, 2, 0] /// total : 8 /// Temps : 74 /// Déplacements : 38 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 74]: les quêtes : [1, 6, 3, 7, 4, 5, 2, 0] /// total : 8 /// Temps : 74 /// Déplacements : 38 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 74]: les quêtes : [1, 6, 3, 7, 4, 2, 5, 0] /// total : 8 /// Temps : 74 /// Déplacements : 38 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 72]: les quêtes : [1, 3, 6, 4, 7, 5, 2, 0] /// total : 8 /// Temps : 72 /// Déplacements : 36 /// Expériences : 950");


        for (Itineraire iti : res){
            int curseur = 0;
            curseur += 1;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(2,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 72]: les quêtes : [1, 6, 3, 7, 5, 2, 4, 0] /// total : 8 /// Temps : 72 /// Déplacements : 36 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 70]: les quêtes : [1, 3, 6, 7, 5, 2, 4, 0] /// total : 8 /// Temps : 70 /// Déplacements : 34 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 70]: les quêtes : [1, 3, 6, 2, 5, 7, 4, 0] /// total : 8 /// Temps : 70 /// Déplacements : 34 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 70]: les quêtes : [1, 6, 3, 2, 5, 7, 4, 0] /// total : 8 /// Temps : 70 /// Déplacements : 34 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 70]: les quêtes : [1, 6, 3, 7, 2, 5, 4, 0] /// total : 8 /// Temps : 70 /// Déplacements : 34 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 69]: les quêtes : [1, 6, 3, 4, 2, 5, 0] /// total : 7 /// Temps : 69 /// Déplacements : 34 /// Expériences : 800\n");



        int curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(1,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 53]: les quêtes : [1, 3, 7, 6, 4, 0] /// total : 6 /// Temps : 53 /// Déplacements : 26 /// Expériences : 650\n");
        test.add("[ITINERAIRE - 55]: les quêtes : [1, 3, 6, 7, 4, 0] /// total : 6 /// Temps : 55 /// Déplacements : 28 /// Expériences : 650\n");
        test.add("[ITINERAIRE - 56]: les quêtes : [1, 3, 2, 7, 6, 4, 0] /// total : 7 /// Temps : 56 /// Déplacements : 28 /// Expériences : 750\n");
        test.add("[ITINERAIRE - 57]: les quêtes : [1, 3, 6, 4, 7, 0] /// total : 6 /// Temps : 57 /// Déplacements : 30 /// Expériences : 650\n");
        test.add("[ITINERAIRE - 57]: les quêtes : [1, 6, 3, 7, 4, 0] /// total : 6 /// Temps : 57 /// Déplacements : 30 /// Expériences : 650\n");
        test.add("[ITINERAIRE - 58]: les quêtes : [1, 3, 2, 6, 7, 4, 0] /// total : 7 /// Temps : 58 /// Déplacements : 30 /// Expériences : 750\n");



        curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(1,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 64]: les quêtes : [1, 3, 2, 7, 5, 6, 4, 0] /// total : 8 /// Temps : 64 /// Déplacements : 28 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 64]: les quêtes : [1, 3, 2, 7, 6, 5, 4, 0] /// total : 8 /// Temps : 64 /// Déplacements : 28 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 66]: les quêtes : [1, 3, 2, 5, 7, 6, 4, 0] /// total : 8 /// Temps : 66 /// Déplacements : 30 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 66]: les quêtes : [1, 3, 2, 5, 6, 7, 4, 0] /// total : 8 /// Temps : 66 /// Déplacements : 30 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 66]: les quêtes : [1, 3, 2, 7, 6, 4, 5, 0] /// total : 8 /// Temps : 66 /// Déplacements : 30 /// Expériences : 950\n");
        test.add("[ITINERAIRE - 66]: les quêtes : [1, 3, 2, 6, 5, 7, 4, 0] /// total : 8 /// Temps : 66 /// Déplacements : 30 /// Expériences : 950\n");


        curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        scenar = creerScenarioTest6();

        choixUser = new ChoixUtilisateur(1,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 198]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 198 /// Déplacements : 123 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 198]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 198 /// Déplacements : 123 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 200]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 200 /// Déplacements : 125 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 200]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 200 /// Déplacements : 125 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 202]: les quêtes : [8, 2, 1, 11, 7, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 202 /// Déplacements : 127 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 204]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 6, 5, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");



        curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(1,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 175]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 0] /// total : 10 /// Temps : 175 /// Déplacements : 117 /// Expériences : 1550\n");
        test.add("[ITINERAIRE - 175]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 9, 5, 0] /// total : 10 /// Temps : 175 /// Déplacements : 115 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [10, 9, 8, 2, 11, 1, 7, 4, 3, 0] /// total : 10 /// Temps : 177 /// Déplacements : 119 /// Expériences : 1550\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 5, 9, 0] /// total : 10 /// Temps : 177 /// Déplacements : 117 /// Expériences : 1500\n");




        curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(2,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 339]: les quêtes : [10, 8, 4, 2, 9, 11, 6, 1, 5, 7, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 339]: les quêtes : [10, 8, 4, 2, 9, 11, 6, 7, 5, 1, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 339]: les quêtes : [10, 8, 4, 2, 9, 1, 6, 11, 5, 7, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 339]: les quêtes : [10, 8, 4, 2, 9, 1, 6, 7, 5, 11, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 339]: les quêtes : [10, 8, 4, 2, 9, 7, 6, 11, 5, 1, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 339]: les quêtes : [10, 8, 4, 2, 9, 7, 6, 1, 5, 11, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");


        curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(2,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 354]: les quêtes : [10, 8, 4, 3, 11, 9, 1, 6, 7, 5, 2, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 354]: les quêtes : [10, 8, 4, 3, 11, 9, 1, 6, 2, 5, 7, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 354]: les quêtes : [10, 8, 4, 3, 11, 9, 7, 6, 1, 5, 2, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 354]: les quêtes : [10, 8, 4, 3, 11, 9, 7, 6, 2, 5, 1, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 354]: les quêtes : [10, 8, 4, 3, 11, 9, 2, 6, 1, 5, 7, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 354]: les quêtes : [10, 8, 4, 3, 11, 9, 2, 6, 7, 5, 1, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");


        curseur = 0;
        for (Itineraire iti : res){
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // =====================================================================
        choixUser = new ChoixUtilisateur(1,0,0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 175]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 0] /// total : 10 /// Temps : 175 /// Déplacements : 117 /// Expériences : 1550\n");
        test.add("[ITINERAIRE - 175]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 9, 5, 0] /// total : 10 /// Temps : 175 /// Déplacements : 115 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [10, 9, 8, 2, 11, 1, 7, 4, 3, 0] /// total : 10 /// Temps : 177 /// Déplacements : 119 /// Expériences : 1550\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 177]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 5, 9, 0] /// total : 10 /// Temps : 177 /// Déplacements : 117 /// Expériences : 1500\n");


        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }


    @Test @Order(10)
    void trouveItineraireNbQuetes() {
        System.out.println("[TEST] --- trouveItineraireNbQuetes");
        Scenario scenar = creerScenarioTest6();
        ChoixUtilisateur choixUser = new ChoixUtilisateur(0,1,0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 6);
        ArrayList<Itineraire> res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        ArrayList<String> test = new ArrayList<>();

        test.add("[ITINERAIRE - 9]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 0] /// total : 9 /// Temps : 230 /// Déplacements : 177 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 9]: les quêtes : [10, 9, 1, 8, 4, 3, 7, 11, 0] /// total : 9 /// Temps : 230 /// Déplacements : 177 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 9]: les quêtes : [10, 9, 1, 8, 4, 7, 3, 11, 0] /// total : 9 /// Temps : 252 /// Déplacements : 199 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 9]: les quêtes : [10, 9, 8, 1, 4, 3, 11, 7, 0] /// total : 9 /// Temps : 202 /// Déplacements : 149 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 9]: les quêtes : [10, 9, 8, 1, 4, 3, 7, 11, 0] /// total : 9 /// Temps : 202 /// Déplacements : 149 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 9]: les quêtes : [10, 9, 8, 1, 4, 7, 3, 11, 0] /// total : 9 /// Temps : 224 /// Déplacements : 171 /// Expériences : 1500\n");

        int curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        choixUser = new ChoixUtilisateur(0,1,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // ============================================
        choixUser = new ChoixUtilisateur(0,1,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 6, 5, 2, 0] /// total : 12 /// Temps : 290 /// Déplacements : 215 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 6, 2, 5, 0] /// total : 12 /// Temps : 296 /// Déplacements : 221 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 2, 6, 5, 0] /// total : 12 /// Temps : 266 /// Déplacements : 191 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 7, 6, 5, 0] /// total : 12 /// Temps : 284 /// Déplacements : 209 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 6, 5, 7, 0] /// total : 12 /// Temps : 308 /// Déplacements : 233 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 6, 7, 5, 0] /// total : 12 /// Temps : 314 /// Déplacements : 239 /// Expériences : 1900\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // ============================================
        choixUser = new ChoixUtilisateur(0,2,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 11]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 6, 5, 0] /// total : 11 /// Temps : 241 /// Déplacements : 177 /// Expériences : 1700\n");
        test.add("[ITINERAIRE - 11]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 6, 7, 0] /// total : 11 /// Temps : 292 /// Déplacements : 225 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 11]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 6, 2, 5, 0] /// total : 11 /// Temps : 267 /// Déplacements : 203 /// Expériences : 1700\n");
        test.add("[ITINERAIRE - 11]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 6, 2, 7, 0] /// total : 11 /// Temps : 288 /// Déplacements : 221 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 11]: les quêtes : [10, 9, 1, 8, 4, 3, 6, 5, 2, 11, 0] /// total : 11 /// Temps : 247 /// Déplacements : 183 /// Expériences : 1700\n");
        test.add("[ITINERAIRE - 11]: les quêtes : [10, 9, 1, 8, 4, 3, 6, 5, 2, 7, 0] /// total : 11 /// Temps : 266 /// Déplacements : 197 /// Expériences : 1600\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        // ============================================
        choixUser = new ChoixUtilisateur(0,2,0, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 6, 5, 2, 0] /// total : 12 /// Temps : 290 /// Déplacements : 215 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 6, 2, 5, 0] /// total : 12 /// Temps : 296 /// Déplacements : 221 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 7, 2, 6, 5, 0] /// total : 12 /// Temps : 266 /// Déplacements : 191 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 7, 6, 5, 0] /// total : 12 /// Temps : 284 /// Déplacements : 209 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 6, 5, 7, 0] /// total : 12 /// Temps : 308 /// Déplacements : 233 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 12]: les quêtes : [10, 9, 1, 8, 4, 3, 11, 2, 6, 7, 5, 0] /// total : 12 /// Temps : 314 /// Déplacements : 239 /// Expériences : 1900\n");



        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }


        // ============================================
        scenar = creerScenarioTest10();
        choixUser = new ChoixUtilisateur(0,1,0, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res =  Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 5]: les quêtes : [2, 18, 8, 3, 0] /// total : 5 /// Temps : 200 /// Déplacements : 183 /// Expériences : 550\n");
        test.add("[ITINERAIRE - 5]: les quêtes : [2, 18, 12, 3, 0] /// total : 5 /// Temps : 226 /// Déplacements : 211 /// Expériences : 500\n");
        test.add("[ITINERAIRE - 5]: les quêtes : [2, 18, 3, 12, 0] /// total : 5 /// Temps : 244 /// Déplacements : 229 /// Expériences : 500\n");
        test.add("[ITINERAIRE - 5]: les quêtes : [2, 18, 3, 8, 0] /// total : 5 /// Temps : 306 /// Déplacements : 289 /// Expériences : 550\n");
        test.add("[ITINERAIRE - 5]: les quêtes : [2, 18, 3, 16, 0] /// total : 5 /// Temps : 202 /// Déplacements : 191 /// Expériences : 350\n");
        test.add("[ITINERAIRE - 5]: les quêtes : [2, 18, 3, 13, 0] /// total : 5 /// Temps : 338 /// Déplacements : 323 /// Expériences : 550\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur++;
        }

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test @Order(10)
    void trouveItineraireDeplacement() {
        System.out.println("[TEST] --- trouveItineraireDeplacement");
        Scenario scenar = creerScenarioTest6();

        ChoixUtilisateur choixUser = new ChoixUtilisateur(0, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        ArrayList<Itineraire> res = Algorithmes.rechercheItineraire(scenar, choixUser, true);
        ArrayList<String> test = new ArrayList<>();

        test.add("[ITINERAIRE - 113]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 113]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 115]: les quêtes : [8, 2, 11, 1, 10, 4, 3, 6, 9, 5, 0] /// total : 11 /// Temps : 179 /// Déplacements : 115 /// Expériences : 1700\n");
        test.add("[ITINERAIRE - 115]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 9, 5, 0] /// total : 10 /// Temps : 175 /// Déplacements : 115 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 117]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 0] /// total : 10 /// Temps : 175 /// Déplacements : 117 /// Expériences : 1550\n");
        test.add("[ITINERAIRE - 117]: les quêtes : [8, 2, 11, 1, 10, 4, 3, 6, 5, 9, 0] /// total : 11 /// Temps : 181 /// Déplacements : 117 /// Expériences : 1700\n");

        int curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        choixUser = new ChoixUtilisateur(0, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 6);
        res = Algorithmes.rechercheItineraire(scenar, choixUser, true);

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        // ============================================
        choixUser = new ChoixUtilisateur(0, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res = Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 123]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 198 /// Déplacements : 123 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 123]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 198 /// Déplacements : 123 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 125]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 200 /// Déplacements : 125 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 125]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 200 /// Déplacements : 125 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 127]: les quêtes : [8, 2, 1, 11, 7, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 202 /// Déplacements : 127 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 129]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 6, 5, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        // ============================================
        choixUser = new ChoixUtilisateur(0, 0, 2, -1, -1, -1, -1, -1, -1, -1, -1, 1, 6);
        res = Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 273]: les quêtes : [10, 8, 4, 2, 9, 11, 6, 1, 5, 7, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 273]: les quêtes : [10, 8, 4, 2, 9, 11, 6, 7, 5, 1, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 273]: les quêtes : [10, 8, 4, 2, 9, 1, 6, 11, 5, 7, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 273]: les quêtes : [10, 8, 4, 2, 9, 1, 6, 7, 5, 11, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 273]: les quêtes : [10, 8, 4, 2, 9, 7, 6, 11, 5, 1, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");
        test.add("[ITINERAIRE - 273]: les quêtes : [10, 8, 4, 2, 9, 7, 6, 1, 5, 11, 0] /// total : 11 /// Temps : 339 /// Déplacements : 273 /// Expériences : 1650\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }


        // ============================================
        choixUser = new ChoixUtilisateur(0, 0, 2, -1, -1, -1, -1, -1, -1, -1, -1, 0, 6);
        res = Algorithmes.rechercheItineraire(scenar, choixUser, true);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 279]: les quêtes : [10, 8, 4, 3, 11, 9, 1, 6, 7, 5, 2, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 279]: les quêtes : [10, 8, 4, 3, 11, 9, 1, 6, 2, 5, 7, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 279]: les quêtes : [10, 8, 4, 3, 11, 9, 7, 6, 1, 5, 2, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 279]: les quêtes : [10, 8, 4, 3, 11, 9, 7, 6, 2, 5, 1, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 279]: les quêtes : [10, 8, 4, 3, 11, 9, 2, 6, 1, 5, 7, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 279]: les quêtes : [10, 8, 4, 3, 11, 9, 2, 6, 7, 5, 1, 0] /// total : 12 /// Temps : 354 /// Déplacements : 279 /// Expériences : 1900\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        choixUser = new ChoixUtilisateur(0, 0, 2, -1, -1, -1, -1, -1, -1, -1, -1, 2, 6);
        res = Algorithmes.rechercheItineraire(scenar, choixUser, true);

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");

    }


    @Test @Order(10)
    void rechercheItineraire() {

        System.out.println("[TEST] --- rechercheItineraire");
        Scenario scenar = creerScenarioTest10();

        ChoixUtilisateur choixUser = new ChoixUtilisateur(1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 50);
        ArrayList<Itineraire> res = Algorithmes.rechercheItineraire(scenar, choixUser);
        ArrayList<String> test = new ArrayList<>();


        test.add("[ITINERAIRE - 378]: les quêtes : [2, 18, 8, 3, 0] /// total : 5 /// Temps : 200 /// Déplacements : 183 /// Expériences : 550\n");
        test.add("[ITINERAIRE - 388]: les quêtes : [2, 18, 3, 16, 0] /// total : 5 /// Temps : 202 /// Déplacements : 191 /// Expériences : 350\n");

        int curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        // ===============================================

        choixUser = new ChoixUtilisateur(1, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 50);
        res = Algorithmes.rechercheItineraire(scenar, choixUser);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 383]: les quêtes : [2, 18, 8, 3, 0] /// total : 5 /// Temps : 200 /// Déplacements : 183 /// Expériences : 550\n");
        test.add("[ITINERAIRE - 393]: les quêtes : [2, 18, 3, 16, 0] /// total : 5 /// Temps : 202 /// Déplacements : 191 /// Expériences : 350\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        // ===============================================
        scenar = creerScenarioTest6();

        choixUser = new ChoixUtilisateur(1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 10);
        res = Algorithmes.rechercheItineraire(scenar, choixUser);
        test = new ArrayList<>();

        assertEquals(test, res);


        // ===============================================
        scenar = creerScenarioTest6();

        choixUser = new ChoixUtilisateur(1, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 10);
        res = Algorithmes.rechercheItineraire(scenar, choixUser);
        test = new ArrayList<>();


        test.add("[ITINERAIRE - 290]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 9, 5, 0] /// total : 10 /// Temps : 175 /// Déplacements : 115 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 290]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 290]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 0] /// total : 11 /// Temps : 177 /// Déplacements : 113 /// Expériences : 1600\n");
        test.add("[ITINERAIRE - 292]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 0] /// total : 10 /// Temps : 175 /// Déplacements : 117 /// Expériences : 1550\n");
        test.add("[ITINERAIRE - 294]: les quêtes : [8, 2, 11, 10, 4, 3, 6, 5, 9, 0] /// total : 10 /// Temps : 177 /// Déplacements : 117 /// Expériences : 1500\n");
        test.add("[ITINERAIRE - 294]: les quêtes : [8, 2, 11, 1, 10, 4, 3, 6, 9, 5, 0] /// total : 11 /// Temps : 179 /// Déplacements : 115 /// Expériences : 1700\n");
        test.add("[ITINERAIRE - 296]: les quêtes : [10, 9, 8, 2, 11, 1, 7, 4, 3, 0] /// total : 10 /// Temps : 177 /// Déplacements : 119 /// Expériences : 1550\n");


        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        // ===============================================
        scenar = creerScenarioTest6();

        choixUser = new ChoixUtilisateur(1, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 10);
        res = Algorithmes.rechercheItineraire(scenar, choixUser);
        test = new ArrayList<>();

        test.add("[ITINERAIRE - 321]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 198 /// Déplacements : 123 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 321]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 198 /// Déplacements : 123 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 325]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 200 /// Déplacements : 125 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 325]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 200 /// Déplacements : 125 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 329]: les quêtes : [8, 2, 1, 11, 7, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 202 /// Déplacements : 127 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 333]: les quêtes : [10, 9, 8, 2, 11, 7, 1, 4, 3, 6, 5, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 333]: les quêtes : [8, 2, 11, 1, 7, 10, 4, 3, 9, 6, 5, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 333]: les quêtes : [8, 2, 11, 7, 1, 10, 4, 3, 9, 6, 5, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 333]: les quêtes : [8, 2, 1, 11, 7, 10, 4, 3, 6, 5, 9, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");
        test.add("[ITINERAIRE - 333]: les quêtes : [8, 2, 1, 7, 11, 10, 4, 3, 6, 9, 5, 0] /// total : 12 /// Temps : 204 /// Déplacements : 129 /// Expériences : 1900\n");

        curseur = 0;
        for (Itineraire iti : res) {
            assertEquals(test.get(curseur).toString(), iti.toString());
            curseur += 1;
        }

        // ===============================================
        scenar = creerScenarioTest6();

        choixUser = new ChoixUtilisateur(1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 10);
        res = Algorithmes.rechercheItineraire(scenar, choixUser);
        test = new ArrayList<>();

        assertEquals(test, res);




        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }


}
