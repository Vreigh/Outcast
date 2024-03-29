package game;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Dot {
    private IntegerProperty time;
    private int damage;
    private String name;
    
    public Dot(int time, int damage, String name){
        this.time = new SimpleIntegerProperty(time);
        this.damage = damage;
        this.name = name;
    }
    public IntegerProperty timeProperty(){
        return time;
    }
    public int getTime(){
        return time.get();
    }
    public int getDamage(){
        return damage;
    }
    public String getName(){
        return name;
    }
    int reduceTime(){
        time.set(time.get() - 1);
        return time.get();
    }
    void refresh(Dot other, boolean positive){
        if(other.time.get() > time.get()) time.set(other.time.get());
        if(positive){
           if(other.damage > damage) damage = other.damage;
        }else{
            if(other.damage < damage) damage = other.damage;
        }
    }
}
