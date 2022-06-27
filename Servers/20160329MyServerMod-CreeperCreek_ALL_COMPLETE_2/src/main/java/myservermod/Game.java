package myservermod;

import com.youthdigital.servermod.game.*;

public class Game extends TeamData {
  
  /* VARIABLES */
  public static int minutesLeft;
  public static int secondsLeft;
  
  public void setTimer() {
    minutesLeft = 1;
    secondsLeft = 5;
  }
  
  
  
  public Game(ChatColors teamColor, String teamNameDisplay) {
    super("All Players", teamColor, teamNameDisplay);
    setTimer();
  }
  
  public static void setupGameRules() {
    
    

/* SERVER PROPERTIES */
    
    // access
    GameInfo.setServerDescription("§6> §5> §6> §5> §2A §1Minecraft §6Server §4By §8onbekendegozer §6< §5< §6<");
    GameInfo.addToWhitelist("onbekendegozer, IsabelleSolange, Mees05, Berebouwer, willempie606, finnie808, Bobdie, onbekendegast");
    GameInfo.addToOPs("onbekendegozer, IsabelleSolange");
    
    // game rules
    GameInfo.disableBlockBreaking();
    GameInfo.addAllowedBreakableBlock(Blocks.diamond_ore);
    GameInfo.addMaxItemsHeldLimit(Items.diamond, 5);
    GameInfo.disableMobSpawning();
    GameInfo.setTime(12000);
    GameInfo.setDifficulty(3);
    GameInfo.isRaining(false);
    GameInfo.setPVP(true);
    GameInfo.setTeamPVP(false);
    
    
    // teams
    Team blueTeam = new  Team("blueTeam", "Blue Team", ChatColors.BLUE);
    Team redTeam = new  Team("redTeam", "Red Team", ChatColors.RED);
    GameInfo.addTeam(blueTeam);
    GameInfo.addTeam(redTeam);
    
    
    
    
    
    
    
    // pointstore items
    Welcome.setWelcomeMessage("§1Welcome To The Server!", "§6§lHave Fun!");
    Welcome.setWelcomeSound("mob.wither.spawn");
    
  }
  
  @Override
  public void onUpdate() {
    
/* MOB SPAWNING */    
    
    // skeleton
    if (Conditions.secondsGoneBy(2)) {
      Actions.spawnSpecialEntityInRange("skeletonSpawn", 5, EntitySkeleton.class, 1,
                                       Items.bow, Enchantment.punch, 1,
                                       Items.leather_helmet,
                                       SharedMonsterAttributes.maxHealth, 1); 
      
    }
    
    // zombie
    if (Conditions.secondsGoneBy(2)) {
      Actions.spawnSpecialEntityInRange("zombieSpawn", 5, EntityZombie.class, 1,
                                       Items.stone_pickaxe, Enchantment.knockback, 3,
                                       Items.chainmail_boots,
                                       Items.chainmail_chestplate,
                                       Items.golden_leggings,
                                       Items.chainmail_helmet,
                                       Potion.fireResistance, 10000,
                                       Potion.invisibility, 10000,
                                       SharedMonsterAttributes.maxHealth, 5,
                                       SharedMonsterAttributes.movementSpeed, 0.3);
      
    }
      
       // spider
       if (Conditions.secondsGoneBy(15)) {
      Actions.spawnSpecialEntityInRange("spiderSpawn", 10, EntitySpider.class, 1,
                                       SharedMonsterAttributes.maxHealth, 20,
                                       Potion.invisibility, 10000,
                                       Potion.moveSpeed, 0.5);
    }
    
    // creeper
    if (Conditions.secondsGoneBy(8)) {
      Actions.spawnSpecialEntityInRange("creeperSpawn", 8, EntityCreeper.class, 1,
                                       SharedMonsterAttributes.maxHealth, 25,
                                       Potion.jump, 10000,
                                       Items.iron_axe);
    }
    
    
    // boss
    if (minutesLeft == 1 && secondsLeft == 0 && Conditions.secondsGoneBy(1)) {
      MobMania.spawnMountedEntityInRange("BossSpawn", 100, 1,
                                         EntityWither.class, 1, "Creek King",
                                         EntitySkeleton.class, 1,
                                         SharedMonsterAttributes.maxHealth, 30,
                                         Items.iron_helmet, Enchantment.unbreaking, 1
                                         );
    }
    
    
   
/* CHESTS */
    
    Random random = new Random();   
    int fillRate = random.nextInt(5) + 3;
    
    String[] chestArray = { "chest1", "chest2", "chest3", "chest4" };
    Item[] itemArray = { Items.golden_apple, Items.stone_sword, Items.diamond_pickaxe, Items.cookie, Items.iron_helmet };
    
    int chestNumber = random.nextInt(chestArray.length);
    int itemNumber = random.nextInt(itemArray.length);
    
    if(Conditions.secondsGoneBy(fillRate) && Conditions.getChestCount(chestArray[chestNumber]) < 3){
      Actions.fillChest(chestArray[chestNumber], itemArray[itemNumber], 1);
    }
    
    // wolf chest
    int wolfFillRate = random.nextInt(5) + 10;
    if (Conditions.secondsGoneBy(wolfFillRate) && Conditions.getChestCount("wolfChest") < 9) {
      SuperItems.fillChestWithSpecialItem("wolfChest", SpawnEggs.wolf);
      Actions.fillChest("wolfChest", Items.bone, 8);
    }
    
    // potion chest
    int potionFillRate = random.nextInt(8) + 8;
    Potions[] potionArray = { Potions.Poison_II, Potions.Strength_II, Potions.Swiftness_II };
    int potionNumber = random.nextInt(potionArray.length);
    if (Conditions.secondsGoneBy(potionFillRate) && Conditions.getChestCount("potionChest") < 3) {
      SuperItems.fillChestWithSpecialItem("potionChest", potionArray[potionNumber]);
    }
    
       
       
    
    
    
    
 
    
    
    
    /* TIMER */
    
    if (GameManager.isGameActive()) {
      if (Conditions.secondsGoneBy(1)) {
      if (secondsLeft > 0) {
        secondsLeft--;
      } else {
        minutesLeft--;
        secondsLeft = 59; 
      }
     } 
    }
    
    if (secondsLeft == 0 && minutesLeft == 0) {
      GameManager.triggerGameOver();
    }
   
    
   /* ORE RESTORE */ 
    
    if (Conditions.secondsGoneBy(60)) {
      Actions.loadBlocks("ores");
      Actions.saveBlocks("ores", 20, Blocks.diamond_ore);
    }
    
    
    
  }
  
  @Override
  public void onResetGameToLobby() {
    
    setTimer();
    Actions.loadBlocks("ores");
    
  }
  
  @Override
  public void onStartGame() {
    
    Actions.saveBlocks("ores", 20, Blocks.diamond_ore);
    
  }
  
}