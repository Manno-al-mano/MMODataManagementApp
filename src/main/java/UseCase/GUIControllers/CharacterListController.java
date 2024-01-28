package UseCase.GUIControllers;

import Logic.Data.Game.Characters.Postac;
import Logic.Data.Users.Playerbase.Gracz;
import javafx.event.ActionEvent;
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
import java.util.ArrayList;
import java.util.List;

public class CharacterListController {

    @FXML
    private ListView<String> listaChar;

    private ObservableList<Postac> postaci;
    @FXML
    public void initialize() {
        postaci = FXCollections.observableArrayList(DatabaseManager.getInstance().getGracz().getPostaci());
        List<String> names= new ArrayList<>();
        for (Postac postac : postaci)
            names.add(postac.getImie()+", Level: " +postac.getLvl());
        ObservableList<String> observableList = FXCollections.observableArrayList(names);
        listaChar.setItems(observableList);
        listaChar.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void deleteCharacter(ActionEvent event) {

        Postac selected = postaci.get(listaChar.getSelectionModel().getSelectedIndex());
       DatabaseManager.getInstance().deleteCharacter(selected);
       postaci.remove(listaChar.getSelectionModel().getSelectedIndex());
        List<String> names= new ArrayList<>();
        for (Postac postac : postaci)
            names.add(postac.getImie()+", Level: " +postac.getLvl());
        ObservableList<String> observableList = FXCollections.observableArrayList(names);
        listaChar.setItems(observableList);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.MESSAGE));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(loader.load()));
        }catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FxmlNames.MAINMENU));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
