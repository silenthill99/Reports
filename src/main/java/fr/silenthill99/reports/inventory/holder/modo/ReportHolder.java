package fr.silenthill99.reports.inventory.holder.modo;

import fr.silenthill99.reports.inventory.SilenthillHolder;
import fr.silenthill99.reports.inventory.hook.modo.ReportInventory.*;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

public class ReportHolder extends SilenthillHolder
{
    private final OfflinePlayer plaignant;
    private final OfflinePlayer accuse;
    private final int inter;

    public ReportHolder(OfflinePlayer plaignant, OfflinePlayer accuse, int inter)
    {
        this.plaignant = plaignant;
        this.accuse = accuse;
        this.inter = inter;
    }

    public OfflinePlayer getPlaignant()
    {
        return this.plaignant;
    }

    public OfflinePlayer getAccuse()
    {
        return this.accuse;
    }

    public int getInter()
    {
        return this.inter;
    }

    public HashMap<Integer, Interadmin> salle = new HashMap<>();
}
