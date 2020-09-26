import java.util.ArrayList;

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
 * @version 2020.08.25
 */

public class Game
{
    private ArrayList <Room>map; // the map - an ArrayList of Rooms
    private Parser parser;
    private Room currentRoom;
    private Character player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
        player = new Character("You are wearing basic clothing and\nhave no armor.\n");
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        this.map = new ArrayList<Room>();

        // create the rooms
        map.add(new Room("start", "in a dark room with one door. You don't\nknow where you are or how you got here.", null));
        map.add(new Room("entrance", "in the dungeon entrance. When you first\nentered, the way north shut and locked " +
                "behind you.\nBut you see a keyhole in the door. Could there be\na key somewhere?", null));
        map.add(new Room("armory", "in an ancient armory. You see a sword and\na shield in fairly good condition. " +
                "Everything else\nis too rusty to use.", null));
        map.add(new Room("riddle", "in a room with a riddle on the wall. It says:\n\n'To get your reward\n'You must " +
                "choose a door.\n'You should travel a ways\n'Towards the end of your days.\n'But if you choose wrong\n" +
                "'That end won't be long!'", null));
        map.add(new Room("reward", "in a room with a treasure chest. You open\nit to find something nice and shiny, " +
                "but you have\nno idea what it is.", null));
        map.add(new Room("pitfall", "now falling into a bottomless pit.\n\nType 'quit' to escape and then start a new " +
                "game.", null));
        map.add(new Room("monster", "in a room filled with bones.", "scary monster"));
        map.add(new Room("chains", "in a room with chains hanging from the\nwall. Old skeletons are still locked in " +
                "some of\nthe chains. You shudder to think about being stuck\nhere so long.", null));
        map.add(new Room("trial", "faced with two strange creatures. One\nstand before a Westward door and the other " +
                "stands\nbefore a Southward door. They tell you that one\ndoor will help you escape and the other " +
                "door will\nleave you cursed. They also tell you that one of\nthem tells the truth and the other " +
                "lies. You ask\nthe South door creature which door the West door\ncreature would say will help you " +
                "escape. It says\nthe West door creature would tell you to go South.\nWhich door do you choose? Or " +
                "do you go North and\navoid the whole thing?", null));
        map.add(new Room("key", "in a room with a key lying on a table.", null));
        map.add(new Room("curse", "now cursed for always and eternity.\n\nType 'quit' to escape and then start a new " +
                "game.", null));

        // initialise room exits
        start.setExits("south", entrance);
        entrance.setExits("north", start);
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

        // initialise restricted exits
        entrance.setRestrictedExits("north", "key");

        // initialise room items
        armory.setItem("sword");
        armory.setItem("shield");
        reward.setItem("shiny");
        key.setItem("key");

        // initialise room character's items
        monster.npc.inv.addItem("potion");

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

        //TODO: set up the "take" and "use" commandWords
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
        else if (commandWord.equals("take")){
            takeItem(command);
        }
        else if (commandWord.equals("use")){
            System.out.println("Still under development.\n"); //TODO: work out how to use items.
        }
        else if (commandWord.equals("inventory")){
            System.out.println("Inventory: " + player.inv.printInventory() + "\n");
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
        System.out.println("");
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?\n");
            return;
        }

        String direction = command.getSecondWord();

        // Check for restricted exit
        if(currentRoom.getRestrictedDirection(direction)){
            String item = currentRoom.getRestrictedItem(direction);
            if(player.inv.getItem(item)){
                System.out.println("You use " + item + " to open the door.\n");
                currentRoom.removeRestriction(direction);
                player.inv.removeItem(item);
            }
            else {
                System.out.println("You cannot get through this door without a " + item + ".\n");
                return;
            }
        }

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!\n");
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
            System.out.println("Quit what?\n");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * "Look" was entered. Get a description of the room you are in.
     * Also see any NPCs and get a description of yourself.
     */
    private void look()
    {
        System.out.println(player.getDescription());
        System.out.println(currentRoom.getLongDescription() + "\n");
    }

    /**
     * "Eat" was entered. For now it just prints, but will later
     * actually let you "eat".
     */
    private void eat()
    {
        System.out.println("You have eaten now and you are not hungry\nany more.\n");
    }

    private void takeItem(Command command)
    {
        if(!command.hasSecondWord()){
            //If there's no second word, we don't know what to take
            System.out.println("Take what?\n");
            return;
        }

        String item = command.getSecondWord();

        if(!currentRoom.items.getItem(item)){
            System.out.println("You don't see that anywhere in this\nroom.\n");
        }
        else {
            player.inv.addItem(item);
            currentRoom.items.removeItem(item);
            System.out.println("You have taken the " + item + ".\n");
        }
    }
}
