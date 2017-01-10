package game;
import java.util.ArrayList;

public final class Vampire extends Unit {
    
    public Vampire(){
        super();
        super.setSpeed(45);
        
        super.power.bind(super.powerUp.multiply(8).add(30));
        super.health.bind(super.healthUp.multiply(40).add(150));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Blood Flow";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Drain";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Night Hunt";
    }
    public String getName(){
        return "Vampire";
    }
}
