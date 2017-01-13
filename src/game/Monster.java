package game;

import java.util.ArrayList;
import java.util.Random;

public abstract class Monster extends Combatable {
    
    public abstract String getName();
    
    public abstract BattleLog firstAbility(Combat combat, Armory armory);
    public abstract String getFirstAbilityName();
    
    public abstract BattleLog secondAbility(Combat combat, Armory armory);
    public abstract String getSecondAbilityName();
    
    public abstract BattleLog thirdAbility(Combat combat, Armory armory);
    public abstract String getThirdAbilityName();
    
    public abstract BattleLog ultAbility(Combat combat, Armory armory);
    public abstract String getUltAbilityName();
    
    public abstract BattleLog makeMove(Combat combat, Armory armory);
    
    public Unit targetNearest(Armory armory){
        int of = armory.getOffset();
        Unit unit = armory.get(of + 1);
        if((unit != null) && (unit.isAlive() == 1) && (RNG.roll(40))){
            return unit;
        }else{
            return armory.get(of);
        }
    }
    public Unit targetWeakest(Armory armory){
        int min = 1000;
        Unit ret = null;
        for(Unit unit : armory.getUnits()){
            if(unit.isAlive() == 1){
                if(unit.getSurvability() < min){
                    ret = unit;
                    min = unit.getSurvability();
                }
            }
        }
        
        return ret;
    }
    public Unit targetRandom(Armory armory){
        Random r = new Random();
        Unit unit = null;
        do{
           unit = armory.get(r.nextInt(5));
        } while((unit == null) || (unit.isAlive() == 0));
        
        return unit;
    }
    
    void setPriority(){
        Random r = new Random();
        priority = getSpeed() + r.nextInt(6);
    }
    public String getFullName(){
        return getName();
    }
    @Override // zeby nie mozna było nakładać debuffow na bossa w nieskonczonosc i ostatecznie healował, bo to by było... słabe
    public int getTmpPower(){
        int tmp = super.getTmpPower();
        if(tmp < power.get() / 2){
            return power.get() / 2;
        }else return tmp;
    }
    
}
