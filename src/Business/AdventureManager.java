package Business;

import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

public class AdventureManager {
    private AdventuresJsonDAO advJsonDAO;
    private MonstersJsonDAO monstJsonDAO;

    public AdventureManager(AdventuresJsonDAO advJsonDAO, MonstersJsonDAO monstJsonDAO) {
        this.advJsonDAO = advJsonDAO;
        this.monstJsonDAO = monstJsonDAO;
    }
}
