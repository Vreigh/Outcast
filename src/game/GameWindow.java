/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import controllers.UnitsController;
import controllers.MainController;
import controllers.UpgradesController;
import controllers.NonCombatController;
import controllers.CombatController;
import controllers.TopMenuController;
import controllers.LeftMenuController;
import helpers.Log;
import helpers.AlertWindow;
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
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.*;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.MediaPlayer.Status;

/**
 *
 * @author Admin
 */
public class GameWindow extends Application {
    
    public static final int shrineCryCost = 100;
    public static final int shrineApCost = 10;
    public static final int extRage = 40;
    public static final int manaIncome = 5;
    public static final int rageDecay = 10;
    public static final int rageIncome = 15;
    
    public static final int extCost = 5;
    public static final int litCost = 5;
    
    public static final int seedCost = 10;
    public static final int seedRest = 5;
    
    public static final int summonCryCost = 50;
    public static final int summonApCost = 5;
    
    public static final int summonUnitCryCost = 500;
    public static final int summonUnitApCost = 10;
    public static final int upgradeUnitApCost = 5;
    
    public static final int shrinesCap = 5;
    
    private Player player;
    private Combat combat;
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
    
    private VBox nonCombatView;
    private NonCombatController nonCombatController;
    
    private VBox combatView;
    private CombatController combatController;
    
    private TableView<Log> table = new TableView<Log>();
    
    private boolean isCombat = false;
    
    public static final double RAND = 0.2; // podstawowe wahanie obrażeń
    
    private MediaPlayer nMediaP;
    private MediaPlayer cMediaP;
    
    private boolean music = true;
    
    @Override
    public void start(Stage stage) throws Exception {
        player = new Player();
        
        String path = new File("media/normal.mp3").getAbsolutePath();
        Media Nmedia = new Media(new File(path).toURI().toString());
        nMediaP = new MediaPlayer(Nmedia);
        nMediaP.setVolume(0.05);
        nMediaP.setCycleCount(MediaPlayer.INDEFINITE);
        
        path = new File("media/battle.mp3").getAbsolutePath();
        Media Cmedia = new Media(new File(path).toURI().toString());
        cMediaP = new MediaPlayer(Cmedia);
        cMediaP.setVolume(0.15);
        cMediaP.setCycleCount(MediaPlayer.INDEFINITE);
        
        FXMLLoader leftLoader = new FXMLLoader(getClass().getResource("/views/leftMenu.fxml"));
        leftMenu = (VBox) leftLoader.load();
        leftMenuController = leftLoader.<LeftMenuController>getController();
        leftMenuController.setGame(this);
        
        FXMLLoader topLoader = new FXMLLoader(getClass().getResource("/views/topMenu.fxml")); 
        topMenu = (HBox) topLoader.load();
        topMenuController = topLoader.<TopMenuController>getController();
        topMenuController.setGame(this);
        topMenuController.bind();
        
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/views/mainView.fxml"));
        mainView = (VBox) mainLoader.load();
        mainController = mainLoader.<MainController>getController();
        mainController.setGame(this);
        mainController.bind();
        
        FXMLLoader upgradesLoader = new FXMLLoader(getClass().getResource("/views/upgradesView.fxml"));
        upgradesView = (VBox) upgradesLoader.load();
        upgradesController = upgradesLoader.<UpgradesController>getController();
        upgradesController.setGame(this);
        upgradesController.bind();
        
        FXMLLoader unitsLoader = new FXMLLoader(getClass().getResource("/views/unitsView.fxml"));
        unitsView = (VBox) unitsLoader.load();
        unitsController = unitsLoader.<UnitsController>getController();
        unitsController.setGame(this);
        unitsController.bind();
        
        FXMLLoader nonCombatLoader = new FXMLLoader(getClass().getResource("/views/nonCombatView.fxml"));
        nonCombatView = (VBox) nonCombatLoader.load();
        nonCombatController = nonCombatLoader.<NonCombatController>getController();
        nonCombatController.setGame(this);
        nonCombatController.bind();
        
        FXMLLoader combatLoader = new FXMLLoader(getClass().getResource("/views/combatView.fxml"));
        combatView = (VBox) combatLoader.load();
        combatController = combatLoader.<CombatController>getController();
        combatController.setGame(this);
        
        TableColumn<Log, Integer> roundColumn = new TableColumn<Log, Integer>("Round");
        roundColumn.setMinWidth(100);
        roundColumn.setCellValueFactory(new PropertyValueFactory<Log, Integer>("round"));
        
        TableColumn<Log, String> contentColumn = new TableColumn<Log, String>("Message");
        contentColumn.setMinWidth(700);
        contentColumn.setCellValueFactory(new PropertyValueFactory<Log, String>("content"));
        
        player.setTable(table);
        table.getColumns().addAll(roundColumn, contentColumn);
        
        nMediaP.play();
        
        layout = new BorderPane();
        layout.setLeft(leftMenu);
        layout.setTop(topMenu);
        layout.setCenter(mainView);
        Scene scene = new Scene(layout, 1000, 621);
        
        stage.setScene(scene);
        scene.getStylesheets().add(GameWindow.class.getResource("style.css").toExternalForm());
        stage.show();
    }
    public void toggleMusic(){
        MediaPlayer player = nMediaP;
        if(isCombat) player = cMediaP;
        
        if(music){
            player.pause();
            music = false;
        }
        else{
            player.play();
            music = true;
        }
    }
    public boolean getMusic(){
        return music;
    }
    public void switchView(int i){
        if(isCombat){
            AlertWindow.showInfo("You are in combat!", "You have to finish your combat first!");
        }else{
            switch(i){
                case 0: layout.setCenter(mainView); break;
                case 1: layout.setCenter(upgradesView); break;
                case 2: layout.setCenter(unitsView); break;
                case 3: layout.setCenter(table); break;
                case 4: layout.setCenter(nonCombatView);break;
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public Player getPlayer(){
        return player;
    }
    public Combat getCombat(){
        return combat;
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
        nonCombatController.bind();
    }
    public void startCombat(){
        if(player.getUnitsSize() > 0){
            combat = new Combat(this);
            isCombat = true;
            
            nMediaP.stop();
            cMediaP.play();
            if(!music) cMediaP.pause();
            
            layout.setCenter(combatView);
            combatController.bind();
            combatController.nextTurn();
        }else{
            AlertWindow.showInfo("You have no units!", "You need at least one unit to fight!");
        }
        
    }
    public void endCombat(boolean won){
        cMediaP.stop();
        if(won){
            boolean dummy = AlertWindow.confirmBox("Victory!", "You have won your battle!", "Confirm!");
            combatWon();
        }else{
            boolean dummy = AlertWindow.confirmBox("Defeat!", "You have lost your battle!", "Confirm...");
            combatLost();
        }
    }
    private void combatWon(){
        isCombat = false;
        layout.setCenter(nonCombatView);
        
        nMediaP.play();
        if(!music) nMediaP.pause();
        
        player.combatWon(this);
    }
    private void combatLost(){ // TO DO
        AlertWindow.showInfo("You lost", "You lost biatch");
    }
    public CombatController getCombatController(){
        return combatController;
    }
}
