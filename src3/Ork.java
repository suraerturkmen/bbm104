import java.util.ArrayList;
import java.util.HashMap;

public class Ork extends Zorde {
    public Ork(int hp, String name) {
        super(hp, name,Constants.orkMaxHP);
    }
    @Override
    public String fightToDeath(HashMap<String, Calliance> coordinates1, HashMap<String, Zorde> coordinates2, String enemy, Character enemyC, String me, ArrayList<Zorde> zordes, ArrayList<Calliance> calliances) {
        ((Calliance) enemyC).changeHp(Constants.orkAP);
        return super.fightToDeath(coordinates1, coordinates2, enemy, enemyC, me, zordes, calliances);
    }
    public void heal(int k1, int k2, String[][] board, HashMap<String,Zorde> coordinates2){
        int[][] neighboors=new int[8][1];
        int[] u={k1-1,k2};
        neighboors[0]=u;
        int[] d={k1+1,k2};
        neighboors[1]=d;
        int[] l={k1,k2-1};
        neighboors[2]=l;
        int[] r={k1,k2+1};
        neighboors[3]=r;
        int[] c1={k1-1,k2-1};
        neighboors[4]=c1;
        int[] c2={k1-1,k2+1};
        neighboors[5]=c2;
        int[] c3={k1+1,k2-1};
        neighboors[6]=c3;
        int[] c4={k1+1,k2+1};
        neighboors[7]=c4;
        for (int[] neighboor:neighboors) {
            try {
                if (coordinates2.containsKey(board[neighboor[0]][neighboor[1]])) {
                    coordinates2.get(board[neighboor[0]][neighboor[1]]).upHp(Constants.orkHealPoints);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        upHp(Constants.orkHealPoints);
    }
}
