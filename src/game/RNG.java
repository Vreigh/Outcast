package game;
import java.util.Random;

public class RNG {
    public static int randomize(int val, double e){
        Random r = new Random();
        int rand = r.nextInt((int)(e*val));
        return val + rand;
    }
    public static boolean roll(int chance){
        Random r = new Random();
        int roll = r.nextInt(100); // wynik z zakresu 0 do 99
        return roll >= (100 - chance);
    }
}
