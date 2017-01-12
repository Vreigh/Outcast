package game;
import java.util.ArrayList;

public final class Vampire extends Unit {
    
    public Vampire(Armory armory, int i){
        super(armory, i);
        super.setSpeed(40);
        
        super.power.bind(super.powerUp.multiply(8).add(40));
        super.health.bind(super.healthUp.multiply(30).add(120));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Blood Flow";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Drain";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Night Hunt";
    }
    public String getName(){
        return "Vampire";
    }
}
