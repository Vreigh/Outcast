package game;
import javafx.beans.property.*;
import javafx.beans.binding.Bindings;

public class Cementary {
    protected static final int baseCapacity = 5;
    protected static final int baseOutput = 10;
    
    private IntegerProperty fill = new SimpleIntegerProperty(0);
    
    private IntegerProperty capacity = new SimpleIntegerProperty();
    private IntegerProperty singleOutput = new SimpleIntegerProperty();
    private IntegerProperty fullOutput = new SimpleIntegerProperty();
    
    public Cementary(){
        fullOutput.bind(singleOutput.multiply(fill));
    }
    
    //gettery i settery
    public IntegerProperty getFillProperty(){
        return fill;
    }
    public int getFill(){
        return fill.get();
    }
    public void addFill(int x){
        fill.set(fill.get() + x);
    }
    
    public IntegerProperty getCapacityProperty(){
        return capacity;
    }
    public int getCapacity(){
        return capacity.get();
    }
    
    public IntegerProperty getSingleOutputProperty(){
        return singleOutput;
    }
    public int getSingleOutput(){
        return singleOutput.get();
    }
    
    public IntegerProperty getFullOutputProperty(){
        return fullOutput;
    }
    public int getFullOutput(){
        return fullOutput.get();
    }
    
    
}
