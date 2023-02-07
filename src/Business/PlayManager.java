package Business;

import Business.Entity.Adventure;
import Business.Entity.Party;
import Business.Entity.Character;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

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
            // Punts vida maxims ->i actuals??
            int xp = character.getXp()*(10+character.getBody());
            character.setXp(xp);
            characters.add(character);
        }
        return characters;
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
    public Party preparationPhase(Party party, int numChar){
            party.getCharacters().get(numChar).selfMotivated();
        return party;
    }
    public Party allMonstIniciate(Party party, int numFight){
        int random = dice.rollDice(12, 1);
        for(int i=0; i<party.getAdventure().getFightList().get(numFight).getMonsters().size(); i++){
            int num = party.getAdventure().getFightList().get(numFight).getMonsters().get(i).getMonster().getInitiative();
            party.getAdventure().getFightList().get(numFight).getMonsters().get(i).getMonster().setInitiative(num + random);
        }

        return party;
    }
    public ArrayList<String> getAllMonstCharsFightNames(Party party, int numFight){
        ArrayList<String> names = new ArrayList<>();
        for(int i=0; i<party.getAdventure().getFightList().get(numFight).getMonsters().size(); i++){
            names.add(party.getAdventure().getFightList().get(numFight).getMonsters().get(i).getMonster().getName());
        }
        for(int j=0; j<party.getCharacters().size() ; j++){
            names.add(party.getCharacters().get(j).getName());
        }
        return names;
    }
    public ArrayList<Integer> getAllMonstCharsFightInit(Party party, int numFight){
        ArrayList<Integer> initiatives = new ArrayList<>();
        for(int i=0; i<party.getAdventure().getFightList().get(numFight).getMonsters().size(); i++){
            initiatives.add(party.getAdventure().getFightList().get(numFight).getMonsters().get(i).getMonster().getInitiative());
        }
        for(int j=0; j<party.getCharacters().size() ; j++){
            int initiative = dice.rollDice(12, 1) + party.getCharacters().get(j).getSpirit();
            initiatives.add(initiative);
        }
        return initiatives;
    }
    public void battlePhase(){

    }
    public void breakPhase(){

    }
}
