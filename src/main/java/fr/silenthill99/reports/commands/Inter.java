package fr.silenthill99.reports.commands;

import fr.silenthill99.reports.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inter implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (args.length == 0)
        {
            player.sendMessage("Erreur : Veuillez saisir l'identifiant d'un signalement !");
            return false;
        }
        if (args.length == 1)
        {
            if (Main.getInstance().staff.contains(Integer.valueOf(args[0])))
            {
                float x = Main.getInstance().getConfig().getInt("coordonnées.reportlocation.x");
                float y = Main.getInstance().getConfig().getInt("coordonnées.reportlocation.y");
                float z = Main.getInstance().getConfig().getInt("coordonnées.reportlocation.z");
                Main.getInstance().plaignant.get(Integer.valueOf(args[0])).teleport(new Location(player.getWorld(), x, y, z));
                Main.getInstance().accuse.get(Integer.valueOf(args[0])).getPlayer().teleport(new Location(player.getWorld(), x, y, z));
                player.teleport(new Location(player.getWorld(), x, y, z));

            }
            else
            {
                player.sendMessage("§4ERREUR §7: §cun §cSTAFF s'occupe déjà de cette inter !");
            }
        }
        else if (args[1].equalsIgnoreCase("close"))
        {
            float x = Main.getInstance().getConfig().getInt("coordonnées.closeinter.x");
            float y = Main.getInstance().getConfig().getInt("coordonnées.closeinter.y");
            float z = Main.getInstance().getConfig().getInt("coordonnées.closeinter.z");
            Main.getInstance().plaignant.get(Integer.valueOf(args[0])).teleport(new Location(player.getWorld(), x, y, z));
            Main.getInstance().accuse.get(Integer.valueOf(args[0])).getPlayer().teleport(new Location(player.getWorld(), x, y, z));

        }
        return false;
    }
}
