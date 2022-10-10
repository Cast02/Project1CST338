import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    private List<Monster> monsters = null;

    private Monster electricRat = null;
    private Monster weirdTurtle = null;
    private Monster flowerDino = null;
    private Monster fireLizard = null;

    private String electricRatName = "Electric Rat";
    private String weirdTurtleName = "Weird Turtle Thing";
    private String flowerDinoName = "Flower Dino";
    private String fireLizardName = "Clearly the best";

    @BeforeEach
    void setUp() {
        electricRat = new ElectricRat(electricRatName);
        weirdTurtle = new WeirdTurtle(weirdTurtleName);
        flowerDino = new FlowerDino(flowerDinoName);
        fireLizard = new FireLizard(fireLizardName);

        monsters = new ArrayList<>();

        monsters.add(electricRat);
        monsters.add(weirdTurtle);
        monsters.add(flowerDino);
        monsters.add(fireLizard);

    }

    @AfterEach
    void tearDown() {
        electricRat = weirdTurtle = flowerDino = fireLizard = null;
        System.out.println("\n\n");
    }

    @Test
    void constructor_Test() {

        System.out.println("CONSTRUCTOR TEST");

        WeirdTurtle conTestMonster = null;
        assertNull(conTestMonster);
        conTestMonster = new WeirdTurtle(weirdTurtleName);
        assertNotNull(conTestMonster);
    }

    @Test
    void roll_Test() {

        System.out.println("ROLL TEST");

        int min = 1;
        int max = 10;
        int roll = Util.roll(min, max);
        assertTrue(roll >= min);
        assertTrue(roll <= max);
    }

    @Test
    void getAttackPoints_test() {
        System.out.println("ATTACK POINT TEST");

        //attack points should NEVER be negative.
        int test = -2;
        for (Monster m : monsters) {
            System.out.println("Testing: " + m);
            assertTrue(m.getAttackPoints() > 0);
        }

    }

    @Test
    void setAttackPoints_test() {
        //AP should never be this high.
        int attackPoints = 42;
        System.out.println("Testing setAttackPoints");
        for (Monster m : monsters) {
            System.out.println("Testing : " + m);
            assertNotEquals(attackPoints, m.getAttackPoints());
            m.setAttackPoints(attackPoints);
            assertEquals(attackPoints, m.getAttackPoints());

            m.setAttackPoints();
            assertTrue(m.getAttackPoints() <= m.getAttackMax());
        }
    }

    @Test
    void attack() {
        System.out.println("ATTACK TEST");

        for (Monster m : monsters) {
            for (Monster o : monsters) {
                System.out.println(m.attack(o));
            }
        }
        System.out.println(electricRat.attack(weirdTurtle));
    }

    @Test
    void toString_test() {
        System.out.println("TESTING To Strings!");
        for (Monster m : monsters) {
            System.out.println(m);
        }

        electricRat.setType(Monster.ElementalType.GRASS);

        System.out.println(electricRat);

        while (!flowerDino.isFainted()) {
            fireLizard.attack(flowerDino);
        }
        System.out.println(flowerDino);

    }

    @Test
    void getElements() {
        System.out.println("TESTING GET ELEMENTS");
        List<Monster.ElementalType> testTypes = new ArrayList<>();
        testTypes.add(Monster.ElementalType.ELECTRIC);
        assertEquals(testTypes, electricRat.getElements());
        assertNotEquals(testTypes, weirdTurtle.getElements());
        testTypes.add(Monster.ElementalType.FIRE);
        assertNotEquals(testTypes, electricRat.getElements());

        System.out.println(electricRat);
    }

    @Test
    void setType() {

        System.out.println("TESTING SET TYPE");

        //Should already have electric set
        assertEquals(1, electricRat.setType(Monster.ElementalType.ELECTRIC));
        //should be able to add fire to electric
        assertEquals(0, electricRat.setType(Monster.ElementalType.FIRE));
        // can't have a water/electric type... in LDPocket anyway.
        assertEquals(-1, electricRat.setType(Monster.ElementalType.WATER));

        System.out.println(electricRat);
    }


}