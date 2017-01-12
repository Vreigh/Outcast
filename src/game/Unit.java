package game;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.beans.binding.StringExpression;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

public abstract class Unit extends Combatable{
    
    public static final int UPGRADE_COST = 100;
    public static final int UPGRADE_STACK = 20;
    public static final int UPGRADE_SHIELD_STACK = 50;
    
    IntegerProperty powerUp = new SimpleIntegerProperty(0); // te pola nie beda private bo az prosza sie o nie bycie private
    IntegerProperty shieldUp = new SimpleIntegerProperty(0);
    IntegerProperty healthUp = new SimpleIntegerProperty(0);
    
    IntegerProperty powerCost = new SimpleIntegerProperty(0);
    IntegerProperty shieldCost = new SimpleIntegerProperty(0);
    IntegerProperty healthCost = new SimpleIntegerProperty(0);
    
    int position;
    
    Armory armory;
    
    public Unit(Armory armory, int i){
        this.armory = armory;
        position = i;
        powerCost.bind(powerUp.multiply(UPGRADE_STACK).add(UPGRADE_COST));
        shieldCost.bind(shieldUp.multiply(UPGRADE_SHIELD_STACK).add(UPGRADE_COST));
        healthCost.bind(healthUp.multiply(UPGRADE_STACK).add(UPGRADE_COST));
    }
    
    public abstract BattleLog mainAbility(Combat combat, Armory armory, Monster monster);
    public abstract String getMainAbilityName();
    
    public abstract BattleLog secondAbility(Combat combat, Armory armory, Monster monster);
    public abstract String getSecondAbilityName();
    
    public abstract BattleLog ultAbility(Combat combat, Armory armory, Monster monster);
    public abstract String getUltAbilityName();
    
    public abstract String getName();
    
    public StringBinding getPowerUpStringBind(){
        return powerUp.asString();
    }
    public StringBinding getShieldUpStringBind(){
        return shieldUp.asString();
    }
    public StringBinding getHealthUpStringBind(){
        return healthUp.asString();
    }
    
    public StringBinding getPowerCostStringBind(){
        return powerCost.asString();
    }
    public int getPowerCost(){
        return powerCost.get();
    }
    
    public StringBinding getShieldCostStringBind(){
        return shieldCost.asString();
    }
    public int getShieldCost(){
        return shieldCost.get();
    }
    
    public StringBinding getHealthCostStringBind(){
        return healthCost.asString();
    }
    public int getHealthCost(){
        return healthCost.get();
    }
    public String getFullName(){
        return getName() + "(" + (getPosition() + 1)  + ")";
    }
    
    public int getPosition(){
        return position;
    }
    
    public boolean hit(int range){
        return RNG.roll(100 -25*(position - armory.getOffset() - range));
    }
    public String miss(){
        return getFullName() + "was too far away and missed!";
    }
    
    
    void incPower(){
        powerUp.set(powerUp.get() + 1);
    }
    void incShield(){
        shieldUp.set(shieldUp.get() + 1);
    }
    void incHealth(){
        healthUp.set(healthUp.get() + 1);
        super.tmpHealth.set(health.get());
    }
    
    public int isAlive(){
        if(getTmpHealth() > 0){
            return 1;
        }else return 0;
    }
    void setPriority(){
        Random r = new Random();
        priority = getSpeed() + r.nextInt(6);
    }
    
    boolean checkEnergy(int val){
        if(energy.get() < val){
            AlertWindow.showInfo("Not enough energy!", "You need at least " + val + " energy to use that ability ");
            return false;
        }else return true;
    }
}
