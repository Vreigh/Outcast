/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.Game;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.beans.property.*;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MainController implements Initializable {
    
    private Game game;
    
    
    @FXML private Button btnBuildShrine;
    @FXML private Button btnLitShrine;
    @FXML private Button btnExtShrine;
    @FXML private Button btnSummon;
    @FXML private Button btnCemAdd;
    @FXML private Button btnMakeSeed;
    @FXML private Label shrinesDisplay;
    @FXML private Label cemDisplay;
    
    @FXML private HBox topSeedBar;
    @FXML private HBox botSeedBar;
    
    //narazie niepotrzebne
    @FXML private Button btnEndTurn;
    
    private ArrayList<Button> seedButtons = new ArrayList<Button>();
    private ArrayList<Label> seedLabels = new ArrayList<Label>();
    
    public MainController(){
        for(int i=0; i<8; i++){
            Button btn = new Button("Harvest");
            Integer x = new Integer(i);
            btn.setPrefWidth(100);
            btn.setPrefHeight(40);
            btn.setOnAction(e -> harvest(x));
            seedButtons.add(btn);
            
            Label lb = new Label("dummy");
            lb.setPrefWidth(50);
            seedLabels.add(lb);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    public void setGame(Game game){
        this.game = game;
    }
    public void bind(){
        shrinesDisplay.textProperty().bind(Bindings.concat("Shrines: ").concat(game.getPlayer().getLitStringBind()).concat(" / ")
               .concat(game.getPlayer().getShrinesStringBind()).concat(", max spirits: ").concat(game.getPlayer().getSpiritsCapStringBind()));
        btnBuildShrine.textProperty().bind(Bindings.concat("Build (").concat(Game.shrineCryCost).concat(" / ").concat(Game.shrineApCost).concat(")"));
        btnLitShrine.textProperty().bind(Bindings.concat("Lit (").concat("0").concat(" / ").concat(Game.litCost).concat(")"));
        btnExtShrine.textProperty().bind(Bindings.concat("Extinguish (").concat("0").concat(" / ").concat(Game.extCost).concat(")"));
        btnSummon.textProperty().bind(Bindings.concat("Summon (").concat(Game.summonCryCost).concat(" / ").concat(Game.summonApCost).concat(")"));
        btnCemAdd.textProperty().bind(Bindings.concat("Send a spirit here (").concat("0").concat(" / ").concat("1").concat(")"));
        btnMakeSeed.textProperty().bind(Bindings.concat("New seed (").concat("0").concat(" / ").concat(Game.seedCost).concat(")"));
        
        cemDisplay.textProperty().bind(Bindings.concat("Spirits working: ").concat(game.getPlayer().getCementary().getFillStringBind()).concat(" / ")
                .concat(game.getPlayer().getCementary().getCapacityStringBind())
                .concat(" income per spirit: ").concat(game.getPlayer().getCementary().getSingleOutputStringBind())
                .concat(" predicted total this turn: ").concat(game.getPlayer().getCementary().getFullOutputStringBind()));
        
        reloadSeeds();
    }
    private void reloadSeeds(){
        for(int i=0; i<3;i++){
            topSeedBar.getChildren().remove(seedLabels.get(i));
            topSeedBar.getChildren().remove(seedButtons.get(i));
        }
        for(int i=3; i<6; i++){
            botSeedBar.getChildren().remove(seedLabels.get(i));
            botSeedBar.getChildren().remove(seedButtons.get(i));
        }
        if(game.getPlayer().getSeeds().isEmpty()){
            return;
        }else{
            int n = game.getPlayer().getSeeds().size();
            
            for(int i=0; i<3; i++){
                if(i >= n){
                    break;
                }else{
                    Seed seed = game.getPlayer().getSeeds().get(i);
                    
                    seedLabels.get(i).textProperty().bind(Bindings.concat("Age: ", seed.getAgeStringBind()));
                    topSeedBar.getChildren().add(seedLabels.get(i));
                    topSeedBar.getChildren().add(seedButtons.get(i));
                }
            }
            for(int i=3; i<6; i++){
                if(i >= n){
                    break;
                }else{
                    Seed seed = game.getPlayer().getSeeds().get(i);
                    
                    seedLabels.get(i).textProperty().bind(Bindings.concat("Age: ", seed.getAgeStringBind()));
                    botSeedBar.getChildren().add(seedLabels.get(i));
                    botSeedBar.getChildren().add(seedButtons.get(i));
                }
            }
        }
    }
    public void buildShrine(){
        game.getPlayer().buildShrine();
    }
    public void litShrine(){
        game.getPlayer().litShrine();
    }
    public void extShrine(){
        game.getPlayer().extShrine();
    }
    public void summon(){
        game.getPlayer().summonSpirit();
    }
    public void cemAdd(){
        game.getPlayer().cemAdd();
    }
    public void makeSeed(){
        if(game.getPlayer().makeSeed()){
            reloadSeeds();
        }
    }
    public void harvest(Integer i){
        int income = game.getPlayer().harvest(i);
        if(income != 0){
            reloadSeeds();
            AlertWindow.showInfo("Collected mana!", "You collected " + income + " mana!" );
        }
    }
    public void endTurn(){
        game.endTurn();
    }
}
