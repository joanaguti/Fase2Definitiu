package Presentation;

import Business.AdventureManager;
import Business.CharacterManager;
import Business.PlayManager;
import Presentation.Views.MenuView;

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
        menu.showMessage("Tavern keeper: “Oh, so you are new to this land.”\n“What’s your name?”");
        String name = menu.askForString("-> Enter your name: ");
        Boolean contains = menu.checkSpecialChar(name);// Comproba que no contingui caracters especials
        if(!contains){
            //Modifica nom a manager (Upper i Lower exemple jOAnA = Joana)
            //Demana demes parametres
        }

    }

    private void listCharacters(){
        menu.showMessage("Tavern keeper: “Lads! They want to see you!”\n“Who piques your interest?”");
        String player = menu.askForString("\n-> Enter the name of the Player to filter: ");
        //List characters (characters manager-> retorna llista) li paso a menu.showList.
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
}
