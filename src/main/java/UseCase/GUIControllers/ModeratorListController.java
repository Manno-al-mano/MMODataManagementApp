package UseCase.GUIControllers;

import Constants.FxmlNames;
import Logic.Data.Users.Moderation.ModeratorCzatu;
import Logic.DatabaseOperations.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ModeratorListController {

    @FXML
    private Button exitButton;

    @FXML
    private ListView<String> listaMod;
    private List<ModeratorCzatu> mods;

    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        mods = DatabaseManager.getInstance().getModerators();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for(var mod: mods)
            observableList.add(mod.toString());
        listaMod.setItems(observableList);
        listaMod.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println(listaMod);
    }

    @FXML
    void changestatus(ActionEvent event) {
        try {
            if(listaMod.getSelectionModel().getSelectedItem()!=null) {

                DatabaseManager.getInstance().setModeratorCzatu(mods.get(listaMod.getSelectionModel().getSelectedIndex()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.MAINMENU));
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(new Scene(loader.load()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exit(ActionEvent event) {
    System.exit(0);
    }

}
