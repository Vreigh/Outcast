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

public abstract class Combatable {
    
    private IntegerProperty speed = new SimpleIntegerProperty(0);
    private int priority = 0;
    
    IntegerProperty power = new SimpleIntegerProperty(0);
    IntegerProperty health = new SimpleIntegerProperty(0);
    IntegerProperty tmpHealth = new SimpleIntegerProperty(0);
    IntegerProperty shield = new SimpleIntegerProperty(0);

    private ArrayList<Buff> buffs;
    private ArrayList<Dot> dots;
    
    public StringBinding getSpeedStringBind(){
        return speed.asString();
    }
    public int getSpeed(){
        return speed.get();
    }
    void setPriority(){
        Random generator = new Random();
        int rand = generator.nextInt(15);
        priority = rand + speed.get();
    }
    //////////////////////
    public StringBinding getPowerStringBind(){
        return power.asString();
    }
    public int getTmpPower(){
        return power.get() + buffs.stream().mapToInt(Buff::getPower).sum();
    }
    
    public StringBinding getShieldStringBind(){
        return shield.asString();
    }
    public int getTmpShield(){
        return shield.get() + buffs.stream().mapToInt(Buff::getShield).sum();
    }
    
    public StringBinding getHealthStringBind(){
        return health.asString();
    }
    public int getHealth(){
        return health.get();
    }
    
    public StringBinding getTmpHealthStringBind(){
        return tmpHealth.asString();
    }
    public int getTmpHealth(){
        return tmpHealth.get();
    }
    
    public void addBuff(Buff buff){
        buffs.add(buff);
    }
    public void addDot(Dot dot){
        dots.add(dot);
    }
    

}
