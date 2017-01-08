package game;

import game.Game;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CombatController implements Initializable {
    
    private Game game;
    
    @FXML private VBox mainBox;
    private HBox unitsContainer = new HBox();
    
    private VBox monsterContainer = new VBox();
    private Label monsterEnergy = new Label("energy");
    private Label monsterPower = new Label("power");
    private Label monsterShield = new Label("shield");
    private Label monsterHealth = new Label("health");
    
    private HBox monsterShow = new HBox();
    private Button monsterBuffs = new Button("buffs");
    private Button monsterDots = new Button("dots");
    
    private TableView<BattleLog> battleLogs = new TableView<BattleLog>();
    
    private ArrayList<VBox> unitContainer = new ArrayList<VBox>();
    private ArrayList<Label> unitName = new ArrayList<Label>();
    
    private ArrayList<Label> powerLabel = new ArrayList<Label>();
    private ArrayList<Label> shieldLabel = new ArrayList<Label>();
    private ArrayList<Label> healthLabel = new ArrayList<Label>();
    
    private ArrayList<Button> btnFirstAbility = new ArrayList<Button>();
    private ArrayList<Button> btnSecondAbility = new ArrayList<Button>();
    private ArrayList<Button> btnUltAbility = new ArrayList<Button>();
    
    private ArrayList<HBox> showBox = new ArrayList<HBox>();
    private ArrayList<Button> btnShowBuffs = new ArrayList<Button>();
    private ArrayList<Button> btnShowDots = new ArrayList<Button>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<BattleLog, String> contentColumn = new TableColumn<BattleLog, String>("Battle Log");
        contentColumn.setCellValueFactory(new PropertyValueFactory<BattleLog, String>("content"));
        contentColumn.setPrefWidth(840);
        
        battleLogs.getColumns().addAll(contentColumn);
        battleLogs.setPrefHeight(180);
        battleLogs.setPrefWidth(840);
        battleLogs.setPlaceholder(new Label("The battle has begun!"));
        
        unitsContainer.setSpacing(10);
        
        mainBox.getChildren().addAll(monsterContainer, battleLogs, unitsContainer);
        
        
        
        for(int i=0; i<5; i++){
            Integer x = new Integer(i);
            unitContainer.add(new VBox());
            unitContainer.get(i).setSpacing(5);
            
            unitName.add(new Label("nazwa"));
            unitName.get(i).setPrefHeight(20);
            unitName.get(i).setPrefWidth(160);
            unitName.get(i).setAlignment(Pos.CENTER); 
            
            powerLabel.add(new Label("40 / 40"));
            powerLabel.get(i).setPrefHeight(20);
            powerLabel.get(i).setPrefWidth(160);
            powerLabel.get(i).setAlignment(Pos.CENTER);
            
            shieldLabel.add(new Label("10 / 10"));
            shieldLabel.get(i).setPrefHeight(20);
            shieldLabel.get(i).setPrefWidth(160);
            shieldLabel.get(i).setAlignment(Pos.CENTER);
            
            healthLabel.add(new Label("100 / 100"));
            healthLabel.get(i).setPrefHeight(20);
            healthLabel.get(i).setPrefWidth(160);
            healthLabel.get(i).setAlignment(Pos.CENTER);
            
            btnFirstAbility.add(new Button("ability1"));
            btnFirstAbility.get(i).setPrefHeight(25);
            btnFirstAbility.get(i).setPrefWidth(160);
            
            btnSecondAbility.add(new Button("ability2"));
            btnSecondAbility.get(i).setPrefHeight(25);
            btnSecondAbility.get(i).setPrefWidth(160);
            
            btnUltAbility.add(new Button("ability3"));
            btnUltAbility.get(i).setPrefHeight(25);
            btnUltAbility.get(i).setPrefWidth(160);
            
            showBox.add(new HBox());
            showBox.get(i).setSpacing(20);
            
            btnShowBuffs.add(new Button("buffs"));
            btnShowBuffs.get(i).setPrefWidth(70);
            btnShowBuffs.get(i).setPrefHeight(25);
            
            btnShowDots.add(new Button("dots"));
            btnShowDots.get(i).setPrefWidth(70);
            btnShowDots.get(i).setPrefHeight(25);
            
            showBox.get(i).getChildren().addAll(btnShowBuffs.get(i), btnShowDots.get(i));
            
            unitContainer.get(i).getChildren().addAll(
                    unitName.get(i),
                    powerLabel.get(i),
                    shieldLabel.get(i),
                    healthLabel.get(i),
                    btnFirstAbility.get(i),
                    btnSecondAbility.get(i),
                    btnUltAbility.get(i),
                    showBox.get(i)
            );
            
            unitsContainer.getChildren().add(unitContainer.get(i));
        }
    }
    public void setGame(Game game){
        this.game = game;
    }
    public void bind(){
        game.getCombat().setTable(battleLogs);
    }
    private void hideAbilities(){
        for(int i =0; i<5; i++){
            unitContainer.get(i).getChildren().removeAll(
                btnFirstAbility.get(i),
                btnSecondAbility.get(i),
                btnUltAbility.get(i)
            );
        }
    }
    private void showAbilites(int i){
        unitContainer.get(i).getChildren().addAll(
                btnFirstAbility.get(i),
                btnSecondAbility.get(i),
                btnUltAbility.get(i)
            );
    }
    
}
