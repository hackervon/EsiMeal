package sample;

import Noyau.ClientFidele;
import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXTextAreaSkin;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;

public class Sign extends Stage{
    Scene scene;
    Scene s;
    Stage e;
    public Sign(Scene s,Stage e){
        this.s = s;
        this.e=e;
        this.setTitle("EsiMeal");
        this.setResizable(false);
        scene = new Scene(createLayout(),500,600);
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
        bouton.setPrefSize(150,30);
        bouton.setTextFill(Color.BLACK);
        bouton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
        bouton.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        return bouton;
    }

    public HBox createFieldPassWordLayout(String name){
        HBox inf = new HBox();
        Label nameLabel = new Label(); nameLabel.setPrefSize(180,30);
        nameLabel.setText(name);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        JFXPasswordField nameText = new JFXPasswordField();  nameText.setPrefSize(180,30);
        nameText.setAlignment(Pos.CENTER_LEFT);
        inf.getChildren().addAll(nameLabel,nameText);
        inf.setSpacing(2);
        inf.setAlignment(Pos.CENTER);
        inf.setPadding(new Insets(2));
        return inf;
    }
    JFXTextField nomm = new JFXTextField();
    JFXTextField prenm = new JFXTextField();
    JFXTextField nTelephone = new JFXTextField();
    ListView npass = new ListView() ;
    JFXTextField adrOne = new JFXTextField();
    JFXTextField adrTwo = new JFXTextField();
    JFXTextField adrThree = new JFXTextField();


    JFXRadioButton etudY = new JFXRadioButton();
    JFXRadioButton etudN = new JFXRadioButton();

    public HBox createFieldCompLayout(String name){
        HBox inf = new HBox();
        Label nameLabel = new Label();
        nameLabel.setPrefSize(180,30);
        nameLabel.setText(name);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        JFXTextField nameText = new JFXTextField();
        nameText.setPrefSize(180,30);
        nameText.setAlignment(Pos.CENTER_LEFT);
        inf.getChildren().addAll(nameLabel,nameText);
        inf.setSpacing(2);
        inf.setAlignment(Pos.CENTER);
        inf.setPadding(new Insets(2));
        switch (name){
            case "Nom":
                nomm=nameText;
                break;
            case "Prenom":
                prenm=nameText;

                break;
            case "Numéro de Téléphone":
                nTelephone=nameText;
                break;
            case "Adr1":
                adrOne=nameText;
                break;
            case "Adr2":
                adrTwo=nameText;
                break;
            case "Adr3":
                adrThree=nameText;
                break;

        }
        return inf;
    }
    public HBox createFieldRadioLayout(String name,String choice1,String choice2){
        HBox inf = new HBox();

        JFXRadioButton stdY = new JFXRadioButton(choice1);
        JFXRadioButton stdN = new JFXRadioButton(choice2);
        Label nameLabel = new Label(); nameLabel.setPrefSize(180,30);
        nameLabel.setText(name);
        nameLabel.setAlignment(Pos.CENTER_LEFT);
        stdY.setPrefSize(80,30);
        stdN.setPrefSize(80,30);
        ToggleGroup group = new ToggleGroup();
        stdY.setToggleGroup(group);
        stdN.setToggleGroup(group);
        stdY.setAlignment(Pos.CENTER);
        stdN.setAlignment(Pos.CENTER);
        stdY.setSelected(true);
        inf.getChildren().addAll(nameLabel,stdY,stdN);
        inf.setSpacing(10);
        inf.setAlignment(Pos.CENTER);
        inf.setPadding(new Insets(2));
        etudN=stdN;
        etudY=stdY;
        return inf;
    }

    public GridPane createLayout() {
        GridPane contenu=new GridPane();
        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(25);
        RowConstraints r2 = new RowConstraints();
        r2.setPercentHeight(15);
        RowConstraints r3 = new RowConstraints();
        r3.setPercentHeight(60);
        contenu.getRowConstraints().addAll(r1,r2,r3);

        contenu.setPadding(new Insets(20,20,20,20));
        contenu.setAlignment(Pos.CENTER);

        GridPane title = new GridPane();
        Label app = new Label("ESIMEAL");
        app.setPrefSize(250,50);
        app.setFont(new Font("Verdana",20));
        app.setTextFill(Color.DARKGREEN);
        app.setAlignment(Pos.CENTER);
        title.getChildren().add(app);
        title.setAlignment(Pos.CENTER);


        FlowPane commandes = new FlowPane();
        commandes.setAlignment(Pos.CENTER);
        commandes.setHgap(20);
        ToggleGroup group = new ToggleGroup();
        RadioButton in =new RadioButton("Sign in");
        in.getStyleClass().remove("radio-button");
        in.getStyleClass().add("toggle-button");
        RadioButton up =new RadioButton("Sign up");
        up.getStyleClass().remove("radio-button");
        up.getStyleClass().add("toggle-button");
        in.setToggleGroup(group);
        up.setToggleGroup(group);
        commandes.getChildren().setAll(in,up);



        contenu.add(title,0,0);
        contenu.add(commandes,0,1);
        contenu.setAlignment(Pos.CENTER);
        contenu.setPadding(new Insets(30));
        contenu.setPrefSize(500,600);



        VBox signUp = new  VBox();
        signUp.setPrefSize(400,400);
        VBox infoSignUp = new VBox();
        HBox nom = createFieldCompLayout("Nom");
        HBox prenom = createFieldCompLayout("Prenom");
        HBox nTele = createFieldCompLayout("Numéro de Téléphone");
        HBox adr1 = createFieldCompLayout("Adr1");
        HBox adr2 = createFieldCompLayout("Adr2");
        HBox adr3 = createFieldCompLayout("Adr3");

        HBox stdent = createFieldRadioLayout("Etudiant","Oui","Non");
        infoSignUp.setSpacing(10);
        HBox btnnn = new HBox();
        HBox btn = new HBox();
        JFXButton signUpBtn = new JFXButton("Register");
        signUpBtn.setOnAction(event->{
            List<String> li = new ArrayList<String>();
            li.add(adrOne.getText());
            li.add(adrTwo.getText());
            li.add(adrThree.getText());
            System.out.println(nomm.getText());
            ClientFidele cF = new ClientFidele(nomm.getText(),prenm.getText(),nTelephone.getText(),etudY.isSelected(),li);
            int p=Main.esiMeal.client.size();
            if(p>1) npass.getItems().clear();
            npass.getItems().add("code fidelité est "+Integer.toString(p));
            Main.esiMeal.client.put(p,cF);
        });
        infoSignUp.getChildren().addAll(nom,prenom,nTele,adr1,adr2,adr3,stdent,npass);

        JFXButton signUpBtn1 = new JFXButton("retour");
        signUpBtn1.setOnAction(event -> {
        e.setScene(s); });

        signUpBtn.setTextFill(Color.BLACK);
        signUpBtn.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        signUpBtn.setPrefSize(100,20);
        btn.getChildren().addAll(signUpBtn,signUpBtn1);
        btn.setAlignment(Pos.CENTER);
        btn.setPadding(new Insets(10,0,0,0));
        HBox retBox = new HBox();
        retBox.setAlignment(Pos.CENTER);
        btnnn.getChildren().addAll(btn,retBox);
        signUp.getChildren().addAll(infoSignUp,btnnn);
        signUp.setSpacing(20);


        VBox signIn = new  VBox();
        signIn.setPrefSize(400,300);
        VBox infoSignIn = new VBox();
        HBox userName2 = createFieldCompLayout("UserName");
        HBox passw = createFieldCompLayout("Password");
        infoSignIn.getChildren().addAll(userName2,passw); infoSignIn.setAlignment(Pos.CENTER);
        infoSignIn.setSpacing(10);


        HBox btn2 = new HBox();
        JFXButton signInBtn = new JFXButton("Log in");
        signInBtn.setOnAction(e->{

        });
        signInBtn.setTextFill(Color.BLACK);
        signInBtn.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        signInBtn.setPrefSize(100,20);
        btn2.getChildren().add(signInBtn);
        btn2.setAlignment(Pos.BOTTOM_CENTER);
        btn2.setPadding(new Insets(100,0,0,0));

        signIn.getChildren().addAll(infoSignIn,btn2);
        signIn.setAlignment(Pos.CENTER);
        signIn.setSpacing(0);

        in.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent)
            {
                try{contenu.getChildren().remove(signUp); contenu.add(signIn,0,2);}catch (Exception e){}
            }} );
        up.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent)
            {
                try{contenu.getChildren().remove(signIn);contenu.add(signUp,0,2);}catch (Exception e){}
            }} );

        contenu.setAlignment(Pos.CENTER);
        contenu.add(signIn,0,2);
        in.setSelected(true);
        return contenu;
    }
}

