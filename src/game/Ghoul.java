package game;
import java.util.ArrayList;

public final class Ghoul extends Unit {
    
    public Ghoul(){
        super();
        super.setSpeed(60);
        
        super.power.bind(super.powerUp.multiply(10).add(40));
        super.health.bind(super.healthUp.multiply(25).add(100));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getMainAbilityName(){
        return "Attack";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getSecondAbilityName(){
        return "Frenzy";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getUltAbilityName(){
        return "Devour";
    }
    public String getName(){
        return "Ghoul";
    }
}
