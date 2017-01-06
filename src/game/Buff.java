package game;

public class Buff {
    private int time;
    
    private int power;
    private int shield;
    
    public Buff(int time, int power, int shield){
        this.power = power;
        this.shield = shield;
        this.time = time;
    }
    public int getPower(){
        return power;
    }
    public int getShield(){
        return shield;
    }
}
