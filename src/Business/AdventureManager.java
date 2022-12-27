package Business;

import Business.Entity.Monster;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

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
    public Boolean existeixAventura(String newAdventure){
        //Si no exixteix
        //DAO retorni boolean si el troba
        return false;
    }
    public ArrayList<String> getAdventureMonsters(String name){
        return advJsonDAO.getAllMonsters(name);
    }
    public void createPersistenceSource(){
        advJsonDAO.createFile();
    }
}
