package game;
import java.util.ArrayList;

public abstract class Unit extends Combatable{
    public abstract void mainAbility(ArrayList<Unit> units, Monster monster);
    public abstract void secondAbility(ArrayList<Unit> units, Monster monster);
    public abstract void ultAbility(ArrayList<Unit> units, Monster monster);
}
