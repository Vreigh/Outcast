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
import javafx.scene.text.Text;
import javafx.beans.property.*;
import javafx.beans.binding.Bindings;
import java.util.ArrayList;
import javafx.geometry.Insets;

/**
 *
 * @author Admin
 */
public class UnitsController implements Initializable {
    
    private Game game; // referencja do gry
    
    @FXML private HBox addingBox;
    @FXML private HBox unitsContainer;
    
    private ArrayList<VBox> unitContainer = new ArrayList<VBox>();
    
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
            
            unitContainer.add(new VBox());
            unitContainer.get(i).setPrefWidth(150);
            unitContainer.get(i).setSpacing(20);
            unitContainer.get(i).getStyleClass().add("unitContainer");
            unitContainer.get(i).setPadding(new Insets(10, 10, 10, 10));
            
            powerBox.add(new HBox());
            powerBox.get(i).setSpacing(10);
            powerLabel.add(new Label("power"));
            powerLabel.get(i).setPrefWidth(90);
            btnPower.add(new Button("+"));
            btnPower.get(i).setPrefWidth(30);
            btnPower.get(i).setOnAction(e -> upPower(x));
            powerBox.get(i).getChildren().addAll(powerLabel.get(i), btnPower.get(i));
            
            shieldBox.add(new HBox());
            shieldBox.get(i).setSpacing(10);
            shieldLabel.add(new Label("shield"));
            shieldLabel.get(i).setPrefWidth(90);
            btnShield.add(new Button("+"));
            btnShield.get(i).setPrefWidth(30);
            btnShield.get(i).setOnAction(e -> upShield(x));
            shieldBox.get(i).getChildren().addAll(shieldLabel.get(i), btnShield.get(i));
            
            healthBox.add(new HBox());
            healthBox.get(i).setSpacing(10);
            healthLabel.add(new Label("health"));
            healthLabel.get(i).setPrefWidth(90);
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
            
            unitContainer.get(i).getChildren().addAll(powerBox.get(i),
                    healthBox.get(i),
                    shieldBox.get(i),
                    btnFirstAbility.get(i),
                    btnSecondAbility.get(i),
                    btnUltAbility.get(i),
                    swapBox.get(i));
            
            unitsContainer.getChildren().add(unitContainer.get(i));
        }
    }
    public void setGame(Game game){
        this.game = game;
    }
    public void bind(){
        for(int i = 0; i<5; i++){
            bindUnit(i);
        }
    }
    private void bindUnit(int i){
        
    }
    
    public void upPower(int i){
        
    }
    public void upShield(int i){
        
    }
    public void upHealth(int i){
        
    }
    
    public void swapLeft(int i){
        System.out.println("left" + i);
    }
    public void swapRight(int i){
        System.out.println("right" + i);
    }
    
    public void showFirstAbility(int i){
        
    }
    public void showSecondAbility(int i){
        
    }
    public void showUltAbility(int i){
        
    }
    
    public void infoGhoul(){
        
    }
    public void infoPhantom(){
        
    }
    public void infoVampire(){
        
    }
    public void infoButcher(){
        
    }
    public void infoPlagueman(){
        
    }
    
    public void summonGhoul(){
        
    }
    public void summonPhantom(){
        
    }
    public void summonVampire(){
        
    }
    public void summonButcher(){
        
    }
    public void summonPlagueman(){
        
    }
    
}
