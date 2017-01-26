package game;
import helpers.BattleLog;
import java.util.ArrayList;

public final class Plagueman extends Unit {
    public static final String poison1 = "Plague Poison";
    public static final String poison2 = "Neurotoxin";
    public static final String poison3 = "Black Death";
    
    public static final String buff1 = "Plague Touch";
    public static final String buff2 = "Blight";
    public static final String buff3 = "Grey Rot";
    
    public Plagueman(Armory armory, int i){
        super(armory, i);
        super.setSpeed(60);
        
        super.power.bind(super.powerUp.multiply(9).add(45));
        super.health.bind(super.healthUp.multiply(20).add(80));
        super.shield.bind(super.shieldUp.multiply(5).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        addEnergy(45);
        if(monster.findDot(poison1) == null){
            monster.addDot(new Dot(3, (int)(getTmpPower() * 0.4), poison1));
            return new BattleLog(getFullName() + " used " + getMainAbilityName() + ", putting " + poison1 + " on the enemy!" );
        }else{
            monster.addBuff(new Buff(3,0,-(int)(getTmpPower() * 0.25), buff1));
            return new BattleLog(getFullName() + " used " + getMainAbilityName() + ", putting " + buff1 + " on the enemy!" );
        }
    }
    public String getMainAbilityName(){
        return "Plague Touch";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(25)) return null;
        addEnergy(-25);
        
        if(monster.findBuff(buff1) != null){
            monster.removeBuff(buff1);
            monster.addDot(new Dot(3, (int)(getTmpPower() * 0.5), poison2));
            return new BattleLog(getFullName() + " used " + getSecondAbilityName() + ", removing " + buff1 + " to put " + poison2 + " on the enemy!" );
        }else{
            monster.addBuff(new Buff(3,-(int)(getTmpPower() * 0.35),0, buff2));
            return new BattleLog(getFullName() + " used " + getSecondAbilityName() + ", putting " + buff2 + " on the enemy!" );
        }
    }
    public String getSecondAbilityName(){
        return "Blight";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(100)) return null;
        addEnergy(-100);
        // więcej buffów - trucizna, więcej dotów - buff. Jesli tyle samo to buff
        if(monster.buffNumber() > monster.dotNumber()){
            double scale = 0.3 + 0.2*monster.buffNumber();
            if(scale > 1.1) scale = 1.1;
            int power = (int)(getTmpPower() * scale);
            
            monster.addOrRefreshDot(new Dot(3, power, poison3), true);
            return new BattleLog(getFullName() + " used " + getUltAbilityName() + ", putting " + poison3 + " on the enemy!" );
        }else{
            double scale = 0.2 + 0.1*monster.dotNumber();
            if(scale > 0.6) scale = 0.6;
            int power = -(int)(getTmpPower() * scale);
            
            monster.addOrRefreshBuff(new Buff(3, 0, power, buff3), false);
            return new BattleLog(getFullName() + " used " + getUltAbilityName() + ", putting " + buff3 + " on the enemy!" );
        }
        
    }
    public String getUltAbilityName(){
        return "Cadaverous Wind";
    }
    public String getName(){
        return "Plagueman";
    }
}
