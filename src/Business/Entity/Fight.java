package Business.Entity;

import java.util.ArrayList;

public class Fight {
    private String fightName;
    private int numMonsters;
    private ArrayList monsters;

    public Fight(String fightName, int numMonsters, ArrayList monsters) {
        this.fightName = fightName;
        this.numMonsters = numMonsters;
        this.monsters = monsters;
    }
}
