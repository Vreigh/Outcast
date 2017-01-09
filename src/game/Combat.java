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
                //monster = new Shadow();
                break;
            case 2:
                //monster = new Boss();
                break;
                
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
                if(unit.getName() == "flag" || unit.getTmpHealth() == 0) continue;
                
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
                log = unit.mainAbility(units, monster);
                break;
            case 1:
                log = unit.secondAbility(units, monster);
                break;
            case 2:
                log = unit.ultAbility(units, monster);
                break;
            default:
                log = new BattleLog("cos sie zjebalo");
        }
        battleLogs.add(log);
        unit.afterTurn();
        
        game.getCombatController().nextTurn();
    }
    private int monsterAbility(){
        battleLogs.add(new BattleLog("Potwor jebnal"));
        monster.afterTurn();
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
