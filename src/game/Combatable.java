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
import javafx.scene.control.TableView;

public abstract class Combatable {
    
    private IntegerProperty speed = new SimpleIntegerProperty(0);
    int priority = 0;
    
    IntegerProperty power = new SimpleIntegerProperty(0);
    IntegerProperty health = new SimpleIntegerProperty(0);
    IntegerProperty tmpHealth = new SimpleIntegerProperty(0);
    IntegerProperty shield = new SimpleIntegerProperty(0);
    IntegerProperty energy = new SimpleIntegerProperty(0);
    
    private ObservableList<Buff> buffs = FXCollections.observableArrayList();
    private ObservableList<Dot> dots = FXCollections.observableArrayList();
    
    abstract void setPriority();
    
    public StringBinding getSpeedStringBind(){
        return speed.asString();
    }
    public int getSpeed(){
        return speed.get();
    }
    //////////////////////
    public StringBinding getPowerStringBind(){
        return power.asString();
    }
    public int getTmpPower(){
        return power.get() + buffs.stream().mapToInt(Buff::getPower).sum();
    }
    public int getPower(){
       return power.get();
    }
    
    public StringBinding getShieldStringBind(){
        return shield.asString();
    }
    public int getTmpShield(){
        return shield.get() + buffs.stream().mapToInt(Buff::getShield).sum();
    }
    public int getShield(){
        return shield.get();
    }
    
    public StringBinding getHealthStringBind(){
        return health.asString();
    }
    public int getHealth(){
        return health.get();
    }
    
    public StringBinding getEnergyStringBind(){
        return energy.asString();
    }
    public int getEnergy(){
        return energy.get();
    }
    
    public StringBinding getTmpHealthStringBind(){
        return tmpHealth.asString();
    }
    public int getTmpHealth(){
        return tmpHealth.get();
    }
    void setSpeed(int x){
        speed.set(x);
    }
    public int getPriority(){
        return priority;
    }
    public void setBuffTable(TableView<Buff> table){
        table.setItems(buffs);
    }
    public void setDotTable(TableView<Dot> table){
        table.setItems(dots);
    }
    /// METODY WALKI
    void addBuff(Buff buff){
        buffs.add(buff);
    }
    void addDot(Dot dot){
        dots.add(dot);
    }
    int takeDamage(int x){
        int dmg = x - getTmpShield();
        if(dmg < 0){
            dmg = 1;
        }
        return takeRealDamage(dmg);
    }
    int heal(int x){
        return takeRealDamage(-x);
    }
    private int takeRealDamage(int dmg){
        tmpHealth.set(tmpHealth.get() - dmg);
        
        if(tmpHealth.get() > health.get()){
            tmpHealth.set(health.get());
        }else if(tmpHealth.get() < 0){
            dmg += tmpHealth.get();
            tmpHealth.set(0);
        }
        return dmg;
    }
    void afterTurn(){
        for(Buff buff : buffs){
            if(buff.reduceTime() == 0){
                buffs.remove(buff);
            }
        }
        for(Dot dot : dots){
            takeRealDamage(dot.getDamage());
            if(dot.reduceTime() == 0){
                dots.remove(dot);
            }
        }
    }
    

}
