import lectureEcritureFichier.LectureFichierTexte;
import modele.AvancerJoueur;
import modele.ChoixUtilisateur;
import modele.Quete;
import modele.Scenario;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_AvancerJoueur {
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


    @Test @Order(1)
    void ajouteQuete(){
        System.out.println("[TEST] --- ajouteQuete");
        Scenario scenar = creerScenarioTest10();
        AvancerJoueur avancer = new AvancerJoueur();

        avancer.ajouteQuete(scenar.get(0), 5);

        assertEquals(6, avancer.getTemps());
        assertEquals(5, avancer.getDeplacements());

        avancer.ajouteQuete(scenar.get(5), 121);


        // === Vérication du TEMPS TOTAL du début du jeu à la fin de la quête.
        assertEquals(135, avancer.getTemps());

        // === Vérication du NOMBRE DE DEPLACEMENT TOTAL du début du jeu à la fin de la quête.
        assertEquals(126, avancer.getDeplacements());

        // === Vérication QUE LA LISTE DES N° ET DES QUÊTES CORRESPONDENT
        assertEquals(avancer.getListNoQuetes().get(0), avancer.getQuete(0).getNumero());
        assertEquals(avancer.getListNoQuetes().get(1), avancer.getQuete(1).getNumero());

        // === Vérication QUE CE SONT BIEN NOS QUÊTES et également que c'est bien une ArrayList, au cas où.
        ArrayList<Integer> testListNoQuetes = new ArrayList<Integer>();
        testListNoQuetes.add(scenar.get(0).getNumero()); testListNoQuetes.add(scenar.get(5).getNumero());
        assertEquals(testListNoQuetes,avancer.getListNoQuetes()); // On regarde si la liste créer manuellement correspond.

        // === Vérication du NOMBRE D'EXPERIENCE TOTAL du début du jeu à la fin de la quête.
        assertEquals(
                scenar.get(0).getExperience() + scenar.get(5).getExperience(),
                avancer.getXp()
        );

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");

    }

    @Test
    @Order(3)
    void quetePlusProcheDuJoueur(){
        System.out.println("[TEST] --- quetePlusProcheDuJoueur");

        Scenario scenar = creerScenarioTest6();
        AvancerJoueur avancer = new AvancerJoueur();

        Object[] listeQueteProche;
        // =========================
        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(6), listeQueteProche[0]);
        assertEquals(22, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(6), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(3), listeQueteProche[0]);
        assertEquals(18, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(3), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(9), listeQueteProche[0]);
        assertEquals(12, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(9), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(4), listeQueteProche[0]);
        assertEquals(13, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(4), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(0), listeQueteProche[0]);
        assertEquals(8, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(0), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(1), listeQueteProche[0]);
        assertEquals(5, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(1), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(5), listeQueteProche[0]);
        assertEquals(6, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(5), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(2), listeQueteProche[0]);
        assertEquals(4, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(2), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(7), listeQueteProche[0]);
        assertEquals(26, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(7), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(10), listeQueteProche[0]);
        assertEquals(6, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(10), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(8), listeQueteProche[0]);
        assertEquals(5, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(8), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(scenar.get(11), listeQueteProche[0]);
        assertEquals(30, listeQueteProche[1]);

        // =========================
        avancer.ajouteQuete(scenar.get(11), 1);

        listeQueteProche = avancer.quetePlusProcheDuJoueur(scenar);
        assertEquals(null, listeQueteProche[0]);
        assertEquals(-1, listeQueteProche[1]);

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }

    @Test @Order(2)
    void fusion(){
        System.out.println("[TEST] --- fusion (AvancerJoueur)");

        Scenario scenar = creerScenarioTest10();

        AvancerJoueur aJ1 = new AvancerJoueur();
        AvancerJoueur aJ2 = new AvancerJoueur();
        AvancerJoueur aJ3 = new AvancerJoueur();
        AvancerJoueur aJ4 = new AvancerJoueur();
        AvancerJoueur aJ5 = new AvancerJoueur();
        AvancerJoueur aJ6 = new AvancerJoueur();

        aJ1.ajouteQuete(scenar.get(0), 1); aJ1.ajouteQuete(scenar.get(2), 1);
        aJ1.ajouteQuete(scenar.get(3), 1); aJ1.ajouteQuete(scenar.get(1), 1);
        aJ1.ajouteQuete(scenar.get(4), 1); aJ1.ajouteQuete(scenar.get(5), 1);

        aJ2.ajouteQuete(scenar.get(6), 1); aJ2.ajouteQuete(scenar.get(7), 1);
        aJ2.ajouteQuete(scenar.get(8), 1);

        aJ3.ajouteQuete(scenar.get(9), 1);

        aJ4.ajouteQuete(scenar.get(10), 1); aJ4.ajouteQuete(scenar.get(5), 1);
        aJ4.ajouteQuete(scenar.get(11), 1); aJ4.ajouteQuete(scenar.get(2), 1);

        aJ5.ajouteQuete(scenar.get(12), 1); aJ5.ajouteQuete(scenar.get(14), 1);
        aJ5.ajouteQuete(scenar.get(13), 1); aJ5.ajouteQuete(scenar.get(8), 1);
        aJ5.ajouteQuete(scenar.get(1), 1);

        AvancerJoueur copieAJ1 = new AvancerJoueur(aJ1);
        AvancerJoueur copieAJ2 = new AvancerJoueur(aJ2);

        copieAJ1.fusion(aJ2);
        ArrayList<Integer> test = new ArrayList<>();

        assertEquals(test, aJ6.getListNoQuetes());

        test.add(18); test.add(4); test.add(17); test.add(2); test.add(11); test.add(6); test.add(7); test.add(9); test.add(14);
        assertEquals(test, copieAJ1.getListNoQuetes());

        copieAJ1.fusion(copieAJ1);
        assertEquals(test, copieAJ1.getListNoQuetes());

        assertNotEquals(copieAJ1.getListNoQuetes(), aJ1.getListNoQuetes());

        test.add(5); test.add(13);
        copieAJ1.fusion(aJ4);
        assertEquals(test, copieAJ1.getListNoQuetes());

        test = new ArrayList<>();
        test.add(7); test.add(9); test.add(14); test.add(18); test.add(4); test.add(17); test.add(2); test.add(11); test.add(6);
        copieAJ1.fusion(aJ2);
        assertNotEquals(test, copieAJ2.getListNoQuetes());

        copieAJ1 = new AvancerJoueur(aJ1);
        test = new ArrayList<>();
        test.add(18); test.add(4); test.add(17); test.add(2); test.add(11); test.add(6); test.add(5); test.add(13);
        copieAJ1.fusion(aJ4);
        assertEquals(test, copieAJ1.getListNoQuetes());

        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }


    @Test @Order(2)
    void retireDerniereQuete() {
        System.out.println("[TEST] --- retireDerniereQuete (AvancerJoueur)");

        ChoixUtilisateur choixUser = new ChoixUtilisateur(-1, -1, 1);

        Scenario scenar = creerScenarioTest10();
        AvancerJoueur aJ1 = new AvancerJoueur();
        AvancerJoueur aJ2 = new AvancerJoueur();

        Quete departJoueur = new Quete("-1|(0, 0)|()|0|0|depart joueur CE NEST PAS UNE QUETE");

        // ===============
        assertFalse(aJ1.retireDerniereQuete(choixUser));

        // ===============
        aJ1.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(departJoueur));
        aJ1.ajouteQuete(scenar.get(3), scenar.get(3).distanceDeplacement(scenar.get(0)));
        aJ2.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(departJoueur));

        assertTrue(aJ1.retireDerniereQuete(choixUser));
        assertTrue(aJ1.equals(aJ2));

        // ===============
        assertTrue(aJ1.retireDerniereQuete(choixUser));
        assertTrue(aJ2.retireDerniereQuete(choixUser));
        assertTrue(aJ1.equals(aJ2));

        // ===============
        assertFalse(aJ1.retireDerniereQuete(choixUser));
        assertFalse(aJ2.retireDerniereQuete(choixUser));
        assertTrue(aJ1.equals(aJ2));

        // ===============
        aJ1.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(departJoueur));
        aJ1.ajouteQuete(scenar.get(3), scenar.get(3).distanceDeplacement(scenar.get(0)));
        aJ1.ajouteQuete(scenar.get(18), scenar.get(18).distanceDeplacement(scenar.get(3)));
        aJ2.ajouteQuete(scenar.get(0), scenar.get(0).distanceDeplacement(departJoueur));
        aJ2.ajouteQuete(scenar.get(3), scenar.get(3).distanceDeplacement(scenar.get(0)));

        assertEquals(aJ1.getXp(), aJ2.getXp());
        aJ1.retireDerniereQuete(choixUser);
        assertEquals(aJ1.getXp(), aJ2.getXp());


        System.out.println("           > Tous les cas ont été exécutés avec succès !\n");
    }
}
