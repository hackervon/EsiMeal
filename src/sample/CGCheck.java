package sample;

import Noyau.EsiMeal;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class CGCheck extends Stage{
    EsiMeal esiMeal;
    public CGCheck(EsiMeal e){
        esiMeal = e;
        this.setTitle("EsiMeal");
        this.setResizable(false);
        Label etiq=MessageBlock("Etes vous un Client ou un Gérant ?");
        Button clientBouton = createButton("Client");
        Button gerantBouton=createButton("Gérant");
        Button[] boutons={clientBouton,gerantBouton};
        Scene scene = new Scene(createLayout(etiq, boutons),600,400);
        scene.getStylesheets().add("stylesheet.css");
        this.setScene(scene);
    }

    public Label MessageBlock(String s){
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana",18));
        etiquette.setTextFill(Color.BLACK);
        etiquette.setLineSpacing(10);
        return etiquette;
    }
    public Button createButton(String s) {
        JFXButton bouton = new JFXButton(s);
        bouton.setPrefSize(120,30);
        bouton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        if("Client".equals(s)){
            bouton.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent actionEvent)
                {
                    Client stage = new Client();
                    CGCheck.this.hide();
                    stage.show();
                }} );
        }
        else{
            bouton.setOnAction(new EventHandler<ActionEvent>(){
                public void handle(ActionEvent actionEvent) {

                    Gerant stage = new Gerant(esiMeal);
                    CGCheck.this.hide();
                    stage.show();
                }});
        }
        return bouton;
    }
    public VBox createLayout(Label ecran, Button[] boutons) {
        VBox contenu=new VBox(50);
        contenu.setPadding(new Insets(20,20,20,20));
        contenu.setAlignment(Pos.CENTER);
        FlowPane commandes = new FlowPane();
        commandes.setAlignment(Pos.CENTER);
        commandes.setHgap(50);
        commandes.getChildren().setAll(boutons);
        contenu.getChildren().setAll(ecran,commandes);
        return contenu;
    }
}

