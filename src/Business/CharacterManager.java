package Business;

import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

public class CharacterManager {
    private CharactersJsonDAO charJsonDAO;

    public CharacterManager(CharactersJsonDAO charJsonDAO) {
        this.charJsonDAO = charJsonDAO;
    }

    public String changeName(String name){
        String newName;
        char newLetter;
        boolean first = false;
        for(int i=0; i<name.length(); i++){
            if(!first){
                newLetter = Character.toUpperCase(name.charAt(i));
                first = true;
            }else{
                newLetter = Character.toLowerCase(name.charAt(i));
            }
            // newName = newName + newLetter;       //Como concateno un char i una cadena.
        }

        return newName;
    }
}
