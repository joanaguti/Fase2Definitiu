package Business;

import Business.Entity.Character;

import java.util.Comparator;

public class CharactersComparator implements Comparator<Character> {



    public int compare(Character p1, Character p2) {
        return Integer.compare(p2.getInitiative(), p1.getInitiative());
    }

}






