import java.util.ArrayList;
import java.util.Arrays;

public class Author {
    private String id;
    private String name;
    private String university;
    private String department;
    private String email;
    private String article1="?";
    private String article2="?";
    private String article3="?";
    private String article4="?";
    private String article5="?";
    public boolean ask_others=true;
    public boolean add_article=false;
    Author( String id,String name, String university, String department, String email){
        this.id=id;
        this.name = name;
        this.university=university;
        this.department=department;
        this.email=email;
    }
    Author(String id){
        this.id=id;
        this.ask_others=false;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArticle1() {
        return article1;
    }

    public void setArticle(String article1) {
        this.add_article=true;
        this.article1 = article1;
    }

    public String getArticle2() {
        return article2;
    }

    public void setArticle(String article1,String article2) {
        this.add_article=true;
        this.article1=article1; this.article2 = article2;
    }

    public String getArticle3() {
        return article3;
    }

    public void setArticle(String article1,String article2,String article3) {
        this.add_article=true;
        this.article1=article1; this.article2 = article2;this.article3 = article3;
    }

    public String getArticle4() {
        return article4;
    }

    public void setArticle(String article1,String article2,String article3,String article4) {
        this.add_article=true;
        this.article1=article1; this.article2 = article2;this.article3 = article3;this.article4 = article4;
    }

    public String getArticle5() {
        return article5;
    }

    public void setArticle(String article1,String article2,String article3,String article4,String article5) {
        this.add_article=true;
        this.article1=article1; this.article2 = article2;this.article3 = article3;this.article4 = article4;this.article5 = article5;
    }

    public ArrayList<String> return_article(){
        ArrayList<String>articles= new ArrayList<String>();
        if (! article1.equals("?")){
        articles.add(article1);}
        if (!article2.equals("?")){
            articles.add(article2);}
        if (!article3.equals("?")){
            articles.add(article3);}
        if (!article4.equals("?")){
            articles.add(article4);}
        if (!article5.equals("?")){
            articles.add(article5);}
        return articles;
    }
    public int article_count(){
        return return_article().size();
    }
    public void sort_articles(){
        String[] dizi1= {article1,article2,article3,article4,article5};
        Arrays.sort(dizi1);
        this.article1=dizi1[0];
        this.article2=dizi1[1];
        this.article3=dizi1[2];
        this.article4=dizi1[3];
        this.article5=dizi1[4];
    }
    public String[] All_articles(){
        String[] all_articles={article1,article2,article3,article4,article5};
        return all_articles;
    }
}

