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

/**
 *
 * @author Admin
 */
public class CombatController implements Initializable {
    
    private Game game;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setGame(Game game){
        this.game = game;
    }
    public void bind(){
        
    }
    
}