package Persistence;

import Business.Entity.Monster;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CharactersJsonDAO {

    private final Gson gson;

    public CharactersJsonDAO() {
        this.gson = new Gson();
    }


    public ArrayList<Character> readFile(){
        ArrayList <Character> characters = new ArrayList<Character>();
        return characters;
    }

}
