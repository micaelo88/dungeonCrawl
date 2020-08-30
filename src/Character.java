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
    public int healthMax;
    public int healthCurrent;
    public int strength;
    public int defense;

    /**
     * Create the player character or NPCs with a description. Sets up their inventory.
     * @param description The character's description
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

    /**
     * Set a character's stats. This will come into play
     * with combat.
     * @param health The character's total health points
     * @param strength How strong a character is, adds to attack
     * @param defense How well a player is defended
     */
    public void setStats(int health, int strength, int defense)
    {
        healthMax = health;
        healthCurrent = health;
        this.strength = strength;
        this.defense = defense;
    }

    /**
     * Get's character's health
     * @return The character's health stat
     */
    public int getHealth()
    {
        return healthCurrent;
    }

    /**
     * Get character's max health
     * @return The character's max health stat
     */
    public int getHealthMax()
    {
        return healthMax;
    }

    /**
     * Get character's strength
     * @return The character's strength stat
     */
    public int getStrength()
    {
        return strength;
    }

    /**
     * Get character's defense
     * @return The character's defense stat
     */
    public int getDefense()
    {
        return defense;
    }

    /**
     * Change character's current health
     * @param healthChange the amount the health changes
     */
    public void changeHealth(int healthChange)
    {
        healthCurrent += healthChange;
        if(healthCurrent > healthMax){
            healthCurrent = healthMax;
        }
    }

    /**
     * Change character's strength
     * @param strengthChange the amount the strength changes
     */
    public void changeStrength(int strengthChange)
    {
        strength += strengthChange;
    }

    /**
     * Change character's defense
     * @param defenseChange the amount the defense changes
     */
    public void changeDefense(int defenseChange)
    {
        defense += defenseChange;
    }
}