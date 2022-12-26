package fr.silenthill99.reports.commands;

import fr.silenthill99.reports.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inter implements CommandExecutor {
    Main main = Main.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (args.length == 0)
        {
            player.sendMessage("Erreur : Veuillez saisir l'identifiant d'un signalement !");
            return false;
        }

        Player plaignant = main.plaignant.get(Integer.valueOf(args[0]));
        Player accuse = (Player) main.accuse.get(Integer.valueOf(args[0]));
        if (args.length == 1)
        {
            if (main.staff.contains(Integer.valueOf(args[0])))
            {
                float x = main.getConfig().getInt("coordonnées.reportlocation.x");
                float y = main.getConfig().getInt("coordonnées.reportlocation.y");
                float z = main.getConfig().getInt("coordonnées.reportlocation.z");
                plaignant.teleport(new Location(player.getWorld(), x, y, z));
                accuse.teleport(new Location(player.getWorld(), x, y, z));
                player.teleport(new Location(player.getWorld(), x, y, z));
                main.staff.remove(Integer.valueOf(args[0]));

            }
            else
            {
                player.sendMessage("§4ERREUR §7: §cun §cSTAFF s'occupe déjà de cette inter !");
            }
        }
        else if (args[1].equalsIgnoreCase("close"))
        {
            float x = main.getConfig().getInt("coordonnées.closeinter.x");
            float y = main.getConfig().getInt("coordonnées.closeinter.y");
            float z = main.getConfig().getInt("coordonnées.closeinter.z");
            plaignant.teleport(new Location(player.getWorld(), x, y, z));
            accuse.getPlayer().teleport(new Location(player.getWorld(), x, y, z));

        }
        return false;
    }
}
