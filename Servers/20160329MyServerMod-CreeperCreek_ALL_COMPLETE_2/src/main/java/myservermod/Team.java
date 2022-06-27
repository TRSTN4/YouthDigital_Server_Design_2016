package myservermod;

import com.youthdigital.servermod.game.*;

public class Team extends TeamData {

  
  // points to win
  public int pointsToWin = 100;
  
  public Team(String blockName, String teamDisplayName, ChatColors teamColor) {
    super(blockName, teamDisplayName, teamColor);
  }
  
  @Override
  public void onUpdate() {
    
    
    
    
    
    
    /* POINTS */
    
    // zombies
    if (Conditions.mobDestroyed(EntityZombie.class)) {
      points += 3;
    }
    
    // skeletons
    if (Conditions.mobDestroyed(EntitySkeleton.class)) {
      points += 2;
    }
    
    // spiders
    if (Conditions.mobDestroyed(EntitySpider.class)) {
      points += 3;
    }
    
    // creepers
    if (Conditions.mobDestroyed(EntityCreeper.class)) {
      points += 5;
    }
    
    // player
    if (Conditions.playerDied()) {
      points -= 5;
    }
    
    // chest
    points += Actions.updateScoreFromChestItemCount(getTeamName() + "Chest", Items.diamond, 5);
    Actions.clearChest(getTeamName() + "Chest");
    
    // game over
    if (points >= pointsToWin) {
      GameManager.triggerGameOver();
      Actions.spawnFireworks("fireworks", getTeamRGBColor());
      Celebration.spawnCustomFireworks("knight", Color.CYAN, 3, true, false);
      Celebration.spawnCustomFireworks("archer", Color.GREEN, 3, true, false);
      Celebration.spawnCustomFireworks("centerFireWorks", Color.GREEN, 3, false, true);
      Actions.displayTitleToAll(getTeamChatColor() + "Game Ended", getTeamDisplayName() + " is the winner!");
    }
    
    
  }
  
  @Override
  public void onResetGameToLobby() {
    
    Actions.displayChatMessageToAll(getTeamChatColor() + getTeamDisplayName() + ChatColors.WHITE + "Points: " + points);
    points = 0;
    
  }
  
  @Override
  public void onStartGame() {
    
  }
  
}