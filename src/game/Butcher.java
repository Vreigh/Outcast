package game;
import java.util.ArrayList;

public final class Butcher extends Unit {
    
    public Butcher(Player player){
        super();
        super.setSpeed(30);
        super.player = player;
        range = 2;
        
        super.power.bind(super.powerUp.multiply(10).add(25));
        super.health.bind(super.healthUp.multiply(50).add(200));
        super.shield.bind(super.shieldUp.multiply(12).add(15));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getMainAbilityName(){
        return "Shove";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getSecondAbilityName(){
        return "Rotten Shield";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster, int i){
        return new BattleLog("oirsgjoigjoiregre");
    }
    public String getUltAbilityName(){
        return "Plague Bomb";
    }
    public String getName(){
        return "Butcher";
    }
}
