import lectureEcritureFichier.LectureFichierTexte;
import modele.AvancerJoueur;
import modele.Itineraire;
import modele.Scenario;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Itineraire /*extends Itineraire*/ {
    /*
    /**
     * Principe :
     * Finalise un trajet. Il ne pourra plus être modifié par la suite.
     * Entrée :
     *
     * @param avancerJoueur [AVANCERJOUEUR] : Objet contenant le trajet que nous allons finaliser
     * @param parScore      [INT] : Score de l'itinéraire dans sa catégorie

    public Test_Itineraire(AvancerJoueur avancerJoueur, int parScore) {
        super(avancerJoueur, parScore);
    }

     */

    private Scenario creerScenarioTest10(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_10_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private Scenario creerScenarioTest3(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_3_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private Scenario creerScenarioTest6(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_6_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private Scenario creerScenarioTest8(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_8_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private ArrayList<AvancerJoueur> genereAJ10(){
        Scenario scenar = creerScenarioTest10();

        ArrayList<AvancerJoueur> list = new ArrayList<>();
        HashSet<AvancerJoueur> set = scenar.cheminsCritiques();

        for (AvancerJoueur unAvancer : set){
            list.add(unAvancer);
        }

        return list;
    }

    private ArrayList<AvancerJoueur> genereAJ6(){
        Scenario scenar = creerScenarioTest6();

        ArrayList<AvancerJoueur> list = new ArrayList<>();
        HashSet<AvancerJoueur> set = scenar.cheminsCritiques();

        for (AvancerJoueur unAvancer : set){
            list.add(unAvancer);
        }

        return list;
    }

    private ArrayList<AvancerJoueur> genereAJ3(){
        Scenario scenar = creerScenarioTest3();

        ArrayList<AvancerJoueur> list = new ArrayList<>();
        HashSet<AvancerJoueur> set = scenar.cheminsCritiques();

        for (AvancerJoueur unAvancer : set){
            list.add(unAvancer);
        }

        return list;
    }

    @Test
    @Order(2)
    void verifTemps(){
        System.out.println("[TEST] --- verifTemps");

        // ======================================
        ArrayList<AvancerJoueur> list = genereAJ10();
        AvancerJoueur AJ1 = list.get(0); AvancerJoueur AJ2 = list.get(1);

        assertEquals(193, Itineraire.verifTemps(AJ1));
        assertEquals(328, Itineraire.verifTemps(AJ2));


        // ======================================
        list = genereAJ6();
        AJ1 = list.get(0);  AJ2 = list.get(1); AvancerJoueur AJ3 = list.get(2); AvancerJoueur AJ4 = list.get(3); AvancerJoueur AJ5 = list.get(4);

        assertEquals(138, Itineraire.verifTemps(AJ1));
        assertEquals(107, Itineraire.verifTemps(AJ2));
        assertEquals(92, Itineraire.verifTemps(AJ3));
        assertEquals(143, Itineraire.verifTemps(AJ4));
        assertEquals(102, Itineraire.verifTemps(AJ5));

        // ======================================
        list = genereAJ3();
        AJ1 = list.get(0);  AJ2 = list.get(1); AJ3 = list.get(2); AJ4 = list.get(3); AJ5 = list.get(4);

        assertEquals(53, Itineraire.verifTemps(AJ1));
        assertEquals(58, Itineraire.verifTemps(AJ2));
        assertEquals(60, Itineraire.verifTemps(AJ3));
        assertEquals(52, Itineraire.verifTemps(AJ4));
        assertEquals(57, Itineraire.verifTemps(AJ5));

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");

    }

    @Test
    @Order(2)
    void verifDeplacements() {
        System.out.println("[TEST] --- verifDeplacements");

        // ======================================
        ArrayList<AvancerJoueur> list = genereAJ10();
        AvancerJoueur AJ1 = list.get(0); AvancerJoueur AJ2 = list.get(1);

        assertEquals(183, Itineraire.verifDeplacements(AJ1));
        assertEquals(317, Itineraire.verifDeplacements(AJ2));


        // ======================================
        list = genereAJ6();
        AJ1 = list.get(0);  AJ2 = list.get(1); AvancerJoueur AJ3 = list.get(2); AvancerJoueur AJ4 = list.get(3); AvancerJoueur AJ5 = list.get(4);

        assertEquals(111, Itineraire.verifDeplacements(AJ1));
        assertEquals(81, Itineraire.verifDeplacements(AJ2));
        assertEquals(73, Itineraire.verifDeplacements(AJ3));
        assertEquals(111, Itineraire.verifDeplacements(AJ4));
        assertEquals(81, Itineraire.verifDeplacements(AJ5));


        // ======================================
        list = genereAJ3();
        AJ1 = list.get(0);  AJ2 = list.get(1); AJ3 = list.get(2); AJ4 = list.get(3); AJ5 = list.get(4);

        assertEquals(26, Itineraire.verifDeplacements(AJ1));
        assertEquals(30, Itineraire.verifDeplacements(AJ2));
        assertEquals(32, Itineraire.verifDeplacements(AJ3));
        assertEquals(26, Itineraire.verifDeplacements(AJ4));
        assertEquals(30, Itineraire.verifDeplacements(AJ5));

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test
    @Order(2)
    void verifPossible() {
        System.out.println("[TEST] --- verifPossible");

        Scenario scenar = creerScenarioTest3();
        HashSet<AvancerJoueur> set = scenar.cheminsCritiques();
        int id = 0;

        ArrayList validateurTrue = new ArrayList(); ArrayList validateurFalse = new ArrayList();
        validateurTrue.add(1); validateurTrue.add(2); validateurTrue.add(4);
        validateurFalse.add(0 ); validateurFalse.add(3 );


        for (AvancerJoueur aj : set){
            boolean res = Itineraire.verifPossible(aj);

            if (validateurTrue.contains(id)){ assertTrue(Itineraire.verifPossible(aj)); }
            else if (validateurTrue.contains(id)){ assertFalse(Itineraire.verifPossible(aj)); }
            id += 1;
        }

        // =====
        scenar = creerScenarioTest6();
        set = scenar.cheminsCritiques();
        id = 0;

        validateurTrue = new ArrayList(); validateurFalse = new ArrayList();
        validateurTrue.add(1); validateurTrue.add(2); validateurTrue.add(4);
        validateurFalse.add(0 ); validateurFalse.add(3 );


        for (AvancerJoueur aj : set){
            assertFalse(Itineraire.verifPossible(aj));

            id += 1;
        }

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");

    }


}
