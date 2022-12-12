package Persistence;

import Business.Entity.Monster;
import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MonstersJsonDAO {
    private final Gson gson;

    public MonstersJsonDAO() {
        this.gson = new Gson();
    }
/*
    public ArrayList<Monster> readFile(){
        FileReader fr = new FileReader("Persistence/Monsters.json");
        Monster[] listall = gson.fromJson(gson.newJsonReader(fr), Monster[].class );
        ArrayList <Monster> monsters = new ArrayList<Monster>(List.of(listall));
        return monsters;
    }*/
}
