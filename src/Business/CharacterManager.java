package Business;

import Business.Entity.Character;
import Business.Entity.Monster;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

import java.util.ArrayList;

public class CharacterManager {
    private CharactersJsonDAO charJsonDAO;
    private Character character;

    public CharacterManager(CharactersJsonDAO charJsonDAO) {
        this.charJsonDAO = charJsonDAO;
        this.character = null;
    }
/*
    public String changeName(String name){
        String newName;
        char newLetter;
        boolean first = false;
        for(int i=0; i<name.length(); i++){
            if(!first){
                newLetter = newName.toUpperCase(name.charAt(i) == ' ');
                first = true;
            }else{
                newLetter = Character.toLowerCase(name.charAt(i));
            }
            // newName = newName + newLetter;       //Como concateno un char i una cadena.
        }

        return newName;
    }*/
    public int generateStatistics(int num){
        int newNumber;
        // 1 i 2???
        if(num == 2){
            newNumber = -1;
        }else if(num>=3 && num<=5){
            newNumber = 0;
        }else if(num>=6 && num<=9){
            newNumber = 1;
        }else if(num>=10 && num<=11){
            newNumber = 2;
        }else{
            newNumber = 3;
        }
        return newNumber;
    }
    public Boolean characterExists(String name){
        return charJsonDAO.findCharacter(name);
    }

    public ArrayList<String> filterCharacters(String player){
        return charJsonDAO.selectCharacters(player);
    }
}
