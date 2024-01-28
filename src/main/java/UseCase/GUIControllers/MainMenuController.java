package UseCase.GUIControllers;

import Constants.FxmlNames;
import Logic.Data.Users.Moderation.ModeratorCzatu;
import Logic.Data.Users.Playerbase.Gracz;
import Logic.DatabaseOperations.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenuController {

    @FXML
    private ListView<String> listaGraczy;

    @FXML
    private Text moderatorInfo;

    @FXML
    private Button powrot;

    @FXML
    private Button characters;

    @FXML
    private Button submit;
    ObservableList<Gracz> gracze;
    @FXML
    public void initialize() {
        gracze = FXCollections.observableArrayList(DatabaseManager.getInstance().getPlayers());
        List<String> nicks= new ArrayList<>();
        for (Gracz gracz : gracze)
            nicks.add(gracz.getNick()+", "+ gracz.getDataZmianyCzatu());
        ObservableList<String> observableList = FXCollections.observableArrayList(nicks);
        listaGraczy.setItems(observableList);
        listaGraczy.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listaGraczy.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Assuming moderatorInfo is the Text node you want to update
                moderatorInfo.setText(gracze.get(listaGraczy.getSelectionModel().getSelectedIndex()).getModeratorCzatu().toString());

            }
        });
    }


    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.MODERATORLIST));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changestatus(ActionEvent event) {
        try {
            if(listaGraczy.getSelectionModel().getSelectedItem()!=null) {
                DatabaseManager.getInstance().setGracz(gracze.get(listaGraczy.getSelectionModel().getSelectedIndex()));
                DatabaseManager.getInstance().checkRelation();
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.MESSAGE));
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(new Scene(loader.load()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showCharacters(ActionEvent event){
        try {
            if(listaGraczy.getSelectionModel().getSelectedItem()!=null) {
                DatabaseManager.getInstance().setGracz(gracze.get(listaGraczy.getSelectionModel().getSelectedIndex()));
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.CHARACTERS));
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(new Scene(loader.load()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
