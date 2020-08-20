import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {

    List<String> inv;

    /**
     * Create an inventory
     */
    public Inventory()
    {
        inv = new ArrayList<>();
    }

    public void addItem(String item)
    {
        inv.add(item);
    }

    /**
     * Search for a particular item by name
     * @param itemName name of the item we're searching for
     * @return Boolean value
     */
    public Boolean getItem(String itemName)
    {
        return inv.contains(itemName);
    }

    /**
     * Remove an item from the inventory
     * @param item name of the item that we're removing
     */
    public void removeItem(String item)
    {
        inv.remove(item);
    }

    public Boolean checkInventory()
    {
        return inv.isEmpty();
    }

    public String printInventory()
    {
        String totalInventory = "";

        for(int i = 0; i < inv.size(); i++) {
            totalInventory = totalInventory + inv.get(i) + " ";
        }

        return totalInventory;
    }
}
