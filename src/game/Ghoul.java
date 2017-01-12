package game;
import java.util.ArrayList;

public final class Ghoul extends Unit {
    
    public Ghoul(Armory armory, int i){
        super(armory, i);
        super.setSpeed(50);
        
        super.power.bind(super.powerUp.multiply(10).add(50));
        super.health.bind(super.healthUp.multiply(25).add(100));
        super.shield.bind(super.shieldUp.multiply(10).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        if(hit(2)){
            int dmg = RNG.randomize(getTmpPower(), Game.RAND);
            int dmgDone = monster.takeDamage(dmg);
        
            addEnergy(40);
        
            return new BattleLog(BattleLog.getUnitDmgLog(getFullName(), getMainAbilityName(), dmgDone));
        }else{
            return new BattleLog(miss());
        }
        
    }
    public String getMainAbilityName(){
        return "Attack";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(25)) return null;
        if(hit(2)){
            int buf = (getTmpPower() / 4);
            addBuff(new Buff(2, buf, -buf, "Frenzy"));

            int dmg1 = RNG.randomize((int)(getTmpPower() * 0.75), Game.RAND);
            int dmgDone1 = monster.takeDamage(dmg1);

            int dmg2 = RNG.randomize((int)(getTmpPower() * 0.75), Game.RAND);
            int dmgDone2 = monster.takeDamage(dmg2);

            addEnergy(-25);

            return new BattleLog(getFullName() + " used " + getSecondAbilityName() + " dealing " + dmgDone1 + ", and " + dmgDone2 + " damage"); 
        }else{
            return new BattleLog(miss());
        }
    }
    public String getSecondAbilityName(){
        return "Frenzy";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(100)) return null;
        if(hit(2)){
            double scale = 1.2;
            int proc = monster.getHealthProc();
            if(proc < 50){
                scale += 0.3;
            }
            if(proc < 33){
                scale += 0.3;
            }
            if(proc < 25){
                scale += 0.2;
            }
            int dmg = RNG.randomize((int)(getTmpPower() * scale), Game.RAND);
            int dmgDone = monster.takeDamage(dmg);
            heal(dmgDone);
        
            addEnergy(-100);
        
            return new BattleLog(BattleLog.getUnitDmgLog(getFullName(), getUltAbilityName(), dmgDone) + " healing itself for " + dmgDone);
        }else{
            return new BattleLog(miss());
        }
    }
    public String getUltAbilityName(){
        return "Devour";
    }
    public String getName(){
        return "Ghoul";
    }
}
