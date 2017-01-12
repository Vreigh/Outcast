package game;
import java.util.ArrayList;

public final class Warrior extends Monster {
    
    public Warrior(){
        super();
        setSpeed(60);
        
        super.power.set(80);
        super.health.set(450);
        super.shield.set(-25);
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog firstAbility(Combat combat, Armory armory){
        Unit unit = targetNearest(armory);
        
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = unit.takeDamage(dmg);
        
        addEnergy(20);
        
        return new BattleLog(BattleLog.getMonsterDmgLog(getName(), getFirstAbilityName(), unit, dmgDone));
    }
    public String getFirstAbilityName(){
        return "Rightous Slash";
    }
    
    public BattleLog secondAbility(Combat combat, Armory armory){
        addBuff(new Buff(4, 0, 50, getSecondAbilityName()));
        addEnergy(25);
        return new BattleLog(BattleLog.getSelfBuffLog(getName(), getSecondAbilityName(), getSecondAbilityName()));
    }
    public String getSecondAbilityName(){
        return "Angelic Preservance";
    }
    
    public BattleLog thirdAbility(Combat combat, Armory armory){
        Unit unit  = targetWeakest(armory);
        String healed = "";
        
        int dmg = RNG.randomize(getTmpPower(), Game.RAND);
        int dmgDone = unit.takeDamage(dmg);
        
        if(unit.isAlive() == 0){
            heal(20);
            healed = ", healing itself for 20";
        }
        addEnergy(-20);
        
        return new BattleLog(BattleLog.getMonsterDmgLog(getName(), getThirdAbilityName(), unit, dmgDone)+healed);
    }
    public String getThirdAbilityName(){
        return "Execute";
    }
    
    public BattleLog ultAbility(Combat combat, Armory armory){
        String damageLog = "";
        for(Unit unit : armory.getUnits()){
            if(unit.isAlive() == 1){
                int dmg = RNG.randomize(getTmpPower(), Game.RAND);
                damageLog += unit.takeDamage(dmg) + ", ";
                if(unit.isAlive() == 0) damageLog += "(fatal)";
            }
        }
        addEnergy(-100);
        
        return new BattleLog(BattleLog.getAoeDmgLog(getName(), getUltAbilityName(), damageLog));
    }
    public String getUltAbilityName(){
        return "Blade Dance";
    }
    
    public BattleLog makeMove(Combat combat, Armory armory){
        if((energy.get() > 25) &&(armory.getAlive() != 1)){ // ulti gdy wiecej niz 25 energii, im mniej hp tym lepiej, ale musi byc przynajmniej 75 proc szans
            int roll = energy.get();
            int rage = 0;
            int proc = getHealthProc();
            if(proc < 50){
                rage += 25;
            }
            if(proc < 33){
                rage += 25;
            }
            if(proc < 25){
                rage += 25;
            }
            roll += rage;
            if((roll > 75) && (RNG.roll(roll))){
                return ultAbility(combat, armory);
            }
        }
        
        
        if(energy.get() > 25){ // execute na jednostce ktora ma malo hp - SŁABOŚĆ : rzuca się tępo na ranne jednostki, niewazne ile mają shielda
            int tmp = 0;
            Unit unit = targetWeakest(armory);
            
            if(getTmpPower() > unit.getTmpHealth()){
                tmp += 30 + 2*(getTmpPower() - unit.getTmpHealth());
            }
            
            if(RNG.roll(energy.get() + tmp)){
                return thirdAbility(combat, armory);
            }
        }
        
        Buff buff = findBuff(getSecondAbilityName()); // rzuci na siebie shielda
        if((buff == null) || (buff.getTime() == 1)){
            return secondAbility(combat, armory);
        }
        
        return firstAbility(combat, armory);
    }
    
    public String getName(){
        return "Angelic Seeker";
    }
}
