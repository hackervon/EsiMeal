package sample;

import Noyau.*;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.ZoneId;
import  java.util.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Client extends Stage{
    Scene mainScene = new Scene(new GridPane(),900,600);
    Scene menusScene = new Scene(new GridPane(),900,600);
    Scene menuScene = new Scene(new GridPane(),900,600);
    Scene infScene = new Scene(new GridPane(),900,600);
    List<ArrayList<JFXRadioButton>>  mett = new ArrayList<>();
    List<GridPane> menus = new ArrayList<GridPane>() ;
    List<JFXButton> menusButtonn = new ArrayList<JFXButton>() ;

    JFXTextField nomm = new JFXTextField();
    JFXTextField prenm = new JFXTextField();
    JFXTextField nTelephone = new JFXTextField();
    JFXRadioButton etud = new JFXRadioButton();
    JFXRadioButton selectt = new JFXRadioButton();
    JFXRadioButton saveAdr = new JFXRadioButton();
    JFXTextField addrss = new JFXTextField();
    JFXComboBox adreesCombo = new JFXComboBox();
    JFXComboBox evtCombo = new JFXComboBox();
    JFXComboBox tableCombo = new JFXComboBox();
    JFXTextField nbPerson = new JFXTextField();
    JFXTimePicker hourConsom = new JFXTimePicker();
    JFXDatePicker dateConsom = new JFXDatePicker();

    String typeFenetre;
    public Client(){
        this.setTitle("EsiMeal");
        GridPane main = createLayout();
        mainScene.setRoot(main);
        mainScene.getStylesheets().add("Client.css");
        menuScene.getStylesheets().add("Client.css");
        menusScene.getStylesheets().add("Client.css");
        infScene.getStylesheets().add("Client.css");
        this.setScene(mainScene);
    }

    public GridPane createMenuLayout(){

        GridPane main = new GridPane();

        main.setPrefSize(900, 600);
        // Dividing the GridPane
        ColumnConstraints back = new ColumnConstraints();
        back.setPercentWidth(25);
        ColumnConstraints mn = new ColumnConstraints();
        mn.setPercentWidth(60);
        ColumnConstraints afterMn = new ColumnConstraints();
        afterMn.setPercentWidth(15);
        RowConstraints title = new RowConstraints();
        title.setPercentHeight(20);
        RowConstraints maiin = new RowConstraints();
        maiin.setPercentHeight(70);
        RowConstraints tale = new RowConstraints();
        tale.setPercentHeight(10);
        main.getColumnConstraints().addAll(back,mn,afterMn);
        main.getRowConstraints().addAll(title,maiin,tale);
        // Styling the GridPane
        HBox retBox = new HBox();
        Button ret = createRetourNext("Retour");
        ret.setOnAction(event -> menuEventRetour());
        retBox.getChildren().add(ret);
        retBox.setAlignment(Pos.CENTER);
        main.add(retBox, 0, 0);

        HBox nextBox = new HBox();
        Button next = createRetourNext("Suivant");
        next.setOnAction(event -> menuEventConf());
        nextBox.getChildren().add(next);
        nextBox.setAlignment(Pos.CENTER);
        main.add(nextBox, 1, 2);

        VBox menu = new VBox();
        HBox repasBox = new HBox();
        RadioButton repas =new RadioButton("Repas");
        repas.setFont(new Font(15));
        repas.getStyleClass().remove("radio-button");
        repas.getStyleClass().add("toggle-button");
        repas.setPrefSize(230,40);
        repasBox.getChildren().add(repas);
        repasBox.setAlignment(Pos.CENTER);

        HBox boissonBox = new HBox();
        RadioButton boisson =new RadioButton("Boisson");
        boisson.getStyleClass().remove("radio-button");
        boisson.getStyleClass().add("toggle-button");
        boisson.setFont(new Font(15));
        boisson.setPrefSize(230,40);
        boissonBox.getChildren().add(boisson);
        boissonBox.setAlignment(Pos.CENTER);

        ToggleGroup mets = new ToggleGroup();
        boisson.setToggleGroup(mets);
        repas.setToggleGroup(mets);
        menu.getChildren().addAll(repasBox,boissonBox);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(50);
        main.add(menu,0,1);
        HBox titleBox = new HBox();
        Label titre = new Label("Mets");
        titre.setAlignment(Pos.CENTER);
        titre.setFont(new Font( 25));
        titre.setTextFill(Color.DARKGREEN);
        titleBox.getChildren().add(titre);
        titleBox.setAlignment(Pos.CENTER);
        main.add(titleBox, 1, 0);
        FlowPane mainPaneRepas = new FlowPane();
        for(int i=0;i<Main.esiMeal.metsDisponible.size();i++) if(Main.esiMeal.metsDisponible.get(i) instanceof Repas) mainPaneRepas.getChildren().add(createMet(i));
        mainPaneRepas.setPadding(new Insets(10));
        mainPaneRepas.setHgap(20);
        mainPaneRepas.setVgap(10);
        mainPaneRepas.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        FlowPane mainPaneBoisson = new FlowPane();
        for(int i=0;i<Main.esiMeal.metsDisponible.size();i++)  if(Main.esiMeal.metsDisponible.get(i) instanceof Boisson)  mainPaneBoisson.getChildren().add(createMet(i));
        mainPaneBoisson.setPadding(new Insets(10));
        mainPaneBoisson.setHgap(20);
        mainPaneBoisson.setVgap(10);
        mainPaneBoisson.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));

        main.add(mainPaneRepas,1,1);
        repas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{if(repas.isSelected()){
                    main.getChildren().remove(mainPaneBoisson);
                    main.add(mainPaneRepas,1,1);
                }}catch (Exception e){}
            }
        });
        boisson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{if(boisson.isSelected()){
                    main.getChildren().remove(mainPaneRepas);
                    main.add(mainPaneBoisson,1,1);}}catch (Exception e){}
            }
        });
        repas.setSelected(true);
        return main;
    }

    //region menuEventRetour
    public void menuEventRetour(){
        this.setScene(menusScene);
    }

    public void menuEventConf(){
        this.setScene(menusScene);
    }

    //endregion

    public GridPane createMenusLayout(){
        int nPers = 5;
        GridPane main = new GridPane();
        main.setPrefSize(900, 600);
        // Dividing the GridPane
        ColumnConstraints back = new ColumnConstraints();
        back.setPercentWidth(25);
        ColumnConstraints mn = new ColumnConstraints();
        mn.setPercentWidth(60);
        ColumnConstraints afterMn = new ColumnConstraints();
        afterMn.setPercentWidth(15);
        RowConstraints title = new RowConstraints();
        title.setPercentHeight(20);
        RowConstraints maiin = new RowConstraints();
        maiin.setPercentHeight(70);
        RowConstraints tale = new RowConstraints();
        tale.setPercentHeight(10);
        main.getColumnConstraints().addAll(back,mn,afterMn);
        main.getRowConstraints().addAll(title,maiin,tale);
        // Styling the GridPane
        HBox retBox = new HBox();
        Button ret = createRetourNext("Retour");
        ret.setOnAction(event -> menusEventRetour());
        retBox.getChildren().add(ret);
        retBox.setAlignment(Pos.CENTER);

        HBox confBox = new HBox();
        Button conf = createRetourNext("Confirmer");
        conf.setOnAction(event -> menusEventConf());
        confBox.getChildren().add(conf);
        confBox.setAlignment(Pos.CENTER);

        main.add(retBox, 0, 0);
        main.add(confBox, 1, 2);

        HBox titleBox = new HBox();
        Label titre = new Label("Menu");
        titre.setAlignment(Pos.CENTER);
        titre.setFont(new Font( 25));
        titre.setTextFill(Color.DARKGREEN);
        titleBox.getChildren().add(titre);
        titleBox.setAlignment(Pos.CENTER);
        main.add(titleBox, 1, 0);
        FlowPane mainPaneRepas = new FlowPane();
        for (int i = 0; i <Integer.parseInt(nbPerson.getText()) ; i++) {
            mainPaneRepas.getChildren().add(createMenuButtonLayout());
            menus.add(createMenuLayout());
            mett.add((ArrayList<JFXRadioButton>) jrb.clone());
            jrb.clear();
        }
        mainPaneRepas.setPadding(new Insets(10));
        mainPaneRepas.setHgap(20);
        mainPaneRepas.setVgap(10);
        mainPaneRepas.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        mainPaneRepas.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        main.add(mainPaneRepas,1,1);
        return main;
    }

    //region EventHandler Menus
    public void menusEventRetour(){
        this.setScene(infScene);
    }

    public void menusEventConf(){
        switch (typeFenetre){
            case "surPlace":
                try {
                    List<Noyau.Menu> menuList=new ArrayList<Noyau.Menu>();

                    for(ArrayList<JFXRadioButton> b:mett){
                        List<Mets> metsList=new ArrayList<Mets>();
                        for(JFXRadioButton c:b){
                            if(c.isSelected()){
                                System.out.println("hello");
                                metsList.add(Main.esiMeal.metsDisponible.get(b.indexOf(c)));
                            }
                        }
                        menuList.add(new Noyau.Menu(metsList));
                        metsList=new ArrayList<Mets>();
                    }

                    TypeTable t=TypeTable.Interieur;
                    switch (tableCombo.getPromptText()){
                        case "A l'éxtériuere":
                            t=TypeTable.Exterieur;
                            break;
                        case "A l'intérieure":
                            t=TypeTable.Interieur;
                            break;
                    }
                    LocalDate localDate = dateConsom.getValue();
                    try {
                        Noyau.CommandeSurPlace cm = new Noyau.CommandeSurPlace(new Noyau.Client(nomm.getText(), prenm.getText(), nTelephone.getText(), etud.isSelected()), Integer.parseInt(nbPerson.getText()), localDate, menuList, t);
                        cmd=cm;

                    }
                    catch (Exception e){}
                    Main.esiMeal.RecevoirCommande(cmd);
                }
                catch (nonValidCommandException e){
                }
                break;

            case "Livre":
                try {
                    List<Noyau.Menu> menuList=new ArrayList<Noyau.Menu>();

                    for(ArrayList<JFXRadioButton> b:mett){
                        List<Mets> metsList=new ArrayList<Mets>();
                        for(JFXRadioButton c:b){
                            if(c.isSelected()){
                                metsList.add(Main.esiMeal.metsDisponible.get(b.indexOf(c)));
                            }
                        }
                        menuList.add(new Noyau.Menu(metsList));
                        metsList=new ArrayList<Mets>();
                    }
                    String adr;
                    if(selectt.isSelected()==false){
                        adr=addrss.getText();
                    }
                    else{
                        adr=adreesCombo.getPromptText();
                    }
                    LocalDate localDate = dateConsom.getValue();
                    Noyau.CommandeLivre cm = new Noyau.CommandeLivre(new Noyau.Client(nomm.getText(), prenm.getText(), nTelephone.getText(), etud.isSelected()), Integer.parseInt(nbPerson.getText()), localDate, menuList, adr);
                    cmd=cm;
                    Main.esiMeal.RecevoirCommande(cmd);
                }
                catch (nonValidCommandException e){
                }
                break;
            case "Event":break;
        }

        this.setScene(mainScene);
    }

    //endregion


    public HBox createMenuButtonLayout(){
        HBox menu = new HBox();
        menu.setPrefSize(150,150);
        JFXButton text =new JFXButton("Menu" + menus.size());
        text.setPrefSize(150,150);
        text.setAlignment(Pos.CENTER);
        menusButtonn.add(text);
        int indice=menusButtonn.indexOf(text);

        text.setOnAction(event -> {
            menuScene.setRoot(menus.get(indice));
            this.setScene(menuScene);
        });
        menu.getChildren().add(text);
        menu.setPadding(new Insets(5));
        menu.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW,new CornerRadii(5),Insets.EMPTY)));
        menu.setAlignment(Pos.CENTER);
        return menu;
    }

    public Label MessageBlock(String s){
        Label etiquette = new Label(s);
        etiquette.setAlignment(Pos.CENTER);
        etiquette.setFont(Font.font("Verdana",18));
        etiquette.setTextFill(Color.BLACK);
        etiquette.setLineSpacing(10);
        etiquette.setPrefSize(150,40);
        return etiquette;
    }
    ArrayList<JFXRadioButton> jrb = new ArrayList<JFXRadioButton>();


    public HBox createMet(int index){
        HBox main = new HBox();
        JFXRadioButton mainRad = new JFXRadioButton();
        jrb.add(mainRad);
        VBox writing = new VBox();
        double prix = 5;
        Label price = new Label("Prix : " + Double.toString(prix));
        price.setPadding(new Insets(0,0,0,20));
        price.setAlignment(Pos.CENTER);
        price.setTextFill(Color.GRAY);
        MenuBar menuBar = new MenuBar();Menu text = new Menu(Main.esiMeal.metsDisponible.get(index).getNom());
        menuBar.setStyle("-fx-background-color: Transparent;");
        MenuItem ingr = new MenuItem("Ingredient");
        MenuItem cal = new MenuItem("Calories");
        MenuItem dis = new MenuItem("Disponibilité");
        menuBar.getMenus().add(text);
        text.getItems().addAll(ingr,cal,dis);
        Alert popup = new Alert(Alert.AlertType.NONE,"Meat",ButtonType.CANCEL);
        popup.setContentText("Ingredients");
        popup.setTitle(text.getText());
        ingr.setOnAction(event -> popup.showAndWait());
        writing.getChildren().addAll(menuBar,price);
        main.getChildren().addAll(mainRad,writing);
        main.setPrefSize(250,50);
        main.setAlignment(Pos.CENTER);
        main.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW,new CornerRadii(5),Insets.EMPTY)));
        return main;
    }

    public Button createRetourNext(String s) {
        JFXButton bouton = new JFXButton(s);
        bouton.setPrefSize(150,30);
        bouton.setTextFill(Color.BLACK);
        bouton.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        //bouton.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,new CornerRadii(5),Insets.EMPTY)));
        return bouton;
    }

    public Button createButton(String s) {
        JFXButton bouton = new JFXButton(s);
        bouton.setPrefSize(150,30);
        bouton.setTextFill(Color.WHITE);
        bouton.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.EMPTY)));
        bouton.setBackground(new Background(new BackgroundFill(Color.GREEN,new CornerRadii(50),Insets.EMPTY)));
        return bouton;
    }

    public HBox createDateLayout(String name,JFXDatePicker date){
        HBox nDate = new HBox();
        Label tDate = new Label(name); tDate.setPrefSize(180,30);
        date.setPrefSize(180,30);
        nDate.getChildren().addAll(tDate,date);
        nDate.setAlignment(Pos.CENTER);
        nDate.setSpacing(10);
        return nDate;
    }

    public HBox createHourLayout(String name,JFXTimePicker date){
        HBox nDate = new HBox();
        Label tDate = new Label(name); tDate.setPrefSize(180,30);
        date.setPrefSize(180,30);
        nDate.getChildren().addAll(tDate,date);
        nDate.setAlignment(Pos.CENTER);
        nDate.setSpacing(10);
        return nDate;
    }

    public HBox createInfChoiceLayout(String name,String[] choices,JFXComboBox<String> nameText){
        HBox inf = new HBox();
        Label nameLabel = new Label(); nameLabel.setPrefSize(180,30);
        nameLabel.setText(name); nameText.setPrefSize(180,30);
        for (int i = 0; i < choices.length; i++) {
            nameText.getItems().add(choices[i]);
        }
        nameText.setValue(choices[0]);
        inf.getChildren().addAll(nameLabel,nameText);
        inf.setSpacing(5);
        inf.setPadding(new Insets(2));
        return inf;
    }

    public HBox createInfChoiceAdressLayout(String[] choices,JFXRadioButton nameRad,JFXTextField nm,JFXRadioButton nameRad3,JFXComboBox<String> nameText){
        HBox inf = new HBox();
        VBox all = new VBox();
        HBox select = new HBox();
        Label nameLabel = new Label("Sélection d'Addresse"); nameLabel.setPrefSize(180,30);
        nameRad.setText("nouveau");
        JFXRadioButton nameRad2 = new JFXRadioButton("favourie");
        ToggleGroup sel = new ToggleGroup();
        nameRad.setToggleGroup(sel);
        nameRad.setSelected(true);
        nameRad2.setToggleGroup(sel);
        select.getChildren().addAll(nameLabel,nameRad,nameRad2);
        select.setSpacing(20);
        select.setSpacing(5);
        select.setPadding(new Insets(2));
        HBox add = new HBox();
        add.setSpacing(20);
        Label nameLabel2 = new Label("Adresse"); nameLabel2.setPrefSize(180,30);
        add.setSpacing(5);
        add.setPadding(new Insets(2));
        add.getChildren().addAll(nameLabel2,nm);

        HBox save = new HBox();
        save.setSpacing(20);
        Label nameLabel3 = new Label("Enregistrer l'Adresse"); nameLabel3.setPrefSize(180,30);
        save.setPadding(new Insets(0,20,0,0));
        nameRad3.setText("OUI");

        JFXRadioButton nameRad4 = new JFXRadioButton("NON");

        ToggleGroup sel2 = new ToggleGroup();
        nameRad3.setToggleGroup(sel2);
        nameRad3.setSelected(true);
        nameRad4.setToggleGroup(sel2);

        save.setSpacing(5);
        save.setPadding(new Insets(2));
        save.getChildren().addAll(nameLabel3,nameRad3,nameRad4);
        HBox list = new HBox();
        list.setSpacing(20);
        Label nameLabel4 = new Label("Adresse"); nameLabel4.setPrefSize(180,30);
        list.setPadding(new Insets(0,20,0,0));
        nameText.setPrefSize(180,30);
        for (int i = 0; i < choices.length; i++) {
            nameText.getItems().add(choices[i]);
        }
        /*
        if(nameRad.isSelected()){nameText.setDisable(true);nameRad3.setDisable(false);nameLabel4.setDisable(false);nm.setDisable(false);}else {nameText.setDisable(false);nameRad3.setDisable(true);nameLabel4.setDisable(true);nm.setDisable(true);}

        nameRad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameRad.isSelected()){nameText.setDisable(true);}else {nameText.setDisable(false);nameRad3.setDisable(true);nameLabel4.setDisable(true);nm.setDisable(true);}
            }
        });

        nameRad2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(nameRad.isSelected()){nameText.setDisable(false);}else {nameText.setDisable(true);nameRad3.setDisable(false);nameLabel4.setDisable(false);nm.setDisable(false);}
            }
        });*/
        nameText.setValue(choices[0]);
        list.setSpacing(5);
        list.setPadding(new Insets(2));
        list.getChildren().addAll(nameLabel4,nameText);
        all.getChildren().addAll(select,add,save,list);
        inf.getChildren().add(all);
        all.setSpacing(5);
        all.setSpacing(10);
        all.setPadding(new Insets(5));
        inf.setPadding(new Insets(5));
        return inf;
    }

    public HBox createInfLayout(String name,JFXTextField nameText){
        HBox inf = new HBox();
        Label nameLabel = new Label(); nameLabel.setPrefSize(180,30);
        nameLabel.setText(name);
        nameText.setPrefSize(180,30);
        //value = nameText.getText();
        inf.getChildren().addAll(nameLabel,nameText);
        inf.setSpacing(5);
        inf.setPadding(new Insets(2));
        return inf;

    }

    public HBox createFieldRadioLayout(String name,JFXRadioButton stdY){
        HBox inf = new HBox();
        stdY.setText("OUI");
        JFXRadioButton stdN = new JFXRadioButton("NON");
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
        return inf;
    }

    public GridPane createSurPlaceLayout() {
        GridPane scene = new GridPane();
        scene.setPrefSize(900, 600);
        for (int i = 0; i < 11; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(9);
            scene.getRowConstraints().add(row);
        }
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        scene.getColumnConstraints().add(col1);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        scene.getColumnConstraints().add(col2);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        scene.getColumnConstraints().add(col3);
        HBox retBox = new HBox();
        Button ret = createRetourNext("Retour");
        ret.setOnAction(event -> retSurPlace());
        retBox.getChildren().add(ret);
        retBox.setAlignment(Pos.CENTER);
        scene.add(retBox, 0, 0);
        HBox titleBox = new HBox();
        Label title = new Label("Commande Sur Place");
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(20));
        title.setTextFill(Color.DARKGREEN);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        scene.add(titleBox, 1, 1);
        HBox nom = createInfLayout("Nom",nomm);
        nom.setAlignment(Pos.CENTER);
        scene.add(nom, 1, 2);
        HBox pnom = createInfLayout("Prenom",prenm);
        pnom.setAlignment(Pos.CENTER);
        scene.add(pnom, 1, 3);
        HBox nTele = createInfLayout("Numéro de Téléphone",nTelephone);
        HBox etd = createFieldRadioLayout("Etudiant",etud);
        scene.add(etd,1,4);
        nTele.setAlignment(Pos.CENTER);
        scene.add(nTele, 1, 5);
        HBox nPer = createInfLayout("Nombre de Personnes",nbPerson);
        nPer.setAlignment(Pos.CENTER);
        scene.add(nPer, 1, 6);
        String[] choices = {"A l'éxtériuere", "A l'intérieure"};
        HBox addr = createInfChoiceLayout("Type de Table", choices,tableCombo);
        scene.add(addr, 1, 7);
        addr.setAlignment(Pos.CENTER);

        HBox nTime = createHourLayout("Heure de Consomation",hourConsom);
        scene.add(nTime,1,9);

        HBox nDate = createDateLayout("Jour de Consomation",dateConsom);
        scene.add(nDate,1,8);

        HBox nextBox = new HBox();
        Button next = createRetourNext("Suivant");
        next.setOnAction(event -> nextSurPlace());
        nextBox.getChildren().add(next);
        nextBox.setAlignment(Pos.CENTER);
        scene.add(nextBox, 2, 10);
        scene.setAlignment(Pos.CENTER);
        return scene;
    }
    Command cmd;
    //region EventHandlers in SurPlace
    public void retSurPlace(){
        this.setScene(mainScene);
    }
    public void nextSurPlace()
    {
        typeFenetre="surPlace";

        menusScene.setRoot(createMenusLayout());
        this.setScene(menusScene);
    }
    //endregion

    public GridPane createLivreLayout(){



        GridPane scene = new GridPane();
        scene.setPrefSize(900,600);

        for (int i = 0; i < 7; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(9);
            scene.getRowConstraints().add(row);
        }
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(36);
        scene.getRowConstraints().add(row1);
        for (int i = 0; i < 3; i++) {
            RowConstraints row2 = new RowConstraints();
            row2.setPercentHeight(9);
            scene.getRowConstraints().add(row2);
        }

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        scene.getColumnConstraints().add(col1);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        scene.getColumnConstraints().add(col2);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        scene.getColumnConstraints().add(col3);

        HBox retBox = new HBox();
        Button ret2 = createRetourNext("Retour");
        ret2.setOnAction(event -> retLivre());
        retBox.getChildren().add(ret2);
        retBox.setAlignment(Pos.CENTER);
        scene.add(retBox,0,0);
        HBox titleBox = new HBox();
        Label title = new Label("Commande à Livré");
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(20));
        title.setTextFill(Color.DARKGREEN);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        scene.add(titleBox,1,1);
        HBox nom = createInfLayout("Nom",nomm);
        nom.setAlignment(Pos.CENTER);
        scene.add(nom,1,2);
        HBox pnom = createInfLayout("Prenom",prenm);
        pnom.setAlignment(Pos.CENTER);
        scene.add(pnom,1,3);
        HBox nTele = createInfLayout("Numéro de Téléphone",nTelephone);
        nTele.setAlignment(Pos.CENTER);
        HBox etd = createFieldRadioLayout("Etudiant",etud);
        scene.add(etd,1,4);
        scene.add(nTele,1,5);
        HBox nPer = createInfLayout("Nombre de Personnes",nbPerson);
        nPer.setAlignment(Pos.CENTER);
        scene.add(nPer,1,6);
        String[] choices = {"Adresse1","Adresse2"};
        HBox addr = createInfChoiceAdressLayout(choices,selectt,addrss,saveAdr,adreesCombo);
        scene.add(addr,1,7);

        HBox nTime = createHourLayout("Heure de Consomation",hourConsom);
        scene.add(nTime,1,9);

        HBox nDate = createDateLayout("Jour de Consomation",dateConsom);
        scene.add(nDate,1,8);

        addr.setAlignment(Pos.CENTER);
        HBox nextBox = new HBox();
        Button next2 = createRetourNext("Suivant");
        next2.setOnAction(event -> nextLivre());
        nextBox.getChildren().add(next2);
        nextBox.setAlignment(Pos.CENTER);
        scene.add(nextBox,2,10);
        scene.setAlignment(Pos.CENTER);

        return scene;
    }

    //region EventHandlers in Livre
    public void retLivre(){
        this.setScene(mainScene);
    }
    public void nextLivre()
    {
        typeFenetre="Livre";
        List<Noyau.Menu> menuList=new ArrayList<Noyau.Menu>();
        for(ArrayList<JFXRadioButton> b:mett){
            List<Mets> metsList=new ArrayList<Mets>();
            for(JFXRadioButton c:b){
                if(c.isSelected()){
                    metsList.add(Main.esiMeal.metsDisponible.get(b.indexOf(c)));
                }
            }
            menuList.add(new Noyau.Menu(metsList));
            metsList.clear();
        }

        LocalDate localDate = dateConsom.getValue();
        String adr;
        if(selectt.isSelected()) {
            adr=adreesCombo.getPromptText();

        }
        else{
            adr=addrss.getText();
        }

        try {
            Noyau.CommandeLivre cm = new Noyau.CommandeLivre(new Noyau.Client(nomm.getText(), prenm.getText(), nTelephone.getText(), etud.isSelected()), Integer.parseInt(nbPerson.getText()), localDate, menuList,adr);
            cmd=cm;
        }
        catch (Exception e){

        }
        menusScene.setRoot(createMenusLayout());
        this.setScene(menusScene);
    }
    //endregion

    public GridPane createEvenementLayout(){
        GridPane scene = new GridPane();
        scene.setPrefSize(900, 600);

        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(10);
            scene.getRowConstraints().add(row);
        }
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        scene.getColumnConstraints().add(col1);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        scene.getColumnConstraints().add(col2);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        scene.getColumnConstraints().add(col3);

        HBox retBox = new HBox();
        Button ret = createRetourNext("Retour");
        ret.setOnAction(event -> retEvt());
        retBox.getChildren().add(ret);
        retBox.setAlignment(Pos.CENTER);
        scene.add(retBox, 0, 0);
        HBox titleBox = new HBox();
        Label title = new Label("Commande d'un Evenement");
        title.setAlignment(Pos.CENTER);
        title.setFont(new Font(23));
        title.setTextFill(Color.DARKGREEN);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        scene.add(titleBox, 1, 1);
        HBox nom = createInfLayout("Nom",nomm);
        nom.setAlignment(Pos.CENTER);
        scene.add(nom, 1, 2);
        HBox pnom = createInfLayout("Prenom",prenm);
        pnom.setAlignment(Pos.CENTER);
        scene.add(pnom, 1, 3);
        HBox nTele = createInfLayout("Numéro de Téléphone",nTelephone);
        nTele.setAlignment(Pos.CENTER);
        scene.add(nTele, 1, 5);
        HBox nPer = createInfLayout("Nombre de Personnes",nbPerson);
        nPer.setAlignment(Pos.CENTER);
        scene.add(nPer, 1, 6);
        String[] choices = {"One", "Two"};
        HBox addr = createInfChoiceLayout("Type de l'Evenement", choices,evtCombo);
        scene.add(addr, 1, 7);
        addr.setAlignment(Pos.CENTER);

        HBox nDate = createDateLayout("Jour de Réservation",dateConsom);
        scene.add(nDate,1,8);

        HBox etd = createFieldRadioLayout("Etudiant",etud);
        scene.add(etd,1,4);

        HBox nextBox = new HBox();
        Button next = createRetourNext("Suivant");
        next.setOnAction(event -> nextLivre());
        nextBox.getChildren().add(next);
        nextBox.setAlignment(Pos.CENTER);
        scene.add(nextBox, 2, 9);
        scene.setAlignment(Pos.CENTER);
        return scene;
    }

    //region EventHandlers in Evt
    public void retEvt(){
        this.setScene(mainScene);
    }
    public void nextEvt()
    {
        typeFenetre="Event";
        List<Noyau.Menu> menuList=new ArrayList<Noyau.Menu>();
        LocalDate localDate = dateConsom.getValue();
        String adr;
        try {
            //Noyau.Event cm = new Noyau.Event(new Noyau.Client(nomm.getText(), prenm.getText(), nTelephone.getText(), etud.isSelected()), Integer.parseInt(nbPerson.getText()), localDate, menuList,new Addresse(adr,0));
            //cmd=cm;
        }
        catch (Exception e){

        }
        menusScene.setRoot(createMenusLayout());
        this.setScene(menusScene);
    }
    //endregion

    public HBox createCommandLayout(){
        HBox command = new HBox();
        command.setPrefSize(700,100);
        command.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE,new CornerRadii(5),Insets.EMPTY)));
        Button confm = new Button();

        return command;
    }


    public GridPane createLayout() {
        GridPane contenu=new GridPane();
        contenu.setPadding(new Insets(5));
        contenu.setAlignment(Pos.TOP_CENTER);
        // Devision de GridPane
        RowConstraints nTitle = new RowConstraints();
        nTitle.setPercentHeight(15);
        RowConstraints nButtons = new RowConstraints();
        nButtons.setPercentHeight(10);
        RowConstraints nCanvas = new RowConstraints();
        nCanvas.setPercentHeight(60);
        RowConstraints nDown = new RowConstraints();
        nDown.setPercentHeight(15);
        ColumnConstraints nMarginLeft = new ColumnConstraints();
        nMarginLeft.setPercentWidth(20);
        ColumnConstraints nMain = new ColumnConstraints();
        nMain.setPercentWidth(60);
        ColumnConstraints nMarginRight = new ColumnConstraints();
        nMarginRight.setPercentWidth(20);
        contenu.getRowConstraints().addAll(nTitle,nButtons,nCanvas,nDown);
        contenu.getColumnConstraints().addAll(nMarginLeft,nMain,nMarginRight);

        // Le titre de la page
        HBox title = new HBox();
        Label textTitle = new Label("HOME");
        textTitle.setTextFill(Color.GREEN);
        textTitle.setFont(new Font(20));
        title.getChildren().add(textTitle);
        title.setAlignment(Pos.CENTER);
        contenu.add(title,1,0);


        HBox retBox = new HBox();
        Button ret = createRetourNext("Login");
        ret.setOnAction(event -> this.setScene(new Sign(mainScene,this).scene));
        retBox.getChildren().add(ret);
        retBox.setAlignment(Pos.CENTER);
        contenu.add(retBox, 2, 0);
        HBox rettBox = new HBox();
        Button rett = createRetourNext("Gerant mode");
        rett.setOnAction(event -> this.setScene(new Gerant(Main.esiMeal).scene));
        rettBox.getChildren().add(rett);
        rettBox.setAlignment(Pos.CENTER);
        contenu.add(rettBox, 0, 0);
        //First View
        GridPane main = new GridPane();
        main.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        main.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        Label textBien = new Label("Bienvenue Client!");
        textBien.setFont(new Font(15));
        textBien.setAlignment(Pos.CENTER);
        main.getChildren().add(textBien);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(20));
        contenu.add(main,1,2);

        //Les buttons de la pages
        HBox buttons = new HBox();
        HBox cmdBtn = new HBox();
        GridPane saisirLayout = saisirCmdLayout();
        GridPane cmdLayout = cmndLayout();
        ToggleButton cmd = new ToggleButton("Commande en cours");
        cmd.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        cmdBtn.getChildren().add(cmd);
        cmd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{contenu.getChildren().remove(main);contenu.getChildren().remove(saisirLayout);}catch (Exception e){

                }finally {
                    contenu.add(cmdLayout,1,2);
                }

            }
        });
        HBox saisirBtn = new HBox();
        ToggleButton saisir = new ToggleButton("Saisir une commande");
        saisir.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        saisir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{contenu.getChildren().remove(main);contenu.getChildren().remove(cmdLayout);}
                catch (Exception e){}finally {
                    contenu.add(saisirLayout,1,2);
                }


            }
        });
        saisirBtn.getChildren().add(saisir);
        ToggleGroup buttonsGroup = new ToggleGroup();
        saisir.setToggleGroup(buttonsGroup);
        cmd.setToggleGroup(buttonsGroup);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(5));
        buttons.getChildren().addAll(cmdBtn,saisirBtn);
        buttons.setSpacing(20);
        contenu.add(buttons,1,1);

        return contenu;
    }


    public GridPane saisirCmdLayout(){
        GridPane main = new GridPane();
        main.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,new CornerRadii(5),Insets.EMPTY)));
        main.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(5));
        JFXButton cmdSurP = new JFXButton("Commande Sur Place");
        cmdSurP.setPrefSize(350,50);
        cmdSurP.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        cmdSurP.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        cmdSurP.setOnAction(event -> cmdSurPlaceEvent_Main());
        JFXButton cmdLivre = new JFXButton("Commande Livré");
        cmdLivre.setPrefSize(350,50);
        cmdLivre.setOnAction(event -> cmdLivreEvent_Main());
        cmdLivre.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        cmdLivre.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        JFXButton cmdEvt = new JFXButton("Commande D'un Evenement");
        cmdEvt.setPrefSize(350,50);
        cmdEvt.setOnAction(event -> cmdEvtEvent_Main());
        cmdEvt.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        cmdEvt.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        buttons.getChildren().addAll(cmdSurP,cmdLivre,cmdEvt);
        buttons.setPrefWidth(350);
        buttons.setAlignment(Pos.CENTER);
        main.getChildren().add(buttons);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(20));
        return main;
    }

    //region Events of Main Buttons
    public void cmdSurPlaceEvent_Main(){
        infScene.setRoot(createSurPlaceLayout());
        this.setScene(infScene);
    }

    public void cmdLivreEvent_Main(){
        infScene.setRoot(createLivreLayout());
        this.setScene(infScene);
    }

    public void cmdEvtEvent_Main(){
        infScene.setRoot(createEvenementLayout());
        this.setScene(infScene);
    }

    //endregion


    public HBox createCommand(String name){
        HBox main = new HBox();
        main.setAlignment(Pos.CENTER);
        JFXButton mainBtn = new JFXButton(name);
        mainBtn.setPrefSize(320,50);
        mainBtn.setTextFill(Color.WHITE);
        mainBtn.setBackground(new Background(new BackgroundFill(Color.GRAY,new CornerRadii(5),Insets.EMPTY)));
        main.setSpacing(10);
        main.getChildren().addAll(mainBtn);
        return main;
    }

    public GridPane cmndLayout(){
        GridPane main = new GridPane();
        main.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        main.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        VBox buttons = new VBox();
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(5));
        buttons.setPrefWidth(500);
        buttons.setAlignment(Pos.TOP_CENTER);
        main.getChildren().add(buttons);
        main.setAlignment(Pos.TOP_CENTER);
        main.setPadding(new Insets(20));
        return main;
    }

}

