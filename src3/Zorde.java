import java.util.ArrayList;
import java.util.HashMap;

public class Zorde extends Character{
    private int hp;
    public Zorde(int hp,String name,int constant_hp) {
        super(name, constant_hp);
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void changeHp(int decrease){
        if(this.hp-decrease<=0){
            this.hp=0;
        }
        else {
            this.hp=this.hp-decrease;}
    }
    public void upHp(int increase){
        if(this.hp+increase>=getConstant_hp()){
            this.hp=getConstant_hp();
        }else{
        this.hp=this.hp+increase; }
    }

    public String fightToDeath(HashMap<String,Calliance>coordinates1,HashMap<String,Zorde>coordinates2,String enemy,Character enemyC,String me,ArrayList<Zorde>zordes,ArrayList<Calliance>calliances){
        if(getHp()==(enemyC).getHp()){
            coordinates1.remove(enemy);
            coordinates2.remove(me);
            calliances.remove(enemyC);
            zordes.remove(coordinates2.get(me));
            return "equal";
        }else if(getHp()> (enemyC).getHp()){
            coordinates1.remove(enemy);
            changeHp((enemyC).getHp());
            enemyC.setHp(0);
            return "win";
        }else{
            coordinates2.remove(me);
            ((Calliance) enemyC).changeHp(getHp());
            setHp(0);
            return "lose";
        }
    }

    public void Attack(int k1, int k2, String[][] board, HashMap<String,Calliance> coordinates1, ArrayList<Calliance> calliances,int constantap){
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
                if (coordinates1.containsKey(board[neighboor[0]][neighboor[1]])) {
                    coordinates1.get(board[neighboor[0]][neighboor[1]]).changeHp(constantap);
                    if (coordinates1.get(board[neighboor[0]][neighboor[1]]).getHp() <= 0) {
                        board[neighboor[0]][neighboor[1]] = "  ";
                        calliances.remove(coordinates1.get(board[neighboor[0]][neighboor[1]]));
                        coordinates1.remove(board[neighboor[0]][neighboor[1]]);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }
}
