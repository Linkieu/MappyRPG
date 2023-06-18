package vue;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modele.Scenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Cette classe représente l'onglet "Scénario"
 */
public class OngletScenario extends VBox {

    private static ComboBox<String> comboBoxScenario = new ComboBox<>();

    private static TextArea textAreaAffichageScenario = new TextArea();
    private static ChangeListener <String> changeListener;

    private StringProperty selectedFilePath = new SimpleStringProperty();


    public OngletScenario() {
        VBox vBoxScenario = new VBox(10);
        vBoxScenario.setPadding(new Insets(10));

        ScrollPane scrollPaneAffichageScenario = new ScrollPane();
        scrollPaneAffichageScenario.setContent(textAreaAffichageScenario);

        Label labelTextAreaAffichageScenario = new Label("Contenu du scénario");

        textAreaAffichageScenario.setPrefSize(1150, 490);
        textAreaAffichageScenario.setEditable(false);




        vBoxScenario.getChildren().addAll(comboBoxScenario, labelTextAreaAffichageScenario, scrollPaneAffichageScenario);

        vBoxScenario.setAlignment(Pos.TOP_CENTER);

        changeListener = (observableValue, oldValue, newValue) -> {
            selectedFilePath.set(newValue);

            String filePath = getSelectedFilePath();
            if (filePath != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    textAreaAffichageScenario.setText(content.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                textAreaAffichageScenario.setText("");
            }
        };
        getChildren().add(vBoxScenario);
        OngletScenario.mettreAjourComboBox();
    }

    /**
     * Méthode statique pour obtenir l'élément sélectionné dans la ComboBox
     * @return élément sélectionné dans la ComboBox
     */
    public static String getItemSelectionneComboBox() {
        return comboBoxScenario.getSelectionModel().getSelectedItem();
    }

    /**
     * Méthode privée statique pour mettre à jour la ComboBox
     */
    private static void mettreAjourComboBox() {
        // remplis la comboBox des fichiers de tour_APLI donc des scénarios + ressources et rajoute une valeur
        // par défaut disant de cliquer dessus au premier appel de cette méthode
        comboBoxScenario.valueProperty().removeListener(changeListener);

        ObservableList<String> listItemsComboBox = FXCollections.observableArrayList();
        File dossierScenario = new File( "scenarios");
        String cheminDossierScenario = dossierScenario.getPath();
        File dossierScenarioServantChemin = new File(cheminDossierScenario);
        File[] contenuDossierScenario = dossierScenarioServantChemin.listFiles();

        if (contenuDossierScenario != null) {
            for (File fichierScenario : contenuDossierScenario) {
                if (fichierScenario.isFile()) {
                    listItemsComboBox.add(fichierScenario.getPath());
                }
            }
        }
        comboBoxScenario.setItems(listItemsComboBox);
        comboBoxScenario.setValue("Cliquez pour sélectionner le scénario souhaité");
        comboBoxScenario.valueProperty().addListener(changeListener);
    }

    /**
     * Méthode pour obtenir le chemin du fichier de scénario sélectionné
     * @return String
     */
    public String getSelectedFilePath() {
        return selectedFilePath.get();
    }
}