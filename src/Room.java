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
 * @version 2020.08.15
 */
public class Room
{
    private String description;
    private HashMap<String, Room> exits;    // stores exits of this room.
    private Inventory items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new Inventory();
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
     * Define an item that starts in this room.
     * @param description The item's description.
     */
    public void setItem(String description)
    {
        items.addItem(description);
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
        String longDescription = "You are " + description + "\n" + getExitString();
        if(!items.checkInventory()) {
            longDescription += "\n" + items.printInventory();
        }
        return longDescription;
    }
}
