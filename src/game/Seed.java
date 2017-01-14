package game;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;

public class Seed {
    private IntegerProperty age = new SimpleIntegerProperty(0);
    
    public StringBinding getAgeStringBind(){
        return age.asString();
    }
    public int getAge(){
        return age.get();
    }
    void addAge(int x){
        age.set(age.get() + x);
    }
    public int getIncome(int base){
        double ageMod = Math.pow((age.get() -1), 0.8) / 3;
        return (int)((1 + ageMod) * base);
    }
    
}
