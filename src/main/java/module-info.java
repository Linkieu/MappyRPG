module com.example.mappy_rpg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.mappy_rpg to javafx.fxml;
    exports client;
    exports console;
    exports controleur;
    exports lectureEcritureFichier;
    exports modele;
    exports vue;
}