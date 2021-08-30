import java.util.ArrayList;
import java.util.HashMap;

public class Human extends Calliance{
    public Human(int hp, String name) {
        super(hp, name,Constants.humanMaxHP);
    }
    @Override
    public String fightToDeath(HashMap<String, Calliance> coordinates1, HashMap<String, Zorde> coordinates2, String enemy, Character enemyC, String me, ArrayList<Zorde> zordes, ArrayList<Calliance> calliances) {
        ((Zorde) enemyC).changeHp(Constants.humanAP);
        return super.fightToDeath(coordinates1, coordinates2, enemy, enemyC, me, zordes, calliances);
    }
}
