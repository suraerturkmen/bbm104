import java.util.ArrayList;

class Methods {
    //I concated the performer with below method
    public static ArrayList<Performer> concatPerformer(ArrayList<Actor> actors,ArrayList<ChildActor> childActors,ArrayList<StuntPerformer> stuntPerformers){
        ArrayList<Performer>allPerformer=new ArrayList<>();
        allPerformer.addAll(actors);
        allPerformer.addAll(childActors);
        allPerformer.addAll(stuntPerformers);
        return allPerformer;
    }
    // I found the users with below method
    public static User findUser(ArrayList<User>users,String check_id){
        User user1=null;
        for (User user:users) {
            if(user.getPeople_id().equals(check_id)){
                user1=user;
            }
        }
        return user1;
    }
    // I found the films with below method
    public static Films findFilm(ArrayList<Films> allFilms,String check_id){
        Films film1=null;
        for (Films film:allFilms) {
            if (film.getFilm_id().equals(check_id)){
                film1=film;
            }
        }
        return film1;
    }
    //The below checks the existence of performers, writers, directors in the given movie.
    public  static boolean Control(ArrayList<Performer> allPerformer,ArrayList<Writer> writers,ArrayList<Director> directors,String[] actor_id,String[] writer_id,String[] director_id){
        int sayi=0;
        int real= actor_id.length;
        boolean actor1=false;
        for (String actor: actor_id) {
            for (Performer real_a: allPerformer) {
                if(actor.equals(real_a.getPeople_id())){
                    sayi+=1;
                }
            }
        }
        if(sayi==real){
            actor1=true;
        }
        int sayi1=0;
        int real1= writer_id.length;
        boolean writer1=false;
        for (String writer: writer_id){
            for (Writer real_a: writers) {
                if(writer.equals(real_a.getPeople_id())){
                    sayi1+=1;
                }
            }
        }
        if(sayi1==real1){
            writer1=true;
        }
        int sayi2=0;
        int real2= director_id.length;
        boolean director1=false;
        for (String director: director_id){
            for (Director real_a: directors) {
                if(director.equals(real_a.getPeople_id())){
                    sayi2+=1;
                }
            }
        }
        if(sayi2==real2){
            director1=true;
        }
        return actor1 && writer1 && director1;
    }
    //The below method checks the existence of performers, writers, directors in the given movie.
    public  static boolean Control(ArrayList<Performer> allPerformer,ArrayList<Director> directors,String[] actor_id,String[] director_id){
        int sayi=0;
        int real= actor_id.length;
        boolean actor1=false;
        for (String actor: actor_id) {
            for (Performer real_a: allPerformer) {
                if(actor.equals(real_a.getPeople_id())){
                    sayi+=1;
                }
            }
        }
        if(sayi==real){
            actor1=true;
        }
        int sayi2=0;
        int real2= director_id.length;
        boolean director1=false;
        for (String director: director_id){
            for (Director real_a: directors) {
                if(director.equals(real_a.getPeople_id())){
                    sayi2+=1;
                }
            }
        }
        if(sayi2==real2){
            director1=true;
        }
        return actor1 && director1;
    }
}


