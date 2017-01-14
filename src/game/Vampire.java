package game;
import java.util.ArrayList;

public final class Vampire extends Unit {
    
    public Vampire(Armory armory, int i){
        super(armory, i);
        super.setSpeed(40);
        
        super.power.bind(super.powerUp.multiply(8).add(40));
        super.health.bind(super.healthUp.multiply(30).add(120));
        super.shield.bind(super.shieldUp.multiply(5).add(10));
        
        super.tmpHealth.set(health.get());
    }
    
    public BattleLog mainAbility(Combat combat, Armory armory, Monster monster){
        int i = AlertWindow.getTarget(armory, armory.getUnits(), null);
        if(i == -1) return null;
        int j = AlertWindow.getTarget(armory, armory.getUnits(), null);
        if(j == -1) return null;
        
        Unit target1 = armory.get(i);
        Unit target2 = armory.get(j);
        
        int dmgDone = monster.takeDamage(RNG.randomize((int)(getTmpPower() * 1.2), GameWindow.RAND));
        addEnergy(40);
        
        int dif = getTmpPower() - target1.getTmpHealth();
        if(dif < 0) dif = 0;
        
        int selfDmg = target1.takeRealDamage(getTmpPower());
        selfDmg += dif;
        target2.heal((int)(dmgDone * 0.75 - dif)); // ile healuje od 0
        int heal = (int)(dmgDone * 0.75); // ile wyhealowałem od np -30 
        
        return new BattleLog(getFullName() + "used " + getMainAbilityName() + " dealing " + selfDmg + " damage to "
        + target1.getFullName() + ", dealing " + dmgDone + " damage to " + monster.getFullName() + " and healing " + target2.getFullName() + " for " + heal );
    }
    public String getMainAbilityName(){
        return "Vital Flow";
    }
    public BattleLog secondAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(20)) return null;
        
        int dmgDone = monster.takeDamage(RNG.randomize((int)(getTmpPower() * 0.9), GameWindow.RAND));
        int healed = -heal(dmgDone);
        addEnergy(-20);
        
        return new BattleLog(getFullName() + " used " + getSecondAbilityName() + " dealing " + dmgDone + " damage, and healing itself for " + healed );

    }
    public String getSecondAbilityName(){
        return "Drain";
    }
    public BattleLog ultAbility(Combat combat, Armory armory, Monster monster){
        if(!checkEnergy(100)) return null;
        for(Unit unit : armory.getUnits()){
            if(unit.isAlive() == 1) unit.addOrRefreshDot(new Dot(3, -(int)(getTmpPower() * 0.4), getUltAbilityName()), false);
        }
        addEnergy(-100);
        return new BattleLog(getFullName() + " used " + getUltAbilityName() + " , putting" + getUltAbilityName() + " on your units!");
    }
    public String getUltAbilityName(){
        return "Night Hunt";
    }
    public String getName(){
        return "Vampire";
    }
}
