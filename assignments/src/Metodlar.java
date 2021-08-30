import java.util.ArrayList;
public class Metodlar {
    public static String list(ArrayList<Author> authors, ArrayList<Article> articles){
        StringBuilder last_out= new StringBuilder();
        String print_list2="";
        String print_list3="";
        String print_list1="----------------------------------------------List---------------------------------------------";
        last_out.append(print_list1);
        for (Author value : authors) {
            if(value.ask_others){
            print_list2 = String.format("\nAuthor:%s\t%s\t%s\t%s\t%s\n", value.getId(), value.getName(), value.getUniversity(), value.getDepartment(), value.getEmail());
            last_out.append(print_list2);
            int article_count = value.article_count();
            for (int j = 0; article_count > j; j++) {
                for (Article item : articles) {
                    ArrayList<String> author_articles = value.return_article();
                    if (item.getPaperid().equals(author_articles.get(j))) {
                        print_list3=String.format("+%s\t%s\t%s\t%s\n", item.getPaperid(), item.getName(), item.getPublisherName(), item.getPublishYear());
                        last_out.append(print_list3);
                    }
                }
            }
        }
            else{print_list2 = String.format("\nAuthor:%s\n", value.getId());
                last_out.append(print_list2);
                if(value.add_article){
                    int article_count = value.article_count();
                    for (int j = 0; article_count > j; j++) {
                        for (Article item : articles) {
                            ArrayList<String> author_articles = value.return_article();
                            if (item.getPaperid().equals(author_articles.get(j))) {
                                print_list3=String.format("+%s\t%s\t%s\t%s\n", item.getPaperid(), item.getName(), item.getPublisherName(), item.getPublishYear());
                                last_out.append(print_list3);
                            }
                        }
                    }
                }

            }
        }
        last_out.append("----------------------------------------------End----------------------------------------------\n");
        return last_out.toString();
    }
    public static void read_articles(ArrayList<Article> articles,String row){
        String[] article_rows = row.split(" ");
        String paperid = article_rows[1];
        String name = article_rows[2];
        String publisherName = article_rows[3];
        String publishYear = article_rows[4];
        articles.add(new Article(paperid, name, publisherName, publishYear));
    }
    public static void read_authors(String[] split_author_text,ArrayList<Author> authors){
        if (split_author_text.length==2){
            String id=split_author_text[1];
            authors.add(new Author(id));
        }else{
            String id = split_author_text[1];
            String name = split_author_text[2];
            String university = split_author_text[3];
            String department = split_author_text[4];
            String email = split_author_text[5];
            authors.add(new Author(id, name, university, department, email));
            int a = split_author_text.length - 6;
            if (a == 1) {
                authors.get(authors.size() - 1).setArticle(split_author_text[6]);
            } else if (a == 2) {
                authors.get(authors.size() - 1).setArticle(split_author_text[6], split_author_text[7]);
            } else if (a == 3) {
                authors.get(authors.size() - 1).setArticle(split_author_text[6], split_author_text[7], split_author_text[8]);
            } else if (a == 4) {
                authors.get(authors.size() - 1).setArticle(split_author_text[6], split_author_text[7], split_author_text[8], split_author_text[9]);
            } else if (a == 5) {
                authors.get(authors.size() - 1).setArticle(split_author_text[6], split_author_text[7], split_author_text[8], split_author_text[9], split_author_text[10]);
            } }
    }
    public static void complete_all(String[] all_articles,ArrayList<String>article_list,Author items,ArrayList<Article> articles){
        for (String all_article : all_articles) {
            if (all_article.equals("?")) {
                continue;
            } else {
                article_list.add(all_article);
            }
        }
        for(Article variables: articles){
            if(variables.getPaperid().startsWith(items.getId()) & article_list.size()<5){
                if(!article_list.contains(variables.getPaperid())){
                    article_list.add(variables.getPaperid());}}
        }
        if(article_list.size()==5){
            items.setArticle(article_list.get(0),article_list.get(1),article_list.get(2),article_list.get(3),article_list.get(4));
        }else if(article_list.size()==4){
            items.setArticle(article_list.get(0),article_list.get(1),article_list.get(2),article_list.get(3));
        }else if(article_list.size()==3){
            items.setArticle(article_list.get(0),article_list.get(1),article_list.get(2));
        }else if(article_list.size()==2){
            items.setArticle(article_list.get(0),article_list.get(1));
        }else if(article_list.size()==1){
            items.setArticle(article_list.get(0));
        }
    }
}
