package knockdowns.openmc;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Skywars extends JavaPlugin
{
    public int players;

    public HashMap<Player, Integer> kills = new HashMap<>();

    public ArrayList<Player> playersAlive = new ArrayList<>();

    public World map;

    public ArrayList<ItemStack> itemsToFillChest = new ArrayList<>();

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
        saveDefaultConfig();
        map = Bukkit.getWorld(getConfig().getString("World"));
    }

    @Override
    public void onDisable() {
        saveConfig();
        
    }

    private void registerCommands() {

    }

    private void registerListeners() {

    }
}
