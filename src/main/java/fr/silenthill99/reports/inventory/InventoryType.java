package fr.silenthill99.reports.inventory;

import fr.silenthill99.reports.inventory.hook.modo.ReportInventory;

public enum InventoryType
{
    REPORTS(new ReportInventory())
    ;
    private final AbstractInventory<?> inv;

    InventoryType(AbstractInventory<?> inv)
    {
        this.inv = inv;
    }

    public AbstractInventory<?> getInv()
    {
        return this.inv;
    }
}
