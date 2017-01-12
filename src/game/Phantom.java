package game;
import java.util.ArrayList;

public final class Phantom extends Unit {
    
    public Phantom(Armory armory, int i){
        super(armory, i);
        super.setSpeed(75);
        
        super.power.bind(super.powerUp.multiply(7).add(35));
        super.health.bind(super.healthUp.multiply(25).add(100));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Power Shift";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Freeze";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Psychic Scream";
    }
    public String getName(){
        return "Phantom";
    }
}
