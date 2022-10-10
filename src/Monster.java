
public abstract class Monster {

    private int defenseMin = 1;
    private int defenseMax = 10;
    private int attackmin = 1;
    private int attackMax = 10;
    private static double MAX_HP = 20.0;
    private String name = "";
    private String phrase = "";
    private double healthPoints = MAX_HP; //need to add key
    private int attackPoints = 10; //need to add key
    private int defensePoints = 10;
    private boolean fainted = false;
    private List<ElementType> elements = new ArrayList<>();

    public Monster(String name, List<ElementType> elements) {
        this.name = name;
        this.elements = elements;
        setPhrase(Monster);
    }

    public void setPhrase(Monster monster){

    }

   
    protected enum ElementalType {
        ELECTRIC,
        FIRE,
        GRASS,
        WATER,
    }

}
