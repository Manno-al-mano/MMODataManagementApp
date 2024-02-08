package UseCase.GUIControllers;

import Constants.FxmlNames;
import Constants.Messages;
import Logic.Data.Users.Moderation.MistrzGry;
import Logic.Data.Users.Playerbase.Gracz;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    ObservableList<Gracz> gracze;
    @FXML
    private ListView<Gracz> listaGraczy;
    @FXML
    private Text moderatorInfo;
    @FXML
    private Button powrot;
    @FXML
    private Button characters;
    @FXML
    private Button submit;

    @FXML
    public void initialize() {
        gracze = FXCollections.observableArrayList(DatabaseManager.getInstance().getPlayers());
        listaGraczy.setItems(gracze);
        listaGraczy.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listaGraczy.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Assuming moderatorInfo is the Text node you want to update
                moderatorInfo.setText((gracze.get(listaGraczy.getSelectionModel().getSelectedIndex()).getModeratorCzatu() == null) ?
                        "Brak ostatniego Moderatora" :
                        ((listaGraczy.getSelectionModel().getSelectedItem()).getModeratorCzatu().toString())
                );

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
            if (listaGraczy.getSelectionModel().getSelectedItem() != null) {
                DatabaseManager.getInstance().setGracz(listaGraczy.getSelectionModel().getSelectedItem());
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
    void showCharacters(ActionEvent event) {
        try {
            if (listaGraczy.getSelectionModel().getSelectedItem() != null) {
                if (DatabaseManager.getInstance().getModeratorCzatu().getClass() == MistrzGry.class) {
                    DatabaseManager.getInstance().setGracz(listaGraczy.getSelectionModel().getSelectedItem());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.CHARACTERS));
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(new Scene(loader.load()));
                } else {
                    DatabaseManager.getInstance().setMessage(Messages.MGREQ);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.MESSAGE));
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.setScene(new Scene(loader.load()));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
