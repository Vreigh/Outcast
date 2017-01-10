package game;
import java.util.ArrayList;

public final class Phantom extends Unit {
    
    public Phantom(){
        super();
        super.setSpeed(80);
        
        super.power.bind(super.powerUp.multiply(10).add(25));
        super.health.bind(super.healthUp.multiply(30).add(120));
        super.shield.bind(super.shieldUp.multiply(15).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Power Shift";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Freeze";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Psychic Scream";
    }
    public String getName(){
        return "Phantom";
    }
}
