import java.util.ArrayList;
import java.util.HashMap;

public class Goblin extends Zorde{
    public Goblin(int hp, String name) {
        super(hp, name,Constants.goblinMaxHP);
    }

    @Override
    public String fightToDeath(HashMap<String, Calliance> coordinates1, HashMap<String, Zorde> coordinates2, String enemy, Character enemyC, String me, ArrayList<Zorde> zordes, ArrayList<Calliance> calliances) {
        ((Calliance) enemyC).changeHp(Constants.goblinAP);
        return super.fightToDeath(coordinates1, coordinates2, enemy, enemyC, me, zordes, calliances);
    }
}
