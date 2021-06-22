import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Actor> actors=new ArrayList<>();
        ArrayList<ChildActor>childActors=new ArrayList<>();
        ArrayList<StuntPerformer> stuntPerformers=new ArrayList<>();
        ArrayList<Director>directors=new ArrayList<>();
        ArrayList<Writer> writers=new ArrayList<>();
        ArrayList<User>users=new ArrayList<>();
        ArrayList<Feature>features=new ArrayList<>();
        ArrayList<Short>shorts=new ArrayList<>();
        ArrayList<TV_series>tv_series=new ArrayList<>();
        ArrayList<Documentary>documentaries=new ArrayList<>();
        try (BufferedReader people_txt = new BufferedReader(new FileReader(args[0])); BufferedReader commands_txt = new BufferedReader(new FileReader(args[2])); BufferedReader films_txt = new BufferedReader(new FileReader(args[1])); BufferedWriter output_txt = new BufferedWriter(new FileWriter(args[3]))) {
                //I read the people.txt
                while (true) {
                String people_line = people_txt.readLine();
                if (people_line == null) {
                    break;
                }
                String[] split_people_line = people_line.split("\t");
                if (people_line.startsWith("Actor")) {
                    actors.add(new Actor(split_people_line[1], split_people_line[2], split_people_line[3], split_people_line[4], split_people_line[5]));
                }
                if (people_line.startsWith("ChildActor")) {
                    childActors.add(new ChildActor(split_people_line[1], split_people_line[2], split_people_line[3], split_people_line[4], Integer.parseInt(split_people_line[5])));
                }
                if (people_line.startsWith("Director")) {
                    directors.add(new Director(split_people_line[1], split_people_line[2], split_people_line[3], split_people_line[4], split_people_line[5]));
                }
                if (people_line.startsWith("Writer")) {
                    writers.add(new Writer(split_people_line[1],split_people_line[2],split_people_line[3],split_people_line[4],split_people_line[5]));
                }
                if (people_line.startsWith("StuntPerformer")) {
                    String[] real_actors_id = split_people_line[6].split(",");
                    stuntPerformers.add(new StuntPerformer(split_people_line[1], split_people_line[2], split_people_line[3], split_people_line[4], split_people_line[5], real_actors_id));
                }
                if (people_line.startsWith("User")) {
                    users.add(new User(split_people_line[1], split_people_line[2], split_people_line[3], split_people_line[4]));
                }
            }
            ArrayList<Performer>allPerformer;
            allPerformer=Methods.concatPerformer(actors,childActors,stuntPerformers);
            ArrayList<Films>allFilms=new ArrayList<>();
            //I read films.txt
            while (true) {
                String film_line = films_txt.readLine();
                if (film_line == null) {
                    break;
                }
                String[] split_film_line = film_line.split("\t");
                if (film_line.startsWith("FeatureFilm")) {
                    String[] directors_id = split_film_line[4].split(",");
                    String[] actors_id = split_film_line[7].split(",");
                    String[] genres = split_film_line[8].split(",");
                    String[] writer_id = split_film_line[10].split(",");
                    features.add(new Feature(split_film_line[1], split_film_line[2], split_film_line[3],
                            directors_id, Integer.parseInt(split_film_line[5]), split_film_line[6], actors_id, genres, split_film_line[9], writer_id, split_film_line[11],"FeatureFilm"));
                    allFilms.add(features.get(features.size()-1));

                }
                if (film_line.startsWith("ShortFilm")) {
                    String[] directors_id = split_film_line[4].split(",");
                    String[] actors_id = split_film_line[7].split(",");
                    String[] genres = split_film_line[8].split(",");
                    String[] writer_id = split_film_line[10].split(",");
                    shorts.add(new Short(split_film_line[1], split_film_line[2], split_film_line[3], directors_id, Integer.parseInt(split_film_line[5])
                            , split_film_line[6], actors_id, genres, split_film_line[9], writer_id,"ShortFilm"));
                    if(shorts.get(shorts.size()-1).control_runtime){
                        allFilms.add(shorts.get(shorts.size()-1));
                    }else{
                        shorts.remove(shorts.size()-1);
                    }
                }
                if (film_line.startsWith("Documentary")) {
                    String[] directors_id = split_film_line[4].split(",");
                    String[] actors_id = split_film_line[7].split(",");
                    documentaries.add(new Documentary(split_film_line[1], split_film_line[2], split_film_line[3], directors_id, Integer.parseInt(split_film_line[5]),
                            split_film_line[6], actors_id, split_film_line[8],"Documentary"));
                    allFilms.add(documentaries.get(documentaries.size()-1));
                }
                if (film_line.startsWith("TVSeries")) {
                    String[] directors_id = split_film_line[4].split(",");
                    String[] actors_id = split_film_line[7].split(",");
                    String[] genres = split_film_line[8].split(",");
                    String[] writer_id = split_film_line[9].split(",");
                    tv_series.add(new TV_series(split_film_line[1], split_film_line[2], split_film_line[3], directors_id, Integer.parseInt(split_film_line[5])
                            , split_film_line[6], actors_id, genres, writer_id, split_film_line[10], split_film_line[11], split_film_line[12], split_film_line[13],"TVSeries"));
                    allFilms.add(tv_series.get(tv_series.size()-1));
                }
            }
            //read the command.txt
            while (true){
                String commands_line = commands_txt.readLine();
                if (commands_line == null) {
                    break;
                }
                String[] split_commands_line =commands_line.split("\t");
                if(commands_line.startsWith("RATE")){
                    if(Integer.parseInt(split_commands_line[3])<=10 & Integer.parseInt(split_commands_line[3])>0){
                    output_txt.write(commands_line+"\n"+"\n");

                    User user1=Methods.findUser(users,split_commands_line[1]);
                    Films film1=Methods.findFilm(allFilms,split_commands_line[2]);
                    //ratefilm
                    if(film1!=null && user1!=null && !(user1.rate.containsKey(film1.getFilm_id())) ){
                        film1.rating(Integer.parseInt(split_commands_line[3]));
                        user1.rate.put(film1.getFilm_id(),split_commands_line[3]);
                        output_txt.write(String.format("Film rated successfully\n" +
                                "Film type: %s\n" +
                                "Film title: %s\n%n",film1.filmtype,film1.getFilm_title()));
                    }
                    //if the film was rated
                    else if(film1!=null && user1!=null && user1.rate.containsKey(Objects.requireNonNull(film1).getFilm_id())){
                        output_txt.write("This film was earlier rated\n\n");
                    //If the requirements are missing
                    }else{
                        output_txt.write(String.format("Command Failed\n" +
                                "User ID: %s\n" +
                                "Film ID: %s\n%n",split_commands_line[1],split_commands_line[2]));
                    }
                        output_txt.write("-----------------------------------------------------------------------------------------------------\n");
                    }
                    else{
                        System.out.println("The rate is greater than 10 or less than 1.\n");
                    }

                }
                if(commands_line.startsWith("ADD")){
                    output_txt.write(commands_line+"\n"+"\n");
                        String unique_id=null;
                        boolean uniq_people;
                        String[] director_list=split_commands_line[5].split(",");
                        String[] writer_list=split_commands_line[11].split(",");
                        String[] performer_list=split_commands_line[8].split(",");
                        assert split_commands_line[2] != null;
                        for (Films item:allFilms) {
                            if (split_commands_line[2].equals(item.getFilm_id())) {
                                unique_id = split_commands_line[2];
                                break;
                            }
                        }
                        //check the performers,directors,writers
                        uniq_people=Methods.Control(allPerformer,writers,directors,performer_list,writer_list,director_list);
                        //give fail for missing requirements
                        if(unique_id != null || !uniq_people){
                            output_txt.write(String.format("Command Failed\n" +
                                    "Film ID: %s\n" +
                                    "Film title: %s\n",split_commands_line[2],split_commands_line[3]));
                        }
                        //add feature film
                        else{
                            String[] genres = split_commands_line[9].split(",");
                            features.add(new Feature(split_commands_line[2], split_commands_line[3], split_commands_line[4],
                                    director_list, Integer.parseInt(split_commands_line[6]), split_commands_line[7], performer_list, genres, split_commands_line[10], writer_list, split_commands_line[12],"FeatureFilm"));
                            allFilms.add(features.get(features.size()-1));
                            output_txt.write(String.format("FeatureFilm added successfully\n" +
                                    "Film ID: %s\n" +
                                    "Film title: %s\n",split_commands_line[2],split_commands_line[3]));
                        }
                        output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                }
                if(commands_line.startsWith("VIEWFILM")){
                    output_txt.write(commands_line+"\n"+"\n");
                    Films found_film=Methods.findFilm(allFilms,split_commands_line[1]);
                    if(found_film==null){
                        output_txt.write(String.format("Command Failed\n" +
                                "Film ID: %s\n",split_commands_line[1]));
                    }
                    else{
                        //for viewfilm commands
                        switch (found_film.filmtype) {
                            case "ShortFilm":
                                output_txt.write(((Short) found_film).viewfilm(writers, directors, allPerformer));
                                break;
                            case "FeatureFilm":
                                output_txt.write(((Feature) found_film).viewfilm(writers, directors, allPerformer));
                                break;
                            case "Documentary":
                                output_txt.write(((Documentary) found_film).viewfilm(directors, allPerformer));
                                break;
                            case "TVSeries":
                                output_txt.write(((TV_series) found_film).viewfilm(writers, directors, allPerformer));
                                break;
                                }
                    }
                    output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                }
                if(commands_line.startsWith("EDIT")){
                    output_txt.write(commands_line+"\n"+"\n");
                    Films film1=Methods.findFilm(allFilms,split_commands_line[3]);
                    User user1=Methods.findUser(users,split_commands_line[2]);
                    //edit user rate
                    if(user1!=null && user1.rate.containsKey(split_commands_line[3])&& film1!=null){
                        int rate1=Integer.parseInt(user1.rate.get(split_commands_line[3]));
                        user1.rate.put(split_commands_line[3],split_commands_line[4]);
                        film1.edit_rating(rate1,Integer.parseInt(split_commands_line[4]));
                        output_txt.write(String.format("New ratings done successfully\n" +
                                "Film title: %s\n" +
                                "Your rating: %s\n",film1.getFilm_title(),split_commands_line[4]));
                    }
                    //give fail for missing requirements
                    else{
                        output_txt.write(String.format("Command Failed\n" +
                                "User ID: %s\n" +
                                "Film ID: %s\n",split_commands_line[2],split_commands_line[3]));
                    }
                    output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                }
                if(commands_line.startsWith("REMOVE")){;
                    output_txt.write(commands_line+"\n"+"\n");
                    Films film1=Methods.findFilm(allFilms,split_commands_line[3]);
                    User user1=Methods.findUser(users,split_commands_line[2]);
                    //remove user rate
                    if(user1!=null && user1.rate.containsKey(split_commands_line[3])&& film1!=null){
                        String rate1=user1.rate.get(split_commands_line[3]);
                        user1.rate.remove(split_commands_line[3]);
                        film1.remove_rating(Integer.parseInt(rate1));
                        output_txt.write(String.format("Your film rating was removed successfully\n" +
                                "Film title: %s\n",film1.getFilm_title()));
                    }
                    //give fail for missing requirements
                    else{
                        output_txt.write(String.format("Command Failed\n" +
                                "User ID: %s\n" +
                                "Film ID: %s\n",split_commands_line[2],split_commands_line[3]));
                    }
                    output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                }
                if(commands_line.startsWith("LIST")){
                    if(split_commands_line[1].equals("USER")){
                        output_txt.write(commands_line+"\n"+"\n");
                        User user1=Methods.findUser(users,split_commands_line[2]);
                        //give fail for missing requirements
                        if(user1==null){
                            output_txt.write(String.format("Command Failed\n" +
                                    "User ID: %s\n",split_commands_line[2]));
                        }
                        //if no rate
                        else if(user1.rate.isEmpty()){
                            output_txt.write("There is not any ratings so far");
                        }
                        //list user rate
                        else {
                            for (Map.Entry me : user1.rate.entrySet()) {
                                Films film1 =Methods.findFilm(allFilms, (String) me.getKey());
                                output_txt.write(String.format("%s: %s\n",film1.getFilm_title(),me.getValue()));
                            }
                        }
                        output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                    }
                    if(split_commands_line[1].equals("FILM")){
                        output_txt.write(commands_line+"\n"+"\n");
                        //if no series
                        if(TV_series.number_of_series==0){
                            output_txt.write("No result\n");
                        }
                        //list tv series seasons and episodes
                        else{
                            for(TV_series series: tv_series){
                                output_txt.write(String.format("%s (%s-%s)\n" +
                                        "%s seasons and %s episodes\n\n",series.getFilm_title(),series.getStartDate().split("\\.")[2],series.getEndDate().split("\\.")[2],series.getSeasons(),series.getEpisodes()));
                            }
                        }
                        output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                    }
                    if(split_commands_line[1].equals("FILMS")){
                        if(split_commands_line[3].equals("COUNTRY")){
                            output_txt.write(commands_line+"\n"+"\n");
                            boolean any_country=false;
                            //list film by country
                            for(Films ffilm: allFilms){
                                if(ffilm.getCountry().equals(split_commands_line[4])){
                                    any_country=true;
                                    output_txt.write(String.format("Film title: %s\n" +
                                            "%s min\n" +
                                            "Language: %s\n\n",ffilm.getFilm_title(),ffilm.getRuntime(),ffilm.getLanguage()));
                                }
                            }//give fail for missing requirements
                                if(!any_country){
                                    output_txt.write("No result"+"\n\n");
                                }
                                output_txt.write("-----------------------------------------------------------------------------------------------------\n");
                        }
                        if(split_commands_line[3].equals("RATE")){
                            //List all the films in descending order and categorized according to film rating degrees
                            output_txt.write(commands_line+"\n"+"\n"+"FeatureFilm:\n");
                            ArrayList<Double>featuresrate=new ArrayList<>();
                            for (Feature f1:features) {
                                featuresrate.add(f1.rating_score);
                            }
                            ArrayList<Feature>features1=new ArrayList<>(features);
                           Collections.sort(featuresrate);
                           Collections.reverse(featuresrate);
                            for(double r1:featuresrate){
                                for(Feature f:features1){
                                    if(r1==f.rating_score){
                                        if(f.rating_score==(int)f.rating_score) {
                                            output_txt.write(String.format("%s (%s) Ratings: %s/10 from %s users\n", f.getFilm_title(), f.getRelease_date().split("\\.")[2], (int)f.rating_score, f.count_of_rate));
                                        }else{
                                            String number= new DecimalFormat("#.#").format(f.rating_score);
                                            char number1= number.charAt(0);
                                            char number2= number.charAt(2);
                                            number=number1+","+number2;
                                            output_txt.write(String.format("%s (%s) Ratings: %s/10 from %s users\n", f.getFilm_title(), f.getRelease_date().split("\\.")[2], number, f.count_of_rate));
                                        }
                                        features1.remove(f);
                                        break;
                                    }
                                }
                            }
                            output_txt.write("\nShortFilm:\n");
                            ArrayList<Double>shortrate=new ArrayList<>();
                            for (Short s1:shorts) {
                                shortrate.add(s1.rating_score);
                            }
                            ArrayList<Short>short1=new ArrayList<>(shorts);
                            Collections.sort(shortrate);
                            Collections.reverse(shortrate);
                            for(double r2:shortrate){
                                for(Short s:short1){
                                    if(r2==s.rating_score){
                                        if(s.rating_score==(int)s.rating_score){
                                            output_txt.write(String.format("%s (%s) Ratings: %s/10 from %s users\n",s.getFilm_title(),s.getRelease_date().split("\\.")[2],(int)s.rating_score,s.count_of_rate));
                                        }else{
                                            String number= new DecimalFormat("#.#").format(s.rating_score);
                                            char number1= number.charAt(0);
                                            char number2= number.charAt(2);
                                            number=number1+","+number2;
                                            output_txt.write(String.format("%s (%s) Ratings: %s/10 from %s users\n",s.getFilm_title(),s.getRelease_date().split("\\.")[2],number,s.count_of_rate));
                                        }
                                        short1.remove(s);
                                        break;
                                    }
                                }
                            }
                            output_txt.write("\nDocumentary:\n");
                            ArrayList<Double>documentaryrate=new ArrayList<>();
                            for (Documentary d1:documentaries) {
                                documentaryrate.add(d1.rating_score);
                            }
                            ArrayList<Documentary>documentaries1=new ArrayList<>(documentaries);
                            Collections.sort(documentaryrate);
                            Collections.reverse(documentaryrate);
                            for(double r3:documentaryrate){
                                for(Documentary d:documentaries1){
                                    if(r3==d.rating_score){
                                        if(d.rating_score==(int)d.rating_score){
                                            output_txt.write(String.format("%s (%s) Ratings: %s/10 from %s users\n",d.getFilm_title(),d.getReleaseDay().split("\\.")[2],(int)d.rating_score,d.count_of_rate));
                                        }else{
                                            String number= new DecimalFormat("#.#").format(d.rating_score);
                                            char number1= number.charAt(0);
                                            char number2= number.charAt(2);
                                            number=number1+","+number2;
                                            output_txt.write(String.format("%s (%s) Ratings: %s/10 from %s users\n",d.getFilm_title(),d.getReleaseDay().split("\\.")[2],number,d.count_of_rate));
                                        }
                                        documentaries1.remove(d);
                                        break;
                                    }
                                }
                            }
                            output_txt.write("\nTVSeries:\n");
                            ArrayList<Double>seriesrate=new ArrayList<>();
                            for (TV_series t1:tv_series) {
                                seriesrate.add(t1.rating_score);
                            }
                            ArrayList<TV_series>tv_series1=new ArrayList<>(tv_series);
                            Collections.sort(seriesrate);
                            Collections.reverse(seriesrate);
                            for(double r4:seriesrate){
                                for(TV_series t:tv_series1){
                                    if(r4==t.rating_score){
                                        if(t.rating_score==(int)t.rating_score) {
                                            output_txt.write(String.format("%s (%s-%s) Ratings: %s/10 from %s users\n", t.getFilm_title(), t.getStartDate().split("\\.")[2], t.getEndDate().split("\\.")[2], (int)t.rating_score, t.count_of_rate));
                                        }else{
                                            String number= new DecimalFormat("#.#").format(t.rating_score);
                                            char number1= number.charAt(0);
                                            char number2= number.charAt(2);
                                            number=number1+","+number2;
                                            output_txt.write(String.format("%s (%s-%s) Ratings: %s/10 from %s users\n", t.getFilm_title(), t.getStartDate().split("\\.")[2], t.getEndDate().split("\\.")[2], number, t.count_of_rate));
                                        }
                                        tv_series1.remove(t);
                                        break;
                                    }
                                }
                            }
                            output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");
                        }
                    }
                    if(split_commands_line[1].equals("FEATUREFILMS")){
                        if(split_commands_line[2].equals("BEFORE")){
                            // List all the films released before a specified year.
                            output_txt.write(commands_line+"\n"+"\n");
                            boolean any_film=false;
                            for (Feature films:features) {
                                if(Integer.parseInt(films.getRelease_date().split("\\.")[2])<Integer.parseInt(split_commands_line[3])){
                                    any_film=true;
                                    output_txt.write(String.format("Film title:%s (%s)\n" +
                                            "%s min\n" +
                                            "Language: %s\n\n",films.getFilm_title(),films.getRelease_date().split("\\.")[2],films.getRuntime(),films.getLanguage()));
                                }
                            }
                            //if no film before this date
                            if (!any_film){
                                output_txt.write("No result"+"\n\n");
                            }
                            output_txt.write("-----------------------------------------------------------------------------------------------------\n");
                        }
                        if(split_commands_line[2].equals("AFTER")){
                            //List all the films released after a specified year.
                            output_txt.write(commands_line+"\n"+"\n");
                            boolean any_film=false;
                            for (Feature films:features) {
                                if(Integer.parseInt(films.getRelease_date().split("\\.")[2])>=Integer.parseInt(split_commands_line[3])){
                                    any_film=true;
                                    output_txt.write(String.format("Film title:%s (%s)\n" +
                                            "%s min\n" +
                                            "Language: %s\n\n",films.getFilm_title(),films.getRelease_date().split("\\.")[2],films.getRuntime(),films.getLanguage()));
                                }
                            }
                            //if no film before this date
                            if (!any_film){
                                output_txt.write("No result"+"\n\n");
                            }
                            output_txt.write("-----------------------------------------------------------------------------------------------------\n");
                        }
                    }
                    if(split_commands_line[1].equals("ARTISTS")){
                        //List all the artists from a specified country and display in a categorized order.
                        output_txt.write(commands_line+"\n");
                        boolean any_director=false;
                        output_txt.write("\nDirectors:\n");
                        for (Director ddirector:directors) {
                            if(ddirector.getCountry().equals(split_commands_line[3])){
                                any_director=true;
                                output_txt.write(String.format("%s %s %s\n",ddirector.getName(),ddirector.getSurname(),ddirector.getAgent()));
                            }
                        }
                        //if no director from this country
                        if(!any_director){
                            output_txt.write("No result\n");
                        }
                        //System.out.println("\nWriters:");
                        output_txt.write("\nWriters:\n");
                        boolean any_writer=false;
                        for (Writer wwriter:writers) {
                            if(wwriter.getCountry().equals(split_commands_line[3])){
                                any_writer=true;
                                output_txt.write(String.format("%s %s %s\n",wwriter.getName(),wwriter.getSurname(),wwriter.getWriting_type()));
                            }
                        }
                        //if no writer from this country
                        if(!any_writer){
                            output_txt.write("No result\n");
                        }
                        output_txt.write("\nActors:\n");
                        boolean any_actors=false;
                        for (Actor aactor:actors) {
                            if(aactor.getCountry().equals(split_commands_line[3])){
                                any_actors=true;
                                output_txt.write(String.format("%s %s %s cm\n",aactor.getName(),aactor.getSurname(),aactor.getHeight()));
                            }
                        }
                        //if no actors from this country
                        if(!any_actors){
                            output_txt.write("No result\n");
                    }
                        output_txt.write("\nChildActors:\n");
                        boolean any_childactor=false;
                        for (ChildActor cchildactor:childActors) {
                            if(cchildactor.getCountry().equals(split_commands_line[3])){
                                any_childactor=true;
                                output_txt.write(String.format("%s %s %s\n",cchildactor.getName(),cchildactor.getSurname(),cchildactor.getAge()));
                            }
                        }
                        //if no childactor from this country
                        if(!any_childactor){
                            output_txt.write("No result\n");
                }
                        output_txt.write("\nStuntPerformers:\n");
                        boolean any_stuntperformers=false;
                        for (StuntPerformer sstuntPerformer: stuntPerformers) {
                            if(sstuntPerformer.getCountry().equals(split_commands_line[3])){
                                any_stuntperformers=true;
                                output_txt.write(String.format("%s %s %s cm\n",sstuntPerformer.getName(),sstuntPerformer.getSurname(),sstuntPerformer.getHeight()));
                            }
                        }
                        //if no stunt performer from this country
                        if(!any_stuntperformers){
                            output_txt.write("No result\n");
            }
                        output_txt.write("\n-----------------------------------------------------------------------------------------------------\n");

                    }
    }
}
        }
    }
}