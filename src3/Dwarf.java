import java.util.ArrayList;
import java.util.HashMap;

public class Dwarf extends Calliance{
    public Dwarf(int hp, String name) {
        super(hp, name,Constants.dwarfMaxHP);
    }

    @Override
    public String fightToDeath(HashMap<String, Calliance> coordinates1, HashMap<String, Zorde> coordinates2, String enemy, Character enemyC, String me, ArrayList<Zorde> zordes, ArrayList<Calliance> calliances) {
        ((Zorde) enemyC).changeHp(Constants.dwarfAP);
        return super.fightToDeath(coordinates1, coordinates2, enemy, enemyC, me, zordes, calliances);
    }
}

