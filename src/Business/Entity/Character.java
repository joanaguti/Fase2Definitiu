package Business.Entity;

import com.google.gson.annotations.SerializedName;

public class Character {

    private String name;
    private String player;
    private int xp;
    //private int characterLever;
    //private int characterPoints;
    private int body;
    private int mind;
    private int spirit;
    @SerializedName("class")
    private String characterType;

    public Character() {
    }

    public Character(String name, String player, int xp, int body, int mind, int spirit, String characterType) {
        this.name = name;
        this.player = player;
        this.xp = xp;
        this.body = body;
        this.mind = mind;
        this.spirit = spirit;
        this.characterType = characterType;
    }

    public String getName() {
        return name;
    }

    public String getPlayer() {
        return player;
    }

    public int getXp() {
        return xp;
    }

    public int getBody() {
        return body;
    }

    public int getMind() {
        return mind;
    }

    public int getSpirit() {
        return spirit;
    }

    public String getCharacterType() {
        return characterType;
    }
}
