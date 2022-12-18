package Business.Entity;

public class Monster {

    private String name;
    private String challenge;

    private int experience;
    private int hitPoints;
    private int initiative;
    private String damageDice;
    private String damageType;


    public Monster(String name, String challenge, int experience, int hitPoints, int initiative, String damageDice, String damageType) {
        this.name = name;
        this.challenge = challenge;
        this.experience = experience;
        this.hitPoints = hitPoints;
        this.initiative = initiative;
        this.damageDice = damageDice;
        this.damageType = damageType;
    }

    public String getNameMonster() {
        return name;
    }

    public String getChallenge() {
        return challenge;
    }
}
