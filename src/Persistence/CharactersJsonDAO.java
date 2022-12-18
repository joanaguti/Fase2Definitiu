package Persistence;

import Business.Entity.Character;
import Business.Entity.Monster;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CharactersJsonDAO {

    private final Gson gson;

    public CharactersJsonDAO() {
        this.gson = new Gson();
    }


    /*
    public ArrayList<Character> readFile(){
        ArrayList <Character> characters = new ArrayList<Character>();

        try{
            FileReader fr = new FileReader("Files/characters.json");
            // No podem llegir de cop.

            }
        }catch(FileNotFoundException e){
            // No s'ha pogut obrir el fitxer.
            System.out.println("ERROR FILE OPENING");
        }

        return characters;
    }*/

    public ArrayList<Character> carregarEdicions() {
        ArrayList<Character> characters = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Files/characters.json");
            JsonElement element = JsonParser.parseReader(fr);
            JsonArray array = element.getAsJsonArray();
            Gson g = new Gson();
            for (int i = 0; i < array.size(); i++) {
                Character character = new Character();
                JsonElement pos = array.get(i);
                JsonObject aux = pos.getAsJsonObject();
                character.setCharacterName(aux.get("name").getAsString());
                character.setNamePlayer(aux.get("player").getAsString());
                character.setXp(aux.get("xp").getAsInt());
                character.setBody(aux.get("body").getAsInt());
                character.setMind(aux.get("mind").getAsInt());
                character.setSpirit(aux.get("spirit").getAsInt());
                character.setCharacterType(aux.get("class").getAsString());
                characters.add(character);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening");
        }
        return characters;
        }
        public Boolean findCharacter(String name){
            ArrayList<Character> characters = carregarEdicions();
            for (int i = 0; i < characters.size(); i++) {
                String output = characters.get(i).getCharacterName();
                if(name.equals(output)){
                    return true;
                }
            }
            return false;
        }

}
