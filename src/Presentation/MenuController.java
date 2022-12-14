package Presentation;

import Business.AdventureManager;
import Business.CharacterManager;
import Business.Entity.Monster;
import Business.PlayManager;
import Presentation.Views.MenuView;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {
    private MenuView menu;
    private CharacterManager cm;
    private AdventureManager am;
    private PlayManager pm;


    public MenuController(MenuView menu, CharacterManager cm, AdventureManager am, PlayManager pm) {
        this.menu = menu;
        this.cm = cm;
        this.am = am;
        this.pm = pm;
    }

    private void executeOption(int option) {
        switch (option) {
            case 1:
                createCharacters();
                break;
            case 2:
                listCharacters();
                break;
            case 3:
                createAdventure();
                break;
            case 4:
                startAdventure();
                break;
            default:
                menu.showMessage("Incorrect option");
                break;
        }
    }

    public void run() {
        int option;
        menu.showMainMessage();

        do {
            // Comprobar fitxer es pot llegir   Data was successfully loaded.   fer funcio al DAO
            menu.showMenu();

            option = menu.askForInteger("\nYour answer: ");
            executeOption(option);
        } while(option != 5);
        menu.showMessage("\nTavern keeper: “Are you leaving already? See you soon, adventurer.”");
    }

    private void createCharacters(){
        ArrayList<Monster> monsters =  am.getAllMonsters();

        menu.showMessage("Tavern keeper: “Oh, so you are new to this land.”\n“What’s your name?”");
        String name = menu.askForString("\n-> Enter your name: ");
        Boolean contains = menu.checkSpecialChar(name);// CONTROLAR ESPAIS. SI S'ACCEPTEN ACCENTS TAMBE!
        if(!contains){
            //Modifica nom a manager (Upper i Lower exemple jOAnA = Joana)
            // COMPROBA QUE NO EXISTEIXI EEL NOM, si existeix ERROR i menu principal fer if
            menu.showMessage("\nTavern keeper: “Hello, "+ name +", be welcome.”");
            menu.showMessage("“And now, if I may break the fourth wall, who is your Player?”");
            String playerName = menu.askForString("\n-> Enter the player’s name: ");
            menu.showMessage("\nTavern keeper: “I see, I see...”");
            menu.showMessage("“Now, are you an experienced adventurer?”");
            int level = menu.askNumberInARange("\n-> Enter the character’s level [1..10]: ", 1, 10);
            menu.showMessage("\nTavern keeper: “Oh, so you are level "+ level +"!”");
            menu.showMessage("“Great, let me get a closer look at you...”");
            int bodyA = rollDice(6, 1);
            int bodyB = rollDice(6, 1);
            int mindA = rollDice(6, 1);
            int mindB = rollDice(6, 1);
            int spiritA = rollDice(6, 1);
            int spiritB = rollDice(6, 1);
            int body = bodyA + bodyB;
            int mind = mindA + mindB;
            int spirit = spiritA + spiritB;
            menu.showMessage("\nGenerating your stats...");
            menu.showMessage("\nBody:   You rolled "+ body +" ("+ bodyA +" and "+ bodyB +").\n" +
                    "Mind:   You rolled  "+ mind +" ("+ mindA +" and "+ mindB +").\n" +
                    "Spirit: You rolled "+ spirit +" ("+ spiritA +" and "+ spiritB +").\n");
            //Convertir daus en estadistica
            int bodySt = cm.adjudicateStatistics(body);
            int mindSt = cm.adjudicateStatistics(mind);
            int spiritSt = cm.adjudicateStatistics(spirit);
            menu.showMessage("\nYour stats are:\n" +
                    "  - Body: "+ bodySt +"\n" +
                    "  - Mind: "+ mindSt +"\n" +
                    "  - Spirit: "+ spiritSt +"");
            //Afegeix al Json el personatge amb info.
            String classe = "Adventurer";       //Fer una classe d'aquest tipus de personatges??
            menu.showMessage("\nThe new character "+name+" has been created.");
        }

    }

    private void listCharacters(){
        menu.showMessage("Tavern keeper: “Lads! They want to see you!”\n“Who piques your interest?”");
        String player = menu.askForString("\n-> Enter the name of the Player to filter: ");
        // ArrayList<Character> characters = cm.filterCharacters(player);
        //Llist characters
        int index = menu.askNumberInARange("", 0, 12);      //Change maxim a num de elements.
        menu.showMessage("Tavern keeper: “Hey Jinx get here; the boss wants to see you!”");     //Change Jinx per nom i elements per return
        //busca personatje per nom (DAO o manager)
        menu.showMessage("* Name:   Jinx\n" +
                "* Player: IPlayLOLInClass\n" +
                "* Class:  Adventurer\n" +
                "* Level:  2\n" +
                "* XP:     132\n" +
                "* Body:   +1\n" +
                "* Mind:   -1\n" +
                "* Spirit: +3");
        menu.showMessage("[Enter name to delete, or press enter to cancel]");
        String nameDel = menu.askForString("Do you want to delete Jinx?");      //Change name
        // COntrolar error en el nom pag 4.
        // Busca pel nom i return false -> misatge derrror -> no l'intento esborrar
        // Boolean delate = cm.deleteCharacter(nameDel);
        /*if(delate){
            menu.showMessage("Tavern keeper: “I’m sorry kiddo, but you have to leave.”\n" +
                    "Character Jinx left the Guild.");                                          // Change Name
        }*/



    }

    private void createAdventure(){
        menu.showMessage("Tavern keeper: “Planning an adventure? Good luck with that!”\n");
        String newAdventure = menu.askForString("-> Name your adventure: ");

    }

    private void startAdventure(){
        menu.showMessage("Tavern keeper: “So, you are looking to go on an adventure?”");
        menu.showMessage("“Where do you fancy going?”\n\nAvailable adventures:");
        //List adventures (adventure manager-> retorna llista) li paso a menu.showList.
    }

    private void stopProgram(){

    }
    private int rollDice(int max, int min) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
