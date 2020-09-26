import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Inventory
 *
 * This class is part of the "Dungeon" application.
 * "Dungeon" is a very simple, text based adventure game.
 *
 * An "Inventory" holds the items in the game. It can be used
 * by the player and the rooms. You can add, get, and remove
 * items. You can check if the inventory is empty.
 *
 * @author Christopher Mitchell
 * @version 2020.08.26
 */

public class Inventory extends Thing {

    private ThingList things = new ThingList();

    /**
     * Create an inventory
     */
    public Inventory(String aName, String aDescription, ThingList tl)
    {
        super(aName, aDescription);
        things = tl;
    }

    /**
     * @return the things
     */
    public ThingList getThings() {
        return things;
    }

    /**
     * @param things the things to set
     */
    public void setThings(ThingList things) {
        this.things = things;
    }

//    /**
//     * Add an item to the inventory
//     * @param item name of the item to be added
//     */
//    public void addItem(String item)
//    {
//        inv.add(item);
//    }
//
//    /**
//     * Search for a particular item by name
//     * @param itemName name of the item we're searching for
//     * @return Boolean value
//     */
//    public Boolean getItem(String itemName)
//    {
//        return inv.contains(itemName);
//    }
//
//    /**
//     * Remove an item from the inventory
//     * @param item name of the item that we're removing
//     */
//    public void removeItem(String item)
//    {
//        inv.remove(item);
//    }
//
//    /**
//     * Check if the inventory is empty
//     * @return Returns True if the inventory is empty
//     */
//    public Boolean checkInventory()
//    {
//        return inv.isEmpty();
//    }
//
//    /**
//     * Get a string list of all items in the inventory
//     * @return String variable of all the items in the inventory
//     */
//    public String printInventory()
//    {
//        String totalInventory = "";
//
//        for(int i = 0; i < inv.size(); i++) {
//            totalInventory = totalInventory + inv.get(i) + " ";
//        }
//
//        return totalInventory;
//    }
}
