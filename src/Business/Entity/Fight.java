package Business.Entity;

import java.util.ArrayList;

public class Fight {
    private String fightName;
    private ArrayList<MonstPlus> monsters;

    public Fight(String fightName, ArrayList<MonstPlus> monsters) {
        this.fightName = fightName;
        this.monsters = monsters;
    }

    public String getFightName() {
        return fightName;
    }

    public ArrayList<MonstPlus> getMonsters() {
        return monsters;
    }
}
