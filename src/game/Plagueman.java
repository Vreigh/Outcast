package game;
import java.util.ArrayList;

public final class Plagueman extends Unit {
    
    public Plagueman(){
        super();
        super.setSpeed(75);
        
        super.power.bind(super.powerUp.multiply(10).add(40));
        super.health.bind(super.healthUp.multiply(20).add(80));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getMainAbilityName(){
        return "Poison";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getSecondAbilityName(){
        return "Blight";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getUltAbilityName(){
        return "Cadaverous Wind";
    }
    public String getName(){
        return "Plagueman";
    }
}
