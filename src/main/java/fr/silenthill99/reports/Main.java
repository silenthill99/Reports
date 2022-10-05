package fr.silenthill99.reports;

import fr.silenthill99.reports.commands.SetReport;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance()
    {
        return instance;
    }


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
        getCommand("setreport").setExecutor(new SetReport());
    }
}
