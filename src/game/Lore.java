package game;

public class Lore {
    public static String getCombatWonLore(int i){
        switch(i){
            case 0:
                return "You won against Angelic Seeker Lorem Ipsum!";
            case 1:
                return "You won against Paladin Lorem Ipsum!";
            case 2:
                return "You won against Lord Imperator!";
        }
        return "Oops";
    }
    public static String getPreCombatLore(int i){
        switch(i){
            case 0:
                return "You are about to fight Angelic Seeker";
            case 1:
                return "You are about to fight Paladin";
            case 2:
                return "You are about to fight Lord Imperator";
        }
        return "Oops";
    }
}
