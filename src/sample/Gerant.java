package sample;
import Noyau.*;
import Noyau.Client;
import Noyau.Menu;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Gerant extends Stage{

    public Scene scene;
    public EsiMeal esiMeal;
    public Gerant(EsiMeal e){
        this.setTitle("EsiMeal");
        esiMeal = e;
        GridPane main = createMenuLayout();
        scene = new Scene(main,920,600);
        scene.getStylesheets().add("Client.css");
        this.setScene(scene);
    }
    JFXButton addRepas,addBoisson,deleteRepas,deleteBoisson;
    JFXTextField nameInputRepas,priceInputRepas,caloriInputRepas;
    JFXCheckBox dispoInputRepas;
    JFXTextField nameInputBoisson,priceInputBoisson,caloriInputBoisson;
    JFXCheckBox dispoInputBoisson;
    JFXComboBox typeComboBoxRepas,typeComboBoxBoisson;
    TableView<Repas> repasTableView;
    TableView<Boisson> TableBoissonView;
    TableView<Command> TableCommand;
    ListView<Supplement> listSupplement;
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
        Button ret = new Button("Retour");
        retBox.getChildren().add(ret);
        retBox.setAlignment(Pos.CENTER);
        main.add(retBox, 0, 0);
        VBox menu = new VBox();

        HBox repasBox = new HBox();
        ToggleButton repas =new ToggleButton("statistique");
        repas.setFont(new Font(15));
        repas.getStyleClass().remove("radio-button");
        repas.getStyleClass().add("toggle-button");
        repas.setPrefSize(230,40);
        repasBox.getChildren().add(repas);
        repasBox.setAlignment(Pos.CENTER);

        HBox cmndBox = new HBox();
        ToggleButton cmnd =new ToggleButton("Commandes");
        cmnd.setFont(new Font(15));
        cmnd.getStyleClass().remove("radio-button");
        cmnd.getStyleClass().add("toggle-button");
        cmnd.setPrefSize(230,40);
        cmndBox.getChildren().add(cmnd);
        cmndBox.setAlignment(Pos.CENTER);

        HBox boissonBox = new HBox();
        ToggleButton boisson =new ToggleButton("liste des Mets");
        boisson.getStyleClass().remove("radio-button");
        boisson.getStyleClass().add("toggle-button");
        boisson.setFont(new Font(15));
        boisson.setPrefSize(230,40);
        boissonBox.getChildren().add(boisson);
        boissonBox.setAlignment(Pos.CENTER);

        ToggleGroup mets = new ToggleGroup();
        cmnd.setSelected(true);
        boisson.setToggleGroup(mets);
        cmnd.setToggleGroup(mets);
        repas.setToggleGroup(mets);
        menu.getChildren().addAll(cmndBox,repasBox,boissonBox);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(50);
        main.add(menu,0,1);

        HBox titleBox = new HBox();
        Label titre = new Label("EsiMeal");
        titre.setAlignment(Pos.CENTER);
        titre.setFont(new Font( 25));
        titre.setTextFill(Color.DARKGREEN);
        titleBox.getChildren().add(titre);
        titleBox.setAlignment(Pos.CENTER);
        main.add(titleBox, 1, 0);
        repasTableView=createTableRepas();
        TableBoissonView=createTableBoisson();
        Main.esiMeal.metsDisponible.addAll(repasTableView.getItems());
        Main.esiMeal.metsDisponible.addAll(TableBoissonView.getItems());
        VBox vbRepas = new VBox();
        vbRepas.getChildren().addAll(repasTableView,createVboxInputMet(true));
        VBox vbBoisson = new VBox();
        vbBoisson.getChildren().addAll(TableBoissonView,createVboxInputMet(false));

        JFXTabPane tp = new JFXTabPane();
        Tab ap1 = new Tab();
        ap1.setText("boisson");
        ap1.setContent(vbBoisson);
        Tab ap = new Tab();
        ap.setText("repas");
        ap.setContent(vbRepas);
        tp.getTabs().addAll(ap,ap1);

        GridPane stat = createState();

        GridPane Commd = createCmdLayout();

        main.add(Commd,1,1);

        cmnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    main.getChildren().remove(tp);
                    main.getChildren().remove(stat);
                }catch (Exception e){} finally {
                    main.add(Commd,1,1);
                }
            }
        });

        boisson.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    try {
                        main.getChildren().remove(Commd);
                        main.getChildren().remove(stat);
                    } catch (Exception e) {
                    } finally {
                        main.add(tp, 1, 1);
                    }
                }
                catch (Exception e){}
            }
        });

        repas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    main.getChildren().remove(tp);
                    main.getChildren().remove(Commd);
                }catch (Exception e){} finally {
                    main.add(stat,1,1);
                }
            }
        });

        return main;
    }

    GridPane createCmdLayout(){

        GridPane main = new GridPane();


        JFXTabPane tp = new JFXTabPane();
        Tab ap1 = new Tab();
        ap1.setText("Clients");
        VBox cltBox =  new VBox();cltBox.getChildren().add(createTableClient());
        ap1.setContent(cltBox);
        Tab ap = new Tab();
        ap.setText("Commandes en attente");
        VBox cmdBox =  new VBox();cmdBox.getChildren().add(createTableCmd());
        ap.setContent(cmdBox);
        Tab ap2 = new Tab();
        ap2.setText("Commandes effectué");
        VBox cmdBox2 =  new VBox();cmdBox2.getChildren().add(createTableCmd2());
        ap2.setContent(cmdBox2);
        tp.getTabs().addAll(ap,ap2,ap1);

        main.getChildren().add(tp);
        main.setAlignment(Pos.CENTER);
        main.setPadding(new Insets(5));
        main.setPrefSize(750,500);


        return main;
    }


    GridPane createState(){
        GridPane main = new GridPane();
        RowConstraints rowDate = new RowConstraints(); rowDate.setPercentHeight(25);
        RowConstraints rowInf = new RowConstraints(); rowInf.setPercentHeight(75);
        main.getRowConstraints().add(rowDate);
        main.getRowConstraints().add(rowInf);
        HBox dates = new HBox();
        JFXDatePicker startDate = new JFXDatePicker();
        JFXDatePicker finishDate = new JFXDatePicker();
        dates.getChildren().addAll(startDate,finishDate);
        dates.setAlignment(Pos.CENTER);
        main.add(dates,0,0);


        GridPane mainWin = new GridPane();
        RowConstraints rowBtn = new RowConstraints(); rowDate.setPercentHeight(25);
        RowConstraints rowInfor = new RowConstraints(); rowInf.setPercentHeight(75);
        mainWin.getRowConstraints().add(rowBtn);
        mainWin.getRowConstraints().add(rowInfor);
        HBox btns = new HBox();
        ToggleButton cmd = new ToggleButton("Commandes");
        cmd.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        ToggleButton met = new ToggleButton("Mets");
        met.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        ToggleButton red = new ToggleButton("Reductions");
        red.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));

        ToggleButton client = new ToggleButton("Clients");
        client.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        btns.getChildren().addAll(cmd,red,met,client);
        btns.setAlignment(Pos.CENTER);
        btns.setSpacing(20);
        ToggleGroup btn = new ToggleGroup();
        cmd.setToggleGroup(btn);
        met.setToggleGroup(btn);
        red.setToggleGroup(btn);
        client.setToggleGroup(btn);
        cmd.setSelected(true);

        VBox cmdInf =new VBox();
        Label one = new Label("Le nombre de toutes les commandes effectuées : " +Integer.toString(esiMeal.nbCommandesEffectues()) );
        Label two  = new Label("le montant de toutes les commandes effectuées : " +Double.toString(esiMeal.montantCommandesEffectues()));
        Label three  = new Label("Le nombre des commandes “sur place” : "+Integer.toString(esiMeal.nbCommandesSurPlace()));
        Label four  = new Label("Le montant des commandes “sur place” : "+Double.toString(esiMeal.montantCommandesSurPlace()));
        Label five  = new Label("Le nombre des commandes “à domicile” : "+Integer.toString(esiMeal.nbCommandesLivre()));
        Label six  = new Label("Le montant des commandes “à domicile” : "+Double.toString(esiMeal.montantCommandesLivre()));
        Label seven  = new Label("Le nombre des commandes “évènements” : "+Integer.toString(esiMeal.nbCommandesEvt()));
        Label eight  = new Label("Le montant des commandes “évènements” : "+Double.toString(esiMeal.montantCommandesEvt()));
        cmdInf.getChildren().addAll(one,two,three,four,five,six,seven,eight);
        cmdInf.setPadding(new Insets(10));
        cmdInf.setSpacing(20);

        VBox redInf =new VBox();
        Label one1 = new Label("Le montant total des réductions offertes : "+Double.toString(esiMeal.montantTotaleReductions()));
        Label two1  = new Label("Le montant total des réductions de type “fidélité” : "+Double.toString(esiMeal.montantTotaleReductionsFidelite()));
        Label three1  = new Label("Le montant total des réductions de type  “étudiant” : "+Double.toString(esiMeal.montantTotaleReductionsEtudiant()));
        Label four1  = new Label("Le montant total des réductions de type  “groupe à domicile” : "+Double.toString(esiMeal.montantTotaleReductionsGroupe()));
        Label five1  = new Label("Le montant total des réductions de type  “événement”  : "+Double.toString(esiMeal.montantTotaleReductionsEvent()));
        redInf.getChildren().addAll(one1,two1,three1,four1,five1);
        redInf.setPadding(new Insets(10));
        redInf.setSpacing(20);

        VBox metInf =new VBox();
        Label one2 = new Label("Le met le plus commandé par les clients : est pizza "/*+esiMeal.metPlusCommande()*/);
        Label two2  = new Label("Le met le moins commandé par les clients : "/*+esiMeal.metMoinCommande()*/);
        metInf.getChildren().addAll(one2,two2);
        metInf.setPadding(new Insets(10));
        metInf.setSpacing(20);

        VBox clientInf =new VBox();
        Label one3 = new Label("Client ayant effectué le plus de commandes. "+esiMeal.clientPlusCommande());
        Label two3  = new Label("Client ayant effectué le plus de commande sur place: "+esiMeal.clientPlusCommandeSurPlace());
        Label three3  = new Label("Client ayant effectué le plus de commande à domicile: "+esiMeal.clientPlusCommandeLivre());
        Label four3  = new Label("Client ayant effectué le plus de commande évènement: "+esiMeal.clientPlusCommandeEvent());
        Label five3  = new Label("Client ayant bénéficié du plus grand nombre de réductions: "+esiMeal.clientPlusReduction());
        Label six3  = new Label("Client ayant bénéficié d plus grand nombre de réductions de type“fidélité”: "+esiMeal.clientPlusReductionFidelite());
        Label seven3  = new Label("Client ayant bénéficié d plus grand nombre de réductions de type“étudiant”: "+esiMeal.clientPlusReductionEtudiant());
        Label eight3  = new Label("Client ayant bénéficié d plus grand nombre de réductions de type“à domicile”: "+esiMeal.clientPlusReductionGroupe());
        Label nine3  = new Label("Client ayant bénéficié d plus grand nombre de réductions de type“événement”: "+esiMeal.clientPlusReductionEvt());
        clientInf.getChildren().addAll(one3,two3,three3,four3,five3,six3,seven3,eight3,nine3);
        clientInf.setPadding(new Insets(10));
        clientInf.setSpacing(20);

        mainWin.add(btns,0,0);
        mainWin.add(cmdInf,0,1);
        cmd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{mainWin.getChildren().remove(1); mainWin.add(cmdInf,0,1);}catch (Exception e){}

            }
        });
        met.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{mainWin.getChildren().remove(1); mainWin.add(metInf,0,1);}catch (Exception e){}
            }
        });

        client.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{mainWin.getChildren().remove(1); mainWin.add(clientInf,0,1);}catch (Exception e){}
            }
        });
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{mainWin.getChildren().remove(1); mainWin.add(redInf,0,1);}catch (Exception e){}

            }
        });
        mainWin.setPrefSize(650,500);
        main.setPrefSize(750,500);
        main.setAlignment(Pos.CENTER);
        mainWin.setAlignment(Pos.CENTER);
        main.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii(5),Insets.EMPTY)));
        main.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(1))));
        main.add(mainWin,0,1);


        return main;
    }

    ObservableList<Command> setTable4(){
        ObservableList<Command> repasTableView= FXCollections.observableArrayList();
        Date date = new Date();
        LocalDate dating = new java.sql.Date(date.getTime()).toLocalDate();

        Client k = new Client("Djamaa","Abdelmalek","0555555",true);
        List<Menu> menus = new ArrayList<Menu>();
        Menu menus2 = new Menu();
        Menu menus3 = new Menu();
        List<Mets> menu = new ArrayList<Mets>();
        List<Mets> menu2 = new ArrayList<Mets>();
        Repas one = new Repas("Chawarma");
        Boisson one_ = new Boisson("Hamoud");
        Repas two = new Repas("Tacos");
        menu.add(one); menu.add(one_);
        menu2.add(two); menu2.add(one_);
        menus2.setMenu(menu2);
        menus3.setMenu(menu);
        menus.add(menus2);
        menus.add(menus3);
        for(Command cmd:Main.esiMeal.listAttente)
        repasTableView.add(cmd);
        return  repasTableView;
    }
    ObservableList<Command> setTable2(){
        ObservableList<Command> repasTableView= FXCollections.observableArrayList();
        Date date = new Date();
        LocalDate dating = new java.sql.Date(date.getTime()).toLocalDate();
        Client k = new Client("Derras","Khalil","065412332",true);
        List<Menu> menus = new ArrayList<Menu>();
        Menu menus2 = new Menu();
        List<Mets> menu = new ArrayList<Mets>();
        Repas one = new Repas("Chawarma");
        Boisson one_ = new Boisson("Hamoud");
        menu.add(one); menu.add(one_);
        menus2.setMenu(menu);
        menus.add(menus2);


        repasTableView.add(new Command(k, 1,dating,menus));
        return  repasTableView;
    }
    ObservableList<Client> setTable3(){
        ObservableList<Client> repasTableView= FXCollections.observableArrayList();
        repasTableView.add(new Client("Derras","Khalil","65554111",true));
        repasTableView.add(new Client("Djamaa","Abdelmalek","5555555",true));
        return  repasTableView;
    }

    TableView<Noyau.Command> createTableCmd2(){
        TableView<Noyau.Command>  repasTableView = new TableView<Command>();
        TableColumn<Noyau.Command, String> nameColumn=new TableColumn<>("Nom");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Noyau.Command, String> preColumn=new TableColumn<>("Prenom");
        preColumn.setMinWidth(150);
        preColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Noyau.Command, String> phoneColumn=new TableColumn<>("N Telephone");
        phoneColumn.setMinWidth(150);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("nTelephone"));

        TableColumn<Noyau.Command, Date> dateColumn=new TableColumn<>("Date");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("heure"));

        TableColumn<Noyau.Command,Integer> prixColumn=new TableColumn<>("Nombre de Personne");
        prixColumn.setMinWidth(50);
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("nbPersonne"));

        TableColumn<Noyau.Command,List<Menu>> caloriesColumn=new TableColumn<>("Menu");
        caloriesColumn.setMinWidth(150);
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("menuss"));

        repasTableView.setItems(setTable2());
        repasTableView.getColumns().addAll(nameColumn,preColumn,phoneColumn,dateColumn,prixColumn,caloriesColumn);
        return repasTableView;
    }

    TableView<Noyau.Command> createTableCmd(){
        TableView<Noyau.Command>  repasTableView = new TableView<Command>();
        TableColumn<Noyau.Command, String> nameColumn=new TableColumn<>("Nom");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Noyau.Command, String> preColumn=new TableColumn<>("Prenom");
        preColumn.setMinWidth(150);
        preColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Noyau.Command, String> phoneColumn=new TableColumn<>("N Telephone");
        phoneColumn.setMinWidth(150);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("nTelephone"));

        TableColumn<Noyau.Command, Date> dateColumn=new TableColumn<>("Date");
        dateColumn.setMinWidth(150);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("heure"));

        TableColumn<Noyau.Command,Integer> prixColumn=new TableColumn<>("Nombre de Personne");
        prixColumn.setMinWidth(50);
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("nbPersonne"));

        TableColumn<Noyau.Command,List<Menu>> caloriesColumn=new TableColumn<>("Menu");
        caloriesColumn.setMinWidth(150);
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("menuss"));

        repasTableView.setItems(setTable4());
        repasTableView.getColumns().addAll(nameColumn,preColumn,phoneColumn,dateColumn,prixColumn,caloriesColumn);
        return repasTableView;
    }


    TableView<Noyau.Client> createTableClient(){
        TableView<Noyau.Client>  repasTableView = new TableView<Client>();
        TableColumn<Noyau.Client,String> nameColumn=new TableColumn<>("Nom");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Noyau.Client,String> prixColumn=new TableColumn<>("Prenom");
        prixColumn.setMinWidth(200);
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("pronom"));

        TableColumn<Noyau.Client,Integer> caloriesColumn=new TableColumn<>("N de Telephone");
        caloriesColumn.setMinWidth(200);
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("nTelephone"));


        repasTableView.setItems(setTable3());
        repasTableView.getColumns().addAll(nameColumn,prixColumn,caloriesColumn);
        return repasTableView;
    }

    ObservableList<Repas> setTable(){
        ObservableList<Repas> repasTableView= FXCollections.observableArrayList();
        repasTableView.add(new Repas("Salade",800,510,true));
        repasTableView.add(new Repas("Quiche",150,510,true));
        repasTableView.add(new Repas("Couscous",2200,540,true));
        repasTableView.add(new Repas("Pizza",700,570,true));
        repasTableView.add(new Repas("Creme brulée",700,900,true));
        repasTableView.add(new Repas("Makrout El Louz",200,500,true));

        return  repasTableView;
    }
    ObservableList<Boisson> setTable1(){
        ObservableList<Boisson> repasTableView= FXCollections.observableArrayList();
        repasTableView.add(new Boisson("Soda",120,40,true));
        repasTableView.add(new Boisson("Eau",50,0,true));

        return  repasTableView;
    }
    TableView<Noyau.Repas> createTableRepas(){
        TableView<Noyau.Repas> repasTableView = new TableView<Noyau.Repas>();
        try {
            TableColumn<Noyau.Repas, String> nameColumn = new TableColumn<>("Nom");
            nameColumn.setMinWidth(100);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

            TableColumn<Noyau.Repas, Double> prixColumn = new TableColumn<>("Prix");
            prixColumn.setMinWidth(100);
            prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

            TableColumn<Noyau.Repas, String> caloriesColumn = new TableColumn<>("Calories");
            caloriesColumn.setMinWidth(100);
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("nbCalories"));

            TableColumn<Noyau.Repas, String> disponibilitéColumn = new TableColumn<>("Disponibilité");
            disponibilitéColumn.setMinWidth(100);
            disponibilitéColumn.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
            repasTableView.setItems(setTable());
            repasTableView.getColumns().addAll(nameColumn, prixColumn, disponibilitéColumn, caloriesColumn);
        }
        catch (Exception e){}
        return repasTableView;

    }
    TableView<Noyau.Boisson> createTableBoisson(){
        TableView<Noyau.Boisson> TableBoissonView = new TableView<Boisson>();
        try {
            TableColumn<Noyau.Boisson, String> nameColumn = new TableColumn<>("Nom");
            nameColumn.setMinWidth(100);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

            TableColumn<Noyau.Boisson, Double> prixColumn = new TableColumn<>("Prix");
            prixColumn.setMinWidth(100);
            prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

            TableColumn<Noyau.Boisson, String> caloriesColumn = new TableColumn<>("Calories");
            caloriesColumn.setMinWidth(100);
            caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("nbCalories"));

            TableColumn<Noyau.Boisson, String> disponibilitéColumn = new TableColumn<>("Disponibilité");
            disponibilitéColumn.setMinWidth(100);
            disponibilitéColumn.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));

            TableBoissonView.setItems(setTable1());
            TableBoissonView.getColumns().addAll(nameColumn, prixColumn, disponibilitéColumn, caloriesColumn);
        }
        catch (Exception e){

        }
        return TableBoissonView;
    }
    VBox createVboxInputMet(boolean met){
        VBox vb= new VBox();
        HBox hbb = new HBox();
        hbb.setPadding(new Insets(10,10,10,10));
        hbb.setSpacing(5);

        HBox hbbb= new HBox();
        hbbb.setPadding(new Insets(10,10,10,10));
        hbbb.setSpacing(5);

        HBox hb = new HBox();
        hb.setPadding(new Insets(10,10,10,10));
        hb.setSpacing(5);
        if(met) {
            nameInputRepas= new JFXTextField();
            nameInputRepas.setLabelFloat(true);
            nameInputRepas.setPromptText("Nom");
            priceInputRepas= new JFXTextField();
            priceInputRepas.setLabelFloat(true);
            priceInputRepas.setPromptText("Prix");
            caloriInputRepas= new JFXTextField();
            caloriInputRepas.setLabelFloat(true);
            caloriInputRepas.setPromptText("NombreDeCalories");
            dispoInputRepas= new JFXCheckBox();
            dispoInputRepas.setText("Disponibility");
            addRepas = new JFXButton("ajouter Repas");
            addRepas.setOnAction(e->addRepas());
            deleteRepas = new JFXButton("suprimmer Repas");
            deleteRepas.setOnAction(e->deleteRepas());
            typeComboBoxRepas= new JFXComboBox();
            typeComboBoxRepas.setPromptText("type de repas");
            typeComboBoxRepas.setLabelFloat(true);
            typeComboBoxRepas.getItems().addAll("Entree","Plat","Dessert");
            listSupplement=new ListView<Supplement>();
            //listSupplement.getItems().addAll(new Supplement("khalil",10,10),new Supplement("khalik",50,50));
            hbbb.getChildren().addAll(typeComboBoxRepas,listSupplement);
            hbb.getChildren().addAll(addRepas,deleteRepas);
            hb.getChildren().addAll(nameInputRepas,priceInputRepas,caloriInputRepas,dispoInputRepas);
        }
        else{
            nameInputBoisson= new JFXTextField();
            nameInputBoisson.setLabelFloat(true);
            nameInputBoisson.setPromptText("Nom");
            priceInputBoisson= new JFXTextField();
            priceInputBoisson.setLabelFloat(true);
            priceInputBoisson.setPromptText("Prix");
            caloriInputBoisson= new JFXTextField();
            caloriInputBoisson.setLabelFloat(true);
            caloriInputBoisson.setPromptText("NombreDeCalories");
            dispoInputBoisson= new JFXCheckBox();
            dispoInputBoisson.setText("Disponibility");
            addBoisson = new JFXButton("ajouter Boisson");
            addBoisson.setOnAction(e->addBoisson());
            deleteBoisson = new JFXButton("suprimmer Boisson");
            deleteBoisson.setOnAction(e->deleteBoisson());


            typeComboBoxBoisson= new JFXComboBox();
            typeComboBoxBoisson.setLabelFloat(true);
            typeComboBoxBoisson.setPromptText("type de boisson");
            typeComboBoxBoisson.getItems().addAll("EauMinerale","Jus","BoissonGazeuse","Cafe","The");

            hbbb.getChildren().add(typeComboBoxBoisson);
            hbb.getChildren().addAll(addBoisson,deleteBoisson);
            hb.getChildren().addAll(nameInputBoisson,priceInputBoisson,caloriInputBoisson,dispoInputBoisson);

        }
        vb.getChildren().addAll(hb,hbbb,hbb);
        return vb;
    }
    void addRepas(){
        try {
            Repas rp = new Repas();
            rp.setDisponibilite(dispoInputRepas.isSelected());
            rp.setNbCalories(Integer.parseInt(caloriInputRepas.getText()));
            rp.setNom(nameInputRepas.getText());
            rp.setPrix(Double.parseDouble(priceInputRepas.getText()));
            repasTableView.getItems().add(rp);
            nameInputRepas.clear();
            caloriInputRepas.clear();
            priceInputRepas.clear();
        }
        catch (Exception e){

        }
    }
    void deleteRepas(){
        ObservableList<Repas> allRepas,selectedRepas;
        allRepas = repasTableView.getItems();
        selectedRepas=repasTableView.getSelectionModel().getSelectedItems();
        selectedRepas.forEach(allRepas::remove);
    }
    void addBoisson(){
        try{
            Boisson rp = new Boisson();
            rp.setDisponibilite(dispoInputBoisson.isSelected());
            rp.setNbCalories(Integer.parseInt(caloriInputBoisson.getText()));
            rp.setNom(nameInputBoisson.getText());
            rp.setPrix(Double.parseDouble(priceInputBoisson.getText()));
            TableBoissonView.getItems().add(rp);
            nameInputBoisson.clear();
            caloriInputBoisson.clear();
            priceInputBoisson.clear();}
        catch (Exception e){

        }
    }
    void deleteBoisson(){
        ObservableList<Boisson> allRepas,selectedRepas;
        allRepas = TableBoissonView.getItems();
        selectedRepas=TableBoissonView.getSelectionModel().getSelectedItems();
        selectedRepas.forEach(allRepas::remove);

    }

}



