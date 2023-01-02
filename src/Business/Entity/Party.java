package Business.Entity;

import java.util.ArrayList;

public class Party {
    ArrayList<Character> characters;
    Adventure adventure;

    public Party(ArrayList<Character> characters, Adventure adventure) {
        this.characters = characters;
        this.adventure = adventure;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }
}

