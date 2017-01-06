package game;
import java.util.ArrayList;

public abstract class Unit extends Combatable{
    
    public abstract ArrayList<Combatable> mainAbility(ArrayList<Combatable> combatants);
    public abstract ArrayList<Combatable> secondAbility(ArrayList<Combatable> combatants);
    public abstract ArrayList<Combatable> ultAbility(ArrayList<Combatable> combatants);
    
}
