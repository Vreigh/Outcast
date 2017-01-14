package game;
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
    public abstract String getName();
    public abstract String getFullName();
    
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
    void addEnergy(int x){
        energy.set(energy.get() + x);
        /*if(energy.get() < 0){
            energy.set(0);
        }*/
    }
    public StringBinding getTmpHealthStringBind(){
        return tmpHealth.asString();
    }
    public int getTmpHealth(){
        return tmpHealth.get();
    }
    
    public int getHealthProc(){
        return 100*tmpHealth.get() / health.get();
    }
    public int getSurvability(){
        return getTmpHealth() + getTmpShield();
    }
    
    void setSpeed(int x){
        speed.set(x);
    }
    public int getPriority(){
        if(getTmpHealth() > 0){
            return priority;
        }else return 0;
    }
    void addPriority(int x){
        priority += x;
    }
    public void setBuffTable(TableView<Buff> table){
        table.setItems(buffs);
    }
    public void setDotTable(TableView<Dot> table){
        table.setItems(dots);
    }
    /// METODY WALKI
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
    int takeRealDamage(int dmg){
        tmpHealth.set(tmpHealth.get() - dmg);
        
        if(tmpHealth.get() > health.get()){
            dmg += tmpHealth.get() - health.get();
            tmpHealth.set(health.get());
        }else if(tmpHealth.get() < 0){
            dmg += tmpHealth.get();
            tmpHealth.set(0);
        }
        return dmg;
    }
    void reducePriority(int val){
        priority -= val;
        if(priority <= 0) priority = 1;
    }
    void afterTurn(ObservableList<BattleLog> battleLogs){
        ArrayList<Buff> buffsToRemove = new ArrayList<Buff>();
        for(Buff buff : buffs){
            if(buff.reduceTime() == 0){
                battleLogs.add(new BattleLog(buff.getName() + " wears off from " + getFullName()));
                buffsToRemove.add(buff);
            }
        }
        buffs.removeAll(buffsToRemove);
        
        ArrayList<Dot> dotsToRemove = new ArrayList<Dot>();
        for(Dot dot : dots){
            int dmg = RNG.randomize(dot.getDamage(), Game.RAND);
            if(dmg >= 0){
                battleLogs.add(new BattleLog(dot.getName() + " deals " + dmg + " damage to " + getFullName()));
            }else{
                battleLogs.add(new BattleLog(dot.getName() + " heals " + getFullName() + " for " + (-dmg)));
            }
            takeRealDamage(dmg);
            if(dot.reduceTime() == 0){
                battleLogs.add(new BattleLog(dot.getName() + " wears off from " + getFullName()));
                dotsToRemove.add(dot);
            }
        }
        dots.removeAll(dotsToRemove);
        
        priority = 0;
    }
    // metody zarządzania buffami i dotami
    public int buffNumber(){
        return buffs.size();
    }
    public int dotNumber(){
        return dots.size();
    }
    void addBuff(Buff buff){
        buffs.add(buff);
    }
    void addDot(Dot dot){
        dots.add(dot);
    }
    void removeBuff(String name){
        Buff buff = findBuff(name);
        buffs.remove(buff);
    }
    void removeDot(String name){
        Dot dot = findDot(name);
        dots.remove(dot);
    }
    void addOrRefreshBuff(Buff buff, boolean positive){
        Buff current = findBuff(buff.getName());
        if(current == null){
            addBuff(buff);
        }else current.refresh(buff, positive);
    }
    void addOrRefreshDot(Dot dot, boolean positive){
        Dot current = findDot(dot.getName());
        if(current == null){
            addDot(dot);
        }else current.refresh(dot, positive);
    }
    public Buff findBuff(String name){
        for(Buff buff : buffs) if(buff.getName().equals(name)) return buff;
        return null;
    }
    public Dot findDot(String name){
       for(Dot dot : dots) if(dot.getName().equals(name)) return dot;
       return null; 
    }
    
    void reset(){
        tmpHealth.set(health.get());
        buffs.clear();
        dots.clear();
        priority = 0;
        energy.set(0);
    }
    

}
