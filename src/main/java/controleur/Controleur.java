package controleur;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import lectureEcritureFichier.LectureFichierTexte;
import modele.Algorithmes;
import modele.ChoixUtilisateur;
import modele.Itineraire;
import modele.Scenario;
import vue.*;

import javafx.event.*;
import java.io.File;
import java.util.ArrayList;


public class Controleur implements EventHandler {
    public void handle(Event event){

        OngletSolution ongletSolution = GUI.getOngletSolution();
        int soluceEfficace = 1;
        int soluceExhaustive = 2;
        int solucePerso = 0;

        int critereDep = 0;
        int critereDur = 0;
        int critereQuete = 0;
        String resultatComboBox = OngletSolution.getComboBoxResultat();



        if (event.getSource() == OngletSolution.getButtonAfficherSolution() && OngletSolution.getButtonSelectionne() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR ! Pas de solution choisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir une solution pour continuer");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution() && !OngletSolution.scenarioValide()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR ! Pas de scenario sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir un scénario pour continuer");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution() && (!OngletSolution.getCheckBoxQuetePlusProche() && !OngletSolution.getCheckBoxCritereDeplacement() && !OngletSolution.getCheckBoxCritereQuete() && !OngletSolution.getCheckBoxCritereDuree())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR ! Pas de critère sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez choisir un critère pour continuer");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution() && (OngletSolution.getValueTextFieldDureeMin() > OngletSolution.getValueTextFieldDureeMax()) && OngletSolution.getValueTextFieldDureeMax() != -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR >> Valeurs incorrectes");
            alert.setHeaderText(null);
            alert.setContentText("La durée maximale est inférieure à la durée minimale !");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution() && (OngletSolution.getValueTextFieldDeplacementMin() > OngletSolution.getValueTextFieldDeplacementMax()) && OngletSolution.getValueTextFieldDeplacementMax() != -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR >> Valeurs incorrectes");
            alert.setHeaderText(null);
            alert.setContentText("La distance maximale est inférieure à la distance minimale !");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution() && (OngletSolution.getValueTextFieldQueteMin() > OngletSolution.getValueTextFieldQueteMax()) && OngletSolution.getValueTextFieldQueteMax() != -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR >> Valeurs incorrectes");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre de quêtes maximal est inférieur au nombre de quête minimale !");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution() && (OngletSolution.getValueTextFieldXpMin() > OngletSolution.getValueTextFieldXpMax()) && OngletSolution.getValueTextFieldXpMax() != -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERREUR >> Valeurs incorrectes");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre d'expérience maximal est inférieure au nombre d'expérience minimale !");
            alert.showAndWait();
        }

        else if (event.getSource() == OngletSolution.getButtonAfficherSolution()){
            File unFichierScenario = new File(OngletSolution.getScenarioSelect());
            Scenario scenario =  LectureFichierTexte.lecture(unFichierScenario);
            if (OngletSolution.getButtonSelectionne().equals("buttonEfficace")){
                if (OngletSolution.getCheckBoxQuetePlusProche()) {
                    ChoixUtilisateur choixUtilisateur = new ChoixUtilisateur(true, soluceEfficace);
                    ArrayList<Itineraire> resultat = Algorithmes.rechercheItineraire(scenario, choixUtilisateur);
                    ongletSolution.update(resultat);
                }
                else {
                    if (OngletSolution.getCheckBoxCritereDeplacement() && resultatComboBox.equals("Meilleur Résultat")){
                        critereDep = 1;
                    }
                    else if (OngletSolution.getCheckBoxCritereDeplacement() && resultatComboBox.equals("Pire Résultat")){
                        critereDep = 2;
                    }
                    if (OngletSolution.getCheckBoxCritereDuree() && resultatComboBox.equals("Meilleur Résultat")){
                        critereDur = 1;
                    }
                    else if (OngletSolution.getCheckBoxCritereDuree() && resultatComboBox.equals("Pire Résultat")){
                        critereDur = 2;
                    }
                    if (OngletSolution.getCheckBoxCritereQuete() && resultatComboBox.equals("Meilleur Résultat")){
                        critereQuete = 1;
                    }
                    else if (OngletSolution.getCheckBoxCritereQuete() && resultatComboBox.equals("Pire Résultat")){
                        critereQuete = 2;
                    }
                    ChoixUtilisateur choixUtilisateur = new ChoixUtilisateur(critereDur, critereQuete, critereDep,
                            OngletSolution.getValueTextFieldDureeMin(), OngletSolution.getValueTextFieldDureeMax(),
                            OngletSolution.getValueTextFieldDeplacementMin(),  OngletSolution.getValueTextFieldDeplacementMax(),
                            OngletSolution.getValueTextFieldQueteMin(),  OngletSolution.getValueTextFieldQueteMax(),
                            OngletSolution.getValueTextFieldXpMin(), OngletSolution.getValueTextFieldXpMax(),
                            soluceEfficace, OngletSolution.getSpinnerQuantiteResultat());

                    ArrayList<Itineraire> resultat = Algorithmes.rechercheItineraire(scenario, choixUtilisateur);
                    ongletSolution.update(resultat);
                }


            }

            if (OngletSolution.getButtonSelectionne().equals("buttonExhaustive")){
                if (OngletSolution.getCheckBoxQuetePlusProche()) {
                    ChoixUtilisateur choixUtilisateur = new ChoixUtilisateur(true, soluceExhaustive);
                    ArrayList<Itineraire> resultat = Algorithmes.rechercheItineraire(scenario, choixUtilisateur);
                    ongletSolution.update(resultat);
                }
                else {
                    if (OngletSolution.getCheckBoxCritereDeplacement() && resultatComboBox.equals("Meilleur Résultat")){
                        critereDep = 1;
                    }
                    else if (OngletSolution.getCheckBoxCritereDeplacement() && resultatComboBox.equals("Pire Résultat")){
                        critereDep = 2;
                    }
                    if (OngletSolution.getCheckBoxCritereDuree() && resultatComboBox.equals("Meilleur Résultat")){
                        critereDur = 1;
                    }
                    else if (OngletSolution.getCheckBoxCritereDuree() && resultatComboBox.equals("Pire Résultat")){
                        critereDur = 2;
                    }
                    if (OngletSolution.getCheckBoxCritereQuete() && resultatComboBox.equals("Meilleur Résultat")){
                        critereQuete = 1;
                    }
                    else if (OngletSolution.getCheckBoxCritereQuete() && resultatComboBox.equals("Pire Résultat")){
                        critereQuete = 2;
                    }
                    ChoixUtilisateur choixUtilisateur = new ChoixUtilisateur(critereDur, critereQuete, critereDep,
                            OngletSolution.getValueTextFieldDureeMin(), OngletSolution.getValueTextFieldDureeMax(),
                            OngletSolution.getValueTextFieldDeplacementMin(),  OngletSolution.getValueTextFieldDeplacementMax(),
                            OngletSolution.getValueTextFieldQueteMin(),  OngletSolution.getValueTextFieldQueteMax(),
                            OngletSolution.getValueTextFieldXpMin(), OngletSolution.getValueTextFieldXpMax(),
                            soluceExhaustive, OngletSolution.getSpinnerQuantiteResultat());


                    ArrayList<Itineraire> resultat = Algorithmes.rechercheItineraire(scenario, choixUtilisateur);
                    ongletSolution.update(resultat);
                }
            }

            if (OngletSolution.getButtonSelectionne().equals("buttonPersonnalise")){
                if (OngletSolution.getCheckBoxCritereDeplacement() && resultatComboBox.equals("Meilleur Résultat")){
                    critereDep = 1;
                }
                else if (OngletSolution.getCheckBoxCritereDeplacement() && resultatComboBox.equals("Pire Résultat")){
                    critereDep = 2;
                }
                if (OngletSolution.getCheckBoxCritereDuree() && resultatComboBox.equals("Meilleur Résultat")){
                    critereDur = 1;
                }
                else if (OngletSolution.getCheckBoxCritereDuree() && resultatComboBox.equals("Pire Résultat")){
                    critereDur = 2;
                }
                if (OngletSolution.getCheckBoxCritereQuete() && resultatComboBox.equals("Meilleur Résultat")){
                    critereQuete = 1;
                }
                else if (OngletSolution.getCheckBoxCritereQuete() && resultatComboBox.equals("Pire Résultat")){
                    critereQuete = 2;
                }
                ChoixUtilisateur choixUtilisateur = new ChoixUtilisateur(critereDur, critereQuete, critereDep,
                        OngletSolution.getValueTextFieldDureeMin(), OngletSolution.getValueTextFieldDureeMax(),
                        OngletSolution.getValueTextFieldDeplacementMin(),  OngletSolution.getValueTextFieldDeplacementMax(),
                        OngletSolution.getValueTextFieldQueteMin(),  OngletSolution.getValueTextFieldQueteMax(),
                        OngletSolution.getValueTextFieldXpMin(), OngletSolution.getValueTextFieldXpMax(),
                        solucePerso, OngletSolution.getSpinnerQuantiteResultat());



                ArrayList<Itineraire> resultat = Algorithmes.rechercheItineraire(scenario, choixUtilisateur);
                ongletSolution.update(resultat);
            }
        }
    }
}