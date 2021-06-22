import java.util.HashMap;

class User extends People{
    public HashMap<String,String> rate=new HashMap<String,String>();
    public User(String people_id, String name, String surname, String country) {
        super(people_id, name, surname, country);
    }
}
