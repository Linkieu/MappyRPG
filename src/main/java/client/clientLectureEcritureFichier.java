package client;

import lectureEcritureFichier.LectureFichierTexte;
import modele.Quete;
import modele.Scenario;

import java.io.File;

public class clientLectureEcritureFichier {
    public static void main(String[] args){
        File unFichierScenario = new File("scenarios" + File.separator + "scenario_0.txt");
        Scenario testScenario =  LectureFichierTexte.lecture(unFichierScenario);

        System.out.println(testScenario);

    }
}
