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
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster, int i){
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = monster.takeDamage(dmg);
        
        addEnergy(40);
        
        return new BattleLog(BattleLog.getUnitDmgLog(getName(), getMainAbilityName(), i, dmgDone));
    }
    public String getMainAbilityName(){
        return "Attack";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster, int i){
        if(!checkEnergy(25)) return null;
        
        int buf = (getTmpPower() / 4);
        addBuff(new Buff(2, buf, -buf, "Frenzy"));
        
        int dmg1 = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone1 = monster.takeDamage(dmg1);
        
        int dmg2 = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone2 = monster.takeDamage(dmg2);
        
        String name = getName() + "(" + i + ")";
        
        addEnergy(-25);
        
        return new BattleLog(name + " used " + getSecondAbilityName() + " dealing " + dmgDone1 + ", and " + dmgDone2 + " damage");
         
    }
    public String getSecondAbilityName(){
        return "Frenzy";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster, int i){
        if(!checkEnergy(100)) return null;
        
        int dmg = RNG.randomize((int)(getTmpPower() * 1.2), Game.RAND);
        int dmgDone = monster.takeDamage(dmg);
        heal(dmgDone);
        
        addEnergy(-100);
        
        return new BattleLog(BattleLog.getUnitDmgLog(getName(), getUltAbilityName(), i, dmgDone) + " healing itself for " + dmgDone);
    }
    public String getUltAbilityName(){
        return "Devour";
    }
    public String getName(){
        return "Ghoul";
    }
}
