package Presentation;

import Business.AdventureManager;
import Business.CharacterManager;
import Business.Entity.Character;
import Business.Entity.Monster;
import Business.PlayManager;
import Presentation.Views.MenuView;

import java.io.IOException;
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

    private void executeOption(int option) throws IOException {
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
            case 5:
                break;
            default:
                menu.showMessage("Incorrect option");
                break;
        }
    }

    public void run() throws IOException {
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

    private void createCharacters() throws IOException {
        menu.showMessage("\nTavern keeper: “Oh, so you are new to this land.”\n“What’s your name?”");
        String name = menu.askForString("\n-> Enter your name: ");
        Boolean contains = menu.checkSpecialChar(name);
        if(!contains){
            String newName = cm.changeName(name);
            Boolean exists =  cm.characterExists(name);
            //Comprobem que no exixteixi el personatge.
            if(!exists){
                menu.showMessage("\nTavern keeper: “Hello, "+ newName +", be welcome.”");
                menu.showMessage("“And now, if I may break the fourth wall, who is your Player?”");
                String playerName = menu.askForString("\n-> Enter the player’s name: ");
                menu.showMessage("\nTavern keeper: “I see, I see...”");
                menu.showMessage("“Now, are you an experienced adventurer?”");
                int level = menu.askNumberInARange("\n-> Enter the character’s level [1..10]: ", 1, 10);
                menu.showMessage("\nTavern keeper: “Oh, so you are level "+ level +"!”");
                menu.showMessage("“Great, let me get a closer look at you...”");
                int bodyA = cm.getRandom();
                int bodyB = cm.getRandom();
                int mindA = cm.getRandom();
                int mindB = cm.getRandom();
                int spiritA = cm.getRandom();
                int spiritB = cm.getRandom();
                int body = cm.getStatistic(bodyA, bodyB);
                int mind = cm.getStatistic(mindA, mindB);
                int spirit = cm.getStatistic(spiritA, spiritB);
                menu.showMessage("\nGenerating your stats...");
                menu.showMessage("\nBody:   You rolled "+ body +" ("+ bodyA +" and "+ bodyB +").\n" +
                        "Mind:   You rolled  "+ mind +" ("+ mindA +" and "+ mindB +").\n" +
                        "Spirit: You rolled "+ spirit +" ("+ spiritA +" and "+ spiritB +").\n");
                int bodySt = cm.generateStatistics(body);
                int mindSt = cm.generateStatistics(mind);
                int spiritSt = cm.generateStatistics(spirit);
                menu.showMessage("\nYour stats are:\n" +
                        "  - Body: "+ bodySt +"\n" +
                        "  - Mind: "+ mindSt +"\n" +
                        "  - Spirit: "+ spiritSt +"");
                //Afegeix al Json el personatge amb info.
                menu.showMessage("\nThe new character "+name+" has been created.");
                //Es fa be el add, ho comentem per no acomular proves al fitxer
                //cm.addCharacter(newName, playerName, level, body, mind, spirit, "Adventure");
            }else{
                menu.showMessage("\nCharacter already exists");
            }
        }

    }

    private void listCharacters() throws IOException {
        menu.showMessage("\nTavern keeper: “Lads! They want to see you!”\n“Who piques your interest?”");
        String player = menu.askForString("\n-> Enter the name of the Player to filter: ");
        ArrayList<String> names = cm.filterCharacters(player);
        // Comprobamos si el jugador tiene personajes.
        if(names.size() != 0){
            menu.showList(names);
            int index = menu.askNumberInARangeOneTime("\nWho would you like to meet [0.." + names.size() +"]: ", 1, names.size());
            if(names.size() >= index){
                String nameCharacter = names.get(index-1);
                //agafar tota la informacio a partir del nom
                ArrayList<String> information= cm.getInformation(nameCharacter);
                menu.showMessage("\nTavern keeper: “Hey " + nameCharacter+ "get here; the boss wants to see you!”\n");     //Change Jinx per nom i elements per return
                //busca personatje per nom (DAO o manager)
                menu.showInformationCharacter(cm.getInformation(nameCharacter));
                Boolean delate = false;
                do{
                    menu.showMessage("\n[Enter name to delete, or press enter to cancel]");
                    String nameDel = menu.askForString("Do you want to delete " + nameCharacter + "? ");      //Change name
                    if (nameDel.equals(nameCharacter)){
                        cm.removeCharacter(nameDel);
                        //KIDDO ns si es el nom del ususari o que coll es
                        menu.showMessage("\nI'm sorry kiddo, but you have to leave.");
                        menu.showMessage("\nCharacter " + nameCharacter + "left the Guild.");
                        delate = true;
                    }else if(nameDel.isEmpty()){
                        delate = true;
                    }else{
                        menu.showMessage("\nERROR: Character not found, try again.");
                    }
                }while(!delate);
            }
        }else{
            menu.showMessage("\nERROR: Characters not found");
        }

    }

    private void createAdventure(){
        menu.showMessage("\nTavern keeper: “Planning an adventure? Good luck with that!”\n");
        String newAdventure = menu.askForString("-> Name your adventure: ");
        Boolean check = am.existeixAventura(newAdventure);
        if(!check) {
            menu.showMessage("\nTavern keeper: “You plan to undertake "+newAdventure+", really?”\n" +
                    "“How long will that take?”\n");
            int numFights = menu.askNumberInARange("-> How many encounters do you want [1..4]:", 1, 4);
            menu.showMessage("Tavern keeper: “"+ numFights +" encounters? That is too much for me...”");
            // Per cada combat de l'aventura demanem info.
            for(int i=0; i<numFights; i++) {
                menu.showMessage("\n\n* Encounter " + (i+1) + " / " + numFights + "\n" +
                        "* Monsters in encounter");

                //LLegir monstres aventura o si esta empty mostro empty
                menu.showMessage("\n1. Add monster\n" +
                        "2. Remove monster\n" +
                        "3. Continue");
                int option = menu.askNumberInARange("\n-> Enter an option [1..3]: ", 1,3);

                switch (option){
                    case 1:
                        // 1. Afegir monstres
                        //Mostra tots els monstres possibles (fitxer)
                        ArrayList<String> monstNames =  am.getAllMonstersName();
                        ArrayList<String> monstTypes =  am.getAllMonstersType();
                        menu.showMonstersList(monstNames, monstTypes);


                        int indexAdd = menu.askNumberInARange("\n-> Choose a monster to add [1.."+monstNames.size()+"]: ", 1, monstNames.size());   //Posar num max de llista.
                        int amount = menu.askForInteger("-> How many NOM MONSTRE (s) do you want to add:"); //Afegir nom monstre
                        //Afegir monstre/s
                        // 1. Add monster (del combat concret, arrayList de monstres de dins de fight, dins del tipo de monstre altre classe).
                        // A aventuraManager fer addMonster, i que faci actualitza num monstres amb el nom, a classe Fight.
                        // creo funcio Show monsters i li paso ArrayList? o faig bucle aqui???
                        break;
                    case 2:
                        // 2. Remove monster
                        int indexDel = menu.askForInteger("-> Which monster do you want to delete: ");
                        // amb l'index busca el monstre i elimina -> advManager -> monst dins de fight.
                        menu.showMessage("9 Nazgul were removed from the encounter."); //change name i num de monstres

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
    }

    private void startAdventure(){
        menu.showMessage("Tavern keeper: “So, you are looking to go on an adventure?”");
        menu.showMessage("“Where do you fancy going?”\n\nAvailable adventures:");
        //List adventures (adventure manager-> retorna llista) li paso a menu.showList.
    }

    private void stopProgram(){
    }
}
