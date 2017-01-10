package game;
import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Combat {
    
    private ArrayList<Unit> units;
    private Monster monster;
    private Game game;
    
    private ObservableList<BattleLog> battleLogs = FXCollections.observableArrayList();
    
    
    public Combat(Game game){
        this.units = game.getPlayer().getUnits();
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
            game.combatLost();
            return -1;
        }else if(checkIfWon()){
            game.combatWon();
            return -1;
        }else{
            if(allUsed() == 0){
                setPriority();
            }
            int max = 0;
            int ret = -1;
            for(int i = 0; i<5; i++){
                Unit unit = units.get(i);
                if(!unit.isTargetable()) continue;
                
                if(unit.getPriority() > max){
                    max = unit.getPriority();
                    ret = i;
                }
            }
            if(monster.getPriority() > max){
                return monsterAbility();
            }else return ret;
        }
    }
    public void unitAbility(int i, int choice){
        Unit unit = units.get(i);
        BattleLog log;
        
        switch(choice){
            case 0:
                log = unit.mainAbility(units, monster, i);
                break;
            case 1:
                log = unit.secondAbility(units, monster, i);
                break;
            case 2:
                log = unit.ultAbility(units, monster, i);
                break;
            default:
                log = null;
                break;
        }
        if(log == null){
            return;
        }
        battleLogs.add(log);
        unit.afterTurn(battleLogs, i);
        
        game.getCombatController().nextTurn();
    }
    private int monsterAbility(){
        BattleLog log = monster.makeMove(units);
        battleLogs.add(log);
        monster.afterTurn(battleLogs, 5);
        
        return getNextActor();
    }
    private boolean checkIfLost(){
        for(Unit unit : units){
            if(unit.getName() == "flag") continue;
            if(unit.getTmpHealth() > 0){
                return false;
            }
        }
        return true;
    }
    private boolean checkIfWon(){
        return (monster.getTmpHealth() == 0);
    }
    private int allUsed(){
        return monster.getPriority() + units.stream().mapToInt(Unit::getPriority).sum();
    }
    private void setPriority(){
        monster.setPriority();
        for(Unit unit : units){
            unit.setPriority();
        }
    }
}
