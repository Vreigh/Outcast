package game;
import java.util.ArrayList;

public final class Butcher extends Unit {
    
    public Butcher(){
        super();
        super.setSpeed(30);
        
        super.power.bind(super.powerUp.multiply(10).add(25));
        super.health.bind(super.healthUp.multiply(50).add(200));
        super.shield.bind(super.shieldUp.multiply(12).add(15));
        
        super.tmpHealth.set(health.get());
    }
    
    public void mainAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getMainAbilityName(){
        return "Shove";
    }
    public void secondAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getSecondAbilityName(){
        return "Rotten Shield";
    }
    public void ultAbility(ArrayList<Unit> units, Monster monster){
        
    }
    public String getUltAbilityName(){
        return "Plague Bomb";
    }
    public String getName(){
        return "Butcher";
    }
}
