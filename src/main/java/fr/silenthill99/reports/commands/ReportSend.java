package fr.silenthill99.reports.commands;

import fr.silenthill99.reports.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportSend implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (player.hasPermission("group.modo-stagiaire"))
            {
                player.sendMessage("");
                player.sendMessage(" §a☆━━━━━━━━Inter : " + Main.getInstance().getConfig().getInt("valeurs.inter") + "%━━━━━━━━━━━☆");
                player.sendMessage(" ");
                player.sendMessage(" §aPlaignant : " + Main.getInstance().plaignant.get(Main.getInstance().getConfig().getInt("valeurs.inter")).getName());
                player.sendMessage(" §aAccusé : " + Main.getInstance().accuse.get(Main.getInstance().getConfig().getInt("valeurs.inter")).getName());
                player.sendMessage(" §aMotif de la Plainte : " + Main.getInstance().raison.get(Main.getInstance().getConfig().getInt("valeurs.inter")));
                player.sendMessage(" ");
                player.sendMessage(" Faites /inter " + Main.getInstance().getConfig().getInt("valeurs.inter"));
                player.sendMessage(" ");
                player.sendMessage(" §a☆━━━━━━━━━ " + Main.getInstance().getConfig().getString("options.prefix") + "━━━━━━━━━━☆");
            }
        }
        return false;
    }
}
