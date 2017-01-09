package game;
import java.util.ArrayList;

public final class Warrior extends Monster {
    
    public Warrior(){
        super();
        super.setSpeed(50);
        
        super.power.set(60);
        super.health.set(500);
        super.shield.set(15);
        
        super.tmpHealth.set(health.get());
    }
    
    public void firstAbility(ArrayList<Unit> units){
        
    }
    public String getFirstAbilityName(){
        return "Attack";
    }
    
    public void secondAbility(ArrayList<Unit> units){
        
    }
    public String getSecondAbilityName(){
        return "Shield Block";
    }
    
    public void thirdAbility(ArrayList<Unit> units){
        
    }
    public String getThirdAbilityName(){
        return "Rush";
    }
    
    public void ultAbility(ArrayList<Unit> units){
        
    }
    public String getUltAbilityName(){
        return "Blade Dance";
    }
    
    public String getName(){
        return "Warrior";
    }
}
