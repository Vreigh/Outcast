package game;
import java.util.ArrayList;

public final class Butcher extends Unit {
    
    public Butcher(Armory armory, int i){
        super(armory, i);
        super.setSpeed(25);
        
        super.power.bind(super.powerUp.multiply(6).add(30));
        super.health.bind(super.healthUp.multiply(37).add(150));
        super.shield.bind(super.shieldUp.multiply(5).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        int dmgDone = monster.takeDamage(RNG.randomize((int)(getTmpPower() * 0.6), GameWindow.RAND));
        int dot = (int)(getTmpPower() * 0.2);
        monster.addDot(new Dot(2, dot, "Infectous Cloud"));
        addEnergy(50);
        return new BattleLog(BattleLog.getUnitDmgLog(getFullName(), getMainAbilityName(), dmgDone) + " and putting Infectous Cloud on the enemy");
        
    }
    public String getMainAbilityName(){
        return "Shove";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(35)) return null;
        
        int i = AlertWindow.getTarget(armory, armory.getUnits(), null);
        if(i == -1) return null;
        int j = AlertWindow.getTarget(armory, armory.getUnits(), null);
        if((j == -1) || (i == j)) return null;
        
        int dur1 = 2; int dur2 = 2;
        if(i == getPosition()) dur1++;
        if(j == getPosition()) dur2++;
        Unit target1 = armory.get(i);
        Unit target2 = armory.get(j);
        
        target1.addOrRefreshBuff(new Buff(dur1, 0, (int)(getTmpPower() * 1.0), getSecondAbilityName()), true);
        target2.addOrRefreshBuff(new Buff(dur2, 0, (int)(getTmpPower() * 1.0), getSecondAbilityName()), true);
        
        addEnergy(-35);
        return new BattleLog(getFullName() + " used " + getSecondAbilityName() + ", shielding " + target1.getFullName() + " and " + target2.getFullName() + "!");
    }
    public String getSecondAbilityName(){
        return "Rotten Shield";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(100)) return null;
        
        int i = AlertWindow.getTarget(armory, armory.getUnits(), monster);
        if(i == -1) return null;
        
        addEnergy(-100);
        
        if(i == 5){
            int dmgDone = monster.takeDamage(RNG.randomize((int)(getTmpPower() * 1.5), GameWindow.RAND));
            monster.addOrRefreshDot(new Dot(2, (int)(getTmpPower() * 1.5), getUltAbilityName()), true);
            monster.addOrRefreshBuff(new Buff(2, (int)(getTmpPower() * 1.5),0, getUltAbilityName()), false);
            return new BattleLog(BattleLog.getUnitDmgLog(getFullName(), getUltAbilityName(), dmgDone) + " and putting " + getUltAbilityName() + " on it!");
        }else{
            int dur = 2;
            if(i == getPosition()) dur++;
            Unit target = armory.get(i);
            int heal = -target.heal(RNG.randomize((int)(getTmpPower() * 1.5), GameWindow.RAND));
            target.addOrRefreshDot(new Dot(2, (int)(getTmpPower() * 1.5), getUltAbilityName()), false);
            target.addOrRefreshBuff(new Buff(dur, (int)(getTmpPower() * 1.5),0, getUltAbilityName()), true);
            return new BattleLog(getFullName() + " used " + getUltAbilityName() + " healing " + target.getFullName() + " for " + heal + " and putting " + getUltAbilityName() + " on it!");
        }
    }
    public String getUltAbilityName(){
        return "Plague Bomb";
    }
    public String getName(){
        return "Butcher";
    }
}
