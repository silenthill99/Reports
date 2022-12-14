package fr.silenthill99.reports;

import fr.silenthill99.reports.commands.*;
import fr.silenthill99.reports.inventory.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
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

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getLogger().info("Le plugin est opérationnel !");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryManager(), this);
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
