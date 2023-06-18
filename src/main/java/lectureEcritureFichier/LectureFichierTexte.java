package lectureEcritureFichier;

import javafx.scene.Scene;
import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureFichierTexte {


    public static modele.Scenario lecture (File fichier){
        Scenario scenario = new Scenario();
        try{
            Scanner scanner = new Scanner(fichier);
            while (scanner.hasNext()){
                scenario.ajout (new Quete(scanner.nextLine()));
            }
        }
        catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }

        return scenario;
    }
}
