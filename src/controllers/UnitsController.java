/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import helpers.AlertWindow;
import game.GameWindow;
import game.GameWindow;
import game.Unit;
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
import javafx.scene.text.Text;
import javafx.beans.property.*;
import javafx.beans.binding.Bindings;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Admin
 */
public class UnitsController implements Initializable {
    
    private GameWindow game; // referencja do gry
    
    @FXML private HBox addingBox;
    @FXML private HBox unitsContainer;
    
    private ArrayList<VBox> unitContainer = new ArrayList<VBox>();
    
    private ArrayList<Label> unitName = new ArrayList<Label>();
    
    private ArrayList<HBox> powerBox = new ArrayList<HBox>();
    private ArrayList<Label> powerLabel = new ArrayList<Label>();
    private ArrayList<Button> btnPower = new ArrayList<Button>();
    
    private ArrayList<HBox> healthBox = new ArrayList<HBox>();
    private ArrayList<Label> healthLabel = new ArrayList<Label>();
    private ArrayList<Button> btnHealth = new ArrayList<Button>();
    
    private ArrayList<HBox> shieldBox = new ArrayList<HBox>();
    private ArrayList<Label> shieldLabel = new ArrayList<Label>();
    private ArrayList<Button> btnShield = new ArrayList<Button>();
    
    private ArrayList<Button> btnFirstAbility = new ArrayList<Button>();
    private ArrayList<Button> btnSecondAbility = new ArrayList<Button>();
    private ArrayList<Button> btnUltAbility = new ArrayList<Button>();
    
    private ArrayList<HBox> swapBox = new ArrayList<HBox>();
    private ArrayList<Button> swapLeft = new ArrayList<Button>();
    private ArrayList<Button> swapRight = new ArrayList<Button>();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i<5; i++){
            Integer x = new Integer(i);
            
            unitName.add(new Label("BRAK!"));
            unitName.get(i).setPrefWidth(130);
            unitName.get(i).setAlignment(Pos.CENTER);
            
            unitContainer.add(new VBox());
            unitContainer.get(i).setPrefWidth(150);
            unitContainer.get(i).setSpacing(20);
            unitContainer.get(i).getStyleClass().add("unitContainer");
            unitContainer.get(i).setPadding(new Insets(10, 10, 10, 10));
            
            powerBox.add(new HBox());
            powerBox.get(i).setSpacing(10);
            powerLabel.add(new Label("power"));
            powerLabel.get(i).setPrefWidth(90);
            powerLabel.get(i).setWrapText(true);
            btnPower.add(new Button("+"));
            btnPower.get(i).setPrefWidth(30);
            btnPower.get(i).setOnAction(e -> upPower(x));
            powerBox.get(i).getChildren().addAll(powerLabel.get(i), btnPower.get(i));
            
            shieldBox.add(new HBox());
            shieldBox.get(i).setSpacing(10);
            shieldLabel.add(new Label("shield"));
            shieldLabel.get(i).setPrefWidth(90);
            shieldLabel.get(i).setWrapText(true);
            btnShield.add(new Button("+"));
            btnShield.get(i).setPrefWidth(30);
            btnShield.get(i).setOnAction(e -> upShield(x));
            shieldBox.get(i).getChildren().addAll(shieldLabel.get(i), btnShield.get(i));
            
            healthBox.add(new HBox());
            healthBox.get(i).setSpacing(10);
            healthLabel.add(new Label("health"));
            healthLabel.get(i).setPrefWidth(90);
            healthLabel.get(i).setWrapText(true);
            btnHealth.add(new Button("+"));
            btnHealth.get(i).setPrefWidth(30);
            btnHealth.get(i).setOnAction(e -> upHealth(x));
            healthBox.get(i).getChildren().addAll(healthLabel.get(i), btnHealth.get(i));
            
            btnFirstAbility.add(new Button("First Ability"));
            btnFirstAbility.get(i).setPrefWidth(130);
            btnFirstAbility.get(i).setOnAction(e -> showFirstAbility(x));
            
            btnSecondAbility.add(new Button("Second Ability"));
            btnSecondAbility.get(i).setPrefWidth(130);
            btnSecondAbility.get(i).setOnAction(e -> showSecondAbility(x));
            
            btnUltAbility.add(new Button("Ult Ability"));
            btnUltAbility.get(i).setPrefWidth(130);
            btnUltAbility.get(i).setOnAction(e -> showUltAbility(x));
            
            swapBox.add(new HBox());
            swapBox.get(i).setSpacing(70);
            
            swapLeft.add(new Button("<-"));
            swapLeft.get(i).setPrefWidth(30);
            swapLeft.get(i).setOnAction(e -> swapLeft(x));
            
            swapRight.add(new Button("->"));
            swapRight.get(i).setPrefWidth(30);
            swapRight.get(i).setOnAction(e -> swapRight(x));
            
            swapBox.get(i).getChildren().addAll(swapLeft.get(i), swapRight.get(i));
            
            unitsContainer.getChildren().add(unitContainer.get(i));
        }
    }
    private void addAll(int i){
        unitContainer.get(i).getChildren().addAll(unitName.get(i),
                    healthBox.get(i),
                    powerBox.get(i),
                    shieldBox.get(i),
                    btnFirstAbility.get(i),
                    btnSecondAbility.get(i),
                    btnUltAbility.get(i),
                    swapBox.get(i));
    }
    public void setGame(GameWindow game){
        this.game = game;
    }
    public void bind(){
        for(int i = 0; i<5; i++){
            bindUnit(i);
        }
    }
    private void bindUnit(int i){
        unitContainer.get(i).getChildren().clear();
        Unit unit = game.getPlayer().getUnit(i);
        
        if(unit != null){
            addAll(i);
            unitName.get(i).setText(unit.getName());
            powerLabel.get(i).textProperty().bind(Bindings.concat("Power(", unit.getPowerUpStringBind(),"): " , unit.getPowerStringBind()));
            shieldLabel.get(i).textProperty().bind(Bindings.concat("Shield(", unit.getShieldUpStringBind(),"): " , unit.getShieldStringBind()));
            healthLabel.get(i).textProperty().bind(Bindings.concat("Health(", unit.getHealthUpStringBind(),"): " , unit.getHealthStringBind()));

            btnFirstAbility.get(i).setText(unit.getMainAbilityName());
            btnSecondAbility.get(i).setText(unit.getSecondAbilityName());
            btnUltAbility.get(i).setText(unit.getUltAbilityName());
        }else{
            unitContainer.get(i).getChildren().clear(); //drugi clear nikomu nie zaszkodzi,a jesli wczesniej cos bylo to tzreba skasować
        }
    }
    
    public void upPower(int i){
        int cost = game.getPlayer().getUnit(i).getPowerCost();
        boolean sure = AlertWindow.confirmBox("Upgrading unit", "Upgrading this stat will cost: " + cost + " crystals and " 
                + GameWindow.upgradeUnitApCost + " ap. Are you sure?", "Upgrade" );
        if(sure) game.getPlayer().upPower(i);
        
    }
    public void upShield(int i){
        int cost = game.getPlayer().getUnit(i).getShieldCost();
        boolean sure = AlertWindow.confirmBox("Upgrading unit", "Upgrading this stat will cost: " + cost + " crystals and " 
                + GameWindow.upgradeUnitApCost + " ap. Are you sure?", "Upgrade" );
        if(sure) game.getPlayer().upShield(i);
    }
    public void upHealth(int i){
        int cost = game.getPlayer().getUnit(i).getHealthCost();
        boolean sure = AlertWindow.confirmBox("Upgrading unit", "Upgrading this stat will cost: " + cost + " crystals and " 
                + GameWindow.upgradeUnitApCost + " ap. Are you sure?", "Upgrade" );
        if(sure) game.getPlayer().upHealth(i);
    }
    
    public void swapLeft(int i){
        int j;
        if(i != 0){
            j = i -1;
        }else{
            j = 4;
        }
        game.getPlayer().swapUnits(i, j);
        bindUnit(i);
        bindUnit(j);
    }
    public void swapRight(int i){
        int j;
        if(i != 4){
            j = i + 1;
        }else{
            j = 0;
        }
        game.getPlayer().swapUnits(i, j);
        bindUnit(i);
        bindUnit(j);
    }
    
    public void showFirstAbility(int i){
        //
    }
    public void showSecondAbility(int i){
        //
    }
    public void showUltAbility(int i){
        //
    }
    
    public void infoGhoul(){
        //
    }
    public void infoVampire(){
        //
    }
    public void infoPlagueman(){
        //
    }
    public void infoPhantom(){
        //
    }
    
    public void infoButcher(){
        //
    }
    
    public void summonGhoul(){
        Unit i = game.getPlayer().summonUnit(0);
        if(i != null){
            bindUnit(i.getPosition());
        }
    }
    public void summonVampire(){
        Unit i = game.getPlayer().summonUnit(1);
        if(i != null){
            bindUnit(i.getPosition());
        }
    }
    public void summonPlagueman(){
        Unit i = game.getPlayer().summonUnit(2);
        if(i != null){
            bindUnit(i.getPosition());
        }
    }
    public void summonPhantom(){
        Unit i = game.getPlayer().summonUnit(3);
        if(i != null){
            bindUnit(i.getPosition());
        }
    }
    
    public void summonButcher(){
        Unit i = game.getPlayer().summonUnit(4);
        if(i != null){
            bindUnit(i.getPosition());
        }
    }
    
    
}
