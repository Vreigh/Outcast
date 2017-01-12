package game;
import java.util.ArrayList;

public final class Plagueman extends Unit {
    
    public Plagueman(Armory armory, int i){
        super(armory, i);
        super.setSpeed(60);
        
        super.power.bind(super.powerUp.multiply(9).add(45));
        super.health.bind(super.healthUp.multiply(20).add(80));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Plague Touch";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Blight";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Cadaverous Wind";
    }
    public String getName(){
        return "Plagueman";
    }
}
