package game;

import java.util.ArrayList;
import java.util.Random;

public abstract class Monster extends Combatable {
    
    public abstract String getName();
    
    public abstract void firstAbility(ArrayList<Unit> units);
    public abstract String getFirstAbilityName();
    
    public abstract void secondAbility(ArrayList<Unit> units);
    public abstract String getSecondAbilityName();
    
    public abstract void thirdAbility(ArrayList<Unit> units);
    public abstract String getThirdAbilityName();
    
    public abstract void ultAbility(ArrayList<Unit> units);
    public abstract String getUltAbilityName();
    
    void setPriority(){
        Random generator = new Random();
        int rand = generator.nextInt((int)(0.25*getSpeed()));
        super.priority = rand + getSpeed();
    }
    
}
