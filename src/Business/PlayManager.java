package Business;

import Business.Entity.Adventure;
import Business.Entity.Party;
import Business.Entity.Character;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class PlayManager {
    private AdventuresJsonDAO advJsonDAO;
    private CharactersJsonDAO charJsonDAO;
    private DiceRoller dice;

    public PlayManager(AdventuresJsonDAO advJsonDAO, CharactersJsonDAO charJsonDAO, DiceRoller dice) {
        this.advJsonDAO = advJsonDAO;
        this.charJsonDAO = charJsonDAO;
        this.dice = dice;
    }

    public ArrayList<String> getAllAdventureNames(){
        return advJsonDAO.getAllAventNamesOfFile();
    }
    public ArrayList<String> getAllCharacterNames(Party party){
        ArrayList<Character> characters = party.getCharacters();
        ArrayList<String> names = new ArrayList<>();
        for(int i=0; i<characters.size(); i++){
            names.add(characters.get(i).getName());
        }
        return names;
    }
    public ArrayList<Character> createPartyList(ArrayList<String> names){
        ArrayList<Character> characters = new ArrayList<>();
        for(int i=0; i<names.size(); i++){
            Character character = charJsonDAO.findInfCharacter(names.get(i));
            int level = calculateCharLevel(character);
            String nameClass = character.getCharacterType();
            switch (nameClass){
                case "Adventurer":
                    character.setLivePoints(level*(10+character.getBody()));
                    character.setInitiative(character.getSpirit() + dice.rollDice(1, 12));
                    break;
            }
            characters.add(character);
        }
        return characters;
    }
    public int calculateCharLevel(Character character){
        int level =0;
        if(character.getXp()>=0 && character.getXp()<100){
            level = 1;
        }else if(character.getXp()>=100 && character.getXp()<200){
            level = 2;
        }else if(character.getXp()>=200 && character.getXp()<300){
            level = 3;
        }else if(character.getXp()>=300 && character.getXp()<400){
            level = 4;
        }else if(character.getXp()>=400 && character.getXp()<500){
            level = 5;
        }else if(character.getXp()>=500 && character.getXp()<600){
            level = 6;
        }else if(character.getXp()>=600 && character.getXp()<700){
            level = 7;
        }else if(character.getXp()>=700 && character.getXp()<800){
            level = 8;
        }else if(character.getXp()>=800 && character.getXp()<900){
            level = 9;
        }else if(character.getXp()>900){
            level = 10;
        }


            return level;
    }

    public Boolean charPartyExists(ArrayList<String> names, String newName){
        for(int i=0; i<names.size(); i++){
            if(names.get(i).equals(newName)){
                return true;
            }
        }
        return false;
    }
    public int getAdvFightSize(Party party){
        return party.getAdventure().getFightList().size();
    }
    public ArrayList<String> getMonstNames(Adventure adventure, int numFight){
        ArrayList<String> names = new ArrayList<>();
        for(int i=0; i<adventure.getFightList().get(numFight).getMonsters().size(); i++){
            String name = adventure.getFightList().get(numFight).getMonsters().get(i).getMonster().getName();
            names.add(name);
        }
        return names;
    }
    public ArrayList<Integer> getMonstAmount(Adventure adventure, int numFight){
        ArrayList<Integer> total = new ArrayList<>();
        for(int i=0; i<adventure.getFightList().get(numFight).getMonsters().size(); i++){
            int amount = adventure.getFightList().get(numFight).getMonsters().get(i).getNum();
            total.add(amount);
        }
        return total;
    }
    ////// COMPROBAAAARRRRRR
    public ArrayList<Character> preparationPhase(ArrayList<Character> party, int numChar){
        switch (party.get(numChar).getCharacterType()){
            case "Adventurer":
                party.get(numChar).setSpirit(party.get(numChar).getSpirit()+1);
                break;
        }
        return party;
    }

    public Boolean checkConcience (Character character){
        if (character.getLivePoints() < 1){
            return false;
        }
        return true;
    }

    public ArrayList<Character> modifyLivePoints (Character character, ArrayList<Character> party){
        for (int i = 0; i < party.size(); i ++){
            if(party.get(i).getName().equals(character.getName())){
                party.get(i).setLivePoints(0);
            }
        }
        return party;
    }

    public ArrayList<Monster> allMonstIniciative(Adventure adventure, int numFight){
        ArrayList<Monster> monsters = new ArrayList<>();
        int count=0;
        for(int i=0; i<adventure.getFightList().get(numFight).getMonsters().size(); i++){

            for(int j=0; j<adventure.getFightList().get(numFight).getMonsters().get(i).getNum(); j++){
                Monster monster = new Monster(adventure.getFightList().get(numFight).getMonsters().get(i).getMonster());
                monsters.add(count, monster);
                int num = adventure.getFightList().get(numFight).getMonsters().get(i).getMonster().getInitiative();
                monsters.get(count).setInitiative(num + dice.rollDice(12, 1));
                count++;
            }
        }

        return monsters;
    }

    public ArrayList<Character> sortParty(ArrayList<Character> party) {
        party.sort(new CharactersComparator());
        return party;
    }


    ArrayList<Adventure> sortMonstAdventure(ArrayList<Adventure> adventure){
        return adventure;

    }

    public String getNameByIndex( int i, ArrayList<Character> characters){
        for (int j = 0; j < characters.size(); j++) {
            if(characters.get(j).getName().equals(characters.get(i).getName())){
                return characters.get(j).getName();
            }

        }
        return null;

    }
    public int getLivePointsByIndex( int i, ArrayList<Character> characters){
        for (int j = 0; j < characters.size(); j++) {
            if(characters.get(j).getLivePoints() == characters.get(i).getLivePoints()){
                return characters.get(j).getLivePoints();
            }

        }
        return 0;

    }
    public int getMaxPointsByIndex( int i, ArrayList<Character> characters){
        for (int j = 0; j < characters.size(); j++) {
            if(characters.get(j).getMaxLivePoints() == characters.get(i).getMaxLivePoints()){
                return characters.get(j).getMaxLivePoints();
            }

        }
        return 0;

    }
    public int battlePhase(ArrayList<Character> party, int numChar){
        int damage=0;
       Character character = searchCharacter(party, name);
        switch (character.getCharacterType()){
            case "Adventurer":
                //COMPROVACIONS FALTA SEGONS EL NUMERO QUE HA SORTIT DE DICE
                damage = dice.rollDice(1, 6)+party.get(numChar).getBody();
                break;
        }
        return damage;
    }

    public Character searchCharacter(ArrayList<Character> party, String name){
        Character character = new Character();
        for(int i=0; i<party.size(); i++){
            if(name.equals(party.get(i).getName())){
                character = party.get(i);
            }
        }
        return character;
    }
    public Boolean monstOrChar(String name, ArrayList<Character> characers){
        for(int i=0; i<characers.size(); i++){
            if(name.equals(characers.get(i).getName())){
                return true;
            }
        }
        return false;
    }
    public int checkAttackDice(){
        int random = dice.rollDice(1, 10);
        if(random <=2){
            return 0;
        }else if(random < 10){
            return 1;
        }else{
            return 3;
        }
    }

    public String getAttackCharName(ArrayList<Monster> monsters, int index){
        return monsters.get(index).getName();
    }

    public int getMonstIndex(ArrayList<Monster> monsters){
        int num_ant = 100, index;
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getHitPoints() < num_ant && monsters.get(i).getHitPoints() > 0) {
                num_ant = monsters.get(i).getHitPoints();
                index = i;
            }
        }

            return index;
    }


    public Boolean checkConcienceMonst(ArrayList<Monster> monsters, String name) {

        for (int i = 0; i < monsters.size(); i++) {
            if(monsters.get(i).getHitPoints() < 1 && monsters.get(i).getName().equals(name)){
               return true;
            }
        }
        return false;
    }


    public ArrayList<Monster> applyDamageInMonst(int damage, ArrayList<Monster> monsters, int indexMonst) {
        monsters.get(indexMonst).setHitPoints(monsters.get(indexMonst).getHitPoints()-damage);
        if (monsters.get(indexMonst).getHitPoints() < 0){
            monsters.get(indexMonst).setHitPoints(0);
        }
        return monsters;
    }
    public String getAttackMonstName(ArrayList<Character> characters){
        int random;
        String name;
        do{
            random = dice.rollDice(characters.size(), 1);
            name =  characters.get(random -1).getName();
        }while (characters.get(random -1).getLivePoints() <= 0);

        return name;
    }
    public int battlePhaseMonst(ArrayList<Monster> monsters, int numOption, String name) {
        String damageChr = null;
        for (int i = 0; i < monsters.size(); i++) {
            if (name.equals(monsters.get(i).getName())) {
                damageChr = monsters.get(i).getDamageDice();
            }
        }

        assert damageChr != null;

        int num_rice = Integer.parseInt(damageChr.substring(1));
        int damage = dice.rollDice(num_rice, 1 );

        if (numOption == 2){
            damage = damage * 2;
        }
        return damage;

    }
    public String getDamageType(ArrayList<Monster> monsters, String name){
        String type = null;
        for (int i = 0; i < monsters.size(); i++) {
            if (name.equals(monsters.get(i).getName())) {
                type = monsters.get(i).getDamageType();
            }
        }
        return type.toLowerCase();
    }
    public ArrayList<Character> applyDamageInChar(int damage, ArrayList<Character> characters, String name){
        for (int i = 0; i < characters.size(); i++) {
            if (name.equals(characters.get(i).getName())) {
                characters.get(i).setLivePoints(characters.get(i).getLivePoints()-damage);
                if(characters.get(i).getLivePoints()<1){
                    characters.get(i).setLivePoints(0);
                }
            }
        }
        return characters;
    }

    public boolean checkConcienceChar (String name, ArrayList<Character> characters){
        for (int i = 0; i < characters.size(); i++) {
            if (name.equals(characters.get(i).getName())) {
                if(characters.get(i).getLivePoints() < 1){
                    return true;
                }
            }
        }
        return false;
    }
    public int unconsciousDoNotPlay(int k, ArrayList<String> names,  ArrayList<Character> characters){
            for (int j = 0; j < characters.size() && k < names.size(); j++){
                if(names.get(k).equals(characters.get(j).getName())){
                    if (characters.get(j).getLivePoints() == 0){
                        k++;
                        j=0;
                    }
                }
            }
        System.out.println(" VALOR DE K = "  + k);

        return k;
    }
    public int getIndexToRemoveMonst(ArrayList<String> names, int index, ArrayList<Monster> monsters, ArrayList<Integer> init){
        int count=0;
        for (int i = 0; i < names.size(); i++) {
            if (monsters.get(index).getName().equals(names.get(i)) && init.get(i) == monsters.get(index).getInitiative()){
                count =i;
            }
        }
        return count;
    }

}