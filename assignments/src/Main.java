import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
class myException extends Exception{
    myException(){}
}

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader initials_txt = new BufferedReader(new FileReader(args[0])); BufferedReader commands_txt = new BufferedReader(new FileReader(args[1])); BufferedWriter output_txt = new BufferedWriter(new FileWriter(args[2]))) {
            initials_txt.readLine();
            String readline1=initials_txt.readLine();
            int a = Integer.parseInt(readline1.split("x")[0]);
            String[][] board= new  String[a][a];
            ArrayList<Zorde> zordes=new ArrayList<>();
            ArrayList<Calliance> calliances=new ArrayList<>();
            HashMap<String,Calliance > coordinates1=new HashMap<>();
            HashMap<String,Zorde >coordinates2=new HashMap<>();
            while (true){
                String readline=initials_txt.readLine();
                if(readline==null){
                    break;
                }
                String[] readline_split=readline.split(" ");
                if(readline.startsWith("ELF")){
                    board[Integer.parseInt(readline_split[3])][Integer.parseInt(readline_split[2])]=readline_split[1];
                    calliances.add(new Elf(Constants.elfMaxHP,readline_split[1]));
                    coordinates1.put(readline_split[1],calliances.get(calliances.size() - 1));
                }
                if(readline.startsWith("DWARF")){
                    board[Integer.parseInt(readline_split[3])][Integer.parseInt(readline_split[2])]=readline_split[1];
                    calliances.add(new Dwarf(Constants.dwarfMaxHP,readline_split[1]));
                    coordinates1.put(readline_split[1],calliances.get(calliances.size() - 1));
                }
                if(readline.startsWith("HUMAN")){
                    board[Integer.parseInt(readline_split[3])][Integer.parseInt(readline_split[2])]=readline_split[1];
                    calliances.add(new Human(Constants.humanMaxHP,readline_split[1]));
                    coordinates1.put(readline_split[1],calliances.get(calliances.size() - 1));
                }
                if(readline.startsWith("GOBLIN")){
                    board[Integer.parseInt(readline_split[3])][Integer.parseInt(readline_split[2])]=readline_split[1];
                    zordes.add(new Goblin(Constants.goblinMaxHP,readline_split[1]));
                    coordinates2.put( readline_split[1],zordes.get(zordes.size()-1));
                }
                if(readline.startsWith("TROLL")){
                    board[Integer.parseInt(readline_split[3])][Integer.parseInt(readline_split[2])]=readline_split[1];
                    zordes.add(new Troll(Constants.trollMaxHP,readline_split[1]));
                    coordinates2.put( readline_split[1],zordes.get(zordes.size()-1));
                }
                if(readline.startsWith("ORK")){
                    board[Integer.parseInt(readline_split[3])][Integer.parseInt(readline_split[2])]=readline_split[1];
                    zordes.add(new Ork(Constants.orkMaxHP,readline_split[1]));
                    coordinates2.put( readline_split[1],zordes.get(zordes.size()-1));
                }
            }
            int b = 0,c=0;
            for (String[] b1:board) {
                for (String b2:b1) {
                    if(b2==null){
                        board[b][c]="  ";
                    }
                    c++;
                }
                c=0;
                b++;
            }
            BoardMethods.print_board(board,output_txt);//boardın ilk hali
            ArrayList<Character> characters1=new ArrayList<>();
            characters1.addAll(zordes);
            characters1.addAll(calliances);
            characters1.sort(Comparator.comparing(Character::getName));
            output_txt.write("\n");
            for (Character character: characters1) {
                output_txt.write(character.getName()+"\t"+character.getHp()+"\t"+"("+character.getConstant_hp()+")"+"\n");
            }
            while(true){
                String readline=commands_txt.readLine();
                if(readline==null){
                    break;
                }
                String[] readline_split=readline.split(" ");
                //elemanı bul
                Zorde z1 = null;
                Calliance c2 = null;
                for (Zorde z: zordes) {
                    if(z.getName().equals(readline_split[0])){
                        z1=z;
                        break;
                    }
                }
                for (Calliance c1:calliances) {
                    if(c1.getName().equals(readline_split[0])){
                        c2=c1;
                        break;
                    }
                }
                String[] move=readline_split[1].split(";");
                try{
                    if(c2!=null){
                        if(c2 instanceof Human){
                            if(Constants.humanMaxMove*2!=move.length){
                                throw new myException();
                            }
                        }
                        if(c2 instanceof Elf){
                            if(Constants.elfMaxMove*2!=move.length){
                                throw new myException();
                            }
                        }
                        if (c2 instanceof Dwarf){
                            if(Constants.dwarfMaxMove*2!=move.length){
                                throw new myException();
                            }
                        }
                        int k1=0;
                        int k2=0;
                        boolean stop=false;
                        for (String[] board1:board) {
                            k2=0;
                            for (String board2:board1) {
                                if(board2.equals(readline_split[0])){
                                    stop=true;
                                    break;
                                }
                                k2++;
                            }
                            if(stop){
                                break;
                            }
                            k1++;
                        }
                        int step=0;
                        int k3;
                        int k4;
                        boolean go=false;
                        for(int i=0;i<move.length;i+=2){
                            k3=k1;
                            k4=k2;
                            k2=k2+Integer.parseInt(move[i]);
                            k1=k1+Integer.parseInt(move[i+1]);
                            step++;
                            try {
                                Character enemyC=BoardMethods.find_characters(board[k1][k2],zordes,calliances);
                                if(enemyC instanceof Zorde){
                                    String enemy=board[k1][k2];
                                    String me=readline_split[0];
                                    String result;
                                    result=(c2).fightToDeath(coordinates1,coordinates2,enemy,enemyC,me,zordes,calliances);
                                    if(result.equals("win")){
                                        board[k1][k2]=readline_split[0];
                                        board[k3][k4]="  ";
                                    }
                                    else if(result.equals("lose")){
                                        board[k3][k4]="  ";
                                    }else{
                                        board[k1][k2]="  ";
                                        board[k3][k4]="  ";
                                    }
                                    break;
                                }
                                else if(enemyC instanceof Calliance){
                                    break;
                                }else{
                                    if(c2 instanceof Human){
                                        if(step==3){
                                            (c2).Attack(k1,k2,board,coordinates2,zordes,Constants.humanAP);
                                        }
                                    }else if(c2 instanceof Elf){
                                        if(step==4){
                                            ((Elf)c2).RangeAttack(k1,k2,board,coordinates2,zordes);
                                        }else{
                                            (c2).Attack(k1,k2,board,coordinates2,zordes,Constants.elfAP);
                                        }
                                    }else{
                                        (c2).Attack(k1,k2,board,coordinates2,zordes,Constants.dwarfAP);
                                    }

                                    board[k1][k2]=readline_split[0];
                                    board[k3][k4]="  ";

                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                if(step==1){
                                    go=true;
                                }else {
                                    output_txt.write("\n");
                                    zordes.removeIf(z -> z.getHp() <= 0);
                                    calliances.removeIf(z -> z.getHp() <= 0);
                                    BoardMethods.print_board(board,output_txt);
                                    ArrayList<Character> characters=new ArrayList<>();
                                    characters.addAll(zordes);
                                    characters.addAll(calliances);
                                    characters.sort(Comparator.comparing(Character::getName));
                                    output_txt.write("\n");
                                    for (Character character: characters) {
                                        output_txt.write(character.getName()+"\t"+character.getHp()+"\t"+"("+character.getConstant_hp()+")"+"\n");
                                    }
                                    go=true;
                                }
                                output_txt.write("\nError : Game board boundaries are exceeded. Input line ignored."+"\n");
                                break;
                            }

                        }
                        if(go){
                            continue;
                        }
                        output_txt.write("\n");
                        zordes.removeIf(z -> z.getHp() <= 0);
                        calliances.removeIf(z -> z.getHp() <= 0);
                        BoardMethods.print_board(board,output_txt);
                        ArrayList<Character> characters=new ArrayList<>();
                        characters.addAll(zordes);
                        characters.addAll(calliances);
                        characters.sort(Comparator.comparing(Character::getName));
                        output_txt.write("\n");
                        for (Character character: characters) {
                            output_txt.write(character.getName()+"\t"+character.getHp()+"\t"+"("+character.getConstant_hp()+")"+"\n");
                        }
                    }if(z1!=null){
                        if(z1 instanceof Ork){
                            if(Constants.orkMaxMove*2!=move.length){
                                throw new myException();
                            }
                        }
                        if(z1 instanceof Troll){
                            if(Constants.trollMaxMove*2!=move.length){
                                throw new  myException();
                            }
                        }
                        if (z1 instanceof Goblin){
                            if(Constants.goblinMaxMove*2!=move.length){
                                throw new  myException();
                            }
                        }
                        int k1=0;
                        int k2=0;
                        boolean stop=false;
                        for (String[] board1:board) {
                            k2=0;
                            for (String board2:board1) {
                                if(board2.equals(readline_split[0])){
                                    stop=true;
                                    break;
                                }
                                k2++;
                            }
                            if(stop){
                                break;
                            }
                            k1++;
                        }
                        int step=0;
                        int k3;
                        int k4;
                        boolean go=false;
                        for(int i=0;i<move.length;i+=2){
                            k3=k1;
                            k4=k2;
                            k2=k2+Integer.parseInt(move[i]);
                            k1=k1+Integer.parseInt(move[i+1]);
                            step++;
                            try {
                                Character enemyC=BoardMethods.find_characters(board[k1][k2],zordes,calliances);
                                if(enemyC instanceof Calliance){
                                    String enemy=board[k1][k2];
                                    String me=readline_split[0];
                                    String result;
                                    if(z1 instanceof Ork){
                                        ((Ork)z1).heal(k3,k4,board,coordinates2);
                                    }
                                    result=(z1).fightToDeath(coordinates1,coordinates2,enemy,enemyC,me,zordes,calliances);
                                    if(result.equals("win")){
                                        board[k1][k2]=readline_split[0];
                                        board[k3][k4]="  ";
                                    }
                                    else if(result.equals("lose")){
                                        board[k3][k4]="  ";
                                    }else{
                                        board[k1][k2]="  ";
                                        board[k3][k4]="  ";
                                    }
                                    break;
                                }
                                else if(enemyC instanceof Zorde){
                                    if(z1 instanceof Ork){
                                    ((Ork)z1).heal(k3,k4,board,coordinates2);
                                    }
                                    break;
                                }else{
                                    if(z1 instanceof Ork){
                                        ((Ork)z1).heal(k3,k4,board,coordinates2);
                                        (z1).Attack(k1,k2,board,coordinates1,calliances,Constants.orkAP);
                                    }else if(z1 instanceof Troll){
                                        (z1).Attack(k1,k2,board,coordinates1,calliances,Constants.trollAP);
                                    }else{
                                        (z1).Attack(k1,k2,board,coordinates1,calliances,Constants.goblinAP);
                                    }

                                    board[k1][k2]=readline_split[0];
                                    board[k3][k4]="  ";
                                }
                            }catch (ArrayIndexOutOfBoundsException e){

                                if(step==1){
                                    go=true;}
                                else {
                                    output_txt.write("\n");
                                    zordes.removeIf(z -> z.getHp() <= 0);
                                    calliances.removeIf(z -> z.getHp() <= 0);
                                    BoardMethods.print_board(board,output_txt);
                                    ArrayList<Character> characters=new ArrayList<>();
                                    characters.addAll(zordes);
                                    characters.addAll(calliances);
                                    characters.sort(Comparator.comparing(Character::getName));
                                    output_txt.write("\n");
                                    for (Character character: characters) {
                                        output_txt.write(character.getName()+"\t"+character.getHp()+"\t"+"("+character.getConstant_hp()+")"+"\n");
                                    }
                                    go=true;
                                }
                                output_txt.write("\nError : Game board boundaries are exceeded. Input line ignored."+"\n");
                                break;
                            }
                        }
                        if (go){
                            continue;
                        }
                        output_txt.write("\n");
                        zordes.removeIf(z -> z.getHp() <= 0);
                        calliances.removeIf(z -> z.getHp() <= 0);
                        BoardMethods.print_board(board,output_txt);
                        ArrayList<Character> characters=new ArrayList<>();
                        characters.addAll(zordes);
                        characters.addAll(calliances);
                        characters.sort(Comparator.comparing(Character::getName));
                        output_txt.write("\n");
                        for (Character character: characters) {
                            output_txt.write(character.getName()+"\t"+character.getHp()+"\t"+"("+character.getConstant_hp()+")"+"\n");
                        }
                    }

                } catch (myException myException) {
                    output_txt.write("\nError : Move sequence contains wrong number of move steps. Input line ignored."+"\n");
                }
                if(zordes.isEmpty()){
                    output_txt.write("\n\nGame Finished\n" +
                                    "Calliance Wins"+"\n");
                    break;
                }else if(calliances.isEmpty()){
                    output_txt.write("\n\nGame Finished\n" +
                            "Zorde Wins"+"\n");
                    break;
                }
            }
        }
    }
}
