package Business;

import Business.Entity.Adventure;
import Business.Entity.Party;
import Business.Entity.Character;
import Persistence.AdventuresJsonDAO;

import java.util.ArrayList;

public class PlayManager {
    private AdventuresJsonDAO advJsonDAO;

    public PlayManager(AdventuresJsonDAO advJsonDAO) {
        this.advJsonDAO = advJsonDAO;
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
    public void createParty(ArrayList<Character> characters, Adventure adventure){

    }
}
