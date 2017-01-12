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


public class Armory {
    private ArrayList<Unit> units = new ArrayList<Unit>();
    
    ArrayList<Unit> getUnits(){return units;}
    public int size() { return units.size();}
    
    public Unit get(int i){
        for(Unit unit : units){
            if(unit.getPosition() == i) return unit;
        }
        return null;
    }
    Unit addUnit(int i){
        int j = getSpace();
        Unit unit;
        switch(i){
            case 0:
                unit = new Ghoul(this, j);
                units.add(unit);
                return unit;
            case 1:
                unit = new Vampire(this, j);
                units.add(unit);
                return unit;
            case 2:
                unit = new Plagueman(this, j);
                units.add(unit);
                return unit;
            case 3:
                unit = new Phantom(this, j);
                units.add(unit);
                return unit;
            case 4:
                unit = new Butcher(this, j);
                units.add(unit);
                return unit;
            default:
                return null;
        }
    }
    public int getAlive(){ return units.stream().mapToInt(Unit::isAlive).sum();}
    
    void swap(int i, int j){ // i to jednostka, która striggerowała
        Unit unit_i = get(i);
        Unit unit_j = get(j);
        
        unit_i.position = j;
        if(unit_j != null){
            unit_j.position = i;
        }
        
    }
    void reset(){
        for(Unit unit : units){
            unit.reset();
        }
    }
    public int getOffset(){
        int min = 5;
        for(Unit unit : units) if((unit.position < min) && (unit.isAlive() == 1)) min = unit.position;
        return min;
    }
    private int getSpace(){
        int ret = -1;
        while(get(++ret) != null){}
        return ret;
    }
    
    
}
