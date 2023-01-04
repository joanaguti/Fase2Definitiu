package Business;

import Business.Entity.Adventure;
import Business.Entity.Party;
import Business.Entity.Character;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class PlayManager {
    private AdventuresJsonDAO advJsonDAO;
    private CharactersJsonDAO charJsonDAO;

    public PlayManager(AdventuresJsonDAO advJsonDAO, CharactersJsonDAO charJsonDAO) {
        this.advJsonDAO = advJsonDAO;
        this.charJsonDAO = charJsonDAO;
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
    public Party createParty(ArrayList<String> names, String advName){
        ArrayList<Character> characters = new ArrayList<>();
        for(int i=0; i<names.size(); i++){
            Character character = charJsonDAO.findInfCharacter(names.get(i));
            // Punts vida maxims ->i actuals??
            int xp = character.getXp()*(10+character.getBody());
            character.setXp(xp);
            characters.add(character);
        }
        Adventure adventure = advJsonDAO.getAdventure(advName);
        Party party = new Party(characters, adventure);
        System.out.println(characters.get(0).getPlayer());
        System.out.println(adventure.getAdventureName());
        return party;
    }

    public Boolean charPartyExists(ArrayList<String> names, String newName){
        for(int i=0; i<names.size(); i++){
            if(names.get(i).equals(newName)){
                return true;
            }
        }
        return false;
    }
    public void preparationPhase(){

    }
    public void battlePhase(){

    }
    public void breakPhase(){

    }
}
