/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.GameWindow;
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

/**
 *
 * @author Admin
 */
public class UpgradesController implements Initializable {
    
    private GameWindow game;
    
    @FXML private Label leftMana;
    @FXML private Label rightMana;
    @FXML private Label leftSeed;
    @FXML private Label rightSeed;
    @FXML private Label leftCry;
    @FXML private Label rightCry;
    @FXML private Label leftCap;
    @FXML private Label rightCap;
    @FXML private Label leftAp;
    @FXML private Label rightAp;
    
    @FXML private Button btnMana;
    @FXML private Button btnSeed;
    @FXML private Button btnCry;
    @FXML private Button btnCap;
    @FXML private Button btnAp;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnMana.setUserData(0);
        btnSeed.setUserData(1);
        btnCry.setUserData(2);
        btnCap.setUserData(3);
        btnAp.setUserData(4);
    }
    public void setGame(GameWindow game){
        this.game = game;
    }
    public void bind(){
        leftMana.textProperty().bind(game.getPlayer().getUpgradeLeftLabel(0));
        rightMana.textProperty().bind(game.getPlayer().getUpgradeRightLabel(0));
        leftSeed.textProperty().bind(game.getPlayer().getUpgradeLeftLabel(1));
        rightSeed.textProperty().bind(game.getPlayer().getUpgradeRightLabel(1));
        leftCry.textProperty().bind(game.getPlayer().getUpgradeLeftLabel(2));
        rightCry.textProperty().bind(game.getPlayer().getUpgradeRightLabel(2));
        leftCap.textProperty().bind(game.getPlayer().getUpgradeLeftLabel(3));
        rightCap.textProperty().bind(game.getPlayer().getUpgradeRightLabel(3));
        leftAp.textProperty().bind(game.getPlayer().getUpgradeLeftLabel(4));
        rightAp.textProperty().bind(game.getPlayer().getUpgradeRightLabel(4));
    }
    public void fill(ActionEvent event){
        int i = (int)((Button)event.getSource()).getUserData();
        game.getPlayer().fillUpgrade(i);
    }
    
}
