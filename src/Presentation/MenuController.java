package Presentation;

import Business.AdventureManager;
import Business.CharacterManager;
import Business.Entity.*;
import Business.Entity.Character;
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

        if(am.checkAccess()){
            do {
                // Comprobar fitxer es pot llegir   Data was successfully loaded.   fer funcio al DAO
                if(cm.activateOp()){
                    menu.showMenu();
                }else{
                    menu.showMenuWithoutAdv();
                }
                option = menu.askForInteger("\nYour answer: ");
                executeOption(option);
            } while(option != 5);
            menu.showMessage("\nTavern keeper: “Are you leaving already? See you soon, adventurer.”");
        }else{
            menu.showMessage("Error: The monsters.json file can’t be accessed.");
        }
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
                int xp = cm.setExpPoints(level);
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
                cm.addCharacter(newName, playerName, xp, body, mind, spirit, "Adventurer");
            }else{
                menu.showMessage("\nCharacter already exists");
            }
        }

    }

    private void listCharacters() throws IOException {
        menu.showMessage("\nTavern keeper: “Lads! They want to see you!”\n“Who piques your interest?”");
        String player = menu.askForString("\n-> Enter the name of the Player to filter: ");
        //Case insensitive.

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

    private void createAdventure() throws IOException {
        menu.showMessage("\nTavern keeper: “Planning an adventure? Good luck with that!”\n");
        String newAdventure = menu.askForString("-> Name your adventure: ");
        Boolean check = am.adventureExists(newAdventure);

        Monster monsterAllInfo = new Monster("Gothmog", "Boss", 148,87 , 3, "d20", "Magical");
        Monster monsterAllInfo2 = new Monster("Cris", "challege2", 2,3 , 3, "dice2", "type2");

        MonstPlus monPlus = new MonstPlus(monsterAllInfo, 4);
        MonstPlus mons2Plus = new MonstPlus(monsterAllInfo2, 5);
        ArrayList<MonstPlus> mm = new ArrayList<>();
        mm.add(monPlus);
        mm.add(mons2Plus);

        Fight fight = new Fight(mm);
        ArrayList<Fight> fights = new ArrayList<>();
        fights.add(fight);
        Adventure adv = new Adventure("Joana Gutierrez", fights);
        am.PROVAJOANA2(adv);


        if(!check) {
            menu.showMessage("\nTavern keeper: “You plan to undertake "+newAdventure+", really?”\n" +
                    "“How long will that take?”\n");
            int numFights = menu.askNumberInARangeThreeTimes("-> How many encounters do you want [1..4]: ", 1, 4);
            if(numFights != 0){
                menu.showMessage("Tavern keeper: “"+ numFights +" encounters? That is too much for me...”\n");
                // Per cada combat de l'aventura demanem info.
                for(int i=0; i<numFights; i++) {
                    Boolean next = false;
                    while(!next){
                        menu.showMessage("\n* Encounter " + (i+1) + " / " + numFights + "\n" +
                                "* Monsters in encounter");
                        ArrayList<String> names = am.getAdventureMonsters(newAdventure);
                        menu.showMonstAdvList(names);

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
                                int indexAdd = menu.askNumberInARange("\n-> Choose a monster to add [1.."+monstNames.size()+"]: ", 1, monstNames.size());
                                Monster monster = am.getMonsterInformation(indexAdd - 1);
                                String monstName = am.getOneMonstName(monster);
                                int amount = menu.askForInteger("-> How many "+monstName+"(s) do you want to add: "); //Afegir nom monstre
                                // check boss exists -> true ya existe boss no add
                                //Posar addMonster al manager perque al controler hi hagi menys logica
                                if(!am.checkBossExists(i, newAdventure)){
                                    if(am.checkIsNotABoss(monster)){
                                        am.addMonsterInAdv(newAdventure, monster, amount, i, numFights);
                                        checkAdd = true;
                                    }else if(am.checkBossAmount(amount)){
                                        am.addMonsterInAdv(newAdventure, monster, amount, i, numFights);
                                        checkAdd = true;
                                    }else{
                                        menu.showMessage("\nERROR: You can add only one boss.");
                                    }
                                }else{
                                    menu.showMessage("\nERROR: There is one Boss in this fight.");
                                }
                                break;
                            case 2:
                                // 2. Remove monster
                                int indexDel = menu.askForInteger("-> Which monster do you want to delete: ");
                                ArrayList<String> names2 = am.getAllMonstersName();
                                String nameMonster = names2.get(indexDel-1);
                                //COMPORBACIO
                                System.out.println("NOM MONSTRE: " + nameMonster);
                                am.removeAdventureMonster(nameMonster, newAdventure);
                                int numMonster = am.getNumMonster(nameMonster, newAdventure);
                                menu.showMessage(numMonster + " " + nameMonster + " were removed from the encounter.");

                                break;
                            case 3:
                                // 3. Continue
                                if(!checkAdd){             //Si faig 2 continues si deixa sense add-> MAL
                                    menu.showMessage("\nERROR: Need one monster in fight.");
                                }else{
                                    next = true;
                                }
                                break;
                        }
                    }

                }
                menu.showMessage("Tavern keeper: “Great plan lad! I hope you won’t die!”\n"+
                        "The new adventure "+newAdventure+" has been created.");
            }

        }else{
            menu.showMessage("\nAdventure already exists.");
        }
    }

    private void startAdventure(){
        menu.showMessage("Tavern keeper: “So, you are looking to go on an adventure?”");
        menu.showMessage("“Where do you fancy going?”\n\nAvailable adventures:");
        menu.showStandardList(pm.getAllAdventureNames());
        int index = menu.askNumberInARange("\n-> Choose an adventure: ", 1, am.getAllAdvSize());

        menu.showMessage("\nTavern keeper: “"+pm.getAllAdventureNames().get(index-1)+" it is!”\n" +
                "“And how many people shall join you?”");
        int charNum = menu.askNumberInARange("\n-> Choose a number of characters [3..5]: ", 3, 5);
        menu.showMessage("\nTavern keeper: “Great, "+charNum+" it is.”\n" +
                "“Who among these lads shall join you?”");
        ArrayList<String> partyCharsNames = new ArrayList<>();
        for(int i=0; i<charNum; i++) {
            menu.showMessage("\n\nYour party (" + (i + 1) + " / " + charNum + "):");
            menu.showMessage("------------------------------");
            menu.showCharsParty(partyCharsNames, charNum);
            menu.showMessage("------------------------------");
            menu.showMessage("Available characters:");
            menu.showStandardList(cm.getCharNames());
            int selectChar = menu.askNumberInARange("\n-> Choose character " + (i + 1) + " in your party: ", 1, cm.getCharNames().size());
            if (!pm.charPartyExists(partyCharsNames, cm.getCharNames().get(selectChar - 1))) {
                partyCharsNames.add(i, cm.getCharNames().get(selectChar - 1));
            } else {
                menu.showMessage("ERROR: Character already exists. ");
                i--;
            }
        }
            Adventure adventure = am.getAdventure(pm.getAllAdventureNames().get(index-1)); //am pm funcio get adventure.
            ArrayList<Character> party = pm.createPartyList(partyCharsNames);
            menu.showMessage("\nTavern keeper: “Great, good luck on your adventure lads!”\n");
            menu.showMessage("The “"+pm.getAllAdventureNames().get(index-1)+"” will start soon...");
            boolean flag = false;
            for(int j=0; j<am.getMaxFights(adventure) && !flag; j++){

                menu.showMessage("\n------------------------------");
                menu.showMessage("Starting Encounter "+(j+1)+":");

                menu.showMonstFightList(pm.getMonstNames(adventure, j), pm.getMonstAmount(adventure, j));
                menu.showMessage("------------------------------");
                menu.showMessage("\n\n------------------------------");
                menu.showMessage("*** Preparation stage ***");
                menu.showMessage("------------------------------");
                for(int i=0; i<charNum; i++) {
                    if(pm.checkConcience(party.get(i))){
                        party = pm.preparationPhase(party, i);
                        switch (cm.getOneCharType(party.get(i))){
                            case "Adventurer":
                                menu.showMessage(partyCharsNames.get(i)+" uses Self-Motivated. Their Spirit increases in +1.");
                        }
                    }else{
                       party =  pm.modifyLivePoints(party.get(i), party);
                        System.out.println("NO ES");
                    }
                }
                menu.showMessage("\nRolling initiative...");
                party = pm.allMonstIniciate(party, j);
                menu.showInitiativeList(pm.getAllMonstCharsFightNames(party, j), pm.getAllMonstCharsFightInit(party, j));
                menu.showMessage("\n\n------------------------------");
                menu.showMessage("*** Combat stage ***");
                menu.showMessage("------------------------------");
                int KO = 0;
                do{
                    KO ++;
                    menu.showMessage("Round"+(KO+1));
                    menu.showMessage("Party:");
                    for(int i=0; i<party.size(); i++){
                        menu.showMessage("\t-"+ pm.getNameByIndex(i, party)+ "\t\t"+ pm.getLivePointsByIndex(i, party)+" / "+pm.getMaxPointsByIndex(i, party)+" hit points");
                    }
                    totalExp = 0;
                    for(int k=0; k<names.size() && monsters.size() != 0 && !flag; k++){
                        k = pm.unconsciousDoNotPlay(k, names, party);
                        if(k < names.size()){
                            //calculem el valor del dau --> 2-10 atac normal i si es 10 atac critic
                            // 0 --> fallit (mes petit que 2)
                            // 1--> atac nomral (dau 2-10)
                            // 2 --> atac doble (dau 10)
                            int numOption = pm.checkAttackDice();
                            if(numOption == 1 || numOption == 2){
                                if(pm.monstOrChar(names.get(k), party)){  //es un char
                                    //en comptes de name atack retornem index i dps amb el aquell index trobem el nom
                                    int indexMonst = pm.getMonstIndex(monsters);
                                    String nameAttack = pm.getAttackCharName(monsters, indexMonst);
                                    int damage = pm.battlePhaseChar(party, names.get(k), numOption);
                                    Character character = pm.searchCharacter(party, names.get(k));
                                    //controlar conciencia, si un monstre es incocnient s'enva fora de la batalla
                                    monsters = pm.applyDamageInMonst(damage, monsters, indexMonst);
                                    switch (cm.getOneCharType(character)){
                                        case "Adventurer":
                                            menu.showMessage(names.get(k) + " attacks "+ nameAttack + " with Sword slash");
                                            if (numOption == 2){
                                                menu.showMessage("Critical hit and deals "+ damage +" physical damage\n");

                                        }else{
                                            menu.showMessage("Hits and deals "+ damage +" physical damage\n");
                                        }
                                        //check concience < 0 --> printf + borrar (monsters, names, init)
                                        if (pm.checkConcienceMonst(monsters, nameAttack)){
                                            int num = pm.getIndexToRemoveMonst(names, indexMonst, monsters, init);
                                            names.remove(num);
                                            init.remove(num);
                                            monsters.remove(indexMonst);
                                            menu.showMessage(nameAttack +" dies.");
                                        }
                                        break;
                                }

                                } else{  //es un monst
                                    String nameAttack = pm.getAttackMonstName(party);
                                    int damage = pm.battlePhaseMonst(monsters, numOption, names.get(k));
                                    party = pm.applyDamageInChar(damage, party, nameAttack);
                                    menu.showMessage(names.get(k) + " attacks "+ nameAttack + ".");
                                    if (numOption== 2){
                                        menu.showMessage("Critical hit and deals "+ damage +" "+pm.getDamageType(monsters, names.get(k)) +" damage\n");
                                    } else{
                                        menu.showMessage("Hits and deals "+ damage +" "+pm.getDamageType(monsters, names.get(k)) +" damage\n");
                                    }

                                    if (pm.checkConcienceChar(nameAttack, party)){
                                        menu.showMessage(nameAttack +" falls unconscious.");

                                    }

                                }
                            } else{
                                //distingir monst o char
                                System.out.println("ATAC FALLIT " + names.get(k));
                            }
                        }

                        //controlar si es el final de batalla i depen de com passar a descans curt o seguir amb el
                        //mateix combat
                        flag = pm.allCharactersInconcient(party);
                        if (flag){
                            menu.showMessage("Tavern keeper: “Lad, wake up. Yes, your party fell unconscious.” “Don’t worry, you are safe back at the Tavern.”");
                        }
                    }


                    /*
                    aqui haurem de mirar qui va primer per ordre d'iniciativa:
                        - si es mosntre atacara de forma aleatoria a un personatge ignirant inconcients
                                el mal que li fara al personatge serà segons el tipus de dau (classe mosntre)
                        - si es un personatge es el que ataca --> atacara el que tingui menys punts de vida
                                el mal que fara sera mal = d6 + cos tenint en compte (3.1)

                     */

                }
                if(!flag){
                    menu.showMessage("\n\n------------------------------");
                    menu.showMessage("*** Short reset stage ***");
                    menu.showMessage("------------------------------");
                    //printar
                    for (int i = 0; i < party.size(); i ++) {
                        menu.showMessage(cm.getOneCharName(party.get(i)) + " gains "+ totalExp+" xp."); //treure \n
                        int levelUp = pm.getIncrementedLevel(party.get(i), totalExp);
                        party = pm.setLevel(party, party.get(i), totalExp);
                        if(levelUp>-1){
                            menu.showMessage(cm.getOneCharName(party.get(i)) + " levels up. They are now lvl "+ levelUp +"!");
                        }
                    }
                    menu.showMessage("\n");

                    for (int i = 0; i < party.size(); i ++) {
                        if (pm.checkConcience(party.get(i))){
                            //falta comprovar que estigui be
                            int numHeals = pm.calculateNumHeals(party.get(i));
                            party = pm.setHealsChar(party.get(i), numHeals, party);
                            switch (party.get(i).getCharacterType()){
                                case "Adventurer":
                                    menu.showMessage(cm.getOneCharName(party.get(i)) + " uses " + "Bandage time. Heals " + numHeals + " hit points.");
                                    break;
                            }
                        }else{
                            menu.showMessage(cm.getOneCharName(party.get(i)) + " is unconscious");
                        }
                    }
                }
                //Numero de torns?
                // Si no morts o inconscients seguim ordre iniciativa
                // Aventurers fan Sword slash
                //Monstre ataca un person aleatori
            }
            if(!flag){
                menu.showMessage("Congratulations, your party completed “"+ pm.getAllAdventureNames().get(index-1)+ "”");
            }
    }

    private void stopProgram(){
    }
}
