import lectureEcritureFichier.LectureFichierTexte;
import modele.AvancerJoueur;
import modele.Quete;
import modele.Scenario;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Scenario {
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

    private Scenario creerScenarioTest10modif(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_10modif_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    private Scenario creerScenarioTest6(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_6_POUR_TEST.txt");
        Scenario scenarioPourTest =  LectureFichierTexte.lecture(unFichierScenario);

        return scenarioPourTest;
    }

    @Test @Order(4)
    void fusion_pour_cheminsCritiques(){
        System.out.println("[TEST] --- fusion_pour_cheminsCritiques");

        Scenario scenar = creerScenarioTest10();

        AvancerJoueur AJ1 = new AvancerJoueur();
        AvancerJoueur AJ2 = new AvancerJoueur();
        AvancerJoueur AJ3 = new AvancerJoueur();
        AvancerJoueur AJ4 = new AvancerJoueur();
        AvancerJoueur AJ5 = new AvancerJoueur();

        AJ1.ajouteQuete(scenar.get(0), 1); AJ1.ajouteQuete(scenar.get(3), 1);
        AJ1.ajouteQuete(scenar.get(4), 1); AJ1.ajouteQuete(scenar.get(5), 1);
        AJ1.ajouteQuete(scenar.get(1), 1); AJ1.ajouteQuete(scenar.get(2), 1);

        AJ2.ajouteQuete(scenar.get(6), 1); AJ2.ajouteQuete(scenar.get(7), 1);
        AJ2.ajouteQuete(scenar.get(8), 1);

        AJ3.ajouteQuete(scenar.get(9), 1);

        AJ4.ajouteQuete(scenar.get(10), 1); AJ4.ajouteQuete(scenar.get(5), 1);
        AJ4.ajouteQuete(scenar.get(11), 1); AJ4.ajouteQuete(scenar.get(2), 1);

        AJ5.ajouteQuete(scenar.get(12), 1); AJ5.ajouteQuete(scenar.get(13), 1);
        AJ5.ajouteQuete(scenar.get(14), 1); AJ5.ajouteQuete(scenar.get(8), 1);
        AJ5.ajouteQuete(scenar.get(1), 1);

        AvancerJoueur cAJ1 = new AvancerJoueur(AJ1); AvancerJoueur cAJ4 = new AvancerJoueur(AJ4);
        AvancerJoueur cAJ2 = new AvancerJoueur(AJ2); AvancerJoueur cAJ5 = new AvancerJoueur(AJ5);
        AvancerJoueur cAJ3 = new AvancerJoueur(AJ3);

        // =====================

        HashSet<AvancerJoueur> setAJ1 = new HashSet<>();
        HashSet<AvancerJoueur> setAJ2 = new HashSet<>();
        HashSet<AvancerJoueur> compar = new HashSet<>();

        setAJ1.add(AJ1); setAJ1.add(AJ2); setAJ1.add(AJ3);
        setAJ2.add(AJ1); setAJ2.add(AJ4); setAJ2.add(AJ5);

        cAJ1.fusion(AJ1);
        compar.add(cAJ1);
        cAJ1 = new AvancerJoueur(AJ1);
        cAJ1.fusion(AJ4);
        compar.add(cAJ1);
        cAJ1 = new AvancerJoueur(AJ1);
        cAJ1.fusion(AJ5);
        compar.add(cAJ1);

        cAJ2.fusion(AJ1);
        compar.add(cAJ2);
        cAJ2 = new AvancerJoueur(AJ2);
        cAJ2.fusion(AJ4);
        compar.add(cAJ2);
        cAJ2 = new AvancerJoueur(AJ2);
        cAJ2.fusion(AJ5);
        compar.add(cAJ2);

        cAJ3.fusion(AJ1);
        compar.add(cAJ3);
        cAJ3 = new AvancerJoueur(AJ3);
        cAJ3.fusion(AJ4);
        compar.add(cAJ3);
        cAJ3 = new AvancerJoueur(AJ3);
        cAJ3.fusion(AJ5);
        compar.add(cAJ3);


        HashSet<AvancerJoueur> res = Scenario.fusion_pour_cheminsCritiques(setAJ1, setAJ2);
        assertEquals(compar, res);

        // =====================

        setAJ1 = new HashSet<>();
        setAJ2 = new HashSet<>();
        compar = new HashSet<>();

        setAJ1.add(AJ1);
        setAJ2.add(AJ1);

        cAJ1 = AJ1;
        cAJ1.fusion(AJ1);
        compar.add(cAJ1);

        res = Scenario.fusion_pour_cheminsCritiques(setAJ1, setAJ2);
        assertEquals(compar, res);

        // =====================

        setAJ1 = new HashSet<>();
        setAJ2 = new HashSet<>();
        compar = new HashSet<>();


        res = Scenario.fusion_pour_cheminsCritiques(setAJ1, setAJ2);
        assertEquals(compar, res);

        // =====================

        setAJ1 = new HashSet<>();
        setAJ2 = new HashSet<>();
        compar = new HashSet<>();

        setAJ1.add(AJ1);
        compar.add(AJ1);

        res = Scenario.fusion_pour_cheminsCritiques(setAJ1, setAJ2);
        assertEquals(compar, res);

        // =====================

        setAJ1 = new HashSet<>();
        setAJ2 = new HashSet<>();
        compar = new HashSet<>();

        setAJ2.add(AJ4);
        compar.add(AJ4);

        res = Scenario.fusion_pour_cheminsCritiques(setAJ1, setAJ2);
        assertEquals(compar, res);

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test @Order(5)
    void cheminsCritiques(){
        System.out.println("[TEST] --- cheminsCritiques");

        Scenario scenar = creerScenarioTest10();
        Scenario scenar1 = creerScenarioTest3();
        Quete departJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        // =========================

        AvancerJoueur aj1 = new AvancerJoueur();
        aj1.ajouteQuete(scenar.getProvQuetes().get(1), scenar.getProvQuetes().get(1).distanceDeplacement(departJoueur));
        aj1.ajouteQuete(scenar.getProvQuetes().get(0), scenar.getProvQuetes().get(0).distanceDeplacement(scenar.getProvQuetes().get(1)));
        aj1.ajouteQuete(scenar.getProvQuetes().get(16), scenar.getProvQuetes().get(16).distanceDeplacement(scenar.getProvQuetes().get(0)));
        aj1.ajouteQuete(scenar.getProvQuetes().get(18), scenar.getProvQuetes().get(18).distanceDeplacement(scenar.getProvQuetes().get(16)));

        AvancerJoueur aj2 = new AvancerJoueur();
        aj2.ajouteQuete(scenar.getProvQuetes().get(1), scenar.getProvQuetes().get(1).distanceDeplacement(departJoueur));
        aj2.ajouteQuete(scenar.getProvQuetes().get(15), scenar.getProvQuetes().get(15).distanceDeplacement(scenar.getProvQuetes().get(1)));
        aj2.ajouteQuete(scenar.getProvQuetes().get(0), scenar.getProvQuetes().get(0).distanceDeplacement(scenar.getProvQuetes().get(15)));
        aj2.ajouteQuete(scenar.getProvQuetes().get(16), scenar.getProvQuetes().get(16).distanceDeplacement(scenar.getProvQuetes().get(0)));
        aj2.ajouteQuete(scenar.getProvQuetes().get(18), scenar.getProvQuetes().get(18).distanceDeplacement(scenar.getProvQuetes().get(16)));

        HashSet<AvancerJoueur> hsAJ = new HashSet<>();
        hsAJ.add(aj1); hsAJ.add(aj2);

        HashSet<AvancerJoueur> res = scenar.cheminsCritiques();

        assertEquals(hsAJ, res);

        // =====================
        aj1 = new AvancerJoueur();
        aj1.ajouteQuete(scenar1.getProvQuetes().get(3), scenar1.getProvQuetes().get(3).distanceDeplacement(departJoueur));
        aj1.ajouteQuete(scenar1.getProvQuetes().get(5), scenar1.getProvQuetes().get(5).distanceDeplacement(scenar1.getProvQuetes().get(3)));
        aj1.ajouteQuete(scenar1.getProvQuetes().get(0), scenar1.getProvQuetes().get(0).distanceDeplacement(scenar1.getProvQuetes().get(5)));
        aj1.ajouteQuete(scenar1.getProvQuetes().get(6), scenar1.getProvQuetes().get(6).distanceDeplacement(scenar1.getProvQuetes().get(0)));
        aj1.ajouteQuete(scenar1.getProvQuetes().get(4), scenar1.getProvQuetes().get(4).distanceDeplacement(scenar1.getProvQuetes().get(6)));
        aj1.ajouteQuete(scenar1.getProvQuetes().get(7), scenar1.getProvQuetes().get(7).distanceDeplacement(scenar1.getProvQuetes().get(4)));

        aj2 = new AvancerJoueur();
        aj2.ajouteQuete(scenar1.getProvQuetes().get(3), scenar1.getProvQuetes().get(3).distanceDeplacement(departJoueur));
        aj2.ajouteQuete(scenar1.getProvQuetes().get(5), scenar1.getProvQuetes().get(5).distanceDeplacement(scenar1.getProvQuetes().get(3)));
        aj2.ajouteQuete(scenar1.getProvQuetes().get(0), scenar1.getProvQuetes().get(0).distanceDeplacement(scenar1.getProvQuetes().get(5)));
        aj2.ajouteQuete(scenar1.getProvQuetes().get(6), scenar1.getProvQuetes().get(6).distanceDeplacement(scenar1.getProvQuetes().get(0)));
        aj2.ajouteQuete(scenar1.getProvQuetes().get(1), scenar1.getProvQuetes().get(1).distanceDeplacement(scenar1.getProvQuetes().get(6)));
        aj2.ajouteQuete(scenar1.getProvQuetes().get(4), scenar1.getProvQuetes().get(4).distanceDeplacement(scenar1.getProvQuetes().get(1)));
        aj2.ajouteQuete(scenar1.getProvQuetes().get(7), scenar1.getProvQuetes().get(7).distanceDeplacement(scenar1.getProvQuetes().get(4)));

        AvancerJoueur aj3 = new AvancerJoueur();
        aj3.ajouteQuete(scenar1.getProvQuetes().get(3), scenar1.getProvQuetes().get(3).distanceDeplacement(departJoueur));
        aj3.ajouteQuete(scenar1.getProvQuetes().get(6), scenar1.getProvQuetes().get(6).distanceDeplacement(scenar1.getProvQuetes().get(3)));
        aj3.ajouteQuete(scenar1.getProvQuetes().get(5), scenar1.getProvQuetes().get(5).distanceDeplacement(scenar1.getProvQuetes().get(6)));
        aj3.ajouteQuete(scenar1.getProvQuetes().get(0), scenar1.getProvQuetes().get(0).distanceDeplacement(scenar1.getProvQuetes().get(5)));
        aj3.ajouteQuete(scenar1.getProvQuetes().get(1), scenar1.getProvQuetes().get(1).distanceDeplacement(scenar1.getProvQuetes().get(0)));
        aj3.ajouteQuete(scenar1.getProvQuetes().get(4), scenar1.getProvQuetes().get(4).distanceDeplacement(scenar1.getProvQuetes().get(1)));
        aj3.ajouteQuete(scenar1.getProvQuetes().get(7), scenar1.getProvQuetes().get(7).distanceDeplacement(scenar1.getProvQuetes().get(4)));

        AvancerJoueur aj4 = new AvancerJoueur();
        aj4.ajouteQuete(scenar1.getProvQuetes().get(3), scenar1.getProvQuetes().get(3).distanceDeplacement(departJoueur));
        aj4.ajouteQuete(scenar1.getProvQuetes().get(6), scenar1.getProvQuetes().get(6).distanceDeplacement(scenar1.getProvQuetes().get(3)));
        aj4.ajouteQuete(scenar1.getProvQuetes().get(5), scenar1.getProvQuetes().get(5).distanceDeplacement(scenar1.getProvQuetes().get(6)));
        aj4.ajouteQuete(scenar1.getProvQuetes().get(4), scenar1.getProvQuetes().get(4).distanceDeplacement(scenar1.getProvQuetes().get(5)));
        aj4.ajouteQuete(scenar1.getProvQuetes().get(7), scenar1.getProvQuetes().get(7).distanceDeplacement(scenar1.getProvQuetes().get(4)));


        AvancerJoueur aj5 = new AvancerJoueur();
        aj5.ajouteQuete(scenar1.getProvQuetes().get(3), scenar1.getProvQuetes().get(3).distanceDeplacement(departJoueur));
        aj5.ajouteQuete(scenar1.getProvQuetes().get(6), scenar1.getProvQuetes().get(6).distanceDeplacement(scenar1.getProvQuetes().get(3)));
        aj5.ajouteQuete(scenar1.getProvQuetes().get(5), scenar1.getProvQuetes().get(5).distanceDeplacement(scenar1.getProvQuetes().get(6)));
        aj5.ajouteQuete(scenar1.getProvQuetes().get(1), scenar1.getProvQuetes().get(1).distanceDeplacement(scenar1.getProvQuetes().get(5)));
        aj5.ajouteQuete(scenar1.getProvQuetes().get(4), scenar1.getProvQuetes().get(4).distanceDeplacement(scenar1.getProvQuetes().get(1)));
        aj5.ajouteQuete(scenar1.getProvQuetes().get(7), scenar1.getProvQuetes().get(7).distanceDeplacement(scenar1.getProvQuetes().get(4)));

        hsAJ = new HashSet<>();
        hsAJ.add(aj1); hsAJ.add(aj2); hsAJ.add(aj3); hsAJ.add(aj4); hsAJ.add(aj5);

        res = scenar1.cheminsCritiques();

        assertEquals(hsAJ, res);


        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }
}
