package game;
import java.util.ArrayList;

public final class Butcher extends Unit {
    
    public Butcher(Armory armory, int i){
        super(armory, i);
        super.setSpeed(25);
        
        super.power.bind(super.powerUp.multiply(6).add(30));
        super.health.bind(super.healthUp.multiply(37).add(150));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Shove";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Rotten Shield";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Plague Bomb";
    }
    public String getName(){
        return "Butcher";
    }
}
