/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class Game extends Application {
    
    public static final int shrineCryCost = 100;
    public static final int shrineApCost = 5;
    public static final int extRage = 40;
    public static final int manaIncome = 5;
    public static final int rageDecay = 10;
    
    public static final int extCost = 5;
    public static final int litCost = 10;
    
    public static final int seedCost = 10;
    public static final int seedRest = 5;
    
    public static final int summonCryCost = 50;
    public static final int summonApCost = 5;
    
    public static final int shrinesCap = 5;
    
    private Player player;
    private IntegerProperty round = new SimpleIntegerProperty(0);
    
    private BorderPane layout;
    
    private VBox leftMenu;
    private LeftMenuController leftMenuController;
    
    private HBox topMenu;
    private TopMenuController topMenuController;
    
    private VBox mainView;
    private MainController mainController;
    
    private VBox upgradesView;
    private UpgradesController upgradesController;
    
    private VBox unitsView;
    private UnitsController unitsController;
    
    private TableView<Log> table = new TableView<Log>();
    
    @Override
    public void start(Stage stage) throws Exception {
        player = new Player();
        
        FXMLLoader leftLoader = new FXMLLoader(getClass().getResource("leftMenu.fxml"));
        leftMenu = (VBox) leftLoader.load();
        leftMenuController = leftLoader.<LeftMenuController>getController();
        leftMenuController.setGame(this);
        
        FXMLLoader topLoader = new FXMLLoader(getClass().getResource("topMenu.fxml")); 
        topMenu = (HBox) topLoader.load();
        topMenuController = topLoader.<TopMenuController>getController();
        topMenuController.setGame(this);
        topMenuController.bind();
        
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainView.fxml"));
        mainView = (VBox) mainLoader.load();
        mainController = mainLoader.<MainController>getController();
        mainController.setGame(this);
        mainController.bind();
        
        FXMLLoader upgradesLoader = new FXMLLoader(getClass().getResource("upgradesView.fxml"));
        upgradesView = (VBox) upgradesLoader.load();
        upgradesController = upgradesLoader.<UpgradesController>getController();
        upgradesController.setGame(this);
        upgradesController.bind();
        
        FXMLLoader unitsLoader = new FXMLLoader(getClass().getResource("unitsView.fxml"));
        unitsView = (VBox) unitsLoader.load();
        unitsController = unitsLoader.<UnitsController>getController();
        unitsController.setGame(this);
        unitsController.bind();
        
        TableColumn<Log, Integer> roundColumn = new TableColumn<Log, Integer>("Round");
        roundColumn.setMinWidth(100);
        roundColumn.setCellValueFactory(new PropertyValueFactory<Log, Integer>("round"));
        
        TableColumn<Log, String> contentColumn = new TableColumn<Log, String>("Message");
        contentColumn.setMinWidth(700);
        contentColumn.setCellValueFactory(new PropertyValueFactory<Log, String>("content"));
        
        player.setTable(table);
        table.getColumns().addAll(roundColumn, contentColumn);
        
        layout = new BorderPane();
        layout.setLeft(leftMenu);
        layout.setTop(topMenu);
        layout.setCenter(mainView);
        Scene scene = new Scene(layout, 1000, 621);
        
        stage.setScene(scene);
        scene.getStylesheets().add(Game.class.getResource("style.css").toExternalForm());
        stage.show();
    }
    public void switchView(int i){
        switch(i){
            case 0:
                layout.setCenter(mainView);
                break;
            case 1:
                layout.setCenter(upgradesView);
                break;
            case 2:
                layout.setCenter(unitsView);
                break;
            case 3:
                // game.getLogs().add(new Log(0, "im new here, hello!")); to dziala! ta tabela jest obserwowalna, nigdy nie musze jej odswiezać!
                layout.setCenter(table);
                break;
            case 4:
                //layout.setCenter();
                break;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public Player getPlayer(){
        return player;
    }
    public IntegerProperty getRoundProperty(){
        return round;
    }
    public int getRound(){
        return round.get();
    }
    public TableView<Log> getJournal(){
        return table;
    }
    public void endTurn(){
        round.set(round.get() + 1);
        player.endTurn(round.get() - 1);
    }
}
