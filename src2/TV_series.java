import java.text.DecimalFormat;
import java.util.ArrayList;

class TV_series extends Films{
    private String[] genre;
    private String[] writers;
    private String startDate;
    private String endDate;
    private String seasons;
    private String episodes;
    public static int number_of_series=0;
    public TV_series(String film_id, String film_title, String language, String[] directors, int runtime, String country, String[] cast, String[] genre, String[] writers, String startDate, String endDate, String seasons, String episodes, String filmtype) {
        super(film_id, film_title, language, directors, runtime, country, cast,filmtype);
        this.genre = genre;
        this.writers = writers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.seasons = seasons;
        this.episodes = episodes;
        number_of_series+=1;
    }
    public String[] getWriters() {
        return writers;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getSeasons() {
        return seasons;
    }

    public String getEpisodes() {
        return episodes;
    }

    public String viewfilm(ArrayList<Writer> writers, ArrayList<Director> directors, ArrayList<Performer> all_performer){
        ArrayList<Performer> my_actors=new ArrayList<>();
        ArrayList<Director>my_directors=new ArrayList<>();
        ArrayList<Writer>my_writers =new ArrayList<>();
        for(String actor:getCast()){
            for (Performer who_actor: all_performer) {
                if(actor.equals(who_actor.getPeople_id())){
                    my_actors.add(who_actor);
                }
            }
        }
        for(String director:getDirectors()){
            for (Director who_director: directors) {
                if(director.equals(who_director.getPeople_id())){
                    my_directors.add(who_director);
                }
            }
        }
        for(String writer:getWriters()){
            for (Writer who_writer: writers) {
                if(writer.equals(who_writer.getPeople_id())){
                    my_writers.add(who_writer);
                }
            }
        }
        StringBuilder view_writer= new StringBuilder();
        StringBuilder view_director= new StringBuilder();
        StringBuilder view_actor= new StringBuilder();
        StringBuilder view_genre= new StringBuilder();
        for (Writer swriter:my_writers) {
            view_writer.append(swriter.getName()).append(" ").append(swriter.getSurname()).append(", ");
        }
        view_writer = new StringBuilder(view_writer.substring(0, view_writer.length() - 2));
        for(Director sdirector:my_directors){
            view_director.append(sdirector.getName()).append(" ").append(sdirector.getSurname()).append(", ");
        }
        view_director = new StringBuilder(view_director.substring(0, view_director.length() - 2));
        for (Performer sartist:my_actors){
            view_actor.append(sartist.getName()).append(" ").append(sartist.getSurname()).append(", ");
        }
        view_actor = new StringBuilder(view_actor.substring(0, view_actor.length() - 2));
        for(String sgenre:genre){
            view_genre.append(sgenre).append(", ");
        }
        view_genre = new StringBuilder(view_genre.substring(0, view_genre.length() - 2));
        String sout;
        if(count_of_rate>0){
            if(rating_score==(int)(rating_score)){
        sout=String.format("%s (%s-%s)\n" +
                "%s seasons, %s episodes\n"+
                "%s\n" +
                "Writers: %s\n" +
                "Directors: %s\n" +
                "Stars: %s\n" +
                        "Ratings: %s/10 from %s users\n",getFilm_title(),getStartDate().split("\\.")[2],getEndDate().split("\\.")[2],getSeasons(),getEpisodes(), view_genre.toString(), view_writer.toString(), view_director.toString(), view_actor.toString(),(int)rating_score,count_of_rate);
            }
            else{
                String number= new DecimalFormat("#.#").format(rating_score);
                char number1= number.charAt(0);
                char number2= number.charAt(2);
                number=number1+","+number2;
                sout=String.format("%s (%s-%s)\n" +
                        "%s seasons, %s episodes\n"+
                        "%s\n" +
                        "Writers: %s\n" +
                        "Directors: %s\n" +
                        "Stars: %s\n" +
                        "Ratings: %s/10 from %s users\n",getFilm_title(),getStartDate().split("\\.")[2],getEndDate().split("\\.")[2],getSeasons(),getEpisodes(), view_genre.toString(), view_writer.toString(), view_director.toString(), view_actor.toString(),number,count_of_rate);
            }
        }
        else{
            sout=String.format("%s (%s-%s)\n" +
                "%s seasons, %s episodes\n"+
                "%s\n" +
                "Writers: %s\n" +
                "Directors: %s\n" +
                "Stars: %s\n" +
                "Awaiting for votes\n",getFilm_title(),getStartDate().split("\\.")[2],getEndDate().split("\\.")[2],getSeasons(),getEpisodes(), view_genre.toString(), view_writer.toString(), view_director.toString(), view_actor.toString());
        }
        return sout;
    }
}

