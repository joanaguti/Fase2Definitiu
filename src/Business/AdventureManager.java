package Business;

import Business.Entity.Adventure;
import Business.Entity.Character;
import Business.Entity.Monster;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

import java.io.IOException;
import java.util.ArrayList;

public class AdventureManager {
    private AdventuresJsonDAO advJsonDAO;
    private MonstersJsonDAO monstJsonDAO;


    public AdventureManager(AdventuresJsonDAO advJsonDAO, MonstersJsonDAO monstJsonDAO) {
        this.advJsonDAO = advJsonDAO;
        this.monstJsonDAO = monstJsonDAO;
    }

    public ArrayList<String> getAllMonstersName(){
        return monstJsonDAO.getAllNames();
    }
    public ArrayList<String> getAllMonstersType(){
        return monstJsonDAO.getAllTypes();
    }
    //NO ESTA FETA!!!!
    public Boolean adventureExists(String newAdventure){
        return advJsonDAO.findAdventure(newAdventure);
    }
    public ArrayList<String> getAdventureMonsters(String name){
        return advJsonDAO.getAllMonsters(name);
    }

    public void addOneAdventure(Adventure adventure) throws IOException {
        advJsonDAO.writeFileOneAdv(adventure);
    }
    public void PROVAJOANA2(Adventure adventure) throws IOException {
        advJsonDAO.PROVAJOANA(adventure);
    }
}
