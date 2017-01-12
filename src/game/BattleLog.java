package game;

public class BattleLog {
    private String content;
    
    public BattleLog(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
    
    public static final String getMonsterDmgLog(String monster, String ability, Unit unit, int dmgDone){
        String killed = "";
        if(unit.getTmpHealth() == 0) killed = " killing it";
        return monster + " used " + ability + " dealing " + dmgDone + " damage to unit " + unit.getFullName() + killed;
    }
    public static final String getSelfBuffLog(String user, String ability, String name){
        return user + " used " + ability + " putting " + name + " on itself!";
    }
    public static final String getAoeDmgLog(String monster, String ability, String dmgDone){
        return monster + " used " + ability + " dealing " + dmgDone + " damage to your units!";
    }
    
    public static final String getUnitDmgLog(String unit, String ability, int dmgDone){
        return unit + " used " + ability + " dealing " + dmgDone + " damage";
        
    }
    
}
