package vue;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.util.List;

/**
 * Cette classe permet de lier les deux onglets scenario et solution. Elle gère un menu.
 */
public class GUI extends VBox {

    // Champ qui contient les onglets
    private static StackPane stackPane = new StackPane();

    private static OngletScenario scenario;
    private static OngletSolution solution;
    public GUI(){

        scenario = new OngletScenario();
        solution = new OngletSolution();

        VBox box = new VBox();
        box.setUserData("Solution");
        box.getChildren().add(solution);
        box.setId("opaque");

        VBox boxScenario = new VBox();
        boxScenario.setUserData("Scénario");
        boxScenario.getChildren().add(scenario);
        boxScenario.setId("opaque");

        stackPane.getChildren().addAll(box, boxScenario);

        MenuBar menuBar = new MenuBar();
        Menu menuScenario = new Menu("Scénario");
        Menu menuSolution = new Menu("Solution");
        menuBar.getMenus().addAll(menuScenario, menuSolution);
        GUI.onActionScenario(menuScenario);
        GUI.onActionSolution(menuSolution);

        getChildren().addAll(menuBar, stackPane);
    }

    /**
     * méthode statique qui définit l'action à effectuer lorsque le menu "Scénario" est sélectionné
     * @param menu menu
     */
    public static void onActionScenario(Menu menu){
        final MenuItem menuItem = new MenuItem();
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
        menu.getItems().add(menuItem);
        menu.setOnAction(actionEvent -> {
            List<Node> stackPaneList = stackPane.getChildren();
            if (stackPaneList.get(0).getUserData().equals("Scénario")) {
                stackPaneList.get(0).toFront();
            }
        });
    }

    /**
     * méthode statique qui définit l'action à effectuer lorsque le menu "Scénario" est sélectionné.
     * @param menu menu
     */
    public static void onActionSolution(Menu menu){
        final MenuItem menuItem = new MenuItem();
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
        menu.getItems().add(menuItem);
        menu.setOnAction(actionEvent -> {
            OngletSolution.setScenarioSelectionne();
            List<Node> stackPaneList = stackPane.getChildren();
            if (stackPaneList.get(0).getUserData().equals("Solution")) {
                stackPaneList.get(0).toFront();
            }
        });
    }

    public static OngletSolution getOngletSolution(){
        return solution;
    }
}