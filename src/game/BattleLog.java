package game;

public class BattleLog {
    private String content;
    
    public BattleLog(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    
    public static final String getMonsterDmgLog(String monster, String ability, Unit unit, int dmgDone, int i){
        String name = unit.getName() + "(" + i + ")";
        String killed = "";
        if(unit.getTmpHealth() == 0) killed = " killing it";
        return monster + " used " + ability + " dealing " + dmgDone + " damage to unit " + name + killed;
    }
    public static final String getSelfBuffLog(String user, String ability, String name){
        return user + " used " + ability + " putting " + name + " on itself!";
    }
    public static final String getAoeDmgLog(String monster, String ability, String dmgDone){
        return monster + " used " + ability + " dealing " + dmgDone + " to your units!";
    }
    
    public static final String getUnitDmgLog(String unit, String ability, int i, int dmgDone){
        String name = unit + "(" + i + ")";
        return name + " used " + ability + " dealing " + dmgDone + " damage";
        
    }
    
}
