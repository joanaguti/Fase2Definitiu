package Business.Entity;

public class Character {

    private String characterName;
    private String namePlayer;
    private String characterType;
    private int characterLever;
    private int characterPoints;
    private int body;
    private int mind;
    private int spirit;

    public Character(String characterName, String namePlayer, String characterType, int characterLever, int characterPoints, int body, int mind, int spirit) {
        this.characterName = characterName;
        this.namePlayer = namePlayer;
        this.characterType = characterType;
        this.characterLever = characterLever;
        this.characterPoints = characterPoints;
        this.body = body;
        this.mind = mind;
        this.spirit = spirit;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getNamePlayer() {
        return namePlayer;
    }
}
