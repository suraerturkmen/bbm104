import java.text.DecimalFormat;
import java.util.ArrayList;

class Documentary extends Films{
    private String releaseDay;

    public Documentary(String film_id, String film_title, String language, String[] directors, int runtime, String country, String[] cast, String releaseDay,String filmtype) {
        super(film_id, film_title, language, directors, runtime, country, cast,filmtype);
        this.releaseDay = releaseDay;
    }

    public String getReleaseDay() {
        return releaseDay;
    }

    public String viewfilm(ArrayList<Director> directors, ArrayList<Performer> all_performer){
        ArrayList<Performer> my_actors=new ArrayList<>();
        ArrayList<Director>my_directors=new ArrayList<>();
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
        StringBuilder view_director= new StringBuilder();
        StringBuilder view_actor= new StringBuilder();
        for(Director sdirector:my_directors){
            view_director.append(sdirector.getName()).append(" ").append(sdirector.getSurname()).append(", ");
        }
        view_director = new StringBuilder(view_director.substring(0, view_director.length() - 2));
        for (Performer sartist:my_actors){
            view_actor.append(sartist.getName()).append(" ").append(sartist.getSurname()).append(", ");
        }
        view_actor=new StringBuilder((view_actor.substring(0,view_actor.length()-2)));
        String sout;
        if(count_of_rate>0) {
            if (rating_score == (int) (rating_score)) {
                sout = String.format("%s (%s)\n" +
                        "Directors: %s\n" +
                        "Stars: %s\n" +
                        "Ratings: %s/10 from %s users\n", getFilm_title(), getReleaseDay().split("\\.")[2], view_director.toString(), view_actor.toString(), (int)rating_score, count_of_rate);
            }else{
                String number= new DecimalFormat("#.#").format(rating_score);
                char number1= number.charAt(0);
                char number2= number.charAt(2);
                number=number1+","+number2;
                sout = String.format("%s (%s)\n" +
                        "Directors: %s\n" +
                        "Stars: %s\n" +
                        "Ratings: %s/10 from %s users\n", getFilm_title(), getReleaseDay().split("\\.")[2], view_director.toString(), view_actor.toString(), number, count_of_rate);

            }
        }else{
        sout=String.format("%s (%s)\n" +
                "Directors: %s\n" +
                "Stars: %s\n" +
                "Awaiting for votes\n",getFilm_title(),getReleaseDay().split("\\.")[2], view_director.toString(), view_actor.toString());}
        return sout;
    }
}
