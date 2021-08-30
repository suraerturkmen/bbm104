import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedReader decimal_txt = new BufferedReader(new FileReader(args[0]) ); BufferedWriter octal_txt=new BufferedWriter(new FileWriter("octal.txt"))){
            String line=decimal_txt.readLine();
            while (true){
                int decimal=Integer.parseInt(line);
                Stack<Integer> octal=new Stack<>();
                while (decimal!=0){
                    octal.push(decimal%8);
                    decimal=decimal/8;
                }
                int size=octal.Size();
                for (int i = 0; i < size; i++) {
                    octal_txt.write(octal.pop().toString());
                }
                line=decimal_txt.readLine();
                if(line==null){
                    break;
                }
                octal_txt.write("\n");

            }
        }
    }
}
