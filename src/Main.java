import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader author_txt=null;//I assigned+
        BufferedWriter output_txt=null;//the initialize+
        BufferedReader command_txt=null;//filo i/o
        BufferedReader article =null;
        ArrayList<Author> authors=new ArrayList<Author>();
        ArrayList<Article> articles=new ArrayList<Article>();
        try {
            String author = args[0];
            String command = args[1];
            author_txt = new BufferedReader(new FileReader(author));
            command_txt = new BufferedReader(new FileReader(command));
            output_txt=new BufferedWriter(new FileWriter("output.txt"));//I created output.txt file
            while (true) {
                String author_text = author_txt.readLine();//I have read author.txt file
                if (author_text == null) {
                    break;
                }
                String[] split_author_text = author_text.split(" ");
                Metodlar.read_authors(split_author_text,authors);// I created author objects and added  that objects in the authors array list in this method.
            }
            while (true) {
                String sentence = command_txt.readLine();//I have read command.txt file
                if (sentence == null) {
                    break;
                }
                if (sentence.startsWith("read")) {
                    String[] new_command = sentence.split(" ");
                    article = new BufferedReader(new FileReader(new_command[1]));
                    while (true) {
                        String row = article.readLine();//I have read article(number).txt file
                        if (row == null) {
                            break;
                        }
                         Metodlar.read_articles(articles,row);//I created article objects and added  that objects in the article array list in this method.
                    }
                }
                if (sentence.startsWith("list")) {
                    String output = Metodlar.list(authors, articles);
                    output_txt.write(output);
                }
                if (sentence.startsWith("sortedAll")) {
                    output_txt.write("*************************************SortedAll Successful*************************************\n");
                    for (Author item : authors) {
                        item.sort_articles();
                    }
                }
                if (sentence.startsWith("del")) {
                    output_txt.write("*************************************del Successful*************************************\n");
                    String[] ilk_cikti = sentence.split(" ");
                    for (Author item : authors) {
                        if (item.getId().equals(ilk_cikti[1])) {
                            authors.remove(item);
                            break;
                        }
                    }
                }
                if (sentence.startsWith("completeAll")) {
                    output_txt.write("*************************************CompleteAll Successful*************************************\n");
                    for (Author items : authors) {
                        String[] all_articles= items.All_articles();
                        ArrayList<String>article_list=new ArrayList<>();
                        Metodlar.complete_all(all_articles,article_list,items,articles);
                        }
                    }
                }
            }
        finally{
            if (author_txt!=null) {
                author_txt.close();
            }
            if(output_txt!=null){
                output_txt.close();
            }
            if(command_txt!=null){
                command_txt.close();
            }
            if(article!=null){
                article.close();
            }
        }
    }
}
