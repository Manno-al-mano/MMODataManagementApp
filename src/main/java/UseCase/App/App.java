package UseCase.App;

import Logic.DatabaseOperations.DatabaseManager;
import UseCase.GUIControllers.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModeratorList.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("My Application");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        DatabaseManager.getInstance().closeSessionFactory();
        super.stop();
        System.out.println("Zamykamy :)");
    }

    public static void start(String[] args) {
        launch(args);
    }



}
