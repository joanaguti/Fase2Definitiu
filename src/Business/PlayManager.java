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
    public Adventure allMonstIniciate(Adventure adventure, int numFight){
        int random = dice.rollDice(12, 1);
        for(int i=0; i<adventure.getFightList().get(numFight).getMonsters().size(); i++){
            int num = adventure.getFightList().get(numFight).getMonsters().get(i).getMonster().getInitiative();
            adventure.getFightList().get(numFight).getMonsters().get(i).getMonster().setInitiative(num + random);
        }

        return adventure;
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
        switch (party.get(numChar).getCharacterType()){
            case "Adventurer":
                //COMPROVACIONS FALTA SEGONS EL NUMERO QUE HA SORTIT DE DICE
                damage = dice.rollDice(1, 6)+party.get(numChar).getBody();
                break;
        }
        return damage;
    }
    public void breakPhase(){

    }
}
