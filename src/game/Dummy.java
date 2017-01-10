package game;
import java.util.ArrayList;


public class Dummy extends Unit {
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog(".");
    }
    public String getMainAbilityName(){
        return "";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog(".");
    }
    public String getSecondAbilityName(){
        return "";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog(".");
    }
    public String getUltAbilityName(){
        return "";
    }
    public String getName(){
        return "flag";
    }
}
