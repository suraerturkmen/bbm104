class ChildActor extends Performer{
    private int age;

    public ChildActor(String people_id, String name, String surname, String country, int age) {
        super(people_id, name, surname, country);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
