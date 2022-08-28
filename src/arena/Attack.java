package arena;

import monsters.Monster;

import java.util.ArrayList;
import java.util.List;

public class Attack implements Action {
    private Monster attacking;
    private Monster being_attacked;


    public Attack(Monster attacking, Monster being_attacked) {
        this.attacking = attacking;
        this.being_attacked = being_attacked;

    }

    @Override
    public boolean perform(){
        //check if the attack was successful.
        return attacking.attack(being_attacked) > 0.0;
    }

}
