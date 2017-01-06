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

/**
 *
 * @author Admin
 */
public class UnitsController implements Initializable {
    
    private Game game; // referencja do gry
    
    @FXML private Label sanityDisplay;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setGame(Game game){
        this.game = game;
    }
    public void bind(){
        //sanityDisplay.textProperty().bind(Bindings.concat("Sanity: ", game.getPlayer().getSanityStringBind()));
    }
    
}
