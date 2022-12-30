package Persistence;

import Business.Entity.*;
import Business.Entity.Character;
import com.google.gson.Gson;
import com.google.gson.*;

import java.io.*;
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

    public ArrayList<String> getAllNameMonstersOfOneFight(String name, int numFight){
        System.out.println("INICI get all name monsters");
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Adventure> adventures = readAdventures();
        for (int i = 0; i < adventures.size(); i++) {
            if(name.equals(adventures.get(i).getAdventureName())){
                ArrayList<Fight> fights = adventures.get(i).getFightList();
                ArrayList<MonstPlus> monstersPlus = fights.get(numFight).getMonsters();
                for(int k=0; k<monstersPlus.size(); k++){
                    Monster monsters = monstersPlus.get(k).getMonster();
                    names.add(monsters.getNameMonster());
                }
            }
        }
        return names;
    }
    public void PROVAJOANA(Adventure adventure) throws IOException {
        ArrayList<Adventure> adventures = readAdventures();
        adventures.add(adventure);
        // escriu
        FileWriter fw = new FileWriter("Files/adventures.json");
        gson.toJson(adventures, fw);
        fw.close();
    }

    public void writeFileOneAdv(Adventure adventure)throws IOException {
        ArrayList<Adventure> adventures = readAdventures();
        adventures.add(adventure);
        // escriu nou characters
        FileWriter fw = new FileWriter("Files/adventures.json");
        gson.toJson(adventures, fw);
        fw.close();
    }

    public Boolean findAdventure(String name){
        ArrayList<Adventure> adventures = readAdventures();
        for (int i = 0; i < adventures.size(); i++) {
            String output = adventures.get(i).getAdventureName();
            if(name.equals(output)){
                return true;
            }
        }
        return false;
    }

}
