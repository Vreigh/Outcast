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

/**
 *
 * @author Admin
 */
public class LeftMenuController implements Initializable {
    
    private GameWindow game;
    
    @FXML private Button btnMain;
    @FXML private Button btnUpgrades;
    @FXML private Button btnUnits;
    @FXML private Button btnJournal;
    @FXML private Button btnCombat;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnMain.setUserData(0);
        btnUpgrades.setUserData(1);
        btnUnits.setUserData(2);
        btnJournal.setUserData(3);
        btnCombat.setUserData(4);
    }
    public void switchView(ActionEvent event) {
        int header = (int)((Button)event.getSource()).getUserData();
        game.switchView(header);
    }
    public void setGame(GameWindow game){
        this.game = game;
        
        
    }
    
}
