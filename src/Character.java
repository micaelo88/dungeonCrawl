/**
 * Class Character
 *
 * This class is part of the "Dungeon" application.
 * "Dungeon" is a very simple, text based adventure game.
 *
 * This class is used to create characters in the game, both
 * the Player character and any NPCs that the player can
 * interact with.
 *
 * @author Christopher Mitchell
 * @version 2020.08.29
 */

public class Character {
    private String description;
    public Inventory inv;

    /**
     * Create the player character or NPCs with a description. Sets up their inventory.
     * @param description The NPC's description
     */
    public Character(String description){
        this.description = description;
        inv = new Inventory();
    }

    /**
     * Get the character description
     * @return the character's description String
     */
    public String getDescription()
    {
        return description;
    }
}