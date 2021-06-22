import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BoardMethods {
    public static void print_board(String[][] board, BufferedWriter output_txt) throws IOException {
        StringBuilder sout= new StringBuilder();
        for (int i=0; board.length*2+2>i;i++){
            sout.append("*");
        }
        output_txt.write(sout+"\n");
        for (String[] b1:board) {
            output_txt.write("*");
            for (String b2:b1) {
                output_txt.write(b2);
            }
            output_txt.write("*\n");
        }
        output_txt.write(sout+"\n");
    }
    public static Character find_characters(String name, ArrayList<Zorde> zordes, ArrayList<Calliance> calliances){
        Zorde z1 = null;
        Calliance c2 = null;
        for (Zorde z: zordes) {
            if(z.getName().equals(name)){
                z1=z;
                break;
            }
        }
        for (Calliance c1:calliances) {
            if(c1.getName().equals(name)){
                c2=c1;
                break;
            }
        }
        if(z1!=null){
            return z1;
        }
        return c2;
    }
}