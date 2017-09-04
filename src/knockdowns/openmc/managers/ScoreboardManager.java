package knockdowns.openmc.managers;

import knockdowns.openmc.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager
{
    private Skywars skywars;
    public ScoreboardManager(Skywars skywars) {
        this.skywars = skywars;
    }

    public void refreshScoreboard(Player p) {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) this, new Runnable() {
            public void run() {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                final Scoreboard board = manager.getNewScoreboard();
                final Objective objective = board.registerNewObjective("test", "dummy");

                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                objective.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "SKYWARS");

                Score score8 = objective.getScore("  ");
                score8.setScore(6);

                Score score7 = objective.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "Map:");
                score7.setScore(5);

                Score score6 = objective.getScore(ChatColor.GREEN + "ForestIsland");
                score6.setScore(4);

                Score score5 = objective.getScore("   ");
                score5.setScore(3);

                Score score4 = objective.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "Kills:");
                score4.setScore(2);

                Score score3 = objective.getScore(ChatColor.GREEN + "" + kills.get(p));
                score3.setScore(1);




                p.setScoreboard(board);



            }

        },0, 20 * 10);

    }

}
