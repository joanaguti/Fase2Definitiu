package Business;

import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

public class CharacterManager {
    private CharactersJsonDAO charJsonDAO;

    public CharacterManager(CharactersJsonDAO charJsonDAO) {
        this.charJsonDAO = charJsonDAO;
    }
}
