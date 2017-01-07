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
    
    private IntegerProperty power = new SimpleIntegerProperty(0);
    private IntegerProperty health = new SimpleIntegerProperty(0);
    private IntegerProperty tmpHealth = new SimpleIntegerProperty(0);
    private IntegerProperty shield = new SimpleIntegerProperty(0);

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
        
    }
    

}
