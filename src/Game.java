/**
 *  This class is the main class of the "Dungeon" application.
 *  "Dungeon" is a very simple, text based adventure game.  Users
 *  can walk around some dungeon rooms and solve two basic puzzles. That's all.
 *  It should really be extended to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser, creates items, and starts the game.
 *  It also evaluates and executes the commands that the parser returns.
 *
 * @author  Michael Kölling, David J. Barnes, and Kit Mitchell
 * @version 2020.08.15
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room start, entrance, armory, riddle, reward, pitfall, monster, chains, trial, key, curse;

        // create the rooms
        start = new Room("in a dark room with one door. You don't\nknow where you are or how you got here.");
        entrance = new Room("in the dungeon entrance. When you\nfirst entered, the way north shut and locked\nbehind you. But you see a keyhole in the\ndoor. Could there be a key somewhere?");
        armory = new Room("in an ancient armory. You see a sword\nand a shield in fairly good condition. Everything\nelse is too rusty to use.");
        riddle = new Room("in a room with a riddle on the wall.\nIt says:\n\n'To get your reward\n'You must choose a door.\n'You should travel a ways\n'Towards the end of your days.\n'But if you choose wrong\n'That end won't be long!'");
        reward = new Room("in a room with a treasure chest. You\nopen it to find something nice and shiny, but\nyou have no idea what it is.");
        pitfall = new Room("now falling into a bottomless pit.\nType 'quit' to escape and then start a new\ngame.");
        monster = new Room("faced with a scary monster who wants\nto eat you.");
        chains = new Room("in a room with chains hanging from\nthe wall. Old skeletons are still locked in\nsome of the chains. You shudder to think\nabout being stuck here so long.");
        trial = new Room("faced with two strange creatures. One\nstand before a Westward door and the other stands\nbefore a Southward door. They tell you that one\ndoor will help you escape and the other door\nwill leave you cursed. They also tell you that\none of them tells the truth and the other lies.\nYou ask the South door creature which door\nthe West door creature would say will help\nyou escape. It says the West door creature would\ntell you to go South. Which door do you choose?\nOr do you go North and avoid the whole thing?");
        key = new Room("in a room with a key lying on a table."); //this will later let you get back through the enterance's North door and escape
        curse = new Room("now cursed for always and eternity.\nType 'quit' to escape and then start a new\ngame.");

        // initialise room exits
        start.setExits("south", entrance);
        entrance.setExits("north", start); // in later iterations there will be a mechanic to prevent going north without a key
        entrance.setExits("east", monster);
        entrance.setExits("west", armory);
        armory.setExits("east", entrance);
        armory.setExits("south", riddle);
        riddle.setExits("north", armory);
        riddle.setExits("east", pitfall);
        riddle.setExits("west", reward);
        reward.setExits("east", riddle);
        monster.setExits("east", chains);
        monster.setExits("west", entrance);
        chains.setExits("south", trial);
        chains.setExits("west", monster);
        trial.setExits("north", chains);
        trial.setExits("south", curse);
        trial.setExits("west", key);
        key.setExits("east", trial);

        // initialise room items
        armory.setItem("sword");
        armory.setItem("shield");
        reward.setItem("shiny thing");
        key.setItem("key");

        currentRoom = start;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Dungeon!");
        System.out.println("Dungeon is a new, puzzle solving adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You don't");
        System.out.println("know how you got here. Can you find a");
        System.out.println("way to escape the Dungeon?");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * Print the directions available as exits
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * "Look" was entered. Get a description of the room you are in.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * "Eat" was entered. For now it just prints, but will later
     * actually let you "eat".
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry\nany more.");
    }
}
