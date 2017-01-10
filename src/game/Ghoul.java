package game;
import java.util.ArrayList;

public final class Ghoul extends Unit {
    
    public Ghoul(Player player){
        super();
        super.setSpeed(50);
        range = 2;
        super.player = player;
        
        super.power.bind(super.powerUp.multiply(10).add(40));
        super.health.bind(super.healthUp.multiply(25).add(100));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(ArrayList<Unit> units, Monster monster, int i){
        if(hit(i)){
            int dmg = RNG.randomize(getTmpPower(), Game.RAND);
            int dmgDone = monster.takeDamage(dmg);
        
            addEnergy(40);
        
            return new BattleLog(BattleLog.getUnitDmgLog(getFullName(i), getMainAbilityName(), i, dmgDone));
        }else{
            return new BattleLog(miss(i));
        }
        
    }
    public String getMainAbilityName(){
        return "Attack";
    }
    public BattleLog secondAbility(ArrayList<Unit> units, Monster monster, int i){
        if(!checkEnergy(25)) return null;
        if(hit(i)){
            int buf = (getTmpPower() / 4);
            addBuff(new Buff(2, buf, -buf, "Frenzy"));

            int dmg1 = RNG.randomize((int)(getTmpPower() * 0.75), Game.RAND);
            int dmgDone1 = monster.takeDamage(dmg1);

            int dmg2 = RNG.randomize((int)(getTmpPower() * 0.75), Game.RAND);
            int dmgDone2 = monster.takeDamage(dmg2);

            addEnergy(-25);

            return new BattleLog(getFullName(i) + " used " + getSecondAbilityName() + " dealing " + dmgDone1 + ", and " + dmgDone2 + " damage"); 
        }else{
            return new BattleLog(miss(i));
        }
    }
    public String getSecondAbilityName(){
        return "Frenzy";
    }
    public BattleLog ultAbility(ArrayList<Unit> units, Monster monster, int i){
        if(!checkEnergy(100)) return null;
        if(hit(i)){
            int dmg = RNG.randomize((int)(getTmpPower() * 1.2), Game.RAND);
            int dmgDone = monster.takeDamage(dmg);
            heal(dmgDone);
        
            addEnergy(-100);
        
            return new BattleLog(BattleLog.getUnitDmgLog(getFullName(i), getUltAbilityName(), i, dmgDone) + " healing itself for " + dmgDone);
        }else{
            return new BattleLog(miss(i));
        }
    }
    public String getUltAbilityName(){
        return "Devour";
    }
    public String getName(){
        return "Ghoul";
    }
}
