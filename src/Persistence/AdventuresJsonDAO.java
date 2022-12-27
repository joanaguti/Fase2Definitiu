package Persistence;

import Business.Entity.*;
import Business.Entity.Character;
import com.google.gson.Gson;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventuresJsonDAO {

    private final Gson gson;

    public AdventuresJsonDAO() {
        this.gson = new Gson();
    }

    public ArrayList<Adventure> readAdventures() {
        ArrayList<Adventure> adventures = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Files/adventures.json");
            Adventure[] listall = gson.fromJson(gson.newJsonReader(fr), Adventure[].class );
            adventures = new ArrayList<Adventure>(List.of(listall));

        } catch (FileNotFoundException e) {
            System.out.println("Error opening");
        }
        return adventures;
    }

    public ArrayList<String> getAllMonsters(String name){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Adventure> adventures = readAdventures();
        for (int i = 0; i < adventures.size(); i++) {
            if(name.equals(adventures.get(i).getAdventureName())){
                ArrayList<Fight> fights = adventures.get(i).getFightList();
                for(int j = 0; i<fights.size(); j++){
                    ArrayList<MonstPlus> monstersPlus = fights.get(j).getMonsters();
                    for(int k=0; k<monstersPlus.size(); k++){
                        Monster monsters = monstersPlus.get(i).getMonster();
                        names.add(monsters.getNameMonster());
                    }
                }
            }
        }
        return names;
    }

    public void writeFileOneAdv(Adventure adventure)throws IOException {
        ArrayList<Adventure> adventures = readAdventures();
        adventures.add(adventure);
        // escriu nou characters
        FileWriter fw = new FileWriter("Files/Characters.json");
        gson.toJson(adventures, fw);
        fw.close();
    }

}
