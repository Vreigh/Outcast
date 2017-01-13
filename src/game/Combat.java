package game;
import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Combat {
    
    private Armory armory;
    private Monster monster;
    private Game game;
    
    private ObservableList<BattleLog> battleLogs = FXCollections.observableArrayList();
    
    
    public Combat(Game game){
        armory = game.getPlayer().getArmory();
        switch(game.getPlayer().getProgress()){
            case 0:
                monster = new Warrior();
                break;
            case 1:
                monster = new Warrior();
                break;
            case 2:
                monster = new Warrior();
                break;
            default:
                monster = new Warrior(); 
        }
        this.game = game;
    }
    public void setTable(TableView<BattleLog> table){
        table.setItems(battleLogs);
    }
    public Monster getMonster(){
        return monster;
    }
    public int getNextActor(){
        if(checkIfLost()){
            game.endCombat(false);
            return -1;
        }else if(checkIfWon()){
            game.endCombat(true);
            return -1;
        }else{
            if(leftPriority() == 0){
                setPriority();
            }
            int max = 0;
            int ret = -1;
            for(Unit unit : armory.getUnits()){
                if(unit.isAlive() == 1){
                    if(unit.getPriority() > max){
                        max = unit.getPriority();
                        ret = unit.getPosition();
                    }
                }
            }
            if(monster.getPriority() > max){
                return monsterAbility();
            }else return ret;
        }
    }
    public void unitAbility(int i, int choice){
        Unit unit = armory.get(i);
        BattleLog log;
        
        switch(choice){
            case 0:
                log = unit.mainAbility(this, armory, monster);
                break;
            case 1:
                log = unit.secondAbility(this, armory, monster);
                break;
            case 2:
                log = unit.ultAbility(this, armory, monster);
                break;
            default:
                log = null;
                break;
        }
        if(log == null){ //jeśli umiejętność nie zwróciła loga, nic się nie dzieje.
            return;
        }
        battleLogs.add(log);
        unit.afterTurn(battleLogs);
        
        game.getCombatController().nextTurn();
    }
    private int monsterAbility(){
        BattleLog log = monster.makeMove(this, armory);
        battleLogs.add(log);
        monster.afterTurn(battleLogs);
        
        return getNextActor();
    }
    private boolean checkIfLost(){
        return (armory.getAlive() == 0);
    }
    private boolean checkIfWon(){
        return (monster.getTmpHealth() == 0);
    }
    private int leftPriority(){
        return monster.getPriority() + armory.getUnits().stream().mapToInt(Unit::getPriority).sum();
    }
    private void setPriority(){
        monster.setPriority();
        for(Unit unit : armory.getUnits()){
            unit.setPriority();
        }
    }
}
