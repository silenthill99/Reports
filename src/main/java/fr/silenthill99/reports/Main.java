package fr.silenthill99.reports;

import fr.silenthill99.reports.commands.*;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }

    public HashMap<Integer, Player> plaignant = new HashMap<>();
    public HashMap<Integer, OfflinePlayer> accuse = new HashMap<>();
    public HashMap<Integer, StringBuilder> raison = new HashMap<>();
    public List<Integer> staff = new ArrayList<>();

    public int inter = 0;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        commands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void commands()
    {
        getCommand("inter").setExecutor(new Inter());
        getCommand("setreport").setExecutor(new SetReport());
        getCommand("setclose").setExecutor(new SetClose());
        getCommand("report").setExecutor(new Report());
        getCommand("reportsend").setExecutor(new ReportSend());
    }
}
