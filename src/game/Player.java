package game;
import java.util.ArrayList;
import java.util.Random;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.beans.binding.StringExpression;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;

public class Player {
    public static final int initialAp = 200;
    
    private IntegerProperty sanity = new SimpleIntegerProperty(40);
    private IntegerProperty spirits = new SimpleIntegerProperty(5);
    private IntegerProperty ap = new SimpleIntegerProperty(initialAp);
    private IntegerProperty mana = new SimpleIntegerProperty(10);
    private IntegerProperty crystals = new SimpleIntegerProperty(5000);
    private IntegerProperty busySpirits = new SimpleIntegerProperty();
    private IntegerProperty freeSpirits = new SimpleIntegerProperty();
    private IntegerProperty shrines = new SimpleIntegerProperty(1);
    private IntegerProperty shrinesLit = new SimpleIntegerProperty(0);
    
    
    
    private IntegerProperty maxSeeds = new SimpleIntegerProperty(3);
    private IntegerProperty manaIncome = new SimpleIntegerProperty(5);
    
    private int spiritRage = 0;
    
    private IntegerProperty spiritsCap = new SimpleIntegerProperty();
    private IntegerProperty maxAp = new SimpleIntegerProperty();
    
    private ArrayList<Unit> units;
    private ArrayList<Seed> seeds;
    private ArrayList<Upgrade> upgrades;
    private Cementary cementary;
    private ObservableList<Log> logs = FXCollections.observableArrayList();
    private int progress = 0;
    
    public Player(){
        cementary = new Cementary();
        
        units = new ArrayList<Unit>();
        for(int i=0; i<5; i++){
            units.add(new Dummy());
        }
        
        seeds = new ArrayList<Seed>();
        
        upgrades = new ArrayList<Upgrade>();
        for(int i=0; i<5; i++){
            Upgrade upgrade = new Upgrade(i);
            upgrades.add(upgrade);
        }
        
        busySpirits.bind(cementary.getFillProperty().add(upgrades.get(0).getFillProperty())
        .add(upgrades.get(1).getFillProperty())
        .add(upgrades.get(2).getFillProperty())
        .add(upgrades.get(3).getFillProperty())
        .add(upgrades.get(4).getFillProperty()));
        
        freeSpirits.bind(spirits.subtract(busySpirits));
        spiritsCap.bind(shrines.multiply(Game.shrinesCap));
        maxAp.bind(upgrades.get(4).getLvlProperty().multiply(Upgrade.ap).add(initialAp));
        
        manaIncome.bind(upgrades.get(0).getLvlProperty().multiply(Upgrade.manaIncome).add(Game.manaIncome));
        maxSeeds.bind(upgrades.get(1).getLvlProperty().add(3));
        
        cementary.getCapacityProperty().bind(upgrades.get(3).getLvlProperty().multiply(Upgrade.cemCap).add(Cementary.baseCapacity));
        cementary.getSingleOutputProperty().bind(upgrades.get(2).getLvlProperty().multiply(Upgrade.cryIncome).add(Cementary.baseOutput));
        
        logs.add(new Log(0, "You have begun your journey or some shit")); 
    }
    // gettery
    public StringBinding getSanityStringBind(){
        return sanity.asString();
    }
    public int getSanity(){
        return sanity.get();
    }
    private void addSanity(int x){
        sanity.set(sanity.get() + x);
    }
    
    public StringBinding getSpiritsStringBind(){
        return spirits.asString();
    }
    public int getSpirits(){
        return spirits.get();
    }
    private void addSpirits(int x){
        spirits.set(spirits.get() + x);
    }
    
    public StringBinding getCrystalsStringBind(){
        return crystals.asString();
    }
    public int getCrystals(){
        return crystals.get();
    }
    private void addCrystals(int x){
        crystals.set(crystals.get() + x);
    }
    
    public StringBinding getManaStringBind(){
        return mana.asString();
    }
    public int getMana(){
        return mana.get();
    }
    private void addMana(int x){
        mana.set(mana.get() + x);
    }
    
    public StringBinding getApStringBind(){
        return ap.asString();
    }
    public int getAp(){
        return ap.get();
    }
    private void addAp(int x){
        ap.set(ap.get() + x);
    }
    
    public StringBinding getFreeSpiritsStringBind(){
        return freeSpirits.asString();
    }
    public int getFreeSpirits(){
        return freeSpirits.get();
    }
    
    public StringBinding getSpiritsCapStringBind(){
        return spiritsCap.asString();
    }
    public int getSpiritsCap(){
        return spiritsCap.get();
    }
    
    public StringBinding getBusySpiritsStringBind(){
        return busySpirits.asString();
    }
    public int getBusySpirits(){
        return busySpirits.get();
    }
    
    public StringBinding getMaxApStringBind(){
        return maxAp.asString();
    }
    public int getMaxAp(){
        return maxAp.get();
    }
    public StringBinding getShrinesStringBind(){
        return shrines.asString();
    }
    public int getShrines(){
        return shrines.get();
    }
    private void addShrines(int x){
        shrines.set(shrines.get() + x);
    }
    
    public StringBinding getLitStringBind(){
        return shrinesLit.asString();
    }
    public int getLit(){
        return shrinesLit.get();
    }
    private void addLit(int x){
        shrinesLit.set(shrinesLit.get() + x);
    }
    public Cementary getCementary(){
        return cementary;
    }
    public ArrayList<Seed> getSeeds(){ //TO MI SIĘ W CHUJ NIE PODOBA
        return seeds;
    }
    public void setTable(TableView<Log> table){
        table.setItems(logs);
    }
    public StringExpression getUpgradeLeftLabel(int i){
        Upgrade up = upgrades.get(i);
        return Bindings.concat(up.getName());
    }
    public StringExpression getUpgradeRightLabel(int i){
        Upgrade up = upgrades.get(i);
        return Bindings.concat("Level: ", up.getLvlProperty(), ", progress: ", up.getProgressStringBind(), "/",
                up.getRequiredStringBind(), ", spirits working: ", up.getFillProperty());
    }
    public Unit getUnit(int i){
        return units.get(i);
    }
    ArrayList<Unit> getUnits(){ // potrzebuje tego w obrębie packega zeby dawac do Combatu
        return units;
    }
    public int getUnitsRealSize(){
        return units.stream().mapToInt(Unit::isReal).sum();
    }
    private int getFirstEmpty(){
        int x = 0;
        for(Unit unit : units){
            if(unit.getName() == "flag"){
                break;
            }else{
                x++;
            }
        }
        return x;
    }
    public int getProgress(){
        return progress;
    }
    
    ////////////////////////////////////////////////////////////////////////
   public void buildShrine(){
        if(crystals.get() < Game.shrineCryCost){
            AlertWindow.showInfo("not enough Crystals", "You need at least " + Game.shrineCryCost + " Crystals to build a Shrine");
        }else if(ap.get() < Game.shrineApCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.shrineApCost + " Action Points to build a Shrine");
        }else{
            addShrines(1);
            addCrystals(-Game.shrineCryCost);
            addAp(-Game.shrineApCost);
        }
    }
    public void litShrine(){
        if(ap.get() < Game.litCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.litCost + " Action Points to lit a Shrine");
        }else if(shrinesLit.get() >= shrines.get()){
            AlertWindow.showInfo("not enough Shrines", "All of Your Shrines are already lit");
        }else{
            addLit(1);
            addAp(-Game.litCost);
        }
    }
    public void extShrine(){
        if(ap.get() < Game.extCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.extCost + " Action Points to extinguish a Shrine");
        }else if(shrinesLit.get() == 0){
            AlertWindow.showInfo("not enough Shrines", "All of Your Shrines are already extinguished");
        }else{
            addLit(-1);
            addAp(-Game.extCost);
            spiritRage += Game.extRage;
        }
    }
    public void summonSpirit(){
        if(ap.get() < Game.summonApCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.summonApCost + " Action Points to summon a Spirit");
        }else if(crystals.get() < Game.summonCryCost){
            AlertWindow.showInfo("not enough Crystals", "You need at least " + Game.summonCryCost + " Crystals to summon a Spirit");
        }else if(spiritsCap.get() <= spirits.get()){
            AlertWindow.showInfo("too many spirits", "You need to build another Shrine to have more Spirits");
        }
        else{
            addSpirits(1);
            addCrystals(-Game.summonCryCost);
            addAp(-Game.summonApCost);
        }
    }
    public void cemAdd(){
        if(ap.get() < 1){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + "1" + " Action Point to do that");
        }else if(cementary.getFill() >= cementary.getCapacity()){
            AlertWindow.showInfo("limit reached", "Your cementary is already full");
        }else if(getFreeSpirits() < 1){
            AlertWindow.showInfo("not enough Spirits", "You need at least 1 free Spirit to do that");
        }else{
            cementary.addFill(1);
            addAp(-1);
        }
    }
    public boolean makeSeed(){
        if(seeds.size() >= maxSeeds.get()){
            AlertWindow.showInfo("limit reached", "You already have maximum number of seeds");
            return false;
        }else if(ap.get() < Game.seedCost){
            AlertWindow.showInfo("not enough Action Points", "You need to have at least " + Game.seedCost + " Action Points to create a Seed");
            return false;
        }else if(getFreeSpirits() < 1){
            AlertWindow.showInfo("not enough Spirits", "You need to have at least " + "1" + " free Spirit to sacrifise");
            return false;
        }else{
            addAp(-Game.seedCost);
            addSpirits(-1);
            Seed seed = new Seed();
            seeds.add(seed);
            return true;
        }
    }
    public int harvest(Integer i){
        Random generator = new Random();
        double rand = (generator.nextDouble() / 4) + 1;
        Seed seed = seeds.get(i);
        if(seed.getAge() == 0){
            AlertWindow.showInfo("Seed not ready", "Seeds can ba harvested after at least one turn!");
            return 0;
        }
        else{
            int income = (int)(manaIncome.get()*(rand * (1 + 0.25*seed.getAge())));
            addMana(income);
            addAp(Game.seedRest);
            seeds.remove(seed);
            return income;
        }
    }
    public void fillUpgrade(int i){
        if(ap.get() < 1){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + "1" + " Action Point to do that");
        }else if(getFreeSpirits() < 1){
            AlertWindow.showInfo("not enough Spirits", "You need at least 1 free Spirit to do that");
        }else{
            addAp(-1);
            upgrades.get(i).addFill(1);
        }
    }
    public void endTurn(int round){
        //TO DO: upgrady
        
        int sanityDisplay = -1;
        int manaDisplay = 0;
        int spiritsDisplay = 0;
        addSanity(-1); // obnizenie sanity
        
        if(mana.get() >= spirits.get()){ //obnizanie many
            addMana(-spirits.get());
            manaDisplay -= spirits.get();
        }else{
            int dif = (spirits.get() - mana.get()) / 2;
            if(dif == 0) dif = 1;
            
            manaDisplay -= mana.get();
            addMana(-mana.get());
            
            addSanity(-dif);
            sanityDisplay -= dif;
        }
        
        addAp(maxAp.get() - ap.get()); // resetowanie ap
        
        for(Seed i : seeds){ // starzenie seedow
            i.addAge(1);
        }
        addCrystals(cementary.getFullOutput()); // pobieranie krysztalow
        int crystalsDisplay = cementary.getFullOutput();
        cementary.addFill(-cementary.getFill());
        
        addSanity(-shrinesLit.get()); // obsługa zapalonych ołtarzy
        sanityDisplay -= shrinesLit.get();
        for(int i=0; i<shrinesLit.get(); i++){
            if(spiritsCap.get() > spirits.get()){
                spiritsDisplay++;
                addSpirits(1);
            }
        }
        spiritRage -= Game.rageDecay;
        
        //UPGRADY
        for(Upgrade i : upgrades){
            i.develop(logs, round);
        }
        
        if(sanity.get() > 0){
            String info = " You live another day! \n income: crystals: " + crystalsDisplay +", spirits: " + spiritsDisplay
            + "\n losses: mana: " + manaDisplay + ", sanity: " + sanityDisplay;
            logs.add(new Log(round, info));
            AlertWindow.showInfo("you lived another day!", info);
        }else AlertWindow.showInfo("u dead", "You died");
    }
    public int summonUnit(int i){
        if(ap.get() < Game.summonUnitApCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.summonUnitApCost + " Action Point to do that");
            return 5;
        }else if(crystals.get() < Game.summonUnitCryCost){
            AlertWindow.showInfo("not enough Crystals", "You need at least " + Game.summonUnitCryCost + " Crystals to do that");
            return 5;
        }else if(getUnitsRealSize() >= 5){
            AlertWindow.showInfo("limit reached", "You already have 5 units!");
            return 5;
        }else{
            int x = getFirstEmpty();
            switch(i){
                case 0:
                    units.set(x, new Ghoul());
                    break;
                case 1:
                    units.set(x, new Vampire());
                    break;
                case 2:
                    units.set(x, new Plagueman());
                    break;
                case 3:
                    units.set(x, new Phantom());
                    break;
                case 4:
                    units.set(x, new Butcher());
                    break;
            }
            addCrystals(-Game.summonUnitCryCost);
            addAp(-Game.summonUnitApCost);
            return x;
        }
    }
    public void upPower(int i){
        Unit unit = units.get(i);
        int cost = unit.getPowerCost();
        
        if(ap.get() < Game.upgradeUnitApCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.upgradeUnitApCost + " Action Point to do that");
        }else if(crystals.get() < cost){
            AlertWindow.showInfo("not enough Crystals", "You need at least " + cost + " Crystals to do that");
        }else{
            addAp(-Game.upgradeUnitApCost);
            addCrystals(-cost);
            unit.incPower();
        }
    }
    public void upShield(int i){
        Unit unit = units.get(i);
        int cost = unit.getShieldCost();
        
        if(ap.get() < Game.upgradeUnitApCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.upgradeUnitApCost + " Action Point to do that");
        }else if(crystals.get() < cost){
            AlertWindow.showInfo("not enough Crystals", "You need at least " + cost + " Crystals to do that");
        }else{
            addAp(-Game.upgradeUnitApCost);
            addCrystals(-cost);
            unit.incShield();
        }
    }
    public void upHealth(int i){
        Unit unit = units.get(i);
        int cost = unit.getHealthCost();
        
        if(ap.get() < Game.upgradeUnitApCost){
            AlertWindow.showInfo("not enough Action Points", "You need at least " + Game.upgradeUnitApCost + " Action Point to do that");
        }else if(crystals.get() < cost){
            AlertWindow.showInfo("not enough Crystals", "You need at least " + cost + " Crystals to do that");
        }else{
            addAp(-Game.upgradeUnitApCost);
            addCrystals(-cost);
            unit.incHealth();
        }
    }
    public void swapUnits(int i, int j){
        Unit unit = units.get(i);
        units.set(i, units.get(j));
        units.set(j, unit);
    }
}
