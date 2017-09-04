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
                org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();

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



                Score score3 = objective.getScore("Players left: " + ChatColor.GREEN + skywars.players);

                score3.setScore(8);



                Score space3 = objective.getScore("         ");

                space3.setScore(7);



                Score score4 = objective.getScore("Kills: " + ChatColor.GREEN + skywars.kills.get(p));

                score4.setScore(6);



                Score space4 = objective.getScore("             ");

                space4.setScore(5);



                Score score5 = objective.getScore("Map: " + ChatColor.GREEN  + skywars.map.getName());

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
