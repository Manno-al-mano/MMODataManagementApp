package UseCase.GUIControllers;

import Constants.FxmlNames;
import Logic.DatabaseOperations.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MessageController {

    @FXML
    private Button zamknij;

    @FXML
    private Label message;

    @FXML
    public void initialize() {
        message.setText(DatabaseManager.getInstance().getMessage());

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

}


