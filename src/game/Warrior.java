package game;
import java.util.ArrayList;

public final class Warrior extends Monster {
    
    public Warrior(){
        super();
        setSpeed(60);
        
        super.power.set(60);
        super.health.set(250);
        super.shield.set(-20);
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog firstAbility(ArrayList<Unit> units){
        int i = targetNearest(units);
        Unit unit = units.get(i);
        
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = unit.takeDamage(dmg);
        
        addEnergy(20);
        
        return new BattleLog(BattleLog.getMonsterDmgLog(getName(), getFirstAbilityName(), unit, dmgDone, i));
    }
    public String getFirstAbilityName(){
        return "Attack";
    }
    
    public BattleLog secondAbility(ArrayList<Unit> units){
        addBuff(new Buff(4, 0, 50, getSecondAbilityName()));
        addEnergy(25);
        return new BattleLog(BattleLog.getSelfBuffLog(getName(), getSecondAbilityName(), getSecondAbilityName()));
    }
    public String getSecondAbilityName(){
        return "Angelic Preservance";
    }
    
    public BattleLog thirdAbility(ArrayList<Unit> units){
        int i = targetWeakest(units);
        Unit unit = units.get(i);
        String healed = "";
        
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = unit.takeDamage(dmg);
        
        if(unit.getTmpHealth() == 0){
            heal(20);
            healed = ", healing itself for 20";
        }
        addEnergy(-20);
        
        return new BattleLog(BattleLog.getMonsterDmgLog(getName(), getThirdAbilityName(), unit, dmgDone, i)+healed);
    }
    public String getThirdAbilityName(){
        return "Execute";
    }
    
    public BattleLog ultAbility(ArrayList<Unit> units){
        String damageDone = "";
        for(int i = 0; i<5; i++){
            if(units.get(i).isTargetable() == 1){
                int dmg = RNG.randomize((int)(getTmpPower() * 0.9), Game.RAND);
                damageDone += units.get(i).takeDamage(dmg) + ", ";
                if(units.get(i).getTmpHealth() == 0) damageDone += "(fatal)";
            }
        }
        
        return new BattleLog(BattleLog.getAoeDmgLog(getName(), getUltAbilityName(), damageDone));
    }
    public String getUltAbilityName(){
        return "Blade Dance";
    }
    
    public BattleLog makeMove(ArrayList<Unit> units){
        if(energy.get() > 25){ // execute na jednostce ktora ma malo hp
            int tmp = 0;
            int i = targetWeakest(units);
            tmp += 30 + getTmpPower() - units.get(i).getTmpHealth();
            if(tmp < 0) tmp = 0;
            
            if(RNG.roll(energy.get() + tmp)){
                return thirdAbility(units);
            }
        }
        
        if(energy.get() > 25){ // ulti gdy wiecej niz 25 energii, im mniej hp tym lepiej, ale musi byc przynajmniej 60 proc szans
            int roll = energy.get();
            roll += (getHealth() - getTmpHealth()) / 3;
            if((roll > 60) && (RNG.roll(roll))){
                return ultAbility(units);
            }
        }
        
        Buff buff = findBuff(getSecondAbilityName());
        if((buff == null) || (buff.getTime() == 1)){
            return secondAbility(units);
        }
        
        return firstAbility(units);
    }
    
    public String getName(){
        return "Angelic Seeker";
    }
}
