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
public class TopMenuController implements Initializable {
    
    private GameWindow game; // referencja do gry
    
    @FXML private Label sanityDisplay;
    @FXML private Label spiritsDisplay;
    @FXML private Label manaDisplay;
    @FXML private Label cryDisplay;
    @FXML private Label roundDisplay;
    @FXML private Label apDisplay;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void setGame(GameWindow game){
        this.game = game;
    }
    public void bind(){
        sanityDisplay.textProperty().bind(Bindings.concat("Sanity: ", game.getPlayer().getSanityStringBind()));
        spiritsDisplay.textProperty().bind(Bindings.concat("Spirits: ", game.getPlayer().getFreeSpiritsStringBind(), "/", game.getPlayer().getSpiritsStringBind()));
        manaDisplay.textProperty().bind(Bindings.concat("Mana: ", game.getPlayer().getManaStringBind()));
        cryDisplay.textProperty().bind(Bindings.concat("Crystals: ", game.getPlayer().getCrystalsStringBind()));
        roundDisplay.textProperty().bind(Bindings.concat("Round: ", game.getRoundProperty().asString()));
        apDisplay.textProperty().bind(Bindings.concat("Ap: ", game.getPlayer().getApStringBind(), " / ", game.getPlayer().getMaxApStringBind()));
    }
    public void toggleMusic(){
        game.toggleMusic();
    }
    
}
