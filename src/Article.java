public class Article {
    private String paperid;
    private String name;
    private String publisherName;
    private String publishYear;

    public Article(String paperid, String name, String publisherName, String publishYear) {
        this.paperid = paperid;
        this.name = name;
        this.publisherName = publisherName;
        this.publishYear = publishYear;
    }
    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }
}
