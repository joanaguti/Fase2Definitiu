package Business;

import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;

public class CharacterManager {
    private CharactersJsonDAO charJsonDAO;

    public CharacterManager(CharactersJsonDAO charJsonDAO) {
        this.charJsonDAO = charJsonDAO;
    }
/*
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
    }*/
    public int adjudicateStatistics(int num){
        int newNumber =0;
        // 1 i 2???
        if(num == 2){
            newNumber = -1;
        }else if(num>=3 && num<=5){
            newNumber = 0;
        } if(num>=6 && num<=9){
            newNumber = 1;
        }else if(num>=10 && num<=11){
            newNumber = 2;
        }else{
            newNumber = 3;
        }
        return newNumber;
    }
}
