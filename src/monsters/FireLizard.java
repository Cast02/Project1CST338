package monsters;

import utilities.Dice;

public class FireLizard extends Monster{
    private final int DEFENSE_MIN = 1;
    private final int DEFENSE_MAX = 8;
    private final int ATTACK_MIN = 8;
    private final int ATTACK_MAX = 16;

    public FireLizard(String name) {
        super(name, ElementalType.FIRE);
        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
    }

    @Override
    public void setDefensePoints(int defensePoints) {
        defensePoints = Dice.roll(DEFENSE_MIN,DEFENSE_MAX);
    }

    @Override
    public void setAttackPoints() {
        attackPoints = Dice.roll(ATTACK_MIN,ATTACK_MAX);
    }

}
