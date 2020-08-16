
/**
 * Class Item - an item in an adventure game.
 *
 * This class is part of the "Dungeon" application.
 * "Dungeon" is a very simple, text based adventure game.
 *
 * An "Item" represents an item and its weight. An item begins in
 * a "Room" but is independent of the room
 *
 * @author Kit Mitchell
 * @version 2018.04.23
 */
public class Item
{
    private String description;
    private int weight;

    /**
     * Create an item described "description". Initially the item has
     * no weight to it. "description" is something like "sword" or "key".
     *
     * @param description The item's description
     */
    public Item(String description)
    {
        this.description = description;
    }

    /**
     * Define the weight of the item.
     * @param weight How much the item weighs in pounds.
     */
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    /**
     * @return The description of the item
     * (the one that was defined in the constructor)
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return The weight of the item
     * (the one that was defined in setWeight()
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * @return Item description and weight String
     */
    public String getDescriptionWeightString()
    {
        return "Item: " + description + ", Weight: " + Integer.toString(weight) + " lbs.";
    }

}
