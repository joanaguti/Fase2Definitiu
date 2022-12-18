package Presentation;

import Business.AdventureManager;
import Business.CharacterManager;
import Business.Dice;
import Business.Entity.Character;
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
    private Dice dice;


    public MenuController(MenuView menu, CharacterManager cm, AdventureManager am, PlayManager pm, Dice dice) {
        this.menu = menu;
        this.cm = cm;
        this.am = am;
        this.pm = pm;
        this.dice = dice;
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
                //createAdventure();
                break;
            case 4:
                startAdventure();
                break;
            case 5:
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
        menu.showMessage("\nTavern keeper: “Oh, so you are new to this land.”\n“What’s your name?”");
        String name = menu.askForString("\n-> Enter your name: ");
        Boolean contains = menu.checkSpecialChar(name);// CONTROLAR ESPAIS. SI S'ACCEPTEN ACCENTS TAMBE!
        if(!contains){
            //Modifica nom a manager (Upper i Lower exemple jOAnA = Joana)
            Boolean exists =  cm.characterExists(name);
            //Comprobem que no exixteixi el personatge.
            if(!exists){
                menu.showMessage("\nTavern keeper: “Hello, "+ name +", be welcome.”");
                menu.showMessage("“And now, if I may break the fourth wall, who is your Player?”");
                String playerName = menu.askForString("\n-> Enter the player’s name: ");
                menu.showMessage("\nTavern keeper: “I see, I see...”");
                menu.showMessage("“Now, are you an experienced adventurer?”");
                int level = menu.askNumberInARange("\n-> Enter the character’s level [1..10]: ", 1, 10);
                menu.showMessage("\nTavern keeper: “Oh, so you are level "+ level +"!”");
                menu.showMessage("“Great, let me get a closer look at you...”");
                int bodyA = dice.rollDice(6, 1);
                int bodyB = dice.rollDice(6, 1);
                int mindA = dice.rollDice(6, 1);
                int mindB = dice.rollDice(6, 1);
                int spiritA = dice.rollDice(6, 1);
                int spiritB = dice.rollDice(6, 1);
                int body = bodyA + bodyB;
                int mind = mindA + mindB;
                int spirit = spiritA + spiritB;
                menu.showMessage("\nGenerating your stats...");
                menu.showMessage("\nBody:   You rolled "+ body +" ("+ bodyA +" and "+ bodyB +").\n" +
                        "Mind:   You rolled  "+ mind +" ("+ mindA +" and "+ mindB +").\n" +
                        "Spirit: You rolled "+ spirit +" ("+ spiritA +" and "+ spiritB +").\n");
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
            }else{
                menu.showMessage("\nCharacter already exists");
            }
        }

    }

    private void listCharacters(){
        menu.showMessage("\nTavern keeper: “Lads! They want to see you!”\n“Who piques your interest?”");
        String player = menu.askForString("\n-> Enter the name of the Player to filter: ");
        ArrayList<String> names = cm.filterCharacters(player);
        menu.showList(names);
        //Llist characters menu.showList();
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
/*
    private void createAdventure(){
        menu.showMessage("Tavern keeper: “Planning an adventure? Good luck with that!”\n");
        String newAdventure = menu.askForString("-> Name your adventure: ");

        Boolean check = am.existeixAventura(newAdventure);
        if(check) {
            menu.showMessage("Tavern keeper: “You plan to undertake Strolling through Mordor, really?”\n" +
                    "“How long will that take?”\n");
            int numFights = menu.askNumberInARange("-> How many encounters do you want [1..4]:", 1, 4);
            menu.showMessage("Tavern keeper: “"+ numFights +" encounters? That is too much for me...”");
            // Per cada combat de l'aventura demanem info.
            for(int i=0; i<numFights; i++) {
                menu.showMessage("* Encounter " + i + " / " + numFights + "\n" +
                        "* Monsters in encounter");

                //LLegir monstres aventura o si esta empty mostro empty
                menu.showMessage("1. Add monster\n" +
                        "2. Remove monster\n" +
                        "3. Continue");
                int option = menu.askNumberInARange("-> Enter an option [1..3]: ", 1,3);

                switch (option){
                    case 1:
                        //Mostra tots els monstres possibles (fitxer)
                        ArrayList<String> monstNames =  am.getAllMonstersName();
                        ArrayList<String> monstTypes =  am.getAllMonstersType();
                        for(int j=0; j<monstNames.size();j++){
                            String name = monstNames.get(i);
                            String type = monstTypes.get(i);
                            menu.showMessage(j+1+". "+name+"("+"type"+")");
                        }

                        int indexAdd = menu.askNumberInARange("-> Choose a monster to add [1..7]: ", 1, 12);   //Posar num max de llista.
                        int amount = menu.askForInteger("-> How many NOM MONSTRE (s) do you want to add:"); //Afegir nom monstre
                        //Afegir monstre/s
                        // 1. Add monster (del combat concret, arrayList de monstres de dins de fight, dins del tipo de monstre altre classe).
                        // A aventuraManager fer addMonster, i que faci actualitza num monstres amb el nom, a classe Fight.
                        // creo funcio Show monsters i li paso ArrayList? o faig bucle aqui???
                        break;
                    case 2:

                        int indexDel = menu.askForInteger("-> Which monster do you want to delete: ");
                        // amb l'index busca el monstre i elimina -> advManager -> monst dins de fight.
                        menu.showMessage("9 Nazgul were removed from the encounter."); //change name i num de monstres
                        // 2. Remove monster
                        break;
                    case 3:
                        // 3. Continue
                        break;
                }
            }
            menu.showMessage("Tavern keeper: “Great plan lad! I hope you won’t die!”\n"+
                    "The new adventure "+newAdventure+" has been created.");
        }else{
            menu.showMessage("Adventure already exists.\n");
        }
    }*/

    private void startAdventure(){
        menu.showMessage("Tavern keeper: “So, you are looking to go on an adventure?”");
        menu.showMessage("“Where do you fancy going?”\n\nAvailable adventures:");
        //List adventures (adventure manager-> retorna llista) li paso a menu.showList.
    }

    private void stopProgram(){

    }
}
