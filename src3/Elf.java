import java.util.ArrayList;
import java.util.HashMap;

public class Elf extends Calliance{
    public Elf(int hp, String name) {
        super(hp, name,Constants.elfMaxHP);
    }

    @Override
    public String fightToDeath(HashMap<String, Calliance> coordinates1, HashMap<String, Zorde> coordinates2, String enemy, Character enemyC, String me, ArrayList<Zorde> zordes, ArrayList<Calliance> calliances) {
        ((Zorde) enemyC).changeHp(Constants.elfAP);
        return super.fightToDeath(coordinates1, coordinates2, enemy, enemyC, me, zordes, calliances);
    }

    public void RangeAttack(int k1, int k2, String[][] board, HashMap<String, Zorde> coordinates2, ArrayList<Zorde> zordes) {
        int[][] neighboors=new int[24][1];
        neighboors[0]= new int[]{k1 - 1, k2};
        neighboors[1]= new int[]{k1 + 1, k2};
        neighboors[2]= new int[]{k1, k2 - 1};
        neighboors[3]= new int[]{k1, k2 + 1};
        neighboors[4]= new int[]{k1 - 1, k2 - 1};
        neighboors[5]= new int[]{k1 - 1, k2 + 1};
        neighboors[6]= new int[]{k1 + 1, k2 - 1};
        neighboors[7]= new int[]{k1 + 1, k2 + 1};
        neighboors[8]= new int[]{k1 - 2, k2-2};
        neighboors[9]= new int[]{k1 - 2, k2-1};
        neighboors[10]= new int[]{k1 - 2, k2};
        neighboors[11]= new int[]{k1 - 2, k2+1};
        neighboors[12]= new int[]{k1 - 2, k2+2};
        neighboors[13]= new int[]{k1 + 2, k2-2};
        neighboors[14]= new int[]{k1 + 2, k2-1};
        neighboors[15]= new int[]{k1 + 2, k2};
        neighboors[16]= new int[]{k1 + 2, k2+1};
        neighboors[17]= new int[]{k1 + 2, k2+2};
        neighboors[18]= new int[]{k1, k2-2};
        neighboors[19]= new int[]{k1, k2+2};
        neighboors[20]= new int[]{k1 - 1, k2-2};
        neighboors[21]= new int[]{k1 - 1, k2+2};
        neighboors[22]= new int[]{k1 + 1, k2-2};
        neighboors[23]= new int[]{k1 + 1, k2+2};
        for (int[] neighboor:neighboors) {
            try {
                if (coordinates2.containsKey(board[neighboor[0]][neighboor[1]])) {
                    coordinates2.get(board[neighboor[0]][neighboor[1]]).changeHp(Constants.elfRangedAP);
                    if (coordinates2.get(board[neighboor[0]][neighboor[1]]).getHp() <= 0) {
                        board[neighboor[0]][neighboor[1]] = "  ";
                        zordes.remove(coordinates2.get(board[neighboor[0]][neighboor[1]]));
                        coordinates2.remove(board[neighboor[0]][neighboor[1]]);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
    }
}
