import java.util.ArrayList;
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
        if (inv.contains(itemName)) {
            return true;
            }

        return false;
    }

    /**
     * Remove an item from the inventory
     * @param item name of the item that we're removing
     */
    public void removeItem(String item)
    {
        inv.remove(item);
    }
}
