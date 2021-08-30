import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Method {
    public static void Readitems(BufferedReader items_txt,ArrayList<Items> itemsArrayList) throws IOException {
        while (true){
            String itemsReadline=items_txt.readLine();
            if(itemsReadline==null){
                break;
            }
            String[] split_line=itemsReadline.split(" ");
            itemsArrayList.add(new Items(split_line[0],split_line[1]));
        }
    }
}
