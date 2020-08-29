import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Dungeon" application.
 * "Dungeon" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * The "Room" can also create an item.
 *
 * @author  Michael Kölling, David J. Barnes, and Christopher Mitchell
 * @version 2020.08.25
 */
public class Room
{
    private String description;
    private HashMap<String, Room> exits;    // stores exits of this room.
    private HashMap<String, String> restricted;     //stores exits that require items/puzzles
    public Inventory items;
    public Character npc = null;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String npcDescription)
    {
        this.description = description;
        exits = new HashMap<>();
        restricted = new HashMap<>();
        items = new Inventory();
        if(npcDescription == null){
            return;
        }
        else{
            npc = new Character(npcDescription);
        }
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room to which the exit leads.
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Define an exit from this room that requires an item.
     * @param direction The direction of the exit.
     * @param item The item needed to use the exit.
     */
    public void setRestrictedExits(String direction, String item)
    {
        restricted.put(direction, item);
    }

    /**
     * Define an item that starts in this room.
     * @param description The item's description.
     */
    public void setItem(String description)
    {
        items.addItem(description);
    }

    /**
     * Check if the restricted HashMap is empty
     * @param direction The exit's direction
     * @return True if there is an item in the HashMap
     */
    public Boolean getRestrictedDirection(String direction)
    {
        return restricted.containsKey(direction);
    }

    /**
     * Return the item needed to advance to the next room in a
     * restricted exit
     * @param direction The exit's direction
     * @return The item description needed
     */
    public String getRestrictedItem(String direction)
    {
        return restricted.get(direction);
    }

    /**
     * Removes a restriction from the restricted HashMap
     * @param direction The exit's direction
     */
    public void removeRestriction(String direction)
    {
        restricted.remove(direction);
    }

    /**
     * Return the room that is reached if we go from this room in
     * direction "direction." If there is no room in that direction,
     * return null.
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * @return The description of the room
     * (the one that was defined in the constructor).
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, of the form:
     *      You are in the kitchen.
     *      Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String longDescription = "You are " + description + "\n";
        if(npc !=null){
            longDescription = longDescription + "There is a " + npc.getDescription() + " in here with you.\n";
        }
        longDescription = longDescription + getExitString();
        if(!items.checkInventory()) {
            longDescription += "\nVisible items: " + items.printInventory();
        }
        return longDescription;
    }
}
