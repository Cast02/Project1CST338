package monsters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Monster {

    private int defenseMin = 1;
    private int defenseMax = 10;
    private int attackMin = 1;
    private int attackMax = 10;

    private static double MAX_HP = 20.0;
    private static double SPEED = 10.0;

    private String name;
    private String phrase;
    private Double healthPoints = MAX_HP;
    protected int attackPoints = 10;
    protected int defensePoints = 10;
    private boolean fainted = false;
    private List<ElementalType> elements = new ArrayList<>();

    public Monster(String name, ElementalType element) {
        this.name = name;
        elements.add(element);
        Monster.setPhrase(this);
    }

    private static Monster setPhrase(Monster m) {

        String phrase = "No phrase for me!";

        if (m instanceof ElectricRat) {
            phrase = "'Lectric!";
        } else if (m instanceof WeirdTurtle) {
            phrase = "'Urtle!";
        } else if (m instanceof FlowerDino) {
            phrase = "Flowah!";
        } else if (m instanceof FireLizard) {
            phrase = "Deal with it.";
        }
        m.setPhrase(phrase);
        return m;
    }

    public static double getSPEED() {
        return SPEED;
    }

    public static void setSPEED(double SPEED) {
        Monster.SPEED = SPEED;
    }

    public double attack(Monster monster_being_attacked) {

        if (this.isFainted()) {
            System.out.println(this.name + " isn't conscious... it can't attack.");
            return 0.0;
        } else if (monster_being_attacked.isFainted()) {
            System.out.println(monster_being_attacked.getName() + " has fainted already. No need to attack.");
            return 0.0;
        }

        System.out.println(this.name + " is attacking " + monster_being_attacked.name);

        System.out.println(getPhrase());

        double attackPoints = calculateAttackPoints(roll(attackMin, attackMax), monster_being_attacked.getElements());

        System.out.println(this.name + " attacking with " + attackPoints + " attack points!");

        attackPoints = monster_being_attacked.takeDamage(attackPoints);

        if(this.equals(monster_being_attacked) && attackPoints > 0){
            System.out.println(this.name + " has hurt itself in the confusion.");
        }

        return attackPoints;
    }

    private double takeDamage(double attackPoints) {
        double defensePoints = calculateDefensePoints(roll(defenseMax, defenseMin));
        double attackValue = attackPoints - defensePoints;
        if (attackValue > 0) {
            System.out.println(this.name + " is hit for " + attackValue + " damage!");
            this.healthPoints -= attackValue;
        } else if (attackValue == 0) {
            System.out.println(this.name + " is nearly hit!");
        } else if (attackValue < (defensePoints / 2.0)) {
            System.out.println(this.name + " shrugs off the puny attack");
        }


        if (healthPoints <= 0) {
            System.out.println(this.name + " has faint-- passed out. It's passed out.");
            this.fainted = true;
        } else {
            System.out.println(this.name + " has " + healthPoints + "/" + MAX_HP + "HP remaining");
        }

        return attackValue;
    }

    protected double calculateDefensePoints(double roll) {

        if (roll < (defenseMax / 2.0) && roll % 2 == 0) {
            System.out.println(this.name + " finds courage in the desperate situation");
            roll += 1.0;
            roll *= 2.0;
        } else if (roll == defenseMin) {
            System.out.println(this.name + " is clearly not paying attention");
        }

        return roll;

    }

    public double calculateAttackPoints(double roll, List<ElementalType> enemyTypes) {

        double modifier = 1.0;

        System.out.println(this.name + " rolls a " + roll);

        for (ElementalType type : enemyTypes) {
            modifier *= attackModifier(type);
        }

        if (modifier >= 2.0) {
            System.out.println("It's su-- *cough* very effective!");
        }

        return modifier * roll;
    }

    protected double attackModifier(ElementalType defending) {
        double modifier = 1.0;
        switch (defending) {
            case ELECTRIC:
                if (elements.contains(ElementalType.ELECTRIC)) {
                    modifier = 0.5;
                }
                break;

            case FIRE:
                if (elements.contains(ElementalType.WATER)) {
                    modifier = 2.0;
                } else if (elements.contains(ElementalType.GRASS) ||
                        elements.contains(ElementalType.FIRE)) {
                    modifier = 0.5;
                }
                break;

            case GRASS:
                if (elements.contains(ElementalType.FIRE)) {
                    modifier = 2.0;
                } else if (elements.contains(ElementalType.ELECTRIC)
                        || elements.contains(ElementalType.GRASS)
                        || elements.contains(ElementalType.WATER)) {
                    modifier = 0.5;
                }
                break;

            case WATER:
                if (elements.contains(ElementalType.ELECTRIC)
                        || elements.contains(ElementalType.GRASS)) {
                    modifier = 2.0;
                } else if (elements.contains(ElementalType.FIRE) ||
                        elements.contains(ElementalType.WATER)) {
                    modifier = 0.5;
                }
                break;

            default:
                modifier = 1.0;
        }
        return modifier;
    }

    public String getPhrase() {
        return phrase + " " + phrase;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(this.name);
        out.append(" has ");
        if (!this.isFainted()) {
            out.append(" ");
            out.append(healthPoints);
            out.append("/");
            out.append(MAX_HP);
            out.append("hp. ");
        } else {
            out.append(" fainted");
        }
        out.append("Elemental type: ");
        for (ElementalType e : elements) {
            out.append(e);
            if (elements.size() > 1) {
                out.append(", ");
            }
        }
        return out.toString();
    }

    public boolean isFainted() {
        return fainted;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    public abstract void setDefensePoints(int defensePoints);

    public void setAttackPoints(int attackPoints){
        this.attackPoints = attackPoints;
    };

    public int setType(ElementalType type) {
        if (elements.contains(type)) {
            System.out.println(type + " already set!");
            return 1;
        } else if (attackModifier(type) > 1.0) {
            System.out.println("can't have conflicting types!");
            return -1;
        } else {
            System.out.println(this.name + " now has type " + type);
            elements.add(type);
            return 0;
        }
    }


    public String getName() {
        return name;
    }

    public Double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Double healthPoints) {
        this.healthPoints = healthPoints;
    }


    public int getDefensePoints() {
        return defensePoints;
    }


    public List<ElementalType> getElements() {
        return elements;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefenseMin() {
        return defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }

    public abstract void setAttackPoints();

    public static int roll(int min, int max) {
        Random random = new Random();
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        return (random.nextInt(max - Math.abs(min)) + 1) + min;
    }

    public static int roll(int max) {
        return roll(max, 1);
    }

    protected enum ElementalType {
        ELECTRIC,
        FIRE,
        GRASS,
        WATER,
    }

}
