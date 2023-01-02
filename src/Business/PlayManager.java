package Business;

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
}
