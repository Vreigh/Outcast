package game;
import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class Combat {
    private ArrayList<Unit> units;
    private Monster monster;
    
    public Combat(Player player){
        this.units = player.getUnits();
        switch(player.getProgress()){
            case 0:
                //monster = new Warrior();
                break;
            case 1:
                //monster = new Shadow();
                break;
            case 2:
                //monster = new Boss();
                break;
                
        }
    }
}
