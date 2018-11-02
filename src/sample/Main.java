package sample;

import Noyau.ClientFidele;
import Noyau.EsiMeal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static EsiMeal esiMeal = new EsiMeal();
    public static CGCheck stage0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Gerant stage1 = new Gerant(esiMeal);
        stage0 = new CGCheck(esiMeal);
        Client stage = new Client();
        stage0.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
