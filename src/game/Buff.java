package game;
import javafx.beans.property.*;

public class Buff {
    private IntegerProperty time;
    private int power;
    private int shield;
    private String name;
    
    public Buff(int time, int power, int shield, String name){
        this.power = power;
        this.shield = shield;
        this.time = new SimpleIntegerProperty(time);
        this.name = name;
    }
    public IntegerProperty timeProperty(){
        return time;
    }
    public int getTime(){
        return time.get();
    }
    public int getPower(){
        return power;
    }
    public int getShield(){
        return shield;
    }
    public String getName(){
        return name;
    }
    int reduceTime(){
        time.set(time.get() - 1);
        return time.get();
    }
}
