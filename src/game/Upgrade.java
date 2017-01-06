package game;
import javafx.beans.property.*;
import javafx.beans.binding.Bindings;

public class Upgrade {
    public static final int manaIncome = 2;
    public static final int cryIncome = 3;
    public static final int cemCap = 3;
    public static final int ap = 5;
    
    private IntegerProperty lvl = new SimpleIntegerProperty(0);
    private IntegerProperty progress = new SimpleIntegerProperty(0);
    private IntegerProperty fill = new SimpleIntegerProperty(0);
    
    private IntegerProperty required = new SimpleIntegerProperty();
    private final String name; // nie property, bo to jest stałe i tak
    
    public Upgrade(int i){
        switch(i){
            case 0:
                name = "+" + manaIncome +  " to seed base income";
                break;
            case 1:
                name = "+1 to maximum seeds number";
                break;
            case 2:
                name = "+" + cryIncome +  " to crystal base income per spirit";
                break;
            case 3:
                name = "+" + cemCap +  " to cementary maximum spirit number";
                break;
            case 4:
                name = "+" + ap +  " to maximum action points";
                break;
            default:
                name = "dummy";
                break;
        }
        
        required.bind(lvl.add(5));
    }
    IntegerProperty getLvlProperty(){
        return lvl;
    }
    int getLvl(){
        return lvl.get();
    }
    private void addLvl(int lvl){
        this.lvl.set(lvl);
    }
    
    IntegerProperty getProgressProperty(){
        return progress;
    }
    int getProgress(){
        return progress.get();
    }
    private void addProgress(int x){
        progress.set(x);
    }
    
    IntegerProperty getFillProperty(){
        return fill;
    }
    int getFill(){
        return fill.get();
    }
    void addFill(int x){
        fill.set(fill.get() + x);
    }
    
    IntegerProperty getRequiredProperty(){
        return required;
    }
    int getRequired(){
        return required.get();
    }
   
    String getName(){
        return name;
    }
    
    String develop(){
        // TO DO
        String info = "";
        return "";
    }
    
}
