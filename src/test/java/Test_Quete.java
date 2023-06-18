import modele.AvancerJoueur;
import org.junit.jupiter.api.*;


import lectureEcritureFichier.LectureFichierTexte;
import modele.Scenario;
import java.io.File;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_Quete {
    private Scenario creerScenarioTest10(){
        File unFichierScenario = new File("src" + File.separator + "test" + File.separator + "scenario_10_POUR_TEST.txt");
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

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test @Order(1)
    void distanceDeplacement(){
        Scenario scenar = creerScenarioTest10();

        System.out.println("[TEST] --- distanceDeplacement");

        assertEquals(36,scenar.get(0).distanceDeplacement(scenar.get(1)));
        assertEquals(146,scenar.get(0).distanceDeplacement(scenar.get(5)));
        assertEquals(146,scenar.get(5).distanceDeplacement(scenar.get(0)));
        assertEquals(0,scenar.get(8).distanceDeplacement(scenar.get(8)));
        assertEquals(89,scenar.get(0).distanceDeplacement(scenar.get(18)));

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test @Order(2)
    void tempsDeplacement(){
        Scenario scenar10 = creerScenarioTest10();
        Scenario scenar6 = creerScenarioTest6();

        System.out.println("[TEST] --- tempsDeplacement");

        assertEquals(147, scenar10.get(5).tempsDeplacemenEtFaireQuetes(scenar10.get(14)));
        assertEquals(74, scenar10.get(9).tempsDeplacemenEtFaireQuetes(scenar10.get(8)));
        assertEquals(74, scenar10.get(8).tempsDeplacemenEtFaireQuetes(scenar10.get(9)));
        assertEquals(130, scenar10.get(9).tempsDeplacemenEtFaireQuetes(scenar10.get(5)));

        assertEquals(27, scenar6.get(9).tempsDeplacemenEtFaireQuetes(scenar6.get(6)));
        assertEquals(61, scenar6.get(3).tempsDeplacemenEtFaireQuetes(scenar6.get(8)));
        assertEquals(0, scenar6.get(9).tempsDeplacemenEtFaireQuetes(scenar6.get(9)));

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test @Order(2)
    void possibleDeFaireQuete(){
        System.out.println("[TEST] --- possibleDeFaireQuete");
        Scenario scenar = creerScenarioTest10();
        AvancerJoueur avancer = new AvancerJoueur();

        // =============
        assertTrue(scenar.get(1).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(15).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(11).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(8).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(2).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(18).possibleDeFaireQuete(avancer));

        // =============
        avancer.ajouteQuete(scenar.get(9),  1);
        assertTrue(scenar.get(1).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(15).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(11).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(8).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(2).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(18).possibleDeFaireQuete(avancer));

        // =============
        avancer.ajouteQuete(scenar.get(1),  1);
        avancer.ajouteQuete(scenar.get(0),  1);
        assertFalse(scenar.get(1).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(15).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(11).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(8).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(2).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(18).possibleDeFaireQuete(avancer));

        // =============
        avancer.ajouteQuete(scenar.get(16),  14);
        assertFalse(scenar.get(1).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(15).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(11).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(8).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(2).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(18).possibleDeFaireQuete(avancer));

        // =============
        avancer.ajouteQuete(scenar.get(10),  1);
        assertFalse(scenar.get(1).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(15).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(11).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(8).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(2).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(18).possibleDeFaireQuete(avancer));

        // =============
        avancer.ajouteQuete(scenar.get(6),  1);
        assertFalse(scenar.get(1).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(15).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(11).possibleDeFaireQuete(avancer));
        assertFalse(scenar.get(8).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(2).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(18).possibleDeFaireQuete(avancer));

        // =============
        avancer.ajouteQuete(scenar.get(7),  1);
        assertFalse(scenar.get(1).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(15).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(11).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(8).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(2).possibleDeFaireQuete(avancer));
        assertTrue(scenar.get(18).possibleDeFaireQuete(avancer));

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }


}
