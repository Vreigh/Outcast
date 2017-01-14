package game;

import game.GameWindow;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.shape.Rectangle;

public class CombatController implements Initializable {
    
    private GameWindow game;
    private Combat combat;
    
    @FXML private VBox mainBox;
    private HBox unitsContainer = new HBox();
    
    @FXML private Label monsterName;
    @FXML private Label monsterEnergy;
    @FXML private Label monsterPower;
    @FXML private Label monsterShield;
    @FXML private Label monsterHealth;
    @FXML private Rectangle monsterHealthRec;

    private TableView<BattleLog> battleLogs = new TableView<BattleLog>();
    
    private ArrayList<VBox> unitContainer = new ArrayList<VBox>();
    private ArrayList<Label> unitName = new ArrayList<Label>();
    
    private ArrayList<Label> powerLabel = new ArrayList<Label>();
    private ArrayList<Label> shieldLabel = new ArrayList<Label>();
    private ArrayList<Pane> healthPane = new ArrayList<Pane>();
    
    private ArrayList<Rectangle> healthRec = new ArrayList<Rectangle>();
    private ArrayList<Label> healthLabel = new ArrayList<Label>();
    private ArrayList<Label> energyLabel = new ArrayList<Label>();
    
    private ArrayList<Button> btnFirstAbility = new ArrayList<Button>();
    private ArrayList<Button> btnSecondAbility = new ArrayList<Button>();
    private ArrayList<Button> btnUltAbility = new ArrayList<Button>();
    
    private ArrayList<HBox> showBox = new ArrayList<HBox>();
    private ArrayList<Button> btnShowBuffs = new ArrayList<Button>();
    private ArrayList<Button> btnShowDots = new ArrayList<Button>();
    
    private TableView<Buff> monsterBuffTable = new TableView<Buff>();
    private TableView<Dot> monsterDotTable = new TableView<Dot>();
    private ArrayList<TableView<Buff>> unitBuffTable = new ArrayList<TableView<Buff>>();
    private ArrayList<TableView<Dot>> unitDotTable = new ArrayList<TableView<Dot>>();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TableColumn<BattleLog, String> contentColumn = new TableColumn<BattleLog, String>("Battle Logs");
        contentColumn.setCellValueFactory(new PropertyValueFactory<BattleLog, String>("content"));
        contentColumn.setPrefWidth(840);
        
        battleLogs.getColumns().addAll(contentColumn);
        battleLogs.setPrefHeight(180);
        battleLogs.setPrefWidth(840);
        battleLogs.setPlaceholder(new Label("The battle has begun!"));
        
        unitsContainer.setSpacing(10);
        mainBox.getChildren().addAll(battleLogs, unitsContainer);
        
        prepareMonsterTable();

        for(int i=0; i<5; i++){
            Integer x = new Integer(i);
            unitContainer.add(new VBox());
            unitContainer.get(i).setSpacing(3);
            unitContainer.get(i).setPrefWidth(160);
            unitContainer.get(i).setPrefHeight(230);
            unitContainer.get(i).getStyleClass().add("unitContainer");
            
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
            
            healthRec.add(new Rectangle(160, 20));
            healthRec.get(i).getStyleClass().add("green");
            
            healthPane.add(new Pane());
            healthPane.get(i).setPrefHeight(20);
            healthPane.get(i).setPrefWidth(160);
            
            healthPane.get(i).getChildren().addAll(healthRec.get(i), healthLabel.get(i));
            
            energyLabel.add(new Label("100 / 100"));
            energyLabel.get(i).setPrefHeight(20);
            energyLabel.get(i).setPrefWidth(160);
            energyLabel.get(i).setAlignment(Pos.CENTER);
            
            btnFirstAbility.add(new Button("ability1"));
            btnFirstAbility.get(i).setOnAction(e -> useAbility(x, 0));
            btnFirstAbility.get(i).setPrefHeight(25);
            btnFirstAbility.get(i).setPrefWidth(160);
            
            btnSecondAbility.add(new Button("ability2"));
            btnSecondAbility.get(i).setOnAction(e -> useAbility(x, 1));
            btnSecondAbility.get(i).setPrefHeight(25);
            btnSecondAbility.get(i).setPrefWidth(160);
            
            btnUltAbility.add(new Button("ability3"));
            btnUltAbility.get(i).setOnAction(e -> useAbility(x, 2));
            btnUltAbility.get(i).setPrefHeight(25);
            btnUltAbility.get(i).setPrefWidth(160);
            
            showBox.add(new HBox());
            showBox.get(i).setSpacing(20);
            
            btnShowBuffs.add(new Button("buffs"));
            btnShowBuffs.get(i).setOnAction(e -> showBuffs(x));
            btnShowBuffs.get(i).setPrefWidth(70);
            btnShowBuffs.get(i).setPrefHeight(25);
            
            btnShowDots.add(new Button("dots"));
            btnShowDots.get(i).setOnAction(e -> showDots(x));
            btnShowDots.get(i).setPrefWidth(70);
            btnShowDots.get(i).setPrefHeight(25);
            
            showBox.get(i).getChildren().addAll(btnShowBuffs.get(i), btnShowDots.get(i));
            
            unitContainer.get(i).getChildren().addAll(
                    unitName.get(i),
                    powerLabel.get(i),
                    shieldLabel.get(i),
                    healthPane.get(i),
                    energyLabel.get(i),
                    showBox.get(i)
            );
            
            unitsContainer.getChildren().add(unitContainer.get(i));
            
            TableColumn<Buff, Integer> powerColumn = new TableColumn<Buff, Integer>("Power");
            powerColumn.setMinWidth(100);
            powerColumn.setCellValueFactory(new PropertyValueFactory<Buff, Integer>("power"));

            TableColumn<Buff, Integer> shieldColumn = new TableColumn<Buff, Integer>("Shield");
            shieldColumn.setMinWidth(100);
            shieldColumn.setCellValueFactory(new PropertyValueFactory<Buff, Integer>("shield"));
            
            TableColumn<Buff, String> nameColumn = new TableColumn<Buff, String>("Name");
            nameColumn.setMinWidth(150);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Buff, String>("name"));
            
            TableColumn<Buff, Integer> timeColumn = new TableColumn<Buff, Integer>("Time");
            timeColumn.setMinWidth(100);
            timeColumn.setCellValueFactory(new PropertyValueFactory<Buff, Integer>("time"));
            
            unitBuffTable.add(new TableView<Buff>());
            unitBuffTable.get(i).getColumns().addAll(powerColumn, shieldColumn, nameColumn, timeColumn);
            unitBuffTable.get(i).setPlaceholder(new Label("No buffs!"));
            
            
            TableColumn<Dot, Integer> damageColumn = new TableColumn<Dot, Integer>("Damage");
            damageColumn.setMinWidth(100);
            damageColumn.setCellValueFactory(new PropertyValueFactory<Dot, Integer>("damage"));
            
            TableColumn<Dot, String> dotNameColumn = new TableColumn<Dot, String>("Name");
            dotNameColumn.setMinWidth(150);
            dotNameColumn.setCellValueFactory(new PropertyValueFactory<Dot, String>("name"));
            
            TableColumn<Dot, Integer> dotTimeColumn = new TableColumn<Dot, Integer>("Time");
            dotTimeColumn.setMinWidth(100);
            dotTimeColumn.setCellValueFactory(new PropertyValueFactory<Dot, Integer>("time"));
            
            unitDotTable.add(new TableView<Dot>());
            unitDotTable.get(i).getColumns().addAll(damageColumn, dotNameColumn, dotTimeColumn);
            unitDotTable.get(i).setPlaceholder(new Label("No dots!"));
        }
    }
    public void setGame(GameWindow game){
        this.game = game;
    }
    public void bind(){
        combat = game.getCombat();
        Monster monster = combat.getMonster();
        
        combat.setTable(battleLogs);
        
        monsterName.setText(monster.getName());
        monsterHealth.textProperty().bind(Bindings.concat("Hp: ", monster.getTmpHealthStringBind(), " / ", monster.getHealthStringBind()));
        monsterEnergy.textProperty().bind(Bindings.concat("En: ", monster.getEnergyStringBind()));
        monster.setBuffTable(monsterBuffTable);
        monster.setDotTable(monsterDotTable);
        
        for(int i=0; i<5; i++){
            Unit unit = game.getPlayer().getUnit(i);
            if( unit != null ){
                unitName.get(i).setText(unit.getName());
                healthLabel.get(i).textProperty().bind(Bindings.concat("Hp: ", unit.getTmpHealthStringBind(), " / ", unit.getHealthStringBind()));
                energyLabel.get(i).textProperty().bind(Bindings.concat("En: ", unit.getEnergyStringBind()));
                btnFirstAbility.get(i).setText(unit.getMainAbilityName());
                btnSecondAbility.get(i).setText(unit.getSecondAbilityName());
                btnUltAbility.get(i).setText(unit.getUltAbilityName());
                
                unit.setBuffTable(unitBuffTable.get(i));
                unit.setDotTable(unitDotTable.get(i));
            }else{
                unitContainer.get(i).getChildren().clear();
            }
        }
    }
    private void resetMonsterStats(){
        Monster monster = combat.getMonster();
        
        monsterPower.setText("P: " + monster.getTmpPower() + " / " + monster.getPower());
        monsterShield.setText("Sh: " + monster.getTmpShield() + " / " + monster.getShield());
        
        int proc = monster.getHealthProc();
        int width = proc * 4;
        monsterHealthRec.setWidth(width);
        monsterHealthRec.getStyleClass().clear();
        String name = "green";
        if(proc < 25){
            name = "red";
        }else if(proc < 33){
            name = "orange";
        }else if(proc < 50){
            name = "yellow";
        }
        monsterHealthRec.getStyleClass().add(name);
    }
    private void resetUnitsStats(){
        for(int i = 0; i<5; i++){
            Unit unit = game.getPlayer().getUnit(i);
            if( unit != null){
                powerLabel.get(i).setText("P: " + unit.getTmpPower() + " / " + unit.getPower());
                shieldLabel.get(i).setText("Sh: " + unit.getTmpShield() + " / " + unit.getShield());
                
                int proc = unit.getHealthProc();
                int width = (int)(proc * 1.6);
                healthRec.get(i).setWidth(width);
                healthRec.get(i).getStyleClass().clear();
                String name = "green";
                if(proc < 25){
                    name = "red";
                }else if(proc < 33){
                    name = "orange";
                }else if(proc < 50){
                    name = "yellow";
                }
                healthRec.get(i).getStyleClass().add(name);
            }
        }
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
    private void showAbilities(int i){
        // TO DO: nie pokazywać wszystkich umiejętności
        //Unit unit = game.getPlayer().getUnit(i);
        // unit.getMainAbilityCost() > unit.getEnergy()
        unitContainer.get(i).getChildren().addAll(
                btnFirstAbility.get(i),
                btnSecondAbility.get(i),
                btnUltAbility.get(i)
            );
    }
    private void prepareMonsterTable(){
            TableColumn<Buff, Integer> powerColumn = new TableColumn<Buff, Integer>("Power");
            powerColumn.setMinWidth(100);
            powerColumn.setCellValueFactory(new PropertyValueFactory<Buff, Integer>("power"));

            TableColumn<Buff, Integer> shieldColumn = new TableColumn<Buff, Integer>("Shield");
            shieldColumn.setMinWidth(100);
            shieldColumn.setCellValueFactory(new PropertyValueFactory<Buff, Integer>("shield"));
            
            TableColumn<Buff, String> nameColumn = new TableColumn<Buff, String>("Name");
            nameColumn.setMinWidth(150);
            nameColumn.setCellValueFactory(new PropertyValueFactory<Buff, String>("name"));
            
            TableColumn<Buff, Integer> timeColumn = new TableColumn<Buff, Integer>("Time");
            timeColumn.setMinWidth(100);
            timeColumn.setCellValueFactory(new PropertyValueFactory<Buff, Integer>("time"));
            
            monsterBuffTable.getColumns().addAll(powerColumn, shieldColumn, nameColumn, timeColumn);
            monsterBuffTable.setPlaceholder(new Label("No buffs!"));
            
            
            TableColumn<Dot, Integer> damageColumn = new TableColumn<Dot, Integer>("Damage");
            damageColumn.setMinWidth(100);
            damageColumn.setCellValueFactory(new PropertyValueFactory<Dot, Integer>("damage"));
            
            TableColumn<Dot, String> dotNameColumn = new TableColumn<Dot, String>("Name");
            dotNameColumn.setMinWidth(150);
            dotNameColumn.setCellValueFactory(new PropertyValueFactory<Dot, String>("name"));
            
            TableColumn<Dot, Integer> dotTimeColumn = new TableColumn<Dot, Integer>("Time");
            dotTimeColumn.setMinWidth(100);
            dotTimeColumn.setCellValueFactory(new PropertyValueFactory<Dot, Integer>("time"));
            
            monsterDotTable.getColumns().addAll(damageColumn, dotNameColumn, dotTimeColumn);
            monsterDotTable.setPlaceholder(new Label("No dots!"));
    }
    
    
    //////////////////////////////////////////////
    public void showMonsterBuffs(){
        AlertWindow.showTable(monsterBuffTable);
    }
    public void showMonsterDots(){
        AlertWindow.showTable(monsterDotTable, true);
    }
    public void showBuffs(int i){
        AlertWindow.showTable(unitBuffTable.get(i));
    }
    public void showDots(int i){
        AlertWindow.showTable(unitDotTable.get(i), true);
    }
    ////////////////////////////////////////////////////
    public void nextTurn(){
        int i = combat.getNextActor();

        hideAbilities();
        resetMonsterStats();
        resetUnitsStats();
        if(i == -1){
            return;
        }
        showAbilities(i);
    }
    public void useAbility(int i, int choice){
        combat.unitAbility(i, choice);
    }
    
}
