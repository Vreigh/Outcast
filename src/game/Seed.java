package game;
import javafx.beans.property.*;

public class Seed {
    private IntegerProperty age = new SimpleIntegerProperty(0);
    
    public IntegerProperty getAgeProperty(){
        return age;
    }
    public int getAge(){
        return age.get();
    }
    public void addAge(int x){
        age.set(age.get() + x);
    }
    
}
