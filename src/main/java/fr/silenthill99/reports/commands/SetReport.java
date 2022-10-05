package fr.silenthill99.reports.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetReport implements CommandExecutor
{
    private Location location;

    public SetReport(Location location)
    {
        this.location = location;
    }

    public SetReport()
    {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            System.out.println("Cette commande ne peut être éxécutée par la console !");
            return false;
        }

        Player player = (Player) sender;
        Location loc = new SetReport(player.getLocation()).getLocation();
        player.sendMessage("Spawn placé en " + player.getLocation().getX() + " " + player.getLocation().getY() + " " + player.getLocation().getZ());

        return false;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
