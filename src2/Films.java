class Films {
    private String film_id;
    private String film_title;
    private String language;
    private String[] directors;
    private int runtime;
    private String country;
    private String[] cast;
    public double rating_score;
    public String filmtype;
    public int count_of_rate;

    public Films(String film_id, String film_title, String language, String[] directors, int runtime, String country, String[] cast,String filmtype) {
        this.film_id = film_id;
        this.film_title = film_title;
        this.language = language;
        this.directors = directors;
        this.runtime = runtime;
        this.country = country;
        this.cast = cast;
        this.filmtype=filmtype;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getFilm_id() {
        return film_id;
    }

    public String getFilm_title() {
        return film_title;
    }

    public String getLanguage() {
        return language;
    }

    public String[] getDirectors() {
        return directors;
    }

    public String getCountry() {
        return country;
    }

    public String[] getCast() {
        return cast;
    }

    //with the below method users can rate
    public void rating(int rate){
        if(rate>10 || rate<1){
            System.out.println("The rate is greater than 10 or less than 1.");
        }else{
        count_of_rate+=1;
        rating_score=(rating_score*(count_of_rate-1)+rate)/count_of_rate;
        }
    }
    //with the below method, users can edit their rate
    public void edit_rating(int rate1,int rate2){
        rating_score=(rating_score*(count_of_rate)-rate1+rate2)/count_of_rate;
    }
    //the below method remove the user's rate
    public void  remove_rating(int rate1){
        count_of_rate-=1;
        if(count_of_rate==0){
            rating_score=0;

        }else{
            rating_score=((rating_score*(count_of_rate+1)-rate1)/count_of_rate);}
    }
    public String viewfilm(){return " ";}
}

