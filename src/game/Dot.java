package game;

public class Dot {
    private int time;
    private int damage;
    
    public Dot(int time, int damage){
        this.time = time;
        this.damage = damage;
    }
    public int getTime(){
        return time;
    }
    public int getDamage(){
        return damage;
    }
    int reduceTime(){
        return --time;
    }
    
}
