package fr.silenthill99.reports.commands;

import fr.silenthill99.reports.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length == 0)
        {
            player.sendMessage("Erreur : Veuillez saisir un pseudo !");
            return false;
        }

        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

        if (!target.isOnline())
        {
            player.sendMessage("Erreur : ce joueur semble s'être déconnecté !");
            return false;
        }

        if (args.length == 1)
        {
            player.sendMessage("Erreur : Veuillez saisir une raison !");
            return false;
        }

        if (args[1].equalsIgnoreCase("hack") || args[1].equalsIgnoreCase("triche"))
        {
            player.sendMessage("Erreur : cette raison est invalide !");
            return false;
        }

        StringBuilder raison = new StringBuilder();

        for (String part : args)
        {
            if (!part.equalsIgnoreCase(args[0]))
            {
                raison.append(part + " ");
            }
        }

        int inter = Main.getInstance().getConfig().getInt("valeurs.inter") + 1;
        Main.getInstance().getConfig().set("valeurs.inter", inter);
        Main.getInstance().saveConfig();
        player.sendMessage(ChatColor.DARK_GREEN + "REPORT " + ChatColor.GREEN + "Votre signalement a été pris en compte | inter : " + inter);

        for (Player players : Bukkit.getOnlinePlayers())
        {
            if (players.hasPermission("group.modo-stagiaire"))
            {

                Main.getInstance().plaignant.put(inter, player);
                Main.getInstance().accuse.put(inter, target);
                Main.getInstance().raison.put(inter, raison);
                Main.getInstance().staff.add(inter);
                Bukkit.dispatchCommand(players, "reportsend");
            }
            else
            {
                player.sendMessage("§4ERREUR §7: §cIl n'y a aucun STAFF §cde connecté !");
            }

        }
        return false;
    }
}
