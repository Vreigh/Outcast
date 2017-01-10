package game;
import java.util.ArrayList;

public final class Warrior extends Monster {
    
    public Warrior(){
        super();
        setSpeed(50);
        
        super.power.set(50);
        super.health.set(500);
        super.shield.set(15);
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog firstAbility(ArrayList<Unit> units){
        int i = targetNearest(units);
        Unit unit = units.get(i);
        
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = unit.takeDamage(dmg);
        
        addEnergy(10);
        
        return new BattleLog(BattleLog.getMonsterDmgLog(getName(), getFirstAbilityName(), unit, dmgDone, i));
    }
    public String getFirstAbilityName(){
        return "Attack";
    }
    
    public BattleLog secondAbility(ArrayList<Unit> units){
        addBuff(new Buff(2, 0, (getTmpPower() / 2), "Shield Block"));
        addEnergy(25);
        return new BattleLog(BattleLog.getSelfBuffLog(getName(), getSecondAbilityName(), "Shield Block"));
    }
    public String getSecondAbilityName(){
        return "Shield Block";
    }
    
    public BattleLog thirdAbility(ArrayList<Unit> units){
        int i = targetRandom(units);
        Unit unit = units.get(i);
        
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = unit.takeDamage(dmg);
        
        addEnergy(-20);
        
        return new BattleLog(BattleLog.getMonsterDmgLog(getName(), getThirdAbilityName(), unit, dmgDone, i));
    }
    public String getThirdAbilityName(){
        return "Rush";
    }
    
    public BattleLog ultAbility(ArrayList<Unit> units){
        String damageDone = "";
        for(int i = 0; i<5; i++){
            if(units.get(i).isTargetable()){
                int dmg = RNG.randomize((int)(getTmpPower() * 0.75), Game.RAND);
                damageDone += units.get(i).takeDamage(dmg);
                if(units.get(i).getTmpHealth() == 0) damageDone += "(fatal)";
            }
        }
        
        return new BattleLog(BattleLog.getAoeDmgLog(getName(), getUltAbilityName(), damageDone));
    }
    public String getUltAbilityName(){
        return "Blade Dance";
    }
    
    public BattleLog makeMove(ArrayList<Unit> units){
        if(energy.get() > 60){
            if(RNG.roll(energy.get())){
                return ultAbility(units);
            }
        }
        
        if(energy.get() > 30){
            if(RNG.roll(energy.get() + 40)){
                return thirdAbility(units);
            }
        }
        int roll = 30 + (250 - getTmpHealth())/2 ;
        if(roll < 30) roll = 30;
        
        if(RNG.roll(roll)){
            return secondAbility(units);
        }
        
        return firstAbility(units);
    }
    
    public String getName(){
        return "Warrior";
    }
}
