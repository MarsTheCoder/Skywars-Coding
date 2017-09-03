package com.HackPixel.Skywars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;



public class Skywars extends JavaPlugin implements Listener{
	
	int players;
	HashMap<Player, Integer> kills = new HashMap<>();
	ArrayList<Player> playersAlive = new ArrayList<>();
	World map;
	ArrayList<ItemStack> itemsToFillChest = new ArrayList<>();
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		
		map = Bukkit.getWorld("Tribute");
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(Bukkit.getOnlinePlayers().size() == 2) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				playersAlive.add(p);
				refreshScoreboard(p);
			}
		
			
			itemsToFillChest.add(new ItemStack(Material.DIAMOND_SWORD));
			itemsToFillChest.add(new ItemStack(Material.BOW));
			itemsToFillChest.add(new ItemStack(Material.ARROW, 11));
			itemsToFillChest.add(new ItemStack(Material.DIAMOND_HELMET));
			itemsToFillChest.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
			itemsToFillChest.add(new ItemStack(Material.DIAMOND_LEGGINGS));
			itemsToFillChest.add(new ItemStack(Material.DIAMOND_BOOTS));
			Player p1 = playersAlive.get(0);
			Player p2 = playersAlive.get(1);
			
			players = 0;
			players = 2;
			
			p1.getInventory().clear();
			p1.setGameMode(GameMode.SURVIVAL);
			p1.setHealth(20.0);
			p1.setFoodLevel(20);
			p1.teleport(new Location(map, 48.373, 71.22429, 52.135));
			p1.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
			p1.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
			p1.updateInventory();
			
			p2.getInventory().clear();
			p2.setGameMode(GameMode.SURVIVAL);
			p2.setHealth(20.0);
			p2.setFoodLevel(20);
			p2.teleport(new Location(map, -50.304, 68.00000, -9.030));
			p2.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
			p2.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
			p2.updateInventory();
			
			kills.put(p2, 0);
			kills.put(p1, 0);
			
			
			
			for (Chunk c : map.getLoadedChunks()) {

	            for (BlockState b : c.getTileEntities()) {

	                if (b instanceof Chest) {
	                    Chest chest = (Chest) b;
	                    Inventory inventory = chest.getBlockInventory();   
	                    
	                    inventory.clear();
	                    //Fill them up
	                    inventory.addItem(itemsToFillChest.get(new Random().nextInt(itemsToFillChest.size())));
	                }
	            }
	        }
			
			
			
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				
				@Override
				public void run() {
					for (Chunk c : map.getLoadedChunks()) {

			            for (BlockState b : c.getTileEntities()) {

			                if (b instanceof Chest) {
			                    Chest chest = (Chest) b;
			                    Inventory inventory = chest.getBlockInventory();   
			                    
			                    inventory.clear();
			                    //Fill them up
			                    inventory.addItem(itemsToFillChest.get(new Random().nextInt(itemsToFillChest.size())));
			                    Bukkit.broadcastMessage(ChatColor.GRAY + "Chests have been refilled!");
			                    return;
			                }
			            }
			        }
					
				}
			}, 600L);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		players--;
		Player entity = (Player) e.getEntity();
		entity.setGameMode(GameMode.SPECTATOR);
		int killsForKiller = kills.get(entity.getKiller());
		
		
		
		kills.put(entity.getKiller(), killsForKiller + 1);
		refreshScoreboard(entity.getKiller());
		
		if(players == 1) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.getGameMode() != GameMode.SPECTATOR) {
					Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.GRAY + " Has Won!");
				}
			}
		}
	}
	
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		e.setCancelled(false);
	}
	
	
	
	public void refreshScoreboard(Player p) {

		
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				final Scoreboard board = manager.getNewScoreboard();
				final Objective objective = board.registerNewObjective("test", "dummy");
				
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
				objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "SKYWARS");

				Score score = objective.getScore(ChatColor.GRAY + "Solo");
				score.setScore(13);
				
				Score space1 = objective.getScore("    ");
				space1.setScore(12);
				
				Score score1 = objective.getScore("Next Event:");
				score1.setScore(11);
				
				Score score2 = objective.getScore(ChatColor.GREEN + "Refill");
				score2.setScore(10);
				
				Score space2 = objective.getScore("       ");
				space2.setScore(9);
				
				Score score3 = objective.getScore("Players left: " + ChatColor.GREEN + players);
				score3.setScore(8);
				
				Score space3 = objective.getScore("         ");
				space3.setScore(7);
				
				Score score4 = objective.getScore("Kills: " + ChatColor.GREEN + kills.get(p));
				score4.setScore(6);
				
				Score space4 = objective.getScore("             ");
				space4.setScore(5);
				
				Score score5 = objective.getScore("Map: " + ChatColor.GREEN  + map.getName());
				score5.setScore(4);
				
				Score score6 = objective.getScore("Mode: " + ChatColor.RED + "Insane");
				score6.setScore(3);
				
				Score space5 = objective.getScore("             ");
				space5.setScore(2);
				
				Score score7 = objective.getScore(ChatColor.YELLOW + "www.hackpixel.me");
				score7.setScore(1);
				
				
				
				
				
				p.setScoreboard(board);

			}
		},0, 20 * 10);
	}
}
