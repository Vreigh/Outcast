package game;
import java.util.ArrayList;

public abstract class Combatable {
    protected int ordinal; // identyfikator 
    protected int speed; // bazowa szybkosc
    protected int priority; // aktualny priorytet
    
    
    protected int power;
    protected int shield;
    
    protected int maxHealth;
    protected int health;
    protected int energy;
    
    protected ArrayList<Buff> buffs;
    protected ArrayList<Dot> dots;
    
    public int getTmpPower(){
        //buffs.stream().map(Buff::getShield).sum().orElse(shield);
        
        
        
        if(buffs.isEmpty()){
            return power;
        }
        else{
            int tmpPower = 0;
            for(Buff buff : buffs){
                tmpPower += buff.getPower();
            }
            return tmpPower;
        }
    }
    public int getTmpShield(){
        if(buffs.isEmpty()){
            return shield;
        }
        else{
            int tmpShield = 0;
            for(Buff buff : buffs){
                tmpShield += buff.getShield();
            }
            return tmpShield;
        }
    }
    public int getHealth(){
        return health;
    }
}
