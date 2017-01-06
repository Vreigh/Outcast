package game;
import javafx.beans.property.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

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
    IntegerProperty getFillProperty(){
        return fill;
    }
    public StringBinding getFillStringBind(){
        return fill.asString();
    }
    public int getFill(){
        return fill.get();
    }
    void addFill(int x){
        fill.set(fill.get() + x);
    }
    
    IntegerProperty getCapacityProperty(){
        return capacity;
    }
    public StringBinding getCapacityStringBind(){
        return capacity.asString();
    }
    public int getCapacity(){
        return capacity.get();
    }
    
    IntegerProperty getSingleOutputProperty(){
        return singleOutput;
    }
    public StringBinding getSingleOutputStringBind(){
        return singleOutput.asString();
    }
    public int getSingleOutput(){
        return singleOutput.get();
    }
    
    public StringBinding getFullOutputStringBind(){
        return fullOutput.asString();
    }
    public int getFullOutput(){
        return fullOutput.get();
    }
    
    
}
