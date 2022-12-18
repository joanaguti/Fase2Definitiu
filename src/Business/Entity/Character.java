package Business.Entity;

public class Character {

    private String characterName;
    private String namePlayer;
    private int xp;
    //private int characterLever;
    //private int characterPoints;
    private int body;
    private int mind;
    private int spirit;
    private String characterType;

    public Character() {
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setCharacterType(String characterType) {
        this.characterType = characterType;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
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
