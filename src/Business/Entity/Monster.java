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

    public void setName(String name) {
        this.name = name;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getInitiative() {
        return initiative;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public String getDamageType() {
        return damageType;
    }
}
