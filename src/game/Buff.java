package game;

public class Buff {
    private int time;
    private int power;
    private int shield;
    private String name;
    
    public Buff(int time, int power, int shield, String name){
        this.power = power;
        this.shield = shield;
        this.time = time;
        this.name = name;
    }
    public int getTime(){
        return time;
    }
    public int getPower(){
        return power;
    }
    public int getShield(){
        return shield;
    }
    public String getName(){
        return name;
    }
    int reduceTime(){
        return --time;
    }
}
