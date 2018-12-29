package demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("License Dialog Demo");
        primaryStage.setScene(scene);
        MainController mainController = fxmlLoader.getController();
//        mainController.setHostServices(getHostServices());
        mainController.setHostServices(getHostServices());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
