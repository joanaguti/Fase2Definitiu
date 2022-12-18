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
        ArrayList<Monster> monsters = monstJsonDAO.readFile();
        ArrayList<String> names = new ArrayList<>();
        // Per retornar nomes names.
        for(int i = 0; i<monsters.size(); i++){
                Monster monster = monsters.get(i);
                names.add(monster.getNameMonster());
            }
        return names;
    }
    public ArrayList<String> getAllMonstersType(){
        ArrayList<Monster> monsters = monstJsonDAO.readFile();
        ArrayList<String> types = new ArrayList<>();
        // Per retornar nomes names.
        for(int i = 0; i<monsters.size(); i++){
            Monster monster = monsters.get(i);
            types.add(monster.getChallenge());
        }
        return types;
    }
}
