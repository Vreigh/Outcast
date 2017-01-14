package game;
import java.util.ArrayList;

public final class Phantom extends Unit {
    
    public Phantom(Armory armory, int i){
        super(armory, i);
        super.setSpeed(75);
        
        super.power.bind(super.powerUp.multiply(7).add(35));
        super.health.bind(super.healthUp.multiply(25).add(100));
        super.shield.bind(super.shieldUp.multiply(5).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        int i = AlertWindow.getTarget(armory, armory.getUnits(), monster);
        if(i == -1) return null;
        if(i == 5){
            int dmgDone = monster.takeDamage(RNG.randomize((int)(getTmpPower() * 0.5), Game.RAND));
            addBuff(new Buff(3, (int)(getTmpPower() * 0.25), 0, "Preparation"));
            
            addEnergy(20);
            return new BattleLog(getFullName() + " used " + getMainAbilityName() + " dealing " + dmgDone + " damage and putting Preparation on itself");
        }else{
            int j = AlertWindow.getTarget(armory, armory.getUnits(), null);
            if((j == -1) || (i == j)){
                AlertWindow.showInfo(".", "You cant use that ability on the same target!");
                return null;
            }
            int debDur = 2; int bufDur = 2;
            if(i == getPosition()) debDur++;
            if(j == getPosition()) bufDur++;
            
            Unit target1 = armory.get(i);
            Unit target2 = armory.get(j);
            
            int debVal = (int)(getTmpPower() * 0.5);
            int dif = target1.getTmpPower() - debVal;
            if(dif < 0){
                debVal += dif;
            }
            
            target1.addBuff(new Buff(debDur, -debVal, 0, "Soul Down"));
            target2.addOrRefreshBuff(new Buff(bufDur, (int)(debVal * 1.5), 0, "Soul Up"), true);
            
            addEnergy(50);
            
            return new BattleLog(getFullName() + " used " + getMainAbilityName() + " lowering power of " + target1.getFullName() + " and increasing power of " + target2.getFullName());
        }
    }
    public String getMainAbilityName(){
        return "Power Shift";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(40)) return null;
        
        int dmgDone = monster.takeDamage(RNG.randomize((int)(getTmpPower() * 0.9), Game.RAND));
        monster.reducePriority(20 + dmgDone / 2);
        
        addEnergy(-40);
        return new BattleLog(BattleLog.getUnitDmgLog(getFullName(), getSecondAbilityName(), dmgDone) + " and intimidating enemy!");
    }
    public String getSecondAbilityName(){
        return "Terror";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(100)) return null;
        for(Unit unit : armory.getUnits()){
            if(unit.isAlive() == 1) unit.takeRealDamage(RNG.randomize((int)(getTmpPower() * 0.25), Game.RAND));
        }
        for(Unit unit : armory.getUnits()){
            if(unit.isAlive() == 1) unit.addOrRefreshBuff(new Buff(3, (int)(getTmpPower() * 0.9), 0, getUltAbilityName()), true);
        }
        findBuff(getUltAbilityName()).increaseTime();
        addEnergy(-100);
        return new BattleLog(getFullName() + " used " + getUltAbilityName() + " , damaging your units and putting" + getUltAbilityName() + " on them!");
    }
    public String getUltAbilityName(){
        return "Psychic Scream";
    }
    public String getName(){
        return "Phantom";
    }
}
