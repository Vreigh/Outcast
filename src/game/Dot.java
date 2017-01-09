package game;

public class Dot {
    private int time;
    private int damage;
    private String name;
    
    public Dot(int time, int damage, String name){
        this.time = time;
        this.damage = damage;
        this.name = name;
    }
    public int getTime(){
        return time;
    }
    public int getDamage(){
        return damage;
    }
    public String getName(){
        return name;
    }
    int reduceTime(){
        return --time;
    }
    
}
