package myservermod;

import com.youthdigital.servermod.game.*;

public class Player extends PlayerData {
  
  
  
  public int credits = 1;
  
  /* VARIABLES */
  public Player(EntityPlayer parPlayerObject) {
    super(parPlayerObject);
  }
  
  @Override
  public void onUpdate() {
    
/* SPECIAL BLOCKS */  
    
    // teleport blocks
    if (Conditions.isStandingOnBlock("tpToBoss")) {
      Actions.teleportPlayers("bossArena");
    }
    
    if (Conditions.isStandingOnBlock("tpToBase")) {
      Actions.teleportPlayers(getTeamBase());
    }
    
    // join blocks
    if (Conditions.didRightClickBlock("redTeamJoin")) {
      GameManager.joinTeam("redTeam");
      Actions.displayChatMessageToAll(Actions.getPlayerName() + " has joined the" + getTeamChatColor() + " red team");
      Actions.startCountdown(30);
      Actions.playSound("redTeamJoin", "note.pling");
    }
    
    if (Conditions.didRightClickBlock("blueTeamJoin")) {
      GameManager.joinTeam("blueTeam");
      Actions.displayChatMessageToAll(Actions.getPlayerName() + " has joined the" + getTeamChatColor() + " blue team");
      Actions.startCountdown(30);
      Actions.playSound("redTeamJoin", "note.pling");
    }
    
    // healing block
    if (Conditions.isStandingOnBlock("health") && Conditions.secondsGoneBy(1)) {
      Actions.restoreHealth(2);
      Actions.restoreHunger(2);
      Actions.spawnParticles("health", EnumParticleTypes.PORTAL, 50);
    }
    
/* STORE */
    
    // archer
    if (Conditions.didRightClickBlock("archer") && credits >= 1) {
      Actions.giveItems(Items.bow, Enchantment.infinity, 3, Enchantment.punch, 2, Items.arrow, 64,
                       Items.chainmail_helmet, Enchantment.unbreaking, 2,
                       Items.chainmail_chestplate, Enchantment.unbreaking, 2,
                       Potion.moveSpeed, 10000, 0,
                       Items.iron_pickaxe);
      Actions.displayChatMessage("§8Aim true, archer!");
      credits = credits - 1;
    }
    
    // knight
    if (Conditions.didRightClickBlock("knight") && credits >= 1) {
      Actions.giveItems(Items.iron_sword, Enchantment.unbreaking, 3, Enchantment.knockback, 1,
                       Items.iron_helmet, Enchantment.unbreaking, 2, Enchantment.fireProtection, 1,
                       Items.iron_chestplate, Enchantment.unbreaking, 2, Enchantment.fireProtection, 1, 
                       Items.iron_leggings, Enchantment.unbreaking, 2, Enchantment.fireProtection, 1, 
                       Items.iron_boots, Enchantment.unbreaking, 2, Enchantment.fireProtection, 1,
                       Potion.moveSlowdown, 10000, 1,
                       Items.iron_pickaxe);
      Actions.displayChatMessage("§8I dub thee knight!");
      credits = credits - 1;
    }
   
/* DISPLAY */
    if (credits > 0) {
      Actions.displaySmallInfo("Credits: " + credits);
    }
  if (GameManager.isGameActive()) {
    if (Game.secondsLeft > 9) {
      Actions.displaySmallInfo(getTeamChatColor() + getTeamDisplayName() + " - " + Game.minutesLeft + ":" + Game.secondsLeft);
    } else {
      Actions.displaySmallInfo(getTeamChatColor() + getTeamDisplayName() + " - " + Game.minutesLeft + ":0" + Game.secondsLeft);
    }
 
    }
    
  /*  ITEM DROPS */
    
    if (Conditions.playerDied()) {
            Actions.dropItems(Items.diamond);
    }
    
    
    /* NOTIFICATIONS */
    
    if (Conditions.playerDied()) {
      Actions.displayChatMessage("§6-5 points");
    }
    
    if (Conditions.mobDestroyed(EntityZombie.class)) {
      Actions.displayChatMessage("§6+3 points");
    }
    
    if (Conditions.mobDestroyed(EntitySkeleton.class)) {
      Actions.displayChatMessage("§6+2 points");
    }
    
    if (Conditions.mobDestroyed(EntitySpider.class)) {
      Actions.displayChatMessage("§6+3 points");
    }
    
    if (Conditions.mobDestroyed(EntityCreeper.class)) {
      Actions.displayChatMessage("§6+5 points");
    }
    
    if (Conditions.itemsInInventory(Items.diamond, 5)) {
      Actions.displayChatMessage("§1Diamonds full!");
    }
    
    /* EASTER EGGS */
    
    if (Conditions.didRightClickBlock("clickEgg")) {
      Actions.giveItems(Items.diamond_sword, Enchantment.knockback, 4);
      Actions.displayChatMessage("§6You found an Easter Egg!");
      Actions.playSound("clickEgg", "random.orb");
      Actions.spawnParticles("clickEgg", EnumParticleTypes.SMOKE_LARGE, 100);
    }
    
    if (Conditions.isSneakingOnBlock("sneakEgg")) {
      Actions.givePotion(Potion.damageBoost, 12, 2);
      Actions.displayChatMessage("§6You found an Easter Egg!");
      Actions.playSound("sneakEgg", "random.orb");
      Actions.spawnParticles("sneakEgg", EnumParticleTypes.SMOKE_LARGE, 100);
    }
    
    if (Conditions.isArrowInArea("arrowEgg", 3)) {
      Actions.displayChatMessage("§6You found a Cheat Code: §8/cheat suitUp ");
      Actions.playSound("arrowEgg", "random.orb");
      Actions.spawnParticles("arrowEgg", EnumParticleTypes.FLAME, 55);
    }
    
    if (Conditions.didRightClickBlock("wizardCheat")) {
      Actions.displayChatMessage("§6You found a Cheat Code: §8/cheat wizardCheat");
      Actions.playSound("wizardCheat", "mob.enderdragon.growl");
      Actions.spawnParticles("wizardCheatwizardCheat", EnumParticleTypes.LAVA, 100);
    }
    
    if (Conditions.isJumpingOnBlock("notchApple")) {
      Actions.giveItems(Items.golden_apple, 16);
      Actions.displayChatMessage("§6You found an Easter Egg!");
      Actions.playSound("notchApple", "random.orb");
      Actions.spawnParticles("notchApple", EnumParticleTypes.SMOKE_LARGE, 100);
    }
    
    if (Conditions.isArrowInArea("arrowChicken", 3)) {
      Actions.spawnEntity("arrowChicken", EntityChicken.class, 50);
      Actions.displayChatMessage("§6You found an Easter Egg!");
      Actions.playSound("arrowChicken", "random.orb");
      Actions.spawnParticles("arrowChicken", EnumParticleTypes.SMOKE_LARGE, 100);
    }
    
    /* CHEAT */
    
    if (Conditions.cheatEntered("suitUp")) {
      Actions.giveItems(Items.diamond_boots, Items.diamond_leggings, Items.diamond_chestplate, Items.diamond_helmet);
      Actions.displayChatMessage("§8Cheat Activated");
    }
    
    if (Conditions.cheatEntered("pointParty")) {
      getTeam().points += GameManager.getValueFromCheat(1);
      Actions.displayChatMessage("§8Cheat Activated");
    }
    
    if (Conditions.cheatEntered("ninjaSpeed")) {
      Actions.givePotion(Potion.moveSpeed, GameManager.getValueFromCheat(1), GameManager.getValueFromCheat(2));
      Actions.givePotion(Potion.jump, GameManager.getValueFromCheat(1), GameManager.getValueFromCheat(2));
      Actions.displayChatMessage("§8Cheat Activated");
    }
    
    if (Conditions.cheatEntered("healtBoost")) {
       Actions.givePotion(Potion.regeneration, GameManager.getValueFromCheat(1), GameManager.getValueFromCheat(2));
      Actions.givePotion(Potion.absorption, GameManager.getValueFromCheat(1), GameManager.getValueFromCheat(2));
      Actions.displayChatMessage("§8Cheat Activated");
    }
    
    if (Conditions.cheatEntered("wizardCheat")) {
      Actions.giveItems(Items.stick, Enchantment.knockback, 3, Enchantment.sharpness, 2, Items.iron_chestplate, Items.diamond_leggings, Items.diamond_boots,
                       Potion.damageBoost, 1);
      Actions.displayChatMessage("§8Cheat Activated");
    }
    
    
    
  
    
  }
  
  
  @Override
  public void onJoinedServer(){
    
    Actions.teleportPlayers("lobbySpawn");
    
    /* INSTRUCTIONS */
    
    Actions.displayChatMessage("§8How to play");
    Actions.displayChatMessage("==================");
    Actions.displayChatMessage("§7Get a kit and join a team");
    Actions.displayChatMessage("§7Bring diamonds back to your chest and fight mobs");
    Actions.displayChatMessage("§7100 points to win");
    
    
  }
  
  @Override
  public void onStartGame() {
    
    Actions.teleportPlayers(getTeamBase());
    Actions.playSoundAtPlayersWithVolumeAndPitch("random.levelup", 0.5F, 0.5F);
    
  }
  
  @Override
  public void onResetGameToLobby() {
    
    credits = 1;
    Actions.restoreHealth(20);
    Actions.restoreHunger(20);
    Actions.clearPotions();
    Actions.removeItems();
    Actions.teleportPlayers("lobbySpawn");
    
  }
  
  @Override
  public void onRespawned() {
    
    Actions.teleportPlayers(getTeamBase());
    
  }
  
}




