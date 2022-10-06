package fr.silenthill99.reports.commands;

import fr.silenthill99.reports.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetClose implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
        {
            System.out.println("Cette commande ne peut pas être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;

        float x = (float) player.getLocation().getX();
        float y = (float) player.getLocation().getY();
        float z = (float) player.getLocation().getZ();

        Main.getInstance().getConfig().set("coordonnées.closeinter.x", x);
        Main.getInstance().getConfig().set("coordonnées.closeinter.y", y);
        Main.getInstance().getConfig().set("coordonnées.closeinter.z", z);
        player.sendMessage("Spawn placé en : x = " + String.format("%.2f", x) + ", y = " + String.format("%.2f", y) + ", z = " + String.format("%.2f", z));
        Main.getInstance().saveConfig();

        return false;
    }
}
