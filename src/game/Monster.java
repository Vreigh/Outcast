package game;

import java.util.ArrayList;
import java.util.Random;

public abstract class Monster extends Combatable {
    
    public abstract String getName();
    
    public abstract BattleLog firstAbility(ArrayList<Unit> units);
    public abstract String getFirstAbilityName();
    
    public abstract BattleLog secondAbility(ArrayList<Unit> units);
    public abstract String getSecondAbilityName();
    
    public abstract BattleLog thirdAbility(ArrayList<Unit> units);
    public abstract String getThirdAbilityName();
    
    public abstract BattleLog ultAbility(ArrayList<Unit> units);
    public abstract String getUltAbilityName();
    
    public abstract BattleLog makeMove(ArrayList<Unit> units);
    
    public int targetNearest(ArrayList<Unit> units){
        for(int i=0; i<5; i++){
            if(units.get(i).isTargetable()){
                if(i == 4){
                    return i;
                }else if(units.get(i + 1).isTargetable()){
                    if(RNG.roll(40)){
                        return i + 1;
                    }
                }
                return i;
            }
        }
        return -1;
    }
    public int targetWeakest(ArrayList<Unit> units){
        int min = 1000;
        int ret = -1;
        for(int i=0; i<5; i++){
            Unit unit = units.get(i);
            if(unit.isTargetable()){
                if(unit.getTmpHealth() + unit.getTmpShield() < min){
                   min = unit.getTmpHealth() + unit.getTmpShield();
                   ret = i;
                }
            }
        }
        return ret;
    }
    public int targetRandom(ArrayList<Unit> units){
        Random r = new Random();
        int ret = -2;
        do{
           ret = r.nextInt(5);
        }while(!units.get(ret).isTargetable());
        
        return ret;
    }
    
    void setPriority(){
        super.priority = RNG.randomize(getSpeed(), 0.25);
    }
    
}
