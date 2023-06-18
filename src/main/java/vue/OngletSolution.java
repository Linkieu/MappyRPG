package vue;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.ChoixUtilisateur;
import modele.Itineraire;

import java.util.ArrayList;
import java.util.Set;

/**
 * Cette classe représente l'onglet "Solution"
 */
public class OngletSolution extends VBox {

    private final static Button buttonAfficherSolution = new Button("Afficher la solution");
    private final static ToggleGroup groupButton = new ToggleGroup();
    private static TextField scenarioSelect = new TextField();

    private static Spinner<Integer> spinnerQuantiteResultat;

    private static Slider sliderDeplacementMin;
    private static Slider sliderDureeMin;
    private static Slider sliderQueteMin;
    private static Slider sliderXpMin;

    private static Slider sliderDeplacementMax;
    private static Slider sliderDureeMax;
    private static Slider sliderQueteMax;
    private static Slider sliderXpMax;

    private static TextField valueTextFieldDeplacementMin;
    private static TextField valueTextFieldDureeMin;
    private static TextField valueTextFieldQueteMin;
    private static TextField valueTextFieldXpMin;

    private static TextField valueTextFieldDeplacementMax;
    private static TextField valueTextFieldDureeMax;
    private static TextField valueTextFieldQueteMax;
    private static TextField valueTextFieldXpMax;

    private static Label labelDeplacementMin;
    private static Label labelDureeMin;
    private static Label labelQueteMin;
    private static Label labelXpMin;

    private static Label labelDeplacementMax;
    private static Label labelDureeMax;
    private static Label labelQueteMax;
    private static Label labelXpMax;

    private static CheckBox checkBoxQuetePlusProche;
    private static CheckBox checkBoxCritereDeplacement;
    private static CheckBox checkBoxCritereDuree;
    private static CheckBox checkBoxCritereQuete;

    private static ComboBox<String> comboBoxResultat;

    private static TableView<Itineraire> tableauSolutions;



    public OngletSolution(){



        // Label + OngletScenario sélectionné
        HBox hBoxTextField = new HBox();
        HBox.setHgrow(scenarioSelect, Priority.ALWAYS);

        Label infoTextField = new Label("Scénario sélectionné : ");
        infoTextField.setAlignment(Pos.CENTER);
        hBoxTextField.setAlignment(Pos.CENTER);
        scenarioSelect.setMaxWidth(300);
        scenarioSelect.setEditable(false);
        hBoxTextField.getChildren().addAll(infoTextField, scenarioSelect);


        // Label "Choisissez la solution voulue"
        Label labelScenario = new Label("Choisissez la solution voulue");

        // VBox qui contient les radioButtons
        VBox vBoxboiteBoutonsAndVBoxQuantiteResultat = new VBox(15);
        RadioButton buttonEfficace = new RadioButton("Solution E_fficace");
        buttonEfficace.setMnemonicParsing(true);
        RadioButton buttonExhaustive = new RadioButton("Solution E_xhaustive");
        buttonExhaustive.setMnemonicParsing(true);
        RadioButton buttonPersonnalise = new RadioButton("Solution _Personnalisée");
        buttonPersonnalise.setMnemonicParsing(true);

        VBox vBoxQuantiteResultat = new VBox();
        Label labelQuantiteResultat = new Label("Nombre de résultat(s)");

        spinnerQuantiteResultat = new Spinner<>(1, 1000, 1);

        vBoxQuantiteResultat.getChildren().addAll(labelQuantiteResultat, spinnerQuantiteResultat);

        vBoxboiteBoutonsAndVBoxQuantiteResultat.getChildren().addAll(buttonEfficace, buttonExhaustive, buttonPersonnalise, vBoxQuantiteResultat);


        // Affectation des boutons au groupe et attribution d'une valeur
        buttonEfficace.setToggleGroup(groupButton);
        buttonEfficace.setUserData("buttonEfficace");
        buttonExhaustive.setToggleGroup(groupButton);
        buttonExhaustive.setUserData("buttonExhaustive");
        buttonPersonnalise.setToggleGroup(groupButton);
        buttonPersonnalise.setUserData("buttonPersonnalise");



        // Création des curseurs pour le déplacement, la durée et le nombre de quêtes
        sliderDeplacementMin = new Slider(-1, 1000, -1);
        sliderDureeMin = new Slider(-1, 1000, -1);
        sliderQueteMin = new Slider(-1, 100, -1);
        sliderXpMin = new Slider(-1, 10000, -1);

        sliderDeplacementMax = new Slider(-1, 1000, -1);
        sliderDureeMax = new Slider(-1, 1000, -1);
        sliderQueteMax = new Slider(-1, 100, -1);
        sliderXpMax = new Slider(-1, 10000, -1);

        // Définition des largeurs des curseurs Min
        sliderDeplacementMin.setStyle("-fx-pref-width: 150px;");
        sliderDureeMin.setStyle("-fx-pref-width: 150px;");
        sliderQueteMin.setStyle("-fx-pref-width: 150px;");
        sliderXpMin.setStyle("-fx-pref-width: 150px;");

        // Définition des largeurs des curseurs Max
        sliderDeplacementMax.setStyle("-fx-pref-width: 150px;");
        sliderDureeMax.setStyle("-fx-pref-width: 150px;");
        sliderQueteMax.setStyle("-fx-pref-width: 150px;");
        sliderXpMax.setStyle("-fx-pref-width: 150px;");

        // Création des textfield Min et Max avec l'appel d'une méthode pour simplifier l'écriture
        valueTextFieldDeplacementMin = createValueTextField(sliderDeplacementMin);
        valueTextFieldDureeMin = createValueTextField(sliderDureeMin);
        valueTextFieldQueteMin = createValueTextField(sliderQueteMin);
        valueTextFieldXpMin = createValueTextField(sliderXpMin);

        valueTextFieldDeplacementMax = createValueTextField(sliderDeplacementMax);
        valueTextFieldDureeMax = createValueTextField(sliderDureeMax);
        valueTextFieldQueteMax = createValueTextField(sliderQueteMax);
        valueTextFieldXpMax = createValueTextField(sliderXpMax);


        // Soulignage des valeurs des curseurs
        valueTextFieldDeplacementMin.setStyle("-fx-underline: true;");
        valueTextFieldDureeMin.setStyle("-fx-underline: true;");
        valueTextFieldQueteMin.setStyle("-fx-underline: true;");
        valueTextFieldXpMin.setStyle("-fx-underline: true;");

        valueTextFieldDeplacementMax.setStyle("-fx-underline: true;");
        valueTextFieldDureeMax.setStyle("-fx-underline: true;");
        valueTextFieldQueteMax.setStyle("-fx-underline: true;");
        valueTextFieldQueteMax.setStyle("-fx-underline: true;");
        valueTextFieldXpMax.setStyle("-fx-underline: true;");



        // Labels qui spécifient les curseurs Min et Max
        labelDeplacementMin = new Label("Nombre de déplacements minimum voulus");
        labelDureeMin = new Label("Nombre de durées minimum voulues");
        labelQueteMin = new Label("Nombre de quêtes minimum voulues");
        labelXpMin = new Label("Nombre d'xp minimum voulues");

        labelDeplacementMax = new Label("Nombre de déplacements maximum voulus");
        labelDureeMax = new Label("Nombre de durées maximum voulues");
        labelQueteMax = new Label("Nombre de quêtes maximum voulues");
        labelXpMax = new Label("Nombre d'xp maximum voulues");



        // Création d'un gridpane pour bien aligner les éléments
        GridPane gridPaneLabelAndCurseur = new GridPane();
        gridPaneLabelAndCurseur.setHgap(10); // Espacement horizontal entre les colonnes
        gridPaneLabelAndCurseur.setVgap(15); // Espacement vertical entre les lignes

        gridPaneLabelAndCurseur.add(labelDeplacementMin, 0, 0);
        gridPaneLabelAndCurseur.add(labelDureeMin, 0, 1);
        gridPaneLabelAndCurseur.add(labelQueteMin, 0, 2);
        gridPaneLabelAndCurseur.add(labelXpMin, 0, 3);

        gridPaneLabelAndCurseur.add(sliderDeplacementMin, 1, 0);
        gridPaneLabelAndCurseur.add(valueTextFieldDeplacementMin, 2, 0);
        gridPaneLabelAndCurseur.add(sliderDureeMin, 1, 1);
        gridPaneLabelAndCurseur.add(valueTextFieldDureeMin, 2, 1);
        gridPaneLabelAndCurseur.add(sliderQueteMin, 1, 2);
        gridPaneLabelAndCurseur.add(valueTextFieldQueteMin, 2, 2);
        gridPaneLabelAndCurseur.add(sliderXpMin, 1, 3);
        gridPaneLabelAndCurseur.add(valueTextFieldXpMin, 2, 3);

        gridPaneLabelAndCurseur.add(labelDeplacementMax, 4, 0);
        gridPaneLabelAndCurseur.add(labelDureeMax, 4, 1);
        gridPaneLabelAndCurseur.add(labelQueteMax, 4, 2);
        gridPaneLabelAndCurseur.add(labelXpMax, 4, 3);

        gridPaneLabelAndCurseur.add(sliderDeplacementMax, 5, 0);
        gridPaneLabelAndCurseur.add(valueTextFieldDeplacementMax, 6, 0);
        gridPaneLabelAndCurseur.add(sliderDureeMax, 5, 1);
        gridPaneLabelAndCurseur.add(valueTextFieldDureeMax, 6, 1);
        gridPaneLabelAndCurseur.add(sliderQueteMax, 5, 2);
        gridPaneLabelAndCurseur.add(valueTextFieldQueteMax, 6, 2);
        gridPaneLabelAndCurseur.add(sliderXpMax, 5, 3);
        gridPaneLabelAndCurseur.add(valueTextFieldXpMax, 6, 3);

        // Alignement du gridpane à droite
        gridPaneLabelAndCurseur.setAlignment(Pos.CENTER_RIGHT);

        // Boucle pour ne pas agrandir les cases horizontalement et verticalement
        for (Node node : gridPaneLabelAndCurseur.getChildren()) {
            if (node instanceof Control) {
                GridPane.setHgrow(node, Priority.NEVER);
                GridPane.setVgrow(node, Priority.NEVER);
            }
        }


        // Création d'une HBox pour disposer les éléments (boiteBoutons et gridPaneLabelAndCurseur) sur la même ligne
        HBox hBoxBoutonAndGridPane = new HBox(15); // Séparation de 30 pixels entre les éléments
        hBoxBoutonAndGridPane.getChildren().addAll(vBoxboiteBoutonsAndVBoxQuantiteResultat, gridPaneLabelAndCurseur);


        // Bouton OngletSolution qui demande au controleur de gérer l'action du clic
        HBox zoneBouton = new HBox(20);
        Controleur controleur = new Controleur();
        buttonAfficherSolution.setOnAction(controleur);
        buttonAfficherSolution.setStyle("-fx-font: 22 arial; -fx-base: #008000;");
        zoneBouton.setAlignment(Pos.CENTER_LEFT);



        // Création de checkBox afin de choisir différents critères
        checkBoxQuetePlusProche = new CheckBox("_Quête la plus proche");
        checkBoxQuetePlusProche.setMnemonicParsing(true);
        checkBoxCritereDeplacement = new CheckBox("_Critère Déplacement");
        checkBoxCritereDeplacement.setMnemonicParsing(true);
        checkBoxCritereDuree = new CheckBox("_Critère Durée");
        checkBoxCritereDuree.setMnemonicParsing(true);
        checkBoxCritereQuete = new CheckBox("_Critère Quête");
        checkBoxCritereQuete.setMnemonicParsing(true);

        comboBoxResultat = new ComboBox<>();
        comboBoxResultat.getItems().addAll("Meilleur Résultat", "Pire Résultat");
        comboBoxResultat.setValue("Meilleur Résultat");

        checkBoxQuetePlusProche.setStyle("-fx-base: #777;");
        checkBoxCritereDeplacement.setStyle("-fx-base: #777;");
        checkBoxCritereDuree.setStyle("-fx-base: #777;");
        checkBoxCritereQuete.setStyle("-fx-base: #777;");
        comboBoxResultat.setStyle("-fx-base: #555;");



        // Empêche les curseurs, le bouton personnalisé, les textfields, les checkBoxes et la comboBox de s'actionner quand la checkBoxQuetePlusProche est sélectionnée
        checkBoxQuetePlusProche.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                sliderDeplacementMin.setValue(-1);
                valueTextFieldDeplacementMin.setEditable(false);
                sliderDeplacementMax.setValue(-1);
                valueTextFieldDeplacementMax.setEditable(false);
                sliderDureeMin.setValue(-1);
                valueTextFieldDureeMin.setEditable(false);
                sliderDureeMax.setValue(-1);
                valueTextFieldDureeMax.setEditable(false);
                sliderQueteMin.setValue(-1);
                valueTextFieldQueteMin.setEditable(false);
                sliderQueteMax.setValue(-1);
                valueTextFieldQueteMax.setEditable(false);
                sliderXpMin.setValue(-1);
                valueTextFieldXpMin.setEditable(false);
                sliderXpMax.setValue(-1);
                valueTextFieldXpMax.setEditable(false);

                buttonPersonnalise.setSelected(false);
                buttonEfficace.setSelected(true);

                checkBoxCritereDeplacement.setSelected(false);
                checkBoxCritereDuree.setSelected(false);
                checkBoxCritereQuete.setSelected(false);

                comboBoxResultat.setDisable(true);

                spinnerQuantiteResultat.setDisable(true);
            }
            else {
                valueTextFieldDeplacementMin.setEditable(true);
                valueTextFieldDeplacementMax.setEditable(true);
                valueTextFieldDureeMin.setEditable(true);
                valueTextFieldDureeMax.setEditable(true);
                valueTextFieldQueteMin.setEditable(true);
                valueTextFieldQueteMax.setEditable(true);
                valueTextFieldXpMin.setEditable(true);
                valueTextFieldXpMax.setEditable(true);

                comboBoxResultat.setDisable(false);

                spinnerQuantiteResultat.setDisable(false);
            }
        });
        sliderDeplacementMin.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                sliderDeplacementMin.setValue(-1);
            }
        });
        sliderDeplacementMax.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected())
                sliderDeplacementMax.setValue(-1);
        });
        sliderDureeMin.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected())
                sliderDureeMin.setValue(-1);
        });
        sliderDureeMax.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected())
                sliderDureeMax.setValue(-1);
        });
        sliderQueteMin.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected())
                sliderQueteMin.setValue(-1);
        });
        sliderQueteMax.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected())
                sliderQueteMax.setValue(-1);
        });
        sliderXpMin.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                sliderXpMin.setValue(-1);
            }
        });
        sliderXpMax.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected())
                sliderXpMax.setValue(-1);
        });
        buttonPersonnalise.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                buttonPersonnalise.setSelected(false);
                buttonEfficace.setSelected(true);
            }
        });
        checkBoxCritereDeplacement.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                checkBoxCritereDeplacement.setSelected(false);
            }
        });
        checkBoxCritereDuree.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                checkBoxCritereDuree.setSelected(false);
            }
        });
        checkBoxCritereQuete.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (checkBoxQuetePlusProche.isSelected()) {
                checkBoxCritereQuete.setSelected(false);
            }
        });



        // Création d'une HBox pour aligner le bouton et la checkBox
        HBox hBoxSolutionCheckBoxComboBox = new HBox(50);
        hBoxSolutionCheckBoxComboBox.setAlignment(Pos.CENTER_LEFT);
        hBoxSolutionCheckBoxComboBox.getChildren().addAll(buttonAfficherSolution, checkBoxQuetePlusProche, checkBoxCritereDeplacement, checkBoxCritereDuree, checkBoxCritereQuete, comboBoxResultat);

        // Création d'un tableau répertoriant les éléments dans les colonnes
        tableauSolutions = new TableView<>();
        // Création des colonnes du tableau
        TableColumn<Itineraire, Integer> colonneScore = new TableColumn<>("Score");
        TableColumn<Itineraire, ArrayList<Itineraire>> colonneParcours = new TableColumn<>("Parcours");
        TableColumn<Itineraire, Integer> colonneQuete = new TableColumn<>("Nombre de Quête");
        TableColumn<Itineraire, Integer> colonneDep = new TableColumn<>("Nombre de Déplacement");
        TableColumn<Itineraire, Integer> colonneDuree = new TableColumn<>("Nombre de Durée");
        TableColumn<Itineraire, Integer> colonneXp = new TableColumn<>("Nombre d'XP");

        // Définition des longueurs des colonnes et le centrage
        colonneScore.setPrefWidth(200);
        colonneScore.setStyle("-fx-alignment: center;");
        colonneParcours.setPrefWidth(200);
        colonneParcours.setStyle("-fx-alignment: center;");
        colonneQuete.setPrefWidth(200);
        colonneQuete.setStyle("-fx-alignment: center;");
        colonneDep.setPrefWidth(200);
        colonneDep.setStyle("-fx-alignment: center;");
        colonneDuree.setPrefWidth(200);
        colonneDuree.setStyle("-fx-alignment: center;");
        colonneXp.setPrefWidth(200);
        colonneXp.setStyle("-fx-alignment: center;");

        // Attribution d'une valeur aux colonnes
        colonneScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colonneParcours.setCellValueFactory(new PropertyValueFactory<>("lesNoQuetes"));
        colonneQuete.setCellValueFactory(new PropertyValueFactory<>("NbQuetes"));
        colonneDep.setCellValueFactory(new PropertyValueFactory<>("deplacements"));
        colonneDuree.setCellValueFactory(new PropertyValueFactory<>("temps"));
        colonneXp.setCellValueFactory(new PropertyValueFactory<>("XP"));

        tableauSolutions.getColumns().addAll(colonneScore, colonneParcours, colonneQuete, colonneDep, colonneDuree, colonneXp);

        this.getChildren().addAll(hBoxTextField, labelScenario, hBoxBoutonAndGridPane, hBoxSolutionCheckBoxComboBox, tableauSolutions);

        this.setPadding(new Insets(10,10,10,10));
    }
    public void update(ArrayList<Itineraire> listItineraire) {
        /*
         * Met à jour le contenu du tableau des réservations.
         * Efface d'abord les anciennes réservations du tableau avec clear(),
         * puis ajoute les nouvelles réservations placées en paramètre.
         * Si la liste de réservations est nulle, le tableau reste vide.
         */
        tableauSolutions.getItems().clear();
        if (listItineraire != null) {
            for (Itineraire itineraire : listItineraire) {
                tableauSolutions.getItems().add(itineraire);
            }
        }
    }

    /**
     * Méthode qui permet de récupérer AfficherSolution
     * @return Button
     */
    public static Button getButtonAfficherSolution(){
        return buttonAfficherSolution;
    }

    /**
     * Méthode qui permet de récupérer la valeur associée au bouton efficace, exhaustive et personnalisé
     * - Elle renvoie cette valeur sous forme de chaîne de caractères
     * - Si aucun bouton n'est sélectionné, elle renvoie null
     * @return String
     */
    public static String getButtonSelectionne(){
        if (groupButton.getSelectedToggle() != null) {
            return groupButton.getSelectedToggle().getUserData().toString();
        }
        return null;
    }

    public static int getSpinnerQuantiteResultat() {
        return spinnerQuantiteResultat.getValue();
    }

    public static int getValueTextFieldDeplacementMin(){
        return Integer.parseInt(valueTextFieldDeplacementMin.getText());
    }
    public static int getValueTextFieldDureeMin(){
        return Integer.parseInt(valueTextFieldDureeMin.getText());
    }
    public static int getValueTextFieldQueteMin(){
        return Integer.parseInt(valueTextFieldQueteMin.getText());
    }
    public static int getValueTextFieldXpMin(){
        return Integer.parseInt(valueTextFieldXpMin.getText());
    }
    public static int getValueTextFieldDeplacementMax(){
        return Integer.parseInt(valueTextFieldDeplacementMax.getText());
    }
    public static int getValueTextFieldDureeMax(){
        return Integer.parseInt(valueTextFieldDureeMax.getText());
    }
    public static int getValueTextFieldQueteMax(){
        return Integer.parseInt(valueTextFieldQueteMax.getText());
    }
    public static int getValueTextFieldXpMax(){
        return Integer.parseInt(valueTextFieldXpMax.getText());
    }

    public static boolean getCheckBoxQuetePlusProche(){
        return checkBoxQuetePlusProche.isSelected();
    }
    public static boolean getCheckBoxCritereDeplacement(){
        return checkBoxCritereDeplacement.isSelected();
    }
    public static boolean getCheckBoxCritereDuree(){
        return checkBoxCritereDuree.isSelected();
    }
    public static boolean getCheckBoxCritereQuete(){
        return checkBoxCritereQuete.isSelected();
    }
    public static String getComboBoxResultat(){
        return comboBoxResultat.getValue();
    }


    /**
     * Méthode qui met à jour la valeur de la variable scenarioSelect en fonction de la sélection faite dans la comboBox de la classe OngletScenario
     * - Si la sélection contient le mot "scenario", elle met à jour scenarioSelect avec cette valeur
     * - Sinon, elle affiche un message indiquant de sélectionner un scénario dans un autre onglet.
     */
    public static void setScenarioSelectionne(){
        if (OngletScenario.getItemSelectionneComboBox().contains("scenario")){
            scenarioSelect.setText(OngletScenario.getItemSelectionneComboBox());
        }
        else {
            scenarioSelect.setText("Sélectionnez un scénario dans l'autre onglet !");
        }
    }

    public static String getScenarioSelect(){
        return scenarioSelect.getText();
    }

    /**
     * Cette méthode vérifie si le texte contenu dans scenarioSelect ne contient pas le mot "onglet"
     * retourne true si c'est le cas,
     * sinon elle retourne false
     *
     * Sert pour savoir si le scenario a bien été pris en compte
     * @return boolean
     */
    public static boolean scenarioValide(){
        return !scenarioSelect.getText().contains("onglet");
    }

    /**
     * Methode qui crée et met à jour le curseur sélectionné en fonction de la valeur du curseur
     * @param slider curseur sélectionné
     * @return valeur du label du curseur
     */
    private TextField createValueTextField(Slider slider) {
        TextField textField = new TextField();
        textField.setText(String.valueOf((int) slider.getValue()));
        textField.setEditable(true);
        textField.setPrefWidth(75);

        // Met à jour la valeur du curseur lorsque le texte est modifié
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                slider.setValue(value);
            } catch (NumberFormatException e) {
                // Si le texte n'est pas un entier valide alors on ne remplace pas et on garde la même chose
                slider.setValue(-1);
                textField.setText(String.valueOf((int) slider.getValue()));
            }
        });

        // Met à jour le texte du textField lorsque le curseur est modifié
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            textField.setText(String.valueOf(newValue.intValue()));
        });

        return textField;
    }
}