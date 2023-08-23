package fr.silenthill99.reports.inventory.hook.modo;

import fr.silenthill99.reports.ItemBuilder;
import fr.silenthill99.reports.Main;
import fr.silenthill99.reports.inventory.AbstractInventory;
import fr.silenthill99.reports.inventory.holder.modo.ReportHolder;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ReportInventory extends AbstractInventory<ReportHolder>
{
    public ReportInventory() {
        super(ReportHolder.class);
    }

    Main main = Main.getInstance();

    @Override
    public void openInventory(Player player, Object... args)
    {
        Player plaignant = (Player) args[0];
        Player accuse = (Player) args[1];
        int inter = (int) args[2];
        ReportHolder holder = new ReportHolder(plaignant, accuse, inter);

        Inventory inv = createInventory(holder, 27, "Inter n°" + inter);
        String nom = "Salle n°";
        int slot = 0;
        for (Interadmin salle :  Interadmin.values())
        {
            holder.salle.put(slot, salle);
            if (!main.getConfig().contains("salle_" + salle.getNumero() + ".staff_id"))
            {
                inv.setItem(slot++, new ItemBuilder(Material.GREEN_WOOL).setName(ChatColor.GREEN + nom + salle.getNumero()).toItemStack());
            }
            else
            {
                inv.setItem(slot++, new ItemBuilder(Material.RED_WOOL).setName(ChatColor.RED + nom + salle.getNumero()).toItemStack());
            }
        }
        player.openInventory(inv);
    }

    @Override
    public void manageInventory(InventoryClickEvent event, ItemStack current, Player player, ReportHolder holder)
    {
        Player plaignant = (Player) holder.getPlaignant();
        Player accuse = (Player) holder.getAccuse();
        int inter = holder.getInter();

        Interadmin salle = holder.salle.get(event.getSlot());

        switch (current.getType())
        {
            case GREEN_WOOL:
            {
                main.getConfig().set("salle_" + salle.getNumero() + ".staff_id", player.getUniqueId());
                main.getConfig().set("salle_" + salle.getNumero() + ".plaignant_loc", plaignant.getLocation());
                main.getConfig().set("salle_" + salle.getNumero() + ".accuse_loc", accuse.getLocation());

                player.teleport(salle.getLoc());
                plaignant.teleport(salle.getLoc());
                accuse.teleport(salle.getLoc());

                main.staff.remove(inter);
                break;
            }
            case RED_WOOL:
            {
                player.sendMessage(ChatColor.RED + "Cette salle n'est pas disponible !");
                break;
            }
        }
    }

    static World world = Bukkit.getWorld("world");

    public enum Interadmin
    {
        SALLE_1(1, new Location(world, -34, 70, 189))
        ;
        private final int numero;
        private final Location loc;

        Interadmin(int numero, Location loc)
        {
            this.numero = numero;
            this.loc = loc;
        }

        public int getNumero()
        {
            return this.numero;
        }


        public Location getLoc()
        {
            return this.loc;
        }
    }
}
